package tiralabra.tietorakenteet;

public class PrioSolmu {
    
    PrioSolmu ennen;
    PrioSolmu jalkeen;
    int etaisyys;
    int x;
    int y;
    
    
    public PrioSolmu(int omaX, int omaY,int kohdeX,int kohdeY) {
        this.x = omaX;
        this.y = omaY;
        this.etaisyys = etaisyys(kohdeX, kohdeY);
        this.ennen = null;
        this.jalkeen = null;
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
    
    public int etaisyys(int kohdeX, int kohdeY) {
        int xOsa = kohdeX - x;
        int yOsa = kohdeY - y;
        int etaisyys = abs(xOsa) + abs(yOsa);
        return etaisyys;
    }
    
    private int abs(int n) {
        return n > 0 ? n : -n;
    }

    
}
