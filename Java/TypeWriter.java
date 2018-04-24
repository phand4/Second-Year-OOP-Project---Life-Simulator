import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TypeWriter {

    private JTextField input;
    private JTextArea output;

    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(() -> new TypeWriter().createAndShowGUI());
    }

    public void createAndShowGUI() {
        output = new JTextArea();
        output.setEditable(false);

        input = new JTextField();
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slowPrint("This is a test answer.\n", 100);
            }
        });

        JPanel contentPane = new JPanel(new BorderLayout(5, 5));
        contentPane.add(input, BorderLayout.NORTH);
        contentPane.add(output);

        JFrame frame = new JFrame("Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(contentPane);
        frame.setSize(800, 600);
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