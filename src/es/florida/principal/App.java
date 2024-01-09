package es.florida.principal;

import es.florida.controlador.Controlador;
import es.florida.model.Model;
import es.florida.vista.VistaPrincipi;

/**
 * CLASSE APP PRINCIPAL.
 */
public class App {
	
	/**
	 * Métode main amb la vista principal i el model.
	 *
	 * @param args Arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model model = new Model();
		VistaPrincipi vista = new VistaPrincipi();

		@SuppressWarnings("unused")
		Controlador controlador = new Controlador(vista, model);
	}

}
