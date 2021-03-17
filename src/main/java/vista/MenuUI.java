package vista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;

import controlador.AltaProductoController;
import controlador.BajaProductoController;
import controlador.ExportarProductosController;
import controlador.ImportarProductosController;
import controlador.ListarProductosController;
import controlador.ProductoReferenciaController;


public abstract class MenuUI {
	private JFrame frame;
	private JTextField numeroTextField;

	private JButton btnGuardar;
	private Integer id;
	private String referencia;

	/**
	 * Create the application.
	 */
	public MenuUI() {
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
		id = null;
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 676, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblAlta = new JLabel("1. Alta producto");
		lblAlta.setBounds(10, 30, 250, 14);
		frame.getContentPane().add(lblAlta);

		JLabel lblBaja = new JLabel("2. Baja producto");
		lblBaja.setBounds(10, 80, 250, 14);
		frame.getContentPane().add(lblBaja);

		JLabel lblObtenerProducto = new JLabel("3. Obtener producto por referencia");
		lblObtenerProducto.setBounds(10, 130, 250, 14);
		frame.getContentPane().add(lblObtenerProducto);

		JLabel lblListarProductos = new JLabel("4. Listar productos");
		lblListarProductos.setBounds(10, 180, 250, 14);
		frame.getContentPane().add(lblListarProductos);

		JLabel lblImportarProductos = new JLabel(
				"5. Importar productos: Importa una lista de productos desde un fichero XML.");
		lblImportarProductos.setBounds(10, 230, 500, 14);
		frame.getContentPane().add(lblImportarProductos);

		JLabel lblExportarProductos = new JLabel(
				"6. Exportar productos: Exportar en un fichero XML todos los productos de la base de datos.");
		lblExportarProductos.setBounds(10, 280, 900, 14);
		frame.getContentPane().add(lblExportarProductos);

		JLabel lblSalir = new JLabel("7. Salir");
		lblSalir.setBounds(10, 330, 250, 14);
		frame.getContentPane().add(lblSalir);

		numeroTextField = new JTextField();
		numeroTextField.setBounds(10, 380, 372, 20);
		frame.getContentPane().add(numeroTextField);

		btnGuardar = new JButton("Aceptar");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int horas = 0;
				try {
					horas = Integer.parseInt(numeroTextField.getText());
					if (horas == 0 || horas > 7) {
						System.out.println("Error, el valor introducido no es un digito.");
						JOptionPane.showMessageDialog(btnGuardar,
								"El dígito introducido no corresponde a ninguna opción del menú", "Advertencia", 0);
					}
					if (horas == 1) {
						altaProducto();

					}
					if (horas == 2) {
						bajaProducto();

					}

					if (horas == 3) {
						productoReferencia();

					}

					if (horas == 4) {
						listarProductos();

					}
					if (horas == 5) {
						importarProductos();

					}
					if (horas == 6) {
						exportarProductos();

					}
					if (horas == 7) {
						System.exit(0);

					}

				} catch (NumberFormatException e) {
					System.out.println("Error, el valor introducido no es un digito.");
					JOptionPane.showMessageDialog(btnGuardar, "El valor introducido no es un dígito", "Advertencia", 0);
				}

				if (id == null) {

					try {
					} catch (NumberFormatException e) {
						System.out.println("Error, el valor introducido no es un digito.");
						JOptionPane.showMessageDialog(btnGuardar, "El valor introducido no es un dígito", "Advertencia",
								0);
					}

				} else {

					try {

					} catch (NumberFormatException e) {
						System.out.println("Error, el valor introducido no es un digito.");
						JOptionPane.showMessageDialog(btnGuardar, "El valor introducido no es un dígito", "Advertencia",
								0);
					}
				}

			}
		});
		btnGuardar.setBounds(400, 380, 89, 20);
		frame.getContentPane().add(btnGuardar);

	}

	protected void listarProductos() {
		// TODO Auto-generated method stub
		ListarProductosController listarProductos = new ListarProductosController();
		listarProductos.show();
	}

	protected void altaProducto() {
		// TODO Auto-generated method stub

		AltaProductoController altaProductoController = new AltaProductoController();
		altaProductoController.show();
	}

	protected void bajaProducto() {
		// TODO Auto-generated method stub

		BajaProductoController bajaProductoController = new BajaProductoController(referencia);
		bajaProductoController.show();
	}

	protected void exportarProductos() {
		// TODO Auto-generated method stub

		ExportarProductosController exportarProductosController = new ExportarProductosController();
		exportarProductosController.show();
	}

	protected void importarProductos() {
		// TODO Auto-generated method stub

		ImportarProductosController importarProductosController = new ImportarProductosController();
		importarProductosController.show();
	}

	protected void productoReferencia() {
		// TODO Auto-generated method stub

		ProductoReferenciaController productoRefenrenciaController = new ProductoReferenciaController();
		productoRefenrenciaController.show();
	}
}
