package br.com.casaDoCodigo.livroOO.entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Consulta {

	private int codigo;

	private Paciente paciente;
	
	private Medico medico;
	
	private Date data;
	
	public Consulta() {
		
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
    }
	
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
			
			return this.data.equals(consulta.getData()) 
					&& this.paciente.equals(consulta.getPaciente())
						&& this.medico.equals(consulta.getMedico());
		}
		return false;
	}
	
	@Override
	public String toString() {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		return "Paciente : " + this.paciente.getNome() + " Médico: " + this.getMedico().getNome() + " Data: " + dateFormat.format(this.data);
	}
}
