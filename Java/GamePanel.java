import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class GamePanel implements ActionListener, KeyListener{
	
	public static Player player;
	private static JTextField input;
	public static JTextArea output = new JTextArea();
	public static JFrame frame = new JFrame("");
	
	private static Font font= new Font("Monospaced", Font.PLAIN, 20);
	
	private static void deleteDB()
	{
		try
		{
			File file = new File("C:\\sqlite\\db\\game.db");
			if(file.delete())
			{
				System.out.println("Starting anew");
			}
			else
			{
				System.out.println("Deletion failed.");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
	
	private static void createGame() throws IOException, SQLException
	{
		deleteDB();
		Random r = new Random();
		int i = r.nextInt(39) + 1;
		InitialiseGame setup = new InitialiseGame();
		setup.fillWorld();
		player = new Player(RequestDatabase.getPerson(i));
	}
	
	public static void start() throws IOException, SQLException {
		createGame();	
		frame.getContentPane().setBackground(Color.black);
		frame.toFront();
		frame.setVisible(true);
		frame.setLocation(450, 100);
		frame.setSize(1000, 700);
		frame.setFont(font);
		frame.addKeyListener(new EnterKeyListener());
		/*FlowLayout layout = new FlowLayout();
		layout.setVgap(5);
		layout.setHgap(5);
		frame.setLayout(layout);*/
		input = new JTextField();
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		panel.setBackground(Color.black);
		
		/*JLabel label = new JLabel(player.toString());
		label.setFont(font);
		label.setBounds(5, 5, 995, 695);
		label.setForeground(Color.WHITE);
		panel.add(label);*/

		slowPrint(player.toString(), 50);
		panel.add(output);		
		frame.setContentPane(panel);
		frame.setVisible(true);
		JButton submitButton = new JButton();
		frame.getRootPane().setDefaultButton(submitButton);
		
	}
	
	public static void startBGMusic() { //Plays the background music
        //make a new AudioPlayer.
          AudioPlayer myBackgroundPlayer = AudioPlayer.player;

          ContinuousAudioDataStream myLoop = null;
			//use a try block in case the file doesn't exist.
          try {
        	  String bip = "res/main_menu.wav";
          AudioStream myBackgroundMusic = new AudioStream(new FileInputStream(new File(bip).toURI().toString()));
          AudioData myData = myBackgroundMusic.getData();
          myLoop = new ContinuousAudioDataStream(myData);
          }
          catch(Exception error) {JOptionPane.showMessageDialog(null, "Invalid file!");}

          // play background music.
          myBackgroundPlayer.start(myLoop);
    }
	
	public static void music(){
		
		String bip = "res/game_music.mp3";
		Media hit = new Media(new File(bip).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
		
	}

    public static void slowPrint(String message, int millisPerChar) throws IOException {
        input.setEditable(false);
        input.setFocusable(false);
        Timer timer = new Timer(millisPerChar, null);
        frame.setFont(font);
        output.setBackground(Color.BLACK);
    	output.setForeground(Color.WHITE);
    	output.setFont(font);
        frame.getContentPane().setBackground(Color.BLACK);
        timer.addActionListener(new ActionListener() {
        		int counter = 0;
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (counter == message.length()) 
                    {
                        timer.stop();
                        input.setEditable(true);
                        input.setFocusable(true);
                        input.requestFocusInWindow();
                        return;
                    }
                    else
                    {	
                    	output.append(String.valueOf(message.charAt(counter)));
                    	output.setCaretPosition(output.getDocument().getLength());
                    	counter++;
                    }
                }
            });
            timer.start();
        }

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) throws IOException, SQLException
	{
		startBGMusic();
		start();
	}
}
class EnterKeyListener extends KeyAdapter
{
    public void keyPressed(KeyEvent e)
    {
    	System.out.println(e.getKeyCode() + e.getKeyChar());
    	boolean loop = true;
    	if(e.getKeyCode() == KeyEvent.VK_ENTER)
    	{
    		Random r = new Random();
    		int i = r.nextInt(19) + 1;
    		try
    		{
    			Event event = RequestDatabase.getEvent(i);
    			while( loop )
    			{
    				if(GamePanel.player.getAge() < event.getMinAge() || GamePanel.player.getAge() > event.getMaxAge())
    				{
    					i = r.nextInt(19) + 1;
    					event = RequestDatabase.getEvent(i);
    				}
    				else
    				{
    					loop = false;
    				}
    			}
    			String ev = event.printEvent();
    			GamePanel.slowPrint(ev, 50);
    			event.runEvent(GamePanel.player);
    		}
    		catch(Exception e1)
    		{
    			e1.printStackTrace();
    		}
    	}
    }
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
