package br.com.casaDoCodigo.livroOO.negocio;

import java.util.ArrayList;
import java.util.List;

import br.com.casaDoCodigo.livroOO.entidades.Medico;
import br.com.casaDoCodigo.livroOO.persistencia.MedicoBancoDeDados;

public class MedicoNegocio {

	private MedicoBancoDeDados bancoDeDados;
	
	public MedicoNegocio() {
		this.bancoDeDados = new MedicoBancoDeDados();
	}
	
	public void cadastrar(Medico medico) {
		bancoDeDados.adicionar(medico);
	}
	
	public void alterar(Medico medico) {
		bancoDeDados.alterar(medico);
	}
	
	public void excluir(Medico medico) {
		bancoDeDados.excluir(medico);
	}
	
	public Medico consultar(int CRM){
		
		Medico medico = new Medico();
		medico.setCrm(CRM);
		
		List<Medico> medicos = bancoDeDados.listarTodos();
		
		for (Medico med : medicos) {
			if (med.equals(medico)) {
				return med;
			}
		}
		
		return null;
	}
	
	public List<Medico> consultar(String nome){
		
		List<Medico> medicos = bancoDeDados.listarTodos();
		
		List<Medico> medicosSelecionados = new ArrayList<>();
		for (Medico med : medicos) {
			if (med.getNome().startsWith(nome)) {
				medicosSelecionados.add(med);
			}
		}
		
		return medicosSelecionados;
	}
	
	public List<Medico> listarTodos() {
		return bancoDeDados.listarTodos();
	}	
}
