import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Cliente {

	public static void main(String[] args) {

		try {
			System.out.println("Qual o IP do servidor ?");
			@SuppressWarnings("resource")
			Scanner teclado = new Scanner(System.in);
			String ip = teclado.next();
			Socket socket = new Socket(ip, 6500);
			System.out.println("Conexão estabelecida com " + socket.getInetAddress() + " porta : " + socket.getPort());
			Ler ler = new Ler(socket);
			ler.start();
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			while (!socket.isClosed()) {
				out.writeUTF(JOptionPane.showInputDialog("Digite a mensagem pro Servidor"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}