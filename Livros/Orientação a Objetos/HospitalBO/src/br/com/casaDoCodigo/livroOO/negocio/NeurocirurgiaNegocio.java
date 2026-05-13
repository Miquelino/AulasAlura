package br.com.casaDoCodigo.livroOO.negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.casaDoCodigo.livroOO.entidades.Medico;
import br.com.casaDoCodigo.livroOO.entidades.Neurocirurgia;
import br.com.casaDoCodigo.livroOO.entidades.Paciente;
import br.com.casaDoCodigo.livroOO.entidades.Procedimento;

public class NeurocirurgiaNegocio extends ProcedimentoNegocio {

	@Override
	public void marcar(List<Medico> medicos, Paciente paciente, Date data) {

		Neurocirurgia neurocirurgia = new Neurocirurgia();
		neurocirurgia.setPaciente(paciente);
		neurocirurgia.setMedicos(medicos);
		neurocirurgia.setData(data);
		
		getBancoDeDados().adicionar(neurocirurgia);
	}
	
	public Neurocirurgia consultar(int codigo) {
		
		List<Procedimento> procedimentos = getBancoDeDados().listarTodos();
		
		for (Procedimento procedimento : procedimentos) {
		
			if (procedimento.getCodigo() == codigo) {
				return (Neurocirurgia) procedimento;
			}
		}
		
		return null;
	}
	
	public List<Neurocirurgia> pesquisarPorMedico(Medico medico) {
		
		List<Procedimento> procedimentos = getBancoDeDados().listarTodos();
		
		List<Neurocirurgia> procedimentosDoMedico = new ArrayList<>();
		for (Procedimento procedimento : procedimentos) {
			if (procedimento.getMedicos().contains(medico) && procedimento instanceof Neurocirurgia) {
				procedimentosDoMedico.add((Neurocirurgia) procedimento);
			}
		}
		
		return procedimentosDoMedico;
	}
	
	public List<Neurocirurgia> listarTodos() {
		
		List<Procedimento> procedimentos = getBancoDeDados().listarTodos();
		
		List<Neurocirurgia> neurocirurgias = new ArrayList<>();
		
		for (Procedimento procedimento : procedimentos) {
			
			if (procedimento instanceof Neurocirurgia) {
				neurocirurgias.add((Neurocirurgia) procedimento);
			}
		}
		return Collections.unmodifiableList(neurocirurgias);
	}
	
	@Override
	public double calcularTotal(Procedimento procedimento) {
		
		double totalMedicos = 0.0;
		for (Medico medico : procedimento.getMedicos()) {
			totalMedicos += medico.getValorHora();
		}
		
		return procedimento.getValor() + totalMedicos;

	}

}
