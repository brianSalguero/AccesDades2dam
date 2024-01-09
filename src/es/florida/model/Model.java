package es.florida.model;

import static com.mongodb.client.model.Filters.*;

import java.io.ByteArrayInputStream;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.apache.commons.codec.binary.Base64;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;

/**
 * CLASSE MODEL PER A CONTROLAR TOT LO RELACIONAT AMB LA BASE DE DADES.
 */
public class Model {
	
	String IP;
	Integer port;
	String baseDades;
	String coleccio;
	
	private void conexioBaseDades(String tipo) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File jsonFile = new File("./conexio.json");
            JsonNode jsonNode = objectMapper.readTree(jsonFile);

            IP = jsonNode.get("IP").asText();
            port = jsonNode.get("port").asInt();
            baseDades = jsonNode.get("database").asText();
            
            if (tipo.equals("imatges")) {
            	coleccio = jsonNode.get("collection_img").asText();
            }else if (tipo.equals("records")) {
            	coleccio = jsonNode.get("collection_records").asText();
            }else if(tipo.equals("usuaris")){
            	coleccio = jsonNode.get("collection_usuaris").asText();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

	/**
	 * Converteix la contrasenya en hash SHA-256 per a pasar-ho a la base de dades.
	 *
	 * @param contrasenya String amb la contrasenya
	 * @return String amb la contrasenya convertida
	 */
	public String convertirContrasenya(String contrasenya) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			byte[] encodedHash = digest.digest(contrasenya.getBytes(StandardCharsets.UTF_8));

			StringBuilder hexStringBuilder = new StringBuilder();
			for (byte b : encodedHash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexStringBuilder.append('0');
				}
				hexStringBuilder.append(hex);
			}

			return hexStringBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Registra l'usuari en la Base de Dades.
	 *
	 * @param usuari          String amb el nom de l'usuari
	 * @param contrasenyaHash String amb la contrasenya en Hash SHA-256
	 */
	public void registrarUsuari(String usuari, String contrasenyaHash) {
		conexioBaseDades("usuaris");
		MongoClient mongoClient = new MongoClient(IP, port);
		MongoDatabase database = mongoClient.getDatabase(baseDades);
		MongoCollection<Document> coleccion = database.getCollection(coleccio);

		Document doc = new Document();
		doc.append("user", usuari);
		doc.append("pass", contrasenyaHash);
		coleccion.insertOne(doc);
		mongoClient.close();
	}

	/**
	 * Verifica si existix un usuari amb eixe nom.
	 *
	 * @param usuari String amb el nom de l'usuari a verificar
	 * @return Boolean si existix un usuari registrat torna true
	 */
	public boolean verificarUsuariExistix(String usuari) {
		conexioBaseDades("usuaris");
		MongoClient mongoClient = new MongoClient(IP, port);
		MongoDatabase database = mongoClient.getDatabase(baseDades);
		MongoCollection<Document> coleccion = database.getCollection(coleccio);

		Bson query = eq("user", usuari);
		MongoCursor<Document> cursor = coleccion.find(query).iterator();
		mongoClient.close();
		return cursor.hasNext();
	}

	/**
	 * Verifica si l'usuari i la contrasenya son correctes.
	 *
	 * @param usuari      String amb el nom del usuari
	 * @param contrasenya String amb la contrasenya
	 * @return Boolean si està correcte les credencials torna true
	 */
	public boolean verificarUsuari(String usuari, String contrasenya) {
		conexioBaseDades("usuaris");
		MongoClient mongoClient = new MongoClient(IP, port);
		MongoDatabase database = mongoClient.getDatabase(baseDades);
		MongoCollection<Document> coleccion = database.getCollection(coleccio);

		String contra = convertirContrasenya(contrasenya);
		Bson query = and(eq("user", usuari), eq("pass", contra));
		MongoCursor<Document> cursor = coleccion.find(query).iterator();
		mongoClient.close();
		return (cursor.hasNext());
	}

	/**
	 * Obté les imatges en Base 64 de la Base de dades y torna segons la dificultat
	 * un numero d'imatges. Dificultat 8: Obté 4 imatges Dificultat 16: Obté 8
	 * imatges Duplica la llista 2 vegades i la barreja amb un Collection.suffle()
	 * 
	 * @param tipo Integer amb el tipo de dificultat. (1 = 2 x 4 || 2 = 4 x 4)
	 * @return ArrayList de imatges en Base64
	 */
	public ArrayList<String> obtenirImatgesBase64(Integer tipo) {
		ArrayList<String> llistaImatgesBase64 = new ArrayList<>();
		conexioBaseDades("imatges");
		MongoClient mongoClient = new MongoClient(IP, port);
		MongoDatabase database = mongoClient.getDatabase(baseDades);
		MongoCollection<Document> coleccion = database.getCollection(coleccio);

		if (tipo == 1) {
			AggregateIterable<Document> imatgesAleatories = coleccion.aggregate(List.of(Aggregates.sample(4)));

			imatgesAleatories.forEach((Consumer<Document>) imatge -> {
				JSONObject obj = new JSONObject(imatge.toJson());
				String base64 = obj.getString("base64");
				llistaImatgesBase64.add(base64);
			});
		} else if (tipo == 2) {
			AggregateIterable<Document> imatgesAleatories = coleccion.aggregate(List.of(Aggregates.sample(8)));

			imatgesAleatories.forEach((Consumer<Document>) imatge -> {
				JSONObject obj = new JSONObject(imatge.toJson());
				String base64 = obj.getString("base64");
				llistaImatgesBase64.add(base64);
			});
		}
		ArrayList<String> llistaDuplicada = new ArrayList<>(llistaImatgesBase64);
		llistaDuplicada.addAll(llistaImatgesBase64);
		Collections.shuffle(llistaDuplicada);
		mongoClient.close();

		return llistaDuplicada;
	}

	/**
	 * Obté les Imatges de la llista d'imatges en Base 64.
	 *
	 * @param llistaImatgesBase64 ArrayList d'Icons que s'han convertit de la base
	 *                            64
	 * @return ArrayList amb les imatges
	 */
	public ArrayList<ImageIcon> obtenirImatgesIcons(ArrayList<String> llistaImatgesBase64) {
		guardarImatges(llistaImatgesBase64);
		ArrayList<ImageIcon> llistaIcons = new ArrayList<>();

		for (String base64 : llistaImatgesBase64) {
			try {
				byte[] btDataFile = Base64.decodeBase64(base64);
				BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(btDataFile));
				Image imatge = bufferedImage.getScaledInstance(-1, 120, java.awt.Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(imatge);
				llistaIcons.add(imageIcon);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return llistaIcons;
	}

	/**
	 * Genera un nombre únic per a guardar Imatges en un directori.
	 *
	 * @param baseName  String amb la ruta y part del nom de la imatge
	 * @param extension String amb la extensio de la imatge
	 * @return String amb el nom de la imatge
	 */
	private String generarNombreUnic(String baseName, String extension) {
		int contador = 1;
		String fileName = baseName + contador + extension;

		while (new File(fileName).exists()) {
			contador++;
			fileName = baseName + contador + extension;
		}

		return fileName;
	}

	/**
	 * Guarda les imatges carregades en un directori.
	 *
	 * @param llistaImatges the llista imatges
	 */
	public void guardarImatges(ArrayList<String> llistaImatges) {
		for (int i = 0; i < llistaImatges.size(); i++) {
			String imatgeBase64 = llistaImatges.get(i);
			String fileName = generarNombreUnic("img/imatge", ".jpg");

			try {
				byte[] btDataFile = Base64.decodeBase64(imatgeBase64);
				BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(btDataFile));
				ImageIO.write(bufferedImage, "jpg", new File(fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Métode per a guardar una partida.
	 *
	 * @param usuari     String amb el nom de l'usuari
	 * @param dificultad int amb la dificultat de la partida
	 * @param temps      String amb la data y l'hora que ha acabat la partida
	 * @param duracio    int amb la duracio que ha tardat
	 */
	public void guardarPartida(String usuari, int dificultad, String temps, int duracio) {
		conexioBaseDades("records");
		MongoClient mongoClient = new MongoClient(IP, port);
		MongoDatabase database = mongoClient.getDatabase(baseDades);
		MongoCollection<Document> coleccion = database.getCollection(coleccio);

		Document doc = new Document();
		doc.append("usuario", usuari);
		doc.append("dificultad", dificultad);
		doc.append("timestamp", temps);
		doc.append("duracion", duracio);
		coleccion.insertOne(doc);
		mongoClient.close();

		JOptionPane.showMessageDialog(null, "Has guardat la partida correctament", "Message",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Obté el menor temps de la dificultat 8 i 16.
	 * 
	 * @return ArrayList Llista amb el menor temps i l'usuari que l'ha fet
	 */
	public ArrayList<Object> obtindreMillorTemps() {
		conexioBaseDades("records");
		MongoClient mongoClient = new MongoClient(IP, port);
		MongoDatabase database = mongoClient.getDatabase(baseDades);
		MongoCollection<Document> coleccion = database.getCollection(coleccio);

		int segMin = Integer.MAX_VALUE;
		String usuario = "";
		int segMin2 = Integer.MAX_VALUE;
		String usuario2 = "";

		Bson query = eq("dificultad", 8);
		MongoCursor<Document> cursor = coleccion.find(query).iterator();
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			int duracion = obj.getInt("duracion");
			String usu = obj.getString("usuario");
			if (duracion < segMin) {
				segMin = duracion;
				usuario = usu;
			}
		}

		Bson query2 = eq("dificultad", 16);
		MongoCursor<Document> cursor2 = coleccion.find(query2).iterator();
		while (cursor2.hasNext()) {
			JSONObject obj = new JSONObject(cursor2.next().toJson());
			int duracion = obj.getInt("duracion");
			String usu = obj.getString("usuario");
			if (duracion < segMin2) {
				segMin2 = duracion;
				usuario2 = usu;
			}
		}

		ArrayList<Object> resultat = new ArrayList<>();
		resultat.add(segMin);
		resultat.add(usuario);
		resultat.add(segMin2);
		resultat.add(usuario2);

		mongoClient.close();
		return resultat;
	}

	/**
	 * Mostra els resultats de la dificultat 2 x 4.
	 *
	 * @return ArrayList amb totes les files de la base de dades
	 */
	public ArrayList<String[]> mostrarResultats2x4() {
		ArrayList<String[]> llistaResultat = new ArrayList<>();
		
		conexioBaseDades("records");
		MongoClient mongoClient = new MongoClient(IP, port);
		MongoDatabase database = mongoClient.getDatabase(baseDades);
		MongoCollection<Document> coleccion = database.getCollection(coleccio);

		Bson query = eq("dificultad", 8);
		MongoCursor<Document> cursor = coleccion.find(query).iterator();
		Document primerDocumento = coleccion.find().first();

		String[] nomsColumnes = primerDocumento.keySet().toArray(new String[0]);
		llistaResultat.add(nomsColumnes);

		while (cursor.hasNext()) {
			Document document = cursor.next();
			LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();

			for (String key : document.keySet()) {
				Object value = document.get(key);

				if (value instanceof Integer) {
					resultMap.put(key, String.valueOf(value));
				} else {
					resultMap.put(key, value.toString());
				}
			}

			String[] resultArray = new String[resultMap.size()];
			int i = 0;
			for (String value : resultMap.values()) {
				resultArray[i++] = value;
			}
			llistaResultat.add(resultArray);
		}

		mongoClient.close();
		return llistaResultat;
	}

	/**
	 * Mostra els resultats de la dificultat 4 x 4.
	 *
	 * @return ArrayList amb totes les files de la base de dades
	 */
	public ArrayList<String[]> mostrarResultats4x4() {
		ArrayList<String[]> llistaResultat = new ArrayList<>();
		
		conexioBaseDades("records");
		MongoClient mongoClient = new MongoClient(IP, port);
		MongoDatabase database = mongoClient.getDatabase(baseDades);
		MongoCollection<Document> coleccion = database.getCollection(coleccio);

		Bson query = eq("dificultad", 16);
		MongoCursor<Document> cursor = coleccion.find(query).iterator();
		Document primerDocumento = coleccion.find().first();

		String[] nomsColumnes = primerDocumento.keySet().toArray(new String[0]);
		llistaResultat.add(nomsColumnes);

		while (cursor.hasNext()) {
			Document document = cursor.next();
			LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();

			for (String key : document.keySet()) {
				Object value = document.get(key);

				if (value instanceof Integer) {
					resultMap.put(key, String.valueOf(value));
				} else {
					resultMap.put(key, value.toString());
				}
			}

			String[] resultArray = new String[resultMap.size()];
			int i = 0;
			for (String value : resultMap.values()) {
				resultArray[i++] = value;
			}
			llistaResultat.add(resultArray);
		}

		mongoClient.close();
		return llistaResultat;
	}
}