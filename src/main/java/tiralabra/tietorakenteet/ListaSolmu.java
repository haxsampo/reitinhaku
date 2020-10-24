package tiralabra.tietorakenteet;

/**
 * LinkitettyLista luokan käsittelemät objektit ovat ListaSolmuja
 */
public class ListaSolmu {
    private int arvo;
    private ListaSolmu ennen;
    private ListaSolmu jalkeen;
    
    /**
     * Konstruktori
     * @param arvo
     */
    public ListaSolmu(int arvo) {
        this.arvo = arvo;
        this.ennen = null;
        this.jalkeen = null;
    }

    /**
     * @return
     */
    public int getArvo() {
        return arvo;
    }

    /**
     * @param arvo
     */
    public void setArvo(int arvo) {
        this.arvo = arvo;
    }

    /**
     * @return
     */
    public ListaSolmu getEnnen() {
        return ennen;
    }

    /**
     * @param ennen
     */
    public void setEnnen(ListaSolmu ennen) {
        this.ennen = ennen;
    }

    /**
     * @return
     */
    public ListaSolmu getJalkeen() {
        return jalkeen;
    }

    /**
     * @param jalkeen
     */
    public void setJalkeen(ListaSolmu jalkeen) {
        this.jalkeen = jalkeen;
    }

}
