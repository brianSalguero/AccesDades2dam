package es.florida.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import es.florida.vista.*;
import es.florida.model.Model;

/**
 * CLASSE CONTROLADOR
 */
public class Controlador {

	/** Vista Principal. */
	private VistaPrincipi vistaPrincipi;
	/** Vista Joc. */
	private VistaJoc vistaJoc;
	/** Vista Iniciar Sessio. */
	private VistaIniciarSessio vistaIniciarSessio;
	/** Model. */
	private Model model;
	/** Vista Registre. */
	private VistaRegistre vistaRegistre;
	/** Vista Graella. */
	private VistaElegirGraella vistaGraella;
	/** Vista Classificacio. */
	private VistaClassificacio vistaClassificacio;
	/** Llista Imatges. */
	private ArrayList<ImageIcon> llistaImatges;
	/** Llista imatges base 64. */
	private ArrayList<String> llistaImatgesBase64;
	/** Format data hora. */
	private DateTimeFormatter formatDataHora = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
	/** Imatge primer boto. */
	private ImageIcon imatgePrimerBoto;
	/** Primer boto. */
	private JButton primerBoto;
	/** Primer boto clicat. */
	private boolean primerBotoClicat = false;
	/** Primer boto String. */
	private String primerBotoString = "";
	/** Usuari loguejat. */
	private String usuariLoguejat;
	/** Tipo de dificultat. */
	private Integer tipo;
	/** Timer. */
	private Timer timer;
	/** Segons. */
	private Integer segons;
	/** Temps acabat. */
	private String tempsAcabat;
	/** Segons Actuals. */
	private int segonsActuals;
	/** Menor temps segons 2 x 4. */
	private int menorTempsSegons2x4;
	/** Menor temps segons 4 x 4. */
	private int menorTempsSegons4x4;

	/**
	 * Constructor amb la vista Principal y el model.
	 *
	 * @param vistaPrincipi Vista principal
	 * @param model         Model amb els métodes de la Base de Dades
	 */
	public Controlador(VistaPrincipi vistaPrincipi, Model model) {
		this.vistaPrincipi = vistaPrincipi;
		this.model = model;
		initEventHandlersPrincipi();
	}

	/**
	 * Métode per a controlar els events de la Vista Principal.
	 */
	public void initEventHandlersPrincipi() {
		vistaPrincipi.setBtnRegistrarse(new ActionListener() {
			/**
			 * Event per a obrir la vista Registre
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				vistaPrincipi.dispose();
				vistaRegistre = new VistaRegistre();
				initEventHandlersRegistre();
			}
		});

		vistaPrincipi.setBtnIniciSessio(new ActionListener() {
			/**
			 * Event per a obrir la vista Inciar Sessio
			 */
			public void actionPerformed(ActionEvent e) {
				vistaPrincipi.dispose();
				vistaIniciarSessio = new VistaIniciarSessio();
				initEventHandlerIniciSessio();
			}
		});
	}

	/**
	 * Métode per a controlar els events de la Vista Registre.
	 */
	public void initEventHandlersRegistre() {

		vistaRegistre.setBtnTornar(new ActionListener() {
			/**
			 * Event per a tornar a la Vista Principal
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				vistaRegistre.dispose();
				vistaPrincipi = new VistaPrincipi();
				initEventHandlersPrincipi();
			}
		});

		vistaRegistre.setBtnRegistre(new ActionListener() {
			/**
			 * Evenet per a Registrar un usuari
			 */
			public void actionPerformed(ActionEvent e) {
				String contrasenya = vistaRegistre.getTxtContrasenya();
				String contrasenya2 = vistaRegistre.getTxtContrasenya2();
				String usuari = vistaRegistre.getTxtUsuari();
				model = new Model();

				if (!contrasenya.equals("") && !usuari.equals("")) {
					// Comproba si la contrasenya es igual a la repeticio de contrasenya
					if (contrasenya.equals(contrasenya2)) {
						// Verifica si existix o no ja eixe usuari
						if (!model.verificarUsuariExistix(usuari)) {
							// Converteix a contrasenya Hash
							String contrasenyaHash = model.convertirContrasenya(contrasenya);
							// Registra l'usuari
							model.registrarUsuari(usuari, contrasenyaHash);

							JOptionPane.showMessageDialog(null, "S'ha registrat l'usuari amb nom: " + usuari, "Message",
									JOptionPane.INFORMATION_MESSAGE);
							// Torna a la vista principal una vegada registrat
							vistaRegistre.dispose();
							vistaPrincipi = new VistaPrincipi();
							initEventHandlersPrincipi();
						} else {
							JOptionPane.showMessageDialog(null, "Ja existix un usuari amb eixe nom", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Les contrasenyes no conicidixen", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Per favor, completa tota la informació", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Métode per a controlar els events d'Inici de Sessio
	 */
	public void initEventHandlerIniciSessio() {

		vistaIniciarSessio.setBtnTornar(new ActionListener() {
			/**
			 * Event per a tornar a la Vista principal
			 */
			public void actionPerformed(ActionEvent e) {
				vistaIniciarSessio.dispose();
				vistaPrincipi = new VistaPrincipi();
				initEventHandlersPrincipi();
			}
		});

		vistaIniciarSessio.setBtnIniciarSessio(new ActionListener() {
			/**
			 * Event per a Iniciar Sessio
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				String contrasenya = vistaIniciarSessio.getTxtContrasenya();
				String usuari = vistaIniciarSessio.getTxtUsuari();
				model = new Model();

				/**
				 * Verifica si l'usuari es correcte o no
				 */
				if (model.verificarUsuari(usuari, contrasenya)) {
					JOptionPane.showMessageDialog(null, "Usuari i contrasenya correcta", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					usuariLoguejat = usuari;
					vistaIniciarSessio.dispose();
					vistaJoc = new VistaJoc();
					initEventHandlerJoc();
				} else {
					JOptionPane.showMessageDialog(null, "Usuari i/o contrasenya incorrecta", "Error",
							JOptionPane.ERROR_MESSAGE);
					vistaIniciarSessio.setTxtContrasenya("");
				}
			}
		});
	}

	/**
	 * Actualitze el temps en el label segons recorra els segons.
	 */
	private void actualitzarTempsLabel() {
		int minuts = segons / 60;
		int segonsRestants = segons % 60;
		String tempsFormatejat = String.format("%02d:%02d", minuts, segonsRestants);
		vistaJoc.setLblTemps(tempsFormatejat);
	}

	/**
	 * Inicie el contador del joc.
	 */
	private void iniciarContador() {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				segons++;
				actualitzarTempsLabel();
			}
		});
		timer.start();
	}

	/**
	 * Agafa els menor temps de la base de dades i lo mostra en el joc.
	 */
	private void menorTemps() {
		ArrayList<Object> llistaMenorTemps = model.obtindreMillorTemps();
		int temps = (int) llistaMenorTemps.get(0);
		// Guarde el temps en la dificultat corresponent
		menorTempsSegons2x4 = temps;
		// Formateje el temps en minuts i segons
		int minuts = temps / 60;
		int seg = temps % 60;
		String tempsFormatejat = String.format("%02d:%02d", minuts, seg);
		String usuari = (String) llistaMenorTemps.get(1);
		// Ho mostre en la vista del Joc
		vistaJoc.setLblMillor(usuari + ": " + tempsFormatejat);

		int temps2 = (int) llistaMenorTemps.get(2);
		menorTempsSegons4x4 = temps2;
		int minuts2 = temps / 60;
		int seg2 = temps2 % 60;
		String tempsFormatejat2 = String.format("%02d:%02d", minuts2, seg2);
		String usuari2 = (String) llistaMenorTemps.get(3);
		vistaJoc.setLblMillor2(usuari2 + ": " + tempsFormatejat2);
	}

	/**
	 * Pare el contador del joc.
	 */
	private void pararContador() {
		// Guarde els segons realitzats en una variable
		segonsActuals = segons;
		timer.stop();
		// Pose els segons a 0 altra vegada
		segons = 0;
		vistaJoc.setBtnGuardarEnabled(true);
	}

	/**
	 * Comproba si el joc ha acabat o no.
	 */
	private void comprobarJocAcaba() {
		boolean image1 = vistaJoc.getBtnImage1().isEnabled();
		boolean image2 = vistaJoc.getBtnImage2().isEnabled();
		boolean image3 = vistaJoc.getBtnImage3().isEnabled();
		boolean image4 = vistaJoc.getBtnImage4().isEnabled();
		boolean image5 = vistaJoc.getBtnImage5().isEnabled();
		boolean image6 = vistaJoc.getBtnImage6().isEnabled();
		boolean image7 = vistaJoc.getBtnImage7().isEnabled();
		boolean image8 = vistaJoc.getBtnImage8().isEnabled();
		boolean image9 = vistaJoc.getBtnImage9().isEnabled();
		boolean image10 = vistaJoc.getBtnImage10().isEnabled();
		boolean image11 = vistaJoc.getBtnImage11().isEnabled();
		boolean image12 = vistaJoc.getBtnImage12().isEnabled();
		boolean image13 = vistaJoc.getBtnImage13().isEnabled();
		boolean image14 = vistaJoc.getBtnImage14().isEnabled();
		boolean image15 = vistaJoc.getBtnImage15().isEnabled();
		boolean image16 = vistaJoc.getBtnImage16().isEnabled();

		/**
		 * Comproba si es de dificultat 16 o 8 i si totes les imatges estan
		 * deshabilitats
		 */
		if (tipo == 2 && image1 == false && image2 == false && image3 == false && image4 == false && image5 == false
				&& image6 == false && image7 == false && image8 == false && image9 == false && image10 == false
				&& image11 == false && image12 == false && image13 == false && image14 == false && image15 == false
				&& image16 == false) {
			/**
			 * Para el contador del temps
			 */
			pararContador();
			JOptionPane.showMessageDialog(null, "HAS COMPLETAT EL JOC!", "Message", JOptionPane.INFORMATION_MESSAGE);
			LocalDateTime fechaHoraActual = LocalDateTime.now();
			tempsAcabat = fechaHoraActual.format(formatDataHora);
			/**
			 * Si els segons en el moment que ha acabat es menor al menor temps de la base
			 * de dades d'eixa dificultat torna un missatge d'enhorabona
			 */
			if (segonsActuals < menorTempsSegons4x4) {
				JOptionPane.showMessageDialog(null,
						"ENHORABONA!\nQUE RÀPID HAS SIGUT!!\nHAS CONSEGUIT EL MILLOR TEMPS DE TOTS EN DIFICULTAD 4 x 4!\n\n"
								+ "SI VOLS QUE ES MOSTRE EL TEMPS CONSEGUIT PER A TOT EL MÓN,\nTENGS QUE GUARDAR LA PARTIDA.",
						"Message", JOptionPane.INFORMATION_MESSAGE);
			}

		} else if (tipo == 1 && image2 == false && image3 == false && image6 == false && image7 == false
				&& image10 == false && image11 == false && image14 == false && image15 == false) {
			pararContador();
			JOptionPane.showMessageDialog(null, "HAS COMPLETAT EL JOC!", "Message", JOptionPane.INFORMATION_MESSAGE);
			LocalDateTime fechaHoraActual = LocalDateTime.now();
			tempsAcabat = fechaHoraActual.format(formatDataHora);
			if (segonsActuals < menorTempsSegons2x4) {
				JOptionPane.showMessageDialog(null,
						"ENHORABONA!\nQUE RÀPID HAS SIGUT!!\nHAS CONSEGUIT EL MILLOR TEMPS DE TOTS EN DIFICULTAD 2 x 4!\n\n"
								+ "SI VOLS QUE ES MOSTRE EL TEMPS CONSEGUIT PER A TOT EL MÓN,\nTENGS QUE GUARDAR LA PARTIDA.",
						"Message", JOptionPane.INFORMATION_MESSAGE);
			}

		}
	}

	/**
	 * Compare les imatges. El primer botó amb la segona
	 *
	 * @param imatge1      Imatge 1 clicada
	 * @param imatge2      Imatge 2 clicada
	 * @param boto1        Boto 1 clicada
	 * @param boto2        Boto 2 clicada
	 * @param imatgeString String amb la imatge en base 64 de la imatge 2
	 */
	private void compararImatges(ImageIcon imatge1, ImageIcon imatge2, JButton boto1, JButton boto2,
			String imatgeString) {
		// Controla que no puguem clicar dues vegades el mateix boto
		if (boto1 != boto2) {
			// Si la imatge2 en base 64 es igual a la imatge 1 en base 64
			if (imatgeString == primerBotoString) {
				boto2.setIcon(imatge2);
				primerBotoClicat = false;
				primerBoto = null;
				imatgePrimerBoto = null;
				primerBotoString = "";
				boto1.setEnabled(false);
				boto1.setDisabledIcon(imatge1);
				boto2.setEnabled(false);
				boto2.setDisabledIcon(imatge2);
				comprobarJocAcaba();
			} else {
				boto2.setIcon(imatge2);
				// Mostra el boto per 300 milisegons i l'oculta si no son iguals
				Timer timer = new Timer(300, (e) -> {
					boto1.setIcon(null);
					boto2.setIcon(null);
					primerBotoClicat = false;
					primerBoto = null;
					imatgePrimerBoto = null;
					primerBotoString = "";
				});

				timer.setRepeats(false);
				timer.start();
			}
		}
	}

	/**
	 * Mostra els resultats en la classificacio
	 */
	private void mostrarResultats() {
		ArrayList<String[]> llista2x4 = model.mostrarResultats2x4();
		ArrayList<String[]> llista4x4 = model.mostrarResultats4x4();
		vistaClassificacio.mostrarResultats2x4(llista2x4);
		vistaClassificacio.mostrarResultats4x4(llista4x4);
	}

	/**
	 * Métode per als events de la classificacio.
	 */
	public void initEventHandlerClassificacio() {
		mostrarResultats();
		vistaClassificacio.setBtnTornar(new ActionListener() {
			/**
			 * Event del botó per a tornar a la vista Joc
			 */
			public void actionPerformed(ActionEvent e) {
				vistaClassificacio.dispose();
				vistaJoc = new VistaJoc();
				initEventHandlerJoc();
			}
		});
	}
	

	/**
	 * Habilita tots els Botons en el moment que començem el joc
	 */
	private void setBotonsEnabled() {
		vistaJoc.setBtnImage1Enabled(true);
		;
		vistaJoc.setBtnImage2Enabled(true);
		;
		vistaJoc.setBtnImage3Enabled(true);
		;
		vistaJoc.setBtnImage4Enabled(true);
		;
		vistaJoc.setBtnImage5Enabled(true);
		;
		vistaJoc.setBtnImage6Enabled(true);
		;
		vistaJoc.setBtnImage7Enabled(true);
		;
		vistaJoc.setBtnImage8Enabled(true);
		;
		vistaJoc.setBtnImage9Enabled(true);
		;
		vistaJoc.setBtnImage10Enabled(true);
		;
		vistaJoc.setBtnImage11Enabled(true);
		;
		vistaJoc.setBtnImage12Enabled(true);
		;
		vistaJoc.setBtnImage13Enabled(true);
		;
		vistaJoc.setBtnImage14Enabled(true);
		;
		vistaJoc.setBtnImage15Enabled(true);
		;
		vistaJoc.setBtnImage16Enabled(true);
		;
	}


	/**
	 * Métode per als events del Joc.
	 */
	public void initEventHandlerJoc() {
		/**
		 * Al carregar el joc modifica els label amb els menor temps
		 */
		menorTemps();

		vistaJoc.setBtnJugar(new ActionListener() {
			/**
			 * Event per que obrir una vista per elegir la graella de la partida
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				// En cas que ja havies jugat abans et pregunta si vols jugar altra volta
				if (vistaJoc.getBtnGuardar().isEnabled()) {
					int response = JOptionPane.showConfirmDialog(null, "¿Vols jugar altra partida?",
							"Confirmar consulta", JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION) {
						vistaJoc.setEnabled(false);
						vistaGraella = new VistaElegirGraella();
						initEventHandlerGraella();
					}
				} else {
					vistaJoc.setEnabled(false);
					vistaGraella = new VistaElegirGraella();
					initEventHandlerGraella();
				}

			}
		});

		/**
		 * Comença la partida habilitant els botons e iniciant el contador
		 */
		vistaJoc.setBtnComenzar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBotonsEnabled();
				iniciarContador();
				vistaJoc.setBtnComenzarEnabled(false);
			}

		});

		/**
		 * Guarda la partida jugada
		 */
		vistaJoc.setBtnGuardar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dificultat = 0;
				if (tipo == 1) {
					dificultat = 8;
				} else if (tipo == 2) {
					dificultat = 16;
				}
				model.guardarPartida(usuariLoguejat, dificultat, tempsAcabat, segonsActuals);

				menorTemps();
			}
		});

		vistaJoc.setBtnMostrar(new ActionListener() {
			/**
			 * Mostra la vista Classificacio
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				vistaJoc.dispose();
				vistaClassificacio = new VistaClassificacio();
				initEventHandlerClassificacio();
			}
		});

		vistaJoc.setBtnImage1(new ActionListener() {
			/**
			 * Event per al botó de la Imatge 1
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				if (tipo == 2) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage1(llistaImatges.get(0));
						primerBoto = vistaJoc.getBtnImage1();
						imatgePrimerBoto = llistaImatges.get(0);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(0);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(0), primerBoto, vistaJoc.getBtnImage1(),
								llistaImatgesBase64.get(0));
					}
				} else {
					vistaJoc.setIconImage1(null);
				}
			}
		});

		vistaJoc.setBtnImage2(new ActionListener() {
			/**
			 * Event per al botó de la Imatge 2
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				if (tipo == 2) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage2(llistaImatges.get(1));
						primerBoto = vistaJoc.getBtnImage2();
						imatgePrimerBoto = llistaImatges.get(1);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(1);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(1), primerBoto, vistaJoc.getBtnImage2(),
								llistaImatgesBase64.get(1));
					}
				} else if (tipo == 1) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage2(llistaImatges.get(0));
						primerBoto = vistaJoc.getBtnImage2();
						imatgePrimerBoto = llistaImatges.get(0);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(0);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(0), primerBoto, vistaJoc.getBtnImage2(),
								llistaImatgesBase64.get(0));
					}
				} else {
					vistaJoc.setIconImage2(null);
				}
			}
		});

		vistaJoc.setBtnImage3(new ActionListener() {
			/**
			 * Event per al botó de la Imatge 3
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				if (tipo == 2) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage3(llistaImatges.get(2));
						primerBoto = vistaJoc.getBtnImage3();
						imatgePrimerBoto = llistaImatges.get(2);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(2);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(2), primerBoto, vistaJoc.getBtnImage3(),
								llistaImatgesBase64.get(2));
					}
				} else if (tipo == 1) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage3(llistaImatges.get(1));
						primerBoto = vistaJoc.getBtnImage3();
						imatgePrimerBoto = llistaImatges.get(1);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(1);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(1), primerBoto, vistaJoc.getBtnImage3(),
								llistaImatgesBase64.get(1));
					}
				} else {
					vistaJoc.setIconImage3(null);
				}
			}
		});

		vistaJoc.setBtnImage4(new ActionListener() {
			/**
			 * Event per al botó de la Imatge 4
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				if (tipo == 2) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage4(llistaImatges.get(3));
						primerBoto = vistaJoc.getBtnImage4();
						imatgePrimerBoto = llistaImatges.get(3);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(3);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(3), primerBoto, vistaJoc.getBtnImage4(),
								llistaImatgesBase64.get(3));
					}
				} else {
					vistaJoc.setIconImage4(null);
				}
			}
		});

		vistaJoc.setBtnImage5(new ActionListener() {
			/**
			 * Event per al botó de la Imatge 5
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				if (tipo == 2) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage5(llistaImatges.get(4));
						primerBoto = vistaJoc.getBtnImage5();
						imatgePrimerBoto = llistaImatges.get(4);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(4);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(4), primerBoto, vistaJoc.getBtnImage5(),
								llistaImatgesBase64.get(4));
					}
				} else {
					vistaJoc.setIconImage5(null);
				}
			}
		});

		vistaJoc.setBtnImage6(new ActionListener() {
			/**
			 * Event per al botó de la Imatge 6
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				if (tipo == 2) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage6(llistaImatges.get(5));
						primerBoto = vistaJoc.getBtnImage6();
						imatgePrimerBoto = llistaImatges.get(5);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(5);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(5), primerBoto, vistaJoc.getBtnImage6(),
								llistaImatgesBase64.get(5));
					}
				} else if (tipo == 1) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage6(llistaImatges.get(2));
						primerBoto = vistaJoc.getBtnImage6();
						imatgePrimerBoto = llistaImatges.get(2);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(2);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(2), primerBoto, vistaJoc.getBtnImage6(),
								llistaImatgesBase64.get(2));
					}
				} else {
					vistaJoc.setIconImage6(null);
				}
			}
		});

		vistaJoc.setBtnImage7(new ActionListener() {
			/**
			 * Event per al botó de la Imatge 7
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				if (tipo == 2) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage7(llistaImatges.get(6));
						primerBoto = vistaJoc.getBtnImage7();
						imatgePrimerBoto = llistaImatges.get(6);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(6);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(6), primerBoto, vistaJoc.getBtnImage7(),
								llistaImatgesBase64.get(6));
					}
				} else if (tipo == 1) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage7(llistaImatges.get(3));
						primerBoto = vistaJoc.getBtnImage7();
						imatgePrimerBoto = llistaImatges.get(3);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(3);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(3), primerBoto, vistaJoc.getBtnImage7(),
								llistaImatgesBase64.get(3));
					}
				} else {
					vistaJoc.setIconImage7(null);
				}
			}
		});

		vistaJoc.setBtnImage8(new ActionListener() {
			/**
			 * Event per al botó de la Imatge 8
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				if (tipo == 2) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage8(llistaImatges.get(7));
						primerBoto = vistaJoc.getBtnImage8();
						imatgePrimerBoto = llistaImatges.get(7);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(7);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(7), primerBoto, vistaJoc.getBtnImage8(),
								llistaImatgesBase64.get(7));
					}
				} else {
					vistaJoc.setIconImage8(null);
				}
			}
		});

		vistaJoc.setBtnImage9(new ActionListener() {
			/**
			 * Event per al botó de la Imatge 9
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				if (tipo == 2) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage9(llistaImatges.get(8));
						primerBoto = vistaJoc.getBtnImage9();
						imatgePrimerBoto = llistaImatges.get(8);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(8);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(8), primerBoto, vistaJoc.getBtnImage9(),
								llistaImatgesBase64.get(8));
					}
				} else {
					vistaJoc.setIconImage9(null);
				}
			}
		});

		vistaJoc.setBtnImage10(new ActionListener() {
			/**
			 * Event per al botó de la Imatge 10
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				if (tipo == 2) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage10(llistaImatges.get(9));
						primerBoto = vistaJoc.getBtnImage10();
						imatgePrimerBoto = llistaImatges.get(9);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(9);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(9), primerBoto, vistaJoc.getBtnImage10(),
								llistaImatgesBase64.get(9));
					}
				} else if (tipo == 1) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage10(llistaImatges.get(4));
						primerBoto = vistaJoc.getBtnImage10();
						imatgePrimerBoto = llistaImatges.get(4);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(4);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(4), primerBoto, vistaJoc.getBtnImage10(),
								llistaImatgesBase64.get(4));
					}
				} else {
					vistaJoc.setIconImage10(null);
				}
			}
		});

		vistaJoc.setBtnImage11(new ActionListener() {
			/**
			 * Event per al botó de la Imatge 11
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				if (tipo == 2) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage11(llistaImatges.get(10));
						primerBoto = vistaJoc.getBtnImage11();
						imatgePrimerBoto = llistaImatges.get(10);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(10);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(10), primerBoto, vistaJoc.getBtnImage11(),
								llistaImatgesBase64.get(10));
					}
				} else if (tipo == 1) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage11(llistaImatges.get(5));
						primerBoto = vistaJoc.getBtnImage11();
						imatgePrimerBoto = llistaImatges.get(5);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(5);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(5), primerBoto, vistaJoc.getBtnImage11(),
								llistaImatgesBase64.get(5));
					}
				} else {
					vistaJoc.setIconImage11(null);
				}
			}
		});

		vistaJoc.setBtnImage12(new ActionListener() {
			/**
			 * Event per al botó de la Imatge 12
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				if (tipo == 2) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage12(llistaImatges.get(11));
						primerBoto = vistaJoc.getBtnImage12();
						imatgePrimerBoto = llistaImatges.get(11);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(11);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(11), primerBoto, vistaJoc.getBtnImage12(),
								llistaImatgesBase64.get(11));
					}
				} else {
					vistaJoc.setIconImage12(null);
				}
			}
		});

		vistaJoc.setBtnImage13(new ActionListener() {
			/**
			 * Event per al botó de la Imatge 13
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				if (tipo == 2) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage13(llistaImatges.get(12));
						primerBoto = vistaJoc.getBtnImage13();
						imatgePrimerBoto = llistaImatges.get(12);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(12);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(12), primerBoto, vistaJoc.getBtnImage13(),
								llistaImatgesBase64.get(12));
					}
				} else {
					vistaJoc.setIconImage13(null);
				}
			}
		});
		vistaJoc.setBtnImage14(new ActionListener() {
			/**
			 * Event per al botó de la Imatge 14
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				if (tipo == 2) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage14(llistaImatges.get(13));
						primerBoto = vistaJoc.getBtnImage14();
						imatgePrimerBoto = llistaImatges.get(13);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(13);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(13), primerBoto, vistaJoc.getBtnImage14(),
								llistaImatgesBase64.get(13));
					}
				} else if (tipo == 1) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage14(llistaImatges.get(6));
						primerBoto = vistaJoc.getBtnImage14();
						imatgePrimerBoto = llistaImatges.get(6);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(6);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(6), primerBoto, vistaJoc.getBtnImage14(),
								llistaImatgesBase64.get(6));
					}
				} else {
					vistaJoc.setIconImage14(null);
				}
			}
		});
		vistaJoc.setBtnImage15(new ActionListener() {
			/**
			 * Event per al botó de la Imatge 15
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				if (tipo == 2) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage15(llistaImatges.get(14));
						primerBoto = vistaJoc.getBtnImage15();
						imatgePrimerBoto = llistaImatges.get(14);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(14);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(14), primerBoto, vistaJoc.getBtnImage15(),
								llistaImatgesBase64.get(14));
					}
				} else if (tipo == 1) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage15(llistaImatges.get(7));
						primerBoto = vistaJoc.getBtnImage15();
						imatgePrimerBoto = llistaImatges.get(7);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(7);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(7), primerBoto, vistaJoc.getBtnImage15(),
								llistaImatgesBase64.get(7));
					}
				} else {
					vistaJoc.setIconImage15(null);
				}
			}
		});
		vistaJoc.setBtnImage16(new ActionListener() {
			/**
			 * Event per al botó de la Imatge 16
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				if (tipo == 2) {
					if (!primerBotoClicat) {
						vistaJoc.setIconImage16(llistaImatges.get(15));
						primerBoto = vistaJoc.getBtnImage16();
						imatgePrimerBoto = llistaImatges.get(15);
						primerBotoClicat = true;
						primerBotoString = llistaImatgesBase64.get(15);
					} else {
						compararImatges(imatgePrimerBoto, llistaImatges.get(15), primerBoto, vistaJoc.getBtnImage16(),
								llistaImatgesBase64.get(15));
					}
				} else {
					vistaJoc.setIconImage16(null);
				}
			}
		});
	}

	/**
	 * Deshabilitem i posem els icons en blanc
	 */
	private void setIconsNull() {
		vistaJoc.setIconImage1(null);
		vistaJoc.setIconImage2(null);
		vistaJoc.setIconImage3(null);
		vistaJoc.setIconImage4(null);
		vistaJoc.setIconImage5(null);
		vistaJoc.setIconImage6(null);
		vistaJoc.setIconImage7(null);
		vistaJoc.setIconImage8(null);
		vistaJoc.setIconImage9(null);
		vistaJoc.setIconImage10(null);
		vistaJoc.setIconImage11(null);
		vistaJoc.setIconImage12(null);
		vistaJoc.setIconImage13(null);
		vistaJoc.setIconImage14(null);
		vistaJoc.setIconImage15(null);
		vistaJoc.setIconImage16(null);

		vistaJoc.setBtnImage1Enabled(false);
		vistaJoc.setBtnImage2Enabled(false);
		vistaJoc.setBtnImage3Enabled(false);
		vistaJoc.setBtnImage4Enabled(false);
		vistaJoc.setBtnImage5Enabled(false);
		vistaJoc.setBtnImage6Enabled(false);
		vistaJoc.setBtnImage7Enabled(false);
		vistaJoc.setBtnImage8Enabled(false);
		vistaJoc.setBtnImage9Enabled(false);
		vistaJoc.setBtnImage10Enabled(false);
		vistaJoc.setBtnImage11Enabled(false);
		vistaJoc.setBtnImage12Enabled(false);
		vistaJoc.setBtnImage13Enabled(false);
		vistaJoc.setBtnImage14Enabled(false);
		vistaJoc.setBtnImage15Enabled(false);
		vistaJoc.setBtnImage16Enabled(false);
	}

	/**
	 * Posem els icons de 2x4 Visibles
	 */
	private void setIcons2x4() {
		vistaJoc.setBtnImage1Visible(false);
		vistaJoc.setBtnImage2Visible(true);
		vistaJoc.setBtnImage3Visible(true);
		vistaJoc.setBtnImage4Visible(false);
		vistaJoc.setBtnImage5Visible(false);
		vistaJoc.setBtnImage6Visible(true);
		vistaJoc.setBtnImage7Visible(true);
		vistaJoc.setBtnImage8Visible(false);
		vistaJoc.setBtnImage9Visible(false);
		vistaJoc.setBtnImage10Visible(true);
		vistaJoc.setBtnImage11Visible(true);
		vistaJoc.setBtnImage12Visible(false);
		vistaJoc.setBtnImage13Visible(false);
		vistaJoc.setBtnImage14Visible(true);
		vistaJoc.setBtnImage15Visible(true);
		vistaJoc.setBtnImage16Visible(false);
	}

	/**
	 * Posem els icons de 4x4 Visibles
	 */
	private void setIcons4x4() {
		vistaJoc.setBtnImage1Visible(true);
		vistaJoc.setBtnImage2Visible(true);
		vistaJoc.setBtnImage3Visible(true);
		vistaJoc.setBtnImage4Visible(true);
		vistaJoc.setBtnImage5Visible(true);
		vistaJoc.setBtnImage6Visible(true);
		vistaJoc.setBtnImage7Visible(true);
		vistaJoc.setBtnImage8Visible(true);
		vistaJoc.setBtnImage9Visible(true);
		vistaJoc.setBtnImage10Visible(true);
		vistaJoc.setBtnImage11Visible(true);
		vistaJoc.setBtnImage12Visible(true);
		vistaJoc.setBtnImage13Visible(true);
		vistaJoc.setBtnImage14Visible(true);
		vistaJoc.setBtnImage15Visible(true);
		vistaJoc.setBtnImage16Visible(true);
	}

	/**
	 * Métode per als events de la Graella
	 */
	public void initEventHandlerGraella() {

		vistaGraella.setBtn2x4(new ActionListener() {
			/**
			 * Event per al botó de 2x4
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				setIconsNull();
				tipo = 1;
				segons = 0;
				vistaGraella.dispose();
				vistaJoc.setEnabled(true);
				vistaJoc.setBtnComenzarEnabled(true);
				vistaJoc.setLblTipo("Tipo: 2 x 4");
				model = new Model();

				llistaImatgesBase64 = model.obtenirImatgesBase64(tipo);
				llistaImatges = model.obtenirImatgesIcons(llistaImatgesBase64);
				setIcons2x4();
				vistaJoc.setLblTemps("00:00");
				if (vistaJoc.getBtnGuardar().isEnabled()) {
					vistaJoc.setBtnGuardarEnabled(false);
				}
			}
		});

		vistaGraella.setBtn4x4(new ActionListener() {
			/**
			 * Event per al botó de 4x4
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				setIconsNull();
				tipo = 2;
				segons = 0;
				vistaGraella.dispose();
				vistaJoc.setEnabled(true);
				vistaJoc.setBtnComenzarEnabled(true);
				vistaJoc.setLblTipo("Tipo: 4 x 4");
				model = new Model();

				llistaImatgesBase64 = model.obtenirImatgesBase64(tipo);
				llistaImatges = model.obtenirImatgesIcons(llistaImatgesBase64);
				setIcons4x4();
				vistaJoc.setLblTemps("00:00");
				if (vistaJoc.getBtnGuardar().isEnabled()) {
					vistaJoc.setBtnGuardarEnabled(false);
				}
			}
		});

		vistaGraella.setBtnCancelar(new ActionListener() {
			/**
			 * Event per al botó Cancelar
			 * 
			 * @param e Listener
			 */
			public void actionPerformed(ActionEvent e) {
				tipo = 0;
				vistaGraella.dispose();
				vistaJoc.setEnabled(true);
			}
		});
	}
}
