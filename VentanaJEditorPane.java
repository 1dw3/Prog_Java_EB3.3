package ebaluaketa3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

import say.swing.JFontChooser;

public class VentanaJEditorPane extends JFrame implements ActionListener, KeyListener{
	
	//defino los componentes
	private static final long serialVersionUID = -5876470080134839940L;
	private JPanel contentPane;
	
	// menuBar
	private JMenuBar menuBar;
	// Archivo
	private JMenu mnuArchivo;
	private JMenuItem mniNuevo;
	private JMenuItem mniAbrir;
	private JMenuItem mniGuardar;
	private JMenuItem mniGuardarComo;
	private JMenuItem mniSalir;
	// Editar
	private JMenu mnuEditar;
	private JMenuItem mniCortar;
	private JMenuItem mniCopiar;
	private JMenuItem mniPegar;
	// Formato
	private JMenu mnuFormato;
	private JMenuItem mniFuente;
	private JMenuItem mniColorTexto;
	// Ayuda
	private JMenu mnuAyuda;
	private JMenuItem mniAcercaDe;
	
	// toolBar
	private JToolBar toolBar;
	private JButton btnNuevo;
	private JButton btnAbrir;
	private JButton btnGuardar;
	private JButton btnGuardarComo;
	private JButton btnCortar;
	private JButton btnCopiar;
	private JButton btnPegar;
	private JButton btnFuente;
	private JButton btnColorTexto;
	private JButton btnAcercaDe;
	
	// barraEstado
	private JPanel barraEstadoPanel;
	private JLabel estado1;
	private JLabel estado2;
	
	// editor
	private JEditorPane editor;
	private JScrollPane scrollPaneEditor;
	
	// modificado
	private boolean modificado = false;
	
	// nombreDocumento
	private File nombreDocumento;
	
	// JFileChooser
	private JFileChooser fileChooser;
	
	// main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJEditorPane frame = new VentanaJEditorPane();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// JFrame
	public VentanaJEditorPane() {
		setTitle("Mi Editor con Men\u00FAs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 993, 457);
		
	// controlo el cierre de la ventana pulsando X para Salir de la aplicación
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				salir();
			}
		});
		
		// Jpanel
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// menuBar
		menuBar = new JMenuBar();
		// Archivo
		mnuArchivo = new JMenu("Archivo");
		mniNuevo = new JMenuItem("Nuevo");
		mniNuevo.addActionListener(this);
		mniAbrir = new JMenuItem("Abrir");
		mniAbrir.addActionListener(this);
		mniGuardar = new JMenuItem("Guardar");
		mniGuardar.addActionListener(this);
		mniGuardarComo = new JMenuItem("Guardar como");
		mniGuardarComo.addActionListener(this);
		mniSalir = new JMenuItem("Salir");
		mniSalir.addActionListener(this);
		//Agrega los items al menu
		mnuArchivo.add(mniNuevo);
		mnuArchivo.add(mniAbrir);
		mnuArchivo.add(mniGuardar);
		mnuArchivo.add(mniGuardarComo);
		mnuArchivo.addSeparator();
		mnuArchivo.add(mniSalir);
		//Agrega el menu a la barra de menu
		menuBar.add(mnuArchivo);
		
		// Editar
		mnuEditar = new JMenu("Editar");
		mniCortar = new JMenuItem("Cortar");
		mniCortar.addActionListener(this);
		mniCopiar = new JMenuItem("Copiar");
		mniCopiar.addActionListener(this);
		mniPegar = new JMenuItem("Pegar");
		mniPegar.addActionListener(this);
		//Agrega los items al menu
		mnuEditar.add(mniCortar);
		mnuEditar.add(mniCopiar);
		mnuEditar.add(mniPegar);
		//Agrega el menu a la barra de menu
		menuBar.add(mnuEditar);
		
		// Formato
		mnuFormato = new JMenu("Formato");
		mniFuente = new JMenuItem("Fuente");
		mniFuente.addActionListener(this);
		mniColorTexto = new JMenuItem("Color Texto");
		mniColorTexto.addActionListener(this);
		//Agrega los items al menu
		mnuFormato.add(mniFuente);
		mnuFormato.add(mniColorTexto);
		//Agrega el menu a la barra de menu
		menuBar.add(mnuFormato);
		
		// Ayuda
		mnuAyuda = new JMenu("Ayuda");
		mniAcercaDe = new JMenuItem("Acerca De ...");
		mniAcercaDe.addActionListener(this);
		//Agrega los items al menu
		mnuAyuda.add(mniAcercaDe);
		//Agrega el menu a la barra de menu
		menuBar.add(mnuAyuda);
		
		// asigna la JMenuBar a nuestra ventana
		this.setJMenuBar(menuBar);
		
		
		// JToolBar
		toolBar = new JToolBar();
		// Nuevo
		btnNuevo = new JButton();
		btnNuevo.setText("Nuevo");
		btnNuevo.setIcon(new ImageIcon(VentanaJEditorPane.class.getResource("ikonoak/nuevo.gif")));
		btnNuevo.setMargin(new Insets(0, 0, 0, 0));
		btnNuevo.addActionListener(this);
		toolBar.add(btnNuevo);
		// Abrir
		btnAbrir = new JButton();
		btnAbrir.setText("Abrir");
		btnAbrir.setIcon(new ImageIcon(VentanaJEditorPane.class.getResource("ikonoak/abrir.gif")));
		btnAbrir.setMargin(new Insets(0, 0, 0, 0));
		btnAbrir.addActionListener(this);
		toolBar.add(btnAbrir);
		
		// Guardar
		btnGuardar = new JButton();
		btnGuardar.setText("Guardar");
		btnGuardar.setIcon(new ImageIcon(VentanaJEditorPane.class.getResource("ikonoak/guardar.gif")));
		btnGuardar.setMargin(new Insets(0, 0, 0, 0));
		btnGuardar.addActionListener(this);
		toolBar.add(btnGuardar);
		
		// GuardarComo
		btnGuardarComo = new JButton();
		btnGuardarComo.setText("Guardar Como");
		btnGuardarComo.setIcon(new ImageIcon(VentanaJEditorPane.class.getResource("ikonoak/guardarcomo.gif")));
		btnGuardarComo.setMargin(new Insets(0, 0, 0, 0));
		btnGuardarComo.addActionListener(this);
		toolBar.add(btnGuardarComo);
		
		//agrega un separador en la toolbar
		toolBar.addSeparator();
		
		// copiar
		btnCopiar = new JButton();
		btnCopiar.setText("Copiar");
		btnCopiar.setIcon(new ImageIcon(VentanaJEditorPane.class.getResource("ikonoak/copiar.gif")));
		btnCopiar.setMargin(new Insets(0, 0, 0, 0));
		btnCopiar.addActionListener(this);
		toolBar.add(btnCopiar);
		
		// cortar
		btnCortar = new JButton();
		btnCortar.setText("Cortar");
		btnCortar.setIcon(new ImageIcon(VentanaJEditorPane.class.getResource("ikonoak/cortar.gif")));
		btnCortar.setMargin(new Insets(0, 0, 0, 0));
		btnCortar.addActionListener(this);
		toolBar.add(btnCortar);
		
		// pegar
		btnPegar = new JButton();
		btnPegar.setText("Pegar");
		btnPegar.setIcon(new ImageIcon(VentanaJEditorPane.class.getResource("ikonoak/pegar.gif")));
		btnPegar.setMargin(new Insets(0, 0, 0, 0));
		btnPegar.addActionListener(this);
		toolBar.add(btnPegar);
		
		//agrega un separador en la toolbar
		toolBar.addSeparator();
		
		// fuente
		btnFuente = new JButton();
		btnFuente.setText("Fuente");
		btnFuente.setIcon(new ImageIcon(VentanaJEditorPane.class.getResource("ikonoak/fuente.gif")));
		btnFuente.setMargin(new Insets(0, 0, 0, 0));
		btnFuente.addActionListener(this);
		toolBar.add(btnFuente);
		
		// fuente
		btnColorTexto = new JButton();
		btnColorTexto.setText("Color Texto");
		btnColorTexto.setIcon(new ImageIcon(VentanaJEditorPane.class.getResource("ikonoak/colortexto.gif")));
		btnColorTexto.setMargin(new Insets(0, 0, 0, 0));
		btnColorTexto.addActionListener(this);
		toolBar.add(btnColorTexto);
		
		//agrega un separador en la toolbar
		toolBar.addSeparator();
		
		// acerca de
		btnAcercaDe = new JButton();
		btnAcercaDe.setText("Acerca De ...");
		btnAcercaDe.setIcon(new ImageIcon(VentanaJEditorPane.class.getResource("ikonoak/acercade.gif")));
		btnAcercaDe.setMargin(new Insets(0, 0, 0, 0));
		btnAcercaDe.addActionListener(this);
		toolBar.add(btnAcercaDe);
		
		// Agrega el toolbar en el norte del contenedor
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		// editor
		editor = new JEditorPane();
		editor.setText("");
		
		// le digo que escuche los cambios en el editor
		editor.addKeyListener(this);
		
		// Barra de Estado - Status Bar
		barraEstadoPanel = new JPanel();
		barraEstadoPanel.setLayout(new BorderLayout());
		//creo los mensajes para la barra de estado
		estado1 = new JLabel("Nuevo");
		estado2 = new JLabel();
		barraEstadoPanel.add(estado1, BorderLayout.WEST);
		barraEstadoPanel.add(estado2, BorderLayout.CENTER);
		
		//Agrega la Barra de Estado al sur del contenedor
		contentPane.add(barraEstadoPanel, BorderLayout.SOUTH);
		
		// añado una barra de desplazamiento al editor
		scrollPaneEditor = new JScrollPane(editor);
		// Agrega el editor en el centro del contenedor
		contentPane.add(scrollPaneEditor, BorderLayout.CENTER);
		
	}

	// actionPerformed
	@Override
	public void actionPerformed(ActionEvent e) {
		// controlo que componente ha producido la accion

		if(e.getSource() == btnNuevo || e.getSource() == mniNuevo){
			//this.editor.setText("Nuevo");
			this.nuevoDocumento();
		}
		else if(e.getSource() == btnAbrir || e.getSource() == mniAbrir){
			//this.editor.setText("Abrir");
			this.abrirDocumento();
		}
		else if(e.getSource() == btnGuardar || e.getSource() == mniGuardar){
			//this.editor.setText("Guardar");
			this.guardarDocumento();
		}
		else if(e.getSource() == btnGuardarComo || e.getSource() == mniGuardarComo){
			//this.editor.setText("Guardar Como");
			this.guardarDocumentoComo();
		}
		else if(e.getSource() == mniSalir){
			// salgo de la aplicación
			this.salir();
		}
		else if(e.getSource() == btnCortar || e.getSource() == mniCortar){
			//this.editor.setText("Cortar");
			// corto el texto seleccionado al portapapeles
			this.editor.cut();
		}
		else if(e.getSource() == btnCopiar || e.getSource() == mniCopiar){
			//this.editor.setText("Copiar");
			// copio el texto seleccionado al portapapeles
			this.editor.copy();
		}
		else if(e.getSource() == btnPegar || e.getSource() == mniPegar){
			//this.editor.setText("Pegar");
			// pego el texto desde el portapapeles
			this.editor.paste();
			// cuando se pega el texto desde el portapapeles en el editor
			if (modificado == false){
				// solo la primera vez
				modificado = true;
				((JLabel)this.barraEstadoPanel.getComponent(0)).setText("Modificado");
			}
		}
		else if(e.getSource() == btnFuente || e.getSource() == mniFuente){
			// this.editor.setText("Fuente");
			// cambio la fuente del editor
			Font nuevaFuente;
			// muestro el JFontChooser
			JFontChooser fontChooser = new JFontChooser();		
			int opcion = fontChooser.showDialog(contentPane);
			
			if (opcion == JFontChooser.OK_OPTION){
				// si se ha pulsado OK
				// cambio la fuente de la etiqueta
				nuevaFuente = fontChooser.getSelectedFont();
				editor.setFont(nuevaFuente);
				// cambio el estado
				((JLabel)this.barraEstadoPanel.getComponent(0)).setText("Fuente Cambiada");
			}
		}
		else if(e.getSource() == btnColorTexto || e.getSource() == mniColorTexto){
			// this.editor.setText("Color Texto");
			// cambio el color del texto
			// muestro el JColorChooser
			Color nuevoColor = JColorChooser.showDialog(contentPane, "Elija un Color ...", contentPane.getBackground());			
			//Font nuevaFuente;
			//nuevaFuente = this.editor.getFont();
			this.editor.setForeground(nuevoColor);

			// cambio el estado
			((JLabel)this.barraEstadoPanel.getComponent(0)).setText("Color de Texto Cambiado");
		}
		else if(e.getSource() == btnAcercaDe || e.getSource() == mniAcercaDe){
			//this.editor.setText("Acerca De ...");
			// muestro una ventana con la Información de la Aplicación
			JOptionPane.showMessageDialog(editor, "Editor Versión 1.0");
		}
	}
	
	// metodos privador de la clase
	// nuevo
	private void nuevoDocumento(){
		if (modificado){
			// si los datos han sido modificados
			guardarDocumento();
		}
		// inicializo los datos del editor

		this.setTitle("nuevo.txt");
		nombreDocumento=null;
		editor.setText("");
		((JLabel)this.barraEstadoPanel.getComponent(0)).setText("Nuevo");
	}
	
	// guardarDocumento
	private void guardarDocumento(){
		if (modificado){
			// si los datos han sido modificados
			if (nombreDocumento == null){
				// si el documento no tiene nombre
				guardarDocumentoComo();
			}
	
			// grabo los datos del editor
			try	{
        
				PrintWriter writer = new PrintWriter(nombreDocumento);
				writer.print(editor.getText());
				writer.close();

				// actualizo los datos
				this.setTitle(this.nombreDocumento.getName());
				((JLabel)this.barraEstadoPanel.getComponent(0)).setText("Guardado");
				modificado = false;
			}
			catch (Exception ioe)	{
				// muestro un mensaje de error
				((JLabel)this.barraEstadoPanel.getComponent(0)).setText("Error al Guardar el Documento.");
			}
		}
	}

	// guardarDocumentoComo
	private void guardarDocumentoComo(){
		// si el documento no tiene nombre
		if (fileChooser == null){
			//Si no existe todavia el file chooser
			fileChooser = new JFileChooser();
			// le pongo un filtro para que muestre solo los .txt
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Ficheros de Texto", "txt");
			fileChooser.setFileFilter(filter);
		}
		int opcion = fileChooser.showSaveDialog(contentPane);
		
		if (opcion == JFileChooser.APPROVE_OPTION){
			// si ha pulsado Aceptar
			nombreDocumento = fileChooser.getSelectedFile();
			
			// guardo los datos
			modificado = true;
			guardarDocumento();
		}

	}
	
	// abrirDocumentoComo
	private void abrirDocumento(){
		
		if (fileChooser == null){
			//Si no existe todavia el file chooser
			fileChooser = new JFileChooser();
			// le pongo un filtro para que muestre solo los .txt
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Ficheros de Texto", "txt");
			fileChooser.setFileFilter(filter);
		}
		// muestro el fileChooser para Abrir	
		int opcion = fileChooser.showOpenDialog(contentPane);
		
		if (opcion == JFileChooser.APPROVE_OPTION){
			// si ha pulsado Aceptar
			nombreDocumento = fileChooser.getSelectedFile();
			
			// cargo los datos en el editor
			try	{
				String path = fileChooser.getSelectedFile().getAbsolutePath();
        String contenido = leerDocumento(path);

				// actualizo los datos
        editor.setText(contenido);
				this.setTitle(this.nombreDocumento.getName());
				((JLabel)this.barraEstadoPanel.getComponent(0)).setText("Abierto");
				modificado = false;
			}
			catch (Exception ioe)	{
				// muestro un mensaje de error
				((JLabel)this.barraEstadoPanel.getComponent(0)).setText("Error al Abrir el Documento.");
			}
			

		}

	}
	// salir
	private void salir(){
		if (modificado){
			// si los datos han cambiado
			preguntarSiGuardar();
		}
		// salgo de la aplicacion
		System.exit(0);
	}
	// preguntarSiGuardar
	private void preguntarSiGuardar() 
	{
		// Componente que muestra ventanas de opciones
		// Opciones
		Object[] opciones={"Sí","No"};
		//Dialogo modal SI_NO
		int opcion;
		opcion=JOptionPane.showOptionDialog(this,"Los datos han cambiado.¿Desea guardarlos?","Preguntar Si Guardar",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);
		
		if(opcion == JOptionPane.YES_OPTION){
			//Si la opcion escogida es Si
			this.guardarDocumento();
		}
	}
	
	

	// eventos de pulsacion de teclas en el editor
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// cuando se escribe una tecla en el editor
		if (modificado == false){
			// solo la primera vez
			modificado = true;
			((JLabel)this.barraEstadoPanel.getComponent(0)).setText("Modificado");
		}
		
	}
	
	// leerDocumento
	public String leerDocumento( String ruta ){
    FileReader fr = null;
    BufferedReader br = null;
    //Cadena de texto donde se guardara el contenido del archivo
    String contenido = "";
    try
    {
        //ruta puede ser de tipo String o tipo File
        fr = new FileReader( ruta );
        br = new BufferedReader( fr );

        String linea;
        //Obtenemos el contenido del archivo linea por linea
        while( ( linea = br.readLine() ) != null ){ 
            contenido += linea + "\n";
        }

    }catch( Exception e ){  }
    //finally se utiliza para que si todo ocurre correctamente o si ocurre 
    //algun error se cierre el archivo que anteriormente abrimos
    finally
    {
        try{
            br.close();
        }catch( Exception ex ){}
    }
    return contenido;
	}

}
