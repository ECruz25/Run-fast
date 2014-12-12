package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Title extends Image{

	public Title(Texture texture) {
		
		super(texture);
		
		this.setX(0);
		
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		this.setY(570);

        this.setX(this.getX()+5);
		
		if(this.getX() >= 1350){
			
			this.setX(-850);
			
		}
        
	}
	
}
