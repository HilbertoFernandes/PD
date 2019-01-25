
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author 20142370287
 */
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Chat_Cliente {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("192.168.15.21", 6500);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(JOptionPane.showInputDialog("Informe o seu nome:"));
			DataInputStream in = new DataInputStream(socket.getInputStream());
			String servidor = in.readUTF();
			while (0 != 1) {
				out.writeUTF(JOptionPane.showInputDialog("Digite a mensagem pro servidor"));
				String msg = in.readUTF();
				JOptionPane.showMessageDialog(null, servidor + ": " + msg);
				if (msg.toUpperCase().equals("XAU")) {
					JOptionPane.showMessageDialog(null, "Sessão Encerrada pelo servidor.");
					socket.close();
				}
			}
		} catch (IOException ex) {
			Logger.getLogger(Chat_Cliente.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
