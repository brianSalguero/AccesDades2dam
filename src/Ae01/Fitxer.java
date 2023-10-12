package Ae01;

public class Fitxer {
	private String nom;
	private String extensio;
	private String tamany;
	private long modificacio;

	/**
	 * 
	 * @param nom         Paràmetre String amb el nom del fitxer.
	 * @param extensio    Paràmetre String amb l'extensió del fitxer.
	 * @param tamany      Paràmetre String amb el tamany del fitxer.
	 * @param modificacio Paràmetre long amb l'última fecha de modificació del
	 *                    fitxer.
	 */
	public Fitxer(String nom, String extensio, String tamany, long modificacio) {
		this.nom = nom;
		this.extensio = extensio;
		this.tamany = tamany;
		this.modificacio = modificacio;
	}

	/**
	 * 
	 * @return Retorna String amb el nom del fitxer.
	 */
	public String getName() {
		return nom;
	}

	/**
	 * 
	 * @return Retorna String amb l'extensió del fitxer.
	 */
	public String getExtension() {
		return extensio;
	}

	/**
	 * 
	 * @return Retorna String amb el tamany del fitxer.
	 */
	public String getSize() {
		return tamany;
	}

	/**
	 * 
	 * @return Retorna long amb la fecha de modificació del fitxer.
	 */
	public long getLastModified() {
		return modificacio;
	}

}
