import java.util.*;
import java.awt.*;
/**
   This class sorts various list data types using bubble sort.
   @author Zachary Vasey
*/
public class BubbleSort{
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
      Sorts a Bar List
      @param g Graphics to draw.
      @param list List to sort
   */
   public static void sort(Graphics g, Bar list[]){
   
      for(int i = list.length - 1; i > 0; i--){
         for(int n = 0; n < i; n++){
            if(list[n].compare(g, list[n + 1]) >= 0){
               list[n].swapValues(g, list[n + 1]);
            }
            //try{Thread.sleep(1);}catch(Exception ex){}
         }
      }
      
   }
   /**
      Sorts a LineSegment List
      @param g Graphics to draw.
      @param list List to sort
   */
   public static void sort(Graphics g, LineSegment list[]){
   
      for(int i = list.length - 1; i > 0; i--){
         for(int n = 0; n < i; n++){
            if(list[n].compare(list[n + 1]) > 0){
               list[n].swapValues(g, list[n + 1]);
               list[n].draw(g);
               list[n + 1].draw(g);
            }
           // try{Thread.sleep(1);}catch(Exception ex){}
         }
      }
   }
   /**
      Sorts a integer List
      @param list List to sort
   */
   public static void sort(int list[]){
      int temp;
      for(int i = list.length - 1; i > 0; i--){
         for(int n = 0; n < i; n++){
            if((list[n] - list[n + 1]) > 0){
               temp = list[n];
               list[n] = list[n + 1];
               list[n + 1] = temp;
            }
            //try{Thread.sleep(1);}catch(Exception ex){}
         }
      }
      
   }







}