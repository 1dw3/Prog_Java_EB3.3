package ebaluaketa3;

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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class textfieldLehioa extends JFrame {

	private static final long serialVersionUID = -484462815273180630L;
	private JPanel contentPane;
	private JTextField txtIzena;
	private JLabel lblTestua = new JLabel("");
	private JButton btnAceptar = new JButton("Onartu");
	
	// main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					textfieldLehioa frame = new textfieldLehioa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// JFrame
	public textfieldLehioa() {
		setTitle("Leihoa JTextField");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblTestua.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestua.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		lblTestua.setBounds(60, 29, 296, 14);
		contentPane.add(lblTestua);
		
		JTextField txtIzena = new JTextField();
		txtIzena.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				lblTestua.setText("Ongietorri "+txtIzena.getText());
			}
		});
		
		txtIzena.setText("Sartu izena ...");
		txtIzena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// enter sakatzean
				lblTestua.setText("Ongietorri "+txtIzena.getText());
			}
		});
		
		txtIzena.setBounds(70, 54, 300, 20);
		contentPane.add(txtIzena);
		txtIzena.setColumns(10);
		
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				// testua aldatu
				lblTestua.setText("Ongi etorri "+txtIzena.getText());
			}
		});
		btnAceptar.setBounds(159, 139, 89, 23);
		contentPane.add(btnAceptar);
		
		
	}
}
