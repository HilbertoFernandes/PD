
import java.io.InputStream;
import java.util.Scanner;

class Mensageiro extends Thread {

	private InputStream servidor;

	public Mensageiro(InputStream servidor) {
		this.servidor = servidor;
	}

	public void run() {
		try (Scanner s = new Scanner(this.servidor)) {
			while (s.hasNextLine()) {
				System.out.println(s.nextLine());
			}
		}
	}
}