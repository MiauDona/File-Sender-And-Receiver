package miau.dona;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    @Override
    public void run() {
        // Abre el puerto en el servidor
        try (ServerSocket serverSocket = new ServerSocket(38528)) {

            // Deja en escucha el puerto y da la opcion de aceptar o rechazar la peticion
            while (true) {
                Socket clientSocket = serverSocket.accept();
                int result = JOptionPane.showOptionDialog(null, "Quieres que el cliente" + clientSocket.getInetAddress().getHostAddress() + "se conecte?", "Esperando conexion...", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                // Si acepta se queda esperando a que le lleguen archivos
                if (result == JOptionPane.OK_OPTION) {
                    JOptionPane.showMessageDialog(null, "El cliente se ha conectado desde: " + clientSocket.getInetAddress().getHostAddress(), "Conexion realizada", JOptionPane.PLAIN_MESSAGE);
                    // TODO: Sistema de recepcion de archivos


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
