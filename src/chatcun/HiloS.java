/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatcun;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author djclu
 */
public class HiloS extends Thread {
     private DefaultTableModel UsuariosModel;

    public HiloS(DefaultTableModel UsuariosModel) {
        this.UsuariosModel = UsuariosModel;
    }
    
    
    public void run() {
		try {
			ServerSocket servidor = new ServerSocket(1212);
	        while(true) {
	        	Socket conecta = servidor.accept();
	        	String ip;
	        	InetAddress dir;
	        	dir = conecta.getInetAddress();
	        	ip = dir.getHostAddress();
	        	DataInputStream recibe = new DataInputStream(conecta.getInputStream());
	        	String texto;
	        	texto=recibe.readUTF();
                      String[] partes = texto.split(",");
                      String desconectado = "";
                      if (partes.length > 1) {
                        desconectado = partes[1].trim();
                      }
                     if (!desconectado.equals("se ha desconectado")) {
                       UsuariosModel.addRow(new Object[]{ partes[0],ip});  
                       System.out.println(partes[0].trim());
try {
    for (int fila1 = 0; fila1 < UsuariosModel.getRowCount(); fila1++) {
        Object dato1 = UsuariosModel.getValueAt(fila1, 1);
        System.out.println("Enviando a: " + dato1);
        for (int fila2 = 0; fila2 < UsuariosModel.getRowCount(); fila2++) {

             Socket conecta1 = new Socket(dato1.toString(), 1213);
         DataOutputStream manda1 = new DataOutputStream(conecta1.getOutputStream());
                Object dato2 = UsuariosModel.getValueAt(fila2, 1);
                Object dato3 = UsuariosModel.getValueAt(fila2, 0);
                String cn = dato3 +": "+ "," + dato2;
                manda1.writeUTF(cn);
                System.out.println("Enviando la ip: " + dato2 + " a: " + dato1);   
                manda1.close();
        conecta1.close();
        }
        System.out.println("Conexión cerrada para: " + dato1);
    }
} catch (IOException e) {
    e.printStackTrace();
}
                      }
                     if (desconectado.equals("se ha desconectado")) {
                   
                    for (int fila = 0; fila < UsuariosModel.getRowCount(); fila++) {
                   Object valorEnSegundaColumna = UsuariosModel.getValueAt(fila, 1);
                    if (valorEnSegundaColumna != null && valorEnSegundaColumna.toString().equals(ip)) {
                   UsuariosModel.removeRow(fila);
                   break;
                     }
                    }
                      }                     
                    
	        	conecta.close();
	        	
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
