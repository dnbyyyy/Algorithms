import java.util.Arrays;

public class BubbleSortTest {

    public static void main(String[] args) {
        int[] data = {4, 3, 6, 7, 2, 0, 1, 5, 9, 8};
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    data[j + 1] += data[j];
                    data[j] = data[j + 1] - data[j];
                    data[j + 1] -= data[j];
                }
            }
        }
        System.out.println(Arrays.toString(data));
    }
}
