package display;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Charactor.*;
import Element.Element;
import event.Event;

public class Game extends JPanel implements KeyListener{

	private static final long serialVersionUID = 1L;
	private static int speed = 50,CatSize = 70 ,waveHeight = 20;
	private static int base=400,xStart = 1000;
	private long point = 0,lastPress=0;
	
	private Cat Cat = new Cat(100,base-23);
	static Display display;
//	------------------Wave SIze ----------------------------
	private Wave[] waveSet = makeWave(4);
//--------------------Cloud--------------------------------
	//private Environment[] envSet = makeEnv(2,Environment.CLOUD);
	private Environment building = new Environment(xStart-100,base-160,this,Environment.BUILDING,4);
	
		public Game(){
		this.setBounds(0,0,1000,600);
		this.addKeyListener(this);
		this.setLayout(null);
		this.setFocusable(true);
	}
	
	@Override
	public void paint(Graphics g) {
			try {
				super.paint(g);
				Graphics2D g2 = (Graphics2D) g;
				this.drawBackground(g2);
				//---POINT----
				g2.setFont(Element.getFont(30));
				g2.setColor(Color.white);
				g2.drawString("Point : "+point,750,40);
				//--- Cat --
				g2.setColor(Color.RED);
				drawCatHealth(g2);
				g2.drawImage(Cat.getImage(),Cat.x,Cat.y,CatSize,CatSize, null);
				//----Wave----
				for(Wave item : waveSet) {
					drawWave(item,g2);
				}
				this.point+=1;
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	private void drawBackground(Graphics2D g2) throws IOException {
			g2.drawImage(ImageIO.read(new File("img\\hell.png")),0,0,2000,1000, null);
			g2.drawImage(building.getImage(),building.x,building.y,500,200,null);
			g2.drawImage(ImageIO.read(new File("img\\lava.png")),-100,base-10,2000,220, null);
	}
	
	private void drawCatHealth(Graphics2D g2) {
		try {
			g2.drawImage(ImageIO.read(new File("img\\heart.png")),10,20, 20,20,null);
			g2.setStroke(new BasicStroke(18.0f));
			g2.setColor(new Color(241, 98, 69));
			g2.drawLine(60, 30,60+Cat.health,30);	
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(6.0f));
			g2.drawRect(50,20, 200,20);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Wave[] makeWave(int size) {
		Wave[] waveSet = new Wave[size];
		int far = 500;
		for(int i=0;i<size;i++) {
			waveSet[i] = new Wave(xStart+far,base,speed,this);
			far+=500;
		}
		return waveSet;
	}
	
	private void drawWave(Wave wave,Graphics2D g2) {
			g2.drawImage(wave.getImage(),wave.x ,(wave.y-waveHeight),40,waveHeight+38,null);
			if(Event.checkHit(Cat,wave,CatSize,waveHeight)){
					g2.setColor(new Color(241, 98, 69));
					g2.fillRect(0, 0,1000,1000);			
					Cat.health-=20 ;
					if(Cat.health<=0) {
						display.endGame(this.point);
						Cat.health = new Cat().health;
						this.point = 0;	
					}
			}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(System.currentTimeMillis() - lastPress > 600) {
			if(e.getKeyCode()==32||e.getKeyCode()==38) {
					Cat.jump(this);
					lastPress = System.currentTimeMillis();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//---
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//---
	}
	
	public static void main(String[] arg) {
		//display = new play();
		display = new Display();
	}
}
