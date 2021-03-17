package controlador;

import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import modelo.vo.ProductoVO;
import modelo.facade.ProductoFacade;
import vista.ListarProductosUI;

public class ListarProductosController extends ListarProductosUI{
	
	@Override
	protected DefaultTableModel listarProductosVO() throws ClassNotFoundException {

		DefaultTableModel listaProductos = null;
		listaProductos = ProductoFacade.listarProductos();
		
		return listaProductos;
	}

}
