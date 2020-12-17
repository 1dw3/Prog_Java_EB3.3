package ebaluaketa3;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;

public class VentanaJEditorPaneUrl extends JFrame implements HyperlinkListener{
	
	//defino los componentes
	
	private JPanel contentPane;
	
	// editor
	private JEditorPane editor;
	private JScrollPane scrollPaneEditor;
	
	// main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJEditorPaneUrl frame = new VentanaJEditorPaneUrl();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// JFrame
	public VentanaJEditorPaneUrl() {
		setTitle("Editor de Páginas Web");
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
		
		 try {
	      editor = new JEditorPane("http://www.fptxurdinaga.com");
	      editor.setEditable(false);

	      editor.addHyperlinkListener(this);

	      // añado una barra de desplazamiento al editor
	  		scrollPaneEditor = new JScrollPane(editor);
	  		// Agrega el editor en el centro del contenedor
	  		contentPane.add(scrollPaneEditor, BorderLayout.CENTER);
	    } catch (IOException e) {
	      System.err.println("Error al Cargar: " + e);
	    }
		
	}
	
	// salir
	private void salir(){
		// salgo de la aplicacion
		System.exit(0);
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent hyperlinkEvent) {
		HyperlinkEvent.EventType type = hyperlinkEvent.getEventType();
    final URL url = hyperlinkEvent.getURL();
    if (type == HyperlinkEvent.EventType.ENTERED) {
      // System.out.println("URL: " + url);
      this.setTitle("URL: " + url);
    } else if (type == HyperlinkEvent.EventType.ACTIVATED) {
      // System.out.println("Activated");
      Document doc = editor.getDocument();
      try {
      	editor.setPage(url);
      } catch (IOException ioException) {
        System.out.println("Error al Cargar el Enlace.");
        editor.setDocument(doc);
      }
    }
		
	}
	
}
