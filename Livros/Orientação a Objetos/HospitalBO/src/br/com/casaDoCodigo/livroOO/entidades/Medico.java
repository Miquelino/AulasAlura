package br.com.casaDoCodigo.livroOO.entidades;

import java.util.List;

public class Medico extends Pessoa {

	private int crm;
	
	private List<Especialidade> especialidades;
	
	private double valorHora;

	public Medico() {
		
	}
	
	public int getCrm() {
		return crm;
	}

	public void setCrm(int crm) {
		this.crm = crm;
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	public double getValorHora() {
		return valorHora;
	}

	public void setValorHora(double valorHora) {
		this.valorHora = valorHora;
	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + crm;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Medico) {
			
			Medico medico = (Medico) obj;
			return this.crm == medico.getCrm();
		}
		return false;
	}
	
	@Override
	public String toString() {
		
		return "Nome: " + this.getNome() + " CRM: " + this.crm;
	}
	
}
