/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatcun;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

/**
 *
 * @author djclu
 */
public class HiloUsuario2 extends Thread{
        private JComboBox<String> comboBox;
private DefaultComboBoxModel<String> comboBoxModel;




   public HiloUsuario2(DefaultComboBoxModel comboBoxModel) {
        this.comboBoxModel = comboBoxModel;
    }
    public void run(){
          
        try {
        ServerSocket servidor2 = new ServerSocket(1213);
        while (true) {
            Socket conecta2 = servidor2.accept();
            DataInputStream recibe = new DataInputStream(conecta2.getInputStream());
            String texto = recibe.readUTF();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    comboBoxModel.addElement(texto);
                }
            });

            conecta2.close();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
