package com.lzm;

import java.util.Random;

public class Airplane extends FlyingObject implements Enemy{

	private int score;
	private int speed = 2;
	public Airplane(){
		image = Game.airplane;
		Width = image.getWidth();
		Height = image.getHeight();
		Random rand = new Random();
		x = rand.nextInt(Game.WIDTH-this.Width);
		y = -this.Height;
		
	}
	public int getScore(){
		return score+=5;
	}
	public void step(){
		y += speed;
	}
	public boolean outOfBounds(){
		return this.y>=Game.HEIGHT;
	}
}
