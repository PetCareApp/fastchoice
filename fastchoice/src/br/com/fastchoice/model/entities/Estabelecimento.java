package br.com.fastchoice.model.entities;

import java.awt.Image;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Estabelecimento {

	private long id;
	private String nome;
	private byte tipo; // 1: bar, 2: restaurante, 3: pub, 4: lanchonete
	private String endRua;
	private String endBairro;
	private String endCidade;
	private String endComplemento;
	private String endGoogle;
	private short endNumero;
	private String telefone;
	private String descricao;
	private byte[] foto;
	private String fotoExtension;
	private String fotoPath;
	private List<Comentario> comentarios;
	private byte vagasTotais;
	private byte vagasDisponiveis;
	private double avaliacao;
	private int visitas;
	private List<Reserva> reservas;
	
	public Estabelecimento() {
		comentarios = new ArrayList<Comentario>();
	}

	// avaliação é a média da consulta no BD
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte getTipo() {
		return tipo;
	}

	public void setTipo(byte tipo) {
		this.tipo = tipo;
	}

	public String getEndRua() {
		return endRua;
	}

	public void setEndRua(String endRua) {
		this.endRua = endRua;
	}

	public String getEndBairro() {
		return endBairro;
	}

	public void setEndBairro(String endBairro) {
		this.endBairro = endBairro;
	}

	public String getEndComplemento() {
		return endComplemento;
	}

	public void setEndComplemento(String endComplemento) {
		this.endComplemento = endComplemento;
	}

	public short getEndNumero() {
		return endNumero;
	}

	public void setEndNumero(short endNumero) {
		this.endNumero = endNumero;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public byte[] getFoto() {
	    return foto;
	}

	public void setFoto(byte[] foto) {
	    this.foto = foto;
	}

	public String getFotoExtension() {
	    return fotoExtension;
	}

	public void setFotoExtension(String fotoExtension) {
	    this.fotoExtension = fotoExtension;
	}

	public String getFotoPath() {
		return fotoPath;
	}

	public void setFotoPath(String fotoPath) {
		this.fotoPath = fotoPath;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public byte getVagasTotais() {
		return vagasTotais;
	}

	public void setVagasTotais(byte vagasTotais) {
		this.vagasTotais = vagasTotais;
	}

	public byte getVagasDisponiveis() {
		return vagasDisponiveis;
	}

	public void setVagasDisponiveis(byte vagasDisponiveis) {
		this.vagasDisponiveis = vagasDisponiveis;
	}
	
	public void subVagasDisponiveis() {
		this.vagasDisponiveis -= 1;
	}
	
	public void removeReserva(Reserva reserva) {
		for (Iterator<Reserva> it = this.reservas.iterator(); it.hasNext();) {
			Reserva r = it.next();
			if (id == reserva.getIdEstabelecimento() && r.getIdPessoa() == reserva.getIdPessoa()) {
				it.remove();
			}
		}
		this.vagasDisponiveis += 1;
	}

	public double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public int getVisitas() {
		return visitas;
	}

	public void setVisitas(int visitas) {
		this.visitas = visitas;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public String getEndCidade() {
	    return endCidade;
	}

	public void setEndCidade(String endCidade) {
	    this.endCidade = endCidade;
	}

	public String getEndGoogle() {
	    return endGoogle;
	}

	public void setEndGoogle(String endGoogle) {
	    this.endGoogle = endGoogle;
	}


}
