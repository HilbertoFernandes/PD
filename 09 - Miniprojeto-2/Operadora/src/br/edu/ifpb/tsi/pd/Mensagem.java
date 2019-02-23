package br.edu.ifpb.tsi.pd;

public class Mensagem {
	private String remetente;
	private String destinatario;
	private String texto;
	private boolean entregue;

	public Mensagem(String remetente, String destinatario, String texto, boolean entregue) {
		super();
		this.remetente = remetente;
		this.destinatario = destinatario;
		this.texto = texto;
		this.entregue = entregue;
	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public boolean isEntregue() {
		return entregue;
	}

	public void setEntregue(boolean entregue) {
		this.entregue = entregue;
	}

}
