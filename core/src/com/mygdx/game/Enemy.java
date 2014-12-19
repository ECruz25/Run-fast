package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Enemy extends Character{

	public Enemy() {

		super(loadImages());
		
		setX(1150);
		setY(500);
		
		this.accelerationY = 0;
	
		name = "";
		
	}
	
	static ArrayList<Image> loadImages(){
		
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(new Image(new Texture("volador01.png")));
		images.add(new Image(new Texture("volador02.png")));
		images.add(new Image(new Texture("volador03.png")));
		
		return images;
		
	}
	
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {

		super.draw(batch, parentAlpha);
		
		images.get(actualImage).setPosition(getX(), getY());
		
		images.get(actualImage).draw(batch, parentAlpha);
	
	}

}
