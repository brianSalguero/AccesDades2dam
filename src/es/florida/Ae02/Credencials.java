package es.florida.Ae02;

/**
 * CLASE CREDENCIAL QUE RECOLLIRÀ LES DADES DEL XML.
 */
public class Credencials {
	String url;
	String usuari;
	String contrasenya;
	
	/**
	 * @param url
	 * @param usuari
	 * @param contrasenya
	 */
	public Credencials(String url, String usuari, String contrasenya) {
		this.url = url;
		this.usuari = usuari;
		this.contrasenya = contrasenya;
	}

	/**
	 * @return String url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return String usuari
	 */
	public String getUsuari() {
		return usuari;
	}

	/**
	 * @return String contrasenya
	 */
	public String getContrasenya() {
		return contrasenya;
	}
	
}
