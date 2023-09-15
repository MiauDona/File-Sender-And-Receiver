package miau.dona;

import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        // Inicia el hilo del servidor
        Thread server = new Thread(new Server());
        server.start();

        // Inicia el hilo del cliente
        Thread client = new Thread(new Client());
        client.start();
    }


}
