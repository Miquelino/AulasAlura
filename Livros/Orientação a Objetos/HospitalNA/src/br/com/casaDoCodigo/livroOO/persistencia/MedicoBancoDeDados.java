package br.com.casaDoCodigo.livroOO.persistencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.casaDoCodigo.livroOO.entidades.Medico;

public class MedicoBancoDeDados {

	private static List<Medico> medicos = new ArrayList<>();;
	
	public MedicoBancoDeDados() {
	}
	
	public void adicionar(Medico medico) {
		
		if (!medicos.contains(medico)) {
			medicos.add(medico);
		} 
	}
	
	public void alterar(Medico medico) {
		
		int posicao = medicos.indexOf(medico);
		medicos.set(posicao, medico);
	}
	
	public void excluir(Medico medico) {
		medicos.remove(medico);
	}
	
	public List<Medico> listarTodos() {
		return Collections.unmodifiableList(medicos);
	}
}
