package controlador;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

import modelo.facade.ProductoFacade;
import modelo.vo.ProductoVO;
import vista.ImportarProductosUI;

public class ImportarProductosController extends ImportarProductosUI {
	@Override
	protected void importarArchivos(String archivoXML) {

		try {
			ProductoFacade.importarProductos(archivoXML);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
