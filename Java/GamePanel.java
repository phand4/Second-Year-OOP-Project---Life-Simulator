import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class GamePanel {
	
	private ArrayList<Person> people = new ArrayList<Person>();
	private ArrayList<Event> events = new ArrayList<Event>();
	private Player player;
	private JTextField input;
	private JTextArea output;
	
	private Font font= new Font("Veranda", Font.PLAIN, 24);
	
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
	
	private void createGame() throws IOException, SQLException
	{
		deleteDB();
		Random r = new Random();
		int i = r.nextInt(40);
		InitialiseGame setup = new InitialiseGame();
		setup.fillWorld();
		people = setup.returnPeopleArray();
		events = setup.returnEventArray();
		player = new Player(people.get(i));
	}
	
	public void start() throws IOException, SQLException {
		createGame();
		JFrame frame = new JFrame("");
		frame.toFront();
		frame.setLocation(450, 100);
		frame.setSize(1000, 700);
		frame.getContentPane().setBackground(Color.BLACK);
		FlowLayout layout = new FlowLayout();
		layout.setVgap(5);
		layout.setHgap(5);
		frame.setLayout(layout);
		input = new JTextField();
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		panel.setBackground(Color.BLACK);
		/*JLabel label = new JLabel(player.toString());
		label.setFont(font);
		label.setBounds(5, 5, 995, 695);
		label.setForeground(Color.WHITE);
		panel.add(label);*/
		slowPrint(player.toString(), 100);
		panel.add(output);
		frame.setContentPane(panel);
		frame.setVisible(true);
		
	}
	

    public void slowPrint(String message, int millisPerChar) {
        input.setEditable(false);
        input.setFocusable(false);
        Timer timer = new Timer(millisPerChar, null);
        timer.addActionListener(new ActionListener() {
        		int counter = 0;
                @Override
                public void actionPerformed(ActionEvent e) {
                    output.append(String.valueOf(message.charAt(counter++)));
                    output.setCaretPosition(output.getDocument().getLength());
                    if (counter >= message.length()) {
                        timer.stop();
                        input.setEditable(true);
                        input.setFocusable(true);
                        input.requestFocusInWindow();
                    }
                }
            });
            timer.start();
        }
	}
