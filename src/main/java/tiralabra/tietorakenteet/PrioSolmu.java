package tiralabra.tietorakenteet;

public class PrioSolmu {
    
    PrioSolmu ennen;
    PrioSolmu jalkeen;
    int etaisyys;
    Koordinaatti kohde;
    Koordinaatti sijainti;
    int x;
    int y;
    
    
    public PrioSolmu(int omaX, int omaY,int kohdeX,int kohdeY) {
        this.x = omaX;
        this.y = omaY;
        this.kohde = new Koordinaatti(kohdeX,kohdeY);
        this.sijainti = new Koordinaatti(omaX, omaY);
        this.etaisyys = etaisyys();
        this.ennen = null;
        this.jalkeen = null;
    }
    
    public PrioSolmu(Koordinaatti sijainti, Koordinaatti kohde) {
        this.sijainti = sijainti;
        this.kohde = kohde;
        this.etaisyys = etaisyys();
        this.ennen = null;
        this.jalkeen = null;
    }
    
    public PrioSolmu(int omaX, int omaY, int etaisyys) {
        this.x = omaX;
        this.y = omaY;
        this.sijainti = new Koordinaatti(omaX, omaY);
        this.etaisyys = etaisyys;
    }

    public Koordinaatti getSijainti() {
        return sijainti;
    }

    public void setSijainti(Koordinaatti sijainti) {
        this.sijainti = sijainti;
    }

    public PrioSolmu getEnnen() {
        return ennen;
    }

    public void setEnnen(PrioSolmu ennen) {
        this.ennen = ennen;
    }

    public PrioSolmu getJalkeen() {
        return jalkeen;
    }

    public void setJalkeen(PrioSolmu jalkeen) {
        this.jalkeen = jalkeen;
    }

    public int getEtaisyys() {
        return etaisyys;
    }

    public void setEtaisyys(int etaisyys) {
        this.etaisyys = etaisyys;
    }
    
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
