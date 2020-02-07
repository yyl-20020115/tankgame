package test;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Tank {
	public int x = 0;
	public int y = 0;
	public int d = 0;
	public Color c = new Color(0,0,0);
	public int w=0;
	public int h=0;
	public int speed = 2;
	
	public int blood=3;
	
	
	public List<Bullet> bullets = new ArrayList<Bullet>();
	
	public boolean hit(Bullet b) {
		return b.y>=this.y-10 && b.y<=this.y+10 && b.x>=this.x-10 && b.x<=this.x+10;
	}
	
	public boolean hitsFrom(List<Tank> tanks) {
		for(Tank tank : tanks) {
			if(this.getHits(tank.bullets)) {
				return true;
			}
		}
		return false;
	}
	public boolean getHits(List<Bullet> bs) {
		boolean f = false;
		List<Bullet> used = new ArrayList<Bullet>();
				
		for(Bullet b : bs) {
			if(this.hit(b))
			{
				used.add(b);
				this.blood --;
				if(this.blood == 0) {
					f = true;
					break;
				}
			}
		}

		bs.removeAll(used);
		return f;
	}
	
	public void advance(int ox, int oy, int ow, int oh,List<Block> blocks)
	{
		List<Bullet> done = new ArrayList<Bullet>();
		for(Bullet b : this.bullets) {
			b.advance();
			if(b.HitTest(ox, oy)) {
				done.add(b);
			}
		}
		if(done.size()>0) {
			this.bullets.removeAll(done);
		}
	}
	public final int gap = 22;
	public void fire() {
		Bullet b= new Bullet();
		switch(this.d) {
		case 0:
			b.x = this.x;
			b.y = this.y-gap;
			break;
		case 1:
			b.x = this.x+gap;
			b.y = this.y;
			break;
		case 2:
			b.x = this.x;
			b.y = this.y+gap;
			break;
		case 3:
			b.x = this.x-gap;
			b.y = this.y;
			break;
		}
		b.d = this.d;
		this.bullets.add(b);
	}
	protected void fence(int ox,int oy,int ow,int oh,List<Block> blocks) {
		if(this.x-16<=ox) {
			this.x = ox+16;
		}
		if(this.y-16<=oy) {
			this.y = oy+16;
		}
		if(this.x+16>=ox+ow) {
			this.x = ox+ow-16;
		}
		if(this.y+16>=oy+oh) {
			this.y = oy+oh-16;
		}
		
		for(Block b : blocks) {
			boolean any =false;
			switch(this.d) {
			case 0:
				if(this.y-16<=b.y+16 && this.y-16>=b.y-16 && this.x>=b.x-16 && this.x<=b.x+16) 
				{
					this.y = b.y+ 32;
					//any=true;
				}
				break;
			case 1:
				if(this.x+16>=b.x-16 && this.x+16<=b.x+16 && this.y>=b.y-16 && this.y<=b.y+16) 
				{
					this.x = b.x -32; 
					//any=true;
				}
				break;
			case 2:
				if(this.y+16>=b.y -16 && this.y+16<=b.y+16 && this.x>=b.x-16 && this.x<=b.x+16) 
				{
					this.y = b.y-32;
					//any=true;
				}
				break;
			case 3:
				if(this.x-16>=b.x-16 && this.x-16<= b.x+16 && this.y>=b.y-16 && this.y<=b.y+16) 
				{
					this.x = b.x+32;
					//any=true;
				}
				break;
			}
			if(any)break;
		}
		
		
	}
	public void drawTank(Graphics g) {
		this.drawTank(g,x,y,w,h,d,c);
	}
	public void drawTank(Graphics g,int x, int y, int w, int h, int d, Color c) {
		
		g.setColor(c);
		
		switch(this.d) {		
		case 0:
			g.fillOval(x-10, y-10, 20, 20);
			g.fillRect(x+5-10, y-5-10, 10, 10);
			
			break;
		case 1:
			g.fillOval(x-10, y-10, 20, 20);
			g.fillRect(x+17-10, y+5-10, 10, 10);
			break;
		case 2:
			g.fillOval(x-10, y-10, 20, 20);
			g.fillRect(x+6-10, y+17-10, 10, 10);
			break;
		case 3:
			g.fillOval(x-10, y-10, 20, 20);
			g.fillRect(x-5-10, y+5-10, 10, 10);
			break;
		}
		for(Bullet b : this.bullets)
		{
			b.draw(g);
		}
		
	}
}
