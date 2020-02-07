package test;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	
	public int x = 0;
	public int y = 0;
	public int d = 0;
	public int s = 6;
	public Color c = new Color(0,0,129);
	
	public final int step = 2;
	
	public void draw (Graphics g) {
		g.setColor(c);
		g.fillOval(x-4, y-4, 8, 8);
	}
	
	public boolean HitTest(int ox,int oy) {
		return this.x>=ox-4 && this.x<=ox+4 && this.y>=oy-4 && this.y<=oy+4;
	}
	
	public void advance()
	{
		if(d==0) {
			y-=s;
		}
		if(d==1) {
			x+=s;
		}
		if(d==2) {
			y+=s;
		}
		if(d==3) {
			x-=s;	
		}
	}
}
