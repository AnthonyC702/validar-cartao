package model;

public class Cartao {
    private final String numeroOriginal;
    private final String numeroLimpo;

    public Cartao(String numero) {
        this.numeroOriginal = numero;
        this.numeroLimpo = numero.replaceAll("\\s+", "");
    }

    // GETTERS
    public String getNumeroOriginal() { return numeroOriginal; }
    public String getNumeroLimpo() { return numeroLimpo; }
    public int getTamanho() { return numeroLimpo.length(); }

    // Métodos utilitários
    public char getDigito(int posicao) {
        return numeroLimpo.charAt(posicao);
    }

    @Override
    public String toString() {
        return numeroOriginal;
    }
}
