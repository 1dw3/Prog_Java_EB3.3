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
import javax.swing.text.JTextComponent;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;

public class VentanaJTableAsignaturasRowSetEventosCampos extends JFrame implements ListSelectionListener,RowSetListener,ActionListener,FocusListener{

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
	
	// JScrollPane de la tabla
	private JScrollPane scrollPane;
	
	// etiquetas de los campos de la tabla
	private JLabel lblCodAsignatura;
	private JLabel lblNombreAsignatura;
	private JLabel lblDescripcion;
	// campos de texto de los campos de la tabla
	private JTextField txtCodAsignatura;
	private JTextField txtNombreAsignatura;
	private JTextField txtDescripcion;
	// botones de desplazamiento
	private JButton btnPrimero;
	private JButton btnAnterior;
	private JButton btnSiguiente;
	private JButton btnUltimo;
	private JButton btnNuevo;
	private JButton btnInsertar;
	private JButton btnActualizar;
	private JButton btnBorrar;
	
	// main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJTableAsignaturasRowSetEventosCampos frame = new VentanaJTableAsignaturasRowSetEventosCampos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// JFrame
	public VentanaJTableAsignaturasRowSetEventosCampos() {
		
		setTitle("Ventana JTable");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 993, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTexto = new JLabel("Asignaturas");
		lblTexto.setBounds(5, 5, 962, 25);
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTexto.setFont(new Font("Lucida Console", Font.PLAIN, 24));
		contentPane.add(lblTexto);
		
		// boton Salir
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(277, 472, 424, 23);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// salgo del programa
				System.exit(0);
			}
		});
		contentPane.add(btnSalir);
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/bdalumnos", "root", "");
			// si se ha conectado correctamente
			conexion.setAutoCommit(false);
      RowSetFactory myRowSetFactory = null;
      myRowSetFactory = RowSetProvider.newFactory();
      crs = myRowSetFactory.createCachedRowSet();
    
	    // selecciono todas las asignaturas
      crs.setCommand("SELECT * FROM asignaturas");
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
			
			// añado una a una las asignaturas al vector de datos
			while (crs.next()) {
				Vector<String> fila = new Vector<String>();
				fila.add(crs.getString("codasignatura"));
				fila.add(crs.getString("nombreasignatura"));
				fila.add(crs.getString("descripcion"));
				fila.add("\n\n\n\n\n\n\n");
        datosTabla.add(fila);
			}
			
			// creo la JTable
			DefaultTableModel modelo = new DefaultTableModel(datosTabla, columnas);
      
      scrollPane = new JScrollPane();
      scrollPane.setBounds(5, 259, 960, 202);
      contentPane.add(scrollPane);
      tabla = new JTable(modelo);
      scrollPane.setViewportView(tabla);
      tabla.setPreferredScrollableViewportSize(new Dimension(500, 200));
      tabla.setFillsViewportHeight(true);
      
      // campos
      lblCodAsignatura = new JLabel("C\u00F3digo Asignatura");
      lblCodAsignatura.setFont(new Font("Arial", Font.BOLD, 14));
      lblCodAsignatura.setBounds(15, 39, 131, 14);
      contentPane.add(lblCodAsignatura);
      
      lblDescripcion = new JLabel("Descripci\u00F3n");
      lblDescripcion.setFont(new Font("Arial", Font.BOLD, 14));
      lblDescripcion.setBounds(15, 92, 131, 14);
      contentPane.add(lblDescripcion);
      
      lblNombreAsignatura = new JLabel("Nombre Asignatura");
      lblNombreAsignatura.setFont(new Font("Arial", Font.BOLD, 14));
      lblNombreAsignatura.setBounds(15, 61, 131, 14);
      contentPane.add(lblNombreAsignatura);
      
      txtCodAsignatura = new JTextField();
      txtCodAsignatura.setFont(new Font("Arial", Font.BOLD, 14));
      txtCodAsignatura.setBounds(149, 33, 245, 20);
      txtCodAsignatura.addFocusListener(this);
      contentPane.add(txtCodAsignatura);
      txtCodAsignatura.setColumns(10);
      
      txtNombreAsignatura = new JTextField();
      txtNombreAsignatura.setFont(new Font("Arial", Font.BOLD, 14));
      txtNombreAsignatura.setColumns(10);
      txtNombreAsignatura.setBounds(149, 58, 245, 20);
      txtNombreAsignatura.addFocusListener(this);
      contentPane.add(txtNombreAsignatura);
      
      txtDescripcion = new JTextField();
      txtDescripcion.setFont(new Font("Arial", Font.BOLD, 14));
      txtDescripcion.setColumns(10);
      txtDescripcion.setBounds(149, 86, 245, 20);
      txtDescripcion.addFocusListener(this);
      contentPane.add(txtDescripcion);
      
      // botones
      btnPrimero = new JButton("Primero");
      btnPrimero.setBounds(5, 144, 89, 23);
      btnPrimero.addActionListener(this);
      contentPane.add(btnPrimero);
      
      btnAnterior = new JButton("Anterior");
      btnAnterior.setBounds(104, 144, 89, 23);
      btnAnterior.addActionListener(this);
      contentPane.add(btnAnterior);
      
      btnSiguiente = new JButton("Siguiente");
      btnSiguiente.setBounds(203, 144, 89, 23);
      btnSiguiente.addActionListener(this);
      contentPane.add(btnSiguiente);
      
      btnUltimo = new JButton("Ultimo");
      btnUltimo.setBounds(305, 144, 89, 23);
      btnUltimo.addActionListener(this);
      contentPane.add(btnUltimo);
      
      btnNuevo = new JButton("Nuevo");
      btnNuevo.setBounds(404, 30, 89, 23);
      btnNuevo.addActionListener(this);
      contentPane.add(btnNuevo);
      
      btnInsertar = new JButton("Insertar");
      btnInsertar.setBounds(404, 58, 89, 23);
      btnInsertar.addActionListener(this);
      contentPane.add(btnInsertar);
      
      btnActualizar = new JButton("Actualizar");
      btnActualizar.setBounds(404, 89, 89, 23);
      btnActualizar.addActionListener(this);
      contentPane.add(btnActualizar);
      
      btnBorrar = new JButton("Borrar");
      btnBorrar.setBounds(404, 116, 89, 23);
      btnBorrar.addActionListener(this);
      contentPane.add(btnBorrar);
      
      // añado el actionListener a la tabla
      tabla.getSelectionModel().addListSelectionListener(this);
      
      // selecciono el primer registro
      this.tabla.setRowSelectionInterval(0,0);
			
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
		int fila = tabla.getSelectedRow();
		// actualizo los datos de los campos
		actualizarCampos(fila);
	}

	// controlo el comportamiento de los botones
	@Override
	public void actionPerformed(ActionEvent ae) {
		// compruebo el boton que se ha pulsado
		// inicializo la fila con el valor del registro actual
		int fila = tabla.getSelectedRow();
		// obtengo el numero de registros de la tabla
		int numeroRegistros = this.tabla.getRowCount();
		if (ae.getSource() == btnPrimero){
			// si se ha pulsado btnPrimero
			// actualizo la posicion
			fila=0;
			
		}
		else if (ae.getSource() == btnAnterior){
			// si se ha pulsado btnAnterior
			if(fila > 0){
				// si no estoy ya en el primer registro
				// voy a la fila anterior
				fila--;
			}
		}
		else if (ae.getSource() == btnSiguiente){
			// si se ha pulsado btnSiguiente
			if(fila < (numeroRegistros-1)){
				// si no estoy ya en el ultimo registro
				// voy a la fila siguiente
				fila++;
			}
		}
		else if (ae.getSource() == btnUltimo){
			// si se ha pulsado btnUltimo
			if(fila != (numeroRegistros-1)){
				// si no estoy ya en el ultimo registro
				// voy al ultimo registro
				fila = numeroRegistros-1;
			}
		}
		else if (ae.getSource() == btnNuevo){
			// si se ha pulsado btnNuevo
			// Inicializo los valores de los campos
			this.txtCodAsignatura.setText("Código");
			this.txtNombreAsignatura.setText("Nombre");
			this.txtDescripcion.setText("Descripción");
			// pongo el foco en txtDNI
			this.txtCodAsignatura.requestFocus();
		}
		else if (ae.getSource() == btnInsertar){
			// si se ha pulsado btnInsertar
			
	    
	    // lo añado a la base de datos
	    try {
	    	// me reconecto a la base de datos
		    conexion = DriverManager.getConnection("jdbc:mysql://localhost/bdalumnos", "root", "");
		    String comando = "INSERT INTO asignaturas VALUES ('" + this.txtCodAsignatura.getText() + "','";
		    comando += txtNombreAsignatura.getText() + "','";
		    comando += txtDescripcion.getText() + "')";
		    //crs.setCommand("INSERT INTO alumnos VALUES ('00000000A','Nuevo','Alumno','1AS3')");
				crs.setCommand(comando);
		    crs.execute(conexion);
			  conexion.close();
			  // solo si lo añade bien a la base de datos
			  // lo añado a la tabla
				DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
				Vector<String> row = new Vector<String>();
		    row.add(this.txtCodAsignatura.getText());
		    row.add(this.txtNombreAsignatura.getText());
		    row.add(this.txtDescripcion.getText());
		    modelo.addRow(row);
				// voy a la nueva fila
				fila = numeroRegistros;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				this.lblTexto.setText("Error al Insertar. Clave Primaria Posiblemente Duplicada.");
			}
	    
		}
		else if (ae.getSource() == btnActualizar){
			// si se ha pulsado btnActualizar
			
			// actualizo los datos de la base de datos
	    try {
	    	// me reconecto a la base de datos
		    conexion = DriverManager.getConnection("jdbc:mysql://localhost/bdalumnos", "root", "");
		    String comando = "UPDATE asignaturas SET nombreasignatura='" + txtNombreAsignatura.getText() + "',";
		    comando += "descripcion='"+txtDescripcion.getText() +"' ";
		    comando += "WHERE codasignatura='"+txtCodAsignatura.getText()+"'";

				crs.setCommand(comando);
		    crs.execute(conexion);
			  conexion.close();
			  // solo si lo actualiza bien en la base de datos
			  // actualizo los datos de la tabla
				tabla.setValueAt(this.txtCodAsignatura.getText(), fila, 0);
				tabla.setValueAt(this.txtNombreAsignatura.getText(), fila, 1);
				tabla.setValueAt(this.txtDescripcion.getText(), fila, 2);
	    } catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				this.lblTexto.setText("Error al Actualizar los datos. La Clave Primaria No Puede Modificarse.");
			}
			
		}
		else if (ae.getSource() == btnBorrar){
			// si se ha pulsado btnBorrar
			
			// borro el elemento de la base de datos
			try {
	    	// me reconecto a la base de datos
		    conexion = DriverManager.getConnection("jdbc:mysql://localhost/bdalumnos", "root", "");
		    String comando = "DELETE FROM asignaturas WHERE codasignatura='" + txtCodAsignatura.getText()+"'";
		    //crs.setCommand("DELETE FROM alumnos WHERE dni='00000000A'");
				crs.setCommand(comando);
		    crs.execute(conexion);
			  conexion.close();
			  // solo si lo borra bien en la base de datos
			  // borro el elemento de la tabla
			  
			  // compruebo si el campo dni del campo de texto y de la tabla coinciden
			  
			  // si coinciden lo borro de la tabla
				DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
				int row = tabla.getSelectedRow();
				String codasignatura = (String) tabla.getModel().getValueAt(row,0);
				if (codasignatura.equals(txtCodAsignatura.getText())){
					modelo.removeRow(row);
					// voy al primer registro
					fila = 0;
					this.tabla.getSelectionModel().setSelectionInterval(0,0);			
					this.btnPrimero.doClick();
				}
				else{
					this.lblTexto.setText("Error al Borrar los datos.");
				}

	    } catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				this.lblTexto.setText("Error al Borrar los datos.");
			}
			
		}
		// actualizo los datos
		if (fila>=0){
			// si existe por lo menos una fila
			// selecciono el registro de la tabla
			this.tabla.getSelectionModel().setSelectionInterval(fila,fila);
		}
	}
	// metodo que actualiza el valor de los campos
	// recibe la fila de la tabla en la que se encuentran los datos y 
	// carga los datos en los campos de texto
	private void actualizarCampos(int fila){
		if (fila>=0){
			this.lblTexto.setText("Registro " + (fila+1) + " seleccionado.");
			// actualizo los datos de los campos
			this.txtCodAsignatura.setText((String)tabla.getModel().getValueAt(fila,0));
			this.txtNombreAsignatura.setText((String)tabla.getModel().getValueAt(fila,1));
			this.txtDescripcion.setText((String)tabla.getModel().getValueAt(fila,2));
		}
	}

	// controlo que se seleccione el texto de los campos al coger el foco
	@Override
	public void focusGained(FocusEvent fe) {
		// obtengo el componente
		((JTextComponent) fe.getSource()).setSelectionStart(0);
		((JTextComponent) fe.getSource()).setSelectionEnd(((JTextComponent) fe.getSource()).getText().length());	
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
