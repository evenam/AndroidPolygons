package com.everettmoser.polygons;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.kilobolt.framework.Screen;

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {
		
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();

		//This is how you would load a sound if you had one.
		//Assets.click = game.getAudio().createSound("explode.ogg");

		
		game.setScreen(new GameScreen(game));

	}

	@Override
	public void paint(float deltaTime) {
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {

	}
}