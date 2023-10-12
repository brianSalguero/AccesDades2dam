package Ae01;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ControladorAe01 {

	private VistaaAE01 vista;
	private ModelAe01 model;

	/**
	 * 
	 * @param vista
	 * @param model
	 */
	public ControladorAe01(VistaaAE01 vista, ModelAe01 model) {
		this.vista = vista;
		this.model = model;
		initEventHandlers();
	}

	/**
	 * Métode on es guarda tots els events que s'utilitza al accionar els botons de
	 * 'Mostrar Fitxers', 'Buscar Coincidencies de Fitxers' i 'Fusionar Fitxers'.
	 */
	public void initEventHandlers() {
		/**
		 * Event Botó de Mostrar Fitxers. Ens Mostrarà en el 'JTable' tots el fitxers
		 * del directory proporcionat.
		 */
		vista.setBtnFitxers(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String directoryPath = vista.getDirectoryPath();
				File[] files = model.listTextFiles(directoryPath);
				ArrayList<Fitxer> fitxers = model.getLlistaFitxers(files);
				vista.setFileList(fitxers);
			}
		});

		/**
		 * Event Botó de Buscar Coincidencies de Fitxers. Ens contarà quantes
		 * coincidencies tenen en totes les celdes de la tabla el string proporcionat.
		 */
		vista.setBtnBuscar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer rowCount = vista.getRowCount();
				Integer columnCount = vista.getColumnCount();
				String buscar = vista.getFilter();
				Integer count = 0;

				for (int row = 0; row < rowCount; row++) {
					for (int col = 0; col < columnCount; col++) {
						Object cellValue = vista.getCell(row, col);
						if (cellValue != null && cellValue.toString().contains(buscar)) {
							count++;
						}
					}
				}

				vista.setResultat(count);
			}
		});

		/**
		 * Event Botó de Fusionar Fitxers Seleccionats. Ens fusionarà tots els fitxers
		 * seleccionats de la tabla en un únic fitxer.
		 */
		vista.setBtnFusionar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String directoryPath = vista.getDirectoryPath();
				String nomFitxer = vista.getNomFitxer();
				String[] fitxersSeleccionats = vista.getFilesSelected();
				File[] files = model.filesFusionados(fitxersSeleccionats, directoryPath);
				try {
					model.fusionarFitxers(files, nomFitxer);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
