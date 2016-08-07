package com.lzm;

import java.util.Random;

public class Bee extends FlyingObject{

	private int xSpeed = 1;
	private int ySpeed = 2;
	private int awardType;
	public Bee(){
		image = Game.bee;
		Width = image.getWidth();
		Height = image.getHeight();
		Random rand = new Random();
		awardType = rand.nextInt(2);
		x = rand.nextInt(Game.WIDTH-this.Width);
		y = -this.Height;
		
	}
	public int getAward(){
		return awardType;
	}
	public void step(){
		x += xSpeed;
		y += ySpeed;
		if(x>=Game.WIDTH-this.Width){
			xSpeed = -1;
		}
		if(x<=0){
			xSpeed = 1;
		}
	}
	public boolean outOfBounds(){
		return this.y>=Game.HEIGHT;
	}
}
