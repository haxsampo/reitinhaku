package algoritmit;

import java.util.HashSet;
import java.util.Set;
import tiralabra.tietorakenteet.DataKoordinaatti;
import tiralabra.tietorakenteet.Koordinaatti;
import tiralabra.tietorakenteet.PrioJono;
import tiralabra.tietorakenteet.PrioSolmu;


public class Atahti {
    
    int kohdeX;
    int kohdeY;       
    int alkuX;
    int alkuY;
    Koordinaatti alku;
    Koordinaatti kohde;
    PrioJono pj;
    DataKoordinaatti[][] kuljettu;
    
    int[][] variKartta;
    int reittiVari = -1690619;
    int harmaa = -1710619;
    
    public Atahti(int alkuX, int alkuY, int kohdeX, int kohdeY, int[][] kartta) {
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
        
        this.kuljettu = new DataKoordinaatti[kartta.length][kartta[0].length];
        DataKoordinaatti alkuKord = new DataKoordinaatti(alkuX, alkuY, alkuX, alkuY);
        alkuKord.setVari(reittiVari);
        alkuKord.setHinta(0);
        this.kuljettu[alkuY][alkuX] = alkuKord;
        this.variKartta = kartta;
    }
    
    public void etsiReitti() {
        while(pj.getPituus() >0) {
            PrioSolmu kasiteltava = pj.ekaPois();
            if(kasiteltava.getSijainti().equals(this.kohde)) {
                break;
            }
            Koordinaatti sij = kasiteltava.getSijainti();
            System.out.println("x: "+sij.getX()+ " y: "+ sij.getY());
            PrioSolmu[] naapurit = haeNaapurit(kasiteltava);
            
            for(int i = 0; i < naapurit.length;i++) {
                PrioSolmu naapuri = naapurit[i];
                int kasiteltavaX = kasiteltava.getSijainti().getX();
                int kasiteltavaY = kasiteltava.getSijainti().getY();
                int uusiHinta = kuljettu[kasiteltavaY][kasiteltavaX].getHinta()+1;
                if(naapuri != null) {  
                    DataKoordinaatti dk = kuljettu[naapuri.getSijainti().getY()][naapuri.getSijainti().getX()];
                    if(dk==null) {
                        int naapuriX = naapuri.getSijainti().getX();
                        int naapuriY = naapuri.getSijainti().getY();
                        DataKoordinaatti uusiDK = new DataKoordinaatti(naapuriX,naapuriY,kasiteltavaX, kasiteltavaY);
                        uusiDK.setHinta(naapuri.getEtaisyys()+uusiHinta);       
                        kuljettu[naapuriY][naapuriX] = uusiDK;
                        naapuri.setEtaisyys(naapuri.getEtaisyys()+uusiHinta);
                        pj.lisaa(naapuri);
                    } else if(uusiHinta < dk.getHinta()) {                        
                        kuljettu[naapuri.getSijainti().getY()][naapuri.getSijainti().getX()].setHinta(uusiHinta);
                        naapuri.setEtaisyys(naapuri.getEtaisyys()+uusiHinta);
                        pj.lisaa(naapuri);
                        kuljettu[naapuri.getSijainti().getY()][naapuri.getSijainti().getX()].setEdeltaja(kasiteltava.getSijainti());
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
        int i = 0;
        if(variKartta[tamaY][tamaX + 1] == harmaa) {
            PrioSolmu uus1 = new PrioSolmu(tamaX+1, tamaY, this.kohdeX, this.kohdeY);
            pal[i] = uus1;
            i++;
        }
        if(variKartta[tamaY + 1][tamaX] == harmaa) {
            PrioSolmu uus2 = new PrioSolmu(tamaX, tamaY+1, this.kohdeX, this.kohdeY);
            pal[i] = uus2;
            i++;
        }
        if(variKartta[tamaY][tamaX - 1] == harmaa) {
            PrioSolmu uus3 = new PrioSolmu(tamaX-1, tamaY, this.kohdeX, this.kohdeY);
            pal[i] = uus3;
            i++;
        }
        if(variKartta[tamaY - 1][tamaX] == harmaa) {
            PrioSolmu uus4 = new PrioSolmu(tamaX, tamaY-1, this.kohdeX, this.kohdeY);
            pal[i] = uus4;
            i++;
        }

        return pal;
    }
    
    public int[][] piirraReitti() {
        Koordinaatti kasiteltava = kuljettu[kohdeY][kohdeX].getTama();       
        variKartta[kohdeY][kohdeX] = reittiVari;
        while(true) {
            if(kasiteltava.getX() == alkuX && kasiteltava.getY() == alkuY) {
                variKartta[alkuY][alkuX] = reittiVari;
                break;
            }
            variKartta[kasiteltava.getY()][kasiteltava.getX()] = reittiVari;
            
            variKartta[kasiteltava.getY()+1][kasiteltava.getX()+1] = reittiVari;
            variKartta[kasiteltava.getY()-1][kasiteltava.getX()-1] = reittiVari;
            
            variKartta[kasiteltava.getY()+1][kasiteltava.getX()] = reittiVari;
            variKartta[kasiteltava.getY()][kasiteltava.getX()+1] = reittiVari;
            
            variKartta[kasiteltava.getY()][kasiteltava.getX()+1] = reittiVari;
            variKartta[kasiteltava.getY()+1][kasiteltava.getX()] = reittiVari;
            
            variKartta[kasiteltava.getY()-1][kasiteltava.getX()+1] = reittiVari;
            variKartta[kasiteltava.getY()+1][kasiteltava.getX()-1] = reittiVari;
            
            kasiteltava = kuljettu[kasiteltava.getY()][kasiteltava.getX()].getEdeltaja();
        }        
        return variKartta;
    }
    
}
