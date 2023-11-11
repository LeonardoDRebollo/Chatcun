package chatcun;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

public class HiloUsuario2 extends Thread {
    private JComboBox<String> comboBox;
    private DefaultComboBoxModel<String> comboBoxModel;

    public HiloUsuario2(DefaultComboBoxModel comboBoxModel) {
        this.comboBoxModel = comboBoxModel;
    }

    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(1213);
            while (true) {
                Socket conecta = servidor.accept();
                DataInputStream recibe = new DataInputStream(conecta.getInputStream());
                String texto = recibe.readUTF();
                comboBoxModel.addElement(texto);
            }
        } catch (IOException e) {
            // Manejar la excepción apropiadamente
            e.printStackTrace();
        }
    }
}
