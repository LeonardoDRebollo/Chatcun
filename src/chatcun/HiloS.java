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
			ServerSocket servidor = new ServerSocket(1211);
	        while(true) {
	        	Socket conecta = servidor.accept();
	        	String ip;
	        	InetAddress dir;
	        	dir = conecta.getInetAddress();
	        	ip = dir.getHostAddress();
	        	DataInputStream recibe = new DataInputStream(conecta.getInputStream());
	        	String texto;
	        	texto=recibe.readUTF();
                        UsuariosModel.addRow(new Object[]{ texto,ip});                        
                        int columna = 1;
			/*try {
                            int filas = ConectadoModel.getRowCount();
                           for (int fila = 0; fila < filas; fila++) {
                            Object dato = ConectadoModel.getValueAt(fila, columna);
                            Socket conecta2 = new Socket(dato.toString(),1211);
                            DataOutputStream manda2 = new DataOutputStream(conecta2.getOutputStream());
		            manda2.writeUTF(texto);
                            System.out.println("Mensaje enviado a: " + dato.toString());
                            manda2.close();
                            conecta2.close();
                      
				
				
			} catch (UnknownHostException e0) {
				// TODO Auto-generated catch block
				e0.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} }*/
	        	conecta.close();
	        	
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
