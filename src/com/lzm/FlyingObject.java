package com.lzm;

import java.awt.image.BufferedImage;

public abstract class FlyingObject {
	protected int Width;
	protected int Height;
	protected int x;
	protected int y;
	protected BufferedImage image;
	public abstract void step();
	public abstract boolean outOfBounds();
}
