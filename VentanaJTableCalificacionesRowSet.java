package ebaluaketa3;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class VentanaJTableCalificacionesRowSet extends JFrame {

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
	// CachedRowSet
	static CachedRowSet crs = null;
	
	// main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJTableCalificacionesRowSet frame = new VentanaJTableCalificacionesRowSet();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// JFrame
	public VentanaJTableCalificacionesRowSet() {
		
		setTitle("Ventana JTable");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		lblTexto = new JLabel("Calificaciones de los Alumnos");
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
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bdalumnos", "root", "");
			// si se ha conectado correctamente
			conexion.setAutoCommit(false);
      RowSetFactory myRowSetFactory = null;
      myRowSetFactory = RowSetProvider.newFactory();
	    crs = myRowSetFactory.createCachedRowSet();
	    crs.setUrl("jdbc:mysql://localhost/bdalumnos");
	    crs.setUsername("root");
	    crs.setPassword("");
	    crs.setConcurrency(CachedRowSet.CONCUR_UPDATABLE);
	    // selecciono todos los alumnos
	    crs.setCommand("SELECT * FROM calificaciones WHERE dni='11111111A'");
	    crs.execute();
					
			// cabeceras de las columnas
			ResultSetMetaData metaDatos = crs.getMetaData();
			// Se obtiene el número de columnas.
			int numeroColumnas = metaDatos.getColumnCount();
			
			columnas = new Vector<String>();
			// Se obtiene cada una de las etiquetas para cada columna
			for (int i = 0; i < numeroColumnas; i++){
				// cojo el valor de la etiqueta de la columna
				// los datos del rs empiezan en 1
				columnas.add(metaDatos.getColumnLabel(i + 1));
			}		
			
			// creo el vector para los datos de la tabla
			datosTabla = new Vector<Vector<String>>();
			
			// añado uno a uno los alumnos al vector de datos
			while (crs.next()) {
				Vector<String> fila = new Vector<String>();
				fila.add(crs.getString("dni"));
				fila.add(crs.getString("codasignatura"));
				fila.add(crs.getString("nota"));
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
			crs.close();

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
