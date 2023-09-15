package miau.dona;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Client implements Runnable {
        @Override
        public void run () {
        //Elegir direccion IP
        String IP = JOptionPane.showInputDialog("Donde te quieres conectar? (IP)");

        // Intenta conectarse a la IP
        try (Socket clientSocket = new Socket(IP, 38528)) {

            // Define las variables que se van a usar mas adelante
            int answer = 0;
            String directorio;

            // Mientras que no se confirme el directorio no empieza el hilo de cliente
            while (answer == JOptionPane.OK_OPTION) {
                directorio = JOptionPane.showInputDialog("Escribe la ruta del archivo que quieras enviar");

                // Comprueba que exista el directorio
                if (new File(directorio).isFile()) {
                    JOptionPane.showMessageDialog(null, "Existe");
                    answer = JOptionPane.showOptionDialog(null, "Quieres enviar seguro '" + directorio + "'?", "Confirmacion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                    // Si existe empezará a convertir el archivo
                    if (answer == JOptionPane.OK_OPTION) {
                        OutputStream outputStream = clientSocket.getOutputStream();
                        File file = new File(directorio);

                        // Empieza el stream del archivo
                        try (FileInputStream fileInputStream = new FileInputStream(file)) {

                            // Convierte el archivo en bytes y envia y recibe archivos
                            byte[] buffer = new byte[(int) fileInputStream.readAllBytes().length];
                            fileInputStream.read(buffer);
                            fileInputStream.close();

                            outputStream.write(buffer);
                            outputStream.flush();

                            outputStream.close();

                            // Si da error al enviar el archivo pone un mensaje y se acaba el programa
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(null, "Error al enviar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }

                    // Manda un mensaje de que el archivo no existe y vuelve a empezar el bucle
                } else {
                    JOptionPane.showMessageDialog(null, "No existe");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Esa direccion IP no existe o no está conectada", "Error -1", JOptionPane.ERROR_MESSAGE);
        }
    }
}
