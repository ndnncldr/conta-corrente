import java.util.Scanner;

public class ContaCorrente {

    //Inicializar dados do cliente
    public static String nomeCliente = "Paola Lacerda";
    public static String tipoConta = "Conta corrente";
    public static double saldoInicial = 2500;
    public static double saldoAtual = saldoInicial;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String exibirMensagemOperacao;
        int opcao = 0;


        String exibirDadosCliente = """
                ********************************************
                Nome: %s
                Tipo da conta: %s
                Saldo inicial: R$%.2f
                ********************************************
                """.formatted(nomeCliente, tipoConta, saldoInicial);
        System.out.println(exibirDadosCliente);

        while (opcao != 4) {

            String exibirMenu = """
                                    
                    Bom dia, %s, as seguintes operações estão disponíveis para sua conta.
                                    
                    1 - Consultar saldo
                    2 - Depositar valor
                    3 - Sacar valor
                    4 - Sair
                    """.formatted(nomeCliente);
            System.out.println(exibirMenu);
            System.out.println("Digite uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Consultar saldo");
                    System.out.printf("O saldo atual da sua conta é de R$%.2f", saldoAtual);
                    break;
                case 2:
                    System.out.println(validarDeposito());
                    break;
                case 3:
                    System.out.println(validarSaque());
                    break;
                case 4:
                    System.out.println("Encerrando a sessão");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    public static String validarDeposito() {
        String exibirMensagemOperacao = "";
        Scanner sc = new Scanner(System.in);
        double valorDeposito = 0.00;

        while (valorDeposito <= 0.00) {
            System.out.println("Digite o valor a ser depositado: ");
            valorDeposito = sc.nextDouble();

            if (valorDeposito <= 0.00) {
                System.out.println("Valor para depósito inválido.");
            }
        }

        saldoAtual += valorDeposito;
        exibirMensagemOperacao = """
                            Depósito realizado com sucesso
                            Seu saldo atual é de R$%.2f
                            """.formatted(saldoAtual);

        return exibirMensagemOperacao;
    }

    public static String validarSaque() {
        String exibirMensagemOperacao = "";
        Scanner sc = new Scanner(System.in);
        double valorSaque = 0.00;


        while (valorSaque <= 0.00 || valorSaque > saldoAtual) {
            System.out.println("Digite o valor a ser sacado: ");
            valorSaque = sc.nextDouble();

            if (valorSaque <= 0.00 || valorSaque > saldoAtual) {
                System.out.println("Valor para saque inválido.");
            }
        }


        if (valorSaque <= 1000) {
            exibirMensagemOperacao = sacarDinheiro(valorSaque);
        } else if (valorSaque > 1000) {
            exibirMensagemOperacao = confirmarOperacao(valorSaque);
        }

        return exibirMensagemOperacao;
    }

    public static String confirmarOperacao(double valorSaque) {
        String exibirMensagemOperacao = "";
        System.out.printf("Você tem certeza que quer sacar R$%.2f? Digite sim para confirmar ou não para cancelar a operação: "
                , valorSaque);
        Scanner scConfirmacao = new Scanner(System.in);
        String confirmacaoCliente = scConfirmacao.nextLine();

        if (confirmacaoCliente.toUpperCase().equals("SIM")) {
            exibirMensagemOperacao = sacarDinheiro(valorSaque);
        } else {
            exibirMensagemOperacao = "Operação cancelada";
        }
        return exibirMensagemOperacao;
    }


    public static String sacarDinheiro(double valorSaque) {
        saldoAtual -= valorSaque;
        String exibirMensagemOperacao = """
                Saque realizado com sucesso
                Seu saldo atual é de R$%.2f
                """.formatted(saldoAtual);
        return exibirMensagemOperacao;
    }


}