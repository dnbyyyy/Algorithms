import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class taskC {
    static long merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, count = 0;
        while (i < left.length || j < right.length) {
            if (i == left.length) {
                arr[i+j] = right[j];
                j++;
            } else if (j == right.length) {
                arr[i+j] = left[i];
                i++;
            } else if (left[i] <= right[j]) {
                arr[i+j] = left[i];
                i++;
            } else {
                arr[i+j] = right[j];
                count += left.length-i;
                j++;
            }
        }
        return count;
    }

    static long invCount(int[] arr) {
        if (arr.length < 2)
            return 0;

        int m = (arr.length + 1) / 2;
        int[] left = Arrays.copyOfRange(arr, 0, m);
        int[] right = Arrays.copyOfRange(arr, m, arr.length);

        return invCount(left) + invCount(right) + merge(arr, left, right);
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("inversions.in"));
        int n = in.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextInt();
        }
        FileWriter writer = new FileWriter("inversions.out");
        writer.write(String.valueOf(invCount(data)));
        in.close();
        writer.close();
    }
}