package evaluacion3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadHolaServidorConcurrente extends Thread{
	// clase que controla los threads
	private Socket socket = null;
	private int numeroCliente;

	// constructor
    public ThreadHolaServidorConcurrente(Socket socket, int numeroCliente) {
      super("ThreadHolaServidorConcurrente");
      this.socket = socket;
      this.numeroCliente = numeroCliente;
    }
    
    // run
	public void run() {
		 try {
			//Scanner scanner = new Scanner(socket.getInputStream());
      PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

      // mando el mensaje al cliente
      printWriter.println("Hola Cliente "+numeroCliente);
      
      socket.close();
            
		 }catch (IOException ex) {
			 Logger.getLogger(HolaServidorConcurrente.class.getName()).log(Level.SEVERE, null, ex);
	   } finally {

        if (socket != null) {
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(HolaServidorConcurrente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

      }
		
	}
}
