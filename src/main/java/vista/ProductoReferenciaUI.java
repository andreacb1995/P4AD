package vista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.query.Query;

import controlador.HibernateUtils;
import controlador.MenuController;
import modelo.vo.ProductoVO;

public class ProductoReferenciaUI {
	private JFrame frame;
	private JTextField numeroTextField;
	private JButton btnGuardar;
	private JButton btnVolver;
	private String referencia;

	/**
	 * Create the application.
	 */
	public ProductoReferenciaUI() {
		initialize();
	}

	/**
	 * Muestra el formulario
	 */
	public void show() {
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 676, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblAlta = new JLabel("Referencia");
		lblAlta.setBounds(10, 30, 250, 14);
		frame.getContentPane().add(lblAlta);

		numeroTextField = new JTextField();
		numeroTextField.setBounds(10, 50, 200, 20);
		frame.getContentPane().add(numeroTextField);

		btnGuardar = new JButton("Buscar");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				referencia = String.valueOf(numeroTextField.getText());
				Session session = HibernateUtils.INSTANCE.getSessionFactory().openSession();
				Query query = session
						.createQuery("SELECT p FROM ProductoVO p where p.referencia like " + "'" + referencia + "'");
				ProductoVO producto = (ProductoVO) session
						.createQuery("SELECT p FROM ProductoVO p where p.referencia like " + "'" + referencia + "'")
						.uniqueResult();
				if (producto == null) {
					JOptionPane.showMessageDialog(btnGuardar, "El producto con la referencia introducida no existe",
							"Advertencia", 0);
					return;
				}
				HibernateUtils.INSTANCE.cerrarSessionFactory();
				System.out.println("Cerrando la session...");
				JOptionPane.showMessageDialog(btnGuardar,
						"PRODUCTO: \n" + "Referencia: " + producto.getReferencia() + "\nNombre: " + producto.getNombre()
								+ "\nDescripción: " + producto.getDescripcion() + "\nCantidad: "
								+ producto.getCantidad() + "\nPrecio: " + producto.getPrecio() + "");

			}
		});
		btnGuardar.setBounds(10, 80, 89, 20);
		frame.getContentPane().add(btnGuardar);

		btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				volverMenuUI();

			}
		});
		btnVolver.setBounds(120, 80, 89, 20);
		frame.getContentPane().add(btnVolver);

	}

	protected void buscarProductoReferencia(String r) {
		// TODO Auto-generated method stub

	}

	protected void volverMenuUI() {
		// TODO Auto-generated method stub
		MenuController menuController = new MenuController();
		menuController.show();
	}

}
