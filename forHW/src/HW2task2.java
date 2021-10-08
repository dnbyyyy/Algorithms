public class HW2task2 {

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

    public static void main(String[] args) {
        stableCheckObject[] data = new stableCheckObject[15];
        for (int i = 0; i < 7; i++) {
            data[i] = new stableCheckObject((int) (Math.random() * 5), i);
        }
        System.out.println("Array before sort: ");
        for (int i = 0; i < 7; i++) {
            System.out.printf("data[%d] = %d\n", data[i].index, data[i].value);
        }
        mergeSort(data, 7);
        System.out.println("Array after sort:");
        for (int i = 0; i < 7; i++) {
            System.out.printf("data[%d] = %d\n", data[i].index, data[i].value);
        }
    }
}
