package tiralabra.reitinhaku;

import java.awt.image.BufferedImage;
import java.awt.Color;


public class KuvaProsessori {
    
    int pikselit[][];
    int kulkukelpoisuus[][];
    BufferedImage kuva;
    BufferedImage pikseliKuva;
    //int musta = 0;
    int musta = -16777216;
    int tumSininen = -16776961;
    int vihrea = -16744704;
    int harmaa = -1710619;
    int vaalSininen = -16757505;
    int path = -12336;
    
    public KuvaProsessori(BufferedImage kuva) {
        this.kuva = kuva;
        pikselit = new int[kuva.getHeight()][kuva.getWidth()];
        kulkukelpoisuus = new int[kuva.getHeight()][kuva.getWidth()];
    }
    
    public void kuvastaPikseleiksi() {
        int korkeus = kuva.getHeight();
        int leveys = kuva.getWidth();
        for(int y = 0; y < korkeus; y++) {
            for(int x = 0; x < leveys; x++) {
                int kordVari = kuva.getRGB(x, y);
                pikselit[y][x] = kordVari;
                if(kordVari == harmaa) {
                    kulkukelpoisuus[y][x] = 1;
                } else {
                    kulkukelpoisuus[y][x] = 0;
                }
                 
            }
        }
    }

    public int[][] getKulkukelpoisuus() {
        return kulkukelpoisuus;
    }

    public void setKulkukelpoisuus(int[][] kulkukelpoisuus) {
        this.kulkukelpoisuus = kulkukelpoisuus;
    }
    
    public void pikseleistaKuvaksi() {
        //värityyppi voi olla päin vittua
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
    
    public void maalaaVarilla() {
        int korkeus = pikselit.length;
        int leveys = pikselit[0].length;
        BufferedImage uusi = new BufferedImage(pikselit.length, pikselit[0].length, 2);
        for(int y = 0; y<korkeus; y++) {
            for(int x=0; x<leveys;x++) {
                uusi.setRGB(x, y, path);
            }
        }
        this.pikseliKuva = uusi;
    }
    
}


