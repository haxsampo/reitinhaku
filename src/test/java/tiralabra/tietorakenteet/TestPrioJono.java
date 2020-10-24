package tiralabra.tietorakenteet;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestPrioJono {

    public void PrioJonoTest() {
        int kohdex = 20;
        int kohdey = 20;
        PrioJono uusi = new PrioJono(kohdex,kohdey);
        assertEquals(uusi.getPituus(), 0);
        
        PrioSolmu yksi = new PrioSolmu(1,1,kohdex,kohdey);
        PrioSolmu kaksi = new PrioSolmu(2,2,kohdex,kohdey);
        
        uusi.lisaa(yksi);      
        assertEquals(uusi.getPituus(), 1);

        uusi.lisaa(kaksi);
        assertEquals(uusi.getPituus(),2);
        
    }
    
    @Test
    public void etaisyysTest() {
        PrioJono uusi = new PrioJono(10,10);
        assertEquals(uusi.etaisyys(5,5),10);       
        assertEquals(uusi.etaisyys(9,4),7);      
    }
    
    @Test
    public void jarjestysTest() {
        int kohdex = 0;
        int kohdey = 0;
        PrioJono uusi = new PrioJono(kohdex,kohdey);       
        PrioSolmu yksi = new PrioSolmu(1,1,kohdex,kohdey);
        PrioSolmu kaksi = new PrioSolmu(2,2,kohdex,kohdey);
        PrioSolmu kolme = new PrioSolmu(3,3,kohdex,kohdey);
        PrioSolmu nelja = new PrioSolmu(4,4,kohdex,kohdey);
        PrioSolmu viisi = new PrioSolmu(5,5,kohdex,kohdey);
        
        uusi.lisaa(kolme);
        assertTrue(uusi.getEka() == kolme);
        
        uusi.lisaa(kaksi);
        assertEquals(uusi.getEka(), kaksi);
        
        uusi.lisaa(yksi);
        assertEquals(uusi.getEka(),yksi);
        assertEquals(uusi.getVika(),kolme);
        
        uusi.lisaa(viisi);
        assertEquals(uusi.getVika(), viisi);
        
        uusi.lisaa(nelja);
        assertEquals(uusi.getVika().getEnnen(), nelja); 
    }
    
    @Test
    public void poistoJarjestysTest() {
        int kohdex = 0;
        int kohdey = 0;
        PrioJono uusi = new PrioJono(kohdex,kohdey);       
        PrioSolmu yksi = new PrioSolmu(11,11,kohdex,kohdey);
        PrioSolmu kaksi = new PrioSolmu(22,22,kohdex,kohdey);
        PrioSolmu kolme = new PrioSolmu(33,33,kohdex,kohdey);
        PrioSolmu nelja = new PrioSolmu(44,44,kohdex,kohdey);
        PrioSolmu viisi = new PrioSolmu(55,55,kohdex,kohdey);
        
        uusi.lisaa(nelja);
        uusi.lisaa(kaksi);
        uusi.lisaa(kolme);
        assertEquals(uusi.getEka(), kaksi);
        //kaksi - kolme nelja
        
        uusi.ekaPois();
        assertEquals(uusi.getEka(), kolme);
        //kolme - nelja
        
        uusi.lisaa(viisi);
        uusi.ekaPois();
        uusi.ekaPois();
        assertEquals(uusi.getEka(), viisi);
        //viisi
        
        uusi.lisaa(yksi);
        assertEquals(uusi.getEka(), yksi);
        //yksi - viisi       
    }
    
    @Test
    public void tyhjaTest(){
        int kohdex = 0;
        int kohdey = 0;
        PrioJono uusi = new PrioJono(0,0);       
        PrioSolmu yksi = new PrioSolmu(11,11,kohdex,kohdey);
        PrioSolmu kaksi = new PrioSolmu(22,22,kohdex,kohdey);
        
        assertEquals(uusi.getPituus(), 0);
        uusi.ekaPois();
        uusi.ekaPois();
        assertEquals(uusi.getPituus(), 0);
        
        uusi.lisaa(yksi);
        assertEquals(uusi.getPituus(),1);
        
        uusi.lisaa(kaksi);
        uusi.ekaPois();
        uusi.ekaPois();
        assertEquals(uusi.ekaPois(), null);
        assertEquals(uusi.getEka(), null);
        assertEquals(uusi.getVika(), null);       
    }
    
    
}
