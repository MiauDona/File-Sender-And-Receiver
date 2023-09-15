package miau.dona;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

                    // Empieza el inputstream de lo que envia el cliente
                    InputStream inputStream = clientSocket.getInputStream();

                    //Pregunta el nombre y la ruta del archivo para crearlo
                    String fileName = JOptionPane.showInputDialog("Que nombre le quieres poner al archivo? \n Debes incluir la extension");
                    String saveDirectory = JOptionPane.showInputDialog("En que directorio quieres guardar el archivo?");
                    FileOutputStream fileOutputStream = new FileOutputStream(saveDirectory + fileName);

                    // Fijar el buffer de entrada y leer los datos del archivo en trozos de 512MB
                    byte[] buffer = new byte[536870912];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }

                    fileOutputStream.close();
                    inputStream.close();
                    clientSocket.close();

                    JOptionPane.showMessageDialog(null, "Archivo recibido y guardado exitosamente");

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
