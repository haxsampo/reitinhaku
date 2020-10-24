package tiralabra.ui;
import algoritmit.Ahne;
import algoritmit.Atahti;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import tiralabra.reitinhaku.Kellottaja;
import tiralabra.reitinhaku.KuvaProsessori;
import tiralabra.tietorakenteet.Koordinaatti;

/**
 * Tuottaa kuvan BufferedImagesta
 * @author toni_
 */
public class Kuvantuotin {
    private int aX;
    private int aY;
    private int lX;
    private int lY;
    private String valittuAlgo;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    /**
     * Konstruktori
     * @param alkuX aloituskoordinaatti x
     * @param alkuY aloituskoordinaatti y
     * @param loppuX kohdekoordinaatti x
     * @param loppuY kohdekoordinaatti y
     * @param algo mitä algoritmia käytetään
     * @throws Exception
     */
    public Kuvantuotin(int alkuX, int alkuY, int loppuX, int loppuY, String algo, String path) throws Exception{
        this.aX = alkuX;
        this.aY = alkuY;
        this.lX = loppuX;
        this.lY = loppuY;
        this.valittuAlgo = algo;
        this.path = path;
        if(path.equals("booty")){
            this.path = "C:/Users/toni_/Koulu/tiralabra/reitinhaku/src/main/java/tiralabra/reitinhaku/bootybay.png";
        }
    }
    
    /**
     * Luo tarvittavat elementit, rullaa algoritmit ja pullauttaa kuvan.
     */
    public void ajaYksi() {
        SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame editorFrame = new JFrame("Reitinetsin");
        editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);        
        BufferedImage image = null;
        try{
          image = ImageIO.read(new File(path));
        }
        catch (Exception e){
          e.printStackTrace();
          System.exit(1);
        }
        KuvaProsessori kp = new KuvaProsessori(image);
        kp.kuvastaPikseleiksi();
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
        int alkuKordVari = kp.getPikselit()[aY][aX];
        int kohdeKordVari = kp.getPikselit()[lY][lX];
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
        Kellottaja kello = new Kellottaja();
        if(valittuAlgo.equals("GBF")) {
            Ahne gbf = new Ahne(aX, aY, lX, lY, kp.getPikselit());
            kello.aloita();
            gbf.etsiReitti();
            int[][] piksgbf = gbf.piirraReitti();
            kello.lopeta();
            System.out.println(kello.kuluma());
            kp.setPikselit(piksgbf);
        } else {
            Atahti star = new Atahti(aX, aY, lX,lY, kp.getPikselit());
            kello.aloita();
            star.etsiReitti();
            int[][] piksUus = star.piirraReitti();
            kello.lopeta();
            System.out.println(kello.kuluma());
            kp.setPikselit(piksUus);
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
