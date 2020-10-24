package tiralabra.reitinhaku;

import tiralabra.ui.Kuvantuotin;

import java.util.Scanner;


/**
 * Ohjelman käyttöliittymä
 */
public class Tekstikayttis {
      
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Yksittäinen algoritmivertailu = 0, laajempi algoritmivertailu=1, vai visuaalinen reitti = 2? ");
        int vrt = Integer.parseInt(scanner.nextLine());
        System.out.println("Anna polku käytettävään kuvaan:");    
        String path = scanner.nextLine();
        if(vrt == 0) {
            AlgoVertailu algVrt = new AlgoVertailu();
            if(path.equals("booty")) {
                path = "C:/Users/toni_/Koulu/tiralabra/reitinhaku/src/main/java/tiralabra/reitinhaku/bootybay.png";
                algVrt.setPath(path);
            }
            algVrt.haeKuva();
            algVrt.yksiVrt();
        } else if(vrt ==2) {
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
                System.out.println("GBF valittu");
            } else if(algo.equals("A*")){
                System.out.println("A* valittu");
            } else {
                System.out.println("Syöte epäselvä, valitaan A*");
                algo = "A*";
            }
            Kuvantuotin kv = new Kuvantuotin(ax, ay, lx, ly, algo, path);
            kv.ajaYksi();
        } else if(vrt ==1){
            AlgoVertailu algVrt = new AlgoVertailu();
            if(path.equals("booty")) {
                path = "C:/Users/toni_/Koulu/tiralabra/reitinhaku/src/main/java/tiralabra/reitinhaku/bootybay.png";
                algVrt.setPath(path);
                algVrt.haeKuva();
                algVrt.sataVrt();
            }
        } else {
            System.out.println("Epäselvä syöte");
        }
  }
        
}
