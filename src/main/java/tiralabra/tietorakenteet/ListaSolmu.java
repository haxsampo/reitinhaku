package tiralabra.tietorakenteet;


public class ListaSolmu {
  
    public int arvo;
    public ListaSolmu ennen;
    public ListaSolmu jalkeen;
    
    public ListaSolmu(int arvo) {
        this.arvo = arvo;
        this.ennen = null;
        this.jalkeen = null;
    }

    public int getArvo() {
        return arvo;
    }

    public void setArvo(int arvo) {
        this.arvo = arvo;
    }

    public ListaSolmu getEnnen() {
        return ennen;
    }

    public void setEnnen(ListaSolmu ennen) {
        this.ennen = ennen;
    }

    public ListaSolmu getJalkeen() {
        return jalkeen;
    }

    public void setJalkeen(ListaSolmu jalkeen) {
        this.jalkeen = jalkeen;
    }

   
}
