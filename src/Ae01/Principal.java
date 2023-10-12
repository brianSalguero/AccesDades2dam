package Ae01;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		VistaaAE01 vista = new VistaaAE01();
		ModelAe01 model = new ModelAe01();
		ControladorAe01 controlador = new ControladorAe01(vista, model);
	}
}
