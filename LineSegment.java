import java.awt.*;
import java.util.*;

/**
   This class draws a line segment and can be compaired to other line segments
   @author Zachary Vasey
*/ 
public class LineSegment{

   protected int xPos, height, maxHeight;
   protected Color bgColor, lineColor;
   protected final Color CHECK_COLOR = new Color(255, 255, 0);
   /**
      Creats a new Line Segment
      @param xPos Sets the x posision in the window.
      @param height Sets the height of the Line
      @param maxHeight Sets the maximum height the line can be.
      @param bgColor Sets the color of the background. Recommend the same color as the window.
      @param lineColor Sets the color of the line.
   */
   public LineSegment(int xPos, int height, int maxHeight, Color bgColor, Color lineColor){
      this.xPos = xPos;
      this.height = height;
      this.maxHeight = maxHeight;
      this.bgColor = bgColor;
      this.lineColor = lineColor;
   }
   /**
      Swaps this line segment with another.
      @param g Graphics to draw lines again once the lines are swapped.
      @param other The other line to swap with this line.
   */
   public void swapValues(Graphics g, LineSegment other){
      int temp = height;
      this.height = other.height;
      other.height = temp;
       //draw over a possible previous line
      g.setColor(bgColor);
      g.drawLine(xPos, maxHeight, xPos, 0);
      g.drawLine(other.getXPos(), maxHeight, other.getXPos(), 0);
      
      g.setColor(CHECK_COLOR);
      g.drawLine(xPos, maxHeight, xPos, maxHeight - (maxHeight - height));
      g.drawLine(other.getXPos(), maxHeight, other.getXPos(), maxHeight - (maxHeight - other.getHeight()));
   }
   /**
      height accessor.
      @return The height of the line.
   */
   public int getHeight(){
      return this.height;
   }
   /**
      XPos accessor.
      @return The x position of the line.
   */
   public int getXPos(){
      return this.xPos;
   }
   /**
      Compairs this line with another.
      @param other The other line to be compaired to this line.
      @return The height of this line minus the height of the other line.
   */
   public int compare(LineSegment other){
      return this.height - other.getHeight();
   }
   /**
      Draws the line in the window.
      @param g Graphics to draw. 
   */
   public void draw(Graphics g){
      //draw over a possible previous line
      g.setColor(bgColor);
      g.drawLine(xPos, maxHeight, xPos, 0); //assuming line will continue to applet ceiling
      
      //draw the line
      g.setColor(lineColor);
      g.drawLine(xPos, maxHeight, xPos, maxHeight - height);
   }
}