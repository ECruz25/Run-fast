package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Explode extends Character{

	public Explode() {

		super(loadImages());
	
	}

	static ArrayList<Image> loadImages(){
		
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(new Image(new Texture("perder01.png")));
		images.add(new Image(new Texture("perder02.png")));
		images.add(new Image(new Texture("perder03.png")));
		images.add(new Image(new Texture("perder04.png")));
		images.add(new Image(new Texture("perder05.png")));
		
		return images;
		
	}
	
	@Override
	public void act(float delta) {
		
		super.act(delta);
	
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {

		super.draw(batch, parentAlpha);

	}
	
}
