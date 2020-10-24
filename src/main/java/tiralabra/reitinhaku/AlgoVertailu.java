package tiralabra.reitinhaku;

import algoritmit.Ahne;
import algoritmit.Atahti;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import tiralabra.tietorakenteet.Koordinaatti;


public class AlgoVertailu {
    
    String path = "C:/Users/toni_/Koulu/tiralabra/reitinhaku/src/main/java/tiralabra/reitinhaku/bootybay.png";
    KuvaProsessori kp;
    Kellottaja kello;
    
    int aX;
    int aY;
    int lX;
    int lY;
    
    public AlgoVertailu(){
        this.kello = new Kellottaja();
    }
    
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    /*public int[][] kopioiArray(int[][] kopioitava) {
        int[][] uusi = new int[kopioitava.length][kopioitava[0].length];
        
        for(int y=0;y<kopioitava.length;y++) {
            for(int x=0;x<kopioitava[0].length;x++) {
                uusi[y][x] = kopioitava[y][x];
            }
        }
        return uusi;
    }*/
}
