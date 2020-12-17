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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class VentanaJPasswordField extends JFrame {

	//defino los componentes
	private static final long	serialVersionUID = 1183812776304203166L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JPasswordField pwfContrasena;
	private JLabel lblTexto;
	private JButton btnAceptar;
	private JLabel lblContrasena;
	private JLabel lblNombre;
	
	// main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJPasswordField frame = new VentanaJPasswordField();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// JFrame
	public VentanaJPasswordField() {
		// defino los datos correctos
		String nombrecorrecto = new String("1dw3");
		String contrasenacorrecta = new String("1dw3");
		
		setTitle("Ventana JPasswordField");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTexto = new JLabel("Noname");
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTexto.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		lblTexto.setBounds(60, 29, 296, 14);
		contentPane.add(lblTexto);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
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
		});
		btnAceptar.setBounds(159, 139, 89, 23);
		contentPane.add(btnAceptar);
		
		// ******* txtNombre *************
		txtNombre = new JTextField();
		txtNombre.setText("Introduzca su nombre ...");
		//ActionListener
		txtNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// al pulsar enter
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
		});
		//FocusListener
		txtNombre.addFocusListener(new FocusListener() {
			 public void focusGained(FocusEvent e) {
				 txtNombre.setSelectionStart(0);
				 txtNombre.setSelectionEnd(txtNombre.getText().length());
			 }

			 public void focusLost(FocusEvent e) {
			  pwfContrasena.select(0, 0);
			 }
			});
		
		txtNombre.setBounds(113, 59, 300, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		// ******* pwfContrasena *************
		pwfContrasena = new JPasswordField();
		//ActionListener
		pwfContrasena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
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
		});
		//FocusListener
		pwfContrasena.addFocusListener(new FocusListener() {
			 public void focusGained(FocusEvent e) {
			  pwfContrasena.setSelectionStart(0);
			 String contrasena = new String(pwfContrasena.getPassword()); 
			 pwfContrasena.setSelectionEnd(contrasena.length());
			 }

			 public void focusLost(FocusEvent e) {
			  pwfContrasena.select(0, 0);
			 }
			});

		pwfContrasena.setBounds(111, 81, 290, 26);
		contentPane.add(pwfContrasena);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(27, 59, 69, 20);
		contentPane.add(lblNombre);
		
		lblContrasena = new JLabel("Contraseña");
		lblContrasena.setBounds(27, 84, 89, 20);
		contentPane.add(lblContrasena);
	}
	
}
