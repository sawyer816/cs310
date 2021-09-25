public class CircularLinkedList {
    /*Inner class*/
    static class Node {
        Object data;
        Node next;

        /*Node Constructor*/
        public Node(Object o) {
            data = o;
            next = null;
        }
    }

    /*Global Instance Variables*/
    public static Node head;
    public static Node tail;
    public int size = 0;

    /*Keeps track of the size within the circular linked list*/
    public int size(){
        Node start = head;
        Node end = tail;
        if(start == null){
            return 0;
        }
        while(start!=end){
            size++;
            start=start.next;
        }
        return size;
    }

    /*prints the list*/
    public static void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    /* builds the test data*/
    public void build() {
        CircularLinkedList list = new CircularLinkedList();

        /*combines Strings and integers for testing*/
        push("hi");
        push(1);
        push(2);
        push(2);
        push(2);
        push(2);

        System.out.println("Input Linked list");
        printList(head);
    }

    /*Adds data to the linked list*/
    public static void push(Object nextdata)
    {
        Node newHead = new Node(nextdata);
        // Make next of new Node as head
        newHead.next = head;
        // Move the head to point to new Node
        head = newHead;
    }

    /* Runs program */
    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();
        list.build();
        System.out.println("");
        //print list size
        System.out.println(list.size());
    }
}
