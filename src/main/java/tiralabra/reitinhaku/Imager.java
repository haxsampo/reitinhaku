package tiralabra.reitinhaku;

import algoritmit.Ahne;
import tiralabra.tietorakenteet.Koordinaatti;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;


public class Imager {
      
    public static void main(String[] args) throws Exception{
        new Imager();
  }
        
 public Imager() throws Exception
  {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
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
        Ahne gbf = new Ahne(260, 373, 601,900, kp.getPikselit());
        gbf.etsiReitti();
        kp.setPikselit(gbf.piirraReitti());
          
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
