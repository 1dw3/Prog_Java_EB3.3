package evaluacion3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServer {
    private static int puerto = 8888;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        
        System.out.println("Port: " + puerto);

        Socket socket = null;
        try {
            serverSocket = new ServerSocket(puerto);

            socket = serverSocket.accept();
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            String line = scanner.nextLine();

            System.out.println("received: " + line);
            printWriter.println("echo: " + line);
            
            scanner.close();
            
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }
}
