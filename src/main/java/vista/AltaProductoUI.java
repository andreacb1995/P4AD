package vista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controlador.MenuController;

import javax.swing.JComboBox;

public abstract class AltaProductoUI {

	private JFrame frame;
	private JTextField nombreTextField;
	private JTextField referenciaTextField;
	private JTextField descripcionTextField;
	private JTextField precioTextField;
	private JTextField cantidadTextField;
	private JButton btnGuardar;
	private JButton btnVolver;

	/**
	 * Create the application.
	 */
	public AltaProductoUI() {
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

		JLabel lblReferencia = new JLabel("Referencia");
		lblReferencia.setBounds(10, 30, 250, 14);
		frame.getContentPane().add(lblReferencia);

		referenciaTextField = new JTextField();
		referenciaTextField.setBounds(10, 50, 200, 20);
		frame.getContentPane().add(referenciaTextField);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 80, 250, 14);
		frame.getContentPane().add(lblNombre);

		nombreTextField = new JTextField();
		nombreTextField.setBounds(10, 100, 200, 20);
		frame.getContentPane().add(nombreTextField);

		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(10, 130, 250, 14);
		frame.getContentPane().add(lblDescripcion);

		descripcionTextField = new JTextField();
		descripcionTextField.setBounds(10, 150, 200, 20);
		frame.getContentPane().add(descripcionTextField);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 180, 250, 14);
		frame.getContentPane().add(lblCantidad);

		cantidadTextField = new JTextField();
		cantidadTextField.setBounds(10, 200, 200, 20);
		frame.getContentPane().add(cantidadTextField);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(10, 230, 500, 14);
		frame.getContentPane().add(lblPrecio);

		precioTextField = new JTextField();
		precioTextField.setBounds(10, 250, 200, 20);
		frame.getContentPane().add(precioTextField);

		btnGuardar = new JButton("Añadir");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				String referencia = referenciaTextField.getText();
				String nombre = nombreTextField.getText();
				String descripcion = descripcionTextField.getText();
				int cantidad = 0;
				if (referencia.equals("") || nombre.equals("") || descripcion.equals("")
						|| cantidadTextField.getText().equals("") || precioTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(btnGuardar, "Campos vacíos");
					return;
				}

				if (referencia.length() > 10) {
					JOptionPane.showMessageDialog(btnGuardar, "La referencia no puede tener más de 10 caracteres");
				}
				if (nombre.length() > 50) {
					JOptionPane.showMessageDialog(btnGuardar, "El nombre no puede tener más de 50 caracteres");
				}
				if (descripcion.length() > 200) {
					JOptionPane.showMessageDialog(btnGuardar, "La descripcion no puede tener más de 200 caracteres");
				}

				if (cantidadTextField.getText().equals("0")) {
					System.out.println("Error, el valor de la cantidad no puede ser 0.");
					JOptionPane.showMessageDialog(btnGuardar, "El valor de la cantidad no puede ser 0", "Advertencia",
							0);
					return;
				}
				if (precioTextField.getText().equals("0")) {
					System.out.println("Error, el valor del precio no puede ser 0.");
					JOptionPane.showMessageDialog(btnGuardar, "El valor del precio no puede ser 0", "Advertencia", 0);
					return;
				}

				try {
					cantidad = Integer.parseInt(cantidadTextField.getText());
					int cifras = 0;// esta variable es el contador de cifras
					while (cantidad != 0) {// mientras a n le queden cifras
						cantidad = cantidad / 10;// le quitamos el último dígito
						cifras++; // sumamos 1 al contador de cifras
					}
					if (cifras > 4) {
						JOptionPane.showMessageDialog(btnGuardar,
								"El valor de la cantidad no puede tener más de 4 dígitos");
						return;
					}

				} catch (NumberFormatException e) {
					System.out.println("Error, el valor de la cantidad introducido no es un digito.");
					JOptionPane.showMessageDialog(btnGuardar, "El valor de la cantidad introducido no es un dígito",
							"Advertencia", 0);
					return;
				}

				boolean hayPunto = false;
				StringBuffer parteEntera = new StringBuffer();
				StringBuffer parteDecimal = new StringBuffer();
				int i = 0, posicionDelPunto = 0;

				for (i = 0; i < precioTextField.getText().length(); i++)
					if (precioTextField.getText().charAt(i) == '.')// Detectar si hay un punto decimal en la cadena
						hayPunto = true;
				if (hayPunto)// Si hay punto guardar la posicion donde se encuentra el carater punto
					posicionDelPunto = precioTextField.getText().indexOf('.');// (si la cadena tiene varios puntos,
																				// detecta donde esta el primero).
				else {// Si no hay punto; no es decimal
					avisoNoEsDecimal();
					return;
				}

				if (posicionDelPunto == precioTextField.getText().length() - 1 || posicionDelPunto == 0) {// Si el punto
																											// esta al
																											// final o
																											// al
																											// principio
																											// no es
																											// decimal
					avisoNoEsDecimal();
					return;
				}

				for (i = 0; i < posicionDelPunto; i++)
					parteEntera.append(precioTextField.getText().charAt(i));// Guardar la parte entera en una variable

				for (i = 0; i < parteEntera.length(); i++)
					if (!Character.isDigit(parteEntera.charAt(i))) {
						// Si alguno de los caracteres de la parte entera no son digitos no es decimal
						avisoNoEsDecimal();
						return;
					}

				for (i = posicionDelPunto + 1; i < precioTextField.getText().length(); i++)
					parteDecimal.append(precioTextField.getText().charAt(i)); // Guardar la parte decimal en una
																				// variable

				for (i = 0; i < parteDecimal.length(); i++)
					if (!Character.isDigit(parteDecimal.charAt(i))) {
						// Si alguno de los caracteres de la parte decimal no es un digito no es decimal
						avisoNoEsDecimal();
						return;
					}

				double precio = Double.parseDouble(precioTextField.getText());
				try {

					addProducto(referencia, nombre, descripcion, cantidad, precio);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(btnGuardar, "Producto añadido correctamente");
			}

		});
		btnGuardar.setBounds(10, 300, 89, 20);
		frame.getContentPane().add(btnGuardar);

		btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				volverMenuUI();

			}
		});
		btnVolver.setBounds(120, 300, 89, 20);
		frame.getContentPane().add(btnVolver);
	}

	protected void volverMenuUI() {
		// TODO Auto-generated method stub
		MenuController menuController = new MenuController();
		menuController.show();
	}

	protected void addProducto(String referencia, String nom, String descripcion, int cantidad, double precio)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	}

	public void avisoNoEsDecimal() {
		JOptionPane.showMessageDialog(btnGuardar, "El valor del precio introducido no es un decimal", "Advertencia", 0);

	}

}
