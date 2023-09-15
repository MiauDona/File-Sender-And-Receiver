package main.miau.dona;

import com.sun.net.httpserver.Authenticator;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main() {
        try (ServerSocket serverSocket = new ServerSocket(38528)) { // Abre el puerto en el servidor
            while (true) { // Deja en escucha el puerto y da la opcion de aceptar o rechazar la peticion
                Socket clientSocket = serverSocket.accept();
                int result = JOptionPane.showOptionDialog(null, "Quieres que el cliente" + clientSocket.getInetAddress().getHostAddress() + "se conecte?", "Esperando conexion...", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (result == JOptionPane.OK_OPTION) {
                    JOptionPane.showMessageDialog(null, "El cliente se ha conectado desde: " + clientSocket.getInetAddress().getHostAddress(), "Conexion realizada", JOptionPane.PLAIN_MESSAGE);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Abre los streams de
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);             // comunicacion

                    String message;
                    while ((message = in.readLine()) != null) {
                        out.println("Server received: " + message); // Printea el mensaje del cliente
                    }
                        // TODO: Hay que hacer que el servidor pueda enviar mensajes

                    in.close();
                    clientSocket.close();
                } else {
                    JOptionPane.showMessageDialog(null, "Se ha rechazado la conexion", "Conexion fallida", JOptionPane.PLAIN_MESSAGE);
                    clientSocket.close();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
