package Main;

import model.Cartao;
import model.Historico;
import service.BancoService;
import service.ValidadorCartao;
import util.Formatador;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ValidadorCartao validador = new ValidadorCartao();
    private static final Historico historico = new Historico();
    private static final BancoService bancoService = new BancoService();

    public static void main(String[] args) {
        exibirCabecalho();

        // DO-WHILE + SWITCH - Menu interativo principal
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1 -> analisarNovoCartao();
                case 2 -> exibirHistorico();
                case 3 -> validarBanco();
                case 4 -> exibirEstatisticas();
                case 0 -> System.out.println("👋 Até logo!");
                default -> System.out.println("❌ Opção inválida!");
            }

            pausar();
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n📋 MENU PRINCIPAL");
        System.out.println("1 - Analisar novo cartão");
        System.out.println("2 - Ver histórico");
        System.out.println("3 - Validar banco emissor");
        System.out.println("4 - Estatísticas");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    // DO-WHILE para validar opção do menu
    private static int lerOpcao() {
        int opcao;
        do {
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                if (opcao < 0 || opcao > 4) {
                    System.out.print("❌ Digite entre 0-4: ");
                }
            } catch (NumberFormatException e) {
                opcao = -1;
                System.out.print("❌ Digite um número: ");
            }
        } while (opcao < 0 || opcao > 4);
        return opcao;
    }

    private static void analisarNovoCartao() {
        String numero = lerNumero();
        if (numero != null) {
            Cartao cartao = new Cartao(numero);
            validador.analisar(cartao);
            historico.adicionar(cartao);
        }
    }

    private static void exibirHistorico() {
        historico.exibir();
    }

    private static void validarBanco() {
        String numero = lerNumero();
        if (numero != null) {
            bancoService.identificarBanco(numero);
        }
    }

    private static void exibirEstatisticas() {
        historico.exibirEstatisticas();
    }

    private static String lerNumero() {
        System.out.print("Número do cartão (16 dígitos): ");
        String numero = scanner.nextLine().trim();

        if (!Formatador.isNumeroValido(numero)) {
            System.out.println("❌ Use exatamente 16 dígitos!\n");
            return null;
        }
        return numero;
    }

    private static void pausar() {
        System.out.print("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void exibirCabecalho() {
        System.out.println("🔍 SISTEMA PROFISSIONAL DE VERIFICAÇÃO");
        System.out.println("======================================");
    }
}