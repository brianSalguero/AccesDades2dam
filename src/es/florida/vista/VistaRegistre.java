package es.florida.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

/**
 * The Class VistaRegistre.
 */
public class VistaRegistre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuari;
	private JLabel lblTitol;
	private JLabel lblUsuari;
	private JLabel lblContrasenya;
	private JLabel lblContrasenya2;
	private JButton btnTornar;
	private JButton btnRegistre;
	private JPasswordField txtContrasenya;
	private JPasswordField txtContrasenya2;
	
	/**
	 * Create the frame.
	 */
	public VistaRegistre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 356, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitol = new JLabel("REGISTRE");
		lblTitol.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitol.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblTitol.setBounds(90, 10, 195, 29);
		contentPane.add(lblTitol);
		
		lblUsuari = new JLabel("Usuari:");
		lblUsuari.setFont(new Font("Arial", Font.BOLD, 15));
		lblUsuari.setBounds(108, 66, 57, 23);
		contentPane.add(lblUsuari);
		
		txtUsuari = new JTextField();
		txtUsuari.setFont(new Font("Arial", Font.PLAIN, 12));
		txtUsuari.setColumns(10);
		txtUsuari.setBounds(175, 67, 137, 22);
		contentPane.add(txtUsuari);
		
		lblContrasenya = new JLabel("Contrasenya:");
		lblContrasenya.setFont(new Font("Arial", Font.BOLD, 15));
		lblContrasenya.setBounds(64, 99, 101, 23);
		contentPane.add(lblContrasenya);
		
		lblContrasenya2 = new JLabel("Repetix Contrasenya");
		lblContrasenya2.setFont(new Font("Arial", Font.BOLD, 15));
		lblContrasenya2.setBounds(10, 132, 155, 23);
		contentPane.add(lblContrasenya2);
		
		btnTornar = new JButton("TORNAR");
		btnTornar.setFont(new Font("Arial", Font.BOLD, 16));
		btnTornar.setBounds(20, 176, 145, 29);
		contentPane.add(btnTornar);
		
		btnRegistre = new JButton("REGISTRARSE");
		btnRegistre.setFont(new Font("Arial", Font.BOLD, 16));
		btnRegistre.setBounds(167, 176, 155, 29);
		contentPane.add(btnRegistre);
		
		txtContrasenya = new JPasswordField();
		txtContrasenya.setFont(new Font("Arial", Font.PLAIN, 12));
		txtContrasenya.setBounds(175, 99, 137, 23);
		contentPane.add(txtContrasenya);
		
		txtContrasenya2 = new JPasswordField();
		txtContrasenya2.setFont(new Font("Arial", Font.PLAIN, 12));
		txtContrasenya2.setBounds(175, 132, 137, 23);
		contentPane.add(txtContrasenya2);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Get txtUsuari
	 *
	 * @return Torna String txt usuari
	 */
	public String getTxtUsuari() {
		return txtUsuari.getText();
	}

	/**
	 * Get txtContrasenya
	 *
	 * @return String contrasenya
	 */
	@SuppressWarnings("deprecation")
	public String getTxtContrasenya() {
		return txtContrasenya.getText();
	}
	
	/**
	 * Get txtContrasenya 2.
	 *
	 * @return String contrasenya 2
	 */
	@SuppressWarnings("deprecation")
	public String getTxtContrasenya2() {
		return txtContrasenya2.getText();
	}
	
	/**
	 * Event botó registre
	 *
	 * @param listener Listener
	 */
	public void setBtnRegistre(ActionListener listener) {
		btnRegistre.addActionListener(listener);
	}
	
	/**
	 * Event botó Tornar
	 *
	 * @param listener Listener
	 */
	public void setBtnTornar(ActionListener listener) {
		btnTornar.addActionListener(listener);
	}
}
