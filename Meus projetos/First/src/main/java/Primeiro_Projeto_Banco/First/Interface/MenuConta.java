package Primeiro_Projeto_Banco.First.Interface;

import Primeiro_Projeto_Banco.First.Entidades.Conta.Conta;

public class MenuConta {

    Conta conta = new Conta();

    public void exibirMenuConta(){
        System.out.println("------MENU------");
        System.out.println("1. Depositar");
        System.out.println("-------------");
        System.out.println("2. Saldo");
        System.out.println("-------------");
        System.out.println("3. Sacar");
        System.out.println("-------------");

        String menu = """
                ------MENU------
                Selecione a opção desejada.
                1. Depositar
                2. Saldo
                3. Sacar
                4. Transferir
                5. Dados da conta
                """;

        boolean choose = true;
        while (choose){
            System.out.println(menu);
            switch (menu){
                case 1:
                    conta.depositar();
                    break;
                case 2:
                    conta.getSaldo();
                    break;
                case 3:
                    conta.sacar();
                    break;
                case 4:
                    conta.transferir();
                    break;
                case 5:
                    conta.dadosConta();
                    break;
                case 0:
                    choose = false;
                    break;
                    default:
                        System.out.println();
            }
        }
    }
}
