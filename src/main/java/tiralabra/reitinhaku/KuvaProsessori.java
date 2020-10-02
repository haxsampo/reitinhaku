package tiralabra.reitinhaku;

import java.awt.image.BufferedImage;


public class KuvaProsessori {
    
    int pikselit[][];
    BufferedImage kuva;
    BufferedImage pikseliKuva;
    
    public KuvaProsessori(BufferedImage kuva) {
        this.kuva = kuva;
        pikselit = new int[kuva.getHeight()][kuva.getWidth()];
    }
    
    public void kuvastaPikseleiksi() {
        int korkeus = kuva.getHeight();
        int leveys = kuva.getWidth();
        for(int y = 0; y < korkeus; y++) {
            for(int x = 0; x < leveys; x++) {
                pikselit[y][x] = kuva.getRGB(x, y);
            }
        }
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
    
}


