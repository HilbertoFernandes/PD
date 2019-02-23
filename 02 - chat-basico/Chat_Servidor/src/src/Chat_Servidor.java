package src;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.swing.JOptionPane;

public class Chat_Servidor {

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(6500);
            System.out.println(servidor.getClass().getSimpleName());
            System.out.println("Aguardando uma conexão...");
            Socket socket = servidor.accept();

            System.out.println("Conexão estabelecida com " + socket.getInetAddress() + " porta : " + socket.getPort());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            int contador = 0;
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String nome = "";

            while (true) {

                String msg = in.readUTF();
                JOptionPane.showMessageDialog(null, msg);
                System.out.println(nome + "Disse: " + msg);

                if (msg.toUpperCase().equals("XAU")) {
                    JOptionPane.showMessageDialog(null, "Cliente saiu!");
                    servidor.close();
                    socket.close();
                } else {

                    if (contador == 0) {
                        nome = in.readUTF();
                        out.writeUTF(JOptionPane.showInputDialog("Seu nome"));
                        contador++;
                    } else {

                        out.writeUTF(JOptionPane.showInputDialog("Digite a mensagem para cliente"));
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Chat_Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
