package br.com.casaDoCodigo.livroOO.util;

import java.util.ArrayList;
import java.util.List;

import br.com.casaDoCodigo.livroOO.entidades.Especialidade;

public final class ConversoraEspecialidade {

	public static List<Especialidade> converterEspecialidades(String especialidades) {
		
		String[] especialidadesArray = especialidades.split(",");
		
		List<Especialidade> listaEspecialidades = new ArrayList<>();		
		for (String nomeEspecialidade : especialidadesArray) {
			
			Especialidade especialidade = new Especialidade();
			especialidade.setNome(nomeEspecialidade);
			listaEspecialidades.add(especialidade);
		}
		
		return listaEspecialidades;
	}
}
