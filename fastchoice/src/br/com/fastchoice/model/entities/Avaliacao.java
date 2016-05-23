package br.com.fastchoice.model.entities;

public class Avaliacao {

	private long id;
	private long idPessoa;
	private long idEstabelecimento;
	private byte valor; // 1 à 5

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public long getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(long idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

	public byte getValor() {
		return valor;
	}

	public void setValor(byte valor) {
		this.valor = valor;
	}

}
