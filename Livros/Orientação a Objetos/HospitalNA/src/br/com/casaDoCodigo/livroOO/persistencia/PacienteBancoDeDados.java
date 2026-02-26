package br.com.casaDoCodigo.livroOO.persistencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.casaDoCodigo.livroOO.entidades.Paciente;

public class PacienteBancoDeDados {

	private static List<Paciente> pacientes = new ArrayList<>();
	
	public PacienteBancoDeDados() {
	}
	
	public void adicionar(Paciente paciente) {
		
		if (!pacientes.contains(paciente)) {
			pacientes.add(paciente);
		} 
	}
	
	public void alterar(Paciente paciente) {
		
		int posicao = pacientes.indexOf(paciente);
		pacientes.set(posicao, paciente);
	}
	
	public void excluir(Paciente paciente) {
		pacientes.remove(paciente);
	}
	
	public List<Paciente> listarTodos() {
		return Collections.unmodifiableList(pacientes);
	}

}
