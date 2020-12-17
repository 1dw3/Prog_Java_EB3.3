package evaluacion3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.Random;

public class ThreadPoolMain {
  public static void main (String args[]){
	  System.out.println("Comienza la ejecución");
	  // creo un pool de 10 hilos
	  ExecutorService ex = Executors.newFixedThreadPool(2);
	  // defino una variable para las tareas de cada hilo
	  Tarea t;
		for(int i = 0;i<10; i++){
			// creo un nuevo hilo
			t = new Tarea(""+i);
			// lo ejecuto
		  ex.execute(t);
		 }
		// cuando han finalizado todos los hilos cierro el Executor
    ex.shutdown();
     
   }
}
class Tarea implements Runnable{
  private int sleepTime;
  private String name;
  public Tarea(String name){
   this.name = name;//le asignamos un nombre a cada tarea.
   //sleepTime = 1000;
   // cada hilo duerme un tiempo aleatorio
   Random rnd = new Random();
   // espero entre 1000 y 5000 milisegundos
   sleepTime = 1000 + (int)(rnd.nextDouble()*4000);
}
   
  public void run(){
try{
   System.out.printf("El hilo de la tarea "+this.name+" va a dormir durante %d milisegundos.\n",sleepTime);
   Thread.sleep(sleepTime);//hacemos que cada hilo duerma durante 1 segundo
}catch(InterruptedException exception){
   exception.printStackTrace();
}
System.out.println("El hilo de la tarea "+this.name+" finaliza su ejecución.");
}
}