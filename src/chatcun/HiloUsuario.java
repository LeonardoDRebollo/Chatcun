/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatcun;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author djclu
 */
public class HiloUsuario extends Thread {
    private DefaultTableModel menModel;
    private JComboBox<String> comboBox;
    private DefaultComboBoxModel<String> comboBoxModel;

    public HiloUsuario(DefaultTableModel menModel,DefaultComboBoxModel comboBoxModel) {
        this.menModel = menModel;
        this.comboBoxModel = comboBoxModel;
    }
    public void run(){
        try {
			ServerSocket servidor = new ServerSocket(1212);
	        while(true) {
	        	Socket conecta = servidor.accept();
	        	DataInputStream recibe = new DataInputStream(conecta.getInputStream());
	        	String texto;
	        	texto=recibe.readUTF();
                        menModel.addRow(new Object[]{ texto});                        
	        	conecta.close();
	        	
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        try {
        ServerSocket servidor = new ServerSocket(1213);
        while (true) {
            Socket conecta = servidor.accept();
            DataInputStream recibe = new DataInputStream(conecta.getInputStream());
            String texto = recibe.readUTF();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    comboBoxModel.addElement(texto);
                }
            });

            conecta.close();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
}
