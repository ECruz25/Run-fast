package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Floor extends Image{

	int speed = 500;
	
	public Floor(float x) {
		
		super(new Texture("tierra.png"));
		
		this.setX(x);
		this.setY(-220);
		
	}
	
	@Override
	public void act(float delta) {
	
		super.act(delta);
	
		this.setX(this.getX() - delta * speed);
		
	}
	
}
