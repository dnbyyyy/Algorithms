import java.awt.*;

public class taskABasedOnList {

    static class Node {

        private Node prev;              // reference to previous node
        private Node next;              // reference to next node

        private int value;              // stored value

        public Node(Node prev, Node next, int value) {              // node constructor
            this.prev = prev;
            this.next = next;
            this.value = value;
        }

        public Node getPrev() {
            return prev;
        }

        public Node getNext() {
            return next;
        }

        public int getValue() {
            return value;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    static class List {

        Node[] list;                // list itself

        int capacity;               // current list capacity
        int size;               // current list size
        int tail;               // last element pointer

        public List() {             // list constructor
            capacity = 0;
            size = 0;
            list = new Node[capacity];
        }

        void reallocate() {
            capacity *= 2 + 1;
            Node[] buf = list;
            list = new Node[capacity];
            System.arraycopy(buf, 0, list, 0, buf.length);

        }


        void add(int index, int value) {

            if (capacity == 0) reallocate();

            //list[index] = new Node();
        }
    }

}
