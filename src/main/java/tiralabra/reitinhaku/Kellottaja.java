package tiralabra.reitinhaku;

public class Kellottaja {
 
    long aloitus;
    long lopetus;
    
    public Kellottaja(){
        
    }
    
    public void aloita() {
        aloitus=0;
        lopetus=0;
        this.aloitus = System.nanoTime();
    }
    
    public void lopeta() {
        this.lopetus = System.nanoTime();
    }
    
    public double kuluma() {
        double kuluma = lopetus-aloitus;
        return (double) kuluma / 1000000000;
    }
}
