import java.util.Arrays;

public class HW2task6 {

    static class stableCheckObject {
        int value;
        int index;

        public stableCheckObject(int value, int index) {
            this.value = value;
            this.index = index;
        }

    }

    static void merge(stableCheckObject[] data, int left, int mid, int right){
        stableCheckObject[] result = new stableCheckObject[right - left];
        int it1 = 0, it2 = 0;
        while(left + it1 < mid && mid + it2 < right)
            if(data[left + it1].value <= data[mid + it2].value){
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

    static void mergeSort(stableCheckObject[] data, int n){
        for(int i = 1; i < n; i *= 2)
            for(int j = 0; j < n - i; j += 2 * i)
                merge(data, j, j + i, Math.min(j + 2 * i, n));
    }

    static int[] lessElementsCounter(stableCheckObject[] data) {
        mergeSort(data, data.length);
        int[] result = new int[data.length];
        int cnt = 0;
        for (int i = 1; i < data.length; i++) {
            result[data[i - 1].index] = cnt;
            if (data[i].value != data[i - 1].value) {
                cnt = i;
            }
        }
        if (data[data.length - 1].value == data[data.length - 2].value) {
            result[data[data.length - 1].index] = result[data[data.length - 2].index];
        }
        else result[data[data.length - 1].index] = cnt;
        return result;
    }

    public static void main(String[] args) {
        stableCheckObject[] data = new stableCheckObject[5];
        for (int i = 0; i < 5; i++) {
            data[i] = new stableCheckObject((int) (Math.random() * 10 + 1), i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.print(data[i].value + " ");
        }
        System.out.println();
        System.out.println(Arrays.toString(lessElementsCounter(data)));
    }
}