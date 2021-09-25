import java.util.*;
class StackPQ<E> {

    // Stack can implement a priority queue
    // by adding items and assigning keys
    // based on order of insertion.
    // Keys should be a negative counter,
    // to keep them in reverse order added.
    int m;
    PriorityQueue<E> queue = new PriorityQueue<E>();
    /** Start with an empty stack. */
    public StackPQ(){ m = 0; }

    // Stack ADT methods:

    public void push(E v) {
        m++; // start at 1

        pq.add(v);
    }

    public V pop() {
        counter--;
        return removeMin();
    }

}

    public static void main(String[] args){

        StackPriorityQueue q = new StackPriorityQueue();
        q.push(1);
        q.push(2);
        q.push(3);

        System.out.print(q.pop()+" ");
        System.out.print(q.pop()+" ");
        System.out.print(q.pop()+" ");
    }
}