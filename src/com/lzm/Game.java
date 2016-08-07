package com.lzm;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel{

	public static final int WIDTH = 400;
	public static final int HEIGHT = 654;
	public static BufferedImage airplane;
	public static BufferedImage background;
	public static BufferedImage bee;
	public static BufferedImage bullet;
	public static BufferedImage gameover;
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	public static BufferedImage hero2;
	public static BufferedImage pause;
	public static BufferedImage start;
	static{
		try{
			airplane = ImageIO.read(Game.class.getResource("airplane.png"));
			background = ImageIO.read(Game.class.getResource("background.png"));
			bee = ImageIO.read(Game.class.getResource("bee.png"));
			bullet = ImageIO.read(Game.class.getResource("bullet.png"));
			gameover = ImageIO.read(Game.class.getResource("gameover.png"));
			hero0 = ImageIO.read(Game.class.getResource("hero0.png"));
			hero1 = ImageIO.read(Game.class.getResource("hero1.png"));
			hero2 = ImageIO.read(Game.class.getResource("hero2.png"));
			pause = ImageIO.read(Game.class.getResource("pause.png"));
			start = ImageIO.read(Game.class.getResource("start.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	Hero hero = new Hero();
	FlyingObject[] flyings = new FlyingObject[]{};
	Bullet[] bullets = new Bullet[]{};
	
	
	public void paint(Graphics g){
		g.drawImage(background,0,0,null);
		paintHero(g);
		paintFlyingObject(g);
		paintBullet(g);
	}
	
	private void paintBullet(Graphics g) {
		for(int i=0;i<bullets.length;i++){
			Bullet b = bullets[i];
			g.drawImage(b.image, b.x, b.y, null);
		}
	}

	private void paintFlyingObject(Graphics g) {
		for(int i=0;i<flyings.length;i++){
			FlyingObject f = flyings[i];
			g.drawImage(f.image, f.x, f.y, null);
		}
	}

	private void paintHero(Graphics g) {
		g.drawImage(hero.image, hero.x, hero.y, null);
	}
	
	public void action(){
		MouseAdapter m = new MouseAdapter(){
			public void mouseMoved(MouseEvent e){
				int x = e.getX();
				int y = e.getY();
				hero.move(x,y);
			}
		};
		this.addMouseListener(m);
		this.addMouseMotionListener(m);
		int interlevel = 10;
		Timer time = new Timer();
		time.schedule(new TimerTask(){
			public void run(){
				interAction();
				stepAction();
				shootAction();
				outOfBoundAction();
				bangAction();
				repaint();
			}
		}, interlevel,interlevel);
	}
	protected void bangAction() {
		
	}

	protected void outOfBoundAction() {
		int index = 0;
		FlyingObject[] flyingLives = new FlyingObject[flyings.length];
		for(int i=0;i<flyings.length;i++){
			FlyingObject f = flyings[i];
			if(!f.outOfBounds()){
				flyingLives[index] = f;
				index++;
			}
		}
		flyings = Arrays.copyOf(flyingLives, index);
		index = 0;
		Bullet[] bulletLives = new Bullet[bullets.length];
		for(int i=0;i<bullets.length;i++){
			Bullet b = bullets[i];
			if(!b.outOfBounds()){
				bulletLives[index] = b;
				index++;
			}
		}
		bullets = Arrays.copyOf(bulletLives, index);
	}
	int indexShoot = 0;
	protected void shootAction() {

		indexShoot++;
		if(indexShoot%40==0){
			Bullet[] bs = hero.shoot();
			bullets = Arrays.copyOf(bullets, bullets.length+bs.length);
			System.arraycopy(bs, 0, bullets, bullets.length-bs.length, bs.length);
		}
	}

	protected void stepAction() {
		hero.step();
		for(int i=0;i<flyings.length;i++){
			flyings[i].step();
		}
		for(int i=0;i<bullets.length;i++){
			bullets[i].step();
		}
		
	}

	public FlyingObject nextOne(){
		Random rand = new Random();
		int type = rand.nextInt(20);
		if(type<8){
			return new Bee();
		}
		return new Airplane();
	}
	int indexInter = 0;
	protected void interAction() {
		indexInter++;
		if(indexInter%30==0){
			FlyingObject f = nextOne();
			flyings = Arrays.copyOf(flyings, flyings.length+1);
			flyings[flyings.length-1] = f;
		}
		
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("LZM");
		Game game = new Game();
		frame.add(game);
		frame.setAlwaysOnTop(true);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.action();
	}

}
