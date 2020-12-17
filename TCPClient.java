package evaluacion3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient {
	private static int puerto = 8888;
    private static String ipServidor="127.0.0.1";
    
    public static void main(String[] args) {

        Socket socket = null;
        try {
            InetAddress inetAddress = InetAddress.getByName(ipServidor);
            
            socket = new Socket(inetAddress, puerto);
            System.out.println("InetAddress: " + inetAddress);
            System.out.println("Port: " + puerto);
            
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            
            Scanner userScanner = new Scanner(System.in);
            String userInput = userScanner.nextLine();
            
            printWriter.println(userInput);
            String serverEcho = scanner.nextLine();
            System.out.println(serverEcho);
            
            scanner.close();
            userScanner.close();

        } catch (UnknownHostException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if( socket != null){
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
