package evaluacion3;

public class SincronizarRelojMain {
		
	public static void main(String[] args) {
		SincronizarRelojThread srt1 = new SincronizarRelojThread();
		srt1.start();

		SincronizarRelojThread srt2 = new SincronizarRelojThread();
		srt2.start();
		}

}
