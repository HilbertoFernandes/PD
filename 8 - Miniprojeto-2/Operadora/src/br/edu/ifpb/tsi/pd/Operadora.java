package br.edu.ifpb.tsi.pd;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/operadora/{numero}")
public class Operadora {

	// linhas registradas
	private static Map<String, Linha> linhas = Collections.synchronizedMap(new HashMap<String, Linha>());
	private String destino = null;
	private String msg = null;
	private Linha linha = null;

	@OnOpen
	public void onOpen(Session s, @PathParam("numero") String numero) {
		registraNumero(s, numero);
		entegarMensagensGuardadas(s, numero);
	}

	@OnMessage
	public void OnMessage(Session s, @PathParam("numero") String numero, String mensagem) {
		tratarMensagens(s, numero, mensagem);
	}

	@OnClose
	public void onClose(@PathParam("numero") String numero) {
		desconectarLinha(numero);
	}

	@OnError
	public void onError(Throwable t) {
		System.out.println(t.getMessage());
	}

	private void registraNumero(Session s, String numero) {
		if (linhas.containsKey(numero)) {
			linha = linhas.get(numero);
			linha.setSession(s);
			linha.setConexao("Conectada");
		} else {
			linha = new Linha(numero);
			linha.setSession(s);
			linha.setConexao("Conectada");
			linhas.put(numero, linha);
		}
	}

	private void tratarMensagens(Session s, String numero, String mensagem) {
		try {
			msg = "";
			destino = Arrays.asList(mensagem.split(":")).get(0);

			for (int i = 1; i < Arrays.asList(mensagem.split(":")).size(); i++) {
				msg = msg + Arrays.asList(mensagem.split(":")).get(i) + " ";
			}

			// Consultar Saldo
			if (destino.equals("saldo")) {
				s.getBasicRemote().sendText("Saldo : " + linhas.get(numero).getSaldo());
			}
			// Inserir Crédito
			if (destino.equals("creditar")) {
				linhas.get(numero).setSaldo(
						linhas.get(numero).getSaldo() + Double.parseDouble(Arrays.asList(mensagem.split(":")).get(1)));
				s.getBasicRemote().sendText("Novo saldo : " + linhas.get(numero).getSaldo());
			}
			// Verifica se há saldo
			if (linhas.get(numero).getSaldo() >= 0.50) {
				// Telefone existe e está em serviço
				if ((linhas.containsKey(destino)) && (linhas.get(destino).getConexao().equals("Conectada"))) {
					linhas.get(numero).setSaldo(linhas.get(numero).getSaldo() - 0.50);
					linhas.get(destino).getSession().getBasicRemote()
							.sendText(numero + " enviou '" + msg + "' em " + getMomento());
					linhas.get(numero).getSession().getBasicRemote()
							.sendText("Mensagem '" + msg + "'entregue a [" + destino + "] em " + getMomento());
				}
				// Telefone existe mas está fora de área
				if ((linhas.containsKey(destino)) && (linhas.get(destino).getConexao().equals("Desconectada"))) {
					linhas.get(numero).setSaldo(linhas.get(numero).getSaldo() - 0.50);
					linhas.get(numero).getSession().getBasicRemote()
							.sendText("A mensagem será entregue quando [" + destino + "] entrar na rede.");
					linhas.get(destino).getMensagens().add(
							new Mensagem(numero, destino, numero + " enviou '" + msg + "' em " + getMomento(), false));
				}
			} else {
				linhas.get(numero).getSession().getBasicRemote()
						.sendText("Seu saldo é insuficiente para enviar mensagens, efetue recarga");
			}
			// Telefone não existe
			if (linhas.get(destino) == null && (!destino.equals("saldo")) && (!destino.equals("creditar")))
				linhas.get(numero).getSession().getBasicRemote().sendText("Número inexistente!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void entegarMensagensGuardadas(Session s, String numero) {
		if (!linhas.get(numero).getMensagens().isEmpty()) {
			List<Mensagem> msgs = linhas.get(numero).getMensagens();
			for (int i = 0; i < msgs.size(); i++) {
				try {
					if (!msgs.get(i).isEntregue()) {
						s.getBasicRemote().sendText(msgs.get(i).getTexto());
						msgs.get(i).setEntregue(true);
						Session s_remetente = linhas.get(msgs.get(i).getRemetente()).getSession();
						if (linhas.get(msgs.get(i).getRemetente()).getConexao().equals("Conectada")) {
							s_remetente.getBasicRemote().sendText("A mensagem '" + msgs.get(i).getTexto()
									+ "' foi entregue a [" + msgs.get(i).getDestinatario() + "] em " + getMomento());
						} else {
							linhas.get(msgs.get(i).getRemetente()).getMensagens()
									.add(new Mensagem(numero, destino,
											"A mensagem '" + msgs.get(i).getTexto() + "' foi entregue em a ["
													+ msgs.get(i).getDestinatario() + "] em " + getMomento(),
											false));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void desconectarLinha(String numero) {
		linha = linhas.get(numero);
		linha.setConexao("Desconectada");
		linha.setSession(null);
	}

	public String getMomento() {
		SimpleDateFormat sdf_hora = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdf_data = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date hora = Calendar.getInstance().getTime();
		String horaFormatada = sdf_hora.format(hora);
		String dataFormatada = sdf_data.format(hora);
		return dataFormatada + " às " + horaFormatada;
	}
}
