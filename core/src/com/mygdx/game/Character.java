package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Character extends Actor{

	ArrayList<Image> images;
	
	int actualImage;
	int floor;
	int jumpImage;
	
	float speedX;
	float speedY;
	float accelerationX;
	float accelerationY;
	float frame;
	float animationSpeed;
	
	String name;
	
	public Character(ArrayList<Image> images){
		
		super();
		
		this.images = images;
		speedX = 0;
		speedY = 0;
		accelerationX = 0;
		accelerationY = 0;
		frame = 0;
		animationSpeed = 0.1f;
		floor = 70;
		jumpImage = 0;
		
		setWidth(images.get(0).getWidth());
		setHeight(images.get(0).getHeight());
		
	}
	
	@Override
	public void act(float delta) {
		
		super.act(delta);
		
		frame += delta;
		
		if(frame>animationSpeed){
			
			actualImage++;
			
			if(actualImage+1 == images.size()){
								
				actualImage = 0;
				
			}
			
			frame = 0;
			
		}
		
		speedX += accelerationX;
		speedY += accelerationY;
		
		moveBy(speedX * delta,speedY*delta);
		
		if(getY()<=floor){
			
			setY(floor);
			
		}
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {

		super.draw(batch, parentAlpha);

		images.get(actualImage).setPosition(getX(), getY());
		
//		images.get(actualImage).draw(batch, parentAlpha);
				
	}
	
}
