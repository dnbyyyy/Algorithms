public class HW3task1 {

    static int maxElementsCnt(int[] data, int x) {
        int cnt = 0, sum = 0;
        for (int datum : data) {
            if (sum + datum > x) break;
            sum += datum;
            cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5};
        System.out.println(maxElementsCnt(data, 6));
    }
}
