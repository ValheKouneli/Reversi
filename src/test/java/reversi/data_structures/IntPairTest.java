
package reversi.data_structures;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Valhe Kouneli
 */
public class IntPairTest {
    
    
    @Test
    public void constructorWorks() {
        IntPair pair = new IntPair(5,4);
        assertEquals(5, pair.getX());
        assertEquals(4, pair.getY());
    }
    
    @Test
    public void equalsGivesTrueForReferencesToTheSamePair() {
        IntPair pair = new IntPair(4,5);
        IntPair reference = pair;
        assertEquals(true, pair.equals(reference));
    }
    
    @Test
    public void equalsGivesTrueForPairWithTheSameValues() {
        IntPair pair = new IntPair(4,5);
        IntPair differentPair = new IntPair(4,5);
        assertEquals(true, pair.equals(differentPair));
    }
    
    @Test
    public void equalsGivesFalseForPairWithDifferentValues() {
        IntPair pair = new IntPair(4,5);
        IntPair anotherPair = new IntPair(5,4);
        assertEquals(false, pair.equals(anotherPair));
    }
    
    @Test
    public void equalsGivesFalseForAnotherElement() {
        class IntPair2 {
            private final int x;
            private final int y;
            public IntPair2(int x, int y) {
                this.x = x;
                this.y = y;
            }
            
            public int getX() {
                return x;
            }
            
            public int getY() {
                return y;
            }
        }
        
        IntPair2 weirdoPair = new IntPair2(1,0);
        IntPair normalPair = new IntPair(1,0);
        assertEquals(false, normalPair.equals((Object) weirdoPair));
    }
    
    
}
