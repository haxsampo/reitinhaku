package tiralabra.ui;

import algoritmit.Ahne;
import algoritmit.Atahti;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import tiralabra.reitinhaku.KuvaProsessori;
import tiralabra.tietorakenteet.Koordinaatti;

public class Kuvantuotin {
    
    public Kuvantuotin(int alkuX, int alkuY, int loppuX, int loppuY, String algo) throws Exception{
        SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        int aX = alkuX;
        int aY = alkuY;
        int lX = loppuX;
        int lY = loppuY;
          
        JFrame editorFrame = new JFrame("Image Demo");
        editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        BufferedImage image = null;
        try{
          image = ImageIO.read(new File("C:/Users/toni_/Koulu/tiralabra/reitinhaku/src/main/java/tiralabra/reitinhaku/bootybay.png"));
        }
        catch (Exception e){
          e.printStackTrace();
          System.exit(1);
        }
        KuvaProsessori kp = new KuvaProsessori(image);
        kp.kuvastaPikseleiksi();
        //kp.maalaaVarilla();
        //260, 373 601,900
        if(kp.getLeveys() < aX || kp.getKorkeus() < aY) {
            System.out.println("Aloituskoordinaatit kuvan ulkopuolella");
            Koordinaatti alku = kp.palautaSatunnainen();
            aX = alku.getX();
            aY = alku.getY();
            System.out.println("Uusi satunnainen aloituskoordinaatti "+alku);
        }
        if(kp.getLeveys() < lX || kp.getKorkeus() < lY ) {
            System.out.println("Kohdekoordinaatit kuvan ulkopuolella");
            Koordinaatti kohde = kp.palautaSatunnainen();
            lX = kohde.getX();
            lY = kohde.getY();
            System.out.println("Uusi satunnainen kohdekoordinaatti "+kohde);
        }
        
        int harmaa = -1710619;
        int alkuKordVari = kp.getPikselit()[alkuY][alkuX];
        int kohdeKordVari = kp.getPikselit()[loppuY][loppuX];
        if(alkuKordVari!=harmaa) {
            System.out.println("Aloituskoordinaatit eivät ole kuljettavalla alueella");
            Koordinaatti alku = kp.palautaSatunnainen();
            aX = alku.getX();
            aY = alku.getY();
            System.out.println("Uusi satunnainen aloituskoordinaatti "+alku);
        }
        if(kohdeKordVari!=harmaa) {
            System.out.println("Kohdekoordinaatit eivät ole kuljettavalla alueella");
            Koordinaatti kohde = kp.palautaSatunnainen();
            lX = kohde.getX();
            lY = kohde.getY();
            System.out.println("Uusi satunnainen kohdekoordinaatti "+kohde);
        }
        
        if(algo.equals("GBF")) {
            Ahne gbf = new Ahne(aX, aY, lX, lY, kp.getPikselit());
            gbf.etsiReitti();
            kp.setPikselit(gbf.piirraReitti());
        } else {
            Atahti star = new Atahti(aX, aY, lX,lY, kp.getPikselit());
            star.etsiReitti();
            kp.setPikselit(star.piirraReitti());
        }
        
          
        kp.pikseleistaKuvaksi();
        
        image = kp.getPikseliKuva();
        
        
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

        editorFrame.pack();
        editorFrame.setLocationRelativeTo(null);
        editorFrame.setVisible(true);
      }
    });
    }
}
