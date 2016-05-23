package br.com.fastchoice.model.entities;

import java.sql.Date;

public class Mensagem {

	private long id;
	private long idRemetente;
	private long idDestinario;
	private String msg;
	private Date data;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdRemetente() {
		return idRemetente;
	}

	public void setIdRemetente(long idRemetente) {
		this.idRemetente = idRemetente;
	}

	public long getIdDestinario() {
		return idDestinario;
	}

	public void setIdDestinario(long idDestinario) {
		this.idDestinario = idDestinario;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
