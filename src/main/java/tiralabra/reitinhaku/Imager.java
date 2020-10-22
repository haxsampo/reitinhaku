package tiralabra.reitinhaku;

import algoritmit.Ahne;
import algoritmit.Atahti;
import tiralabra.tietorakenteet.Koordinaatti;
import tiralabra.ui.Kuvantuotin;

import java.util.Scanner;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;


public class Imager {
      
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        
        
        //260, 373, 600, 900
        while(true) {
            System.out.println("Aloituskoordinaatti x:");
            int ax = Integer.parseInt(scanner.nextLine());
            System.out.println("Aloituskoordinaatti y:");
            int ay = Integer.parseInt(scanner.nextLine());
            System.out.println("Kohdekoordinaatti x:");
            int lx = Integer.parseInt(scanner.nextLine());
            System.out.println("Kohdekoordinaatti y:");
            int ly = Integer.parseInt(scanner.nextLine());
            System.out.println("GBF vai A*");
            String algo = scanner.nextLine();
            if(algo.equals("GBF")) {
                System.out.println("Greedy Best First");
            } else if(algo.equals("A*")){
                System.out.println("A*");
            } else {
                System.out.println("Syöte epäselvä, valitaan A*");
            }
           new Kuvantuotin(ax, ay, lx, ly, algo);
        }
        
        
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
        //Ahne gbf = new Ahne(260, 373, 601,900, kp.getPikselit());
        //gbf.etsiReitti();
        Atahti star = new Atahti(260,373,601,900,kp.getPikselit());
        kp.setPikselit(star.piirraReitti());
          
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
