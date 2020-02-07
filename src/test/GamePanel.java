package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;

	public OurTank AlmightEagle = new OurTank();
	public OurTank RebornHero = new OurTank();
	public Timer timer = null;
	public GameTimerTask task = null;
	
	public List<Tank> enimies = new ArrayList<Tank>();
	public List<Tank> ourselves = new ArrayList<Tank>();
	
	public boolean stopped = false;
	
	public boolean designing = true;
	
	public Designer designer = null;
	
	public class GameTimerTask extends TimerTask
	{
		GamePanel g = null;
		public GameTimerTask(GamePanel g) {
			this.g = g;
		}
		@Override
		public void run() {
			if(!g.designing) {
				this.g.advance();
			}
		}
		
	}
	
	public int TotalEnimiesCount = 1;


	
	public List<Block> blocks = new ArrayList<Block>();
	public GamePanel()
	{
		this.designer = new Designer(this);
		this.designer.UpKey = KeyEvent.VK_UP;
		this.designer.DownKey = KeyEvent.VK_DOWN;
		this.designer.LeftKey = KeyEvent.VK_LEFT;
		this.designer.RightKey = KeyEvent.VK_RIGHT;
		this.designer.BreakableBlockKey = KeyEvent.VK_B;
		this.designer.NonbreakableBlockKey=KeyEvent.VK_N;
		
		
		this.AlmightEagle.x = 100;
		this.AlmightEagle.y = 900;
		this.RebornHero.x = 1550;
		this.RebornHero.y = 900;
		
		this.AlmightEagle.UpKey = KeyEvent.VK_W;
		this.AlmightEagle.DownKey = KeyEvent.VK_S;
		this.AlmightEagle.LeftKey = KeyEvent.VK_A;
		this.AlmightEagle.RightKey = KeyEvent.VK_D;
		this.AlmightEagle.FireKey = KeyEvent.VK_Q;
		
		this.RebornHero.UpKey = KeyEvent.VK_I;
		this.RebornHero.DownKey = KeyEvent.VK_K;
		this.RebornHero.LeftKey = KeyEvent.VK_J;
		this.RebornHero.RightKey = KeyEvent.VK_L;
		this.RebornHero.FireKey = KeyEvent.VK_U;
		this.ourselves.add(this.AlmightEagle);
		this.ourselves.add(this.RebornHero);
		
		this.timer = new Timer();
		
		this.task = new GameTimerTask(this);
		this.timer.schedule(task, 1000, 30);

		
	}

	private boolean gen=true;
	
	public void advance() {
		int ox =0;
		int oy =0;
		int ow = this.getWidth();
		int oh = this.getHeight();

		
		this.AlmightEagle.advance(ox,oy,ow,oh,this.blocks);
		this.RebornHero.advance(ox,oy,ow,oh,this.blocks);
		
		if(gen && this.enimies.size() <TotalEnimiesCount) {
			gen =false;
			for(int i =0;i<TotalEnimiesCount;i++) {
				EnimyTank e = new EnimyTank();
				e.x = this.getWidth()/2;
				e.y = this.getHeight()/2;
				
				this.enimies.add(e);
			}
		}
		for(Tank e : this.enimies)
		{
			e.advance(ox, oy, ow, oh,this.blocks);
		}
		this.enimies.removeAll(this.AlmightEagle.OnDestoryEnemies(this.enimies));
		this.enimies.removeAll(this.RebornHero.OnDestoryEnemies(this.enimies));

		List<Tank> tks = new ArrayList<Tank>();
		tks.addAll(this.enimies);
		tks.addAll(this.ourselves);
		List<Block> rbs = new ArrayList<Block>();
		for(Block b : this.blocks) {
			for(Tank t : tks) {
				List<Bullet> tbs = new ArrayList<Bullet>();
				for(Bullet u : t.bullets) {
					if(b.hit(u)) {
						if(b instanceof BreakableBlock) {
							rbs.add(b);
							tbs.add(u);
						}else if(b instanceof NonbreakableBlock) {
							tbs.add(u);
						}
					}
				}
				t.bullets.removeAll(tbs);
			}
		}
		this.blocks.removeAll(rbs);

		
		List<Tank> ourdead = new ArrayList<Tank>();
		
		for(Tank t : this.ourselves) {
			if(t.hitsFrom(this.enimies)) {
				ourdead.add(t);
			}
		}
		
		this.ourselves.removeAll(ourdead);
		
		if(this.ourselves.size()==0) {
			//game over!
			this.stopped = true;
			this.enimies.clear();
		}
		if(this.enimies.size()==0) {
			this.TotalEnimiesCount ++;
			gen = true;
		}
		this.repaint();
	}
	
	public Color blackColor = new Color(0,0,0);
	public void paint(Graphics g) {
		if(g!=null) {
			if(this.designing) {
				this.designer.draw(g);
			}
			for(Block b:this.blocks) {
				b.draw(g);
			}
			g.setColor(this.blackColor);
			g.drawString("A:"+this.AlmightEagle.Score, 10, 10);
			g.drawString("B:"+this.RebornHero.Score, 10, 32);
			if(!this.stopped) {
				for(Tank t : this.ourselves) {
					t.drawTank(g);
				}
				for(Tank e : this.enimies) {
					e.drawTank(g);
				}
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		boolean f = false;
		if(this.designing) {
			f = this.designer.keyPressed(e, 0, 0, this.getWidth(), this.getHeight());
			if(e.getKeyCode()==KeyEvent.VK_S) {
				this.designing = false;
				f = true;
			}
		}
		else {
			for(Tank t : this.ourselves)
			{;
				OurTank o = (OurTank)t;
				f|= o.keyPressed(e, 0, 0, this.getWidth(), this.getHeight(),blocks);
			}
		}
		if(f) {
			this.repaint();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
}
