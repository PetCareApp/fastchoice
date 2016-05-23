package br.com.fastchoice.model.entities;

import java.awt.Image;
import java.io.File;
import java.util.List;

public class Pessoa {

	private long id;
	private String nome;
	private boolean masculino;
	private byte idade;
	private String cpf;
	private String endRua;
	private String endBairro;
	private String endComplemento;
	private short endNumero;
	private String telefone;
	private byte[] foto;
	private String fotoExtension;
	private List<Amigo> amigos;
	private String endGoogle;

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

	public boolean isMasculino() {
		return masculino;
	}

	public void setMasculino(boolean masculino) {
		this.masculino = masculino;
	}

	public byte getIdade() {
		return idade;
	}

	public void setIdade(byte idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public byte[] getFoto() {
	    return foto;
	}

	public void setFoto(byte[] foto) {
	    this.foto = foto;
	}

	public List<Amigo> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<Amigo> amigos) {
		this.amigos = amigos;
	}

	public String getFotoExtension() {
	    return fotoExtension;
	}

	public void setFotoExtension(String fotoExtension) {
	    this.fotoExtension = fotoExtension;
	}

	public String getEndGoogle() {
	    return endGoogle;
	}

	public void setEndGoogle(String endGoogle) {
	    this.endGoogle = endGoogle;
	}

}
