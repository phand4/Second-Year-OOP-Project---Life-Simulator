import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.*;

@SuppressWarnings("serial")
public class TextAreaOutputStreamTest extends JPanel {

   private JTextArea textArea = new JTextArea(15, 30);
   private TextAreaOutputStream taOutputStream = new TextAreaOutputStream(
         textArea, "GameOfLife");

   public TextAreaOutputStreamTest() {
      setLayout(new BorderLayout());
      add(new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
      System.setOut(new PrintStream(taOutputStream));

      int timerDelay = 1000;
      String message = "Pls print this message slowly";
      new Timer(timerDelay , new ActionListener() {
         int count = 0;
         @Override
         public void actionPerformed(ActionEvent arg0) {

            // though this outputs via System.out.println, it actually displays
            // in the JTextArea:
        	System.out.println("Count is now: " + count + " seconds");
        	slowPrint(message, 10);
        	count++;
         }
      }).start();
   }
   private static void slowPrint(String message, long millis)
   {
	   for(int i = 0; i < message.length(); i++)
	   {
		   System.out.print(String.valueOf(message.charAt(i)));
		   try{
			   Thread.sleep(millis);
		   }
		   catch(InterruptedException e)
		   {
			   e.printStackTrace();
		   }
	   }
   }

   private static void createAndShowGui() {
      JFrame frame = new JFrame("Test");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(new TextAreaOutputStreamTest());
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}