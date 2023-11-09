/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package chatcun;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author djclu
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }
    
    
    //LOGIN METHOD
    
    String Username, Password; 
    private int intentosFallidos = 0; // Variable para llevar el control de intentos fallidos
    
    boolean logic(String user, String pass) {
           // Utiliza una ruta relativa desde la ubicación de tu proyecto.
           String directorioActual = System.getProperty("user.dir");
            String rutaArchivo = "src/chatcun/Usuarios.txt";
           
            File file = new File(directorioActual, rutaArchivo);
        try {
            //Acceder al archivo
            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            String line;
            while ((line = raf.readLine()) != null) {
                if (line.startsWith("Username:")) {
                    // Verificar si la línea tiene suficientes caracteres
                    if (line.length() > 9) {
                        Username = line.substring(9).trim();
                    } else {
                        Username = "";
                    }

                    // Leer la siguiente línea
                    line = raf.readLine();

                    // Verificar si la siguiente línea tiene suficientes caracteres
                    if (line != null && line.length() > 9) {
                        Password = line.substring(9).trim();
                    } else {
                        Password = "";
                    }
                    System.out.println("Username from file: " + Username);
                    System.out.println("Password from file: " + Password);
                    System.out.println("User input: " + user);
                    System.out.println("Password input: " + pass);

                    if (user.trim().equalsIgnoreCase(Username) && pass.equals(Password)) {
                        return true; // Credenciales correctas
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false; // Credenciales incorrectas
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnIngresar = new javax.swing.JButton();
        TxtUsuario = new javax.swing.JTextField();
        TxtPass = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BtnIngresar.setText("Ingresar");
        BtnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIngresarActionPerformed(evt);
            }
        });

        TxtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPassActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Chatcun");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(BtnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(TxtPass, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                        .addComponent(TxtUsuario)))
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(TxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TxtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(BtnIngresar)
                .addGap(90, 90, 90))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIngresarActionPerformed
        // TODO add your handling code here:
        
           String username = TxtUsuario.getText();
        String password = TxtPass.getText();

        // Verifica que los campos no estén vacíos
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete ambos campos.");
            return; // Sale del método si los campos están vacíos
        }

        // Continúa con la lógica de inicio de sesión
        boolean accesoCorrecto = logic(username, password);

        if (accesoCorrecto) {
             String nombreDeUsuario = TxtUsuario.getText();
            // Crea un temporizador para cerrar el JOptionPane después de 2 segundos (por ejemplo)
            Timer timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Cierra el JOptionPane después de 2 segundos
                    JOptionPane.getRootFrame().dispose();
                }
            });

            timer.setRepeats(false); // Evita que el temporizador se repita
            timer.start();
 
            JOptionPane.showMessageDialog(null, "Acceso correcto");  
             Socket conecta;
			try {
				conecta = new Socket("192.168.1.67",1211);
				DataOutputStream manda = new DataOutputStream(conecta.getOutputStream());
				manda.writeUTF(TxtUsuario.getText());
				manda.close();
			} catch (UnknownHostException e0) {
				// TODO Auto-generated catch block
				e0.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            VUsuario h = new VUsuario();   //VUsuario h = new VUsuario(txtUsername.getText());
            h.setVisible(true);
           
            // Cierra el formulario de inicio de sesión solo si el acceso es correcto
            this.dispose();
        } else {
            intentosFallidos++;
            if (intentosFallidos > 3) {
                int segundos = 1; // Establece la cantidad de segundos antes de redirigir al usuario
                JOptionPane.showMessageDialog(null, "Demasiados intentos fallidos. Volviendo a la página de registro en " + segundos + " segundos.");

                // Bloquea los campos de texto
                TxtUsuario.setEnabled(false);
                TxtPass.setEnabled(false);

                // Crea un temporizador (Timer) para redirigir al usuario después de ciertos segundos
                Timer timer = new Timer(segundos * 1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Registro h = new Registro();
                        //h.setVisible(true);
                        Login.this.dispose();
                    }
                });
                timer.setRepeats(false); // Evita que el temporizador se repita
                timer.start();
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña/usuario incorrectos");
            }
        }
        
        
        
        
        
        
    }//GEN-LAST:event_BtnIngresarActionPerformed

    private void TxtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPassActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnIngresar;
    private javax.swing.JPasswordField TxtPass;
    private javax.swing.JTextField TxtUsuario;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
