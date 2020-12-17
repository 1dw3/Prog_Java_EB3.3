package evaluacion3;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaJFileChooser extends JFrame {


	private static final long serialVersionUID = -1971749973978182889L;

	private JPanel contentPane;
	private JLabel lblTexto;
	private JButton btnAceptar;
	
	// main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJFileChooser frame = new VentanaJFileChooser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// JFrame
	public VentanaJFileChooser() {
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
				// muestro el fileChooser para Abrir
				JFileChooser fileChooser = new JFileChooser();
				int opcion = fileChooser.showOpenDialog(contentPane);
				
				if (opcion == JFileChooser.APPROVE_OPTION){
					// si ha pulsado Aceptar
					lblTexto.setText("Ha elegido el archivo "+fileChooser.getSelectedFile());
				}
				else if (opcion == JFileChooser.CANCEL_OPTION){
					// si ha pulsado Cancelar
					lblTexto.setText("Ha pulsado Cancelar");
				}
				else if (opcion == JFileChooser.ERROR_OPTION){
					// si ha producido un Error
					lblTexto.setText("Se ha producido un Error.");
				}
			}
		});
		btnAceptar.setBounds(159, 66, 89, 23);
		contentPane.add(btnAceptar);
	}
}
