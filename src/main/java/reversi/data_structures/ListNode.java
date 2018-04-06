/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.data_structures;



/**
 *
 * @author Valhe Kouneli
 * @param <E>
 */
public class ListNode<E> {
    
    private final E point;
    private ListNode next;
    
    protected ListNode(E point) {
        this.point = point;
        next = null;
    }
    
    protected E getPoint() {
        return point;
    }
    
    protected ListNode getNext() {
        return next;
    }
    
    protected void setNext(ListNode next) {
        this.next = next;
    }
    
}
