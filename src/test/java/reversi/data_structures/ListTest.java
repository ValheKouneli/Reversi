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
    
    @Test
    public void addingManyItemsWorks() {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals((Integer) 0, list.get(0));
        assertEquals((Integer) 1, list.get(1));
        assertEquals((Integer) 2, list.get(2));
        assertEquals((Integer) 3, list.get(3));
        assertEquals(4, list.size());
    }
    
    @Test
    public void containsGivesFalseForEmptryList() {
        assertEquals(false, list.contains(5));
    }
    
    @Test
    public void containsGivesTrueForListThatContainsOnlyTheSameElement() {
        list.add(5);
        assertEquals(true, list.contains(5));
    }
    
    @Test
    public void containsGivesTrueForListWithSeveralElementsAndTheAskedElement() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertEquals(true, list.contains(3));
    }
    
    @Test
    public void containsGivesFalseForListWithSeveralElementsButNotTheAskedElement() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertEquals(false, list.contains(6));
    }
    
    
    
}
