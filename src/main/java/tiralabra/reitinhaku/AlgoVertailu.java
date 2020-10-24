package tiralabra.reitinhaku;
import algoritmit.Ahne;
import algoritmit.Atahti;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import tiralabra.tietorakenteet.Koordinaatti;

/**
 * Algovertailu antaa kyvyn ajaa samat koordinaatit samaan karttaan ja vertailla
 * A*:in ja GBF:in tuloksia
 * @author toni_
 */
public class AlgoVertailu {   
    private String path = "C:/Users/toni_/Koulu/tiralabra/reitinhaku/src/main/java/tiralabra/reitinhaku/bootybay.png";
    private KuvaProsessori kp;
    private Kellottaja kello;  
    private int aX;
    private int aY;
    private int lX;
    private int lY;
    
    /**
     * Luo uuden ajanottajan.
     */
    public AlgoVertailu(){
        this.kello = new Kellottaja();
    }
    
    /**
     * Hakee kuvan ja prosessoi sen väriarvot pikselikartaksi kuvaprosessoriin 
     */
    public void haeKuva() {
        BufferedImage image = null;
        try{
          image = ImageIO.read(new File(path));
        }
        catch (Exception e){
          e.printStackTrace();
          System.exit(1);
        }
        
        KuvaProsessori kpUus = new KuvaProsessori(image);
        this.kp = kpUus;
        kp.kuvastaPikseleiksi();
    }
    
    /**
     * Luo yhdet satunnaiskoordinaatit ja ajaa niillä reitinetsinnät kummallekkin
     * algoritmille. Printtaa lopulta reittien pituudet ja käytetyt ajat
     */
    public void yksiVrt() {
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
    }

    /**
     * @return Palauttaa reitin kuvaan
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path Asettaa reitin kuvaan 
     */
    public void setPath(String path) {
        this.path = path;
    }
    
}
