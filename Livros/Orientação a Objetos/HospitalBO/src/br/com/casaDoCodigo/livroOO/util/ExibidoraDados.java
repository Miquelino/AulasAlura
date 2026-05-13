package br.com.casaDoCodigo.livroOO.util;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.casaDoCodigo.livroOO.entidades.Consulta;
import br.com.casaDoCodigo.livroOO.entidades.Faringoplastia;
import br.com.casaDoCodigo.livroOO.entidades.Medico;
import br.com.casaDoCodigo.livroOO.entidades.Neurocirurgia;
import br.com.casaDoCodigo.livroOO.entidades.Paciente;
import br.com.casaDoCodigo.livroOO.entidades.Procedimento;

public final class ExibidoraDados {

	public ExibidoraDados() {
		
	}
	
	public void exibirPaciente(Paciente paciente) {
		
		System.out.println("Paciente");
		System.out.println("         " + paciente.toString());
		System.out.println("         Endereço: " + paciente.getEndereco());

	}

	public void exibirPacientes(List<Paciente> pacientes) {
		
		for (Paciente paciente : pacientes) {
			System.out.println(paciente.toString());
		}
	}

	public void exibirMedico(Medico medico) {
		System.out.println("Médico");
		System.out.println("         " + medico.toString());
		System.out.println("         Endereço: " + medico.getEndereco());		
	}
	
	public void exibirMedicos(List<Medico> medicos) {
		
		for (Medico medico : medicos) {
			System.out.println(medico.toString());
		}
	}

	public void exibirConsulta(Consulta consulta) {

		SimpleDateFormat formater = new SimpleDateFormat("dd/mm/yyyy");
		System.out.println("Consulta");
		System.out.println("         Paciente: " + consulta.getPaciente());
		System.out.println("         Médico: " + consulta.getMedico());	
		System.out.println("         Data: " + formater.format(consulta.getData()));
		System.out.println("         Código: " + consulta.getCodigo());
	}
	
	public void exibirConsultas(List<Consulta> consultasListagem) {

	    SimpleDateFormat formater = new SimpleDateFormat("dd/mm/yyyy");
		for (Consulta consulta : consultasListagem) {
			System.out.println("Consulta");
			System.out.println("         Paciente: " + consulta.getPaciente());
			System.out.println("         Médico: " + consulta.getMedico());	
			System.out.println("         Data: " + formater.format(consulta.getData()));
			System.out.println("         Código: " + consulta.getCodigo());
		}
	}

	public void exibirFaringoplastia(Faringoplastia faringoplastia) {

		SimpleDateFormat formater = new SimpleDateFormat("dd/mm/yyyy");
		System.out.println("Faringoplastia");
		System.out.println("         Paciente: " + faringoplastia.getPaciente());
		System.out.println("         Médico: " + faringoplastia.getMedicos());	
		System.out.println("         Data: " + formater.format(faringoplastia.getData()));
		System.out.println("         Código: " + faringoplastia.getCodigo());
	}

	public void exibirNeurocirurgia(Neurocirurgia neurocirurgia) {
		
		SimpleDateFormat formater = new SimpleDateFormat("dd/mm/yyyy");
		System.out.println("Neurocirurgia");
		System.out.println("         Paciente: " + neurocirurgia.getPaciente());
		System.out.println("         Médico: " + neurocirurgia.getMedicos());	
		System.out.println("         Data: " + formater.format(neurocirurgia.getData()));
		System.out.println("         Código: " + neurocirurgia.getCodigo());		
	}

	public void exibirProcedimentos(List<Procedimento> procedimentos) {

		SimpleDateFormat formater = new SimpleDateFormat("dd/mm/yyyy");
		for (Procedimento procedimento : procedimentos) {
			System.out.println(procedimento.getClass().getSimpleName());
			System.out.println("         Paciente: " + procedimento.getPaciente());
			System.out.println("         Médico: " + procedimento.getMedicos());	
			System.out.println("         Data: " + formater.format(procedimento.getData()));
			System.out.println("         Código: " + procedimento.getCodigo());
		}
	}	
}
