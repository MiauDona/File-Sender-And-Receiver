package main.miau.dona;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int result = JOptionPane.showOptionDialog(null, "Quieres ser el servidor? Si cancelas serás el cliente", "Cliente o servidor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (result == JOptionPane.OK_OPTION) {
            Server.main();
        } else {
            Client.main();
        }
    }
    // No se puede ejecutar porque no encuentra el directorio main.miau.dona
    // TODO: Hacer que sea ejecutable el archivo y a ser posible compilable
}
