import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Menu implements Runnable {
	
    private Display Display;
    private Thread t;
    private boolean running;
    public int dai, rong;
    private String tuade;
    private BufferStrategy bs;
    private Graphics g;
    private BufferedImage testImage;

    public Menu(String tuade, int dai, int rong) {
    	
        this.dai = dai;
        this.rong = rong;
        this.tuade = tuade;

    }

    @Override
    public void run() {
        init();
        System.err.println("run..." + running);
        while (running) {
            //System.err.println("run..." + running);
            tick();
            render();
        }
        //stop();
    }

    private void render() {
        bs = Display.getCanvas().getBufferStrategy();

        if (bs == null) {
            System.out.println("bs is null....");
            Display.getCanvas().createBufferStrategy(3);
            return;
        }
        
        Font myFont = new Font("Courier New", 1, 50 );
        
        
        
        

        g = Display.getCanvas().getGraphics();
        ////////////////////////////////////
        g.setFont(myFont);
        g.setColor(Color.WHITE);
        g.drawString("> New Game", 325, 400);
        g.drawString("> Rules", 375, 500);
        g.drawString("> Exit", 390, 600);

        g.drawImage(testImage, 30, 100, null);
        ////////////////////////////////////
    }

    private void tick() {

    }

    private static final class ImageLoader
    {

        static BufferedImage loadImage(String fileName)
        {
            BufferedImage bi = null;
            //System.err.println("....setimg...." + fileName);

            try {
                bi = ImageIO.read(new File(fileName)); 

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Image could not be read");
                System.exit(1);
            }

            return bi;
        }
    }

    private void init() {
        Display = new Display(tuade, dai, rong);
        testImage = ImageLoader.loadImage("res/GameofLife.png");
        
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        t = new Thread(this);
        t.start();

    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            t.join();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

    }
    
    public static void main(String[] args) {
    	
    	   Menu menu = new Menu("Game of Life", 800, 1000);
    	   menu.start();

    	}

}