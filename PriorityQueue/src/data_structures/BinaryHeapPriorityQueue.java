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

public class BinaryHeapPriorityQueue <E extends Comparable <E>> implements PriorityQueue <E> {

    //Instance variables
    private E[] binaryHeap;
    private int size;
    private int capacity;

    //Constructor without parameters
    public BinaryHeapPriorityQueue(){
        size = 0;
        capacity = DEFAULT_MAX_CAPACITY;
        binaryHeap = (E[]) new Comparable[capacity];
    }

    //Constructor with parameters
    public  BinaryHeapPriorityQueue(int capacity){
        size = 0;
        this.capacity=capacity;
        binaryHeap = (E[]) new Comparable[capacity];
    }


    @Override
    // insert the item into the queue at the right position
    public boolean insert(E object) {
        //checks if full
        if (isFull()) {
            return false;
        }
        //insert the obj at the last position of the array and increase the size
        binaryHeap[size] = object;
        size = size + 1;
        int curIndex = size - 1;
        //puts obj into the correct position.
        while (curIndex != 0 && binaryHeap[parent(curIndex)].compareTo(binaryHeap[curIndex]) > 0) {
            swap(curIndex, parent(curIndex));
            curIndex = parent(curIndex);
        }
        return true;
    }

    //return the index of the parent
    private static int parent(int index) {
        return (index - 1) / 2;
    }

    // return the index of the left child
    private static int leftChild(int index) {
        return 2 * index + 1;
    }

    // return the index of the right child
    private static int rightChild(int index) {
        return 2 * index + 2;
    }

    //moves the obj into the minHeap in the right position.
    private void minHeap(int small){
        // find left child
        int left = leftChild(small);
        // find right child
        int right = rightChild(small);
        // find the smallest among the 3 objects
        int smallest = small;
        // check if the left node is smaller than the current object
        if (left < size && binaryHeap[left].compareTo(binaryHeap[smallest]) < 0) {
            smallest = left;
        }
        // check if the right node is smaller than the current object and left object
        if (right < size && binaryHeap[right].compareTo(binaryHeap[smallest]) < 0) {
            smallest = right;
        }
        // swap the smallest node with the current object and repeat
        // this process until the current node is smaller than the right and the left object
        if (smallest != small) {
            swap(small, smallest);
            minHeap(smallest);
        }
    }

    //swaps two objects in the queue
    private void swap(int x, int y) {
        E temp = binaryHeap[x];
        binaryHeap[x] = binaryHeap[y];
        binaryHeap[y] = temp;
    }

    //removes the object with the highest priority
    @Override
    public E remove() {
        E minItem = binaryHeap[0];
        // swap the priority object at index 1
        // with the last item and reduce the size
        swap(0, size-1);
        size = size - 1;
        //Prioritize the queue
        minHeap(0);
        return minItem;
    }

    //Deletes the specified object from the queue
    @Override
    public boolean delete(E obj) {
        if(isEmpty() || index(obj) == -1) {
            return false;
        }
        // removes all instances of the object
        while(index(obj) != -1){
            int temp = index(obj);
            //moves the
            swap(index(obj), size - 1);
            size--;
            //rePrioritizes the heap
            minHeap(temp);
        }
        return true;
    }

    //returns the priority object
    @Override
    public E peek() {
        if(!isEmpty()) {
            return binaryHeap[0];
        }
        return null;
    }

    //checks if the object is in the queue
    @Override
    public boolean contains(E obj) {
        for(int i = 0; i < size; i++) {
            if(binaryHeap[i].compareTo( obj) == 0) {
                 return true;
            }
        }
        return false;
    }

    //returns the index of where the object is in the queue
    private int index(E obj) {
        for (int i = 0; i < size; i++) {
            if(binaryHeap[i] == obj) {
                return i;
            }
        }
        //returns -1 if not in the queue
        return -1;
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
            return binaryHeap[index++];
        }
    }

    @Override
    public Iterator iterator() {
        return new myIterator();
    }
}
