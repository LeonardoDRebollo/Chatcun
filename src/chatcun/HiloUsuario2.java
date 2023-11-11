package chatcun;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
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
                eliminarDuplicados();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void eliminarDuplicados() {
        Set<String> elementosUnicos = new HashSet<>();
        for (int i = 0; i < comboBoxModel.getSize(); i++) {
            elementosUnicos.add(comboBoxModel.getElementAt(i));
        }
        comboBoxModel.removeAllElements();
        for (String elemento : elementosUnicos) {
            comboBoxModel.addElement(elemento);
        }
    }
}

