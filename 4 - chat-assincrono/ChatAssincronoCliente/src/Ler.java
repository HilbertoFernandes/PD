import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Ler extends Thread {

	private Socket socket;
	private DataInputStream in;
	String msg;

	public Ler(Socket socket) throws IOException {
		this.socket = socket;
		this.in = new DataInputStream(socket.getInputStream());
	}

	public void run() {
		while (!socket.isClosed()) {
			try {
				msg = in.readUTF();
				System.out.println("Servidor disse : " + msg);
				if ((in.readUTF()).toUpperCase().equals("XAU")) {
					JOptionPane.showMessageDialog(null, "Sessão encerrada pelo servidor.");
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
