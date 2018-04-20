/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.data_structures;

/**
 *
 * @author Valhe Kouneli
 * @param <Class1>
 * @param <Class2>
 */
public class Pair <Class1, Class2> {
    private final Class1 x;
    private final Class2 y;
    
    public Pair(Class1 x, Class2 y) {
        this.x = x;
        this.y = y;
    }
    
    public Class1 getFirst() {
        return x;
    }
    
    public Class2 getSecond() {
        return y;
    }
    
    @Override
    public String toString() {
        return "[" + x.toString() + "," + y.toString() + "]";
    }
    
}
