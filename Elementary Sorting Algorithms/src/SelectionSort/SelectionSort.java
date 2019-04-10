package SelectionSort;

public class SelectionSort {
    /* find index min of smallest remaining entry and swap a[i] and a[min] */

    public static void main(String[] args) {
        int[] a = new int[7777];
        for(int i = 0; i < a.length; i++){
            a[i] = (int) (Math.random()*10000);
        }
        long startTime = System.currentTimeMillis();
        SelectionSort(a);
        for (int i : a)
            System.out.print("\t" + i);
        long endTime = System.currentTimeMillis();
        System.out.println("\nRunning time: " + (startTime - endTime) + "ms");
    }

    public static void SelectionSort(int[] toBeSorted){
        int min = 0;
        for(int j = 0; j < toBeSorted.length; j++) {
            for (int i = j + 1; i < toBeSorted.length; i++) {
                if (toBeSorted[min] > toBeSorted[i]) {
                    min = i;
                }
            }
            int temp = toBeSorted[j];
            toBeSorted[j] = toBeSorted[min];
            toBeSorted[min] = temp;
        }
    }
}
