package evaluacion3;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JCheckBox;

import java.awt.FlowLayout;

import javax.swing.JRadioButton;

public class VentanaJCheckBoxJRadioButton extends JFrame implements ChangeListener,ActionListener{

	//defino los componentes
	private static final long	serialVersionUID = 1183812776304203166L;
	private JPanel contentPane;
	private JLabel lblTexto;
	private JButton btnSalir;
	
	private GridBagLayout gbl_contentPane;
	private GridBagConstraints gbc_lblTexto;
	private JPanel panel_1;
	private GridBagConstraints gbc_panel_1;
	//JCheckBox
	private JCheckBox chkCursiva;
	private JCheckBox chkNegrita;
	
	private JPanel panel;
	private GridBagConstraints gbc_panel;
	//Group the radio buttons.
	private ButtonGroup btgColores;
	//JRadioButton
	private JRadioButton rbtNegro;
	private JRadioButton rbtRojo;
	private JRadioButton rbtAzul;
	
	// salir
	GridBagConstraints gbc_btnSalir;
	
	// main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJCheckBoxJRadioButton frame = new VentanaJCheckBoxJRadioButton();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// JFrame
	public VentanaJCheckBoxJRadioButton() {
		
		setTitle("Ventana JCheckBox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{60, 296, 0};
		gbl_contentPane.rowHeights = new int[]{14, 96, 23, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblTexto = new JLabel("Texto de Prueba");
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTexto.setFont(new Font("Lucida Console", Font.PLAIN, 24));
		gbc_lblTexto = new GridBagConstraints();
		gbc_lblTexto.anchor = GridBagConstraints.NORTH;
		gbc_lblTexto.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTexto.insets = new Insets(0, 0, 5, 0);
		gbc_lblTexto.gridx = 1;
		gbc_lblTexto.gridy = 0;
		contentPane.add(lblTexto, gbc_lblTexto);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});
		
		panel_1 = new JPanel();
		gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.WEST;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.VERTICAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// JCheckBox
		chkCursiva = new JCheckBox("Cursiva");
		chkCursiva.setHorizontalAlignment(SwingConstants.LEFT);
		chkCursiva.addChangeListener(this);
		panel_1.add(chkCursiva);
		
		chkNegrita = new JCheckBox("Negrita");
		chkNegrita.setHorizontalAlignment(SwingConstants.LEFT);
		chkNegrita.addChangeListener(this);
		panel_1.add(chkNegrita);
		
		// panel JRadioButton
		panel = new JPanel();
		gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		
		//JRadioButton
		rbtNegro = new JRadioButton("Negro");
		rbtNegro.setFont(new Font("Tahoma", Font.BOLD, 11));
		rbtNegro.setSelected(true);
		rbtNegro.addActionListener(this);
		panel.add(rbtNegro);
		
		rbtRojo = new JRadioButton("Rojo");
		rbtRojo.setFont(new Font("Tahoma", Font.BOLD, 11));
		rbtRojo.setForeground(Color.RED);
		rbtRojo.addActionListener(this);
		panel.add(rbtRojo);
		
		rbtAzul = new JRadioButton("Azul");
		rbtAzul.setFont(new Font("Tahoma", Font.BOLD, 11));
		rbtAzul.setForeground(new Color(0, 0, 255));
		rbtAzul.addActionListener(this);
		panel.add(rbtAzul);
		
		//agrupo los radio buttons.
		btgColores = new ButtonGroup();
		btgColores.add(rbtNegro);
		btgColores.add(rbtRojo);
		btgColores.add(rbtAzul);
		
		// boton Salir
		gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.anchor = GridBagConstraints.NORTH;
		gbc_btnSalir.gridx = 1;
		gbc_btnSalir.gridy = 2;
		contentPane.add(btnSalir, gbc_btnSalir);
	}

	// controlo el cambio de los JCheckBox
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// cojo la fuente actual
		Font nuevaFuente = this.lblTexto.getFont();
		int formato = 0;
		// actualizo la fuente
		if (this.chkCursiva.isSelected()){
			// si hay que ponerla en cursiva
			formato = formato + Font.ITALIC;
		}
		if (this.chkNegrita.isSelected()){
			// si hay que ponerla en negrita
			formato = formato + Font.BOLD;
		}
		// actualizo el formato de la fuente
		this.lblTexto.setFont(new Font(nuevaFuente.getFamily(), formato, nuevaFuente.getSize()));

	}

	//controlo el cambio de los JRadioButton
	@Override
	public void actionPerformed(ActionEvent ae) {
		// compruebo que JRadioButton se ha pulsado
		Object source = ae.getSource();
		if (source == this.rbtNegro){
			// si se ha pulsado negro
			this.lblTexto.setForeground(Color.BLACK);
		}
		else if (source == this.rbtRojo){
			// si se ha pulsado rojo
			this.lblTexto.setForeground(Color.RED);
		}
		else if (source == this.rbtAzul){
			// si se ha pulsado azul
			this.lblTexto.setForeground(Color.BLUE);
		}
		
	}
}
