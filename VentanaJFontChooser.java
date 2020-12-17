package ebaluaketa3;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import say.swing.JFontChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaJFontChooser extends JFrame {


	private static final long serialVersionUID = -1971749973978182889L;

	private JPanel contentPane;
	private JLabel lblTexto;
	private JButton btnAceptar;
	
	// main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// creo el Frame
					VentanaJFontChooser frame = new VentanaJFontChooser();
					// lo muestro
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// JFrame
	public VentanaJFontChooser() {
		setTitle("Evento Clic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTexto = new JLabel("No se ha pulsado Aceptar");
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTexto.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		lblTexto.setBounds(60, 29, 296, 14);
		contentPane.add(lblTexto);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int opcion;
				Font nuevaFuente;
				// muestro el JFontChooser
				JFontChooser fontChooser = new JFontChooser();		
				opcion = fontChooser.showDialog(contentPane);
				
				if (opcion == JFontChooser.OK_OPTION){
					// si se ha pulsado OK
					// cambio la fuente de la etiqueta
					nuevaFuente = fontChooser.getSelectedFont();
					lblTexto.setFont(nuevaFuente);
					// cambio el texto de la etiqueta
					lblTexto.setText("La fuente ha sido cambiada.");
				}
			
			}
		});
		btnAceptar.setBounds(159, 66, 89, 23);
		contentPane.add(btnAceptar);
	}
}
