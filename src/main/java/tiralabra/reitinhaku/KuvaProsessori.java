package tiralabra.reitinhaku;

import tiralabra.tietorakenteet.Koordinaatti;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


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
    ArrayList<Koordinaatti> kulkukelpoiset;
    int korkeus;
    int leveys;
    
    public KuvaProsessori(BufferedImage kuva) {
        this.kuva = kuva;
        pikselit = new int[kuva.getHeight()][kuva.getWidth()];
        System.out.println("KuvaprosessoriKonstruktori korkeus_leveys "+kuva.getHeight() + "_"+kuva.getWidth());
        this.kulkukelpoiset = new ArrayList();
    }
    
    public void kuvastaPikseleiksi() {
        int korkeus = kuva.getHeight();
        int leveys = kuva.getWidth();
        this.korkeus = korkeus;
        this.leveys = leveys;
        for(int y = 0; y < korkeus; y++) {
            for(int x = 0; x < leveys; x++) {
                int kordVari = kuva.getRGB(x, y);
                pikselit[y][x] = kordVari;
                 if(kordVari==harmaa) {
                     Koordinaatti kelpaa = new Koordinaatti(x,y);
                     kulkukelpoiset.add(kelpaa);
                 }
            }
        }
    }

    public int getKorkeus() {
        return korkeus;
    }

    public void setKorkeus(int korkeus) {
        this.korkeus = korkeus;
    }

    public int getLeveys() {
        return leveys;
    }

    public void setLeveys(int leveys) {
        this.leveys = leveys;
    }

    public ArrayList<Koordinaatti> getKulkukelpoiset() {
        return kulkukelpoiset;
    }

    public void setKulkukelpoiset(ArrayList<Koordinaatti> kulkukelpoiset) {
        this.kulkukelpoiset = kulkukelpoiset;
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
    
    public Koordinaatti palautaSatunnainen() {
        return kulkukelpoiset.get(getRandomNumber(0,kulkukelpoiset.size()-1));
        
    }
    
    public int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
}
    
}


