/**
 * Program 2 - Priority Queue
 * This program implements the PriorityQueue interface using an unsorted array,
 * sorted array, and binary heap.
 * @author Sawyer Thompson
 * @date 03/22/21
 * @account cssc3259@edoras.sdsu.edu
 * @accountNumber 3259
 * CS 310 Section 2
 */
package data_structures;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedArrayPriorityQueue <E extends Comparable <E>> implements PriorityQueue <E> {

    //Instance Variables
    private E[] orderedArray;
    private int size;
    private int capacity;

    //Constructor without parameter
    public OrderedArrayPriorityQueue(){
        capacity = DEFAULT_MAX_CAPACITY;
        orderedArray = (E[]) new Comparable[capacity];
        size = 0;
    }

    //Constructor with parameter
    public  OrderedArrayPriorityQueue(int capacity){
        orderedArray = (E[]) new Comparable[capacity];
        size = 0;
        this.capacity=capacity;
    }

    //Inserts object into the queue
    @Override
    public boolean insert(E object) {
        if (!isFull()) {
            //finds where to insert the object
            int index = findInsertionPoint(object, 0, size - 1);
            //moves the queue back behind insertion point
            if (size - index >= 0) {
                System.arraycopy(orderedArray, index, orderedArray,
                        index + 1, size - index);
            }
            orderedArray[index] = object;
            size++;
            return true;
        }
        return false;
    }

    //finds insertion point recursively using binary search
    private int findInsertionPoint(E obj, int low, int hi) {
        //returns the lowest index if the lowest index is greater than the highest index
        if (low > hi) {
            return low;
        }
        int mid = (low + hi) / 2;
        //searches through left half
        if (obj.compareTo(orderedArray[mid]) <= 0) {
            return findInsertionPoint(obj, low, mid - 1);
        }
        //searches through right half
        return findInsertionPoint(obj, mid+1, hi);
    }

    //removes the highest priority object
    @Override
    public E remove() {
        if (!isEmpty()) {
            E removed = orderedArray[0];
            //shifts queue down one as the priority is at the start of the queue.
            if (size - 1 >= 0){
                System.arraycopy(orderedArray, 1, orderedArray, 0, size - 1);
            }
            size --;
            return removed;
        }
        throw new NoSuchElementException();
    }

    //deletes specified object in queue
    @Override
    public boolean delete(E obj) {
        int index = 0;
        if(!contains(obj)){
            return false;
        }
        //binary searches through all objects and deletes them while they are still contained in the queue
        while(true) {
            index = binarySearch(obj, 0, size - 1);
            if(index == -1) {
                break;
            }
            size--;
            //shifts queue up one to keep the priority queue.
            for (int j = index; j < size - 1; j++) {
                orderedArray[j] = orderedArray[j + 1];
            }
        }
        return true;
    }

    //returns the priority object
    @Override
    public E peek() {
        if (!isEmpty()) {
            return orderedArray[0];
        }
       return null;
    }

    //checks to see if object is in queue
    @Override
    public boolean contains(E obj) {
        return binarySearch(obj, 0, size-1) != -1;
    }

    //binary searches for an object and returns its index.
    private int binarySearch(E obj, int low, int hi) {
        //returns -1 if it was not found
        if (low > hi) {
            return -1;
        }
        int mid = (low + hi ) / 2;
        // If the element is present at the middle itself
        if (orderedArray[mid].compareTo(obj) == 0) {
            return mid;
        }

        //searches through left side of queue
        if (orderedArray[mid].compareTo(obj) < 0) {
            return binarySearch(obj, mid + 1, hi);
        }
        //searches through right side of queue
        return binarySearch(obj, low, mid - 1);
    }


        @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    //Creates Iterator class
    private class myIterator implements Iterator<E>{

        int index;

        private myIterator(){
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index<size;
        }

        @Override
        public E next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            return orderedArray[index++];
        }
    }

    @Override
    public Iterator iterator() {
        return new myIterator();
    }
}
