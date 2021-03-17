package controlador;

import java.sql.SQLException;

import modelo.facade.ProductoFacade;
import vista.BajaProductoUI;

public class BajaProductoController extends BajaProductoUI {
	public BajaProductoController(String referencia) {
		super(referencia);
	}

	@Override
	protected boolean eliminarProducto(String referencia) throws ClassNotFoundException, SQLException {
		return ProductoFacade.eliminarProducto(referencia);

	}

}
