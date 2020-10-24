package tiralabra.tietorakenteet;

/**
 * Linkitettylista implementaatio
 */
public class LinkitettyLista {
    private ListaSolmu eka,vika;
    private int pituus;
    
    /**
     * Konstruktori
     */
    public LinkitettyLista() {
        this.pituus = 0;
    }
    
    /**
     * Lisää uuden solmun listalle
     * @param uusi
     */
    public void lisaa(ListaSolmu uusi) {
        if(pituus == 0) {
            uusi.setEnnen(null);
            uusi.setJalkeen(null);
            this.eka = uusi;
            this.vika = uusi;
            pituus++;
        } else {
            uusi.setEnnen(vika);
            uusi.setJalkeen(null);
            vika.setJalkeen(uusi);
            vika = uusi;
            pituus++;
        }
    }
    
    /**
     * @return Poppaa ensimmäisen solmun
     */
    public ListaSolmu ekaPois() {
        if(pituus == 1) {
            pituus--;
            ListaSolmu ls = eka;
            this.eka = eka.getJalkeen();
            return ls;
        } else if (pituus > 1) {
            eka.getJalkeen().setEnnen(null);
            pituus--;
            ListaSolmu ls = eka;
            this.eka = eka.getJalkeen();
            return ls;
        } else {
            return null;
        }
    }
    
    /**
     * @return Palauttaa ensimmäisen solmun poistamatta sitä
     */
    public ListaSolmu getEka() {
        return eka;
    }
    
    /**
     * @return Palauttaa listan pituuden
     */
    public int getPituus() {
        return pituus;
    }
    
    /** 
     * @return
     */
    public boolean onTyhja() {
        if(pituus == 0) {
            return true;
        } else {
            return false;
        }
    }
}
