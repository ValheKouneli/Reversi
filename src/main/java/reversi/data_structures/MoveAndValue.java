/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.data_structures;

/**
 *
 * @author aepiiroi
 */
public class MoveAndValue {
    
    private Pair move;
    private int value;
    
    public MoveAndValue(Pair move, int value) {
        this.move = move;
        this.value = value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public void setMove(Pair move) {
        this.move = move;
    }
    
    public int getX() {
        return move.getX();
    }
    
    public int getY() {
        return move.getY();
    }
    
    public int getValue() {
        return value;
    }
    
}
