package com.lzm;

public class Bullet extends FlyingObject{

	private int speed = 3;
	public Bullet(int x,int y){
		image = Game.bullet;
		Width = image.getWidth();
		Height = image.getHeight();
		this.x = x;
		this.y = y;
	}
	public void step(){
		y -= speed;
	}
	public boolean outOfBounds(){
		return this.y<-this.Height;
	}
}
