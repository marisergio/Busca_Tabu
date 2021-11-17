
package busca_tabu;

import java.util.List;


public class Tabu {
    public Cidades cidadeA;
    public Cidades cidadeB;
    public Movimento movimento;
    
    
    public static Boolean isTabu(List<Tabu> listaTabu, Tabu t){
        Boolean isTabu = false;
        for (Tabu tabu : listaTabu) {
            if ((t.cidadeA.getId()==tabu.cidadeA.getId() && t.cidadeB.getId()==tabu.cidadeB.getId()) || 
                (t.cidadeB.getId()==tabu.cidadeA.getId() && t.cidadeA.getId()==tabu.cidadeB.getId())){
                isTabu =  true;
                break;
            }
        }
        
        return isTabu;
        
    }
    
}
