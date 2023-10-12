package Ae01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ModelAe01 {

	/**
	 * 
	 * @param directoryPath Paràmetre String que ens dona el nom de la ruta de la
	 *                      carpeta a on vols listar tots els fitxers.
	 * @return Retorna tots els fitxers amb extensió '.txt' en un File[].
	 */
	public File[] listTextFiles(String directoryPath) {
		File directory = new File(directoryPath);

		FileFilter extension = new FileFilter() {

			public boolean accept(File file) {
				if (file.getName().endsWith(".txt")) {
					return true;
				}
				return false;
			}
		};

		if (directory.exists() && directory.isDirectory()) {
			File[] files = directory.listFiles(extension);
			return files;
		} else {
			return new File[] { new File("El directorio no existe o no es una carpeta.") };
		}
	}

	/**
	 * 
	 * @param files Paràmetre File[] que ens dona tots el fitxers retornats del
	 *              métode anterior 'listTextFiles'.
	 * @return Retorna una llista de fitxers en un ArrayList<Fitxer>. Els datos dels
	 *         fitxers venen de la clase Fitxer.
	 */
	public ArrayList<Fitxer> getLlistaFitxers(File[] files) {
		ArrayList<Fitxer> llistaFitxers = new ArrayList<>();

		for (File file : files) {
			Fitxer fitxer = new Fitxer(file.getName(), "txt", file.length() + " Bytes", file.lastModified());
			llistaFitxers.add(fitxer);
		}

		return llistaFitxers;
	}

	/**
	 * 
	 * @param nombres       Paràmetre String[] amb tots els noms dels fitxers
	 *                      seleccionats en la tabla.
	 * @param directoryPath Paràmetre String amb la ruta de la carpeta a on s'ha
	 *                      llistat els fitxers
	 * @return Retorna tots els fitxers seleccionats.
	 */
	public File[] filesFusionados(String[] noms, String directoryPath) {

		File[] files = new File[noms.length];
		for (int i = 0; i < noms.length; i++) {
			String nomPath = directoryPath + "/" + noms[i];
			files[i] = new File(nomPath);
		}

		return files;

	}

	/**
	 * 
	 * @param files     Paràmetre File[] amb tots els fitxers seleccionats en la
	 *                  tabla.
	 * @param nomFitxer Paràmetre String amb el nom del fitxer nou.
	 */
	public void fusionarFitxers(File[] files, String nomFitxer) {

		File fitxerNou = new File(nomFitxer + ".txt");

		// Si el fitxer nou existix, ens farà la pregunta de si volem sobreescriure eixe
		// fitxer.
		if (fitxerNou.exists()) {
			int response = JOptionPane.showConfirmDialog(null, "El fitxer ja existix. ¿Vols sobreescriure-ho?",
					"Confirmar sobrescritura", JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.NO_OPTION) {
				return;
			}
		}

		try {

			FileWriter fw = new FileWriter(fitxerNou);
			BufferedWriter bw = new BufferedWriter(fw);

			// Recorreguem tots els fitxers seleccionats, llegim el contingut, y l'escribim
			// llinea a llinea en el fitxer nou.
			for (File selectedFile : files) {
				FileReader fr = new FileReader(selectedFile);
				BufferedReader reader = new BufferedReader(fr);
				String line;
				while ((line = reader.readLine()) != null) {
					bw.write(line);
					bw.newLine();
				}
				reader.close();
			}
			JOptionPane.showMessageDialog(null, "Fitxers fusionats correctament.", "Mensaje",
					JOptionPane.INFORMATION_MESSAGE);

			bw.close();
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
