package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {
	
	public void render(Graphics g) {
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0); 
		g.setColor(Color.white);
		g.drawString("Game of Life", Game.WIDTH/2, 100);
		
	}

}
