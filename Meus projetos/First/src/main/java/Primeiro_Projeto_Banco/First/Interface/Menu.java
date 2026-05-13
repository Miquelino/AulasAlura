package Primeiro_Projeto_Banco.First.Interface;

import Primeiro_Projeto_Banco.First.Entidades.Cliente.Cliente;
import Primeiro_Projeto_Banco.First.Entidades.Cliente.ClienteDTO;
import Primeiro_Projeto_Banco.First.Entidades.Endereco.Endereco;
import Primeiro_Projeto_Banco.First.Entidades.Endereco.EnderecoDTO;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    Cliente cliente = new Cliente();

    public void exibirEntrada(){
        String menu = """
                Seja bem vindo ao Banco Master!
                Selecione a opção desejada.
                1. Entrar na conta.
                2. Cadastrar Conta.
                0. Fechar APP.
                """;
        boolean choose = true;
        while (choose){
            System.out.println(menu);
            int opcao = scanner.nextInt();

            switch (opcao){
                case 1:
                    entrarConta();
                    break;
                case 2:
                    cadastrarConta();
                break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    choose = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void entrarConta(){
        System.out.println("Digite o CPF");
        int cpf = scanner.nextInt();
        System.out.println("Digite a Senha");
        String senha = scanner.next();
        try{

        } catch (Exception e){
            System.out.println("Erro ao tentar entrar com o CPF!");
        }
    }

    public void cadastrarConta(){
        System.out.println("Nome:");
        String nome = scanner.nextLine();

        System.out.println("CPF:");
        String cpf = scanner.nextLine();

        System.out.println("Email:");
        String email = scanner.nextLine();

        System.out.println("Telefone:");
        String telefone = scanner.nextLine();

        System.out.println("Depósito inicial:");
        double deposito = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Tipo de conta (1 = Corrente, 2 = Poupança):");
        int tipoConta = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nome da rua:");
        String rua = scanner.nextLine();

        System.out.println("Bairro:");
        String bairro = scanner.nextLine();

        System.out.println("Numero:");
        int numero = scanner.nextInt();

        System.out.println("CEP:");
        int cep = scanner.nextInt();

        System.out.println("Cidade:");
        String cidade = scanner.nextLine();
        scanner.nextLine();

        System.out.println("Estado:");
        String estado = scanner.nextLine();
        scanner.nextLine();

        EnderecoDTO enderecoDTO = new EnderecoDTO(rua, bairro, numero, cep, cidade, estado);

        ClienteDTO dto = new ClienteDTO(
                        nome, cpf, email, tipoConta, telefone, deposito, enderecoDTO
                );

        int numeroContaGerado = new Random().nextInt(
                9000) + 1000;

        cliente.abrirConta(dto, numeroContaGerado);

        System.out.println("Conta criada com sucesso!");
        System.out.println("Número da conta: " + numeroContaGerado);
    }
}
