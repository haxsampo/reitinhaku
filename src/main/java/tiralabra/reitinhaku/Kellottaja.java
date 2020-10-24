package tiralabra.reitinhaku;

/**
 * Ajanottajaobjekti, kykenee yhteen ajanottoon kerrallaan
 * @author toni_
 */
public class Kellottaja {
    private long aloitus;
    private long lopetus;
    
    /**
     * Konstruktori
     */
    public Kellottaja(){     
    }
    
    /**
     * Nollaa arvot ja aloittaa ajanoton
     */
    public void aloita() {
        aloitus=0;
        lopetus=0;
        this.aloitus = System.nanoTime();
    }
    
    /**
     * Lopettaa ajanoton
     */
    public void lopeta() {
        this.lopetus = System.nanoTime();
    }
    
    /**
     * 
     * @return Palauttaa kuluneen ajan double muodossa
     */
    public double kuluma() {
        double kuluma = lopetus-aloitus;
        return (double) kuluma / 1000000000;
    }
}
