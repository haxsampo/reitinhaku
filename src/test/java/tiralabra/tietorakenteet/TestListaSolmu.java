package tiralabra.tietorakenteet;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestListaSolmu {
    
    @Test
    public void luonti() {
        ListaSolmu ls = new ListaSolmu(1);
        assertEquals(ls.getArvo(), 1);
        
        ListaSolmu lsNeg = new ListaSolmu(-1);
        assertEquals(lsNeg.getArvo(), -1);
        
        ListaSolmu lsNol = new ListaSolmu(0);
        assertEquals(lsNol.getArvo(), 0);
     
    }
    
    @Test
    public void setArvoTest() {
        ListaSolmu ls = new ListaSolmu(1);
        ls.setArvo(10);     
        assertEquals(ls.getArvo(), 10);
        
        ls.setArvo(-3456346);     
        assertEquals(ls.getArvo(), -3456346);
    }
    
    @Test
    public void edeltava() {
        ListaSolmu ls = new ListaSolmu(1);
        ListaSolmu ennen = new ListaSolmu(20);
        
        ls.setEnnen(ennen);
        assertEquals(ls.getEnnen().getArvo(), ennen.getArvo());
        
        ls.setEnnen(null);
        assertEquals(ls.getEnnen(), null);
        
    }
    
    @Test
    public void nimi() {
        ListaSolmu ls = new ListaSolmu(1);
        ListaSolmu jalkeen = new ListaSolmu(2);
        
        ls.setJalkeen(jalkeen);
        assertEquals(ls.getJalkeen().getArvo(), jalkeen.getArvo());
        
        ls.setJalkeen(null);
        assertEquals(ls.getJalkeen(), null);
    }
    

}
