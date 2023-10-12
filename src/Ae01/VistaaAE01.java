package Ae01;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VistaaAE01 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPathCarpeta;
	private JTextField txtCoincidencies;
	private JTextField txtNomFitxer;
	private JLabel lblBuscarCoincidencies;
	private JLabel lblResultat;
	private JLabel lblNomFitxerNou;
	private JButton btnFitxers;
	private JButton btnBuscar;
	private JButton btnFusionar;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private TableRowSorter<TableModel> tablaOrdenar;
	private DefaultTableCellRenderer centrarCeldas;

	public VistaaAE01() {
		setTitle("Gestió de fitxers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtPathCarpeta = new JTextField();
		txtPathCarpeta.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtPathCarpeta.setBounds(235, 76, 252, 28);
		contentPane.add(txtPathCarpeta);
		txtPathCarpeta.setColumns(10);

		JLabel lblTitol = new JLabel("GESTIÓ DE FITXERS");
		lblTitol.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitol.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTitol.setBounds(264, 10, 211, 28);
		contentPane.add(lblTitol);

		JLabel lblMostrarFitxers = new JLabel("Introduix la ruta de una carpeta:");
		lblMostrarFitxers.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostrarFitxers.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMostrarFitxers.setBounds(245, 48, 242, 28);
		contentPane.add(lblMostrarFitxers);

		btnFitxers = new JButton("Mostrar Fitxers");
		btnFitxers.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnFitxers.setBounds(294, 114, 132, 21);
		contentPane.add(btnFitxers);

		tableModel = new DefaultTableModel() {
			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
		};
		tableModel.addColumn("Nom");
		tableModel.addColumn("Extensió");
		tableModel.addColumn("Tamany");
		tableModel.addColumn("Última Modificació");
		
		tablaOrdenar = new TableRowSorter<TableModel>(tableModel);

		table = new JTable(tableModel);
		table.setRowSorter(tablaOrdenar);

		table.setBounds(398, 151, 300, 178);
		
		centrarCeldas = new DefaultTableCellRenderer();
        centrarCeldas.setHorizontalAlignment(SwingConstants.CENTER);
        
        for(int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centrarCeldas);
        }
        
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(136, 156, 457, 178);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		scrollPane.setViewportView(table);

		JLabel lblNewLabel_2 = new JLabel(
				"_____________________________________________________________________________________________");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 334, 747, 28);
		contentPane.add(lblNewLabel_2);

		lblBuscarCoincidencies = new JLabel("Buscar Coincidencies:");
		lblBuscarCoincidencies.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarCoincidencies.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblBuscarCoincidencies.setBounds(10, 374, 154, 28);
		contentPane.add(lblBuscarCoincidencies);

		txtCoincidencies = new JTextField();
		txtCoincidencies.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtCoincidencies.setBounds(173, 376, 116, 25);
		contentPane.add(txtCoincidencies);
		txtCoincidencies.setColumns(10);

		JLabel lblTitolRes = new JLabel("Resultat:");
		lblTitolRes.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolRes.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTitolRes.setBounds(88, 412, 76, 28);
		contentPane.add(lblTitolRes);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnBuscar.setBounds(294, 379, 85, 21);
		contentPane.add(btnBuscar);

		lblResultat = new JLabel("0");
		lblResultat.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblResultat.setBounds(173, 412, 115, 28);
		lblResultat.setVisible(false);
		contentPane.add(lblResultat);

		lblNomFitxerNou = new JLabel("Introduix el nom del fitxer nou");
		lblNomFitxerNou.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomFitxerNou.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNomFitxerNou.setBounds(484, 372, 211, 28);
		contentPane.add(lblNomFitxerNou);

		txtNomFitxer = new JTextField();
		txtNomFitxer.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtNomFitxer.setColumns(10);
		txtNomFitxer.setBounds(536, 400, 116, 25);
		contentPane.add(txtNomFitxer);

		btnFusionar = new JButton("Fusionar");
		btnFusionar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnFusionar.setBounds(546, 435, 85, 21);
		contentPane.add(btnFusionar);

		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		setVisible(true);

	}

	/**
	 * 
	 * @return Retorna String amb la ruta que passa l'usuari en el textField.
	 */
	public String getDirectoryPath() {
		return txtPathCarpeta.getText();
	}

	/**
	 * 
	 * @return Retorna Integer amb el número de filas de la tabla.
	 */
	public Integer getRowCount() {
		return table.getRowCount();
	}

	/**
	 * 
	 * @return Retorna Integer amb el número de columnes de la tabla.
	 */
	public Integer getColumnCount() {
		return table.getColumnCount();
	}

	/**
	 * 
	 * @return Retorna String amb el filtre que volen buscar coincidencies que passa
	 *         l'usuari en el textField.
	 */
	public String getFilter() {
		return txtCoincidencies.getText();
	}

	/**
	 * 
	 * @param row Paràmetre Integer amb el número de filas.
	 * @param col Paràmetre Integer amb el número de columnes.
	 * @return Retorna un Objecte amb la celda selecciona según la fila y la columna
	 *         donada.
	 */
	public Object getCell(int row, int col) {
		return table.getValueAt(row, col);
	}

	/**
	 * 
	 * @return Retorna String amb el nom del fitxer nou que volen donar-li al fitxer
	 *         fusionat que passa l'usuari en el textField.
	 */
	public String getNomFitxer() {
		return txtNomFitxer.getText();
	}

	/**
	 * 
	 * @return Retorna String[] amb els noms del fitxer seleccionats en la tabla.
	 */
	public String[] getFilesSelected() {
		int[] selectedRows = table.getSelectedRows();
		String[] valores = new String[selectedRows.length];

		for (int i = 0; i < selectedRows.length; i++) {
			valores[i] = table.getValueAt(selectedRows[i], 0).toString();
		}

		return valores;
	}

	/**
	 * Mostrarà en la tabla tots els fitxers.
	 * 
	 * @param fitxers Paràmetre ArrayList<Fitxer> amb els fitxers que es mostraràn
	 *                en la tabla.
	 */
	public void setFileList(ArrayList<Fitxer> fitxers) {
		tableModel.setRowCount(0);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		for (Fitxer fitxer : fitxers) {
			String[] rowData = { fitxer.getName(), fitxer.getExtension(), fitxer.getSize(),
					dateFormat.format(fitxer.getLastModified()) };

			tableModel.addRow(rowData);
		}
	}

	/**
	 * Modificarà un label amb el contador del número de coincidencies.
	 * 
	 * @param count Paràmete Integer amb el contador del número de coincidencies.
	 */
	public void setResultat(Integer count) {
		lblResultat.setVisible(true);
		lblResultat.setText(count + " coincidencies");
	}

	/**
	 * 
	 * @param listener Realitza el event del botó de mostrar fitxers. El event es fa
	 *                 en la clase Controlador.
	 */
	public void setBtnFitxers(ActionListener listener) {
		btnFitxers.addActionListener(listener);
	}

	/**
	 * 
	 * @param listener Realitza el event del botó de buscar coincidencies. El event
	 *                 es fa en la clase Controlador.
	 */
	public void setBtnBuscar(ActionListener listener) {
		btnBuscar.addActionListener(listener);
	}

	/**
	 * 
	 * @param listener Realitza el event del botó de fusionar fitxers. El event es
	 *                 fa en la clase Controlador.
	 */
	public void setBtnFusionar(ActionListener listener) {
		btnFusionar.addActionListener(listener);
	}
}
