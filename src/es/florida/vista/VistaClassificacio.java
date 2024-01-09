package es.florida.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;

/**
 * The Class VistaClassificacio.
 */
public class VistaClassificacio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table2x4;
	private JTable table4x4;
	private JLabel lblTitol;
	private JLabel lbl2x4;
	private JLabel lbl4x4;
	private DefaultTableModel modeloTabla;
	private DefaultTableCellRenderer centrarCeldas;
	private DefaultTableModel modeloTabla2;
	private DefaultTableCellRenderer centrarCeldas2;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane2;
	private JButton btnTornar;

	/**
	 * Create the frame.
	 */
	public VistaClassificacio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 790);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

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

		table2x4 = new JTable(modeloTabla);
		table2x4.setBounds(88, 140, 628, 247);
		contentPane.add(table2x4);

		scrollPane = new JScrollPane(table2x4);
		scrollPane.setBounds(88, 124, 628, 247);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table2x4);

		lblTitol = new JLabel("CLASSIFICACIÓ");
		lblTitol.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitol.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblTitol.setBounds(272, 25, 312, 43);
		contentPane.add(lblTitol);

		lbl2x4 = new JLabel("2 x 4");
		lbl2x4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2x4.setFont(new Font("Arial Black", Font.BOLD, 20));
		lbl2x4.setBounds(88, 85, 76, 29);
		contentPane.add(lbl2x4);

		lbl4x4 = new JLabel("4 x 4");
		lbl4x4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4x4.setFont(new Font("Arial Black", Font.BOLD, 20));
		lbl4x4.setBounds(88, 399, 76, 29);
		contentPane.add(lbl4x4);

		modeloTabla2 = new DefaultTableModel() {
			/**
			 * Default serial version modeloTabla
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table4x4 = new JTable(modeloTabla2);
		table4x4.setBounds(88, 454, 628, 247);
		contentPane.add(table4x4);

		scrollPane2 = new JScrollPane(table4x4);
		scrollPane2.setBounds(88, 438, 628, 247);
		contentPane.add(scrollPane2);
		scrollPane2.setViewportView(table4x4);

		btnTornar = new JButton("TORNAR");
		btnTornar.setFont(new Font("Arial", Font.BOLD, 16));
		btnTornar.setBounds(571, 695, 145, 29);
		contentPane.add(btnTornar);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Event botó tornar
	 *
	 * @param listener Listener
	 */
	public void setBtnTornar(ActionListener listener) {
		btnTornar.addActionListener(listener);
	}

	/**
	 * Mostra resultats 2 x 4.
	 *
	 * @param resultats llista de les files de la base de dades
	 */
	public void mostrarResultats2x4(ArrayList<String[]> resultats) {
		/**
		 * Limpia l'àrea de resultats abans de mostrar nous resultats
		 */
		modeloTabla.setRowCount(0);
		modeloTabla.setColumnCount(0);
		/**
		 * Mostra els noms de les columnes
		 */
		String[] nombresColumnas = resultats.get(0);
		for (String nombreColumna : nombresColumnas) {
			modeloTabla.addColumn(nombreColumna);
		}

		Object[][] data = new Object[resultats.size() - 1][];
		for (int i = 1; i < resultats.size(); i++) {
			data[i - 1] = resultats.get(i);
		}

		Arrays.sort(data, (a, b) -> Integer.compare(Integer.parseInt((String) a[4]), Integer.parseInt((String) b[4])));

		for (Object[] fila : data) {
			modeloTabla.addRow(fila);
		}

		/**
		 * CENTRARÀ CADA DADA EN EL CENTRE
		 */
		centrarCeldas = new DefaultTableCellRenderer();
		centrarCeldas.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < table2x4.getColumnCount(); i++) {
			table2x4.getColumnModel().getColumn(i).setCellRenderer(centrarCeldas);
		}
	}

	/**
	 * Mostra resultats 4 x 4.
	 *
	 * @param resultats llista de les files de la base de dades
	 */
	public void mostrarResultats4x4(ArrayList<String[]> resultats) {
		/**
		 * Limpia l'àrea de resultats abans de mostrar nous resultats
		 */
		modeloTabla2.setRowCount(0);
		modeloTabla2.setColumnCount(0);
		/**
		 * Mostra els noms de les columnes
		 */
		String[] nombresColumnas = resultats.get(0);
		for (String nombreColumna : nombresColumnas) {
			modeloTabla2.addColumn(nombreColumna);
		}

		Object[][] data = new Object[resultats.size() - 1][];
		for (int i = 1; i < resultats.size(); i++) {
			data[i - 1] = resultats.get(i);
		}

		Arrays.sort(data, (a, b) -> Integer.compare(Integer.parseInt((String) a[4]), Integer.parseInt((String) b[4])));

		for (Object[] fila : data) {
			modeloTabla2.addRow(fila);
		}

		/**
		 * CENTRARÀ CADA DADA EN EL CENTRE
		 */
		centrarCeldas2 = new DefaultTableCellRenderer();
		centrarCeldas2.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < table4x4.getColumnCount(); i++) {
			table4x4.getColumnModel().getColumn(i).setCellRenderer(centrarCeldas2);
		}
	}

}
