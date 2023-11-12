package es.florida.Ae02;

import java.io.File;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * MODEL QUE ARREPLEGARÀ LES FUNCIONALITATS AMB LA BASE DE DADES
 */
public class Model {
	private String currentUserType;

	/**
	 * FUNCIONALITAT QUE ENS TORNARÀ LA CONNEXIO AMB LA CREDENCIAL QUE PASSEM
	 * 
	 * @param credencial
	 * @return Connexio
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connection connectUsuari(Credencials credencial) throws SQLException, ClassNotFoundException {
		String url = credencial.getUrl();
		String user = credencial.getUsuari();
		String password = credencial.getContrasenya();
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * FUNCIONALITAT QUE ENS VERIFICARÀ SI LA CONTRASENYA INTRODUÏDA ES CORRECTA.
	 * COMPARARÀ LA CONTRASENYA PASSADA AMB LA CONTRASENYA DE LA BASE DE DADES.
	 * 
	 * @param passIntroducit       Password passada per l'usuari
	 * @param storedHashedPassword Password de la base de dades
	 * @return boolean: Si es correcta true, sino false
	 */
	private boolean verifyPassword(String passIntroducit, String storedHashedPassword) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hashedBytes = md.digest(passIntroducit.getBytes());

			/**
			 * Convertir los bytes a hexadecimal
			 */
			StringBuilder stringBuilder = new StringBuilder();
			for (byte b : hashedBytes) {
				stringBuilder.append(String.format("%02x", b));
			}

			String hashedPassword = stringBuilder.toString();
			/**
			 * Comparar el hash proporcionado con el almacenado
			 */
			return hashedPassword.equals(storedHashedPassword);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * FUNCIONALITAT QUE COMPROBARÀ SI L'USUARI I CONTRASENYA ES CORRECTA
	 * 
	 * @param usuari
	 * @param contrasenya
	 * @param con
	 * @return boolean: En cas de ser correcte, torna true, sino false.
	 */
	public boolean comprobacioUsuari(String usuari, String contrasenya, Connection con) {
		try {
			/**
			 * Fem la sentencia SELECT per a visualitzar si l'usuari existix.
			 */
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE user = '" + usuari + "';");

			while (rs.next()) {
				/**
				 * En cas de existir l'usuari, comparem la contrasenya passada amb la
				 * contrasenya de la base de dades.
				 */
				String storedHashedPassword = rs.getString("pass");
				if (verifyPassword(contrasenya, storedHashedPassword)) {
					currentUserType = rs.getString("type");
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/**
	 * FUNCIONALITAT PER A LLEGIR EL XML DE ADMIN O CLIENT
	 * 
	 * @param ruta : Ruta del XML
	 * @return Retorna la Credencial amb la url, usuari i contrasenya extraida del
	 *         Xml
	 */
	public Credencials llegirXml(String ruta) {
		try {
			String url;
			String usuari;
			String password;

			File fitxerCredencial = new File(ruta);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(fitxerCredencial);

			/**
			 * Recorreguem tots els elements y guardem el Credencial.
			 */
			NodeList nodeList = document.getElementsByTagName("conexio");
			for (int i = 0; i < nodeList.getLength();) {
				Node node = nodeList.item(i);
				Element eElement = (Element) node;
				url = eElement.getElementsByTagName("url").item(0).getTextContent();
				usuari = eElement.getElementsByTagName("usuari").item(0).getTextContent();
				password = eElement.getElementsByTagName("password").item(0).getTextContent();
				Credencials credencial = new Credencials(url, usuari, password);
				return credencial;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * FUNCIONALITAT QUE EXECUTA LA CONSULTA DE SELECT I LA GUARDA EN UN ARRAYLIST
	 * DE STRING[]
	 * 
	 * @param consulta
	 * @param con
	 * @return Torna una ArrayList amb els noms de la columna y els resultats de la
	 *         consulta.
	 */
	public ArrayList<String[]> executarSelect(String consulta, Connection con) {
		ArrayList<String[]> llistaResultat = new ArrayList<>();

		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(consulta);
			int columnas = rs.getMetaData().getColumnCount();
			String[] nomsColumns = new String[columnas];
			for (int i = 1; i <= nomsColumns.length; i++) {
				nomsColumns[i - 1] = rs.getMetaData().getColumnLabel(i);
			}
			llistaResultat.add(nomsColumns);

			while (rs.next()) {
				String[] resDades = new String[columnas];
				for (int i = 1; i <= nomsColumns.length; i++) {
					resDades[i - 1] = rs.getString(i);
				}
				llistaResultat.add(resDades);
			}
			JOptionPane.showMessageDialog(null, "S'ha executat la consulta correctament", "Message",
					JOptionPane.INFORMATION_MESSAGE);
			rs.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al executar la consulta!", "Message",
					JOptionPane.INFORMATION_MESSAGE);
		}
		return llistaResultat;

	}

	/**
	 * FUNCIONALITAT QUE EXECUTA LA CONSULTA DE INSERT
	 * 
	 * @param consulta
	 * @param con
	 * @return Torna un int amb el tipus de resultat
	 */
	public int executarInsert(String consulta, Connection con) {
		int resultadoInsertar = 0;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(consulta);
			resultadoInsertar = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultadoInsertar;
	}

	/**
	 * FUNCIONALITAT QUE EXECUTA LA CONSULTA DE UPDATE
	 * 
	 * @param consulta
	 * @param con
	 * @return Torna un int amb el tipus de resultat
	 */
	public int executarUpdate(String consulta, Connection con) {
		int resultadoActualitzar = 0;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(consulta);
			resultadoActualitzar = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultadoActualitzar;
	}

	/**
	 * FUNCIONALITAT QUE EXECUTA LA CONSULTA DE DELETE
	 * 
	 * @param consulta
	 * @param con
	 * @return Torna un int amb el tipus de resultat
	 */
	public int executarDelete(String consulta, Connection con) {
		int resultatDelete = 0;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(consulta);
			resultatDelete = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultatDelete;
	}

	/**
	 * FUNCIONALITAT QUE ENS DONA EL TIPUS DE CONSULTA
	 * 
	 * @param consulta
	 * @return Torna el tipus de consulta (select/insert/update/delete)
	 */
	public String executarConsulta(String consulta) {
		String tipo = "";
		try {
			if (consulta.trim().toLowerCase().startsWith("select")) {
				tipo = "select";
			} else if (consulta.trim().toLowerCase().startsWith("insert")) {
				tipo = "insert";
			} else if (consulta.trim().toLowerCase().startsWith("update")) {
				tipo = "update";
			} else if (consulta.trim().toLowerCase().startsWith("delete")) {
				tipo = "delete";
			} else {
				tipo = "errorPermisos";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipo;
	}

	/**
	 * FUNCIONALITA PER A TANCAR LA CONEXIÓ
	 * 
	 * @param con
	 * @throws SQLException
	 */
	public void tancarConexio(Connection con) throws SQLException {
		if (con != null && !con.isClosed()) {
			con.close();
		}
	}

	/**
	 * @return Torna un String amb el tipus de usuari (admin/client)
	 */
	public String getCurrentUserType() {
		return currentUserType;
	}
}
