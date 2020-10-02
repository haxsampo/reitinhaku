package tiralabra.reitinhaku;


public class Solmu {
       
    boolean kayty;
    boolean kulkukelpoinen;
    int x;
    int y;
    //lisää joku came_from
    
    public Solmu(int x, int y){
        this.x = x;
        this.y = y;
        kayty = false;
        kulkukelpoinen = false;
    }

    public boolean isKayty() {
        return kayty;
    }

    public void setKayty(boolean kayty) {
        this.kayty = kayty;
    }

    public boolean isKulkukelpoinen() {
        return kulkukelpoinen;
    }

    public void setKulkukelpoinen(boolean kulkukelpoinen) {
        this.kulkukelpoinen = kulkukelpoinen;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
