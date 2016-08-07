package com.lzm;

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject{
	private int speed = 2;
	private int life = 3;
	private int doubleFire = 10;
	private BufferedImage[] images;
	private int index;
	public Hero(){
		image = Game.hero0;
		Width = image.getWidth();
		Height = image.getHeight();
		x = 150;
		y = 300;
		images = new BufferedImage[]{Game.hero0,Game.hero1};
		index = 0;
	}
	public void step(){
		image = images[index++/10%images.length];
	}
	public Bullet[] shoot(){
		int xStep = this.Width/4;
		int yStep = 20;
		if(doubleFire>0){
			Bullet[] bs = new Bullet[2];
			bs[0] = new Bullet(this.x+1*xStep,this.y-yStep);
			bs[1] = new Bullet(this.x+3*xStep,this.y-yStep);
			doubleFire -= 2;
			return bs;
		}else{
			Bullet[] bs = new Bullet[1];
			bs[0] = new Bullet(this.x+2*xStep,this.y-yStep);
			return bs;
		}
	}
	public boolean outOfBounds(){
		return false;
	}
	public void move(int x,int y){
		this.x = x-this.Width/2;
		this.y = y-this.Height/2;
	}
}
