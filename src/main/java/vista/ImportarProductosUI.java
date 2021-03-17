package vista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.hibernate.Session;

import controlador.HibernateUtils;
import controlador.MenuController;
import modelo.vo.ProductosVO;



public class ImportarProductosUI {

	private JFrame frame;

	private JTextField archivoTextField;
	private JButton btnGuardar;
	private JButton btnVolver;
//	private ArrayList<ComboxItem> ciclos;


	/**
	 * Create the application.
	 */
	public ImportarProductosUI() {
		initialize();
	}

	/**
	 * Muestra el formulario
	 */
	public void show(){
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

		JLabel lblAlta = new JLabel("Fichero XML");
		lblAlta.setBounds(10, 30, 250, 14);
		frame.getContentPane().add(lblAlta);
		archivoTextField = new JTextField();
		archivoTextField.setBounds(10, 50, 200, 20);
		frame.getContentPane().add(archivoTextField);
		
		btnGuardar = new JButton("Importar");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				File archivo = new File("src\\" + archivoTextField.getText()+ ".xml");
				if (!archivo.exists()) {
				    System.out.println("No existe el archivo xml introducido");
					 JOptionPane.showMessageDialog(btnGuardar,"El archivo xml introducido no existe","Advertencia",0);
				}else {
					importarArchivos(archivoTextField.getText());
					JOptionPane.showMessageDialog(btnGuardar, "Productos importados correctamente");
				}
					


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
	
	
	protected void importarArchivos(String archivoXML) {
		// TODO Auto-generated method stub
		
	}

	protected void volverMenuUI() {
		// TODO Auto-generated method stub
		MenuController menuController=new MenuController();
		menuController.show(); 
	}
}
