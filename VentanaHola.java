package eb3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VentanaHola extends JFrame {

	private static final long serialVersionUID = -5959033280333645634L;

	private JPanel contentPane;
	private JLabel lblHolaMundo;
	
	// main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaHola frame = new VentanaHola();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// JFrame
	public VentanaHola() {
		setTitle("Leihoa kaixo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblHolaMundo = new JLabel("kaixo mundua");
		lblHolaMundo.setHorizontalAlignment(SwingConstants.CENTER);
		lblHolaMundo.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		lblHolaMundo.setBounds(111, 122, 176, 14);
		contentPane.add(lblHolaMundo);
	}
}
