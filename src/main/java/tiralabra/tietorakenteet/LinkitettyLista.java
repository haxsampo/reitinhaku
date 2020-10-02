/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tietorakenteet;


public class LinkitettyLista {
    
    ListaSolmu eka,vika;
    int pituus;
    
    public LinkitettyLista() {
        this.pituus = 0;
    }
    
    public void lisaa(ListaSolmu uusi) {
        if(pituus == 0) {
            uusi.setEnnen(null);
            uusi.setJalkeen(null);
            this.eka = uusi;
            this.vika = uusi;
            pituus++;
        } else {
            uusi.setEnnen(vika);
            uusi.setJalkeen(null);
            vika.setJalkeen(uusi);
            vika = uusi;
            pituus++;
        }
    }
    
    public ListaSolmu ekaPois() {
        if(pituus == 1) {                  
            pituus--;
            ListaSolmu ls = eka;
            this.eka = eka.getJalkeen();
            return ls;
        } else if (pituus > 1) {
            eka.getJalkeen().setEnnen(null);
            pituus--;
            ListaSolmu ls = eka;
            this.eka = eka.getJalkeen();
            return ls;
        } else {
            return null;
        }
    }
    
    public ListaSolmu getEka() {
        return eka;
    }
    
    public int getPituus() {
        return pituus;
    }
    
    public boolean onTyhja() {
        if(pituus == 0) {
            return true;
        } else {
            return false;
        }
    }
}
