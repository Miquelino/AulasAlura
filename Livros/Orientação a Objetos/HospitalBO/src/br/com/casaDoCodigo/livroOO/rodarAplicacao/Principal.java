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
import br.com.casaDoCodigo.livroOO.negocio.ConsultaNegocio;
import br.com.casaDoCodigo.livroOO.negocio.FaringoplastiaNegocio;
import br.com.casaDoCodigo.livroOO.negocio.MedicoNegocio;
import br.com.casaDoCodigo.livroOO.negocio.NeurocirurgiaNegocio;
import br.com.casaDoCodigo.livroOO.negocio.PacienteNegocio;
import br.com.casaDoCodigo.livroOO.util.ConversoraData;
import br.com.casaDoCodigo.livroOO.util.ExibidoraDados;
import br.com.casaDoCodigo.livroOO.util.LeitoraDados;

public class Principal {

	public static void main(String[] args) {
		
		System.out.print("Bem vindo ao Sistema Hospitalar!");
		System.out.print("\n");
		
		PacienteNegocio pacienteNegocio = new PacienteNegocio();
		MedicoNegocio medicoNegocio = new MedicoNegocio();
		ConsultaNegocio consultaNegocio = new ConsultaNegocio();
		FaringoplastiaNegocio faringoplastiaNegocio = new FaringoplastiaNegocio();
		NeurocirurgiaNegocio neurocirurgiaNegocio = new NeurocirurgiaNegocio();
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
				pacienteNegocio.cadastrar(novoPaciente);
				break;
			case "2":
				System.out.println("Digite o CPF  do Paciente a ser alterado:");
				String cpfAlterar = leitoraDados.lerTexto();
				Paciente pacienteAlterar = pacienteNegocio.consultar(cpfAlterar);
				exibidoraDados.exibirPaciente(pacienteAlterar);
				System.out.println("Deseja realmente alterar? S/N");
				String alterar = leitoraDados.lerTexto();
				if ("S".equalsIgnoreCase(alterar)) {
					
					Paciente pacienteAlterado = leitoraDados.lerPacienteAlteracao(cpfAlterar);
					pacienteNegocio.alterar(pacienteAlterado);
				}
				break;
			case "3":
				System.out.println("Digite o CPF do Paciente a ser excluido:");
				String cpfExcluir = leitoraDados.lerTexto();
				Paciente pacienteExcluir = pacienteNegocio.consultar(cpfExcluir);
				pacienteNegocio.excluir(pacienteExcluir);
				break;
			case "4":
				System.out.println("Digite o CPF  do Paciente:");
				String cpfConsultar = leitoraDados.lerTexto();
				Paciente pacienteConsultar = pacienteNegocio.consultar(cpfConsultar);
				exibidoraDados.exibirPaciente(pacienteConsultar);				
				break;
			case "5":
				Paciente paciente = leitoraDados.nomeDataPaciente();
				List<Paciente> pacientes = pacienteNegocio.consultar(paciente.getNome(), paciente.getDataNascimento());
				exibidoraDados.exibirPacientes(pacientes);				
				break;
			case "6":
				List<Paciente> pacientesListagem = pacienteNegocio.listarTodos();
				exibidoraDados.exibirPacientes(pacientesListagem);
				break;
			case "7":
				Medico novoMedico = leitoraDados.lerNovoMedico();
				medicoNegocio.cadastrar(novoMedico);				
				break;
			case "8":
				System.out.println("Digite o CRM  do médico a ser alterado:");
				String crmAlterar = leitoraDados.lerTexto();
				Medico medicoAlterar = medicoNegocio.consultar(Integer.parseInt(crmAlterar));
				exibidoraDados.exibirMedico(medicoAlterar);
				System.out.println("Deseja realmente alterar? S/N");
				alterar = leitoraDados.lerTexto();
				if ("S".equalsIgnoreCase(alterar)) {
					
					Medico medicoAlterado = leitoraDados.lerMedicoAlteracao(crmAlterar);
					medicoNegocio.alterar(medicoAlterado);
				}				
				break;
			case "9":
				System.out.println("Digite o CRM do Médico a ser excluido:");
				String crmExcluir = leitoraDados.lerTexto();
				Medico medicoExcluir = medicoNegocio.consultar(Integer.parseInt(crmExcluir));
				medicoNegocio.excluir(medicoExcluir);				
				break;
			case "10":
				System.out.println("Digite o CRM  do Médico:");
				String crmConsultar = leitoraDados.lerTexto();
				Medico medicoConsultar = medicoNegocio.consultar(Integer.parseInt(crmConsultar));
				exibidoraDados.exibirMedico(medicoConsultar);				
				break;
			case "11":
				System.out.println("Digite o nome do Médico:");
				String nome = leitoraDados.lerTexto();
				List<Medico> medicos = medicoNegocio.consultar(nome);
				exibidoraDados.exibirMedicos(medicos);				
				break;
			case "12":
				List<Medico> medicosListagem = medicoNegocio.listarTodos();
				exibidoraDados.exibirMedicos(medicosListagem);				
				break;				
			case "13":
				System.out.println("Digite o CRM  do Médico:");
				String crmConsulta  = leitoraDados.lerTexto();
				System.out.println("Digite o CPF  do Paciente:");
                String cpfConsulta  = leitoraDados.lerTexto();
                Medico medicoConsulta = medicoNegocio.consultar(Integer.parseInt(crmConsulta ));
                Paciente pacienteConsulta = pacienteNegocio.consultar(cpfConsulta);
                System.out.println("Digite a data da consulta(dd/mm/aaaa): ");
        		String dataConsulta = leitoraDados.lerTexto();
                consultaNegocio.marcar(pacienteConsulta, medicoConsulta, ConversoraData.converterData(dataConsulta));
				break;
			case "14":
				System.out.println("Digite o código da consulta:");
				String codigoConsulta = leitoraDados.lerTexto();
				Consulta consultaDesmarcar = consultaNegocio.consultar(Integer.parseInt((codigoConsulta)));
				exibidoraDados.exibirConsulta(consultaDesmarcar);
				System.out.println("Deseja realmente desmarcar? S/N");
				String desmarcar = leitoraDados.lerTexto();
				if ("S".equalsIgnoreCase(desmarcar)) {
					consultaNegocio.cancelar(consultaDesmarcar);
				}			
				break;
			case "15":
				System.out.println("Digite o CPF do paciente da consulta:");
                String cpfPacienteConsulta = leitoraDados.lerTexto();
                Paciente pacConsulta = new Paciente();
                pacConsulta.setCpf(cpfPacienteConsulta);
                List<Consulta> consultasListagemPesquisa = consultaNegocio.pesquisarPorPaciente(pacConsulta);
                exibidoraDados.exibirConsultas(consultasListagemPesquisa);
				break;
			case "16":
				List<Consulta> consultasListagem = consultaNegocio.listarTodos();
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
					
                	Medico medicoProcedimento = medicoNegocio.consultar(Integer.parseInt(crm));
                	medicosProcedimento.add(medicoProcedimento);
				}
                Paciente pacienteProcedimento = pacienteNegocio.consultar(cpfProcedimento);
                System.out.println("Digite a data do procedimento(dd/mm/aaaa): ");
        		String dataProcedimento = leitoraDados.lerTexto();
        		System.out.println("Digite N para Neurologia e F para Faringoplastia: ");
        		String procedimento = leitoraDados.lerTexto();
        		if ("F".equalsIgnoreCase(procedimento)) {
        			faringoplastiaNegocio.marcar(medicosProcedimento, pacienteProcedimento, ConversoraData.converterData(dataProcedimento));				
        		} else {
        			neurocirurgiaNegocio.marcar(medicosProcedimento, pacienteProcedimento, ConversoraData.converterData(dataProcedimento));
        		}
				break;
			case "18":
				System.out.println("Digite o código do procedimento e N para Neurologia e F para Faringoplastia(separado por virgula) para cancelar:");
				String entrada = leitoraDados.lerTexto();
				String codigo = entrada.split(",")[0];
				String tipo = entrada.split(",")[1];				
				if ("F".equalsIgnoreCase(tipo)) {
					
					Faringoplastia faringoplastia = faringoplastiaNegocio.consultar(Integer.parseInt(codigo));
										
					exibidoraDados.exibirFaringoplastia(faringoplastia);
					System.out.println("Deseja realmente desmarcar? S/N");
					String desmarcarFaringoplastia = leitoraDados.lerTexto();
					if ("S".equalsIgnoreCase(desmarcarFaringoplastia)) {
						faringoplastiaNegocio.cancelar(faringoplastia);				
					}						
				} else {
					
					Neurocirurgia neurocirurgia = neurocirurgiaNegocio.consultar(Integer.parseInt(codigo));
					
					exibidoraDados.exibirNeurocirurgia(neurocirurgia);
					System.out.println("Deseja realmente desmarcar? S/N");
					String desmarcarNeurocirurgia = leitoraDados.lerTexto();
					if ("S".equalsIgnoreCase(desmarcarNeurocirurgia)) {
						neurocirurgiaNegocio.cancelar(neurocirurgia);
					}						
        		}
				break;
			case "19":
				System.out.println("Digite o CRM dos Médicos(separado por vírgula):");
				String crmsMedicos  = leitoraDados.lerTexto();
                String[] crmsProcedimentos = crmsMedicos.split(",");
                
                List<Procedimento> procedimentos = new ArrayList<>();
                for (String crm : crmsProcedimentos) {
					Medico medico = medicoNegocio.consultar(Integer.parseInt(crm));
					procedimentos.addAll(faringoplastiaNegocio.pesquisarPorMedico(medico));
					procedimentos.addAll(neurocirurgiaNegocio.pesquisarPorMedico(medico));
				}
                exibidoraDados.exibirProcedimentos(procedimentos);
				break;
			case "20":
				List<Procedimento> procedimentosListagem = new ArrayList<>();
				procedimentosListagem.addAll(faringoplastiaNegocio.listarTodos());
				procedimentosListagem.addAll(neurocirurgiaNegocio.listarTodos());
				exibidoraDados.exibirProcedimentos(procedimentosListagem);				
				break;	
			case "21":
				List<Procedimento> procedimentosDemonstrativo = new ArrayList<>();
				procedimentosDemonstrativo.addAll(faringoplastiaNegocio.listarTodos());
				procedimentosDemonstrativo.addAll(neurocirurgiaNegocio.listarTodos());
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
