public class HW2task9 {

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

    static void mergeSort(int[] data, int left, int right){
        if (left + 1 >= right) return;
        int mid = (left + right) / 2;
        mergeSort(data, left, mid);
        mergeSort(data, mid, right);
        merge(data, left, mid, right);
    }

    static long shuffleCounter(int[] a) {
        long result = 1;
        mergeSort(a, 0, a.length);
        long factorial = 1, cnt = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] == a[i - 1]) {
                cnt++;
            }
            else {
                for (int j = 1; j <= cnt; j++) {
                    factorial *= j;
                }
                result *= factorial;
                factorial = 1;
                cnt = 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 1, 3, 4, 5};
        System.out.println(shuffleCounter(a));
    }
}
