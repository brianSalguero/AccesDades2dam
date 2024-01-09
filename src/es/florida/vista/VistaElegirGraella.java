package es.florida.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;

/**
 * CLASSE PER A LA VISTA DE ELEGIR DIFICULTAT
 */
public class VistaElegirGraella extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblElegirGraella;
	private JButton btn2x4;
	private JButton btn4x4;
	private JButton btnCancelar;

	/**
	 * Create the frame.
	 */
	public VistaElegirGraella() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblElegirGraella = new JLabel("ELEGIR GRAELLA");
		lblElegirGraella.setBounds(62, 20, 312, 43);
		lblElegirGraella.setHorizontalAlignment(SwingConstants.CENTER);
		lblElegirGraella.setFont(new Font("Arial Black", Font.BOLD, 30));
		contentPane.add(lblElegirGraella);
		
		btn2x4 = new JButton("2 x 4");
		btn2x4.setFont(new Font("Arial Black", Font.PLAIN, 20));
		btn2x4.setBounds(73, 94, 131, 28);
		contentPane.add(btn2x4);
		
		btn4x4 = new JButton("4 x 4");
		btn4x4.setFont(new Font("Arial Black", Font.PLAIN, 20));
		btn4x4.setBounds(224, 94, 131, 28);
		contentPane.add(btn4x4);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCancelar.setBounds(135, 132, 152, 28);
		contentPane.add(btnCancelar);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Event boto 2x4
	 *
	 * @param listener Listener
	 */
	public void setBtn2x4(ActionListener listener) {
		btn2x4.addActionListener(listener);
	}
	
	/**
	 * Event boto 4x4
	 *
	 * @param listener Listener
	 */
	public void setBtn4x4(ActionListener listener) {
		btn4x4.addActionListener(listener);
	}
	
	/**
	 * Event boto Cancelar
	 *
	 * @param listener Listener
	 */
	public void setBtnCancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
}
