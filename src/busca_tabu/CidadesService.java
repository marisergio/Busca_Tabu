
package busca_tabu;

import java.util.Random;

public class CidadesService {
    
  //  public static int mDistancias[][];
    public static final int qdt = 50;
    
    
    public CidadesService(int qtde){
        System.out.println("passou aqui");
    }
    
    public static Cidades[] getCidades(){
        Cidades cidades[] = new Cidades[qdt];

        for(int i = 0; i<qdt; i++) {
            cidades[i] = new Cidades("C"+i);
            cidades[i].setId(i);
        }
        return cidades;
    }
    
    public static int[][] getMatrizDistancias(){
        int mDistancias[][] = new int[qdt][qdt];

        Random random = new Random();

        for(int i = 0; i<qdt; i++){
            for(int j=0; j<qdt; j++){
                if(i==j){
                    mDistancias[i][j] = 0;
                }else {
                    if(i>j){
                        mDistancias[i][j]=mDistancias[j][i];
                    }else{
                        if(j%2!=0) {
                            if(i%2!=0)
                                mDistancias[i][j] = (int)1 + random.nextInt(qdt);
                            else
                                mDistancias[i][j] = (int)1 + random.nextInt(qdt);
                        }else {
                            if(i==0)
                                mDistancias[i][j] = (int)1 + random.nextInt(qdt);
                            if(j==0)
                                mDistancias[i][j] = (int)1 + random.nextInt(qdt);
                            if(i!=0&&j!=0)
                                mDistancias[i][j] = (int)1 + random.nextInt(qdt);
                        }
                    }
                }
            }
        }
        return mDistancias;
    }
    
    public static void imprimirCidades(Cidades cidades[]){
        System.out.println("-------- Cidades ---------");
        for (int i = 0; i < qdt; i++) {
            System.out.println("id: " + cidades[i].getId() + " Cidade: " + cidades[i].getNome());
        }
    }
    
    public static void imprimirDistancias(int mDistancias[][]){        
        System.out.println("\n-------- MATRIZ DE DISTÂNCIAS ---------");
        for(int i = 0; i<qdt; i++){
            for(int j = 0; j<qdt; j++){
                System.out.print(mDistancias[i][j]+" ");
            }
            System.out.print("\n");
        }
    }    
}
