package tiralabra.tietorakenteet;

/**
 * Prioriteettisolmu prioriteetilistausta varten
 * @author toni_
 */
public class PrioSolmu {  
    private PrioSolmu ennen;
    private PrioSolmu jalkeen;
    private int etaisyys;
    private Koordinaatti kohde;
    private Koordinaatti sijainti;
    private int x;
    private int y;
    
    /**
     * Konstruktori
     * @param omaX Solmun oma koordinaatti
     * @param omaY Solmun oma koordinaatti
     * @param kohdeX kohdekoordinaatti
     * @param kohdeY kohdekoordinaatti
     */
    public PrioSolmu(int omaX, int omaY,int kohdeX,int kohdeY) {
        this.x = omaX;
        this.y = omaY;
        this.kohde = new Koordinaatti(kohdeX,kohdeY);
        this.sijainti = new Koordinaatti(omaX, omaY);
        this.etaisyys = etaisyys();
        this.ennen = null;
        this.jalkeen = null;
    }
    
    /**
     * Vaihtoehtoinen konstruktori
     * @param sijainti oma koordinaatti
     * @param kohde kohdekoordinaatti
     */
    public PrioSolmu(Koordinaatti sijainti, Koordinaatti kohde) {
        this.sijainti = sijainti;
        this.kohde = kohde;
        this.etaisyys = etaisyys();
        this.ennen = null;
        this.jalkeen = null;
    }
    
    /**
     * Vaihtoehtoinen konstruktori siten, että etäisyys syötetään jo tässä vaiheessa
     * Jos prioriteetti lasketaan muuten kuin pelkän etäisyyden suhteen.
     * @param omaX
     * @param omaY
     * @param etaisyys
     */
    public PrioSolmu(int omaX, int omaY, int etaisyys) {
        this.x = omaX;
        this.y = omaY;
        this.sijainti = new Koordinaatti(omaX, omaY);
        this.etaisyys = etaisyys;
    }

    /**
     * @return
     */
    public Koordinaatti getSijainti() {
        return sijainti;
    }

    /**
     * @param sijainti
     */
    public void setSijainti(Koordinaatti sijainti) {
        this.sijainti = sijainti;
    }

    /**
     * @return
     */
    public PrioSolmu getEnnen() {
        return ennen;
    }

    /**
     * @param ennen
     */
    public void setEnnen(PrioSolmu ennen) {
        this.ennen = ennen;
    }

    /**
     * @return
     */
    public PrioSolmu getJalkeen() {
        return jalkeen;
    }

    /**
     * @param jalkeen
     */
    public void setJalkeen(PrioSolmu jalkeen) {
        this.jalkeen = jalkeen;
    }

    /**
     * @return
     */
    public int getEtaisyys() {
        return etaisyys;
    }

    /**
     * @param etaisyys
     */
    public void setEtaisyys(int etaisyys) {
        this.etaisyys = etaisyys;
    }
    
    /**
     * @return
     */
    public int etaisyys() {
        int xOsa = kohde.getX() - sijainti.getX();
        int yOsa = kohde.getY() - sijainti.getY();
        int etaisyys = abs(xOsa) + abs(yOsa);
        return etaisyys;
    }
    
    private int abs(int n) {
        return n > 0 ? n : -n;
    }
 
}
