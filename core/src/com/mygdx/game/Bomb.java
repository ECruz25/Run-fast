package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Bomb extends Character{

	Character character;
	
	public static final int X = 1150;
	
	public static final int Y = 450; 
	
	Math math;
	
	public Bomb(Character character) {

		super(loadImages());

		this.character = character;
		
		setX(X);
		setY(Y);
		
		speedY=-100;
		
		this.name = "";
		
	}
	
	static ArrayList<Image> loadImages(){
		
		ArrayList<Image>images = new ArrayList<Image>();
		
		images.add(new Image(new Texture("bomba01.png")));
	    images.add(new Image(new Texture("bomba02.png")));
	    images.add(new Image(new Texture("bomba03.png")));
		
		return images;
		
	}
	
	@Override
	public void act(float delta) {

		super.act(delta);
		
		if(this.getY() < 500){

			this.setY(this.getY()-(17 + Math.round((Player.score)/2)));
			this.setX(this.getX()-(12 + Math.round((Player.score)/2)));
			
			System.out.println(Player.score);
			
			if(this.getY() == 100){
				
				if(this.getX() < 200){
		        	
					this.setX(1300);
					this.setY(450);
			        	
				}
				
			}	
			
		}
		
		this.setY(this.getY()-5);

		this.setX(this.getX()-5);
		
		if(this.getX() < -120){
			
			this.setX(1000);
			this.setY(350);
			
		}
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		super.draw(batch, parentAlpha);
		
		images.get(actualImage).draw(batch,parentAlpha);
		
		images.get(actualImage).setX(this.getX()-1);
		images.get(actualImage).setY(this.getY()-1);
		
	}

}
