package com.mygdx.game;


import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MenuSprite extends Character{

	float gravity;
	
	int jump1;
	
	public static int score;
	
	public MenuSprite() {
		
		super(loadImages());
		
		score = 0;
		
		gravity = 400f;
		
		setX(1100);
		
		this.name = "player";
		
		this.floor-=20;
	
	}
	
	static ArrayList<Image> loadImages(){
		
		ArrayList<Image>images = new ArrayList<Image>();
		
		images.add(new Image(new Texture("run01.png")));
        images.add(new Image(new Texture("run02.png")));
        images.add(new Image(new Texture("run03.png")));
        images.add(new Image(new Texture("run04.png")));
        
		return images;
		
	}
	
	@Override
	public void act(float delta) {
		
		super.act(delta);		
		
		accelerationY -= gravity*delta;
		
		if(getY() <= floor){
			
			speedY = 0;
			
			accelerationY = 0;
			
		}
		
	}
	
	public void jump(){
				
		if(getY() == floor ){
			
			this.jumpImage = 1;

			accelerationY = 95f;
			
			score++;
			
		}
		
		else if(getY() == floor*2){
			
			this.jumpImage = 1;

			accelerationY = 95f;
			
		}
		
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
				
		super.draw(batch, parentAlpha);
		
		if(getY() > floor){
			
			images.get(images.size()-1).setPosition(getX(), getY());
			
			images.get(images.size()-1).draw(batch, parentAlpha);
			
		}
		
		else if(getY() == floor){

			images.get(actualImage).setPosition(getX(), getY());
			
			images.get(actualImage).draw(batch, parentAlpha);
			
		}
		
	}
	
}