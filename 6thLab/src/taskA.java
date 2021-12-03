import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class taskA {

    static class Set {

        private static ArrayList<Integer>[] hashTable = new ArrayList[1001];

        private static int hash(int key) {
            return (int) Math.abs(Math.floor(1001 * (key * 0.3 % 1)));
        }

        public Set() {
            Arrays.fill(hashTable, new ArrayList<>());
        }

        boolean exists(int key) {
            int index = hash(key);
            for (int i = 0; i < hashTable[index].size(); i++) {
                if (hashTable[index].get(i) == key) return true;
            }
            return false;
        }

        void insert(int key) {
            if (!exists(key)) hashTable[hash(key)].add(key);
        }

        void remove(int key) {
            if (exists(key)) {
                int deleted = 0;
                int index = hash(key);
                for (int i = 0; i < hashTable[index].size(); i++) {
                    if (hashTable[index].get(i) == key) {
                        deleted = i;
                        break;
                    }
                }
                hashTable[index].remove(deleted);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner reader  = new Scanner(new FileReader("set.in"));
        FileWriter writer = new FileWriter("set.out");

        Set set = new Set();

        while (reader.hasNext()) {
            String cmd = reader.next();
            int key = reader.nextInt();

            switch (cmd) {
                case "insert":
                    set.insert(key);
                    break;
                case "delete":
                    set.remove(key);
                    break;
                case "exists":
                    if (set.exists(key)) writer.write("true\n");
                    else writer.write("false\n");
                    break;
            }
        }
        reader.close();
        writer.close();
    }
}
