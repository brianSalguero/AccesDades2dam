package es.florida.Ae02;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;

/**
 * CLASE VISTA PRINCIPAL
 */
public class VistaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuari;
	private JTextField txtPassword;
	private JButton btnLogin;
	private JLabel lblTitLogin;
	private JLabel lblUsuari;
	private JLabel lblContrasenya;
	private Connection con;

	/**
	 * @param con Conexio que es passa al cridar la vista
	 */
	public VistaLogin(Connection con) {
		this.con = con;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 342, 267);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblTitLogin = new JLabel("LOGIN");
		lblTitLogin.setForeground(new Color(0, 0, 128));
		lblTitLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitLogin.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblTitLogin.setBounds(62, 30, 193, 43);
		contentPane.add(lblTitLogin);

		txtUsuari = new JTextField();
		txtUsuari.setFont(new Font("Arial", Font.PLAIN, 12));
		txtUsuari.setBounds(172, 94, 118, 24);
		contentPane.add(txtUsuari);
		txtUsuari.setColumns(10);

		lblUsuari = new JLabel("Usuari:");
		lblUsuari.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuari.setFont(new Font("Arial", Font.BOLD, 20));
		lblUsuari.setBounds(79, 91, 83, 24);
		contentPane.add(lblUsuari);

		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		txtPassword.setColumns(10);
		txtPassword.setBounds(172, 131, 118, 24);
		contentPane.add(txtPassword);

		lblContrasenya = new JLabel("Contrasenya:");
		lblContrasenya.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasenya.setFont(new Font("Arial", Font.BOLD, 20));
		lblContrasenya.setBounds(21, 128, 141, 24);
		contentPane.add(lblContrasenya);

		btnLogin = new JButton("ENTRAR");
		btnLogin.setFont(new Font("Arial", Font.BOLD, 15));
		btnLogin.setBounds(106, 177, 103, 24);
		contentPane.add(btnLogin);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * BOTÓ LOGIN QUE FARÀ EL EVENT QUE ES CONTROLARÀ EN CONTROLADOR (LOGIN AMB
	 * USUARI/CONTRASENYA)
	 * 
	 * @param listener
	 */
	public void setBtnLogin(ActionListener listener) {
		btnLogin.addActionListener(listener);
	}

	/**
	 * @return String amb l'user passada pero l'usuari
	 */
	public String getTxtUsuari() {
		return txtUsuari.getText();
	}

	/**
	 * @return String amb la contrasenya passada per l'usuari
	 */
	public String getTxtPassword() {
		return txtPassword.getText();
	}

	/**
	 * @return Conexio
	 */
	public Connection getCon() {
		return con;
	}
}
