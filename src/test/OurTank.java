package test;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class OurTank extends Tank {
	
	public int UpKey = 0;//KeyEvent.VK_W;
	public int DownKey =0;
	public int LeftKey =0;
	public int RightKey =0;
	public int FireKey =0;
	
	public int Score = 0;
	
	public OurTank()
	{
		this.speed = 3;
	}
	
	public List<Tank> OnDestoryEnemies(List<Tank> tanks){
		List<Tank> deads = new ArrayList<Tank>();
		for(Tank e : tanks) {
			if(e.getHits(this.bullets)) {
				this.Score +=5;
				deads.add(e);
			}
		}
		return deads;
	}
	
	public boolean keyPressed(KeyEvent e, int ox, int oy, int ow, int oh,List<Block> blocks) {
		boolean flag = false;
		int key = e.getKeyCode();
		
		if(key == this.UpKey) {
			this.d = 0;
			this.y-=this.speed;
			flag = true;
		}
		else if(key == this.DownKey) {
			this.d = 2;
			this.y+=this.speed;
			flag = true;
		}else if(key == this.LeftKey) {
			this.d = 3;
			this.x-=this.speed;
			flag = true;
		}else if(key == this.RightKey) {
			this.d = 1;
			this.x+=this.speed;
			flag = true;
		}else if(key == this.FireKey) {
			this.fire();
			flag = true;
		}
		
		this.fence(ox, oy, ow, oh,blocks);
		return flag;
		
	}
}
