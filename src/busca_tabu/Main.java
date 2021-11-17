package busca_tabu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        
        int k = 3;
        int iteracao = 50;
        int distancia = 0;

        List<Tabu> listaTabu = new ArrayList<Tabu>();

        //gerando as cidades
        Cidades cidades[] = CidadesService.getCidades();

        // gerando a matriz de distancias
        int mDistancias[][] = CidadesService.getMatrizDistancias();

        CidadesService.imprimirCidades(cidades);

        CidadesService.imprimirDistancias(mDistancias);

        //aplicando o método guloso
        Cidades solucaoInicial[] = AlgoritmoGuloso.run(cidades, CidadesService.qdt, mDistancias);
        distancia = calculaFuncaoObjetiva(solucaoInicial, CidadesService.qdt);
        Cidades solucaoTemp[] = copiaSolucao(solucaoInicial, CidadesService.qdt);
        int distanciaTemp = distancia;
        System.out.println("\nSOLUÇÃO INICIAL");
        imprimeCaminho(solucaoInicial, CidadesService.qdt);
        System.out.println("Distancia inicial: " + distancia + "\n");

        for (int i = 0; i < iteracao; i++) {
            System.out.println("ITERAÇÃO: " + (i + 1));
            List<Movimento> movimentos = new ArrayList<Movimento>();

            for (int j = 0; j < k; j++) {
                Movimento movimento = gerarMovimento(CidadesService.qdt);
                System.out.println("MOVIMENTO " + (j + 1) + ": ("
                        + solucaoTemp[movimento.indiceA].getNome() + ","
                        + solucaoTemp[movimento.indiceB].getNome() + ")");
                solucaoTemp = efetuarMovimento(solucaoTemp, CidadesService.qdt, movimento);
                solucaoTemp = calculaDistancias(solucaoTemp, CidadesService.qdt, mDistancias);
                movimento.distanciaTotal = calculaFuncaoObjetiva(solucaoTemp, CidadesService.qdt);
                movimentos.add(movimento);

                imprimeCaminho(solucaoTemp, CidadesService.qdt);
                System.out.println("Distancia: " + movimento.distanciaTotal);
                solucaoTemp = copiaSolucao(solucaoInicial, CidadesService.qdt);
                System.out.println(" ");

            }
            Tabu movimentoTabu = null;
            for (Movimento m : movimentos) {
                if (m.distanciaTotal < distanciaTemp) {

                    distanciaTemp = m.distanciaTotal;

                    movimentoTabu = new Tabu();
                    movimentoTabu.cidadeA = solucaoTemp[m.indiceA];
                    movimentoTabu.cidadeB = solucaoTemp[m.indiceB];
                    movimentoTabu.movimento = m;
                }
            }

            if (movimentoTabu != null && !Tabu.isTabu(listaTabu, movimentoTabu)) {

                listaTabu.add(movimentoTabu);
                solucaoInicial = efetuarMovimento(solucaoInicial, CidadesService.qdt, movimentoTabu.movimento);
                solucaoInicial = calculaDistancias(solucaoInicial, CidadesService.qdt, mDistancias);
                distanciaTemp = movimentoTabu.movimento.distanciaTotal;
                if (listaTabu.size() == k) {
                    listaTabu.remove(listaTabu.get(0));
                }

                System.out.print("LISTA TABU: ");
                for (Tabu e : listaTabu) {
                    System.out.print("(" + e.cidadeA.getId() + "," + e.cidadeB.getId() + ") ");
                }

            }
            System.out.println(" ");

            System.out.println("------------------------------\n");

        }
        System.out.println("DISTANCIA INICIAL: " + distancia);
        System.out.println("DISTANCIA FINAL: " + distanciaTemp);

    }

    public static Movimento gerarMovimento(int qdt) {
        Random random = new Random();

        Movimento movimento = new Movimento();

        do {
            movimento.indiceA = random.nextInt(qdt);
            movimento.indiceB = random.nextInt(qdt);
        } while (movimento.indiceA == movimento.indiceB);

        return movimento;

    }

    public static int calculaFuncaoObjetiva(Cidades s[], int qdt) {
        int soma = 0;
        for (int i = 0; i < qdt; i++) {
            soma += s[i].getDistanciaProxima();
        }
        return soma;
    }

    public static Cidades[] efetuarMovimento(Cidades s[], int qdt, Movimento movimento) {
        Cidades temp = s[movimento.indiceA];
        s[movimento.indiceA] = s[movimento.indiceB];
        s[movimento.indiceB] = temp;

        return s;
    }

    public static Cidades[] calculaDistancias(Cidades s[], int qdt, int mDistancias[][]) {
        for (int i = 0; i < qdt - 1; i++) {
            s[i].setDistanciaProxima(mDistancias[s[i].getId()][s[i + 1].getId()]);
        }
        s[qdt - 1].setDistanciaProxima(mDistancias[s[qdt - 1].getId()][s[0].getId()]);

        return s;
    }

    public static void imprimeCaminho(Cidades s[], int qdt) {

        for (int i = 0; i < qdt - 1; i++) {
            System.out.print(s[i].getNome() + "=>" + s[i + 1].getNome() + ": " + s[i].getDistanciaProxima() + " - ");
        }
        System.out.print(s[qdt - 1].getNome() + "=>" + s[0].getNome() + ": " + s[qdt - 1].getDistanciaProxima() + "\n");
    }

    public static Cidades[] copiaSolucao(Cidades s1[], int qdt) {
        Cidades s2[] = new Cidades[qdt];
        for (int i = 0; i < qdt; i++) {
            s2[i] = s1[i];
        }
        return s2;
    }

}
