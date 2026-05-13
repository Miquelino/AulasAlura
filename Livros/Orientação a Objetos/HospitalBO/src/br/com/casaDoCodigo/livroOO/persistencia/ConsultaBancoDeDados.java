package br.com.casaDoCodigo.livroOO.persistencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.casaDoCodigo.livroOO.entidades.Consulta;

public class ConsultaBancoDeDados {

	private List<Consulta> consultas;
	
	public ConsultaBancoDeDados() {
		this.consultas = new ArrayList<>();
	}
	
	public void adicionar(Consulta consulta) {
		
		if (!consultas.contains(consulta)) {
			consultas.add(consulta);
			consulta.setCodigo(consultas.size());
		} 
	}
	
	public void excluir(Consulta consulta) {
		consultas.remove(consulta);
	}
	
	public List<Consulta> listarTodos() {
		return Collections.unmodifiableList(consultas);
	}
}
