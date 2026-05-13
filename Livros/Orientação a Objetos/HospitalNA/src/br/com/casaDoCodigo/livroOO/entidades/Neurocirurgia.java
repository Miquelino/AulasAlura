package br.com.casaDoCodigo.livroOO.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.casaDoCodigo.livroOO.persistencia.ProcedimentoBancoDeDados;

public class Neurocirurgia extends Procedimento {

	public Neurocirurgia() {
	}
	
	public Neurocirurgia(Paciente paciente, List<Medico> medicos, Date data) {
		super(paciente, medicos, data);
	}
	
	public Neurocirurgia(int codigo) {
		setCodigo(codigo);
	}

	@Override
	public void marcar(List<Medico> medicos, Paciente paciente, Date data) {

		Neurocirurgia neurocirurgia = new Neurocirurgia(paciente, medicos, data);
		
		new ProcedimentoBancoDeDados().adicionar(neurocirurgia);
	}
	
	public Neurocirurgia consultar() {
		
		List<Procedimento> procedimentos = new ProcedimentoBancoDeDados().listarTodos();
		
		for (Procedimento procedimento : procedimentos) {
		
			if (procedimento.getCodigo() == getCodigo()) {
				return (Neurocirurgia) procedimento;
			}
		}
		
		return null;
	}
	
	public List<Neurocirurgia> pesquisarPorMedico(Medico medico) {
		
		List<Procedimento> procedimentos = new ProcedimentoBancoDeDados().listarTodos();
		
		List<Neurocirurgia> procedimentosDoMedico = new ArrayList<>();
		for (Procedimento procedimento : procedimentos) {
			if (procedimento.getMedicos().contains(medico) && procedimento instanceof Neurocirurgia) {
				procedimentosDoMedico.add((Neurocirurgia) procedimento);
			}
		}
		
		return procedimentosDoMedico;
	}
	
	public List<Neurocirurgia> listarTodos() {
		
		List<Procedimento> procedimentos = new ProcedimentoBancoDeDados().listarTodos();
		
		List<Neurocirurgia> neurocirurgias = new ArrayList<>();
		
		for (Procedimento procedimento : procedimentos) {
			
			if (procedimento instanceof Neurocirurgia) {
				neurocirurgias.add((Neurocirurgia) procedimento);
			}
		}
		return Collections.unmodifiableList(neurocirurgias);
	}
	
	@Override
	public double calcularTotal() {
		
		double totalMedicos = 0.0;
		for (Medico medico : getMedicos()) {
			totalMedicos += medico.getValorHora();
		}
		
		return getValor() + totalMedicos;
	}

}
