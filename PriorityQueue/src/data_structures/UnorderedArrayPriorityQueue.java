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

public class UnorderedArrayPriorityQueue <E extends Comparable <E>> implements PriorityQueue <E> {

    //Instance Variables
    private E[] unorderedArray;
    private int size;
    private int capacity;

    //Constructor with no parameter
    public UnorderedArrayPriorityQueue(){
        capacity = DEFAULT_MAX_CAPACITY;
        unorderedArray = (E[]) new Comparable[capacity];
        size = 0;
    }

    //Constructor with parameter
    public  UnorderedArrayPriorityQueue(int capacity){
        unorderedArray = (E[]) new Comparable[capacity];
        size = 0;
        this.capacity=capacity;
    }

    //inserts object into array
    @Override
    public boolean insert(E object) {
        if(!isFull()){
            unorderedArray[size] = object;
            size+=1;
            return true;
        }
        return false;
    }

    //removes highest priority object and returns it.
    @Override
    public E remove() {
        if(!isEmpty()) {
            E remove = unorderedArray[minPriorityIndex()];
            int min = minPriorityIndex();
            for(int i = min; i < size - 1; i++) {
                unorderedArray[i] = unorderedArray[i+1];
            }
            size--;
            return remove;
        }
        throw new NoSuchElementException();
    }

    //returns the index of the priority object
    private int minPriorityIndex(){
        int min = 0;
        for (int i = 1; i < size; i++) {
            if(unorderedArray[i].compareTo(unorderedArray[min]) < 0 ){
                min = i;
            }
        }
        return min;
    }

    //deletes object in the queue.
    @Override
    public boolean delete(E obj) {
        boolean deleted = false;
        for (int i = 0; i < size; i++) {
            //checks if the object is in the queue
            if (unorderedArray[i].compareTo(obj) == 0) {
                //moves the queue up
                for (int j = i--; j < size - 1; j++) {
                    unorderedArray[j] = unorderedArray[j + 1];
                }
                size--;
                deleted = true;
            }
        }
        return deleted;
    }

    //returns object of highest priority.
    @Override
    public E peek() {
        if(!isEmpty()) {
            return unorderedArray[minPriorityIndex()];
        }
        return null;
    }

    //checks if the object is in the queue.
    @Override
    public boolean contains(E obj) {
        for (int i = 0; i < size; i++) {
            if(unorderedArray[i].compareTo(obj) == 0){
                return true;
            }
        }
        return false;
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
        return size==capacity;
    }

    //Creates Iterator class
    private class myIterator implements Iterator<E>{
        int index;
        public myIterator(){
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
            return unorderedArray[index++];
        }
    }

    @Override
    public Iterator iterator() {
        return new myIterator();
    }
}
