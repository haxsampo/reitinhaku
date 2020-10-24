package algoritmit;
import tiralabra.tietorakenteet.PrioJono;
import tiralabra.tietorakenteet.PrioSolmu;
import tiralabra.tietorakenteet.Koordinaatti;

/*
 * Greedy Best First algoritmi
 */
public class Ahne {       
    private int kohdeX;
    private int kohdeY;       
    private int alkuX;
    private int alkuY;
    private Koordinaatti alku;
    private Koordinaatti kohde;
    private PrioJono pj;
    private Koordinaatti[][] kuljettu; //mistä koordinaatista tultu kuhunkin koordinaattiin 
    private int reittiVari = -1690619; //määrittää kartalle piirrettävän reitin värin RGB
    private int harmaa = -1710619; //määrittää kulkukelpoisuuden
    private int reitinPituus;
    private int[][] kartta;
    
    /*
     *  Konstruktori
     * @param alkuX Aloituskoordinaatin X arvo
     * @param alkuY Aloituskoordinaatin Y arvo
     * @param kohdeX Kohdekoordinaatin X arvo
     * @param kohdeY Kohdekoordinaatin Y arvo
     * @param kartta sisältää väri-informaation per kordiinaatti - käytetään
     * kulkukelpoisuuden arvioimiseen 
     */
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
    
    /*
     * Etsii reitin ja tallentaa sen kuljettu[][] listojen listaan
     */
    public void etsiReitti() {
        this.reitinPituus = 0;
        while(pj.getPituus() > 0) {
            PrioSolmu kasiteltava = pj.ekaPois();
            if(kasiteltava.getSijainti().equals( this.kohde)) {
                break;
            }
            Koordinaatti sij = kasiteltava.getSijainti();
            PrioSolmu[] naapurit = haeNaapurit(kasiteltava);           
            for(int i = 0; i < naapurit.length; i++) {                
                PrioSolmu naapuri = naapurit[i];
                if(naapuri != null) {
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
    
    /*
     * @param kasiteltava hakee solmulle naapurit ylempää, alempaa, oikealta ja vasemmalta
     * @return palauttaa vakiopituisen listan jonka elementit joko PrioSolmuja,
     * tai null(jos naapuri ei ole kulkukelpoinen, värin perusteella).
     */
    public PrioSolmu[] haeNaapurit(PrioSolmu kasiteltava) {
        PrioSolmu[] pal = new PrioSolmu[4];
        Koordinaatti tama = kasiteltava.getSijainti();
        int tamaX = tama.getX();
        int tamaY = tama.getY();
        int i = 0;
        if(kartta[tamaY][tamaX + 1] == harmaa) {
            PrioSolmu uus1 = new PrioSolmu(tamaX+1, tamaY, this.kohdeX, this.kohdeY);
            pal[i] = uus1;
            i++;
        }
        if(kartta[tamaY + 1][tamaX] == harmaa) {
            PrioSolmu uus2 = new PrioSolmu(tamaX, tamaY+1, this.kohdeX, this.kohdeY);
            pal[i] = uus2;
            i++;
        }
        if(kartta[tamaY][tamaX - 1] == harmaa) {
            PrioSolmu uus3 = new PrioSolmu(tamaX-1, tamaY, this.kohdeX, this.kohdeY);
            pal[i] = uus3;
            i++;
        }
        if(kartta[tamaY - 1][tamaX] == harmaa) {
            PrioSolmu uus4 = new PrioSolmu(tamaX, tamaY-1, this.kohdeX, this.kohdeY);
            pal[i] = uus4;
            i++;
        }
        return pal;
    }
    
    /*
     * Piirtää kartalle oikeisiin positioihin reittivärillä kuljetun reitin
     * reitin pituus lasketaan täällä
     * @return palauttaa karttakoordinaatiston johon on piiretty kuljettu reitti
     * kartassa on muuten alkuperäisen väriarvot
     */
    public int[][] piirraReitti() {
        Koordinaatti kasiteltava = kuljettu[kohdeY][kohdeX];       
        kartta[kohdeY][kohdeX] = reittiVari;
        int nodeMaara = 1;
        while(true) {
            if(kasiteltava.getX() == alkuX && kasiteltava.getY() == alkuY) {
                kartta[alkuY][alkuX] = reittiVari;
                break;
            }
            kartta[kasiteltava.getY()][kasiteltava.getX()] = reittiVari;           
            kasiteltava = kuljettu[kasiteltava.getY()][kasiteltava.getX()];
            nodeMaara++;
        }
        this.reitinPituus = nodeMaara;
        return kartta;
    }

    /*
     * @return palauttaa kuljetun reitin pituuden
     */
    public int getReitinPituus() {
        return reitinPituus;
    }
}
