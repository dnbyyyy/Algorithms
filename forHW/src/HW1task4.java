import java.util.ArrayList;

public class HW1task4 {

    static boolean hasSame(int[] a, int[] b) {
        int l = 0, r = 0;
        while (l < a.length) {
            if (a[l] == b[r]) return true;
            else if (a[l] < b[r]) l++;
            else r++;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {1, 1, 2, 3, 4, 4, 5, 5, 5, 6};
        int[] b = {0, 5, 8, 9};
        System.out.println(hasSame(a, b));
    }
}