package br.com.casaDoCodigo.livroOO.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.casaDoCodigo.livroOO.persistencia.ProcedimentoBancoDeDados;

public class Faringoplastia extends Procedimento {

	
	public Faringoplastia() {
	}
	
	public Faringoplastia(int codigo) {
		setCodigo(codigo);
	}
	
	public Faringoplastia(Paciente paciente, List<Medico> medicos, Date data) {
		super(paciente, medicos, data);
	}

	@Override
	public void marcar(List<Medico> medicos, Paciente paciente, Date data) {

		Faringoplastia faringoplastia = new Faringoplastia(paciente, medicos, data);
		
		new ProcedimentoBancoDeDados().adicionar(faringoplastia);
	}
	
	public Faringoplastia consultar() {
		
		List<Procedimento> procedimentos = new ProcedimentoBancoDeDados().listarTodos();
		
		for (Procedimento procedimento : procedimentos) {
		
			if (procedimento.getCodigo() == getCodigo()) {
				return (Faringoplastia) procedimento;
			}
		}
		
		return null;
	}

	public List<Faringoplastia> pesquisarPorMedico(Medico medico) {
		
		List<Procedimento> procedimentos = new ProcedimentoBancoDeDados().listarTodos();
		
		List<Faringoplastia> procedimentosDoMedico = new ArrayList<>();
		for (Procedimento procedimento : procedimentos) {
			if (procedimento.getMedicos().contains(medico) && procedimento instanceof Faringoplastia) {
				procedimentosDoMedico.add((Faringoplastia) procedimento);
			}
		}
		
		return procedimentosDoMedico;
	}

	public List<Faringoplastia> listarTodos() {
		
		List<Procedimento> procedimentos = new ProcedimentoBancoDeDados().listarTodos();
		
		List<Faringoplastia> faringoplastias = new ArrayList<>();
		
		for (Procedimento procedimento : procedimentos) {
			
			if (procedimento instanceof Faringoplastia) {
				faringoplastias.add((Faringoplastia) procedimento);
			}
		}
		return Collections.unmodifiableList(faringoplastias);
	}
	
	@Override
	public double calcularTotal() {

		double valorCliente = getPaciente().getPlano().getMensalidade() * 0.25;
		
		double totalMedicos = 0.0;
		for (Medico medico : getMedicos()) {
			totalMedicos += medico.getValorHora();
		}
		
		return valorCliente + getValor() + totalMedicos;
	}
}
