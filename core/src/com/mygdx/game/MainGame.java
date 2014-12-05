package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MainGame extends Game implements InputProcessor, ApplicationListener{
	
	int frame;
	int iteraciones;
	
	public static int score;
	
	Stage stage;
	Stage menu;
	Stage gameOver;
	Stage explosion;
	
	Music music;
	
	Player player;
	
	Bomb bomb;
	
	Boolean restart;
	Boolean collisionn;
	
	Floor floor;
	
	Rectangle playerR, bombR;
	
	@Override
	public void create () {
		
//		setScreen(new Menu());
		
		Gdx.input.setInputProcessor(this);
		
		stage = new Stage();
		menu = new Stage();
		gameOver = new Stage();
		explosion = new Stage();
		
		frame = 0;
		iteraciones = 0;
		score = 0;
		
		music = Gdx.audio.newMusic(Gdx.files.internal("fight.wav"));
		
		restart = false;
		collisionn = false;
		
		player = new Player();
		
		bomb = new Bomb(player);
		
		bomb.setX(Bomb.X);
		bomb.setY(Bomb.Y);
		
		floor = new Floor(0);
		
		stage.addActor(bomb);
		stage.addActor(new Enemy());
		stage.addActor(floor);
		stage.addActor(player);
		
		menu.addActor(new Image(new Texture("start_Button.png")));
		menu.addActor(new Image(new Texture("Quit_Button.png")));
		menu.addActor(new Image(new Texture("HowTo_Button.png")));
		
		Image gameover = new Image(new Texture("gameover.png"));
		gameover.addListener(new InputListener(){ //volver a jugar
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				
				gameOver.dispose();
				
				restart = true;
				
				Gdx.input.setInputProcessor(gameOver);
				stage.act();
				stage.draw();
				return true;
				
			}
			
		});
		
		menu.addListener(new InputListener(){
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				
				menu.dispose();
				
				Gdx.input.setInputProcessor(menu);
				
				stage.act();
				stage.draw();
				
				return true;
				
			}
			
		});
			
		
		gameOver.addActor(gameover);
		
		bombR = new Rectangle(bomb.getX()+bomb.getWidth()/4,
				bomb.getY()+bomb.getHeight()/4, 
				bomb.getWidth()/2,
				bomb.getHeight()/2);
		
		playerR = new Rectangle(player.getX() + player.getWidth()/4, 
				player.getY() + player.getHeight()/4,
				player.getWidth()/2,
				player.getHeight()/2);
		
	}
	
	public boolean startMenu(){
		
		menu.addActor(new Image(new Texture("main_menu.png")));
		
		return true;
		
	}
	
	public boolean pauseMenu(){
		
		menu.addActor(new Image(new Texture("pause_menu.png")));
		
		return true;
		
	}
	
	public void createFloor(){
		
		float x = floor.getX();
		
		if (x<300){
		
			floor = new Floor(x+535);
			
			stage.addActor(floor);
		
		}
			
	}
	
	boolean collision(){
		
		bombR.set(bomb.getX(), bomb.getY(), 100, 100);
		playerR.set(player.getX(), player.getY(), 100,100);

		collisionn = true;
		
		return bombR.overlaps(playerR);
		
	}

	@Override
	public void render () {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(collision()){
			
			Gdx.input.setInputProcessor(gameOver);
			
			gameOver.draw();
			gameOver.act();
			
			
			Gdx.input.setInputProcessor(gameOver);
			
			gameOver.draw();
			gameOver.act();
			
			if(restart){
				
				create();
				
				Gdx.input.setInputProcessor(this);

				stage.act();
				stage.draw();
				
//				menu.draw();
//				menu.act();
				
				createFloor();
				
				if(collision()){
					
					Gdx.input.setInputProcessor(gameOver);
					
					gameOver.draw();
					gameOver.act();
					
				}
				
			}
			
			iteraciones++;
			
//			System.out.println("Score: " + Player.score + " + " + iteraciones);
			
		}
		
		else {
			
			stage.act();
			stage.draw();
		
//			menu.draw();
//			menu.act();
			
			createFloor();
			
		}
		
	}


	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {

		if(character == 'p'){
			
			pauseMenu();
			
			return true;
			
		}
		
		if(character == 'm'){
			
			menu.clear();
			
			return false;
			
		}
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		player.jump();

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
