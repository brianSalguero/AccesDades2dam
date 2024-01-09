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

// TODO: Auto-generated Javadoc
/**
 * The Class VistaIniciarSessio.
 */
public class VistaIniciarSessio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuari;
	private JPasswordField txtContrasenya;
	private JLabel lblTitol;
	private JLabel lblUsuario;
	private JLabel lblContrasenya;
	private JButton btnIniciSessio;
	private JButton btnTornar;

	/**
	 * Create the frame.
	 */
	public VistaIniciarSessio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitol = new JLabel("INICIAR SESSIÓ");
		lblTitol.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitol.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblTitol.setBounds(35, 30, 289, 29);
		contentPane.add(lblTitol);
		
		lblUsuario = new JLabel("Usuari:");
		lblUsuario.setFont(new Font("Arial", Font.BOLD, 15));
		lblUsuario.setBounds(72, 86, 57, 23);
		contentPane.add(lblUsuario);
		
		txtUsuari = new JTextField();
		txtUsuari.setFont(new Font("Arial", Font.PLAIN, 12));
		txtUsuari.setBounds(139, 87, 137, 22);
		contentPane.add(txtUsuari);
		txtUsuari.setColumns(10);
		
		lblContrasenya = new JLabel("Contrasenya:");
		lblContrasenya.setFont(new Font("Arial", Font.BOLD, 15));
		lblContrasenya.setBounds(35, 119, 101, 23);
		contentPane.add(lblContrasenya);
		
		btnIniciSessio = new JButton("INICIAR SESSIÓ");
		btnIniciSessio.setFont(new Font("Arial", Font.BOLD, 16));
		btnIniciSessio.setBounds(149, 157, 157, 29);
		contentPane.add(btnIniciSessio);
		
		btnTornar = new JButton("TORNAR");
		btnTornar.setFont(new Font("Arial", Font.BOLD, 16));
		btnTornar.setBounds(14, 157, 130, 29);
		contentPane.add(btnTornar);
		
		txtContrasenya = new JPasswordField();
		txtContrasenya.setFont(new Font("Arial", Font.PLAIN, 12));
		txtContrasenya.setBounds(139, 119, 137, 23);
		contentPane.add(txtContrasenya);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Get usuari.
	 *
	 * @return String usuari
	 */
	public String getTxtUsuari() {
		return txtUsuari.getText();
	}

	/**
	 * Get contrasenya.
	 *
	 * @return String contrasenya
	 */
	@SuppressWarnings("deprecation")
	public String getTxtContrasenya() {
		return txtContrasenya.getText();
	}
	
	/**
	 * Modifica txt contrasenya.
	 *
	 * @param contrasenya de txt contrasenya
	 */
	public void setTxtContrasenya(String contra) {
		txtContrasenya.setText(contra);
	}
	
	/**
	 * Event botó inici sessio.
	 *
	 * @param listener Listener
	 */
	public void setBtnIniciarSessio(ActionListener listener) {
		btnIniciSessio.addActionListener(listener);
	}
	
	/**
	 * Event botó tornar
	 *
	 * @param listener Listener
	 */
	public void setBtnTornar(ActionListener listener) {
		btnTornar.addActionListener(listener);
	}
}
