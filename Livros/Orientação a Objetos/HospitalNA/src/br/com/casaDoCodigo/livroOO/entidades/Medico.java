package br.com.casaDoCodigo.livroOO.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.casaDoCodigo.livroOO.persistencia.MedicoBancoDeDados;

public class Medico extends Pessoa {

	private int crm;
	
	private List<Especialidade> especialidades;
	
	private double valorHora;

	public Medico() {
		
	}
	
	public Medico(int CRM) {
		this.crm = CRM;
	}
	
	public Medico(String nome) {
		super(nome);
	}
		
	public Medico(String nome, int crm, Endereco endereco, Date dataNascimento, double valorHora,
			List<Especialidade> especialidades) {
		super(nome, dataNascimento, endereco);
		this.crm = crm;
		this.valorHora = valorHora;
		this.especialidades = especialidades;
	}

	public Medico(String nome, int crm, Endereco endereco, double valorHora) {
		super(nome, endereco);
		this.crm = crm;
		this.valorHora = valorHora;
	}

	public double getValorHora() {
		return valorHora;
	}
	
	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}
	
	public void cadastrar() {
		new MedicoBancoDeDados().adicionar(this);
	}
	
	public void alterar() {
		new MedicoBancoDeDados().alterar(this);
	}
	
	public void excluir() {
		new MedicoBancoDeDados().excluir(this);
	}
	
	public Medico consultarPorCRM(){
		
		List<Medico> medicos = new MedicoBancoDeDados().listarTodos();
		
		for (Medico med : medicos) {
			if (med.equals(this)) {
				return med;
			}
		}
		
		return null;
	}
	
	public List<Medico> consultarPorNome(){
		
		List<Medico> medicos = new MedicoBancoDeDados().listarTodos();
		
		List<Medico> medicosSelecionados = new ArrayList<>();
		for (Medico med : medicos) {
			if (med.getNome().startsWith(getNome())) {
				medicosSelecionados.add(med);
			}
		}
		
		return medicosSelecionados;
	}
	
	public List<Medico> listarTodos() {
		return new MedicoBancoDeDados().listarTodos();
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
			return this.crm == medico.crm;
		}
		return false;
	}
	
	@Override
	public String toString() {
		
		return "Nome: " + this.getNome() + " CRM: " + this.crm;
	}
	
}
