package algoritmit;

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
    
    int leveys;
    int korkeus;
    
    PrioJono pj;
    int[][] cost_so_far;
    Koordinaatti[][] came_from;
    
    int reitinPituus;
    
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
        this.korkeus = kartta.length;
        this.leveys = kartta[0].length;
        
        PrioJono pj = new PrioJono(this.kohdeX, this.kohdeY);
        this.pj = pj;
        PrioSolmu aloitus = new PrioSolmu(this.alku, this.kohde);
        pj.lisaa(aloitus); 
        
        DataKoordinaatti alkuKord = new DataKoordinaatti(alkuX, alkuY, alkuX, alkuY);
        alkuKord.setVari(reittiVari);
        alkuKord.setHinta(0);
        this.variKartta = kartta;
        this.came_from = new Koordinaatti[kartta.length][kartta[0].length];
        this.cost_so_far = new int[kartta.length][kartta[0].length];
    }
    
    public void etsiReitti() {
        this.reitinPituus = 0;
        while(pj.getPituus() >0) {
            PrioSolmu kasiteltava = pj.ekaPois();
            if(kasiteltava.getSijainti().equals(this.kohde)) {
                break;
            }
            Koordinaatti sij = kasiteltava.getSijainti();
            //System.out.println("x: "+sij.getX()+ " y: "+ sij.getY());
            PrioSolmu[] naapurit = haeNaapurit(kasiteltava);
            
            for(int i = 0; i < naapurit.length;i++) {
                PrioSolmu naapuri = naapurit[i];
                if(naapuri != null) {
                    int kasiteltavaX = kasiteltava.getSijainti().getX();
                    int kasiteltavaY = kasiteltava.getSijainti().getY();
                    int naapuriX = naapuri.getSijainti().getX();
                    int naapuriY = naapuri.getSijainti().getY();
                    
                    int uusiHinta = cost_so_far[kasiteltavaY][kasiteltavaX] + 1;
                    int costNext = cost_so_far[naapuriY][naapuriX];
                    if( costNext== 0 || uusiHinta<costNext) {
                        cost_so_far[naapuriY][naapuriX] = uusiHinta;
                        int prioriteettiArvo = uusiHinta + etaisyys(kohde, naapuri.getSijainti());
                        PrioSolmu uusPrio = new PrioSolmu(naapuriX, naapuriY, prioriteettiArvo);
                        pj.lisaa(uusPrio);
                        if(naapuriY == kohdeY && naapuriX == kohdeX) {
                        }
                        came_from[naapuriY][naapuriX] = sij;
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
        if(!(tamaX + 1 > leveys-1)) {
            if(variKartta[tamaY][tamaX + 1] == harmaa) {
                PrioSolmu uus1 = new PrioSolmu(tamaX+1, tamaY, this.kohdeX, this.kohdeY);
                pal[i] = uus1;
                i++;
            }
        }        
        if(!(tamaY+1>korkeus-1)) {
            if(variKartta[tamaY + 1][tamaX] == harmaa) {
                PrioSolmu uus2 = new PrioSolmu(tamaX, tamaY+1, this.kohdeX, this.kohdeY);
                pal[i] = uus2;
                i++;
            }
        }        
        if(!(tamaX-1 < 0)){
            if(variKartta[tamaY][tamaX - 1] == harmaa) {
                PrioSolmu uus3 = new PrioSolmu(tamaX-1, tamaY, this.kohdeX, this.kohdeY);
                pal[i] = uus3;
                i++;
            }
        }
        if(!(tamaY-1 <0)) {
          if(variKartta[tamaY - 1][tamaX] == harmaa) {
                PrioSolmu uus4 = new PrioSolmu(tamaX, tamaY-1, this.kohdeX, this.kohdeY);
                pal[i] = uus4;
                i++;
            }  
        }
        

        return pal;
    }
    
    public int[][] piirraReitti() {
        Koordinaatti kasiteltava = came_from[kohdeY][kohdeX];
        System.out.println(kasiteltava);
        variKartta[kohdeY][kohdeX] = reittiVari;
        int nodeMaara = 1;
        while(true) {
            if(kasiteltava.getX() == alkuX && kasiteltava.getY() == alkuY) {
                variKartta[alkuY][alkuX] = reittiVari;
                break;
            }
            //Kohde
            variKartta[kasiteltava.getY()][kasiteltava.getX()] = reittiVari;
            
            //Reitin laajentaminen
            //variKartta[kasiteltava.getY()+1][kasiteltava.getX()+1] = reittiVari;
            //variKartta[kasiteltava.getY()-1][kasiteltava.getX()-1] = reittiVari;
            
            //variKartta[kasiteltava.getY()+1][kasiteltava.getX()] = reittiVari;
            //variKartta[kasiteltava.getY()][kasiteltava.getX()+1] = reittiVari;
            
            //variKartta[kasiteltava.getY()][kasiteltava.getX()+1] = reittiVari;
            //variKartta[kasiteltava.getY()+1][kasiteltava.getX()] = reittiVari;
            
            //variKartta[kasiteltava.getY()-1][kasiteltava.getX()+1] = reittiVari;
            //variKartta[kasiteltava.getY()+1][kasiteltava.getX()-1] = reittiVari;
            
            kasiteltava = came_from[kasiteltava.getY()][kasiteltava.getX()];
            nodeMaara++;
        }        
        //System.out.println("Reitin pituus: "+nodeMaara);
        this.reitinPituus = nodeMaara;
        return variKartta;
    }
    
    public int etaisyys(Koordinaatti sij, Koordinaatti kohd) {
        
        int xOsa = kohd.getX() - sij.getX();
        int yOsa = kohd.getY() - sij.getY();
        int etaisyys = abs(xOsa) + abs(yOsa);
        return etaisyys;
    }
    
    private int abs(int n) {
        return n > 0 ? n : -n;
    }

    public int getReitinPituus() {
        return reitinPituus;
    }

    public void setReitinPituus(int reitinPituus) {
        this.reitinPituus = reitinPituus;
    }

    public int getHarmaa() {
        return harmaa;
    }

    public void setHarmaa(int harmaa) {
        this.harmaa = harmaa;
    }
    
}
