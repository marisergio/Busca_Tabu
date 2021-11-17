package busca_tabu;
public class Cidades {
    private int id;
    private String nome;
    private int visita = 0;
    private int distanciaProxima;

    public Cidades(){

    }
     public Cidades(String nome){
         this.nome=nome;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVisita() {
        return visita;
    }

    public void setVisita(int visita) {
        this.visita = visita;
    }

    public int getDistanciaProxima() {
        return distanciaProxima;
    }

    public void setDistanciaProxima(int distanciaProxima) {
        this.distanciaProxima = distanciaProxima;
    }      

}