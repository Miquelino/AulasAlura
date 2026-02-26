package br.com.casaDoCodigo.livroOO.entidades;

import java.util.Date;

public abstract class Pessoa {

	private String nome;
	
	private Date dataNascimento;
	
	private Endereco endereco;

	public Pessoa() {
		
	}
		
	public Pessoa(String nome) {
		this.nome = nome;
	}
	
	public Pessoa(String nome, Date dataNascimento) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}
	
	public Pessoa(String nome, Endereco endereco) {
		this.nome = nome;
		this.endereco = endereco;
	}
	
	public Pessoa(String nome, Date dataNascimento, Endereco endereco) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	
}
