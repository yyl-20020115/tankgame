package test;

import java.awt.Color;
import java.awt.Graphics;

public class Block {
	public int x = 0;
	public int y = 0;
	public int d = 0;
	public int s = 6;
	public Color c = new Color(0,0,0);
	
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillRect(x - 16,y - 16 , 32, 32);
	}
	
	public boolean hit(Bullet b) {
		return b.x>=this.x-16&& b.x<=this.x+16 && b.y>=this.y-16 && b.y<=this.y+16;
	}
}
