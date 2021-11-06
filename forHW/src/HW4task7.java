import java.util.Arrays;

public class HW4task7 {

    static int[] data = {7, 2, 3, 4, 1, 6, 5};

    static void siftDown(int key) {
        while (2 * key + 1 < data.length) {
            System.out.println(Arrays.toString(data));
            int left = 2 * key + 1;
            int right = 2 * key + 2;
            int j = left;
            if (right < data.length && data[right] < data[left]) j = right;
            if (data[key] <= data[j]) break;
            int tmp = data[key];
            data[key] = data[j];
            data[j] = tmp;
            key = j;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            siftDown(i);
        }
    }
}
