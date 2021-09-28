import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskAMergeSortV2 {
    static void merge(int[] data, int left, int mid, int right){
        int result[] = new int[right - left];
        int it1 = 0, it2 = 0;
        while(left + it1 < mid && mid + it2 < right)
            if(data[left + it1] <= data[mid + it2]){
                result[it1 + it2] = data[left + it1];
                it1++;
            }
            else{
                result[it1 + it2] = data[mid + it2];
                it2++;
            }
        while(left + it1 < mid){
            result[it1 + it2] = data[left + it1];
            it1++;
        }
        while(mid + it2 < right){
            result[it1 + it2] = data[mid + it2];
            it2++;
        }
        for(int i = 0; i < it1 + it2; i++)
            data[left + i] = result[i];
    }

    static void mergeSort(int[] data, int n){
        for(int i = 1; i < n; i *= 2)
            for(int j = 0; j < n - i; j += 2 * i)
                merge(data, j, j + i, Math.min(j + 2 * i, n));
    }

    public static void main(String[] args) throws IOException {

    Scanner in = new Scanner(new FileReader("sort.in"));

    int n = in.nextInt();
    int[] data = new int[n];
    for (int i = 0; i < n; i++) {
        data[i] = in.nextInt();
    }

    mergeSort(data, n);

    StringBuilder buf = new StringBuilder();
    for (int i = 0; i < n; i++) {
        if (i != n - 1) buf.append(data[i]).append(" ");
        else buf.append(data[i]);
    }
    String sortedDataStr = buf.toString();
    FileWriter writer = new FileWriter("sort.out");
    writer.write(sortedDataStr);

    in.close();
    writer.close();
    }
}
