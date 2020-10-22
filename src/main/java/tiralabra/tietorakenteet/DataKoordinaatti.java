
package tiralabra.tietorakenteet;


public class DataKoordinaatti {
    
    int tamaX;
    int tamaY;
    Koordinaatti tama;
    Koordinaatti edeltaja;
    int hinta;
    int vari;

    public DataKoordinaatti(int tamaX, int tamaY, int edeltajaX, int edeltajaY) {
        this.tamaX = tamaX;
        this.tamaY = tamaY;
        this.tama = new Koordinaatti(tamaX, tamaY);
        this.edeltaja = new Koordinaatti(edeltajaX, edeltajaY);
    }    
    
    public Koordinaatti getTama() {
        return tama;
    }

    public void setTama(Koordinaatti tama) {
        this.tama = tama;
    }

    public Koordinaatti getEdeltaja() {
        return edeltaja;
    }

    public void setEdeltaja(Koordinaatti edeltaja) {
        this.edeltaja = edeltaja;
    }

    public int getVari() {
        return vari;
    }

    public void setVari(int vari) {
        this.vari = vari;
    }
    
    public int getHinta() {
        return hinta;
    }

    public void setHinta(int hinta) {
        this.hinta = hinta;
    }
    
    
}
