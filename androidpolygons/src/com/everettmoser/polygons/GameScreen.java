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

	Polygon p1, p2;

	public GameScreen(Game game) {
		super(game);

		// Initialize game objects here
		
		p1 = new Polygon(new Vector2D(100, 100));
		p2 = new Polygon(new Vector2D(200, 100));
		
		p1.addVertex(new Vector2D(0, -60));
		p1.addVertex(new Vector2D((float)(-90 / Math.sqrt(3)), 30));
		p1.addVertex(new Vector2D((float)(90 / Math.sqrt(3)), 30));
		
		p2.addVertex(new Vector2D(0, 60));
		p2.addVertex(new Vector2D((float)(-90 / Math.sqrt(3)), -30));
		p2.addVertex(new Vector2D((float)(90 / Math.sqrt(3)), -30));
	}

	
	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		p1.rotate((float)Math.PI / 100);
		p2.rotate((float)Math.PI / 100);
		
		Polygon.collisionCheck(p1, p2);
			
	}


	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.clearScreen(Color.BLACK);
		Paint whitePaint = new Paint();
		whitePaint.setColor(Color.WHITE);
		
		if (Polygon.collisionCheck(p1, p2))
		{
			p1.draw(g, Color.WHITE);
			p2.draw(g, Color.WHITE);
			g.drawString("COLLISION", 100, 400, whitePaint);
		}
		else
		{
			p1.draw(g, Color.CYAN);
			p2.draw(g, Color.RED);
			g.drawString("NO COLLISION", 100, 400, whitePaint);
		}
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
