import java.awt.*;

/**
   This class is used to sort a List using a merge sort utilizing multiple threads.
   @author Vangelis Metis and Zachary Vasey
*/

public class MergeSort{
      
      private static int numberOfThreads;
      /**
         This method sets the amount of Threads used to the amount of CPU cores in your computer
      */
      public static void setNumberOfThreads(){
           numberOfThreads = Runtime.getRuntime().availableProcessors();
      }
      /**
         This method sets the amount of Threads used in this sorting algorithm
      */
      public static void setNumberOfThreads(int i){
           numberOfThreads = i;
      }
      
      /**
         Starts the sorting algorithm
      */
      public static void sort(Graphics g, Bar list[]) {
        parallelMergeSort(g, list, 0, list.length - 1);
        //mergeSort(g, list, 0, list.length - 1);
      }
      
      private static void parallelMergeSort(Graphics g, Bar a[], int l, int r){
        //Thread.currentThread().yield();
        if(r > l){
           if(numberOfThreads <= 1){
               mergeSort(g, a, l, r);
           }
           else{
              int m = (l + r) / 2;
              Runnable first = new Runnable(){
                  public void run(){
                     numberOfThreads--;
                     parallelMergeSort(g, a, l, m);  
                  }    
              };
              Runnable last = new Runnable(){
                  public void run(){
                     numberOfThreads--;
                     parallelMergeSort(g, a, m + 1, r);
                  }
              };
                 
              Thread threads[] = {new Thread(first), new Thread(last)};
              try{
                  threads[0].start();
                  threads[1].start();
                  threads[0].join();
                  threads[1].join();
                  
              }
              catch(Exception ex){
                  System.out.println(ex);
              }
              // Sort the first and the second half
              merge(g, a, l, m, r);
           }
        }
    }

    private static void mergeSort(Graphics g, Bar a[], int from, int to) {
        if (from == to) {
            return;
        }
        int mid = (from + to) / 2;
        // Sort the first and the second half
        mergeSort(g, a, from, mid);
        mergeSort(g, a, mid + 1, to);
        merge(g, a, from, mid, to);
    }
    
    @SuppressWarnings("unchecked")
    private static void merge(Graphics g, Bar a[], int from, int mid, int to) {
        int n = to - from + 1;
         // Size of the range to be merged

        // Merge both halves into a temporary array b
        int[] b = new int[n];

        int i1 = from;
        // Next element to consider in the first range
        int i2 = mid + 1;
        // Next element to consider in the second range
        int j = 0;
         // Next open position in b

        // As long as neither i1 nor i2 past the end, move
        // the smaller element into b
        //System.out.println("" + i1 + ", " + i2 + ", " + a[0] + ", " + n + ", " + b[0]);
        while (i1 <= mid && i2 <= to) {
            if (a[i1].compare(g, a[i2]) < 0) {
                //b[j] = a[i1];
                b[j] = a[i1].getHeight(g);
                a[i1].hide(g);
                i1++;
            } else {
                //b[j] = a[i2];
                b[j] = a[i2].getHeight(g);
                a[i2].hide(g);
                i2++;
            }
            j++;
        }

        // Note that only one of the two while loops
        // below is executed
        // Copy any remaining entries of the first half
        while (i1 <= mid) {
            //b[j] = a[i1];
            b[j] = a[i1].getHeight(g);
            a[i1].hide(g);
            //redundant but shows the graphical representation
            //((Bar)b[j]).equal(g, a[i1]);
            //((Bar)b[j]).draw(g);
            i1++;
            j++;
        }

        // Copy any remaining entries of the second half
        while (i2 <= to) {
            //b[j] = a[i2];
            b[j] = a[i2].getHeight(g);
            a[i2].hide(g);
            i2++;
            j++;
        }

        // Copy back from the temporary array
        for (j = 0; j < n; j++) {
            //a[from + j] = (Bar) b[j];
            //a[from + j].equal(g, a[from + j]);
            a[from + j].setHeight(g, b[j]);
        }   
    }
}