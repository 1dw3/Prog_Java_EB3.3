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
import javax.swing.JPasswordField;

public class VentanaActionListenerComun extends JFrame {

	private static final long	serialVersionUID	= -2351775708449263953L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JPasswordField pwfContrasena;
	private JLabel lblTexto;
	
	// main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaActionListenerComun frame = new VentanaActionListenerComun();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// JFrame
	public VentanaActionListenerComun() {
		
		setTitle("Ventana JPasswordField");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTexto = new JLabel("Noname");
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTexto.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		lblTexto.setBounds(60, 29, 296, 14);
		contentPane.add(lblTexto);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new comprobarDatosActionListener());
		btnAceptar.setBounds(159, 139, 89, 23);
		contentPane.add(btnAceptar);
		
		txtNombre = new JTextField();
		txtNombre.setText("Introduzca su nombre ...");
		txtNombre.addActionListener(new comprobarDatosActionListener());
		txtNombre.setBounds(113, 59, 300, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		pwfContrasena = new JPasswordField();
		pwfContrasena.addActionListener(new comprobarDatosActionListener());
		pwfContrasena.setBounds(111, 81, 290, 26);
		contentPane.add(pwfContrasena);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(27, 59, 69, 20);
		contentPane.add(lblNombre);
		
		JLabel lblContraseña = new JLabel("Contraseña");
		lblContraseña.setBounds(27, 84, 89, 20);
		contentPane.add(lblContraseña);
	}
	
	// creo una clase interna comun para controlar los ActionListener
	private class comprobarDatosActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// defino los datos correctos
			String nombrecorrecto = new String("1dw3");
			String contrasenacorrecta = new String("1dw3");
			// compruebo los datos
			// el metodo getPassword de JPasswordField devuelve un char[]
			// para poder usar equals tengo que convertir el char [] a String
			String contrasena = new String(pwfContrasena.getPassword());
			if(nombrecorrecto.equals(txtNombre.getText()) && contrasenacorrecta.equals(contrasena)){
				lblTexto.setText("Bienvenido "+txtNombre.getText());
			}
			else{
				lblTexto.setText("Datos Incorrectos.");
			}
		}
	}
	
}
