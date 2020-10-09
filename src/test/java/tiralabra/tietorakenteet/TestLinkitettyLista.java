package tiralabra.tietorakenteet;

import tiralabra.tietorakenteet.ListaSolmu;
import tiralabra.tietorakenteet.LinkitettyLista;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLinkitettyLista{
    
    
    @Test
    public void luonti() {
        LinkitettyLista l = new LinkitettyLista();
        assertEquals(l.getPituus(),0);
      
    }
    
    @Test
    public void pituus() {
        LinkitettyLista ll = new LinkitettyLista();
        assertEquals(ll.getPituus(),0);
        
        ListaSolmu ls = new ListaSolmu(3);
        ll.lisaa(ls);
        assertEquals(ll.getPituus(),1);
        
        ListaSolmu ls1 = new ListaSolmu(1);
        ll.lisaa(ls1);
        assertEquals(ll.getPituus(),2);
        
        ListaSolmu ls2 = new ListaSolmu(20);
        ll.lisaa(ls2);
        assertEquals(ll.getPituus(),3);
        
        ll.ekaPois();
        assertEquals(ll.getPituus(),2);
        
        ll.getEka();
        assertEquals(ll.getPituus(),2);
        
        ll.ekaPois();
        assertEquals(ll.getPituus(),1);
        
        ll.ekaPois();
        assertEquals(ll.getPituus(),0);
        
        ll.ekaPois();
        assertEquals(ll.getPituus(),0);
        
        ll.lisaa(ls);
        assertEquals(ll.getPituus(),1);

    }
    
    @Test
    public void testOnkoTyhja() {
        LinkitettyLista l = new LinkitettyLista();
        ListaSolmu ls = new ListaSolmu(3);
        
        assertTrue(l.onTyhja());
        l.lisaa(ls);
        assertFalse(l.onTyhja());      
    }
    
    @Test
    public void testLinkit() {
        LinkitettyLista l = new LinkitettyLista();
        ListaSolmu ls = new ListaSolmu(3);
        ListaSolmu ab = new ListaSolmu(6);
        ListaSolmu cd = new ListaSolmu(9);
        ListaSolmu ef = new ListaSolmu(12);
        
        l.lisaa(ls);
        l.lisaa(ab);
        //listalla ls <-> ab
        
        l.ekaPois();
        assertEquals(ab.getArvo(),l.getEka().getArvo());
        //listalla ab
        
        l.lisaa(cd);
        l.lisaa(ef);
        l.lisaa(ls);
        //listalla ab - cd - ef - ls
        
        assertEquals(l.getEka().getArvo(),ab.getArvo());
        assertEquals(l.getEka().getJalkeen().getArvo(),cd.getArvo());
        assertEquals(l.getEka().getJalkeen().getJalkeen().getArvo(),ef.getArvo());
        assertEquals(l.getEka().getJalkeen().getJalkeen().getJalkeen().getArvo(),ls.getArvo());
 
    }
}
