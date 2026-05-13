package br.com.casaDoCodigo.livroOO.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.casaDoCodigo.livroOO.persistencia.PacienteBancoDeDados;

public class Paciente extends Pessoa {

	private String cpf;
	
	private Plano plano;

	public Paciente() {
		
	}
	
	public Paciente(String cpf) {
		this.cpf = cpf;
	}

	public Paciente(String cpf, String nome, Endereco endereco) {
		super(nome, endereco);
		this.cpf = cpf;
	}
	
	public Paciente(String nome, Date dataNascimento) {
		super(nome, dataNascimento);
	}

	public Paciente(String nome, Date dataNascimento, Endereco endereco, String cpf, Plano plano) {
		super(nome, dataNascimento, endereco);
		this.cpf = cpf;
		this.plano = plano;
	}

	public String getCpf() {
		return cpf;
	}

	public Plano getPlano() {
		return plano;
	}

	public void cadastrar() {
		new PacienteBancoDeDados().adicionar(this);
	}
	
	public void alterar() {
		new PacienteBancoDeDados().alterar(this);
	}
	
	public void excluir() {
		new PacienteBancoDeDados().excluir(this);
	}
	
	public Paciente consultarPorCPF(){
		
		List<Paciente> pacientes = new PacienteBancoDeDados().listarTodos();
		
		for (Paciente pac : pacientes) {
			if (pac.equals(this)) {
				return pac;
			}
		}
		
		return null;
	}
	
	public List<Paciente> consultarPorNomeDataNascimento(){
		
		List<Paciente> pacientes = new PacienteBancoDeDados().listarTodos();
		
		List<Paciente> pacientesSelecionados = new ArrayList<>();
		for (Paciente pac : pacientes) {
			if (pac.getNome().startsWith(getNome())
					&& pac.getDataNascimento().equals(getDataNascimento())) {
				pacientesSelecionados.add(pac);
			}
		}
		
		return pacientesSelecionados;
	}

	public List<Paciente> listarTodos() {
		return new PacienteBancoDeDados().listarTodos();
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
