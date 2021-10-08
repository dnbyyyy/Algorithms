import java.util.Arrays;

public class HW2task3 {

    static void merge(int[] data, int l, int mid, int r) {
        int it1 = 0, it2 = 0;
        int[] result = new int[r - l];

        while (l + it1 < mid && mid + it2 < r) {
            if (data[l + it1] < data[mid + it2]) {
                result[it1 + it2] = data[l + it1];
                it1++;
            }
            else {
                result[it1 + it2] = data[mid + it2];
                it2++;
            }
        }

        while (l + it1 < mid) {
            result[it1 + it2] = data[l + it1];
            it1++;
        }

        while (mid + it2 < r) {
            result[it1 + it2] = data[mid + it2];
            it2++;
        }

        for (int i = 0; i < it1 + it2; i++) {
            data[l + i] = result[i];
        }
    }

    static void mergeSort(int[] data, int n) {
        for (int i = 1; i < n; i *= 2) {
            for (int j = 0; j < n - i; j += 2 * i) {
                merge(data, j, j + i, Math.min(j + 2 * i, n));
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {5, 4, 3, 2, 1, 5, 3, 2, 1, 7};
        mergeSort(a, a.length);
        System.out.println(Arrays.toString(a));
    }
}
