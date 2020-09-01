import java.awt.*;
import java.util.*;
/**
   This class sorts various list data types using Zachary Vasey's sorting algorithm.
   @author Zachary Vasey
*/
public class VaseySort{
   /**
      This sort finds the min and max. It then goes the list starting from the min and checks if the an element is the minimum. If so, that element is switched. Repeats for min + 1, min + 2, ... , max - 1, max. Very inefficient and can be improved
      @param g Graphics to draw.
      @param list List to sort
   */
   public static void sort1(Graphics g, Bar list[]){
      //Do nothing if the list is empty.
      if(list.length <= 0){
         return;
      }
      int max = list[0].getHeight(), least = list[0].getHeight();
      for(int i = 1; i < list.length; i++){
         //list[i].compare(g, list[i]);
         if(max < list[i].getHeight(g)){
            max = list[i].getHeight();
            //list[i].hide(g);
         }
         
         if(least > list[i].getHeight(g)){
            least = list[i].getHeight();
            //list[i].hide(g);
         }
      }
      int currentIndex = 0;
      for(int i = least; i <= max; i++){
         for(int n = currentIndex; n < list.length; n++){
            if(list[n].getHeight(g) == i){
               list[n].swapValues(g, list[currentIndex]);
               list[n].draw(g);
               list[currentIndex].draw(g);
               currentIndex++;
               least = max;
               
               //try{Thread.sleep(10);}catch(Exception ex){}
            }
         }
      }
   }
   /**
      This sort goes through the list, finds the min and max and pushes them to the left and right. It repeats until the list is sorted. Inefficient and can be improved
      @param g Graphics to draw.
      @param list List to sort
   */
   public static void sort2(Graphics g, Bar list[]){
      //Do nothing if the list is empty.
      if(list.length <= 0){
         return;
      }
      int max, least;
      for(int i = 0; i < (list.length / 2); i++){
         max = i;
         least = i;
         for(int n = i; n < (list.length - i); n++){
            //list[n].compare(g, list[n]);
            if(list[n].getHeight(g) < list[least].getHeight(g)){
               least = n;
            }
            if(list[n].getHeight(g) > list[max].getHeight(g)){
               max = n;
            }
         }
         
         //problems happen when least is list.length - i - 1 or max is i\\
         
         if(max == i && least == list.length - i - 1){
            list[i].swapValues(g, list[list.length - i - 1]);
            list[list.length - i - 1].draw(g);
            list[i].draw(g);
         }
         else if(max == i){
             max++;
             if(max == least){
               max++;
             }
             list[max].swapValues(g, list[i]);
         }
         else if(least == list.length - i - 1){
             least--;
             if(max == least){
               least--;
             }
             list[least].swapValues(g, list[list.length - i - 1]);
         }
         else{
            //error checking
         }
          list[i].swapValues(g, list[least]);
          list[i].draw(g);
          list[least].draw(g);
          list[list.length - i - 1].swapValues(g, list[max]);
          list[list.length - i - 1].draw(g);
          list[max].draw(g);
      }
   }
}