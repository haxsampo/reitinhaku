
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
    PrioJono pj;
    Koordinaatti[][] kuljettu; //mistä koordinaatista tultu kuhunkin koordinaattiin 
    int reittiVari = -1690619;
    int harmaa = -1710619;
    
    int[][] kartta;
    
    public Ahne(int alkuX, int alkuY, int kohdeX, int kohdeY, int[][] kartta) {
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
            
        this.kartta = kartta;
        kuljettu = new Koordinaatti[kartta.length][kartta[0].length];
        kuljettu[alkuY][alkuX] = this.alku;// tehdään myöhemmin terminaatioehto tätä hyväksikäyttäen  
    }
    
    public void etsiReitti() {
        while(pj.getPituus() > 0) {
            PrioSolmu kasiteltava = pj.ekaPois();
            if(kasiteltava.getSijainti().equals( this.kohde)) {
                break;
            }
            if(kasiteltava.getSijainti().getX() == this.kohdeX && kasiteltava.getSijainti().getY() == this.kohdeY) {
                break;
            }
            
            PrioSolmu[] naapurit = haeNaapurit(kasiteltava);
            
            for(int i = 0; i < naapurit.length; i++) {                
                PrioSolmu naapuri = naapurit[i];
                if(naapuri != null) {
                    //System.out.println(naapuri.getSijainti().getX()+" "+naapuri.getSijainti().getY() +" - "+this.siEd.keySet());
                    int naapuriX = naapuri.getSijainti().getX();
                    int naapuriY = naapuri.getSijainti().getY();
                    if(kuljettu[naapuriY][naapuriX] == null) {
                        pj.lisaa(naapuri);
                        kuljettu[naapuriY][naapuriX] = kasiteltava.getSijainti();
                    }
                }
            }
            
        }
    }
    
    public PrioSolmu[] haeNaapurit(PrioSolmu kasiteltava) {
        PrioSolmu[] pal = new PrioSolmu[4];
        Koordinaatti tama = kasiteltava.getSijainti();
        int tamaX = tama.getX();
        int tamaY = tama.getY();
        System.out.println("TamaX: "+ tamaX + " tamaY: "+ tamaY);
        int i = 0;
        if(kartta[tamaY][tamaX + 1] == harmaa) {
            PrioSolmu uus1 = new PrioSolmu(tamaX+1, tamaY, this.kohdeX, this.kohdeY);
            System.out.println(i+" - "+uus1.getSijainti().getX()+" "+ uus1.getSijainti().getY() + "   kartta:" + kartta[tamaY][tamaX + 1]);
            pal[i] = uus1;
            i++;
        }
        if(kartta[tamaY + 1][tamaX] == harmaa) {
            PrioSolmu uus2 = new PrioSolmu(tamaX, tamaY+1, this.kohdeX, this.kohdeY);
            System.out.println(i+" - "+uus2.getSijainti().getX()+" "+ uus2.getSijainti().getY()+ "   kartta:" +kartta[tamaY + 1][tamaX]);
            pal[i] = uus2;
            i++;
        }
        if(kartta[tamaY][tamaX - 1] == harmaa) {
            PrioSolmu uus3 = new PrioSolmu(tamaX-1, tamaY, this.kohdeX, this.kohdeY);
            System.out.println(i+" - "+uus3.getSijainti().getX()+" "+ uus3.getSijainti().getY()+ "   kartta:" +kartta[tamaY][tamaX - 1]);
            pal[i] = uus3;
            i++;
        }
        if(kartta[tamaY - 1][tamaX] == harmaa) {
            PrioSolmu uus4 = new PrioSolmu(tamaX, tamaY-1, this.kohdeX, this.kohdeY);
            System.out.println(i+" - "+uus4.getSijainti().getX()+" "+ uus4.getSijainti().getY()+ "   kartta:" +kartta[tamaY - 1][tamaX]);
            pal[i] = uus4;
            i++;
        }

        return pal;
    }
    
    public int[][] piirraReitti() {
        Koordinaatti kasiteltava = kuljettu[kohdeY][kohdeX];       
        kartta[kohdeY][kohdeX] = reittiVari;
        while(true) {
            System.out.println("piirraReitti kasiteltava: "+ kasiteltava);
            if(kasiteltava.getX() == alkuX && kasiteltava.getY() == alkuY) {
                kartta[alkuY][alkuX] = reittiVari;
                break;
            }
            kartta[kasiteltava.getY()][kasiteltava.getX()] = reittiVari;//OIKEA
            
            kartta[kasiteltava.getY()+1][kasiteltava.getX()+1] = reittiVari;
            kartta[kasiteltava.getY()-1][kasiteltava.getX()-1] = reittiVari;
            
            kartta[kasiteltava.getY()+1][kasiteltava.getX()] = reittiVari;
            kartta[kasiteltava.getY()][kasiteltava.getX()+1] = reittiVari;
            
            kartta[kasiteltava.getY()][kasiteltava.getX()+1] = reittiVari;
            kartta[kasiteltava.getY()+1][kasiteltava.getX()] = reittiVari;
            
            kartta[kasiteltava.getY()-1][kasiteltava.getX()+1] = reittiVari;
            kartta[kasiteltava.getY()+1][kasiteltava.getX()-1] = reittiVari;
            
            kasiteltava = kuljettu[kasiteltava.getY()][kasiteltava.getX()];
        }
        
        /*
        for(Koordinaatti kord : siEd.keySet()) {
            System.out.println(kord.getX() + "   "+ kord.getY());
            kartta[kord.getY()][kord.getX()] = reittiVari;
            
            kartta[kord.getX()+1][kord.getX()+1] = reittiVari;
            kartta[kord.getY()-1][kord.getX()-1] = reittiVari;
            
            kartta[kord.getY()+1][kord.getX()] = reittiVari;
            kartta[kord.getY()][kord.getX()+1] = reittiVari;
            
            kartta[kord.getY()][kord.getX()-1] = reittiVari;           
            kartta[kord.getY()-1][kord.getX()] = reittiVari;
            
            kartta[kord.getY()+1][kord.getX()-1] = reittiVari;
            kartta[kord.getY()-1][kord.getX()+1] = reittiVari;
            
            kartta[kord.getY()][kord.getX()] = reittiVari;
            kartta[kord.getY()][kord.getX()] = reittiVari;
        }*/
        
        return kartta;
    }
    
    
}
