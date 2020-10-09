package tiralabra.tietorakenteet;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestPrioSolmu {
    
    @Test
    public void PrioSolmuTest() {
        PrioSolmu uusi = new PrioSolmu(5,5,6,5);
        assertEquals(uusi.getEtaisyys() ,1);
        
        uusi.setEtaisyys(3);
        assertTrue(uusi.getEtaisyys() == 3);
    }
    
    @Test
    public void EnnenJalkeenTest() {
        int kohdex = 5;
        int kohdey = 5;
        PrioSolmu yksi = new PrioSolmu(1,1,kohdex,kohdey);
        PrioSolmu kaksi = new PrioSolmu(2,2,kohdex,kohdey);
        PrioSolmu kolme = new PrioSolmu(3,3,kohdex,kohdey);
        
        kaksi.setEnnen(yksi);
        yksi.setJalkeen(kaksi);
        
        kolme.setEnnen(kaksi);
        kaksi.setJalkeen(kolme);
        
        assertTrue(yksi.getEnnen() == null);
        assertEquals(yksi.getJalkeen(), kaksi);
        
        assertEquals(kaksi.getEnnen(), yksi);
        assertEquals(kaksi.getJalkeen(), kolme);
        
        assertEquals(kolme.getEnnen(), kaksi);
        assertTrue(kolme.getJalkeen()==null);                      
    }
}
