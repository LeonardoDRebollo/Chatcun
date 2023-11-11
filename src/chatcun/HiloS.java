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
                           int columna = 1;
			int filas = UsuariosModel.getRowCount();
                   for (int fila = 0; fila < filas; fila++) {
                     Object dato = UsuariosModel.getValueAt(fila, columna);
                          Socket conecta3 = new Socket(dato.toString(), 1213);
                          for (int fila2 = 0; fila2 < filas; fila2++) {
                                   try {
                             DataOutputStream manda2 = new DataOutputStream(conecta3.getOutputStream());
                          manda2.writeUTF(dato.toString());
                        } finally {
                             conecta3.close();
                          }
                              
                          }
                    
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
