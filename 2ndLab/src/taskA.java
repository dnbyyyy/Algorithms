import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskA {
    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader("sort.in");
        Scanner in = new Scanner(reader);
        int n = in.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextInt();
        }
        for (int i = 0; i < n - 1; i++) {
            int minId = i;
            for (int j = i + 1; j < n; j++) {
                if (data[j] < data[minId]) minId = j;
            }
            if (minId != i){
                int tmp = data[i];
                data[i] = data[minId];
                data[minId] = tmp;
            }
        }
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
