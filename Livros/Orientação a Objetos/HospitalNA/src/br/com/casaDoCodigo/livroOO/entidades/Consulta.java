package br.com.casaDoCodigo.livroOO.entidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.casaDoCodigo.livroOO.persistencia.ConsultaBancoDeDados;

public class Consulta {

	private int codigo;

	private Paciente paciente;
	
	private Medico medico;
	
	private Date data;
	
	public Consulta() {
		
	}

	public Consulta(int codigo) {
		this.codigo = codigo;
	}
	
	public Consulta(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public Medico getMedico() {
		return medico;
	}
	
	public Date getData() {
		return data;
	}
	
	public void marcar(Paciente paciente, Medico medico, Date data) {
		
		this.paciente = paciente;
		this.medico = medico;
		this.data = data;
		
		new ConsultaBancoDeDados().adicionar(this);
	}
	
	public void cancelar() {
		new ConsultaBancoDeDados().excluir(this);
	}
	
	public Consulta consultar() {
		
		List<Consulta> consultas = new ConsultaBancoDeDados().listarTodos();
		
		for (Consulta consulta : consultas) {
			if (consulta.getCodigo() == codigo) {
				return consulta;
			}
		}
		return null;
	}
	
	public List<Consulta> pesquisarPorPaciente() {
		
		List<Consulta> consultas = new ConsultaBancoDeDados().listarTodos();
		
		List<Consulta> consultasDoPaciente = new ArrayList<Consulta>();
		for (Consulta consulta : consultas) {
			if (consulta.getPaciente().equals(paciente)) {
				consultasDoPaciente.add(consulta);
			}
		}
		
		return consultasDoPaciente;
	}
	
	public List<Consulta> listarTodos() {
		return  new ConsultaBancoDeDados().listarTodos();
	}

	@Override
	public int hashCode() {
		
		final int prime = 11;
		int result = 23;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((paciente == null) ? 0 : paciente.hashCode());
		result = prime * result + ((medico == null) ? 0 : medico.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Consulta) {
			
			Consulta consulta = (Consulta) obj;
			
			return this.data.equals(consulta.data) 
					&& this.paciente.equals(consulta.paciente)
						&& this.medico.equals(consulta.medico);
		}
		return false;
	}
	
	@Override
	public String toString() {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		return "Paciente : " + this.paciente.getNome() + " Médico: " + this.medico.getNome() + " Data: " + dateFormat.format(this.data);
	}
}
