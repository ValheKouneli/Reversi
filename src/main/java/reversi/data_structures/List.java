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
public class List<E> {
    private ListNode<E> first;
    private ListNode<E> last;
    private ListNode<E> iterator;
    private int size;
    
    public List() {
        first = null;
        last = null;
        iterator = null;
        size = 0;
    }
    
    public void add(E point){
        if (isEmpty()) {
            first = new ListNode<>(point);
            last = first;
            iterator = first;
            size++;
        } else {
            last.setNext(new ListNode<>(point));
            last = last.getNext();
            size++;
        }
    }
    
    public int size() {
        return size;
    }
    
    public void iterate() {
        iterator = first;
    }
    
    public E next() {
        if (iterator == null) {
            return null;
        } else {
            E temp = iterator.getPoint();
            iterator = iterator.getNext();
            return temp;
        }
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
}
