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
        System.out.println("Algoritmivertailu = 0, vai visuaalinen reitti = 1? ");
        int vrt = Integer.parseInt(scanner.nextLine());
        if(vrt == 0) {
            System.out.println("Muu kuin testikuva? 0 = ei, 1=kyllä");
            int testikuvaVast = Integer.parseInt(scanner.nextLine());
            AlgoVertailu algVrt = new AlgoVertailu();
            if(testikuvaVast==1) {
                System.out.println("Anna polku kuvaan:");
                String path = scanner.nextLine();  
                algVrt.setPath(path);
            } else {
                System.out.println("Käytetään testikuvaa");
            }
            algVrt.haeKuva();
            algVrt.yksiVrt();
        } else if(vrt ==1) {
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
            Kuvantuotin kv = new Kuvantuotin(ax, ay, lx, ly, algo);
            kv.ajaYksi();
        } else {
            System.out.println("Epäselvä syöte");
        }       
  }
        
}
