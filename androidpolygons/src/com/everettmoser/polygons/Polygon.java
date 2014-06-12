package com.everettmoser.polygons;

import java.util.ArrayList;

public class Polygon 
{
	private ArrayList<Vector2D> pts;
	private Vector2D center;
	private int size;
	private float radius;
	
	public Vector2D getVertex(int i)
	{
		if (i < size)
			return pts.get(i);
		return null;
	}
	
	public void setVertex(int i, Vector2D newVertex)
	{
		if (i < size)
		{
			pts.get(i).setX(newVertex.getX());
			pts.get(i).setY(newVertex.getY());
		}
	}
	
	public Vector2D getCenter()
	{
		return center;
	}
	
	public void setCenter(Vector2D newCenter)
	{
		center.setX(newCenter.getX());
		center.setY(newCenter.getY());
	}
	
	public void addVertex(Vector2D newVertex)
	{
		pts.add(new Vector2D(newVertex));
		size ++;
	}
	
	public void addVertexIndex(int index, Vector2D newVertex)
	{
		if (index < size)
			pts.add(index, new Vector2D(newVertex));
		else
			pts.add(new Vector2D(newVertex));
		size ++;
	}
	
	public void removeVertex(int index)
	{
		if (index < size)
		{
			pts.remove(index);
			size --;
		}
	}
	
	public void removeVertexLast()
	{
		if (size > 0)
		{
			pts.remove(pts.size() - 1);
			size --;
		}
	}
	
	public void move(float dx, float dy)
	{
		center.setX(center.getX() + dx);
		center.setY(center.getY() + dy);
	}
	
	public void rotate(float theta)
	{
		float px, py, nx, ny;
		for (int i = 0; i < size; i ++)
		{
			px = pts.get(i).getX();
			py = pts.get(i).getY();
			nx = px * (float)Math.cos(theta) + py * (float)Math.sin(theta);
			ny = px * (float)Math.sin(theta) - py * (float)Math.cos(theta);
			pts.get(i).setX(nx);
			pts.get(i).setY(ny);
		}
	}
	
	public int getSize()
	{
		return size;
	}
	
	public float getRadius()
	{
		return radius;
	}
	
	public Polygon()
	{
		center = new Vector2D(0, 0);
		pts = new ArrayList<Vector2D>();
		size = 0;
		radius = 0.f;
	}
	
	public Polygon(Vector2D Center)
	{
		center = new Vector2D(Center);
		pts = new ArrayList<Vector2D>();
		size = 0;
		radius = 0.f;
	}
	
	public Polygon(Vector2D Center, ArrayList<Vector2D> points)
	{
		center = new Vector2D(Center);
		pts = new ArrayList<Vector2D>(points);
		size = points.size();
		radius = 0.f;
		for (int i = 0; i < size; i ++)
		{
			if (Vector2D.distance(pts.get(i)) > radius)
				radius = Vector2D.distance(pts.get(i));
		}
	}
	
	public Polygon(Polygon p)
	{
		center = new Vector2D(p.getCenter());
		size = 0;
		radius = p.getRadius();
		pts = new ArrayList<Vector2D>();
		for (int i = 0; i < p.getSize(); i ++)
		{
			pts.add(new Vector2D(p.getVertex(i)));
			size ++;
		}
	}
	
	public static boolean collisionCheck(Polygon p1, Polygon p2)
	{
		// collision circle check
		if ((p1.getRadius() + p2.getRadius()) < (Vector2D.distance(p1.getCenter(), p2.getCenter())))
			return false;
		
		// check lines
		for (int i = 0; i < p1.getSize(); i ++)
		{
			for (int j = 0; j < p2.getSize(); j ++)
			{
				
			}
		}
		
		//check points
		
		
		return false;
	}
}
