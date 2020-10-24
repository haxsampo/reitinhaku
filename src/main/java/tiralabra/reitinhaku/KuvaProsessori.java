package tiralabra.reitinhaku;
import tiralabra.tietorakenteet.Koordinaatti;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Kuvaprosessori ottaa vastaan kuvia, joita voi käsitellä buffered image muodossa
 * Luokka tekee buffered imagesta pikselikoordinaatiston, tai toistepäin.
 * Arraylist kulkukelpoista käytetään vain siinä tapauksessa, että käyttäjä
 * antaa ohjelmalle syötteenä koordinaatit, jotka eivät ole kulkukelpoisia
 * tällöin ohjelma antaa satunnaisen arvon kulkukelpoisista.
 */
public class KuvaProsessori {
    private ArrayList<Koordinaatti> kulkukelpoiset;
    private int korkeus;
    private int leveys;
    private int pikselit[][];
    private BufferedImage kuva;
    private BufferedImage pikseliKuva;
    //Bootybay.png:ssä käytetyt väriarvot
    private int musta = -16777216;
    private int tumSininen = -16776961;
    private int vihrea = -16744704;
    private int harmaa = -1710619;
    private int vaalSininen = -16757505;
    private int path = -1236;
     
    /**
     * Konstruktori
     * @param kuva Kuva jota halutaan käyttää reitinetsimiskarttana
     */
    public KuvaProsessori(BufferedImage kuva) {
        this.kuva = kuva;
        pikselit = new int[kuva.getHeight()][kuva.getWidth()];
        this.kulkukelpoiset = new ArrayList();
    }
    
    /**
     * Luo kuvasta pikselikartan 
     */
    public void kuvastaPikseleiksi() {
        int korkeus = kuva.getHeight();
        int leveys = kuva.getWidth();
        this.korkeus = korkeus;
        this.leveys = leveys;
        for(int y = 0; y < korkeus; y++) {
            for(int x = 0; x < leveys; x++) {
                int kordVari = kuva.getRGB(x, y);
                pikselit[y][x] = kordVari;
                 if(kordVari==harmaa) {
                     Koordinaatti kelpaa = new Koordinaatti(x,y);
                     kulkukelpoiset.add(kelpaa);
                 }
            }
        }
    }

    /**
     * @return palauttaa kuvan korkeuden
     */
    public int getKorkeus() {
        return korkeus;
    }

    /**
     * @return palauttaa kuvan leveyden
     */
    public int getLeveys() {
        return leveys;
    }

    /**
     * @return palauttaa kulkukelpoiset koordinaatit
     */
    public ArrayList<Koordinaatti> getKulkukelpoiset() {
        return kulkukelpoiset;
    }
    
    /**
     * Luo uuden bufferedImagen pikselikartasta. Tarkoituksenmukaista on, että
     * karttaan on määritetty reitti värejä vaihtamalla tässä vaiheessa.
     */
    public void pikseleistaKuvaksi() {      
        int korkeus = pikselit.length;
        int leveys = pikselit[0].length;
        BufferedImage uusi = new BufferedImage(pikselit.length, pikselit[0].length, 2);
        for(int y = 0; y<korkeus; y++) {
            for(int x=0; x<leveys;x++) {
                uusi.setRGB(x, y, pikselit[y][x]);
            }
        }
        this.pikseliKuva = uusi;
    }

    /**
     * @return Palauttaa pikseleistä muodostetun BufferedImagen
     */
    public BufferedImage getPikseliKuva() {
        return pikseliKuva;
    }

    /**
     * @param pikseliKuva asettaa uuden bufferedimagen
     */
    public void setPikseliKuva(BufferedImage pikseliKuva) {
        this.pikseliKuva = pikseliKuva;
    }
    
    /**
     * @return Palauttaa BufferedImagesta muodostetun koordinaatiston
     */
    public int[][] getPikselit() {
        return pikselit;
    }
    
    /**
     * @param pikselit asettaa uuden koordinaatiston
     */
    public void setPikselit(int[][] pikselit) {
        this.pikselit = pikselit;
    }
    
    /**
     * Malaa koko kuvan annetulla variArvolla. Käytetty lähinnä debuggaukseen.
     */
    public void maalaaVarilla(int variArvo) {
        int korkeus = pikselit.length;
        int leveys = pikselit[0].length;
        BufferedImage uusi = new BufferedImage(pikselit.length, pikselit[0].length, 2);
        for(int y = 0; y<korkeus; y++) {
            for(int x=0; x<leveys;x++) {
                uusi.setRGB(x, y, variArvo);
                pikselit[y][x] = variArvo;
            }
        }
        this.pikseliKuva = uusi;
    }
    
    /**
     * @return Palauttaa satunnaisen koordinaatin kulkukelpoisista koordinaateista
     */
    public Koordinaatti palautaSatunnainen() {
        return kulkukelpoiset.get(getRandomNumber(kulkukelpoiset.size()-1,0));
        
    }
    
    /**
     * @param max
     * @param min
     * @return Palauttaa satunnaisen arvon väliltä max-min
     * Satunnaista arvoa käytetään vaan kulkukelpoisen koordinaatin etsimiseen
     * siinä tapauksessa, että käyttäjä antaa huonon koordinaatin; funktio
     * on olemassa käyttäjäkokemuksen helpottamiseksi.
     */
    public int getRandomNumber(int max, int min) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    /**
     * @param harmaa Asettaa väriarvon jota ohjelma käyttää kulkukelpoisena maastona
     */
    public void setHarmaa(int harmaa) {
        this.harmaa = harmaa;
    }
    
    /**
     * @return Palautaa väriarvon jota ohjelma käyttää kulkukelpoisena maastona
     */
    public int getHarmaa() {
        return this.harmaa;
    }
    
    /**
     * Kopioi annettavan int[][] arrayn 
     * @param kopioitava
     * @return
     */
    public int[][] kopioiArray(int[][] kopioitava) {
        int[][] uusi = new int[kopioitava.length][kopioitava[0].length];        
        for(int y=0;y<kopioitava.length;y++) {
            for(int x=0;x<kopioitava[0].length;x++) {
                uusi[y][x] = kopioitava[y][x];
            }
        }
        return uusi;
    }
    
}


