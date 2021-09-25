/**
 * RingBuffer
 * This class works with queues to enqueue or dequeue elements
 *
 * @author Sawyer Thompson
 * @date 04/18/20
 * CS 108 Section 1
 */
public class RingBuffer<E> {
    private E[] buffer;
    private int size;
    private int front;
    private int rear;
    private int capacity;

    /**
     * Constructor no parameter creates the array at capacity 10.
     **/
    public RingBuffer() {
        capacity = 10;
        buffer = (E[]) new Object[size];
        size = 0;
        front = -1;
        rear = -1;
    }

    /**
     * @param capacity an integer that the array's capacity is set to.
     **/
    public RingBuffer(int capacity) {
        this.capacity = capacity;
        buffer = (E[]) new Object[capacity];
        size = 0;
        front = -1;
        rear = -1;
    }
    /**
     *  Enqueues the integer at the rear of the array.
     **/
    public boolean enQueue(E integer) {
        if (!isFull()) {
            //Ensures rear is in the correct position
            rear = (rear + 1) % capacity;
            buffer[rear] = integer;
            size++;
            System.out.println(buffer[rear]);
            //makes front to 0 if it is the first to enqueue.
            if (front == -1) {
                front = rear;
            }
            return true;
        }
        return false;
    }

    /**
     * Dequeue but doesn't delete the integer at front.
     **/
    public E deQueue() {
        if (!isEmpty()) {
            Object begin = buffer[front];
            //Ensures front is in the correct position
            front = (front + 1) % capacity;
            size--;
            return (E) begin;
        }
        return null;
    }

    //returns the integer of buffer at front
    public Integer peek() {
        if (isEmpty()) {
            return null;
        }
        return buffer[front];
    }

    //tests if array is empty
    public boolean isEmpty() {
        return size == 0;
    }

    //tests if array is full
    public boolean isFull() {
        return size == capacity;
    }

    //returns the integer of buffer at rear
    public Integer last() {
        if (isEmpty()) {
            return null;
        }
        return buffer[rear];
    }

    public Integer[] getArray() {
        return buffer;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getCapacity() {
        return capacity;
    }

    //Ensures that no null objects or wrong elements are being added
    //to the string.
    public String toString() {
        String str = "";
        //adds only the elements from front to rear
        if (rear > front) {
            for (int i = front; i < rear + 1; i++) {
                if (i != rear) {
                    str += (buffer[i] + ", ");
                } else {
                    str += buffer[i];
                }
            }
            //adds elements from front to the length
            //of the array and the from 0 to rear
        } else if (rear < front) {
            for (int i = front; i < capacity; i++) {
                if (rear != 0) {
                    str += (buffer[i] + ", ");
                } else {
                    str += (buffer[i]);
                }
            }
            for (int i = 0; i <= rear; i++) {
                if (i != rear) {
                    str += (buffer[i] + ", ");
                } else {
                    str += buffer[i];
                }
            }
        }
        return str;
    }


}

