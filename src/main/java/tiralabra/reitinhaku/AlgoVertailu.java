package tiralabra.reitinhaku;
import algoritmit.Ahne;
import algoritmit.Atahti;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import tiralabra.tietorakenteet.Koordinaatti;

/*
 * Algovertailu antaa kyvyn ajaa samat koordinaatit samaan karttaan ja vertailla
 * A*:in ja GBF:in tuloksia.
 */
public class AlgoVertailu {   
    private String path = "C:/Users/toni_/Koulu/tiralabra/reitinhaku/src/main/java/tiralabra/reitinhaku/bootybay.png";
    private KuvaProsessori kp;
    private Kellottaja kello;  
    private int aX;
    private int aY;
    private int lX;
    private int lY;
    
    /*
     * Luo uuden ajanottajan.
     */
    public AlgoVertailu(){
        this.kello = new Kellottaja();
    }
    
    /*
     * Hakee kuvan ja prosessoi sen väriarvot pikselikartaksi kuvaprosessoriin 
     */
    public void haeKuva() {
        BufferedImage image = null;
        try {
          image = ImageIO.read(new File(path));
        }
        catch (Exception e) {
          e.printStackTrace();
          System.exit(1);
        }        
        KuvaProsessori kpUus = new KuvaProsessori(image);
        this.kp = kpUus;
        kp.kuvastaPikseleiksi();
    }
    
    /*
     * Luo yhdet satunnaiskoordinaatit ja ajaa niillä reitinetsinnät kummallekkin
     * algoritmille. Printtaa lopulta reittien pituudet ja käytetyt ajat
     */
    public double[] yksiVrt() {
        Koordinaatti kohde = kp.palautaSatunnainen();
        Koordinaatti alku = kp.palautaSatunnainen();
        Koordinaatti kohdekop = new Koordinaatti(kohde.getX(),kohde.getY());
        Koordinaatti alkukop = new Koordinaatti(alku.getX(),alku.getY());       
        this.aX = alku.getX();
        this.aY = alku.getY();
        this.lX = kohde.getX();
        this.lY = kohde.getY();        
        int[][] astarPikselit = kp.kopioiArray(kp.getPikselit());
        int[][] ahnePikselit = kp.kopioiArray(kp.getPikselit());
        Ahne gbf = new Ahne(this.aX, this.aY, this.lX, this.lY, ahnePikselit);
        kello.aloita();
        gbf.etsiReitti();
        gbf.piirraReitti();
        kello.lopeta();
        double gbfAika = kello.kuluma();
        int gbfPituus = gbf.getReitinPituus();        
        Atahti star = new Atahti(this.aX, this.aY, this.lX, this.lY, astarPikselit);
        kello.aloita();
        star.etsiReitti();
        star.piirraReitti();
        kello.lopeta();
        double starAika = kello.kuluma();
        int starPituus = star.getReitinPituus();
        System.out.println("GBF pituus: "+gbfPituus+" aika: "+gbfAika);
        System.out.println("A* pituus: "+starPituus+" aika: "+starAika);
        double[] pal = new double[6];
        pal[0] = gbfPituus;
        pal[1] = gbfAika;
        pal[2] = starPituus;
        pal[3] = starAika;
        pal[4] = (double) gbf.getKasitellytNaapurit();
        pal[5] = (double) star.getKasitellytNaapurit();
        return pal;
    }
    
    /*
    * Käyttää yksiVrt funktiota sata kertaa, tuotaa statistiikkaa
    */
    public void sataVrt() {
        double[] gbfAjat = new double[100];
        int[] gbfPituudet = new int[100];
        double[] atahtiAjat = new double[100];
        int[] atahtiPituudet = new int[100];
        int[] tahtiNaapurit = new int[100];
        int[] gbfNaapurit = new int[100];
        for(int i = 0; i<100;i++) {
            double[] palautettu = yksiVrt();
            gbfAjat[i] = palautettu[1];
            gbfPituudet[i] = (int)palautettu[0];
            atahtiAjat[i] = palautettu[3];
            atahtiPituudet[i] = (int)palautettu[2];
            tahtiNaapurit[i] = (int)palautettu[5];
            gbfNaapurit[i] = (int)palautettu[4];
        }           
        double gbfAjatKA= keskiArvoDouble(gbfAjat);
        double starAjatKA = keskiArvoDouble(atahtiAjat);
        int gbfPituusKA = keskiArvoInt(gbfPituudet);
        int starPituusKA = keskiArvoInt(atahtiPituudet);     
        int[][] tmp = new int[2][100];
        tmp[0] = gbfPituudet;
        tmp[1] = atahtiPituudet;
        int pituusEro = suurinPituusEro(tmp);
        int pieninPituusEro = pieninPituusEro(tmp);
        int naapuritGbfKA = keskiArvoInt(gbfNaapurit);
        int naapuritStarKA = keskiArvoInt(tahtiNaapurit);
        System.out.println("Arvot 100 toistolle:");
        System.out.println("GBF keskiarvot - pituus: "+gbfPituusKA+" GBF aika: "+gbfAjatKA+" GBF nodejen määrä: "+naapuritGbfKA);
        System.out.println("A* keskiarvot - pituus: "+starPituusKA+" A* aika: "+starAjatKA+" A* nodejen määrä: "+naapuritStarKA);
        System.out.println("Suurin ero reittien pituuksien välillä: "+pituusEro);
        System.out.println("Pienin ero reittien pituuksien välillä: "+pieninPituusEro);
    
    }
    
    /*
    * Palauttaa annetun listan keskiarvon
    */
    public double keskiArvoDouble(double[] arvot) {
        double yhteen = 0;
        for(int i = 0;i<100;i++) {          
            yhteen += arvot[i];
        }
        return yhteen/100;
    }
    
    /*
    * Palauttaa annetun listan keskiarvon
    */
    public int keskiArvoInt(int[] arvot) {
        int yhteen = 0;
        for(int i = 0;i<100;i++) {
            yhteen += arvot[i];
        }
        return yhteen/100;
    }
    
    /*
    * Palauttaa annetun listan korkeimman arvon
    */
    public double korkeinArvo(double[] arvot) {
        double korkein = 0;
        for(int i = 0;i<100;i++) {
            if(arvot[i]>korkein) {
                korkein = arvot[i];
            }
        }
        return korkein;
    }
    
    /*
    * Ottaa sisään listan jonka ensimmäisenä elementtinä GBF:llä tuotettu lista
    * pituuksia; toisena A*:lla tuotettu lista pituuksia.
    * Listat on rakennettu niin, että samassa indeksissä on samalla koordinaatilla
    *tehty haku -> vertaillaan arvoja per indeksi ja palautetaan suurin ero
    */
    public int suurinPituusEro(int[][] lista) {
        int[] gbfPituudet = lista[0];
        int[] starPituudet = lista[1];
        int suurinEro = 0;
        for(int i = 0;i<100;i++) {           
            int ero = gbfPituudet[i] - starPituudet[i];
            if(ero>suurinEro) {
                suurinEro = ero;
            }
        }
        return suurinEro;
    }
    
    /*
    * Ottaa sisään listan jonka ensimmäisenä elementtinä GBF:llä tuotettu lista
    * pituuksia; toisena A*:lla tuotettu lista pituuksia.
    * Listat on rakennettu niin, että samassa indeksissä on samalla koordinaatilla
    *tehty haku -> vertaillaan arvoja per indeksi ja palautetaan pienin ero
    */
    public int pieninPituusEro(int[][] lista) {
        int[] gbfPituudet = lista[0];
        int[] starPituudet = lista[1];
        int pieninEro =gbfPituudet[0] - starPituudet[0];
        for(int i = 1;i<100;i++) {           
            int ero = gbfPituudet[i] - starPituudet[i];
            if(ero<pieninEro) {
                pieninEro = ero;
            }
        }
        return pieninEro;
    } 
    
    /*
    * Palauttaa annetun listan korkeimman arvon
    */
    public int korkeinArvo(int[] arvot) {
        int korkein = 0;
        for(int i = 0;i<100;i++) {
            if(arvot[i]>korkein) {
                korkein = arvot[i];
            }
        }
        return korkein;
    }

    /*
     * @return Palauttaa reitin kuvaan
     */
    public String getPath() {
        return path;
    }

    /*
     * @param path Asettaa reitin kuvaan 
     */
    public void setPath(String path) {
        this.path = path;
    }
    
}
