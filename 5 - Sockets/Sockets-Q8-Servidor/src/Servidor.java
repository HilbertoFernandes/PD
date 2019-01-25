import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

	public static void main(String[] args) {
		String msg = null;

		try {
			DatagramSocket socket = new DatagramSocket(6800);
			int tamanho = 1024;
			byte[] dados = new byte[tamanho];
			DatagramPacket pacote = new DatagramPacket(dados, tamanho);
			System.out.println("Aguardando a chegada de um datagrama...");
			socket.receive(pacote);
			System.out.println("Cliente enviou:" + new String(pacote.getData()));

			String op = new String(pacote.getData()).trim();
			if (op.equals("um")) {
				System.out.println("OPÇÃO : " + op);
				msg = "Agora vai!";
			} else {
				System.out.println("Não foi " + op);
			}

			DatagramPacket eco = new DatagramPacket(msg.getBytes(), pacote.getLength(), pacote.getAddress(),
					pacote.getPort());
			socket.send(eco);
			socket.close();
		} catch (SocketException ex) {
			Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
