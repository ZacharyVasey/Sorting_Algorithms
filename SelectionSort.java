import java.awt.*;
import java.util.*;
/**
   This class sorts various list data types using selection sort.
   @author Zachary Vasey
*/
public class SelectionSort{
   /**
      Gets the time the sort takes.
      @param g Graphics to draw.
      @param list List to sort
   */
   public static long sortTime(Graphics g, LineSegment list[]){
      long start = System.currentTimeMillis();
      sort(g, list);
      return System.currentTimeMillis() - start;
   }
   /**
      Gets the time the sort takes.
      @param list List to sort
   */
   public static long sortTime(int list[]){
      long start = System.currentTimeMillis();
      sort(list);
      return System.currentTimeMillis() - start;
   }
   /**
      Sorts a LineSegment List
      @param g Graphics to draw.
      @param list List to sort
   */
   public static void sort(Graphics g, LineSegment list[]){
      int maxIndex;
      for(int i = list.length - 1; i > 1; i--){
         maxIndex = 0;
         for(int n = 1; n < i; n++){
            if(list[maxIndex].getHeight() < list[n].getHeight()){
               maxIndex = n;
            }      
         }
         //try{Thread.sleep(10);}catch(Exception ex){} 
         list[i].swapValues(g, list[maxIndex]);
         list[i].draw(g);
         list[maxIndex].draw(g);
      }
   }
   /**
      Sorts a Integer List
      @param list List to sort
   */
   public static void sort(int list[]){
      int maxIndex;
      for(int i = list.length - 1; i > 1; i--){
         maxIndex = 0;
         for(int n = 1; n < i; n++){
            if(list[maxIndex] <= list[n]){
               maxIndex = n;
            }
            try{Thread.sleep(1);}catch(Exception ex){}      
         }
         //try{Thread.sleep(10);}catch(Exception ex){} 
         int temp = list[i];
         list[i] = list[maxIndex];
         list[maxIndex] = temp;
      }
   }
   /**
      Sorts a Bar List
      @param g Graphics to draw.
      @param list List to sort
   */
   public static void sort(Graphics g, Bar list[]){
      int maxIndex;
      for(int i = list.length - 1; i >= 0; i--){
         maxIndex = 0;
         for(int n = 0; n <= i; n++){
            if(list[maxIndex].getHeight(g) <= list[n].getHeight(g)){
               maxIndex = n;
            }      
         }
         //try{Thread.sleep(10);}catch(Exception ex){} 
         list[i].swapValues(g, list[maxIndex]);
         list[i].draw(g);
         list[maxIndex].draw(g);
      }
   }
}