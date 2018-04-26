package reversi.data_structures;

/**
 *
 * @author Valhe Kouneli
 */
public class IntPair {
    private final int x;
    private final int y;
    
    public IntPair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
    
    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o==this) {
            return true;
        }
        
        if (!(o instanceof IntPair)) {
            return false;
        }

        return (((IntPair) o).getX() == x && ((IntPair) o).getY() == y);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.x;
        hash = 29 * hash + this.y;
        return hash;
    }
    
}
