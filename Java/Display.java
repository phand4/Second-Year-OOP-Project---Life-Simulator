import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Display {
    private JFrame jframe;
    private static Canvas canvas;
    private String title;
    private int width, height;

    public Display(String tuade, int rong, int dai) {
        this.title = tuade;
        this.width = dai;
        this.height = rong;
        initCanvas();
    }

    private void initCanvas() {

        jframe = new JFrame(title);
        jframe.setSize(width, height);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setVisible(true);
        jframe.setLocationRelativeTo(null);
        
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setBackground(Color.BLACK);
        canvas.setMinimumSize(new Dimension(width, height));
        
        JButton but = new JButton("Button");
        
        but.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                but.setBackground(Color.GREEN);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                but.setBackground(UIManager.getColor("control"));
            }
        });
        
        jframe.add(but, BorderLayout.SOUTH);
        jframe.add(canvas);
        jframe.pack();

    }

    public Canvas getCanvas() {

        if(canvas == null)
        {
            System.out.println("Canvas is null");
            return null;
        }

        return canvas;
    }
}