import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskD {

    static class IndexedValue {

        int value;
        int index = -1;

        public IndexedValue(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    static int[] indexes = new int[10000000];

    static class Heap {

        int[] heap = new int[1000000];

        int heapSize = 0;

        int siftUp(int key) {
            while (heap[key] < heap[(key - 1) / 2]) {
                int tmp = heap[key];
                heap[key] = heap[(key - 1) / 2];
                heap[(key - 1) / 2] = tmp;
                key = (key - 1) / 2;
            }
            return key;
        }

        void siftDown(int key) {
            while (2 * key + 1 < heapSize) {
                int left = 2 * key + 1;
                int right = 2 * key + 2;
                int j = left;
                if (right < heapSize && heap[right] < heap[left]) j = right;
                if (heap[key] <= heap[j]) break;
                int tmp = heap[key];
                heap[key] = heap[j];
                heap[j] = tmp;
                key = j;
            }
        }

        int extractMin() {
            int min = heap[0];
            heap[0] = heap[heapSize - 1];
            heapSize--;
            siftDown(0);
            return min;
        }

        int insert(int value) {
            heapSize++;
            heap[heapSize - 1] = value;
            return siftUp(heapSize - 1);
        }

        void decreaseKey(int key, int value) {
            heap[key] = value;
            siftUp(key);
        }
    }

    public static void main(String[] args) throws IOException {

        Heap heap = new Heap();
        Scanner in = new Scanner(new FileReader("priorityqueue.in"));
        FileWriter writer = new FileWriter("priorityqueue.out");
        int cmdNum = 0;
        while (in.hasNext()) {
            String[] cmd = in.nextLine().split(" ");
            cmdNum++;
            if (cmd[0].equals("push")) {
                indexes[cmdNum] = heap.insert(Integer.parseInt(cmd[1]));
            }
            if (cmd[0].equals("extract-min")) {
                if (heap.heapSize == 0) {
                    writer.write("*\n");
                }
                else writer.write(String.format("%d\n", heap.extractMin()));
            }
            if (cmd[0].equals("decrease-key")) {
                heap.decreaseKey(indexes[Integer.parseInt(cmd[1])], Integer.parseInt(cmd[2]));
            }
        }
        in.close();
        writer.close();
    }
}
