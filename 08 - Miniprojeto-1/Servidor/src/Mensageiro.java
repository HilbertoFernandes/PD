
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class Mensageiro extends Thread {

	private Socket cliente;
	private Servidor servidor;

	public Mensageiro(Socket cliente, Servidor servidor) {
		this.cliente = cliente;
		this.servidor = servidor;
	}

	public void run() {
		try(Scanner s = new Scanner(this.cliente.getInputStream())) {
			while (s.hasNextLine()) {
				servidor.entregador(this.cliente, s.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}