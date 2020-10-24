
package tiralabra.tietorakenteet;

/*
 * Representaatio (x,y) arvosta
 * @author toni_
 */
public class Koordinaatti {
    
    private int x,y;
    
    /*
     * @param x
     * @param y
     */
    public Koordinaatti(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /*
     * @return
     */
    public int getX() {
        return x;
    }

    /*
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /*
     * @return
     */
    public int getY() {
        return y;
    }

    /*
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /*
     * Määrittää koordinaattiobjektien vertailun siten, että jos vertailuobjekti
     * on arvoltaan null; tai vertailuobjektin luokka on eri; tai jos vertailuobjektin
     * x tai y arvo on eri, niin tällöin funktio palauttaa epätosi, muuten tosi.
     * @param obj
     * @return
     */
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
    
    /*
     * @return
     */
    @Override
    public String toString() {
        return String.format(this.x + " "+ this.y);
    } 

    /*
     * Sietää noin 30 000 x 30 000 pikselin karttoja ennenkuin
     * ylittää intin bittimäärän. Ei pitäisi tulla hashcollisioneita.
     * @return
     */
 
    @Override
    public int hashCode() {
        int result = (this.x + this.y) * (this.x + this.y + 1) / 2 + this.x;
        return result;
    }
}
