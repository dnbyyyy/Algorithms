import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class taskD {

    static class IndexedValue {

        int value;
        int index;

        public IndexedValue(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public IndexedValue(int value) {
            this.value = value;
        }



    }

    static class Heap {

        IndexedValue[] heap = new IndexedValue[1000000];

        int heapSize = 0;

        void siftUp(int key) {
            while (heap[key].value < heap[(key - 1) / 2].value) {
                IndexedValue tmp = heap[key];
                heap[key] = heap[(key - 1) / 2];
                heap[(key - 1) / 2] = tmp;
                key = (key - 1) / 2;
            }
        }

        void siftDown(int key) {
            while (2 * key + 1 < heapSize) {
                int left = 2 * key + 1;
                int right = 2 * key + 2;
                int j = left;
                if (right < heapSize && heap[right].value < heap[left].value) j = right;
                if (heap[key].value <= heap[j].value) break;
                IndexedValue tmp = heap[key];
                heap[key] = heap[j];
                heap[j] = tmp;
                key = j;
            }
        }

        int extractMin() {
            int min = heap[0].value;
            heap[0] = heap[heapSize - 1];
            heapSize--;
            siftDown(0);
            return min;
        }

        void insert(int value, int cmdNum) {
            heapSize++;
            IndexedValue indexedValue = new IndexedValue(value, cmdNum);
            heap[heapSize - 1] = indexedValue;
            siftUp(heapSize - 1);
        }

        void decreaseKey(int key, int value) {
            heap[key].value = value;
            siftUp(key);
        }
    }

    public static void main(String[] args) throws IOException {

        Heap heap = new Heap();
        Scanner in = new Scanner(new FileReader("priorityqueue.in"));
        FileWriter writer = new FileWriter("priorityqueue.out");
        int cmdNum = -1;
        while (in.hasNext()) {
            String[] cmd = in.nextLine().trim().split(" ");
            cmdNum++;
            if (cmd[0].equals("push")) {
                heap.insert(Integer.parseInt(cmd[1]), cmdNum);
            }
            if (cmd[0].equals("extract-min")) {
                if (heap.heapSize == 0) {
                    writer.write("*\n");
                }
                else writer.write(String.format("%d\n", heap.extractMin()));
            }
            if (cmd[0].equals("decrease-key")) {
                int key = Integer.parseInt(cmd[1]) - 1, value = Integer.parseInt(cmd[2]);
                for (int i = 0; i < heap.heapSize; i++) {
                    if (heap.heap[i].index == key) {
                        heap.decreaseKey(heap.heap[i].index, value);
                        break;
                    }
                }
            }
        }
        in.close();
        writer.close();
    }
}
