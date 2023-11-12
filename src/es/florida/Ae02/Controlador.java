package es.florida.Ae02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * CONTROLADOR DEL EVENTS DE LA VISTA PRINCIPAL I LOGIN
 */
public class Controlador {
	private VistaLogin vistaLogin;
	private Vista vistaPrincipal;
	private Model model;
	private Connection con;

	/**
	 * @param vistaLogin Vista de Login
	 * @param model      Model
	 * @param con        Connexió
	 */
	public Controlador(VistaLogin vistaLogin, Model model, Connection con) {
		this.vistaLogin = vistaLogin;
		this.model = model;
		this.con = con;
		initEventHandlersLogin();

	}

	/**
	 * EVENTS DE LA VISTA LOGIN.
	 */
	public void initEventHandlersLogin() {
		/**
		 * EVENTO DEL BOTÓ LOGIN.
		 */
		vistaLogin.setBtnLogin(new ActionListener() {
			/**
			 * REALITZARÀ LA COMPROBACIÓ DE QUE L'USUARI EXISTIX I DE LA EXECUCIÓ DE LA
			 * VISTA PRINCIPAL.
			 */
			public void actionPerformed(ActionEvent e) {

				/**
				 * Agafa l'usuari i la contrasenya que passa l'usuari en Login.
				 */
				String user = vistaLogin.getTxtUsuari();
				String pass = vistaLogin.getTxtPassword();

				/**
				 * Realitzem primer la comprobació de que l'usuari existix. Si no existix obrirà
				 * una finestra pop-up.
				 */
				if (model.comprobacioUsuari(user, pass, con)) {
					/**
					 * Recollim el tipus de usuari que l'usuari ha introduït, es a dir (admin /
					 * client).
					 */
					String userType = model.getCurrentUserType();

					/**
					 * Depenent del tipus de usuari, llegim les credencials que es passa desde un
					 * xml. Segons la credencial, entrarem com admin o client a la vista principal.
					 */
					if ("admin".equals(userType) || "client".equals(userType)) {
						/**
						 * Credencial recollit desde un xml.
						 */
						Credencials credencial = model.llegirXml(userType + ".xml");
						try {
							/**
							 * Fem la conexió amb la credencial donada.
							 */
							con = model.connectUsuari(credencial);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						/**
						 * Tanquem la vista Login.
						 */
						vistaLogin.dispose();

						/**
						 * Obrim la vista Principal.
						 */
						vistaPrincipal = new Vista(credencial, con);

						/**
						 * Pasem els events de eixa vista Principal.
						 */
						initEventHandlers();
					}

					System.out.println("USUARI CONECTAT");
				} else {
					JOptionPane.showMessageDialog(null, "USUARI / CONTRASENYA INCORRECTA", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * EVENTS DE LA VISTA PRINCIPAL
	 */
	public void initEventHandlers() {
		/**
		 * EVENT DEL BOTÓ EXECUTAR CONSULTES
		 */
		vistaPrincipal.setBtnExecutar(new ActionListener() {
			/**
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {

				try {
					/**
					 * Agafem la conexió i la credencial que s'ha passat a l'hora de executar la
					 * vista
					 */
					Connection con = vistaPrincipal.getConexio();
					Credencials credencial = vistaPrincipal.getCredencial();

					/**
					 * Recollim l'String amb totes les llinies que s'han escribit en el textArea de
					 * la vista
					 */
					String[] lineas = vistaPrincipal.getLineas();

					/**
					 * En cas de que la conexió es null o està tancada, no es podrà fer la consulta.
					 */
					if (con != null && !con.isClosed()) {
						/**
						 * Recorreguem cada linea d'eixe Array de String.
						 */
						for (String linea : lineas) {
							/**
							 * Recollim el tipus de consulta, es a dir SELECT/INSERT/UPDATE/DELETE.
							 */
							String tipo = model.executarConsulta(linea);

							/**
							 * Select sempre es podrà fer, ja sigues client o admin.
							 */
							if (tipo == "select") {
								/**
								 * Executem el Select y la mostrem en la tabla de Vista Principal.
								 */
								ArrayList<String[]> llista = model.executarSelect(linea, con);
								vistaPrincipal.mostrarResultados(llista);
							} else if ("admin".equals(credencial.usuari)) {
								/**
								 * En cas que siga admin es podrà fer qualsevol consulta. Si es client no podrà
								 * fer-les.
								 */
								int response = JOptionPane.showConfirmDialog(null,
										"¿Vols realment realitzar la consulta?", "Confirmar consulta",
										JOptionPane.YES_NO_OPTION);

								if (response != JOptionPane.NO_OPTION) {
									if (tipo == "insert") {
										/**
										 * Executem la consulta de INSERT.
										 */
										int resultado = model.executarInsert(linea, con);
										if (resultado > 0) {
											JOptionPane.showMessageDialog(null,
													"S'ha executat la inserció correctament", "Message",
													JOptionPane.INFORMATION_MESSAGE);
										} else {
											JOptionPane.showMessageDialog(null, "Error al executar la inserció",
													"Error", JOptionPane.ERROR_MESSAGE);
										}
									} else if (tipo == "update") {
										/**
										 * Executem la consulta de UPDATE.
										 */
										int resultado = model.executarUpdate(linea, con);
										if (resultado > 0) {
											JOptionPane.showMessageDialog(null,
													"S'ha executat la actulització correctament", "Message",
													JOptionPane.INFORMATION_MESSAGE);
										} else {
											JOptionPane.showMessageDialog(null, "Error al executar la actualització",
													"Error", JOptionPane.ERROR_MESSAGE);
										}
									} else if (tipo == "delete") {
										/**
										 * Executem la consulta de DELETE.
										 */
										int resultado = model.executarDelete(linea, con);
										if (resultado > 0) {
											JOptionPane.showMessageDialog(null,
													"S'ha executat la eliminació correctament", "Message",
													JOptionPane.INFORMATION_MESSAGE);
										} else {
											JOptionPane.showMessageDialog(null, "Error al executar la eliminació",
													"Error", JOptionPane.ERROR_MESSAGE);
										}
									} else {
										JOptionPane.showMessageDialog(null, "Error al executar la consulta", "Error",
												JOptionPane.ERROR_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null, "No s'ha executat cap consulta", "Message",
											JOptionPane.INFORMATION_MESSAGE);
								}

							} else {
								JOptionPane.showMessageDialog(null,
										"No tens permisos per a executar l'acció. A soles pots fer un SELECT",
										"Falta de Permisos", JOptionPane.ERROR_MESSAGE);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "S'ha tancat la conexió", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
				}
			}
		});

		/**
		 * EVENT DEL BOTÓ TANCAR SESSIÓ
		 */
		vistaPrincipal.setBtnTancarSessio(new ActionListener() {
			/**
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					int response = JOptionPane.showConfirmDialog(null, "¿Vols realment tancar la sessio?",
							"Tancar Sessio", JOptionPane.YES_NO_OPTION);
					if (response != JOptionPane.NO_OPTION) {
						/**
						 * Les credencials i la conexió de la Vista Principal, passarem a null ja que la
						 * tancarem.
						 */
						vistaPrincipal.setCredencial(null);
						vistaPrincipal.setConexio(null);

						/**
						 * Tanquem la Vista Principal
						 */
						vistaPrincipal.dispose();
						/**
						 * Fem una nova conexió client per a executar el Login amb les credencials i una
						 * conexió.
						 */
						Credencials credencial = model.llegirXml("client.xml");
						Connection con = model.connectUsuari(credencial);

						/**
						 * Executem la vista Login.
						 */
						VistaLogin vista = new VistaLogin(con);

						/**
						 * Executem el controlador del Login.
						 */
						Controlador controlador = new Controlador(vista, model, con);
					} else {
						JOptionPane.showMessageDialog(null, "No has tancat la sessió", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		/**
		 * EVENT DEL BOTÓ TANCAR CONEXIÓ
		 */
		vistaPrincipal.setBtnTancarConexio(new ActionListener() {
			/**
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					/**
					 * Agafem la conexió que s'ha passat a l'hora de executar la vista Principal.
					 */
					Connection con = vistaPrincipal.getConexio();

					int response = JOptionPane.showConfirmDialog(null, "¿Vols realment tancar la conexio?",
							"Tancar Conexio", JOptionPane.YES_NO_OPTION);
					if (response != JOptionPane.NO_OPTION) {
						// Tanquem la conexió.
						model.tancarConexio(con);
					} else {
						JOptionPane.showMessageDialog(null, "No has tancat la connexió", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

}
