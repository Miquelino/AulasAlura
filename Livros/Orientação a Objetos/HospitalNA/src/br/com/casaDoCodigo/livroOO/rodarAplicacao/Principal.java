package br.com.casaDoCodigo.livroOO.rodarAplicacao;

import java.util.ArrayList;
import java.util.List;

import br.com.casaDoCodigo.livroOO.entidades.Consulta;
import br.com.casaDoCodigo.livroOO.entidades.Faringoplastia;
import br.com.casaDoCodigo.livroOO.entidades.Medico;
import br.com.casaDoCodigo.livroOO.entidades.Neurocirurgia;
import br.com.casaDoCodigo.livroOO.entidades.Paciente;
import br.com.casaDoCodigo.livroOO.entidades.Procedimento;
import br.com.casaDoCodigo.livroOO.integracaoMinisterio.DemonstrativoMinisterio;
import br.com.casaDoCodigo.livroOO.util.ConversoraData;
import br.com.casaDoCodigo.livroOO.util.ExibidoraDados;
import br.com.casaDoCodigo.livroOO.util.LeitoraDados;

public class Principal {

	public static void main(String[] args) {
		
		System.out.print("Bem vindo ao Sistema Hospitalar!");
		System.out.print("\n");
		
		DemonstrativoMinisterio demonstrativoMinisterio = new DemonstrativoMinisterio(); 
		LeitoraDados leitoraDados = new LeitoraDados();
		ExibidoraDados exibidoraDados = new ExibidoraDados();
		
		String opcao = "";
		
		while (!opcao.equals("22")) {
			
			System.out.println("Selecione um número:");
			System.out.println("1 - Cadastrar paciente");
			System.out.println("2 - Alterar paciente");
			System.out.println("3 - Excluir paciente");
			System.out.println("4 - Consultar paciente pelo CPF");
			System.out.println("5 - Consultar paciente por nome e data de nascimento");
			System.out.println("6 - Listar pacientes");
			
			System.out.println("7 - Cadastar médico");
			System.out.println("8 - Alterar médico");
			System.out.println("9 - Excluir médico");
			System.out.println("10 - Consultar médico por CRM");
			System.out.println("11 - Consular médico por nome");
			System.out.println("12 - Listar médicos");
			
			System.out.println("13 - Marcar consulta");
			System.out.println("14 - Cancelar consulta");
			System.out.println("15 - Pesquisar consulta por paciente");
			System.out.println("16 - Listar consultas");
			
			System.out.println("17 - Marcar procedimento");
			System.out.println("18 - Cancelar procedimento");
			System.out.println("19 - Pesquisar procedimento por médico");
			System.out.println("20 - Listar procedimentos");
			
			System.out.println("21 - Gerar Demonstrativo Ministério");
			
			System.out.println("22 - Sair");
		
			opcao = leitoraDados.lerTexto();
			
			switch (opcao) {
			case "1":
				Paciente novoPaciente = leitoraDados.lerNovoPaciente();
				novoPaciente.cadastrar();
				break;
			case "2":
				System.out.println("Digite o CPF  do Paciente a ser alterado:");
				String cpfAlterar = leitoraDados.lerTexto();
				Paciente pacienteAlterar = new Paciente(cpfAlterar); 
				Paciente pacienteAlterarCPF = pacienteAlterar.consultarPorCPF();
				exibidoraDados.exibirPaciente(pacienteAlterarCPF);
				System.out.println("Deseja realmente alterar? S/N");
				String alterar = leitoraDados.lerTexto();
				if ("S".equalsIgnoreCase(alterar)) {
					
					Paciente pacienteAlterado = leitoraDados.lerPacienteAlteracao(cpfAlterar);
					pacienteAlterado.alterar();
				}
				break;
			case "3":
				System.out.println("Digite o CPF do Paciente a ser excluido:");
				String cpfExcluir = leitoraDados.lerTexto();
				Paciente pacienteExcluir = new Paciente(cpfExcluir);
				Paciente pacienteExcluirCPF = pacienteExcluir.consultarPorCPF();
				pacienteExcluirCPF.excluir();
				break;
			case "4":
				System.out.println("Digite o CPF  do Paciente:");
				String cpfConsultar = leitoraDados.lerTexto();
				Paciente pacienteConsultar = new Paciente(cpfConsultar);
				Paciente pacienteConsultaCPF = pacienteConsultar.consultarPorCPF();
				exibidoraDados.exibirPaciente(pacienteConsultaCPF);				
				break;
			case "5":
				Paciente pacienteNomeData = leitoraDados.nomeDataPaciente();
				List<Paciente> pacientes = pacienteNomeData.consultarPorNomeDataNascimento();
				exibidoraDados.exibirPacientes(pacientes);				
				break;
			case "6":
				List<Paciente> pacientesListagem = new Paciente().listarTodos();
				exibidoraDados.exibirPacientes(pacientesListagem);
				break;
			case "7":
				Medico novoMedico = leitoraDados.lerNovoMedico();
				novoMedico.cadastrar();				
				break;
			case "8":
				System.out.println("Digite o CRM  do médico a ser alterado:");
				String crmAlterar = leitoraDados.lerTexto();
				Medico medicoAlterar = new Medico(Integer.parseInt(crmAlterar));
				medicoAlterar = medicoAlterar.consultarPorCRM();
				exibidoraDados.exibirMedico(medicoAlterar);
				System.out.println("Deseja realmente alterar? S/N");
				alterar = leitoraDados.lerTexto();
				if ("S".equalsIgnoreCase(alterar)) {
					
					Medico medicoAlterado = leitoraDados.lerMedicoAlteracao(crmAlterar);
					medicoAlterado.alterar();
				}				
				break;
			case "9":
				System.out.println("Digite o CRM do Médico a ser excluido:");
				String crmExcluir = leitoraDados.lerTexto();
				Medico medicoExcluir = new Medico(Integer.parseInt(crmExcluir));
				medicoExcluir = medicoExcluir.consultarPorCRM();
				medicoExcluir.excluir();				
				break;
			case "10":
				System.out.println("Digite o CRM  do Médico:");
				String crmConsultar = leitoraDados.lerTexto();
				Medico medicoConsultar = new Medico(Integer.parseInt(crmConsultar));
				medicoConsultar = medicoConsultar.consultarPorCRM();
				exibidoraDados.exibirMedico(medicoConsultar);				
				break;
			case "11":
				System.out.println("Digite o nome do Médico:");
				String nome = leitoraDados.lerTexto();
				Medico medicoConsultarNome = new Medico(nome);
				List<Medico> medicos = medicoConsultarNome.consultarPorNome();
				exibidoraDados.exibirMedicos(medicos);				
				break;
			case "12":
				Medico medicoListar = new Medico();
				List<Medico> medicosListagem = medicoListar.listarTodos();
				exibidoraDados.exibirMedicos(medicosListagem);				
				break;				
			case "13":
				System.out.println("Digite o CRM  do Médico:");
				String crmConsulta  = leitoraDados.lerTexto();
				System.out.println("Digite o CPF  do Paciente:");
                String cpfConsulta  = leitoraDados.lerTexto();
                Medico medicoConsulta = new Medico(Integer.parseInt(crmConsulta));
                Paciente pacienteConsulta = new Paciente(cpfConsulta);
                medicoConsulta = medicoConsulta.consultarPorCRM();
                pacienteConsulta = pacienteConsulta.consultarPorCPF();
                System.out.println("Digite a data da consulta(dd/mm/aaaa): ");
        		String dataConsulta = leitoraDados.lerTexto();
        		Consulta consulta = new Consulta();
                consulta.marcar(pacienteConsulta, medicoConsulta, ConversoraData.converterData(dataConsulta));
				break;
			case "14":
				System.out.println("Digite o código da consulta:");
				String codigoConsulta = leitoraDados.lerTexto();
				Consulta consultaDesmarcar = new Consulta(Integer.parseInt(codigoConsulta));
				consultaDesmarcar = consultaDesmarcar.consultar();
				exibidoraDados.exibirConsulta(consultaDesmarcar);
				System.out.println("Deseja realmente desmarcar? S/N");
				String desmarcar = leitoraDados.lerTexto();
				if ("S".equalsIgnoreCase(desmarcar)) {
					consultaDesmarcar.cancelar();
				}			
				break;
			case "15":
				System.out.println("Digite o CPF do paciente da consulta:");
                String cpfPacienteConsulta = leitoraDados.lerTexto();
                Paciente pacConsulta = new Paciente(cpfPacienteConsulta);
                Consulta consultaPorPaciente = new Consulta(pacConsulta);
                List<Consulta> consultasListagemPesquisa = consultaPorPaciente.pesquisarPorPaciente();
                exibidoraDados.exibirConsultas(consultasListagemPesquisa);
				break;
			case "16":
				List<Consulta> consultasListagem = new Consulta().listarTodos();
				exibidoraDados.exibirConsultas(consultasListagem);				
				break;
			case "17":
				System.out.println("Digite o CRM de 2 Médicos(separado por vírgula):");
				String crmProcedimento  = leitoraDados.lerTexto();
				System.out.println("Digite o CPF  do Paciente:");
                String cpfProcedimento = leitoraDados.lerTexto();
                
                String[] crms = crmProcedimento.split(",");
                List<Medico> medicosProcedimento = new ArrayList<>();
                for (String crm : crms) {
					
                	Medico medicoProcedimento = new Medico(Integer.parseInt(crm)).consultarPorCRM();
                	medicosProcedimento.add(medicoProcedimento);
				}
                Paciente pacienteProcedimento = new Paciente(cpfProcedimento).consultarPorCPF();
                System.out.println("Digite a data do procedimento(dd/mm/aaaa): ");
        		String dataProcedimento = leitoraDados.lerTexto();
        		System.out.println("Digite N para Neurologia e F para Faringoplastia: ");
        		String procedimento = leitoraDados.lerTexto();
        		if ("F".equalsIgnoreCase(procedimento)) {
        			new Faringoplastia().marcar(medicosProcedimento, pacienteProcedimento, ConversoraData.converterData(dataProcedimento));				
        		} else {
        			new Neurocirurgia().marcar(medicosProcedimento, pacienteProcedimento, ConversoraData.converterData(dataProcedimento));
        		}
				break;
			case "18":
				System.out.println("Digite o código do procedimento e N para Neurologia e F para Faringoplastia(separado por virgula) para cancelar:");
				String entrada = leitoraDados.lerTexto();
				String codigo = entrada.split(",")[0];
				String tipo = entrada.split(",")[1];				
				if ("F".equalsIgnoreCase(tipo)) {
					
					Faringoplastia faringoplastia = new Faringoplastia(Integer.parseInt(codigo)).consultar();
										
					exibidoraDados.exibirFaringoplastia(faringoplastia);
					System.out.println("Deseja realmente desmarcar? S/N");
					String desmarcarFaringoplastia = leitoraDados.lerTexto();
					if ("S".equalsIgnoreCase(desmarcarFaringoplastia)) {
						faringoplastia.cancelar();				
					}						
				} else {
					
					Neurocirurgia neurocirurgia = new Neurocirurgia(Integer.parseInt(codigo)).consultar();
					
					exibidoraDados.exibirNeurocirurgia(neurocirurgia);
					System.out.println("Deseja realmente desmarcar? S/N");
					String desmarcarNeurocirurgia = leitoraDados.lerTexto();
					if ("S".equalsIgnoreCase(desmarcarNeurocirurgia)) {
						neurocirurgia.cancelar();
					}						
        		}
				break;
			case "19":
				System.out.println("Digite o CRM dos Médicos(separado por vírgula):");
				String crmsMedicos  = leitoraDados.lerTexto();
                String[] crmsProcedimentos = crmsMedicos.split(",");
                
                List<Procedimento> procedimentos = new ArrayList<>();
                for (String crm : crmsProcedimentos) {
					Medico medico = new Medico(Integer.parseInt(crm)).consultarPorCRM();
					procedimentos.addAll(new Faringoplastia().pesquisarPorMedico(medico));
					procedimentos.addAll(new Neurocirurgia().pesquisarPorMedico(medico));
				}
                exibidoraDados.exibirProcedimentos(procedimentos);
				break;
			case "20":
				List<Procedimento> procedimentosListagem = new ArrayList<>();
				procedimentosListagem.addAll(new Faringoplastia().listarTodos());
				procedimentosListagem.addAll(new Neurocirurgia().listarTodos());
				exibidoraDados.exibirProcedimentos(procedimentosListagem);				
				break;				
			case "21":
				List<Procedimento> procedimentosDemonstrativo = new ArrayList<>();
				procedimentosDemonstrativo.addAll(new Faringoplastia().listarTodos());
				procedimentosDemonstrativo.addAll(new Neurocirurgia().listarTodos());
			    demonstrativoMinisterio.gerarDados(procedimentosDemonstrativo);
				break;				
			default:
				System.out.println("Volte sempre!");
				System.exit(0);
				break;
			}
		
		}
	}

}
