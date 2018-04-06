/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.data_structures;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Valhe Kouneli
 */
public class ListTest {
    
    private List<Integer> list;
    
    @Before
    public void setUp() {
        list = new List();
    }
    
    @Test
    public void newListIsEmpty() {
        assertEquals(true, list.isEmpty());
    }

    @Test
    public void addingFirstItemWorks() {
        list.add(2);
        assertEquals(false, list.isEmpty());
        assertEquals((Integer) 2, list.get(0));
    }
    
    
    
}
