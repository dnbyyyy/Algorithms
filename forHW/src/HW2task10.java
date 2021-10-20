import java.util.Arrays;

public class HW2task10 {

    static int shortestRoute(int[] data) {
        int minId = 0;
        int[] routes = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[j] == 1) routes[i] += Math.abs(j - i);
            }
        }
        for (int i = 0; i < routes.length; i++) {
            if (routes[i] < routes[minId]) minId = i;
        }
        return minId;
    }

    public static void main(String[] args) {
        int[] data = {1, 0, 1, 0, 1};
        System.out.println(shortestRoute(data));
    }
}
