import java.util.Arrays;

public class HW2task4 {

    static int cnt = 0;

    static void quickSort(int[] array, int low, int high) {

        System.out.println(Arrays.toString(array));

        if (array.length == 0) return;

        if (low >= high) return;

        int pivotId = (low + high) / 2;
        //int pivotId = low;
        int pivotValue = array[pivotId];

        int i = low, j = high;

        while (i <= j) {

            while (array[i] < pivotValue) i++;

            while (array[j] > pivotValue) j--;

            if (i <= j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
                j--;
            }

        }

        if (low < j) quickSort(array, low, j);

        if (high > i) quickSort(array, i, high);
    }

    static void aQSort (int[] data) {
        for (int i = 0; i < data.length; i++) {
            int tmp = data[i];
            data[i] = data[i / 2];
            data[i / 2] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        //int[] a = {7, 6, 5, 4, 3, 2, 1};
        aQSort(a);
        System.out.println(Arrays.toString(a));
        quickSort(a, 0, a.length - 1);
    }
}
