package es.florida.Ae02;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * CLASE QUE EXECUTARÀ LA APLICACIÓ
 */
public class Principal {

	/**
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		Model model = new Model();
		Credencials credencial = model.llegirXml("client.xml");
		Connection con = model.connectUsuari(credencial);

		VistaLogin vista = new VistaLogin(con);

		Controlador controlador = new Controlador(vista, model, con);
	}

}
