package br.com.casaDoCodigo.livroOO.negocio;

import java.util.Date;
import java.util.List;

import br.com.casaDoCodigo.livroOO.entidades.Medico;
import br.com.casaDoCodigo.livroOO.entidades.Paciente;
import br.com.casaDoCodigo.livroOO.entidades.Procedimento;
import br.com.casaDoCodigo.livroOO.persistencia.ProcedimentoBancoDeDados;

public abstract class ProcedimentoNegocio {

	private ProcedimentoBancoDeDados bancoDeDados;
	
	public ProcedimentoNegocio() {
		this.bancoDeDados = new ProcedimentoBancoDeDados();
	}
	
	public ProcedimentoBancoDeDados getBancoDeDados() {
		return bancoDeDados;
	}
	
	public void cancelar(Procedimento procedimento) {
		bancoDeDados.excluir(procedimento);
	}

	public abstract void marcar(List<Medico> medico, Paciente paciente, Date data);
	
	public abstract double calcularTotal(Procedimento procedimento);
	
}
