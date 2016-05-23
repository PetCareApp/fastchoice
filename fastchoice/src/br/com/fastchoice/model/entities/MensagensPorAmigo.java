package br.com.fastchoice.model.entities;

import java.util.ArrayList;
import java.util.List;

public class MensagensPorAmigo {

	private long idRemetente;
	private List<Mensagem> mensagens;
	
	public MensagensPorAmigo() {
		mensagens = new ArrayList<Mensagem>();
	}

	public long getIdRemetente() {
		return idRemetente;
	}

	public void setIdRemetente(long idRemetente) {
		this.idRemetente = idRemetente;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

}
