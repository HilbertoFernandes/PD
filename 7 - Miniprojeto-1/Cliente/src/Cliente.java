
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	private String host;
	private int porta;
	private Scanner teclado;

	public Cliente(String host, int porta) {
		this.host = host;
		this.porta = porta;
	}

	public void conecta() throws UnknownHostException, IOException {
		teclado = new Scanner(System.in);

		try (Socket cliente = new Socket(this.host, this.porta);

				PrintStream saida = new PrintStream(cliente.getOutputStream())) {
			System.out.println("\n --------------------------------------------------------------------------"
					+ "\n| Conectado na porta " + cliente.getLocalPort() + ", Comandos do Chat                                |"
					+ "\n|--------------------------------------------------------------------------|"
					+ "\n| bye                                  | Sair do grupo                     |"
					+ "\n| send -all <mensagem>                 | Enviar mensagem a todos usuários  |"
					+ "\n| send -user <nome_usuario> <mensagem> | Enviar mensagem privada           |"
					+ "\n| list                                 | Ver usuarios conectados           |"
					+ "\n| rename <novo_nome>                   | Mudar nome                        |"
					+ "\n --------------------------------------------------------------------------");
			Mensageiro mensageiro = new Mensageiro(cliente.getInputStream());
			mensageiro.start();

			while (teclado.hasNextLine()) {
				saida.println(teclado.nextLine());
			}
		}
	}
}
