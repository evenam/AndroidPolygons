package com.everettmoser.polygons;

public class Vector2D 
{
	private float x, y;
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public void setX(float X)
	{
		x = X;
	}
	
	public void setY(float Y)
	{
		y = Y;
	}
	
	public Vector2D()
	{
		x = 0.f;
		y = 0.f;
	}
	
	public Vector2D(float X, float Y)
	{
		x = X;
		y = Y;
	}
	
	public Vector2D(Vector2D copy)
	{
		x = copy.getX();
		y = copy.getY();
	}
	
	public static float dot(Vector2D v1, Vector2D v2)
	{
		return (v1.getX() * v2.getX() + v1.getY() * v2.getY());
	}
	
	public static float cross(Vector2D v1, Vector2D v2)
	{
		return (v1.getX() * v2.getY() - v2.getY() * v1.getY());
	}
	
	public void move(float dx, float dy)
	{
		x += dx;
		y += dy;
	}
	
	public static float direction(Vector2D origin, Vector2D raytip)
	{
		float direction;
		float nx, ny;
		nx = raytip.getX() - origin.getX();
		ny = raytip.getY() - origin.getY();
		direction = (float) Math.atan2(ny, nx);
		return direction;
	}
	
	public static float distance(Vector2D p1, Vector2D p2)
	{
		float distance = (float)Math.sqrt(Math.pow(p1.getX() - p2.getX(),2) + Math.pow(p1.getY() - p2.getY(), 2));
		return distance;
	}
}