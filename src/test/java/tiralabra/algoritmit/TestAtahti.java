
package tiralabra.algoritmit;

import algoritmit.Atahti;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.reitinhaku.KuvaProsessori;

public class TestAtahti {
    
    Atahti star;
    KuvaProsessori kp;
    int[][] alkupPikselit;
    
    int aX;
    int aY;
    int kX;
    int kY;
    
    
    @Before
    public void esiHommat() {
        this.aX = 0;
        this.aY = 0;
        this.kX = 91;
        this.kY = 81;
    
        BufferedImage image = null;
        try{
          image = ImageIO.read(new File("C:/Users/toni_/Koulu/tiralabra/reitinhaku/src/main/java/tiralabra/reitinhaku/testikartta.png"));
        }
        catch (Exception e){
          e.printStackTrace();
          System.exit(1);
        }
        
        KuvaProsessori kp = new KuvaProsessori(image);
        this.kp = kp;
        kp.setHarmaa(image.getRGB(0,0));
        kp.kuvastaPikseleiksi();
        this.alkupPikselit=kp.kopioiArray(kp.getPikselit());
                
        this.star = new Atahti(aX,aY,kX,kY,kp.getPikselit());
        star.setHarmaa(image.getRGB(0,0));
    }
    
    @Test
    public void AtahtiReitinPituus() {
        star.etsiReitti();
        star.piirraReitti();
        assertEquals(star.getReitinPituus(),208);               
    }
    
    @Test
    public void AtahtiReitinPituus2() {
        star = new Atahti(0,0,43,93,alkupPikselit);
        star.setHarmaa(kp.getHarmaa());
        star.etsiReitti();
        star.piirraReitti();
        assertEquals(star.getReitinPituus(),146);               
    }
    
}
