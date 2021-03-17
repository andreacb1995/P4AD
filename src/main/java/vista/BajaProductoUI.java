package vista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.MenuController;

public class BajaProductoUI {
	private JFrame frame;

	private JTextField numeroTextField;

	private JButton btnGuardar;
	private JButton btnVolver;

	private String referencia;
//	private ArrayList<ComboxItem> ciclos;

	/**
	 * Create the application.
	 */
	public BajaProductoUI(String referencia) {
		this.referencia = referencia;
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

		btnGuardar = new JButton("Dar de baja");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				referencia = numeroTextField.getText();
				try {
					if (!eliminarProducto(referencia)) {
						JOptionPane.showMessageDialog(btnGuardar, "El valor introducido no corresponde con ningún producto");
					}else {
						eliminarProducto(referencia);
						JOptionPane.showMessageDialog(btnGuardar, "Producto eliminado correctamente");
					}

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnGuardar.setBounds(10, 80, 100, 20);
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

	protected void volverMenuUI() {
		// TODO Auto-generated method stub
		MenuController menuController = new MenuController();
		menuController.show();
	}

	protected boolean eliminarProducto(String referencia) throws ClassNotFoundException, SQLException {
		return false;
		// TODO Auto-generated method stub

	}
}
