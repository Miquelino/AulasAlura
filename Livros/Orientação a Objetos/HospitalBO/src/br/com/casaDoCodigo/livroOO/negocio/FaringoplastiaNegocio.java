package br.com.casaDoCodigo.livroOO.negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.casaDoCodigo.livroOO.entidades.Faringoplastia;
import br.com.casaDoCodigo.livroOO.entidades.Medico;
import br.com.casaDoCodigo.livroOO.entidades.Paciente;
import br.com.casaDoCodigo.livroOO.entidades.Procedimento;

public class FaringoplastiaNegocio extends ProcedimentoNegocio {

	@Override
	public void marcar(List<Medico> medicos, Paciente paciente, Date data) {

		Faringoplastia faringoplastia = new Faringoplastia();
		faringoplastia.setPaciente(paciente);
		faringoplastia.setMedicos(medicos);
		faringoplastia.setData(data);
		
		getBancoDeDados().adicionar(faringoplastia);
	}
	
	public Faringoplastia consultar(int codigo) {
		
		List<Procedimento> procedimentos = getBancoDeDados().listarTodos();
		
		for (Procedimento procedimento : procedimentos) {
		
			if (procedimento.getCodigo() == codigo) {
				return (Faringoplastia) procedimento;
			}
		}
		
		return null;
	}

	public List<Faringoplastia> pesquisarPorMedico(Medico medico) {
		
		List<Procedimento> procedimentos = getBancoDeDados().listarTodos();
		
		List<Faringoplastia> procedimentosDoMedico = new ArrayList<>();
		for (Procedimento procedimento : procedimentos) {
			if (procedimento.getMedicos().contains(medico) && procedimento instanceof Faringoplastia) {
				procedimentosDoMedico.add((Faringoplastia) procedimento);
			}
		}
		
		return procedimentosDoMedico;
	}

	public List<Faringoplastia> listarTodos() {
		
		List<Procedimento> procedimentos = getBancoDeDados().listarTodos();
		
		List<Faringoplastia> faringoplastias = new ArrayList<>();
		
		for (Procedimento procedimento : procedimentos) {
			
			if (procedimento instanceof Faringoplastia) {
				faringoplastias.add((Faringoplastia) procedimento);
			}
		}
		return Collections.unmodifiableList(faringoplastias);
	}
	
	@Override
	public double calcularTotal(Procedimento procedimento) {
		
		double valorCliente = procedimento.getPaciente().getPlano().getMensalidade() * 0.25;
		
		double totalMedicos = 0.0;
		for (Medico medico : procedimento.getMedicos()) {
			totalMedicos += medico.getValorHora();
		}
		
		return valorCliente + procedimento.getValor() + totalMedicos;
	}

}
