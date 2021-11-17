package busca_tabu;

public class AlgoritmoGuloso {

    public static Cidades[] run(Cidades cidades[], int qdt, int mDistancias[][]) {
      //  System.out.println("\n-------- UTILIZANDO METODO GULOSO ---------");

        int menor = 0;
        int id = 0;
        int cont3 = 0;
        int idAtual = 0;
        int dist = 0;
        String caminho = cidades[id].getNome();
        Cidades solucaoGulosa[] = new Cidades[qdt];
        solucaoGulosa[id] = cidades[id];
        
        cidades[id].setVisita(2);

        for (int c = 0; c < qdt; c++) {
            if (c != qdt - 1) {
                for (int i = 0; i < qdt; i++) {
                    if (i != id && cidades[i].getVisita() != 1) {
                        if (cont3 == 0) {
                            menor = mDistancias[cidades[id].getId()][cidades[i].getId()];
                            idAtual = i;
                        } else if (menor > mDistancias[cidades[id].getId()][cidades[i].getId()]) {
                            menor = mDistancias[cidades[id].getId()][cidades[i].getId()];
                            idAtual = i;                            
                        }
                        ++cont3;
                    }
                }// final do segundo laço

                cidades[id].setVisita(1);
                cidades[id].setDistanciaProxima(menor);
                id = idAtual;
                dist += menor;
              //  System.out.println("Menor: " + menor);
                cont3 = 0;
                cidades[id].setVisita(1);
                caminho += " - " + cidades[id].getNome();
                
                solucaoGulosa[c+1] = cidades[id];
            } else {
                dist += mDistancias[0][id];
               // System.out.println("Menor: " + mDistancias[0][id]);
                caminho += " - " + cidades[0].getNome();
                cidades[id].setDistanciaProxima(mDistancias[0][id]);
                
            }
        } // final do primeiro laço

      //  System.out.println("\n::. DISTANCIA: " + dist);
      //  System.out.println("::. CAMINHO: " + caminho);
        
        return solucaoGulosa;
    }

}
