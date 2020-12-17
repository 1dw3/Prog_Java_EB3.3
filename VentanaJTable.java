package ebaluaketa3;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class VentanaJTable extends JFrame {

	//defino los componentes
	private static final long	serialVersionUID = 1183812776304203166L;
	private JPanel contentPane;
	private JLabel lblTexto;
	private JButton btnSalir;
	// JTable
	private JTable tabla;
	// cabeceras de las columnas
	private Vector<String> columnas;
	// datos de la tabla
	private Vector<Vector<String>> datosTabla;
	
	// main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJTable frame = new VentanaJTable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// JFrame
	public VentanaJTable() {
		
		setTitle("Ventana JTable");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		lblTexto = new JLabel("Datos de los Alumnos");
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTexto.setFont(new Font("Lucida Console", Font.PLAIN, 24));
		contentPane.add(lblTexto, BorderLayout.NORTH);
		
		// boton Salir
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});
		contentPane.add(btnSalir, BorderLayout.SOUTH);
		
		// JTable
		// cabeceras de las columnas
		columnas = new Vector<String>();
		columnas.add("DNI");
		columnas.add("Nombre");
		columnas.add("Apellidos");
		columnas.add("Grupo");
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bdalumnos", "root", "");
			// si se ha conectado correctamente
			
			// selecciono todos los alumnos
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM alumnos");
			
			// creo el vector para los datos de la tabla
			datosTabla = new Vector<Vector<String>>();
			
			// añado uno a uno los alumnos al vector de datos
			while (rs.next()) {
				Vector<String> fila = new Vector<String>();
				fila.add(rs.getString("dni"));
				fila.add(rs.getString("nombre"));
				fila.add(rs.getString("apellidos"));
				fila.add(rs.getString("grupo"));
				fila.add("\n\n\n\n\n\n\n");
        datosTabla.add(fila);
			}
			
			// creo la JTable
			DefaultTableModel modelo = new DefaultTableModel(datosTabla, columnas);
      tabla = new JTable(modelo);
      tabla.setPreferredScrollableViewportSize(new Dimension(500, 200));
      tabla.setFillsViewportHeight(true);

      // creo un scroll pane y le añado la tabla
      JScrollPane scrollPane = new JScrollPane(tabla);

      // añado el scroll pane al panel principal
      contentPane.add(scrollPane, BorderLayout.CENTER);
			
			// cierro el ResultSet
			rs.close();
			// cierro el Statement despues de realizar la consulta
			st.close();

			// cierro la conexion con la base de datos
			conexion.close();
		}
		catch (SQLException | ClassNotFoundException sqle){
			// si NO se ha conectado correctamente
			sqle.printStackTrace();
			System.out.println("Error de Conexión");
		}
		
	}

}
