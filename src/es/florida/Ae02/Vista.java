package es.florida.Ae02;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JScrollPane;

/**
 * CLASE VISTA PRINCAL
 */
public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblMostrarDades;
	private JLabel lblConexio;
	private JTextArea txtArea;
	private JButton btnExecutar;
	private JButton btnTancarSessio;
	private JButton btnTancarConexio;
	private JScrollPane scrollPane;
	private DefaultTableModel modeloTabla;
	private DefaultTableCellRenderer centrarCeldas;
	private Credencials credencial;
	private Connection conexio;
	private JLabel lblTitol;

	private String[] lineas;

	/**
	 * PASSEM A LA VISTA LA CREDENCIAL DE L'USUARI Y LA CONNEXIOÓ
	 * 
	 * @param credencial
	 * @param con
	 */
	public Vista(Credencials credencial, Connection con) {
		this.credencial = credencial;
		this.conexio = con;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 524);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblConexio = new JLabel("CONEXIO:");
		lblConexio.setHorizontalAlignment(SwingConstants.CENTER);
		lblConexio.setFont(new Font("Arial", Font.BOLD, 15));
		lblConexio.setBounds(10, 10, 172, 23);
		lblConexio.setText("Conexió: " + credencial.usuari);
		contentPane.add(lblConexio);

		txtArea = new JTextArea();
		txtArea.setFont(new Font("Arial", Font.PLAIN, 15));
		txtArea.setBackground(new Color(225, 225, 225));
		txtArea.setBounds(20, 49, 536, 113);
		contentPane.add(txtArea);

		btnExecutar = new JButton("EXECUTAR");
		btnExecutar.setFont(new Font("Arial", Font.BOLD, 15));
		btnExecutar.setBounds(20, 172, 130, 27);
		contentPane.add(btnExecutar);

		btnTancarSessio = new JButton("TANCAR SESSIÓ");
		btnTancarSessio.setToolTipText("");
		btnTancarSessio.setFont(new Font("Arial", Font.BOLD, 20));
		btnTancarSessio.setBounds(566, 120, 206, 102);
		contentPane.add(btnTancarSessio);

		btnTancarConexio = new JButton("TANCAR CONEXIÓ");
		btnTancarConexio.setFont(new Font("Arial", Font.BOLD, 18));
		btnTancarConexio.setToolTipText("");
		btnTancarConexio.setBounds(566, 286, 206, 102);
		contentPane.add(btnTancarConexio);

		modeloTabla = new DefaultTableModel() {
			/**
			 * Default serial version modeloTabla
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblMostrarDades = new JTable(modeloTabla);
		tblMostrarDades.setBorder(new LineBorder(new Color(0, 0, 0), 3));

		scrollPane = new JScrollPane(tblMostrarDades);
		scrollPane.setBounds(20, 209, 536, 246);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(tblMostrarDades);

		lblTitol = new JLabel("EXECUCIÓ DE CONSULTES");
		lblTitol.setForeground(new Color(0, 0, 128));
		lblTitol.setFont(new Font("Arial", Font.BOLD, 25));
		lblTitol.setBounds(421, 6, 351, 33);
		contentPane.add(lblTitol);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * GET CONEXIÓ
	 * 
	 * @return La conexió que s'ha passat al executar la vista
	 */
	public Credencials getCredencial() {
		return credencial;
	}

	/**
	 * MODIFICARÀ LA CREDENCIAL DE LA VISTA
	 * 
	 * @param credencial
	 */
	public void setCredencial(Credencials credencial) {
		this.credencial = credencial;
	}

	/**
	 * GET CONEXIÓ
	 * 
	 * @return La conexió que s'ha passat al executar la vista
	 */
	public Connection getConexio() {
		return conexio;
	}

	/**
	 * MODIFICARÀ LA CONEXIÓ DE LA VISTA
	 * 
	 * @param conexio
	 */
	public void setConexio(Connection conexio) {
		this.conexio = conexio;
	}

	/**
	 * GET LINEAS
	 * 
	 * @return STRING[] amb el text del textArea linea a linea
	 */
	public String[] getLineas() {
		lineas = txtArea.getText().split("\\n");
		return lineas;
	}

	/**
	 * BOTÓ EXECUTAR QUE FARÀ EL EVENT QUE ES CONTROLARÀ EN CONTROLADOR (EXECUTAR
	 * CONSULTA)
	 * 
	 * @param listener
	 */
	public void setBtnExecutar(ActionListener listener) {
		btnExecutar.addActionListener(listener);
	}

	/**
	 * BOTÓ TANCAR CONEXIÓ, QUE FARÀ EL EVENT QUE ES CONTROLARÀ EN CONTROLADOR
	 * (TANCA LA CONEXIÓ)
	 * 
	 * @param listener
	 */
	public void setBtnTancarConexio(ActionListener listener) {
		btnTancarConexio.addActionListener(listener);
	}

	/**
	 * BOTÓ TANCAR SESSIÓ QUE FARÀ EL EVENT QUE ES CONTROLARÀ EN CONTROLADOR (TANCA
	 * LA SESSIÓ)
	 * 
	 * @param listener
	 */
	public void setBtnTancarSessio(ActionListener listener) {
		btnTancarSessio.addActionListener(listener);
	}

	/**
	 * MÉTODE QUE MOSTRA EN LA TABLA TOTES LES DADES
	 * 
	 * @param resultados ArrayList de Strings amb els noms de les columnes y amb els
	 *                   resultats
	 */
	public void mostrarResultados(ArrayList<String[]> resultados) {
		/**
		 * Limpia l'àrea de resultats abans de mostrar nous resultats
		 */
		modeloTabla.setRowCount(0);
		modeloTabla.setColumnCount(0);
		/**
		 * Mostra els noms de les columnes
		 */
		String[] nombresColumnas = resultados.get(0);
		for (String nombreColumna : nombresColumnas) {
			modeloTabla.addColumn(nombreColumna);
		}

		// Mostra les dades de cada fila
		for (int i = 1; i < resultados.size(); i++) {
			String[] fila = resultados.get(i);
			modeloTabla.addRow(fila);
		}

		/**
		 * CENTRARÀ CADA DADA EN EL CENTRE
		 */
		centrarCeldas = new DefaultTableCellRenderer();
		centrarCeldas.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < tblMostrarDades.getColumnCount(); i++) {
			tblMostrarDades.getColumnModel().getColumn(i).setCellRenderer(centrarCeldas);
		}
	}
}
