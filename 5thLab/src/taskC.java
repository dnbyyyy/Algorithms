import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskC {

    static class BinarySearchTree {

        static class Node {
            int key;
             Node right;
             Node left;
             Node parent;

            public Node(int key) {
                this.key = key;
                left = null;
                right = null;
                parent = null;
            }
        }

        Node root = null;

        void insert(Node x, int key) {
            Node z = new Node(key);

            if (root == null) {
                root = z;
                return;
            }

            while (x != null) {
                if (z.key > x.key) {
                    if (x.right != null) x = x.right;
                    else  {
                        z.parent = x;
                        x.right = z;
                        break;
                    }
                }
                else if (z.key < x.key) {
                    if (x.left != null) x = x.left;
                    else {
                        z.parent = x;
                        x.left = z;
                        break;
                    }
                }
            }
        }

        boolean search(Node x, int k) {
            if (x == null) return false;

            if (x.key == k) return true;

            if (k < x.key) return search(x.left, k);

            else return search(x.right, k);
        }

        Node minimum(Node x) {
            if (x == null) return null;
            if (x.left == null) return x;
            return minimum(x.left);
        }

        Node maximum(Node x) {
            if (x == null) return null;
            if (x.right == null) return x;
            return maximum(x.right);
        }

        Node next(Node root, int key) {

            Node current = root;
            Node successor = null;

            while (current != null) {
                if (current.key > key) {
                    successor = current;
                    current = current.left;
                }
                else current = current.right;
            }
            return successor;
        }

        Node prev(Node root, int key) {

            Node current = root;
            Node successor = null;

            while (current != null) {
                if (current.key < key) {
                    successor = current;
                    current = current.right;
                }
                else  current = current.left;
            }
            return successor;
        }

        Node delete(Node current, int z) {
            if (current == null) return null;

            if (z < current.key) current.left = delete(current.left, z);

            else if (z > current.key) current.right = delete(current.right, z);

            else if (current.left != null && current.right != null) {
                current.key = minimum(current.right).key;
                current.right = delete(current.right, current.key);
            }
            else {
                if (current.left != null) current = current.left;
                else if (current.right != null) current = current.right;
                else current = null;
            }
            return current;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("bstsimple.in"));
        FileWriter out = new FileWriter("bstsimple.out");
        BinarySearchTree tree = new BinarySearchTree();
        while (in.hasNext()) {
            String[] cmd = in.nextLine().split(" ");
            if (cmd[0].equals("insert")) {
                tree.insert(tree.root, Integer.parseInt(cmd[1]));
            }
            if (cmd[0].equals("delete")) {
                if (tree.root != null) tree.delete(tree.root, Integer.parseInt(cmd[1]));
            }
            if (cmd[0].equals("exists")) {
                out.write(Boolean.toString(tree.search(tree.root, Integer.parseInt(cmd[1]))) + '\n');
            }
            if (cmd[0].equals("prev")) {

                String ans = tree.prev(tree.root, Integer.parseInt(cmd[1])) != null ? String.valueOf(tree.prev(tree.root, Integer.parseInt(cmd[1])).key) + '\n' : "none\n";

                out.write(ans);
            }
            if (cmd[0].equals("next")) {
                String ans = tree.next(tree.root, Integer.parseInt(cmd[1])) != null ? String.valueOf(tree.next(tree.root, Integer.parseInt(cmd[1])).key) + '\n' : "none\n";

                out.write(ans);
            }
        }
        in.close();
        out.close();
    }
}
