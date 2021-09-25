public class DoublyLinkedLists {
    /*Inner class*/
    static class Node {
        Object data;
        Node next;
        Node prev;

        public void setPrev(Node p) { prev = p; }
        public void setNext(Node n) { next = n; }

        /*Node Constructor*/
        public Node(Object o) {
            data = o;
            next = null;
            prev = null;
        }
        private Node header; // header sentinel
        private Node trailer;

        public void addFirst(Object e) {
            addBetween(e, header, header.getNext()); // place just after the header
        }

        private Node getNext() {
            return next;
        }

        public Node(Object e, Node predecessor, Node successor) {
            data = e;
            next = successor;
            prev = predecessor;
        }

        private void addBetween(Object e, Node predecessor, Node successor) {
            // create and link a new node
            Node newest = new Node(e, predecessor, successor);
            predecessor.setNext(newest);
            successor.setPrev(newest);
        }

        public Node() {
            next = null;
            prev = null;
        }
    }

    public Node merge(Node head1, Node head2){
        Node newNode = new Node();
        while(head1.next!=null){
            newNode.addFirst(head1.next);
        }
        while(head2.next!=null){
            newNode.addFirst(head2.next);
        }
        return newNode;
    }

    /*prints the list*/
    public static void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    /*Adds data to the linked list*/
    public static void push(Object nextdata, Node oldHead)
    {
        Node newHead = new Node(nextdata);
        // Make next of new Node as head
        newHead.next = oldHead;
    }

}
