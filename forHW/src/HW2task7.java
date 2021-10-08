import java.util.Arrays;
import java.util.Scanner;

public class HW2task7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextInt();
        }
        String[] shifts = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder buf = new StringBuilder();
            for (int j = i; j < n; j++) {
                buf.append((char) data[j] + 32).append(" ");
            }
            for (int j = 0; j < i; j++) {
                buf.append((char) data[j] + 32).append(" ");
            }
            shifts[i] = buf.toString().trim();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (shifts[j].compareTo(shifts[j + 1]) > 0) {
                    String tmp = shifts[j];
                    shifts[j] = shifts[j + 1];
                    shifts[j + 1] = tmp;
                }
            }
        }
        String[][] buf = new String[n][n];
        for (int i = 0; i < n; i++) {
            buf[i] = shifts[i].split(" ");
        }
        int[][] answer = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer[i][j] = Integer.parseInt(buf[i][j]) - 32;
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(answer[i]));
        }
    }
}
