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
    private E[] list;
    
    public List() {
        list = (E[]) new Object[2];
        internalSize = 2;
        size = 0;
    }
    
    public void add(E point){
        if (internalSize == size) {
            internalSize *= 2;
            E[] newList;
            newList = (E[]) new Object[internalSize];
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
        if (i<size) {
            return list[i];
        } else {
            throw new IndexOutOfBoundsException("for index " + i);
        }
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
}
