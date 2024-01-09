package es.florida.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

// TODO: Auto-generated Javadoc
/**
 * The Class VistaJoc.
 */
public class VistaJoc extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnJugar;
	private JLabel lblTemps;
	private JLabel lblTitTemps;
	private JLabel lblTitol;
	private JButton btnComenzar;
	private JLabel lblTipo;
	private JButton btnGuardar;
	private JButton btnMostrar;
	private JLabel lblMillor;
	private JLabel lblMillor2;
	private JLabel lblText;

	private JButton btnImage1;
	private JButton btnImage2;
	private JButton btnImage3;
	private JButton btnImage4;
	private JButton btnImage5;
	private JButton btnImage6;
	private JButton btnImage7;
	private JButton btnImage8;
	private JButton btnImage9;
	private JButton btnImage10;
	private JButton btnImage11;
	private JButton btnImage12;
	private JButton btnImage13;
	private JButton btnImage14;
	private JButton btnImage15;
	private JButton btnImage16;

	private JLabel lbl2x4;
	private JLabel lbl4x4;

	/**
	 * Create the frame.
	 */
	public VistaJoc() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 792);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblTitol = new JLabel("JOC DE MEMORIA");
		lblTitol.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitol.setFont(new Font("Arial Black", Font.BOLD, 50));
		lblTitol.setBounds(308, 21, 521, 59);
		contentPane.add(lblTitol);

		lblTitTemps = new JLabel("Temps de Joc");
		lblTitTemps.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitTemps.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblTitTemps.setBounds(60, 658, 161, 24);
		contentPane.add(lblTitTemps);

		lblTemps = new JLabel("00:00");
		lblTemps.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemps.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTemps.setBounds(105, 692, 70, 37);
		contentPane.add(lblTemps);

		btnJugar = new JButton("JUGAR");
		btnJugar.setFont(new Font("Arial Black", Font.PLAIN, 25));
		btnJugar.setBounds(308, 90, 174, 41);
		contentPane.add(btnJugar);

		btnComenzar = new JButton("COMENÇAR");
		btnComenzar.setEnabled(false);
		btnComenzar.setFont(new Font("Arial Black", Font.PLAIN, 25));
		btnComenzar.setBounds(622, 90, 197, 41);
		contentPane.add(btnComenzar);

		lblTipo = new JLabel("");
		lblTipo.setFont(new Font("Arial", Font.PLAIN, 25));
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo.setBounds(469, 141, 161, 37);
		contentPane.add(lblTipo);

		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setEnabled(false);
		btnGuardar.setFont(new Font("Arial Black", Font.PLAIN, 25));
		btnGuardar.setBounds(46, 199, 174, 41);
		contentPane.add(btnGuardar);

		btnImage1 = new JButton("");
		btnImage1.setVisible(false);
		btnImage1.setEnabled(false);
		btnImage1.setBounds(287, 188, 128, 128);
		contentPane.add(btnImage1);

		btnImage2 = new JButton("");
		btnImage2.setVisible(false);
		btnImage2.setEnabled(false);
		btnImage2.setBounds(425, 188, 128, 128);
		contentPane.add(btnImage2);

		btnImage3 = new JButton("");
		btnImage3.setVisible(false);
		btnImage3.setEnabled(false);
		btnImage3.setBounds(563, 188, 128, 128);
		contentPane.add(btnImage3);

		btnImage4 = new JButton("");
		btnImage4.setVisible(false);
		btnImage4.setEnabled(false);
		btnImage4.setBounds(701, 188, 128, 128);
		contentPane.add(btnImage4);

		btnImage5 = new JButton("");
		btnImage5.setVisible(false);
		btnImage5.setEnabled(false);
		btnImage5.setBounds(287, 326, 128, 128);
		contentPane.add(btnImage5);

		btnImage6 = new JButton("");
		btnImage6.setVisible(false);
		btnImage6.setEnabled(false);
		btnImage6.setBounds(425, 326, 128, 128);
		contentPane.add(btnImage6);

		btnImage7 = new JButton("");
		btnImage7.setVisible(false);
		btnImage7.setEnabled(false);
		btnImage7.setBounds(563, 326, 128, 128);
		contentPane.add(btnImage7);

		btnImage8 = new JButton("");
		btnImage8.setVisible(false);
		btnImage8.setEnabled(false);
		btnImage8.setBounds(701, 326, 128, 128);
		contentPane.add(btnImage8);

		btnImage9 = new JButton("");
		btnImage9.setVisible(false);
		btnImage9.setEnabled(false);
		btnImage9.setBounds(287, 464, 128, 128);
		contentPane.add(btnImage9);

		btnImage10 = new JButton("");
		btnImage10.setVisible(false);
		btnImage10.setEnabled(false);
		btnImage10.setBounds(425, 464, 128, 128);
		contentPane.add(btnImage10);

		btnImage11 = new JButton("");
		btnImage11.setVisible(false);
		btnImage11.setEnabled(false);
		btnImage11.setBounds(563, 464, 128, 128);
		contentPane.add(btnImage11);

		btnImage12 = new JButton("");
		btnImage12.setVisible(false);
		btnImage12.setEnabled(false);
		btnImage12.setBounds(701, 464, 128, 128);
		contentPane.add(btnImage12);

		btnImage13 = new JButton("");
		btnImage13.setVisible(false);
		btnImage13.setEnabled(false);
		btnImage13.setBounds(287, 601, 128, 128);
		contentPane.add(btnImage13);

		btnImage14 = new JButton("");
		btnImage14.setVisible(false);
		btnImage14.setEnabled(false);
		btnImage14.setBounds(425, 602, 128, 128);
		contentPane.add(btnImage14);

		btnImage15 = new JButton("");
		btnImage15.setVisible(false);
		btnImage15.setEnabled(false);
		btnImage15.setBounds(563, 601, 128, 128);
		contentPane.add(btnImage15);

		btnImage16 = new JButton("");
		btnImage16.setVisible(false);
		btnImage16.setEnabled(false);
		btnImage16.setBounds(701, 601, 128, 128);
		contentPane.add(btnImage16);

		btnMostrar = new JButton("CLASSIFICACIÓ");
		btnMostrar.setFont(new Font("Arial Black", Font.PLAIN, 20));
		btnMostrar.setBounds(31, 371, 221, 41);
		contentPane.add(btnMostrar);

		lblText = new JLabel("Millor temps");
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		lblText.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblText.setBounds(60, 422, 161, 24);
		contentPane.add(lblText);

		lblMillor = new JLabel("00:00");
		lblMillor.setHorizontalAlignment(SwingConstants.CENTER);
		lblMillor.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMillor.setBounds(70, 456, 182, 37);
		contentPane.add(lblMillor);

		lblMillor2 = new JLabel("00:00");
		lblMillor2.setHorizontalAlignment(SwingConstants.CENTER);
		lblMillor2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMillor2.setBounds(70, 503, 182, 37);
		contentPane.add(lblMillor2);

		lbl2x4 = new JLabel("2 x 4");
		lbl2x4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2x4.setFont(new Font("Arial", Font.BOLD, 20));
		lbl2x4.setBounds(20, 456, 70, 37);
		contentPane.add(lbl2x4);

		lbl4x4 = new JLabel("4 x 4");
		lbl4x4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4x4.setFont(new Font("Arial", Font.BOLD, 20));
		lbl4x4.setBounds(20, 503, 70, 37);
		contentPane.add(lbl4x4);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Event Boto Jugar
	 *
	 * @param listener btn jugar
	 */
	public void setBtnJugar(ActionListener listener) {
		btnJugar.addActionListener(listener);
	}

	/**
	 * Modifica LblTipo
	 *
	 * @param tipo String text del lblTipo
	 */
	public void setLblTipo(String tipo) {
		lblTipo.setText(tipo);
	}

	/**
	 * Modifica lblTemps
	 *
	 * @param temps String text del lbl temps
	 */
	public void setLblTemps(String temps) {
		lblTemps.setText(temps);
	}

	/**
	 * Métode per a habilitar o deshabilitar el botó començar
	 *
	 * @param enabled boolean true or false
	 */
	public void setBtnComenzarEnabled(boolean enabled) {
		btnComenzar.setEnabled(enabled);
	}

	/**
	 * Event Boto Començar
	 *
	 * @param listener Listener
	 */
	public void setBtnComenzar(ActionListener listener) {
		btnComenzar.addActionListener(listener);
	}

	/**
	 * Métode per a habilitar o deshabilitar el botó guardar
	 *
	 * @param enabled boolean true or false
	 */
	public void setBtnGuardarEnabled(boolean enabled) {
		btnGuardar.setEnabled(enabled);
	}

	/**
	 * Event Boto Guardar
	 *
	 * @param listener Listener
	 */
	public void setBtnGuardar(ActionListener listener) {
		btnGuardar.addActionListener(listener);
	}

	/**
	 * Event Boto Mostrar
	 *
	 * @param listener Listener
	 */
	public void setBtnMostrar(ActionListener listener) {
		btnMostrar.addActionListener(listener);
	}

	/**
	 * Modifica lblMillor
	 *
	 * @param temps String text del lbl Millor
	 */
	public void setLblMillor(String temps) {
		lblMillor.setText(temps);
	}

	/**
	 * Modifica lblMillor2
	 *
	 * @param temps String text del lbl Millor2
	 */
	public void setLblMillor2(String temps) {
		lblMillor2.setText(temps);
	}

	/**
	 * Modifica el Icon del boto Image 1
	 *
	 * @param imatge nuevo icon image 1
	 */
	public void setIconImage1(ImageIcon imatge) {
		btnImage1.setIcon(imatge);
	}

	/**
	 * Modifica si es visible o no el boto Image 1
	 *
	 * @param visible boolean true or false visible
	 */
	public void setBtnImage1Visible(boolean visible) {
		btnImage1.setVisible(visible);
	}

	/**
	 * Métode per a habilitar o deshabilitar el botó Image1
	 *
	 * @param enabled boolean true or false image 1 enabled
	 */
	public void setBtnImage1Enabled(boolean enabled) {
		btnImage1.setEnabled(enabled);
	}

	/**
	 * Event per a la Imatge 1
	 *
	 * @param listener Listener
	 */
	public void setBtnImage1(ActionListener listener) {
		btnImage1.addActionListener(listener);
	}

	/**
	 * Modifica el Icon del boto Image 2
	 *
	 * @param imatge nuevo icon image 2
	 */
	public void setIconImage2(ImageIcon imatge) {
		btnImage2.setIcon(imatge);
	}

	/**
	 * Modifica si es visible o no el boto Image 2
	 *
	 * @param visible boolean true or false visible
	 */
	public void setBtnImage2Visible(boolean visible) {
		btnImage2.setVisible(visible);
	}

	/**
	 * Métode per a habilitar o deshabilitar el botó Image2
	 *
	 * @param enabled boolean true or false image 2 enabled
	 */
	public void setBtnImage2Enabled(boolean enabled) {
		btnImage2.setEnabled(enabled);
	}

	/**
	 * Event per a la Imatge 2
	 *
	 * @param listener Listener
	 */
	public void setBtnImage2(ActionListener listener) {
		btnImage2.addActionListener(listener);
	}

	/**
	 * Modifica el Icon del boto Image 3
	 *
	 * @param imatge nuevo icon image 3
	 */
	public void setIconImage3(ImageIcon imatge) {
		btnImage3.setIcon(imatge);
	}

	/**
	 * Modifica si es visible o no el boto Image 3
	 *
	 * @param visible boolean true or false visible
	 */
	public void setBtnImage3Visible(boolean visible) {
		btnImage3.setVisible(visible);
	}

	/**
	 * Métode per a habilitar o deshabilitar el botó Image3
	 *
	 * @param enabled boolean true or false image 3 enabled
	 */
	public void setBtnImage3Enabled(boolean enabled) {
		btnImage3.setEnabled(enabled);
	}

	/**
	 * Event per a la Imatge 3
	 *
	 * @param listener Listener
	 */
	public void setBtnImage3(ActionListener listener) {
		btnImage3.addActionListener(listener);
	}

	/**
	 * Modifica el Icon del boto Image 4
	 *
	 * @param imatge nuevo icon image 4
	 */
	public void setIconImage4(ImageIcon imatge) {
		btnImage4.setIcon(imatge);
	}

	/**
	 * Modifica si es visible o no el boto Image 4
	 *
	 * @param visible boolean true or false visible
	 */
	public void setBtnImage4Visible(boolean visible) {
		btnImage4.setVisible(visible);
	}

	/**
	 * Métode per a habilitar o deshabilitar el botó Image4
	 *
	 * @param enabled boolean true or false image 4 enabled
	 */
	public void setBtnImage4Enabled(boolean enabled) {
		btnImage4.setEnabled(enabled);
	}

	/**
	 * Event per a la Imatge 4
	 *
	 * @param listener Listener
	 */
	public void setBtnImage4(ActionListener listener) {
		btnImage4.addActionListener(listener);
	}

	/**
	 * Modifica el Icon del boto Image 5
	 *
	 * @param imatge nuevo icon image 5
	 */
	public void setIconImage5(ImageIcon imatge) {
		btnImage5.setIcon(imatge);
	}

	/**
	 * Modifica si es visible o no el boto Image 5
	 *
	 * @param visible boolean true or false visible
	 */
	public void setBtnImage5Visible(boolean visible) {
		btnImage5.setVisible(visible);
	}

	/**
	 * Métode per a habilitar o deshabilitar el botó Image5
	 *
	 * @param enabled boolean true or false image 5 enabled
	 */
	public void setBtnImage5Enabled(boolean enabled) {
		btnImage5.setEnabled(enabled);
	}

	/**
	 * Event per a la Imatge 5
	 *
	 * @param listener Listener
	 */
	public void setBtnImage5(ActionListener listener) {
		btnImage5.addActionListener(listener);
	}

	/**
	 * Modifica el Icon del boto Image 6
	 *
	 * @param imatge nuevo icon image 6
	 */
	public void setIconImage6(ImageIcon imatge) {
		btnImage6.setIcon(imatge);
	}

	/**
	 * Modifica si es visible o no el boto Image 6
	 *
	 * @param visible boolean true or false visible
	 */
	public void setBtnImage6Visible(boolean visible) {
		btnImage6.setVisible(visible);
	}

	/**
	 * Métode per a habilitar o deshabilitar el botó Image6
	 *
	 * @param enabled boolean true or false image 6 enabled
	 */
	public void setBtnImage6Enabled(boolean enabled) {
		btnImage6.setEnabled(enabled);
	}

	/**
	 * Event per a la Imatge 6
	 *
	 * @param listener Listener
	 */
	public void setBtnImage6(ActionListener listener) {
		btnImage6.addActionListener(listener);
	}

	/**
	 * Modifica el Icon del boto Image 7
	 *
	 * @param imatge nuevo icon image 7
	 */
	public void setIconImage7(ImageIcon imatge) {
		btnImage7.setIcon(imatge);
	}

	/**
	 * Modifica si es visible o no el boto Image 7
	 *
	 * @param visible boolean true or false visible
	 */
	public void setBtnImage7Visible(boolean visible) {
		btnImage7.setVisible(visible);
	}

	/**
	 * Métode per a habilitar o deshabilitar el botó Image7
	 *
	 * @param enabled boolean true or false image 7 enabled
	 */
	public void setBtnImage7Enabled(boolean enabled) {
		btnImage7.setEnabled(enabled);
	}

	/**
	 * Event per a la Imatge 7
	 *
	 * @param listener Listener
	 */
	public void setBtnImage7(ActionListener listener) {
		btnImage7.addActionListener(listener);
	}

	/**
	 * Modifica el Icon del boto Image 8
	 *
	 * @param imatge nuevo icon image 8
	 */
	public void setIconImage8(ImageIcon imatge) {
		btnImage8.setIcon(imatge);
	}

	/**
	 * Modifica si es visible o no el boto Image 8
	 *
	 * @param visible boolean true or false visible
	 */
	public void setBtnImage8Visible(boolean visible) {
		btnImage8.setVisible(visible);
	}

	/**
	 * Métode per a habilitar o deshabilitar el botó Image8
	 *
	 * @param enabled boolean true or false image 8 enabled
	 */
	public void setBtnImage8Enabled(boolean enabled) {
		btnImage8.setEnabled(enabled);
	}

	/**
	 * Event per a la Imatge 8
	 *
	 * @param listener Listener
	 */
	public void setBtnImage8(ActionListener listener) {
		btnImage8.addActionListener(listener);
	}

	/**
	 * Modifica el Icon del boto Image 9
	 *
	 * @param imatge nuevo icon image 9
	 */
	public void setIconImage9(ImageIcon imatge) {
		btnImage9.setIcon(imatge);
	}

	/**
	 * Modifica si es visible o no el boto Image 9
	 *
	 * @param visible boolean true or false visible
	 */
	public void setBtnImage9Visible(boolean visible) {
		btnImage9.setVisible(visible);
	}

	/**
	 * Métode per a habilitar o deshabilitar el botó Image9
	 *
	 * @param enabled boolean true or false image 9 enabled
	 */
	public void setBtnImage9Enabled(boolean enabled) {
		btnImage9.setEnabled(enabled);
	}

	/**
	 * Event per a la Imatge 9
	 *
	 * @param listener Listener
	 */
	public void setBtnImage9(ActionListener listener) {
		btnImage9.addActionListener(listener);
	}

	/**
	 * Modifica el Icon del boto Image 10
	 *
	 * @param imatge nuevo icon image 10
	 */
	public void setIconImage10(ImageIcon imatge) {
		btnImage10.setIcon(imatge);
	}

	/**
	 * Modifica si es visible o no el boto Image 10
	 *
	 * @param visible boolean true or false visible
	 */
	public void setBtnImage10Visible(boolean visible) {
		btnImage10.setVisible(visible);
	}

	/**
	 * Métode per a habilitar o deshabilitar el botó Image10
	 *
	 * @param enabled boolean true or false image 10 enabled
	 */
	public void setBtnImage10Enabled(boolean enabled) {
		btnImage10.setEnabled(enabled);
	}

	/**
	 * Event per a la Imatge 10
	 *
	 * @param listener Listener
	 */
	public void setBtnImage10(ActionListener listener) {
		btnImage10.addActionListener(listener);
	}

	/**
	 * Modifica el Icon del boto Image 11
	 *
	 * @param imatge nuevo icon image 11
	 */
	public void setIconImage11(ImageIcon imatge) {
		btnImage11.setIcon(imatge);
	}

	/**
	 * Modifica si es visible o no el boto Image 11
	 *
	 * @param visible boolean true or false visible
	 */
	public void setBtnImage11Visible(boolean visible) {
		btnImage11.setVisible(visible);
	}

	/**
	 * Métode per a habilitar o deshabilitar el botó Image11
	 *
	 * @param enabled boolean true or false image 11 enabled
	 */
	public void setBtnImage11Enabled(boolean enabled) {
		btnImage11.setEnabled(enabled);
	}

	/**
	 * Event per a la Imatge 11
	 *
	 * @param listener Listener
	 */
	public void setBtnImage11(ActionListener listener) {
		btnImage11.addActionListener(listener);
	}

	/**
	 * Modifica el Icon del boto Image 12
	 *
	 * @param imatge nuevo icon image 12
	 */
	public void setIconImage12(ImageIcon imatge) {
		btnImage12.setIcon(imatge);
	}

	/**
	 * Modifica si es visible o no el boto Image 12
	 *
	 * @param visible boolean true or false visible
	 */
	public void setBtnImage12Visible(boolean visible) {
		btnImage12.setVisible(visible);
	}

	/**
	 * Métode per a habilitar o deshabilitar el botó Image12
	 *
	 * @param enabled boolean true or false image 12 enabled
	 */
	public void setBtnImage12Enabled(boolean enabled) {
		btnImage12.setEnabled(enabled);
	}

	/**
	 * Event per a la Imatge 12
	 *
	 * @param listener Listener
	 */
	public void setBtnImage12(ActionListener listener) {
		btnImage12.addActionListener(listener);
	}

	/**
	 * Modifica el Icon del boto Image 13
	 *
	 * @param imatge nuevo icon image 13
	 */
	public void setIconImage13(ImageIcon imatge) {
		btnImage13.setIcon(imatge);
	}

	/**
	 * Modifica si es visible o no el boto Image 13
	 *
	 * @param visible boolean true or false visible
	 */
	public void setBtnImage13Visible(boolean visible) {
		btnImage13.setVisible(visible);
	}

	/**
	 * Métode per a habilitar o deshabilitar el botó Image13
	 *
	 * @param enabled boolean true or false image 13 enabled
	 */
	public void setBtnImage13Enabled(boolean enabled) {
		btnImage13.setEnabled(enabled);
	}

	/**
	 * Event per a la Imatge 13
	 *
	 * @param listener Listener
	 */
	public void setBtnImage13(ActionListener listener) {
		btnImage13.addActionListener(listener);
	}

	/**
	 * Modifica el Icon del boto Image 14
	 *
	 * @param imatge nuevo icon image 14
	 */
	public void setIconImage14(ImageIcon imatge) {
		btnImage14.setIcon(imatge);
	}

	/**
	 * Modifica si es visible o no el boto Image 14
	 *
	 * @param visible boolean true or false visible
	 */
	public void setBtnImage14Visible(boolean visible) {
		btnImage14.setVisible(visible);
	}

	/**
	 * Métode per a habilitar o deshabilitar el botó Image14
	 *
	 * @param enabled boolean true or false image 14 enabled
	 */
	public void setBtnImage14Enabled(boolean enabled) {
		btnImage14.setEnabled(enabled);
	}

	/**
	 * Event per a la Imatge 14
	 *
	 * @param listener Listener
	 */
	public void setBtnImage14(ActionListener listener) {
		btnImage14.addActionListener(listener);
	}

	/**
	 * Modifica el Icon del boto Image 15
	 *
	 * @param imatge nuevo icon image 15
	 */
	public void setIconImage15(ImageIcon imatge) {
		btnImage15.setIcon(imatge);
	}

	/**
	 * Modifica si es visible o no el boto Image 15
	 *
	 * @param visible boolean true or false visible
	 */
	public void setBtnImage15Visible(boolean visible) {
		btnImage15.setVisible(visible);
	}

	/**
	 * Métode per a habilitar o deshabilitar el botó Image15
	 *
	 * @param enabled boolean true or false image 15 enabled
	 */
	public void setBtnImage15Enabled(boolean enabled) {
		btnImage15.setEnabled(enabled);
	}

	/**
	 * Event per a la Imatge 15
	 *
	 * @param listener Listener
	 */
	public void setBtnImage15(ActionListener listener) {
		btnImage15.addActionListener(listener);
	}

	/**
	 * Modifica el Icon del boto Image 16
	 *
	 * @param imatge nuevo icon image 16
	 */
	public void setIconImage16(ImageIcon imatge) {
		btnImage16.setIcon(imatge);
	}

	/**
	 * Modifica si es visible o no el boto Image 16
	 *
	 * @param visible boolean true or false visible
	 */
	public void setBtnImage16Visible(boolean visible) {
		btnImage16.setVisible(visible);
	}

	/**
	 * Métode per a habilitar o deshabilitar el botó Image16
	 *
	 * @param enabled boolean true or false image 16 enabled
	 */
	public void setBtnImage16Enabled(boolean enabled) {
		btnImage16.setEnabled(enabled);
	}

	/**
	 * Event per a la Imatge 16
	 *
	 * @param listener Listener
	 */
	public void setBtnImage16(ActionListener listener) {
		btnImage16.addActionListener(listener);
	}

	/**
	 * Get boto 1
	 *
	 * @return Torna boto 1
	 */
	public JButton getBtnImage1() {
		return btnImage1;
	}

	/**
	 * Get boto 2
	 *
	 * @return Torna boto 2
	 */
	public JButton getBtnImage2() {
		return btnImage2;
	}

	/**
	 * Get boto 3
	 *
	 * @return Torna boto 3
	 */
	public JButton getBtnImage3() {
		return btnImage3;
	}

	/**
	 * Get boto 4
	 *
	 * @return Torna boto 4
	 */
	public JButton getBtnImage4() {
		return btnImage4;
	}

	/**
	 * Get boto 5
	 *
	 * @return Torna boto 5
	 */
	public JButton getBtnImage5() {
		return btnImage5;
	}

	/**
	 * Get boto 6
	 *
	 * @return Torna boto 6
	 */
	public JButton getBtnImage6() {
		return btnImage6;
	}

	/**
	 * Get boto 7
	 *
	 * @return Torna boto 7
	 */
	public JButton getBtnImage7() {
		return btnImage7;
	}

	/**
	 * Get boto 8
	 *
	 * @return Torna boto 8
	 */
	public JButton getBtnImage8() {
		return btnImage8;
	}

	/**
	 * Get boto 9
	 *
	 * @return Torna boto 9
	 */
	public JButton getBtnImage9() {
		return btnImage9;
	}

	/**
	 * Get boto 10
	 *
	 * @return Torna boto 10
	 */
	public JButton getBtnImage10() {
		return btnImage10;
	}

	/**
	 * Get boto 11
	 *
	 * @return Torna boto 11
	 */
	public JButton getBtnImage11() {
		return btnImage11;
	}

	/**
	 * Get boto 12
	 *
	 * @return Torna boto 12
	 */
	public JButton getBtnImage12() {
		return btnImage12;
	}

	/**
	 * Get boto 13
	 *
	 * @return Torna boto 13
	 */
	public JButton getBtnImage13() {
		return btnImage13;
	}

	/**
	 * Get boto 14
	 *
	 * @return Torna boto 14
	 */
	public JButton getBtnImage14() {
		return btnImage14;
	}

	/**
	 * Get boto 15
	 *
	 * @return Torna boto 15
	 */
	public JButton getBtnImage15() {
		return btnImage15;
	}

	/**
	 * Get boto 16
	 *
	 * @return Torna boto 16
	 */
	public JButton getBtnImage16() {
		return btnImage16;
	}

	/**
	 * Get boto Guardar
	 *
	 * @return Torna boto guardar
	 */
	public JButton getBtnGuardar() {
		return btnGuardar;
	}
}
