package br.com.casaDoCodigo.livroOO.util;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.casaDoCodigo.livroOO.entidades.Endereco;
import br.com.casaDoCodigo.livroOO.entidades.Especialidade;
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
		
		System.out.println("Digite o CPF: ");
		String cpf = scanner.nextLine();
				
		System.out.println("Digite o nome: ");
		String nome = scanner.nextLine();
		
		System.out.println("Digite o endereço:(separado por virgula sem espaço entre virgula. Ex: Rua 1 2 3 de oliveira 4,n 20,centro,60529837)");
		String endereco = scanner.nextLine();
		Endereco enderecoConvertido =ConversoraEndereco.converterEndereco(endereco);
		
		System.out.println("Digite a data de nascimento(dd/mm/aaaa): ");
		String data = scanner.nextLine();
		Date dataConvertida = ConversoraData.converterData(data);
		
		System.out.println("Digite o nome do plano e sua mensalidade separados por virgula(valores separador por .): ");
		String plano = scanner.nextLine();
		Plano plan = new Plano();
		plan.setNome(plano.split(",")[0]);
		plan.setMensalidade(Double.parseDouble(plano.split(",")[1]));
		
		Paciente paciente = new Paciente(nome, dataConvertida, enderecoConvertido, cpf, plan);
		
		return paciente;
	}
	
	public Paciente lerPacienteAlteracao(String cpf) {
		
		
		System.out.println("Digite o nome: ");
		String nome = scanner.nextLine();
		
		System.out.println("Digite o endereço:(separado por virgula. Ex: Rua 1 2 3 de oliveira 4, n 20, centro, 60529837)");
		String endereco = scanner.nextLine();
		Endereco enderecoConvertido = ConversoraEndereco.converterEndereco(endereco);
		
		Paciente paciente = new Paciente(cpf, nome, enderecoConvertido);
		
		return paciente;
	}

	public Paciente nomeDataPaciente() {

		System.out.println("Digite o nome: ");
		String nome = scanner.nextLine();
		
		System.out.println("Digite a data de nascimento(dd/mm/aaaa): ");
		String data = scanner.nextLine();
		Date dataConvertida = ConversoraData.converterData(data);
		
		Paciente paciente = new Paciente(nome, dataConvertida);
		
		return paciente;
	}

	public Medico lerNovoMedico() {
		
		System.out.println("Digite o CRM: ");
		String crm = scanner.nextLine();
		int crmConvertido = Integer.parseInt(crm);
				
		System.out.println("Digite o nome: ");
		String nome = scanner.nextLine();
		
		System.out.println("Digite o endereço:(separado por virgula sem espaço entre virgula. Ex: Rua 1 2 3 de oliveira 4,n 20,centro,60529837)");
		String endereco = scanner.nextLine();
		Endereco enderecoConvertido = ConversoraEndereco.converterEndereco(endereco);
		
		System.out.println("Digite a data de nascimento(dd/mm/aaaa): ");
		String data = scanner.nextLine();
		Date dataConvertida = ConversoraData.converterData(data);
		
		System.out.println("Digite o valor da hora(separado por ponto): ");
		String valor = scanner.nextLine();
		double valorHora  = Double.parseDouble(valor);
		
		System.out.println("Digite até 3 especialidades(somente o nome e separadas por virgula): ");
		String especialidades = scanner.nextLine();
		List<Especialidade> especialidadesConvertidas = ConversoraEspecialidade.converterEspecialidades(especialidades);
		
		Medico medico = new Medico(nome, crmConvertido, enderecoConvertido, dataConvertida, valorHora, especialidadesConvertidas);
		
		return medico;
	}
	
	public Medico lerMedicoAlteracao(String crm) {
		
		
		System.out.println("Digite o nome: ");
		String nome = scanner.nextLine();
		
		System.out.println("Digite o endereço:(separado por virgula. Ex: Rua 1 2 3 de oliveira 4, n 20, centro, 60529837)");
		String endereco = scanner.nextLine();
		Endereco enderecoConvertido = ConversoraEndereco.converterEndereco(endereco);
		
		System.out.println("Digite o valor da hora(separado por ponto): ");
		String valor = scanner.nextLine();
		double valorHora = Double.parseDouble(valor);
		
		int crmConvertido = Integer.parseInt(crm);
		
		Medico medico = new Medico(nome, crmConvertido, enderecoConvertido, valorHora);
		
		return medico;
	}
	
	@Override
	protected void finalize() throws Throwable {

		scanner.close();
	}

}
