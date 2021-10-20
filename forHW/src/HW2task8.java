public class HW2task8 {

    static class ComparisonStructure {

        int[] data;
        int valuesRange;
        static int[] valuesCounter;

        public ComparisonStructure(int[] data, int valuesRange) {
            this.data = data;
            this.valuesRange = valuesRange;
            valuesCounter = new int[this.valuesRange];
            for (int datum : data) {
                valuesCounter[datum - 1]++;
            }
        }

        int howManyBetween(int a, int b) {
            int cnt = 0;
            for (int i = a - 1; i < b; i++) {
                cnt +=  valuesCounter[i];
            }
            return cnt;
        }
    }

    public static void main(String[] args) {
        int[] data = {2, 4, 2, 1, 5, 6, 3, 2, 7, 8, 9, 10};
        ComparisonStructure structure = new ComparisonStructure(data, 10);
        System.out.println(structure.howManyBetween(1, 10));
    }

}
