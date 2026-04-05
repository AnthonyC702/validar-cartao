package model;

import java.util.ArrayList;
import java.util.List;

public class Historico {
    private final List<Cartao> cartoes;
    private int suspeitos;
    private int validos;

    public Historico() {
        this.cartoes = new ArrayList<>();
        this.suspeitos = 0;
        this.validos = 0;
    }

    // Adiciona cartão ao histórico
    public void adicionar(Cartao cartao) {
        cartoes.add(cartao);
    }

    // FOR-EACH - Exibe histórico completo
    public void exibir() {
        if (cartoes.isEmpty()) {
            System.out.println("\n📝 Histórico vazio!");
            return;
        }

        System.out.println("\n📋 HISTÓRICO (" + cartoes.size() + " cartões)");
        System.out.println("====================================");

        for (Cartao cartao : cartoes) {
            System.out.println("- " + cartao);
        }
        System.out.println();
    }

    // FOR-EACH - Estatísticas
    public void exibirEstatisticas() {
        if (cartoes.isEmpty()) {
            System.out.println("\n📊 Sem dados para estatísticas!");
            return;
        }

        suspeitos = 0;
        validos = 0;

        // FOR-EACH para contar estatísticas
        for (Cartao cartao : cartoes) {
            // Simula validação (na prática, viria do Validador)
            if (cartao.getNumeroLimpo().matches(".*(1111|2222|1234).*")) {
                suspeitos++;
            } else {
                validos++;
            }
        }

        System.out.println("\n📊 ESTATÍSTICAS");
        System.out.println("================");
        System.out.println("Total: " + cartoes.size());
        System.out.println("✅ Válidos: " + validos);
        System.out.println("🚨 Suspeitos: " + suspeitos);
        System.out.printf("📈 Taxa suspeita: %.1f%%\n",
                (double) suspeitos / cartoes.size() * 100);
    }
}