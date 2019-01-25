package br.edu.ifpb.tsi.pd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.websocket.Session;

public class Linha {
	private Session session;
	private String numero;
	private double saldo;
	private String conexao;
	private List<Mensagem> mensagens;

	public Linha(String numero) {
		this.numero = numero;
		this.saldo = 0;
		this.session = null;
		this.mensagens = Collections.synchronizedList(new ArrayList<Mensagem>());
		this.conexao = "Desconectada";
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getConexao() {
		return conexao;
	}

	public void setConexao(String conexao) {
		this.conexao = conexao;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
}
