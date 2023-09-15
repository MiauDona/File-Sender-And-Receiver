package main.miau.dona;

import javax.swing.*;
import java.io.*;
import java.net.Proxy;
import java.net.Socket;

public class Client {
        public static void main() {
            //Elegir direccion IP
            String IP = JOptionPane.showInputDialog("Donde te quieres conectar? (IP)");
            try (Socket clientSocket = new Socket(IP, 38528); // Intenta conectarse a la IP
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Crea el input
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);) {  // Crea el output

               BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in)); // Envia lo que el usuario escribe en la consola
               String message;
               while ((message = userInput.readLine()) != null) {
                   out.println(message); // Printea el mensaje del usuario
               }



            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Esa direccion IP no existe o no está conectada", "Error -1", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
