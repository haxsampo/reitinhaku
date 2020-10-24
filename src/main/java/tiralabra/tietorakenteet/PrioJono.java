package tiralabra.tietorakenteet;

/**
 * Prioriteettijonon implementaatio
 * Käyttää PrioSolmu luokan objekteja listan elementteinä
 * Prioriteettijärjestys toimii pienin ensin perusteella
 * @author toni_
 */
public class PrioJono {
    private PrioSolmu eka,vika;
    private int pituus;
    private int kohdeX, kohdeY;
    
    /**
     * Konstruktori jossa jonolle annetaan myös kohdekoordinaatit - prioriteetin
     * laskeminen GBF algoritmissa puhtaasti etäisyyden laskemista, joten prioriteetin
     * eli etäisyyden laskeminen on upotettu luokkaan.
     * @param kohdeX
     * @param kohdeY
     */
    public PrioJono(int kohdeX, int kohdeY){
        this.pituus = 0;
        this.kohdeX = kohdeX;
        this.kohdeY = kohdeY;
    }
    
    /**
     * Uuden solmun lisäys listaan.
     * @param uusi
     */
    public void lisaa(PrioSolmu uusi) {
        if(pituus == 0) {
            uusi.setEnnen(null);
            uusi.setJalkeen(null);
            this.eka = uusi;
            this.vika = uusi;
            pituus++;
        } else {
            PrioSolmu ennen = loydaEnnen(uusi);
            if(ennen == uusi) { //uusi solmu jonon ensimmäiseksi
               PrioSolmu vanhaEka = eka;
               vanhaEka.setEnnen(uusi);
               uusi.setJalkeen(vanhaEka);
               this.eka = uusi;
            } else {
                if(ennen.getJalkeen()==null) {//uusi solmu jonon perälle
                    uusi.setEnnen(ennen);
                    ennen.setJalkeen(uusi);
                    this.vika = uusi;                   
                } else {//uusi solmu jonon keskelle
                    PrioSolmu jalk = ennen.getJalkeen();
                    ennen.setJalkeen(uusi);
                    jalk.setEnnen(uusi);
                    uusi.setEnnen(ennen);
                    uusi.setJalkeen(jalk);
                }
            }
            pituus++;
        }
    }
    
    /*Vertaa jonon solmuja ja palauttaa sen jonka arvo on
    pienempi ts jonka jälkeiseksi uusi solmu tulisi sijoittaa jonossa.
    Palauttaa argumenttina annetun solmun jos ei löydy pienempää etäisyyttä jonosta.
    */
    private PrioSolmu loydaEnnen(PrioSolmu uusi) {
        int uusEtaisyys = uusi.getEtaisyys();
        if(this.eka.getEtaisyys()>= uusEtaisyys) {
            return uusi;
        } else if(pituus==1) {
            return this.eka;
        }
        PrioSolmu vert = eka.getJalkeen();
        for(int i=0;i<this.pituus;i++) {
            if(vert.getEtaisyys() >= uusEtaisyys) {
                return vert.getEnnen();
            } else {
                if(vert.getJalkeen() == null) {//jono loppu
                    return vert;
                } else {
                    vert = vert.getJalkeen();
                }
            } 
        }
        return uusi;
    }

    /**
     * Poppaa ensimmäisen priosolmun jonosta
     * @return
     */
    public PrioSolmu ekaPois() {
        if(pituus == 1) {
            pituus--;
            PrioSolmu ls = this.eka;
            this.eka = null;
            this.vika = null;
            return ls;
        } else if (pituus > 1) {
            this.eka.getJalkeen().setEnnen(null); //uusi jonoon pää viittaa nulliin
            pituus--;
            PrioSolmu pal = this.eka;
            this.eka = eka.getJalkeen(); //jonon päätyviite kuntoon
            return pal;
        } else {
            return null;
        }
    }
    
    /**
     * @return
     */
    public PrioSolmu getEka() {
        return eka;
    }
    
    /**
     * @return
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
    
    /**
     * Etäisyyden laskemista annettujen koordinaattien ja kohteen välillä. 
     * Kohde on annettu konstruktorissa.
     * @param x
     * @param y
     * @return
     */
    public int etaisyys(int x, int y) {
        int xOsa = kohdeX - x;
        int yOsa = kohdeY - y;
        int etaisyys = abs(xOsa) + abs(yOsa);
        return etaisyys;
    }

    /**
     * @return
     */
    public PrioSolmu getVika() {
        return vika;
    }

    /**
     * @param vika
     */
    public void setVika(PrioSolmu vika) {
        this.vika = vika;
    }
    
    private int abs(int n) {
        return n > 0 ? n : -n;
    }
    
}
