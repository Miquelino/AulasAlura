package br.com.casaDoCodigo.livroOO.entidades;

public class Plano {

	private String nome;
	
	private double mensalidade;

	public Plano() {
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getMensalidade() {
		return mensalidade;
	}

	public void setMensalidade(double mensalidade) {
		this.mensalidade = mensalidade;
	}

	@Override
	public int hashCode() {
		final int prime = 5;
		int result = 7;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Plano) {
			
			Plano plano = (Plano) obj;
			return nome.equalsIgnoreCase(plano.getNome());
		}
		return false;
	}
	
	@Override
	public String toString() {
		
		return "Nome: " + this.nome;
	}
}
