package test;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Designer {
	public int x = 16;
	public int y = 16;
	public int UpKey = 0;//KeyEvent.VK_W;
	public int DownKey =0;
	public int LeftKey =0;
	public int RightKey =0;
	public int BreakableBlockKey =0;
	public int NonbreakableBlockKey =0;
	
	
	public GamePanel gp = null;
	
	public Designer(GamePanel gp) {
		this.gp = gp;
	}
	public void draw(Graphics g) {
		g.drawRect(x-16, y-16, 32, 32);
	}
	
	
	public boolean keyPressed(KeyEvent e, int ox, int oy, int ow, int oh) {
		boolean flag = false;
		int key = e.getKeyCode();
		
		if(key == this.UpKey) {
			this.y-=32;
			flag = true;
		}
		else if(key == this.DownKey) {
			this.y+=32;
			flag = true;
		}else if(key == this.LeftKey) {
			this.x-=32;
			flag = true;
		}else if(key == this.RightKey) {
			this.x+=32;
			flag = true;
		}else if(key == this.BreakableBlockKey) {
			BreakableBlock b = new BreakableBlock();
			b.x = x;
			b.y = y;
			this.gp.blocks.add(b);
			flag = true;
		}
		else if(key == this.NonbreakableBlockKey) {
			NonbreakableBlock b = new NonbreakableBlock();
			b.x = x;
			b.y = y;
			this.gp.blocks.add(b);
			flag = true;
		}
		this.fence(ox, oy, ow, oh);
		return flag;
		
	}
	protected void fence(int ox,int oy,int ow,int oh) {
		if(this.x-16<=ox) {
			this.x = ox+16;
		}
		if(this.y-16<=oy) {
			this.y = oy+16;
		}
		if(this.x+16>=ox+ow) {
			this.x = ox+ow -16;
		}
		if(this.y+16>=oy+oh) {
			this.y = oy+oh -16;
		}
	}
}
