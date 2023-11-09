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

/**
 *
 * @author djclu
 */
public class HiloMensajes extends Thread{
    
    
    
public void run(){
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
                        System.out.println(texto);
                        String[] partes = texto.split(",");
                        try {
				Socket conecta2 = new Socket(partes[1],1212);
				DataOutputStream manda = new DataOutputStream(conecta2.getOutputStream());
				manda.writeUTF(partes[0]);
				manda.close();
			} catch (UnknownHostException e0) {
				// TODO Auto-generated catch block
				e0.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        	conecta.close();
	        	
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
