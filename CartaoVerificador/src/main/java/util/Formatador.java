package util;

public class Formatador {
    public static String formatarCartao(String numero) {
        return numero.replaceAll("(.{4})", "$1 ");
    }

    public static String removerFormatacao(String numero) {
        return numero.replaceAll("\\s+", "");
    }

    public static boolean isNumeroValido(String numero) {
        String limpo = removerFormatacao(numero);
        return limpo.length() == 16 && limpo.matches("\\d{16}");
    }
}
