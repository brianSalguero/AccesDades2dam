package es.florida.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;

// TODO: Auto-generated Javadoc
/**
 * The Class VistaPrincipi.
 */
public class VistaPrincipi extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The lbl titol. */
	private JLabel lblTitol;
	
	/** The btn registrarse. */
	private JButton btnRegistrarse;
	
	/** The btn inici sessio. */
	private JButton btnIniciSessio;

	/**
	 * Create the frame.
	 */
	public VistaPrincipi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitol = new JLabel("JOC DE MEMORIA");
		lblTitol.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitol.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblTitol.setBounds(45, 31, 250, 32);
		contentPane.add(lblTitol);
		
		btnRegistrarse = new JButton("REGISTRARSE");
		btnRegistrarse.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnRegistrarse.setBounds(72, 73, 186, 32);
		contentPane.add(btnRegistrarse);
		
		btnIniciSessio = new JButton("INICIAR SESSIÓ");
		btnIniciSessio.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnIniciSessio.setBounds(72, 115, 186, 32);
		contentPane.add(btnIniciSessio);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Sets the btn registrarse.
	 *
	 * @param listener the new btn registrarse
	 */
	public void setBtnRegistrarse(ActionListener listener) {
		btnRegistrarse.addActionListener(listener);
	}
	
	/**
	 * Sets the btn inici sessio.
	 *
	 * @param listener the new btn inici sessio
	 */
	public void setBtnIniciSessio(ActionListener listener) {
		btnIniciSessio.addActionListener(listener);
	}

}
