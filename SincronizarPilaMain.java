package evaluacion3;

public class SincronizarPilaMain {

	public static void main(String[] args) {
		SincronizarPila sp = new SincronizarPila() ;
		SincronizarEscribirPila escribirPila = new SincronizarEscribirPila(sp);
		SincronizarLeerPila leerPila = new SincronizarLeerPila(sp);
		escribirPila.start();
		leerPila.start();
		}
}
