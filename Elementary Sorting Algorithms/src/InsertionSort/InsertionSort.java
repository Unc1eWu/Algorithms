package InsertionSort;

public class InsertionSort {
    public static void main(String[] args) {
        int[] a = new int[7777];
        for(int i = 0; i < a.length; i++){
            a[i] = (int) (Math.random()*10000);
        }
        long startTime = System.currentTimeMillis();
        InsertionSort(a);
        for (int i : a)
            System.out.print("\t" + i);
        long endTime = System.currentTimeMillis();
        System.out.println( "\nRunning time: " + (startTime - endTime) + "ms");
    }

    public static void InsertionSort(int[] toBeSorted) {
        int temp;
        for (int i = 0; i < toBeSorted.length; i++){
            for(int j = i; j > 0; j--){
                if (toBeSorted[j] < toBeSorted[j - 1]){
                    temp = toBeSorted[j - 1];
                    toBeSorted[j - 1] = toBeSorted[j];
                    toBeSorted[j] = temp;
                }
            }
        }
    }
}
