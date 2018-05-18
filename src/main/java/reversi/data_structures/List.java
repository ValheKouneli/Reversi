package reversi.data_structures;


/**
 * Represents an array list of objects of the type E
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

    /**
     * @param i index starting from 0
     * @return object in the list in position i
     */
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
    
    /**
     * @param item
     * @return if, in the list, there is an object equal to the given item
     */
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
