package controlador;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import modelo.facade.ProductoFacade;
import modelo.vo.ProductoVO;
import vista.AltaProductoUI;
import vista.MenuUI;

public class AltaProductoController extends AltaProductoUI {

	@Override
	protected void addProducto(String referencia, String nom, String descripcion, int cantidad, double precio)
			throws ClassNotFoundException, SQLException {

		ProductoVO producto = new ProductoVO(referencia, nom, descripcion, cantidad, precio);

		ProductoFacade.nuevoProducto(producto);
	}

}
