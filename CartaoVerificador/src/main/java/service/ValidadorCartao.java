package service;
import model.Cartao;
public class ValidadorCartao {


    public void analisar(Cartao cartao) {
        System.out.println("\n📊 ANALISANDO: " + formatar(cartao));
        System.out.println("=====================================");

        // FOR SIMPLES - Repetição total
        if (temRepeticaoTotal(cartao)) {
            System.out.println("🚨 SUSPEITO: Todos dígitos iguais!");
            return;
        }

        // FOR ANINHADO - Análise de frequência
        analisarFrequencia(cartao);

        // FOR com contador - Sequências consecutivas
        int sequencia = maiorSequenciaConsecutiva(cartao);
        if (sequencia >= 5) {
            System.out.println("🚨 SUSPEITO: Sequência de " + sequencia + " dígitos!");
        }

        // WHILE - Padrões alternados
        if (temPadraoAlternado(cartao)) {
            System.out.println("🚨 SUSPEITO: Padrão alternado detectado!");
        }

        // FOR reverso - Algoritmo Luhn
        if (!validarLuhn(cartao)) {
            System.out.println("❌ INVÁLIDO: Falhou algoritmo Luhn!");
            return;
        }

        System.out.println("✅ Cartão passou em todos os testes!\n");
    }

    // 1. FOR SIMPLES - Repetição total
    private boolean temRepeticaoTotal(Cartao cartao) {
        char primeiro = cartao.getDigito(0);
        for (int i = 1; i < cartao.getTamanho(); i++) {
            if (cartao.getDigito(i) != primeiro) return false;
        }
        return true;
    }

    // 2. FOR ANINHADO - Frequência de dígitos
    private void analisarFrequencia(Cartao cartao) {
        int[] frequencia = new int[10];

        // FOR externo - percorre todos os dígitos
        for (int i = 0; i < cartao.getTamanho(); i++) {
            int digito = Character.getNumericValue(cartao.getDigito(i));
            frequencia[digito]++; // Incrementa frequência
        }

        // FOR interno - encontra máximo
        int maxFreq = 0, digitoMax = 0;
        for (int i = 0; i < 10; i++) {
            if (frequencia[i] > maxFreq) {
                maxFreq = frequencia[i];
                digitoMax = i;
            }
        }

        if (maxFreq > 8) {
            System.out.println("🚨 SUSPEITO: '" + digitoMax + "' aparece " + maxFreq + "x!");
        }
    }

    // 3. FOR com contador - Sequência consecutiva
    private int maiorSequenciaConsecutiva(Cartao cartao) {
        int maxSeq = 1, seqAtual = 1;
        for (int i = 1; i < cartao.getTamanho(); i++) {
            if (cartao.getDigito(i) == cartao.getDigito(i - 1)) {
                seqAtual++;
                maxSeq = Math.max(maxSeq, seqAtual);
            } else {
                seqAtual = 1;
            }
        }
        return maxSeq;
    }

    // 4. WHILE - Padrão alternado
    private boolean temPadraoAlternado(Cartao cartao) {
        // Padrão ABABAB (while)
        int i = 0;
        while (i < cartao.getTamanho() - 2) {
            if (cartao.getDigito(i) != cartao.getDigito(i + 2)) {
                break;
            }
            i += 2;
        }
        if (i >= cartao.getTamanho() - 2) return true;

        // Padrão ABABAB diferente (reinicia while)
        i = 0;
        while (i < cartao.getTamanho() - 2) {
            if (cartao.getDigito(i) == cartao.getDigito(i + 1)) {
                return false;
            }
            i += 2;
        }
        return true;
    }

    // 5. FOR reverso - Algoritmo Luhn
    private boolean validarLuhn(Cartao cartao) {
        int soma = 0;
        boolean posImpar = true;

        for (int i = cartao.getTamanho() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(cartao.getDigito(i));
            if (posImpar) {
                digito *= 2;
                if (digito > 9) digito -= 9;
            }
            soma += digito;
            posImpar = !posImpar;
        }
        return soma % 10 == 0;
    }

    private String formatar(Cartao cartao) {
        return cartao.getNumeroLimpo().replaceAll("(.{4})", "$1 ");
    }
}