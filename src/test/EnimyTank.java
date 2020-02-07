package test;

import java.awt.Color;
import java.util.List;
import java.util.Random;

public class EnimyTank extends Tank {
	public int steps = 15;
	
	public Random rand = new Random();
	
	public EnimyTank() {
		this.speed = 1;
		this.c=new Color(255,0,0);
	}
	
	public void advance(int ox, int oy, int ow, int oh,List<Block> blocks) {
		super.advance(ox, oy, ow, oh,blocks);
		if (steps == 0) {
			steps = 15;
			this.d = this.rand.nextInt(4);
			this.fire();
		}else {
			steps --;
		}
		switch(d) {
		case 0:
			this.y-=this.speed;
			break;
		case 1:
			this.x+=this.speed;
			break;
		case 2:
			this.y+=this.speed;
			break;
		case 3:
			this.x-=this.speed;
			break;
		}
		this.fence(ox, oy, ow, oh,blocks);
		
	}
	
}
