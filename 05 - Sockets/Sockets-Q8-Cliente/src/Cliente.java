
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    public static void main(String[] args) {
        try {
            DatagramSocket dg = new DatagramSocket();
            String msg = "um";
            
            DatagramPacket pacote = new DatagramPacket(msg.getBytes(),
                                                       msg.length(),
                                                       InetAddress.getByName("localhost"),
                                                       6800);
            
            dg.send(pacote);
            
            DatagramPacket resposta = new DatagramPacket(new byte[1024], 1024);
            dg.receive(resposta);
            
            
            System.out.println("Servidor devolveu:"+new String(resposta.getData()));
            
            dg.close();
            
        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
