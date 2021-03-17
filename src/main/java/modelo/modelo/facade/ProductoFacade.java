package modelo.facade;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.vo.ProductoVO;
import modelo.vo.ProductosVO;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import controlador.HibernateUtils;

public class ProductoFacade {
	static ArrayList<ProductoVO> listaProductos;

	public ProductoFacade() {

	}

	public static void nuevoProducto(ProductoVO producto) throws SQLException, ClassNotFoundException {

		Session session = HibernateUtils.INSTANCE.getSessionFactory().openSession();

		session.beginTransaction();
		ProductoVO p = new ProductoVO(producto.getReferencia(), producto.getNombre(), producto.getDescripcion(),
				producto.getCantidad(), producto.getPrecio());

		session.save(p);
		session.getTransaction().commit();
		System.out.println("Producto insertado correctamente");

		HibernateUtils.INSTANCE.cerrarSessionFactory();
		System.out.println("Cerrando la session...");

	}

	public static DefaultTableModel listarProductos() throws ClassNotFoundException {
		DefaultTableModel model;
		String[] nombreColumnas = { "Referencia", "Nombre", "Descripción", "Cantidad", "Precio" };

		model = new DefaultTableModel(null, nombreColumnas);

		Session session = HibernateUtils.INSTANCE.getSessionFactory().openSession();
		session.beginTransaction();
		List<ProductoVO> result = session.createQuery("from ProductoVO").list();

		for (ProductoVO producto : result) {
			// añado la fila al modelo
			Object[] aux = { producto.getReferencia(), producto.getNombre(),
					producto.getDescripcion(), producto.getCantidad(), producto.getPrecio() };

			// añado la fila al modelo
			model.addRow(aux);

		}
		HibernateUtils.INSTANCE.cerrarSessionFactory();
		System.out.println("Cerrando la session...");
		return model;

	}

	public static boolean eliminarProducto(String referencia) throws ClassNotFoundException {

		Session session = HibernateUtils.INSTANCE.getSessionFactory().openSession();
		session.beginTransaction();

//		 Query query = session.createQuery("SELECT p FROM ProductoVO p where p.referencia like "+ "'"+ referencia+"'");
		ProductoVO producto = (ProductoVO) session
				.createQuery("SELECT p FROM ProductoVO p where p.referencia like " + "'" + referencia + "'")
				.uniqueResult();
		if (producto == null) {
			return false;
		}

		session.delete(producto);
		System.out.println("Producto eliminado correctamente");

		session.getTransaction().commit();

		HibernateUtils.INSTANCE.cerrarSessionFactory();
		System.out.println("Cerrando la session...");
		return true;

	}

	public static ProductoVO buscarProducto(String referencia) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.INSTANCE.getSessionFactory().openSession();
		Query query = session
				.createQuery("SELECT p FROM ProductoVO p where p.referencia like " + "'" + referencia + "'");
		ProductoVO producto = (ProductoVO) session
				.createQuery("SELECT p FROM ProductoVO p where p.referencia like " + "'" + referencia + "'")
				.uniqueResult();
		session.getTransaction().commit();

		HibernateUtils.INSTANCE.cerrarSessionFactory();
		System.out.println("Cerrando la session...");
		return producto;
	}

	public static void importarProductos(String archivoXML) throws JAXBException, FileNotFoundException {

		Session session = HibernateUtils.INSTANCE.getSessionFactory().openSession();

		session.beginTransaction();

		// Ahora leemos el XML e instanciamos las clases Java
		JAXBContext context = JAXBContext.newInstance(ProductosVO.class);

		Unmarshaller um = context.createUnmarshaller();
		ProductosVO productos = null;
		productos = (ProductosVO) um.unmarshal(new FileReader("src\\" + archivoXML + ".xml"));

		for (int i = 0; i < productos.getProducto().toArray().length; i++) {
			session.save(productos.getProducto().get(i));

		}

		session.getTransaction().commit();
		System.out.println("Productos insertados correctamente");

		HibernateUtils.INSTANCE.cerrarSessionFactory();
		System.out.println("Cerrando la session...");

	}

	public static void exportarProductos(String archivoXML) throws IOException, JAXBException {
		// TODO Auto-generated method stub
		ArrayList listaProductos = new ArrayList();
		Session session = HibernateUtils.INSTANCE.getSessionFactory().openSession();
		session.beginTransaction();

		List<ProductoVO> result = session.createQuery("from ProductoVO").list();
		ProductosVO productos = new ProductosVO();
		for (ProductoVO producto : result) {
			listaProductos.add(producto);

		}

		productos.setProducto(listaProductos);

		// Creamos el contexto e instanciamos el marshaller
		JAXBContext context = JAXBContext.newInstance(ProductosVO.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(productos, System.out);

		Writer w = null;
		try {
			w = new FileWriter("src\\" + archivoXML + ".xml");
			m.marshal(productos, w);
		} finally {
			try {
				w.close();
			} catch (Exception e) {
			}
		}
	}

}
