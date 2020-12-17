package evaluacion3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class VentanaJTextField extends JFrame {

	private static final long serialVersionUID = -484462815273180630L;
	private JPanel contentPane;
	private JTextField txtNombre;
	
	// main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJTextField frame = new VentanaJTextField();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// JFrame
	public VentanaJTextField() {
		setTitle("Ventana JTextField");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTexto = new JLabel("An\u00F3nimo");
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTexto.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		lblTexto.setBounds(60, 29, 296, 14);
		contentPane.add(lblTexto);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// cambio el texto de lblAceptar
				lblTexto.setText("Bienvenido "+txtNombre.getText());
			}
		});
		btnAceptar.setBounds(159, 139, 89, 23);
		contentPane.add(btnAceptar);
		
		txtNombre = new JTextField();
		txtNombre.setText("Introduzca su nombre ...");
		txtNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// al pulsar enter
				lblTexto.setText("Bienvenido "+txtNombre.getText());
			}
		});
		txtNombre.setBounds(70, 54, 300, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
	}
}
