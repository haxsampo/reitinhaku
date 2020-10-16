
package algoritmit;

import java.util.HashMap;
import tiralabra.tietorakenteet.PrioJono;
import tiralabra.tietorakenteet.PrioSolmu;
import tiralabra.tietorakenteet.Koordinaatti;

public class Ahne {
    
    int kohdeX;
    int kohdeY;       
    int alkuX;
    int alkuY;
    Koordinaatti alku;
    Koordinaatti kohde;
    HashMap<Koordinaatti, Koordinaatti> siEd; //sijainti-edeltäjä
    PrioJono pj;
    int[][] kulkukelpoisuus;   
    int reittiVari = -12336;
    int harmaa = -1710619;
    
    int[][] kartta; //0 ei kulkukelpoinen maasto, 1 kulkukelpoinen maasto
    
    public Ahne(int alkuX, int alkuY, int kohdeX, int kohdeY, int[][] kulkukelpoisuus) {
        this.kohdeX = kohdeX;
        this.kohdeY = kohdeY;
        this.alkuX = alkuX;
        this.alkuY = alkuY;   
        
        this.alku = new Koordinaatti(alkuX, alkuY);
        this.kohde = new Koordinaatti(kohdeX, kohdeY);
        
        PrioJono pj = new PrioJono(this.kohdeX, this.kohdeY);
        this.pj = pj;
        PrioSolmu aloitus = new PrioSolmu(this.alku, this.kohde);
        pj.lisaa(aloitus);  
        
        this.siEd = new HashMap<Koordinaatti,Koordinaatti>();
        siEd.put(this.alku, this.alku); // tehdään myöhemmin terminaatioehto tätä hyväksikäyttäen       
        this.kartta = kulkukelpoisuus;
    }
    
    public void etsiReitti() {
        while(pj.getPituus() > 0) {
            PrioSolmu kasiteltava = pj.ekaPois();
            
            if(kasiteltava.getSijainti() == this.kohde) {
                break;
            }
            
            PrioSolmu[] naapurit = haeNaapurit(kasiteltava);
            for(int i = 0; i < naapurit.length; i++) {
                PrioSolmu naapuri = naapurit[i];
                if(naapuri != null) {
                    pj.lisaa(naapuri);
                    siEd.put(naapuri.getSijainti(), kasiteltava.getSijainti());
                }
            }
            
        }
        //diilaa reitin kanssa jotenkin
    }
    
    public PrioSolmu[] haeNaapurit(PrioSolmu kasiteltava) {
        PrioSolmu[] pal = new PrioSolmu[4];
        Koordinaatti tama = kasiteltava.getSijainti();
        int tamaX = tama.getX();
        int tamaY = tama.getY();
        int i = 0;
        if(kartta[tamaY][tamaX + 1] == 1) {
            PrioSolmu uus1 = new PrioSolmu(tamaX+1, tamaY, this.kohdeX, this.kohdeY);
            pal[i] = uus1;
            i++;
        }
        if(kartta[tamaY + 1][tamaX] == 1) {
            PrioSolmu uus2 = new PrioSolmu(tamaX, tamaY+1, this.kohdeX, this.kohdeY);
            pal[i] = uus2;
            i++;
        }
        if(kartta[tamaY][tamaX - 1] == 1) {
            PrioSolmu uus3 = new PrioSolmu(tamaX-1, tamaY, this.kohdeX, this.kohdeY);
            pal[i] = uus3;
            i++;
        }
        if(kartta[tamaY - 1][tamaX] == 1) {
            PrioSolmu uus4 = new PrioSolmu(tamaX, -1, this.kohdeX, this.kohdeY);
            pal[i] = uus4;
            i++;
        }

        return pal;
    }
    
    public int[][] piirraReitti() {
        int korkeus = kartta.length;
        int leveys = kartta[0].length;
        for(int y = 0; y<korkeus; y++) {
            for(int x=0; x<leveys;x++) {
                //uusi.setRGB(x, y, path);
                //aseta karttaan pathiksi
            }
        }
        
        return kartta;
    }
    
    
}
