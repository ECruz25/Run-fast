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
	Stage howTo;
	Stage levels;
	Stage trainingLvl;
	Stage hardcoreLvl;
	
	Image startButton;
	Image quitButton;
	Image howToButton;
	Image backButton;
	Image howToScreen;
	Image menuScreen;
	Image background;
	Image background2;
	Image endlessButton;
	Image trainingButton;
	Image normalButton;
	Image gameover;
	
	Music music;
	Music jumpSound;
	
	Player player;
	Player player2;
	Player player3;
	
	Training training;
	
	Hardcore hardcore;
	
	Bomb bomb;
	
	Title title;
	
	Boolean restart;
	Boolean collisionn;
	Boolean start;
	Boolean howto;
	Boolean quit;
	Boolean backB;
	Boolean trainingB;
	Boolean endlessB;
	Boolean normalB;
	
	Floor floor;
	Floor floor2;
	Floor floor3;
	
	Rectangle playerR;
	Rectangle bombR;
	Rectangle playerR2;
	Rectangle bombR2;
	Rectangle playerR3;
	Rectangle bombR3;
	
	@Override
	public void create () {
		
//		setScreen(new Menu());
		
		Gdx.input.setInputProcessor(this);
		
		title = new Title(new Texture("title.png"));
		
		stage = new Stage();
		menu = new Stage();
		gameOver = new Stage();
		howTo = new Stage();
		levels = new Stage();
		trainingLvl = new Stage();
		hardcoreLvl = new Stage();
		
		frame = 0;
		iteraciones = 0;
		score = 0;
		
		title.setBounds(0, 750, 880, 90);
		
		music = Gdx.audio.newMusic(Gdx.files.internal("fight.wav"));
		jumpSound = Gdx.audio.newMusic(Gdx.files.internal("jump.ogg"));
		
		restart = false;
		collisionn = false;
		start = false;
		howto = false;
		quit = false;
		backB = false;
		endlessB = false;
		trainingB = false;
		normalB = false;
		
		player = new Player();
		player2 = new Player();
		player3 = new Player();

		bomb = new Bomb(player);
		
		training = new Training(player2);
		
		hardcore = new Hardcore(player3);
		
		bomb.setX(Bomb.X);
		bomb.setY(Bomb.Y);
		
		floor = new Floor(0);
		floor2 = new Floor(0);
		floor3 = new Floor(0);
		
		startButton = new Image(new Texture("start_Button.png"));
		quitButton = new Image(new Texture("Quit_Button.png"));
		howToButton = new Image(new Texture("HowTo_Button.png"));
		howToScreen = new Image(new Texture("howto_Screen.png"));
		backButton = new Image(new Texture("backButton.png"));
		background = new Image(new Texture("dark.png"));
		background2 = new Image(new Texture("dark.png"));
		endlessButton = new Image(new Texture("endlessButton.png"));
		trainingButton = new Image(new Texture("trainingButton.png"));
		normalButton = new Image(new Texture("normalButton.png"));
		
		startButton.setBounds(200, 300, 250, 100);
		quitButton.setBounds(200, 50, 250, 100);
		howToButton.setBounds(800, 300, 250, 100);
		backButton.setBounds(900, 50, 200, 100);
		normalButton.setBounds(500, 500, 400, 100);
		trainingButton.setBounds(500, 300, 400, 100);
		endlessButton.setBounds(500, 100, 400, 100);

		menu.addActor(background);
		menu.addActor(startButton);
		menu.addActor(quitButton);
		menu.addActor(howToButton);
		menu.addActor(new Title(new Texture("title.png")));
		menu.addActor(new MenuSprite());
		
		howTo.addActor(howToScreen);
		howTo.addActor(backButton);

		stage.addActor(bomb);
		stage.addActor(new Enemy());
		stage.addActor(floor);
		stage.addActor(player);
		
		trainingLvl.addActor(training);
		trainingLvl.addActor(new Enemy());
		trainingLvl.addActor(floor2);
		trainingLvl.addActor(player2);
		
		hardcoreLvl.addActor(hardcore);
		hardcoreLvl.addActor(new Enemy());
		hardcoreLvl.addActor(floor3);
		hardcoreLvl.addActor(player3);
		
		levels.addActor(background2);
		levels.addActor(endlessButton);
		levels.addActor(trainingButton);
		levels.addActor(normalButton);
		
		gameover = new Image(new Texture("gameover.png"));
		
		gameOver.addActor(gameover);
		
		bombR = new Rectangle(bomb.getX()+bomb.getWidth()/4,
				bomb.getY()+bomb.getHeight()/4, 
				bomb.getWidth()/2,
				bomb.getHeight()/2);
		
		playerR = new Rectangle(player.getX() + player.getWidth()/4, 
				player.getY() + player.getHeight()/4,
				player.getWidth()/2,
				player.getHeight()/2);
		
		bombR2 = new Rectangle(training.getX()+training.getWidth()/4,
				training.getY()+training.getHeight()/4, 
				training.getWidth()/2,
				training.getHeight()/2);
		
		playerR2 = new Rectangle(player2.getX() + player2.getWidth()/4, 
				player2.getY() + player2.getHeight()/4,
				player2.getWidth()/2,
				player2.getHeight()/2);

		bombR3 = new Rectangle(hardcore.getX()+hardcore.getWidth()/4,
				hardcore.getY()+hardcore.getHeight()/4, 
				hardcore.getWidth()/2,
				hardcore.getHeight()/2);
		
		playerR3 = new Rectangle(player3.getX() + player3.getWidth()/4, 
				player3.getY() + player3.getHeight()/4,
				player3.getWidth()/2,
				player3.getHeight()/2);
		
		
		gameover.addListener(new InputListener(){ //volver a jugar
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				
				gameOver.dispose();
				
				restart = true;
				
				System.out.println("gameover Button pressed");
				
				Gdx.input.setInputProcessor(gameOver);
				menu.act();
				menu.draw();
				return true;
				
			}
			
		});
		
		startButton.addListener(new InputListener(){
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				
				menu.dispose();
				
				Gdx.input.setInputProcessor(menu);
				
				System.out.println("Going to levels");
				
				start = true;
				
				return true;
				
			}
			
		});
		
		howToButton.addListener(new InputListener(){
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				
				System.out.println("Going to How To Play");
				
				System.out.println("1" + howto);
				
				howto = true;
				
				System.out.println("2 " + howto);
				
				Gdx.input.setInputProcessor(menu);
				
				return true;
				
			}
			
		});
		
		backButton.addListener(new InputListener(){
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				
				howTo.dispose();
				
				System.out.println("going back");
				
				backB = true;
				howto = false;
				
				System.out.println("how to: " + howto);
				
				Gdx.input.setInputProcessor(howTo);
				
				return true;
				
			}
			
		});
		
		quitButton.addListener(new InputListener(){
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				
				levels.dispose();
				
				System.exit(0);
				
				return true;
				
			}
			
		});
		
		trainingButton.addListener(new InputListener(){
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				
				levels.dispose();
				
				Gdx.input.setInputProcessor(trainingLvl);
				
				System.out.println("Going to training level");
				
				trainingB = true;
				
				return true;
				
			}
			
		});
		
		endlessButton.addListener(new InputListener(){
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				
				levels.dispose();
				
				System.out.println("Going to endless level");
				
				endlessB = true;
				
				return true;
			}
			
		});
		
		normalButton.addListener(new InputListener(){
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				
				levels.dispose();
				
				Gdx.input.setInputProcessor(hardcoreLvl);
				
				System.out.println("Going to training level");
				
				normalB = true;
				
				return true;
			}
			
		});
		
		music.play();
		music.setVolume(0.05f);
		
		
		
	}
	@Override
	public void render () {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(collision()){
			
			music.stop();
			
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
				
				createFloor();
				
				if(collision()){
					
					Gdx.input.setInputProcessor(gameOver);
					
					gameOver.draw();
					gameOver.act();
					
				}
				
			}
			
			iteraciones++;
			
		}
		
		else if(howto){
			
			howTo.draw();
			howTo.act();
			
			Gdx.input.setInputProcessor(howTo);
			
			if(backB){
				
				Gdx.input.setInputProcessor(menu);
				
				menu.draw();
				menu.act();
				
				howto=false;
				
			}
			
		}
		
		else {
			
			Gdx.input.setInputProcessor(menu);
		
			menu.draw();
			menu.act();
			
			if(start){
				
				Gdx.input.setInputProcessor(levels);
				
				levels.draw();
				levels.act();
				
				if(endlessB){
					
					Gdx.input.setInputProcessor(this);

					stage.act();
					stage.draw();
					
//					music.play();
					
				}
				
				else if(trainingB){
					
					Gdx.input.setInputProcessor(this);

					trainingLvl.act();
					trainingLvl.draw();
					
				}
				
				else if(normalB){
					
					Gdx.input.setInputProcessor(this);

					hardcoreLvl.act();
					hardcoreLvl.draw();
					
				}
				
			}
			
			createFloor();
			
		}
		
	}
	
	public void createFloor(){
		
		float x = floor.getX();
		float y = floor2.getX();
		float z = floor3.getX();
		
		if (x<300){
		
			floor = new Floor(x+535);
			
			stage.addActor(floor);
			
		}
		
		if(y<300){

			floor2 = new Floor(y+535);
			
			trainingLvl.addActor(floor2);
		
		}
		
		if(z<300){

			floor3 = new Floor(z+535);
			
			hardcoreLvl.addActor(floor3);
			
		}
			
	}
	
	boolean collision(){
		
		bombR.set(bomb.getX(), bomb.getY(), 100, 100);
		playerR.set(player.getX(), player.getY(), 100,100);
		
		bombR2.set(training.getX(), training.getY(), 100, 100);
		playerR2.set(player2.getX(), player2.getY(), 100,100);
		
		bombR3.set(hardcore.getX(), hardcore.getY(), 100, 100);
		playerR3.set(player3.getX(), player3.getY(), 100,100);
		
		if(bombR.overlaps(playerR)){
			
			return true;
			
		}
		
		else if(bombR2.overlaps(playerR2)){
			
			return true;
			
		}
		
		else if(bombR3.overlaps(playerR3)){
			
			return true;
			
		}

		collisionn = true;
		
		return false;
		
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
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		player.jump();
		player2.jump();
		player3.jump();

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
