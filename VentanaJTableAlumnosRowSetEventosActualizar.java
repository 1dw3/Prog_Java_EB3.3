package basesdedatos;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

public class VentanaJTableAlumnosRowSetEventosActualizar extends JFrame implements ListSelectionListener,RowSetListener{

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
	private CachedRowSet crs = null;
	//conexion con la base de datos
	private Connection conexion;
	// main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJTableAlumnosRowSetEventosActualizar frame = new VentanaJTableAlumnosRowSetEventosActualizar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// JFrame
	public VentanaJTableAlumnosRowSetEventosActualizar() {
		
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
			// me conecto usando una conexion
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/bdalumnos", "root", "");
			// desactivo la actualizacion automatica de datos
			conexion.setAutoCommit(false);
			// creo el RowSet
			RowSetFactory myRowSetFactory = null;
      myRowSetFactory = RowSetProvider.newFactory();
	    crs = myRowSetFactory.createCachedRowSet();
	    // selecciono todos los alumnos
	    // usando la conexion anterior
	    crs.setCommand("SELECT * FROM alumnos");
	    crs.execute(conexion);
      
      // controlo los eventos
      crs.addRowSetListener(this);
					
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
				fila.add(crs.getString("nombre"));
				fila.add(crs.getString("apellidos"));
				fila.add(crs.getString("grupo"));
				fila.add("\n\n\n\n\n\n\n");
        datosTabla.add(fila);
			}
			
			// creo la JTable
			DefaultTableModel modelo = new DefaultTableModel(datosTabla, columnas);
      tabla = new JTable(modelo);
      tabla.setPreferredScrollableViewportSize(new Dimension(500, 200));
      tabla.setFillsViewportHeight(true);
      // añado el actionListener a la tabla
      tabla.getSelectionModel().addListSelectionListener(this);
      
      // creo un scroll pane y le añado la tabla
      JScrollPane scrollPane = new JScrollPane(tabla);

      // añado el scroll pane al panel principal
      contentPane.add(scrollPane, BorderLayout.CENTER);
			
			// cierro el RowSet
      // crs.close();

		}
		catch (SQLException | ClassNotFoundException sqle){
			// si NO se ha conectado correctamente
			sqle.printStackTrace();
			System.out.println("Error de Conexión");
		}
		
		// modifico los datos del RowSet para provocar eventos
		// añado un nuevo registro
		try {
			crs.setCommand("UPDATE alumnos SET grupo='1DW3' WHERE dni='11111111A'");
			crs.execute(conexion);
			//crs.moveToInsertRow();
	    //crs.updateString("dni", "99900000A");
	    //crs.updateString("nombre", "Bartolo");
	    //crs.updateString("apellidos", "López García");
	    //crs.updateString("grupo", "1DW3");
	    //crs.insertRow();
	    //crs.moveToCurrentRow();
	    //crs.acceptChanges(conexion);
			// actualizo la tabla
			//tabla.setValueAt(this.txtDNI.getText(), fila, 0);
			//tabla.setValueAt(this.txtNombre.getText(), fila, 1);
			//tabla.setValueAt(this.txtApellidos.getText(), fila, 2);
			//tabla.setValueAt(this.txtGrupo.getText(), fila, 3);
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			// sqle.printStackTrace();
			System.out.println("Error. No se ha podido añadir el registro");
		}
	
		// cierro la conexion
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
	}

	// controlo los eventos del RowSet
	@Override
	public void cursorMoved(RowSetEvent rse) {
		// movimiento del cursor
		int fila;
		try {
			// obtengo el valor de la nueva fila
			fila = this.crs.getRow();
			// actualizo el valor
			this.lblTexto.setText("Fila Actual: "+fila);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void rowChanged(RowSetEvent rse) {
		// cambio de una fila
		// obtengo el valor de la fila cambiada
		int fila;
		try {
			fila = this.crs.getRow();
			// actualizo el valor
			this.lblTexto.setText("La Fila "+fila+ " ha sido modificada.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void rowSetChanged(RowSetEvent rse) {
		// cambio en el RowSet
		// actualizo el valor
		this.lblTexto.setText("El Rowset ha sido modificado.");
		
	}

	// controlo los clic que se hacen sobre la tabla
	@Override
	public void valueChanged(ListSelectionEvent lse) {
		// tengo que sumar 1 al indice de la fila de la tabla ya que 
		// los indices del RowSet empiezan en 1
		this.lblTexto.setText("Registro " + (tabla.getSelectedRow()+1) + " seleccionado.");
	}

}
