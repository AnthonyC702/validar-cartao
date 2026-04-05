package service;

import java.util.HashMap;
import java.util.Map;

public class BancoService {
    private final Map<String, String> prefixosBancos;

    public BancoService() {
        prefixosBancos = new HashMap<>();
        inicializarBancos();
    }

    public void inicializarBancos() {
        prefixosBancos.put("34", "AMERICAN EXPRESS");
        prefixosBancos.put("37", "AMERICAN EXPRESS");
        prefixosBancos.put("4", "VISA");
        prefixosBancos.put("5", "MASTERCARD");
        prefixosBancos.put("6011", "DISCOVER");
        prefixosBancos.put("35", "MASTERCARD (JCB)");
        prefixosBancos.put("2131", "JCB");
        prefixosBancos.put("1800", "JCB");
    }

    public void identificarBanco(String numero) {
        String numeroLimpo = numero.replaceAll("\\s+", "");
        String banco = "DESCONHECIDO";

        //FOR-EACH no HasMap, verifica tos os pefixos
        for (Map.Entry<String, String> entrada : prefixosBancos.entrySet()) {
            String prefixo = entrada.getKey();
            if (numeroLimpo.startsWith(prefixo)) {
                banco = entrada.getValue();
                break;
            }
        }

        System.out.println("\n ANÁLISE DE BANCO EMISSOR ");
        System.out.println("==========================");
        System.out.println("Cartao : " + numeroLimpo.replaceAll("(.{4})", "$1 "));
        System.out.println("Banco :" + banco);
        System.out.println("prefixo : " + numeroLimpo.substring(0, Math.min(4, numeroLimpo.length())));

        //FOR-EACH Análise adcional de pefixos suspeitos
        analisarPrefixosSuspeitos(numeroLimpo);

    }

    //FOR aninhado - verifica padrões suspeitos no pefixo
    private void analisarPrefixosSuspeitos(String numero) {
        String prefixo = numero.substring(0, 6);

        //Padrões suspeitos comuns em cartões falsos
        String[] padroesSuspeitos = {"000000", "111111", "123456", "999999"};

        outer:
        for (String padrao : padroesSuspeitos) { //FOR externo(FOR-EACH)
            for (int i = 0; i < padrao.length(); i++) { //FOR interno
                if (prefixo.charAt(i) != padrao.charAt(i)) {
                    continue outer;
                }
            }
            System.out.println("ALERTA : pefixo suspeito detectado! ");
            return;
        }
    }
}