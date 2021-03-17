package vista;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.hibernate.Session;

import controlador.HibernateUtils;
import controlador.MenuController;
import modelo.vo.ProductoVO;

public abstract class ListarProductosUI {

	private JFrame frame;
	private JButton btnVolver;
	// el modelo de tabla, aquí van a estar los datos.
	private DefaultTableModel model;

	/**
	 * Create the application.
	 */
	public ListarProductosUI() {
		try {
			initialize();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	private void initialize() throws ClassNotFoundException {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 676, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblAlta = new JLabel("Productos");
		lblAlta.setBounds(10, 30, 250, 14);
		frame.getContentPane().add(lblAlta);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 650, 287);
		frame.getContentPane().add(scrollPane);

		model = listarProductosVO();
		JTable tablaProductos = new JTable(model);
		
	    TableColumn columna;
	      columna=tablaProductos.getColumnModel().getColumn(0);
	      columna.setPreferredWidth(90);
	      columna.setMaxWidth(90);
	      columna.setMinWidth(90);
	      TableColumn columna1;
	      columna1=tablaProductos.getColumnModel().getColumn(1);
	      columna1.setPreferredWidth(140);
	      columna1.setMaxWidth(140);
	      columna1.setMinWidth(140);
	      TableColumn columna2;
	      columna2=tablaProductos.getColumnModel().getColumn(2);
	      columna2.setPreferredWidth(310);
	      columna2.setMaxWidth(310);
	      columna2.setMinWidth(310);
	      
	      tablaProductos.getColumnModel().getColumn(2).setCellRenderer(
	    	      new TextAreaRenderer());
	    	    // We hard-code the height of rows 0 and 5 to be 100
	    	    tablaProductos.setRowHeight(0, 100);
	    	    tablaProductos.setRowHeight(5, 100);
	    	    
	    	    
	      tablaProductos.setRowHeight(35);
	      TableColumn columna3;
	      columna3=tablaProductos.getColumnModel().getColumn(3);
	      columna3.setPreferredWidth(60);
	      columna3.setMaxWidth(60);
	      columna3.setMinWidth(60);
	      TableColumn columna4;
	      columna4=tablaProductos.getColumnModel().getColumn(4);
	      columna4.setPreferredWidth(50);
	      columna4.setMaxWidth(50);
	      columna4.setMinWidth(50);
	      scrollPane.setViewportView(tablaProductos);

		btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				volverMenuUI();

			}
		});
		btnVolver.setBounds(10, 380, 89, 20);
		frame.getContentPane().add(btnVolver);

	}

	protected void volverMenuUI() {
		// TODO Auto-generated method stub
		MenuController menuController = new MenuController();
		menuController.show();
	}

	protected DefaultTableModel listarProductosVO() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
