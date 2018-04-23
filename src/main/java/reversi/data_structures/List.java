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
    private int size;
    private int internalSize;
    private Object[] list;
    
    public List() {
        list = new Object[2];
        internalSize = 2;
        size = 0;
    }
    
    public void add(E point){
        if (internalSize == size) {
            internalSize *= 2;
            Object[] newList;
            newList = new Object[internalSize];
            System.arraycopy(list, 0, newList, 0, size);
            newList[size] = point;
            size++;
            list = newList;
        } else {
            list[size] = point;
            size++;
        }
    }
    
    public int size() {
        return size;
    }

    public E get(int i) {
        if (0<= i && i<size) {
            return (E) list[i];
        } else {
            throw new IndexOutOfBoundsException("for index " + i);
        }
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean contains(E item) {
        boolean contains = false;
        for (int i=0; i<size; i++) {
            if (list[i].equals(item)) {
                return true;
            }
        }
        return contains;
    }

  
    
}
