
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
    
    @Override
    public String toString() {
        return String.format(this.x + " "+ this.y);
    }
    
    /*
    Sietää noin 30 000 x 30 000 pikselin karttoja ennenkuin
    ylittää intin bittimäärän
    */ 
    @Override
    public int hashCode() {
        int result = (this.x + this.y) * (this.x + this.y + 1) / 2 + this.x;
        return result;
    }
    
    
}
