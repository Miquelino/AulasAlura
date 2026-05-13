package br.com.casaDoCodigo.livroOO.entidades;

import java.util.Date;
import java.util.List;

import br.com.casaDoCodigo.livroOO.persistencia.ProcedimentoBancoDeDados;

public abstract class Procedimento {

	private int codigo;
	
	private Paciente paciente;
	
	private List<Medico> medicos;
	
	private Date data;
	
	private Sala sala;
	
	private double valor;
	
	private int tempoDuracao;
	
	public Procedimento() {
		
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public List<Medico> getMedicos() {
		return medicos;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	
	public Date getData() {
		return data;
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public double getValor() {
		return valor;
	}
	
	public int getTempoDuracao() {
		return tempoDuracao;
	}
	
	public Procedimento(Paciente paciente, List<Medico> medicos, Date data) {
		this.paciente = paciente;
		this.medicos = medicos;
		this.data = data;
	}

	public void cancelar() {
		new ProcedimentoBancoDeDados().excluir(this);
	}

	public abstract void marcar(List<Medico> medico, Paciente paciente, Date data);
	
	public abstract double calcularTotal();
	
	
}
