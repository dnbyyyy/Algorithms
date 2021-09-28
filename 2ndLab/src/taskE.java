import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class taskE {
    static int orderStats(int[] array, int k) {
        int left = 0, right = array.length;
        while (true) {
            int mid = partition(array, left, right - 1);

            if (mid == k) {
                return array[mid];
            }
            else if (k < mid) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
    }

    static int partition(int[] data, int left, int right) {
        int pivot = data[(left + right) / 2];
        int i = left;
        int j = right;
        while (i <= j) {
            while (data[i] < pivot) {
                i++;
            }
            while (data[j] > pivot) {
                j--;
            }
            if (i >= j) {
                break;
            }
            int tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
        }
        return j;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("kth.in"));
        int n = in.nextInt(), k = in.nextInt();
        int a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
        int[] data = new int[n];
        data[0] = in.nextInt();
        if (n != 1) data[1] = in.nextInt();
        for (int i = 2; i < n; i++) {
            data[i] = a * data[i - 2] + b * data[i - 1] + c;
        }
        FileWriter writer = new FileWriter("kth.out");
        writer.write(String.valueOf(orderStats(data, k - 1)));
        writer.close();
        in.close();
    }
}
