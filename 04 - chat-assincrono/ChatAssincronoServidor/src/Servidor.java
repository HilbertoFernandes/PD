import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Servidor {

	public static void main(String[] args) {

		ServerSocket servidor;
		try {
			servidor = new ServerSocket(6500);
			System.out.println("Aguardando uma conexão...");
			Socket socket = servidor.accept();
			System.out.println("Conexão estabelecida com " + socket.getInetAddress() + " porta : " + socket.getPort());
			Ler ler = new Ler(socket);
			ler.start();
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			while (!socket.isClosed()) {
				out.writeUTF(JOptionPane.showInputDialog("Digite a mensagem pro cliente"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}