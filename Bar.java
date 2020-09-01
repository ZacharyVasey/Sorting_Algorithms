import java.awt.*;
import java.util.*;
/**
   This class allows a Bar similar to a bar graph bar to be drawn. 
   @author Zachary Vasey 
*/
public class Bar{
   
   protected static int DELAY = 5;  //lower values will increase chance of graphical glitches
   protected int xPos, height, width, maxHeight;
   protected Color bgColor, barColor;
   protected final Color CHECK_COLOR = new Color(255, 255, 0);
   protected final Color SWAP_COLOR = new Color(255, 0, 255);
   protected final Color EQUAL_COLOR = new Color(0, 255, 0);
   protected final Color HIDE_COLOR = new Color(0, 255, 255);
   /**
      This is an attempt to make drawing the Bars more thread safe. Multithreading with Java.swing is not thread safe 
      @see javax.swing.JFrame  
   */
   protected boolean avalible = true;
   /**
      Creats a new Bar
      @param xPos Sets the x posision in the window.
      @param height Sets the height of the Bar
      @param maxHeight Sets the maximum height the Bar can be.
      @param width Sets the width of the Bar.
      @param bgColor Sets the color of the background. Recommend the same color as the window.
      @param barColor Sets the color of the Bar.
   */
   public Bar(int xPos, int height, int maxHeight, int width, Color bgColor, Color barColor){
      this.xPos = xPos;
      this.height = height;
      this.width = width;
      this.maxHeight = maxHeight;
      this.bgColor = bgColor;
      this.barColor = barColor;
   }
   /**
      Creates a new copy of a bar.
      @param g Graphics to draw the bar.
      @param other Bar to create a copy of.
   */
   public Bar(Graphics g, Bar other){
      this.xPos = other.getXPos();
      this.height = other.getHeight(g);
      this.width = other.getWidth();
      this.maxHeight = other.getMaxHeight();
      this.bgColor = other.getBGColor();
      this.barColor = other.getBarColor();
   }
   /**
      Sets the delay between actions when used in an animation.
      @param delay The delay in miliseconds between actions.
   */
   public static void setDelay(int delay){
      DELAY = delay;
   }
   /**
      Hides the bar.
      @param g Graphics to draw
   */
   public void hide(Graphics g){
      waitUntil();
      avalible = false;
      this.drawHideBar(g);
      try{Thread.currentThread().sleep(DELAY);}catch(Exception ex){}
      avalible = true;
   }
   /**
      Checks if this bar equal another
      @param g Graphics to draw.
      @param other The other bar
   */
   public void swapValues(Graphics g, Bar other){
      waitUntil();
      avalible = false;
      
      int temp = this.height;
      this.height = other.height;
      other.height = temp;
       
      this.drawCoverBar(g);
      other.drawCoverBar(g);
      
      this.drawSwapBar(g);
      other.drawSwapBar(g);
      
      try{Thread.currentThread().sleep(DELAY);}catch(Exception ex){}
      
      this.drawBar(g);
      other.drawBar(g);
      
      avalible = true;
   }
   /**
      Height Mutator. Also draw graphical reprsentation. 
      @param g Graphics to draw
      @param height Value to set to
   */
   public void setHeight(Graphics g, int height){
      waitUntil();
      avalible = false;
      
      this.drawSwapBar(g);
      
      this.height = height;
      
      try{Thread.currentThread().sleep(DELAY);}catch(Exception ex){}
       
      this.drawCoverBar(g);
      this.drawBar(g);
      
      avalible = true;
   }
   /**
      Checks if this bar equal another
      @param g Graphics to draw.
      @param other The other bar
   */
   public void equal(Graphics g, Bar other){
      waitUntil();
      avalible = false;
      this.height = other.getHeight(g);
       
      this.drawCoverBar(g);
      other.drawCoverBar(g);
      
      this.drawEqualBar(g);
      other.drawEqualBar(g);
      
      try{Thread.currentThread().sleep(DELAY);}catch(Exception ex){}
      
      this.drawBar(g);
      other.drawBar(g);
      
      avalible = true;
   }
   /**
      To String Override
      @return String representation
   */
   public String toString(){
      return "" + this.height;
   }
   /**
      Height Accessor
      @return height
   */
   public int getHeight(){
      return this.height;
   }
   /**
      Height Accessor. Also draw graphical reprsentation.
      @param g Graphics to draw
      @return height  
   */
   public int getHeight(Graphics g){
      waitUntil();
      avalible = false;
      
      this.drawCoverBar(g);
      this.drawCheckBar(g);
      
      try{Thread.currentThread().sleep(DELAY);}catch(Exception ex){}
      
      this.drawBar(g);
      
      avalible = true;
      
      return this.height;
   }
   /**
      Width Accessor
      @return width
   */
   public int getWidth(){
      return this.width;
   }
   /**
      XPos Accessor
      @return width
   */
   public int getXPos(){
      return this.xPos;
   }
   /**
      MaxHeight Accessor
      @return maxHeight
   */
   public int getMaxHeight(){
      return this.maxHeight;
   }
   /**
      Background Color Accessor
      @return Background Color
   */
   public Color getBGColor(){
      return this.bgColor;
   }
   /**
      Bar Color Accessor
   */
   public Color getBarColor(){
      return this.barColor;
   }
   /**
      Compairs this line with another. Also draw graphical reprsentation.
      @param other The other line to be compaired to this line.
      @return The height of this line minus the height of the other line.
   */
   public int compare(Graphics g, Bar other){
      waitUntil();
      avalible = false;
      
      this.drawCoverBar(g);
      other.drawCoverBar(g);
      
      this.drawCheckBar(g);
      other.drawCheckBar(g);
      
      try{Thread.currentThread().sleep(DELAY);}catch(Exception ex){}
      
      this.drawBar(g);
      other.drawBar(g);
      
      avalible = true;
      
      return this.height - other.getHeight(g);
   }
   /**
      Compairs this line with another.
      @param other The other line to be compaired to this line.
      @return The height of this line minus the height of the other line.
   */
   public int compare(Bar other){
        return this.height - other.getHeight();
   }
   /**
      Draws the bar.
      @param g Graphics to draw.
   */
   public void draw(Graphics g){
      waitUntil();
      avalible = false;
      //draw over a possible previous line
      this.drawCoverBar(g);   
      //draw the line
      this.drawBar(g);
      
      avalible = true;
   }
   
   private void drawBar(Graphics g){
      g.setColor(barColor);
      fillRect(g, xPos, maxHeight, xPos + width, maxHeight - height);
   }
   
   private void drawCoverBar(Graphics g){
      g.setColor(bgColor);
      fillRect(g, xPos, maxHeight, xPos + width, 0);
   }
   
   private void drawCheckBar(Graphics g){
      g.setColor(CHECK_COLOR);
      fillRect(g, xPos, maxHeight, xPos + width, maxHeight - height);
   }
   
   private void drawEqualBar(Graphics g){
      g.setColor(EQUAL_COLOR);
      fillRect(g, xPos, maxHeight, xPos + width, maxHeight - height);
   }
   
   private void drawSwapBar(Graphics g){
      g.setColor(SWAP_COLOR);
      fillRect(g, xPos, maxHeight, xPos + width, maxHeight - height);
   }
   
   private void drawHideBar(Graphics g){
      g.setColor(HIDE_COLOR);
      fillRect(g, xPos, maxHeight, xPos + width, maxHeight - height);
   }
   
   private void waitUntil(){
      while(!avalible){
         try{Thread.currentThread().sleep(DELAY * 2);}catch(Exception ex){}
      }
   }
   
   private void fillRect(Graphics g, int x1, int y1, int x2, int y2){
      int width = Math.abs(x1 - x2);
      int height = Math.abs(y1 - y2);
      int xPoint, yPoint;
      if(x1 >= x2){
         xPoint = x2;
      }
      else{
         xPoint = x1;
      }
      
      if(y1 >= y2){
         yPoint = y2;
      }
      else{
         yPoint = y1;
      }
      g.fillRect(xPoint, yPoint, width, height);
   }
   
}