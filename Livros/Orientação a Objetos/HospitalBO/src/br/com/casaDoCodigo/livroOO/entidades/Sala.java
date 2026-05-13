package br.com.casaDoCodigo.livroOO.entidades;

public class Sala {

	private String nome;
	
	public Sala() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		
		if (obj instanceof Sala) {
			
			Sala sala = (Sala) obj;
			return nome.equalsIgnoreCase(sala.getNome());
		}
		return false;
	}
	
	@Override
	public String toString() {
		
		return "Nome: " + this.nome;
	}
}
