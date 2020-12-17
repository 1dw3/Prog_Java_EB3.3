package evaluacion3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.BorderLayout;

import javax.swing.JList;

public class VentanaJList extends JFrame implements ListSelectionListener{

	//defino los componentes
	private static final long	serialVersionUID = 1183812776304203166L;
	private JPanel contentPane;
	private JLabel lblTexto;
	private JButton btnSalir;
	// lista de los Grupos
	private JList<String> lstGrupos;
	
	// main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJList frame = new VentanaJList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// JFrame
	public VentanaJList() {
		
		setTitle("Ventana JList");
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
		
		// lista de Opciones
		String[] opciones = { "1AS3", "2AS3", "1DW3", "2DW3" };
		lstGrupos = new JList<String>(opciones);
		lstGrupos.addListSelectionListener(this);
		contentPane.add(lstGrupos, BorderLayout.WEST);
	}

	// controlo cuando cambia la seleccion de la lista
	@Override
	public void valueChanged(ListSelectionEvent lse) {
		// cuando cambia el elemento seleccionado
		// cambio el valor de la etiqueta
		//int indiceSelecionado = lse.getFirstIndex();
		String seleccion = (String) this.lstGrupos.getSelectedValue();
		this.lblTexto.setText(seleccion);
	}

}
