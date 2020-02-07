package test;

import java.awt.Rectangle;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	public MainFrame()
	{
		this.setBounds(new Rectangle(100,100,1600,1200));
		GamePanel p = new GamePanel();
		this.addKeyListener(p);
		this.getContentPane().add(p);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
