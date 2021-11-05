public class HW3task5 {
    private static class List {

        private static class Node {

            Node prev;
            Node next;

            char value;

            public Node(Node prev, char value, Node next) {
                this.prev = prev;
                this.next = next;
                this.value = value;
            }
        }

        int size = 0;

        Node first;
        Node last;

        Node node(int index) {
            Node buf = first;
            for (int i = 0; i < index; i++) {
                buf = buf.next;
            }
            return buf;
        }

        void add(char value) {

            if (size == 0) {
                Node buf = new Node(null, value, null);
                first = buf;
                last = buf;
            }
            else {
                Node buf = new Node(last, value, null);
                last.next = buf;
                last = buf;
            }

            size++;
        }

        void add(int index, char value) {
            if (index > size) {
                System.err.print("IndexOutOfBoundsException");
                return;
            }
            if (index == size) add(value);
            if (index < size) {
                Node withNeededIndex = node(index);
                Node buf = new Node(withNeededIndex.prev, value, withNeededIndex);
                buf.prev.next = buf;
                withNeededIndex.prev = buf;
            }
            size++;
        }

        char remove(int index) {
            if (size < 1 || index > size - 1){
                System.err.print("IndexOutOfBoundsException");
                return ' ';
            }
            if (index == 0) {
                char deletedValue = first.value;
                first = first.next;
                size--;
                return deletedValue;
            }
            if (index == size - 1) {
                char deletedValue = last.value;
                last = last.prev;
                last.next = null;
                size--;
                return deletedValue;
            }
            else {
                Node deleted = node(index);
                deleted.prev.next = deleted.next;
                deleted.next.prev = deleted.prev;
                size--;
                return deleted.value;
            }
        }
    }

    static class Stack{
        List stack = new List();
        int size = stack.size;

        void push(char value){
            stack.add(value);
        }

        char pop(){
            return stack.remove(stack.size - 1);
        }

    }

    static class Queue {
        List queue = new List();
        int size = queue.size;

        void push(char value){
            queue.add(value);
        }

        char pop() {
            return queue.remove(0);
        }

    }

    String infixToPostfix(String infix) {
        Stack stack = new Stack();
        Queue queue = new Queue();
        for (int i = 0; i < infix.length(); i++) {
            if (Character.isDigit(infix.charAt(i))) queue.push(infix.charAt(i));
            if (infix.charAt(i) == '+' || infix.charAt(i) == '-' || infix.charAt(i) == '*' || infix.charAt(i) == '/') {
                if (stack.size == 0 || stack.stack.node(stack.size - 1).value == '(') {
                    stack.push(infix.charAt(i));
                }
                if ((infix.charAt(i) == '*' || infix.charAt(i) == '/') && (stack.stack.node(stack.size - 1).value != '*'
                        || stack.stack.node(stack.size - 1).value != '/')) {
                    stack.push(infix.charAt(i));
                }
                if (stack.stack.node(stack.size - 1).value == '*' || stack.stack.node(stack.size - 1).value == '/') {

                }
            }
        }
        return "";
    }
}
