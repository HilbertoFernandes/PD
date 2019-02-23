import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Servidor {

	private int porta;
	private Map<String, Socket> conectados;
	private String comando;

	public Servidor(int porta) {
		this.porta = porta;
		this.conectados = new HashMap<String, Socket>();
	}

	SimpleDateFormat sdf_hora = new SimpleDateFormat("HH:mm");
	SimpleDateFormat sdf_data = new SimpleDateFormat("dd/MM/yyyy");
	java.util.Date hora = Calendar.getInstance().getTime();
	String horaFormatada = sdf_hora.format(hora);
	String dataFormatada = sdf_data.format(hora);

	public void executa() throws IOException {
		try (ServerSocket servidor = new ServerSocket(this.porta)) {
			System.out.println("Aguardando conexões...");

			while (true) {
				Socket cliente = servidor.accept();
				System.out.println("Nova conexão estabelecida com " + cliente.getInetAddress().getHostAddress());
				conectados.put(cliente.getLocalAddress() + " : " + cliente.getPort() + " anônimo", cliente);
				Mensageiro mensageiro = new Mensageiro(cliente, this);
				mensageiro.start();
			}
		}
	}

	public void entregador(Socket quemEnviou, String mensagem_recebida) {

		// Verifica a funcionalidade desejada
		if (mensagem_recebida.startsWith("bye"))
			comando = "Sair do grupo";
		else if (mensagem_recebida.startsWith("send -all"))
			comando = "Enviar mensagem ao grupo";
		else if (mensagem_recebida.startsWith("send -user"))
			comando = "Enviar mensagem reservada";
		else if (mensagem_recebida.startsWith("list"))
			comando = "Visualizar participantes";
		else if (mensagem_recebida.startsWith("rename"))
			comando = "Renomear usuario";
		else {
			comando = "Comando invalido";
		}

		switch (comando) {
		case "Enviar mensagem ao grupo": {
			List<String> mensagem_com_comando = Arrays.asList(mensagem_recebida.split(" "));
			String mensagem_sem_comando = "";
			for (int i = 2; i < mensagem_com_comando.size(); i++) {
				mensagem_sem_comando = mensagem_sem_comando + " " + mensagem_com_comando.get(i);
			}
			String remetente = "";
			for (Entry<String, Socket> cliente : conectados.entrySet()) {
				if (quemEnviou.equals(cliente.getValue())) {
					remetente = cliente.getKey();
				}
			}
			String mensagem_enviada = remetente + " : " + mensagem_sem_comando + " " + horaFormatada + " "
					+ dataFormatada;
			for (Map.Entry<String, Socket> conectado : conectados.entrySet()) {
				Socket cliente = conectado.getValue();
				if (!cliente.equals(quemEnviou)) {
					try {
						PrintStream entrega = new PrintStream(cliente.getOutputStream());
						entrega.println(mensagem_enviada);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
			break;

		case "Enviar mensagem reservada": {

			boolean entregue = false;
			String mensagem_sem_comando = "";
			List<String> mensagem_com_comando = Arrays.asList(mensagem_recebida.split(" "));

			for (int i = 3; i < mensagem_com_comando.size(); i++) {
				mensagem_sem_comando = mensagem_sem_comando + " " + mensagem_com_comando.get(i);
			}

			String remetente = "";
			for (Entry<String, Socket> cliente : conectados.entrySet()) {
				if (quemEnviou.equals(cliente.getValue())) {
					remetente = cliente.getKey();
				}
			}
			String mensagem_enviada = remetente + " : " + mensagem_sem_comando + " " + horaFormatada + " "
					+ dataFormatada;
			String destinatario = mensagem_com_comando.get(2);

			for (Entry<String, Socket> cliente : conectados.entrySet()) {
				String chave = cliente.getKey();
				Socket destino = cliente.getValue();
				List<String> chave_em_array = Arrays.asList(chave.split(" "));
				String nome = chave_em_array.get(chave_em_array.size() - 1);
				try {

					if (nome.equals(destinatario) && (!nome.equals("anônimo"))) {
						PrintStream entrega = new PrintStream(destino.getOutputStream());
						entrega.println(mensagem_enviada);
						PrintStream entrega_remetente = new PrintStream(quemEnviou.getOutputStream());
						entrega_remetente.println("Sua mensagem foi entregue a " + nome);
						entregue = true;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (!entregue) {
				PrintStream entrega_remetente;
				try {
					entrega_remetente = new PrintStream(quemEnviou.getOutputStream());
					entrega_remetente.println("O usuário solicitado não existe");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

			break;

		case "Renomear usuario": {
			List<String> nomes = new ArrayList<>();
			for (Map.Entry<String, Socket> conectado : conectados.entrySet()) {
				String chave = conectado.getKey();
				List<String> array_chave = Arrays.asList(chave.split(" "));
				String nome = array_chave.get(array_chave.size() - 1);
				nomes.add(nome);
			}

			List<String> mensagem_com_comando = Arrays.asList(mensagem_recebida.split(" "));
			String nome_desejado = mensagem_com_comando.get(1);
			String chave_atual = "";

			if (!nomes.contains(nome_desejado)) {

				for (Map.Entry<String, Socket> conectado : conectados.entrySet()) {
					if (quemEnviou.equals(conectado.getValue()))
						chave_atual = conectado.getKey();
				}

				// inserindo com novo nome
				conectados.put(quemEnviou.getLocalAddress() + " : " + quemEnviou.getPort() + " " + nome_desejado,
						quemEnviou);

				// removendo o nome antigo
				while (conectados.containsKey(chave_atual)) {
					conectados.remove(chave_atual);
				}

				try {
					PrintStream entrega = new PrintStream(quemEnviou.getOutputStream());
					entrega.println("SUCESSO : Seu nome foi atualizado para '" + nome_desejado + "'");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				PrintStream entrega;
				try {
					entrega = new PrintStream(quemEnviou.getOutputStream());
					entrega.println("ERRO : O nome informado já está em uso por outro usuário !");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

			break;

		case "Visualizar participantes": {
			String on_lines = "";
			try {
				for (Map.Entry<String, Socket> conectado : conectados.entrySet()) {
					String chave = conectado.getKey();
					on_lines = on_lines + "\n" + chave;
				}
				PrintStream entrega;
				entrega = new PrintStream(quemEnviou.getOutputStream());
				entrega.println(on_lines);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
		}
			break;

		case "Sair do grupo": {

			try {
				for (Map.Entry<String, Socket> conectado : conectados.entrySet()) {
					Socket pede_pra_sair = conectado.getValue();
					String chave_pede_pra_sair = conectado.getKey();
					if (pede_pra_sair.equals(quemEnviou)) {
						PrintStream entrega;
						entrega = new PrintStream(quemEnviou.getOutputStream());
						entrega.println("SUCESSO : Você saiu do chat");
						conectados.remove(chave_pede_pra_sair);
					}
				}
				quemEnviou.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			break;

		case "Comando invalido": {
			try {
				PrintStream entrega;
				entrega = new PrintStream(quemEnviou.getOutputStream());
				entrega.println("Comando inválido");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			break;

		default:
			break;
		}
	}
}

//19 11 18 - 16 25 - Hilberto