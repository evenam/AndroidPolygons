package com.everettmoser.polygons;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.graphics.Color;
import android.graphics.Paint;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.framework.Screen;

public class GameScreen extends Screen 
{

	// Variable Setup

	public GameScreen(Game game) {
		super(game);

		// Initialize game objects here

	}

	
	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

	}


	@Override
	public void paint(float deltaTime) {

		Graphics g = game.getGraphics();
		g.clearScreen(Color.BLACK);
		g.drawString("AccelX=" + game.getInput().getAccelerometerX(), 100, 100, new Paint());
	}

	private void nullify() {

		// Set all variables to null. You will be recreating them in the
		// constructor.

		// Call garbage collector to clean up memory.
		System.gc();

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
		pause();
	}
}
