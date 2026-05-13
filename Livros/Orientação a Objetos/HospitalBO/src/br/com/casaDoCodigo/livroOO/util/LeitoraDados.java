package br.com.casaDoCodigo.livroOO.util;

import java.util.Scanner;

import br.com.casaDoCodigo.livroOO.entidades.Medico;
import br.com.casaDoCodigo.livroOO.entidades.Paciente;
import br.com.casaDoCodigo.livroOO.entidades.Plano;

public final class LeitoraDados {

	private Scanner scanner;
	
	public LeitoraDados() {
		scanner = new Scanner(System.in);
	}
	
	public String lerTexto() {
		
		String texto = scanner.nextLine();
		
		return texto;
	}
	
	public Paciente lerNovoPaciente() {
		
		Paciente paciente = new Paciente();
		System.out.println("Digite o CPF: ");
		String cpf = scanner.nextLine();
		paciente.setCpf(cpf);
				
		System.out.println("Digite o nome: ");
		String nome = scanner.nextLine();
		paciente.setNome(nome);
		
		System.out.println("Digite o endereço:(separado por virgula sem espaço entre virgula. Ex: Rua 1 2 3 de oliveira 4,n 20,centro,60529837)");
		String endereco = scanner.nextLine();
		paciente.setEndereco(ConversoraEndereco.converterEndereco(endereco));
		
		System.out.println("Digite a data de nascimento(dd/mm/aaaa): ");
		String data = scanner.nextLine();
		paciente.setDataNascimento(ConversoraData.converterData(data));
		
		System.out.println("Digite o nome do plano e sua mensalidade separados por virgula(valores separador por .): ");
		String plano = scanner.nextLine();
		Plano plan = new Plano();
		plan.setNome(plano.split(",")[0]);
		plan.setMensalidade(Double.parseDouble(plano.split(",")[1]));
		paciente.setPlano(plan);
		
		return paciente;
	}
	
	public Paciente lerPacienteAlteracao(String cpf) {
		
		Paciente paciente = new Paciente();
		paciente.setCpf(cpf);
		
		System.out.println("Digite o nome: ");
		String nome = scanner.nextLine();
		paciente.setNome(nome);
		
		System.out.println("Digite o endereço:(separado por virgula. Ex: Rua 1 2 3 de oliveira 4, n 20, centro, 60529837)");
		String endereco = scanner.nextLine();
		paciente.setEndereco(ConversoraEndereco.converterEndereco(endereco));
		
		return paciente;
	}

	public Paciente nomeDataPaciente() {

		Paciente paciente = new Paciente();
		
		System.out.println("Digite o nome: ");
		String nome = scanner.nextLine();
		paciente.setNome(nome);
		
		System.out.println("Digite a data de nascimento(dd/mm/aaaa): ");
		String data = scanner.nextLine();
		paciente.setDataNascimento(ConversoraData.converterData(data));
		
		return paciente;
	}

	public Medico lerNovoMedico() {
		
		Medico medico = new Medico();
		System.out.println("Digite o CRM: ");
		String crm = scanner.nextLine();
		medico.setCrm(Integer.parseInt(crm));
				
		System.out.println("Digite o nome: ");
		String nome = scanner.nextLine();
		medico.setNome(nome);
		
		System.out.println("Digite o endereço:(separado por virgula sem espaço entre virgula. Ex: Rua 1 2 3 de oliveira 4,n 20,centro,60529837)");
		String endereco = scanner.nextLine();
		medico.setEndereco(ConversoraEndereco.converterEndereco(endereco));
		
		System.out.println("Digite a data de nascimento(dd/mm/aaaa): ");
		String data = scanner.nextLine();
		medico.setDataNascimento(ConversoraData.converterData(data));
		
		System.out.println("Digite o valor da hora(separado por ponto): ");
		String valor = scanner.nextLine();
		medico.setValorHora(Double.parseDouble(valor));
		
		System.out.println("Digite até 3 especialidades(somente o nome e separadas por virgula): ");
		String especialidades = scanner.nextLine();
		medico.setEspecialidades(ConversoraEspecialidade.converterEspecialidades(especialidades));
		
		return medico;
	}
	
	public Medico lerMedicoAlteracao(String crm) {
		
		Medico medico = new Medico();
		medico.setCrm(Integer.parseInt(crm));
		
		System.out.println("Digite o nome: ");
		String nome = scanner.nextLine();
		medico.setNome(nome);
		
		System.out.println("Digite o endereço:(separado por virgula. Ex: Rua 1 2 3 de oliveira 4, n 20, centro, 60529837)");
		String endereco = scanner.nextLine();
		medico.setEndereco(ConversoraEndereco.converterEndereco(endereco));
		
		System.out.println("Digite o valor da hora(separado por ponto): ");
		String valor = scanner.nextLine();
		medico.setValorHora(Double.parseDouble(valor));
		
		return medico;
	}
	
	@Override
	protected void finalize() throws Throwable {

		scanner.close();
	}

}
