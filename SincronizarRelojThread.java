package evaluacion3;

import java.util.Date;

public class SincronizarRelojThread  extends Thread {
	
	public void run() {
		while (true) {
			System.out.println(new Date());
			try { 
				Thread.sleep(1000);  
			} catch (InterruptedException x) { 
				
			}
		}
	}

}
