import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GameText {
	private static final int TICK_RATE = 250;
	private String text;
	private Graphics g;
	private int x, y;
	
	public GameText(String text, Graphics g, int x, int y)
	{
		this.text = text;
		this.g = g; 
		this.x = x;
		this.y = y;
	}
	
	public void toScreen()
	{
		int i;
		for(i = 1; i <= text.length(); i++)
		{
			g.drawString(text.substring(0, i), x, y);
			try{
				Thread.sleep(TICK_RATE);
			}
			catch(InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public void newText(String text)
	{
		this.text = text;
	}
}
