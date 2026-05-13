package br.com.casaDoCodigo.livroOO.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.casaDoCodigo.livroOO.entidades.Paciente;
import br.com.casaDoCodigo.livroOO.persistencia.PacienteBancoDeDados;

public class PacienteNegocio {
	
	private PacienteBancoDeDados bancoDeDados;
	
	public PacienteNegocio() {
		this.bancoDeDados = new PacienteBancoDeDados();
	}
	
	public void cadastrar(Paciente paciente) {
		bancoDeDados.adicionar(paciente);
	}
	
	public void alterar(Paciente paciente) {
		bancoDeDados.alterar(paciente);
	}
	
	public void excluir(Paciente paciente) {
		bancoDeDados.excluir(paciente);
	}
	
	public Paciente consultar(String CPF){
		
		Paciente paciente = new Paciente();
		paciente.setCpf(CPF);
		
		List<Paciente> pacientes = bancoDeDados.listarTodos();
		
		for (Paciente pac : pacientes) {
			if (pac.equals(paciente)) {
				return pac;
			}
		}
		
		return null;
	}
	
	public List<Paciente> consultar(String nome, Date dataNascimento){
		
		List<Paciente> pacientes = bancoDeDados.listarTodos();
		
		List<Paciente> pacientesSelecionados = new ArrayList<>();
		for (Paciente pac : pacientes) {
			if (pac.getNome().startsWith(nome)
					&& pac.getDataNascimento().equals(dataNascimento)) {
				pacientesSelecionados.add(pac);
			}
		}
		
		return pacientesSelecionados;
	}

	public List<Paciente> listarTodos() {
		return bancoDeDados.listarTodos();
	}	
}
