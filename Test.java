import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.lang.*;
import javax.swing.*;
/**
   This class uses Swing and Grapics to display and control the sorting algorithms.
   @author Zachary Vasey
*/
public class Test extends Applet implements ActionListener{
   private final Dimension APPLET_SIZE = new Dimension(1000, 650);
   private Color bgColor = new Color(255, 255, 255);
   private Color lineColor = new Color(0, 0, 0);
   ///////////////////////////////////////////
   private int numBars = 50;
   private final int MAX_HEIGHT = 600;
   ///////////////////////////////////////////
   private int width;
   private Bar list[] = new Bar[numBars];
   private JLabel delayLbl, numBarsLbl, legendLbl, checkLbl, swapLbl, equalLbl, hideLbl;
   private JPanel uiCollection;
   private JTextField delayTxt;
   private JButton startBtn, resetBtn;
   private boolean inProgress = false, reset = true;
   private JComboBox<String> sortOptionJcb;
   private JComboBox<String> numBarsJcb;
   private Point uiLocation = new Point(5, 605);
   private String[] sortingAlgs = {"Merge Sort", "Selection Sort", " Bubble Sort", "Vasey Sort 1", "Vasey Sort 2"};
   private String[] numberOfBars = {"10", "20", "50", "100", "250", "500"};
   /**
      This method runs before the paint method. Used to initialize most of the swing componets and applet window.
   */
   public void init(){
      
      uiCollection = new JPanel();
      uiCollection.setLocation(uiLocation);
      uiCollection.setSize(new Dimension(990, 40));
      uiCollection.setLayout(new FlowLayout()); //setLayout(new PropertionalLayoutManager());
      this.add(uiCollection);
      
      startBtn = new JButton("Start");
      startBtn.setActionCommand("start");
      uiCollection.add(startBtn);
      
      resetBtn = new JButton("Reset");
      resetBtn.setActionCommand("reset");
      uiCollection.add(resetBtn);
      
      delayLbl = new JLabel("Delay between a singular action: ");
      uiCollection.add(delayLbl);
      
      delayTxt = new JTextField("DELAY");     
      uiCollection.add(delayTxt);
      
      sortOptionJcb = new <String>JComboBox(sortingAlgs);
      uiCollection.add(sortOptionJcb);
      
      numBarsLbl = new JLabel("Number of elements: ");
      uiCollection.add(numBarsLbl);
      
      numBarsJcb = new <String>JComboBox(numberOfBars);
      numBarsJcb.setActionCommand("numbers");
      uiCollection.add(numBarsJcb);
      
      legendLbl = new JLabel("Legend");
      uiCollection.add(legendLbl);
      checkLbl = new JLabel("<html><font size = '5' color = 'yellow' bgcolor = #000000>Check</font></html>");
      uiCollection.add(checkLbl);
      swapLbl = new JLabel("<html><font size = '5' color = #FF00FF bgcolor = #000000>Swap</font></html>");
      uiCollection.add(swapLbl);
      equalLbl = new JLabel("<html><font size = '5' color = 'green' bgcolor = #000000>Equal </font></html>");
      uiCollection.add(equalLbl);
      hideLbl = new JLabel("<html><font size = '5' color = #00FFFF bgcolor = #000000>Hide</font></html>");
      uiCollection.add(hideLbl);
      
      startBtn.setVisible(true);
      resetBtn.setVisible(true);
      delayLbl.setVisible(true);
      delayTxt.setVisible(true);
      numBarsJcb.setVisible(true);
      sortOptionJcb.setVisible(true);
      
      uiCollection.setVisible(true);
      this.setSize(APPLET_SIZE);
      this.setMaximumSize(APPLET_SIZE);
      this.setMinimumSize(APPLET_SIZE);
      
      resetBtn.addActionListener(this);
      startBtn.addActionListener(this);
      numBarsJcb.addActionListener(this);
   }
   /**
      Used to display everything within the applet window after init()
      @param g Graphics to draw
   */
   public void paint(Graphics g){
      
      this.add(uiCollection);
      uiCollection.setVisible(true);
      uiCollection.setLocation(uiLocation);
      
      numBars = Integer.parseInt((String)numBarsJcb.getSelectedItem());
      width = 1000 / (numBars * 2);
      list = new Bar[numBars];
        
      
      
      
      
      
      if(delayTxt.getText().equals("")) delayTxt.setText(" ");
      delayTxt.setVisible(true);
     
      populateRandom(g, list);     
      drawBarList(g, list);
   }
   
   //////////////////////////////////  
   /**
      This method is the listener for the two buttons and number of bars JCombo Box
      @param ae ActionEvent from the component
   */
   public void actionPerformed(ActionEvent ae) { 
      Test window = this;
      Runnable r = new Runnable(){
               public void run(){
                  Bar.setDelay(0);
               }
            };
      Runnable r2 = new Runnable(){
               public void run(){
                  sortVisual(window.getGraphics());
               }
            };
      Thread sort = new Thread(r2);
      Thread stop = new Thread(r);
      switch(ae.getActionCommand()){
         case "reset":
            stop.start();
            try{
               sort.join();
               //stop.join();/
               populateRandom(this.getGraphics(), list);
            }
            catch(Exception ex){
               System.out.print("unable to stop: " + ex);
            }
            //populateRandom(this.getGraphics(), list);
            
            break;
         case "start":
            sort.start();
            break;
         case "numbers":
            numBars = Integer.parseInt((String)numBarsJcb.getSelectedItem());
            width = 1000 / (numBars * 2);
            list = new Bar[numBars];
            clearBars(this.getGraphics());
            populateRandom(this.getGraphics(), list);
            drawBarList(this.getGraphics(), list);
            break;
      }
   }
   ///////////////////////////////////
   private void sortVisual(Graphics g){
         
         try{
            Bar.setDelay(Integer.parseInt(delayTxt.getText()));
         }
         catch(Exception ex){
            Bar.setDelay(5);
            delayTxt.setText("5");
         }
         delayTxt.setEnabled(false);
         switch(sortOptionJcb.getSelectedIndex()){
            case 0:
                     MergeSort.setNumberOfThreads();
                     MergeSort.sort(g, list);
                     break;
            case 1:
                     SelectionSort.sort(g, list);
                     break;
            case 2:
                     BubbleSort.sort(g, list);
                     break;
            case 3:
                     VaseySort.sort1(g, list);
                     break;
            case 4:  
                     VaseySort.sort2(g, list);
                     break;
         
            default: SelectionSort.sort(g, list);
         }
         
         delayTxt.setEnabled(true);
      
      
   
   }
   
   private void clearBars(Graphics g){
      Color temp = g.getColor();
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, 1000, 600);
      g.setColor(temp);
   
   }
   private void drawBarList(Graphics g, Bar list[]){
      for(int i = 0; i < list.length; i++){
         list[i].draw(g);
      }

   }
   //public Bar(int xPos, int height, int maxHeight, int width, Color bgColor, Color lineColor)
   /**
      Gives the Bar list a new random value.
      @param g Graphics to draw
      @param list Bar list to be modified
   */
   public void populateRandom(Graphics g, Bar list[]){
      for(int i = 0; i < list.length ; i++){
         list[i] = new Bar(i * width * 2, (int)(Math.random() * MAX_HEIGHT), MAX_HEIGHT, width, bgColor, lineColor);
         list[i].draw(g);
      }
   }
   /**
      Gives the Bar list values that will be the worst possible scenario for sorting. The list is sorted from least to greatest; this method makes the list have values greatest to least proportionally.
      @param g Graphics to draw
      @param list Bar list to be modified
   */
   public void populateWorst(Graphics g, Bar list[]){
      double ratioHeight;
      for(int i = 0; i < list.length ; i++){
         ratioHeight = (list.length - (double)i)/(list.length);
         list[i] = new Bar(i * 10, (int)(ratioHeight * 600), 600, 5, bgColor, lineColor);
         list[i].draw(g);
      }
   }
}