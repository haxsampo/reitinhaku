
package tiralabra.tietorakenteet;


public class Koordinaatti {
    
    int x,y;
    
    public Koordinaatti(int x, int y) {
        this.x = x;
        this.y = y;
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
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        
        if(obj.getClass() != this.getClass()) {
            return false;
        }
        
        final Koordinaatti vert = (Koordinaatti) obj;   
        
        if(this.x != vert.getX() || this.y != vert.getY()) {
            return false;
        }
        return true;
    }
}
