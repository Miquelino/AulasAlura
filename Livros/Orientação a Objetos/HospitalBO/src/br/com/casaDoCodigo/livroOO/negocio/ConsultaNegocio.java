package br.com.casaDoCodigo.livroOO.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.casaDoCodigo.livroOO.entidades.Consulta;
import br.com.casaDoCodigo.livroOO.entidades.Medico;
import br.com.casaDoCodigo.livroOO.entidades.Paciente;
import br.com.casaDoCodigo.livroOO.persistencia.ConsultaBancoDeDados;

public class ConsultaNegocio {

	private ConsultaBancoDeDados bancoDeDados;
	
	public ConsultaNegocio() {
		this.bancoDeDados = new ConsultaBancoDeDados();
	}
	
	public void marcar(Paciente paciente, Medico medico, Date data) {
		
		Consulta consulta = new Consulta();
		consulta.setPaciente(paciente);
		consulta.setMedico(medico);
		consulta.setData(data);
		
		bancoDeDados.adicionar(consulta);
	}
	
	public void cancelar(Consulta consulta) {
		bancoDeDados.excluir(consulta);
	}
	
	public Consulta consultar(int codigo) {
		
		List<Consulta> consultas = bancoDeDados.listarTodos();
		
		for (Consulta consulta : consultas) {
			if (consulta.getCodigo() == codigo) {
				return consulta;
			}
		}
		return null;
	}
	
	public List<Consulta> pesquisarPorPaciente(Paciente paciente) {
		
		List<Consulta> consultas = bancoDeDados.listarTodos();
		
		List<Consulta> consultasDoPaciente = new ArrayList<Consulta>();
		for (Consulta consulta : consultas) {
			if (consulta.getPaciente().equals(paciente)) {
				consultasDoPaciente.add(consulta);
			}
		}
		
		return consultasDoPaciente;
	}
	
	public List<Consulta> listarTodos() {
		return  bancoDeDados.listarTodos();
	}

}
