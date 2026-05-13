package br.com.casaDoCodigo.livroOO.entidades;

public class Paciente extends Pessoa {

	private String cpf;
	
	private Plano plano;

	public Paciente() {
		
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	@Override
	public int hashCode() {
		
		final int prime = 17;
		int result = 3;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Paciente) {
			
			Paciente paciente = (Paciente) obj;
			return this.cpf.equals(paciente.getCpf());
		}
		return false;
	}

	@Override
	public String toString() {
		
		return "Nome: " + this.getNome() + " CPF: " + this.cpf;
	}
}
