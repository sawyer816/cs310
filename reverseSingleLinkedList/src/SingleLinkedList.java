class SinglyLinkedList<E>{
    /*Inner class*/
    static class Node {
        Object data;
        Node next;

        /*Node Constructor*/
        public Node (Object o) {
            data = o;
            next = null;
        }
    }

    public static Node head = null;
    public static Node tail = null;
    /* Method that reverse the linked list */
    Node reverse(Node node)
    {
        Node prev = null;
        Node curr = node;
        Node next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        node = prev;
        return node;
    }

    /* prints content of double linked list */
    public void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

/*driver method. Tests the reverse method*/
    public void runReverse() {
        SinglyLinkedList<E> list = new SinglyLinkedList<>();

/*combines Strings and integers for testing*/
       push("hi");
       push(1);
       push(2);

        System.out.println("Input Linked list");
        printList(head);
        head = list.reverse(head);
        System.out.println("");
        System.out.println("");
        System.out.println("Reversed linked list ");
        printList(head);
    }

    /*Adds data to the linked list*/
    public void push(Object nextdata)
    {
        Node newHead = new Node(nextdata);

        // Make next of new Node as head
        newHead.next = head;

        // Move the head to point to new Node
        head = newHead;
    }

    /* Runs program */
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.runReverse();
    }
}
