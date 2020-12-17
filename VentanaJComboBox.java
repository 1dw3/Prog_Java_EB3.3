package ebaluaketa3;

import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class VentanaJComboBox extends JFrame implements ActionListener{

	//defino los componentes
	private static final long	serialVersionUID = 1183812776304203166L;
	private JPanel contentPane;
	private JLabel lblTexto;
	private JButton btnSalir;
	// JComboBox de los Grupos
	private JComboBox<String> cmbGrupos;
	
	// main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJComboBox frame = new VentanaJComboBox();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// JFrame
	public VentanaJComboBox() {
		
		setTitle("Ventana JComboBox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		lblTexto = new JLabel("Selecciona una Opción ...");
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTexto.setFont(new Font("Lucida Console", Font.PLAIN, 24));
		contentPane.add(lblTexto, BorderLayout.NORTH);
		
		// boton Salir
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});
		contentPane.add(btnSalir, BorderLayout.SOUTH);
		
		// comboBox de Opciones
		cmbGrupos = new JComboBox<String>();
		cmbGrupos.setToolTipText("Grupos");
		cmbGrupos.addItem("1AS3");
		cmbGrupos.addItem("2AS3");
		cmbGrupos.addItem("1DW3");
		cmbGrupos.addItem("2DW3");
		cmbGrupos.setSelectedIndex(0);
		cmbGrupos.addActionListener(this);
		contentPane.add(cmbGrupos, BorderLayout.WEST);
	}

	// controlo cuando cambia la seleccion de la lista
	@Override
	public void actionPerformed(ActionEvent ae) {
		// cuando cambia el elemento seleccionado
		// cambio el valor de la etiqueta
		//int indiceSelecionado = lse.getFirstIndex();
		String seleccion = (String) this.cmbGrupos.getSelectedItem();
		this.lblTexto.setText(seleccion);
		
	}

}
