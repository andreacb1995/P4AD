package controlador;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import modelo.facade.ProductoFacade;
import vista.ExportarProductosUI;

public class ExportarProductosController extends ExportarProductosUI {
	@Override
	protected void exportarArchivos(String archivoXML) {

		try {
			ProductoFacade.exportarProductos(archivoXML);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
