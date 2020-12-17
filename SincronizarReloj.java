package evaluacion3;

import java.util.Date;

public class SincronizarReloj  implements Runnable {
	
	public void run() {
		while (true) {
			System.out.println(new Date());
			try { 
				Thread.sleep(1000);  
			} catch (InterruptedException x) { 
				
			}
		}
	}
		
	public static void main(String[] args) {
		final Runnable tarea = new SincronizarReloj();
		Thread t = new Thread(tarea,"SincronizarReloj");
		t.start();

		}

}
