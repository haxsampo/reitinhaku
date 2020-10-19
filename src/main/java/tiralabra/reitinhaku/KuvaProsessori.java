package tiralabra.reitinhaku;

import java.awt.image.BufferedImage;
import java.awt.Color;


public class KuvaProsessori {
    
    int pikselit[][];
    BufferedImage kuva;
    BufferedImage pikseliKuva;
    //int musta = 0;
    int musta = -16777216;
    int tumSininen = -16776961;
    int vihrea = -16744704;
    int harmaa = -1710619;
    int vaalSininen = -16757505;
    int path = -1236;
    
    public KuvaProsessori(BufferedImage kuva) {
        this.kuva = kuva;
        pikselit = new int[kuva.getHeight()][kuva.getWidth()];
        System.out.println("KuvaprosessoriKonstruktori korkeus_leveys "+kuva.getHeight() + "_"+kuva.getWidth());
    }
    
    public void kuvastaPikseleiksi() {
        int korkeus = kuva.getHeight();
        int leveys = kuva.getWidth();
        for(int y = 0; y < korkeus; y++) {
            for(int x = 0; x < leveys; x++) {
                int kordVari = kuva.getRGB(x, y);
                pikselit[y][x] = kordVari;
                 
            }
        }
    }
    
    public void pikseleistaKuvaksi() {      
        int korkeus = pikselit.length;
        int leveys = pikselit[0].length;
        BufferedImage uusi = new BufferedImage(pikselit.length, pikselit[0].length, 2);
        for(int y = 0; y<korkeus; y++) {
            for(int x=0; x<leveys;x++) {
                uusi.setRGB(x, y, pikselit[y][x]);
            }
        }
        this.pikseliKuva = uusi;
    }

    public BufferedImage getPikseliKuva() {
        return pikseliKuva;
    }

    public void setPikseliKuva(BufferedImage pikseliKuva) {
        this.pikseliKuva = pikseliKuva;
    }
    
    public int[][] getPikselit() {
        return pikselit;
    }
    
    public void setPikselit(int[][] pikselit) {
        this.pikselit = pikselit;
    }
    
    public void maalaaVarilla() {
        int korkeus = pikselit.length;
        int leveys = pikselit[0].length;
        BufferedImage uusi = new BufferedImage(pikselit.length, pikselit[0].length, 2);
        for(int y = 0; y<korkeus; y++) {
            for(int x=0; x<leveys;x++) {
                uusi.setRGB(x, y, -1710619);
                pikselit[y][x] = -1710619;
            }
        }
        this.pikseliKuva = uusi;
    }
    
}


