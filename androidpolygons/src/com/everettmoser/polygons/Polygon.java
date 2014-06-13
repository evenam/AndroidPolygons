package com.everettmoser.polygons;

import java.util.ArrayList;
import android.graphics.Color;
import android.util.Log;

import com.kilobolt.framework.Graphics;

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
			if (radius < Vector2D.distance(newVertex))
				radius = Vector2D.distance(newVertex);
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
		if (radius < Vector2D.distance(newVertex))
			radius = Vector2D.distance(newVertex);
	}
	
	public void addVertexIndex(int index, Vector2D newVertex)
	{
		if (index < size)
			pts.add(index, new Vector2D(newVertex));
		else
			pts.add(new Vector2D(newVertex));

		if (radius < Vector2D.distance(newVertex))
			radius = Vector2D.distance(newVertex);
		size ++;
	}
	
	public void removeVertex(int index)
	{
		if (index < size)
		{
			pts.remove(index);
			size --;
			radius = 0.f;
			for (int i = 0; i < size; i ++)
			{
				if (Vector2D.distance(pts.get(i)) > radius)
					radius = Vector2D.distance(pts.get(i));
			}
		}
	}
	
	public void removeVertexLast()
	{
		if (size > 0)
		{
			pts.remove(pts.size() - 1);
			size --;
			radius = 0.f;
			for (int i = 0; i < size; i ++)
			{
				if (Vector2D.distance(pts.get(i)) > radius)
					radius = Vector2D.distance(pts.get(i));
			}
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
			nx = px * (float)Math.cos(theta) - py * (float)Math.sin(theta);
			ny = px * (float)Math.sin(theta) + py * (float)Math.cos(theta);
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

		Log.d("Polygon", "COLLISION CHECK " + (p1.getRadius() + p2.getRadius()) + " " + (Vector2D.distance(p1.getCenter(), p2.getCenter())));
		// collision circle check
		if ((p1.getRadius() + p2.getRadius()) < (Vector2D.distance(p1.getCenter(), p2.getCenter())))
			return false;
		
		Log.d("Polygon", "Passed circular collision");
		
		// check lines
		Vector2D pt = new Vector2D(0, 0);
		float cl_x1, cl_x2, cl_x3, cl_x4, cl_y1, cl_y2, cl_y3, cl_y4;
		float denom;
		for (int i = 0; i < p1.getSize(); i ++)
		{
			for (int j = 0; j < p2.getSize(); j ++)
			{
				cl_x1 = p1.getVertex(i).getX();
				cl_x2 = p1.getVertex((i + 1) % p1.getSize()).getX();
				cl_y1 = p1.getVertex(i).getY();
				cl_y2 = p1.getVertex((i + 1) % p1.getSize()).getY();
				cl_x3 = p2.getVertex(j).getX();
				cl_x4 = p2.getVertex((j + 1) % p2.getSize()).getX();
				cl_y3 = p2.getVertex(j).getY();
				cl_y4 = p2.getVertex((j + 1) % p2.getSize()).getY();
				denom = ((cl_x1 - cl_x2) * (cl_y3 - cl_y4) - (cl_y1 - cl_y2) * (cl_x3 - cl_x4));
				
				// check for parallel lines
				if (denom == 0)
					break;
				
				// the point
				pt.setX(((cl_x1 * cl_y2 - cl_y1 * cl_x2) * (cl_x3 - cl_x4) - (cl_x1 - cl_x2) * (cl_x3 * cl_y4 - cl_y3 * cl_y4)) / denom);
				pt.setY(((cl_x1 * cl_y2 - cl_y1 * cl_x2) * (cl_y3 - cl_y4) - (cl_y1 - cl_y2) * (cl_x3 * cl_y4 - cl_y3 * cl_y4)) / denom);
				
				// check if point is on segment
				if (pointOnLine(new Vector2D(cl_x1, cl_y1), new Vector2D(cl_x2, cl_y2), pt) 
				 && pointOnLine(new Vector2D(cl_x3, cl_y3), new Vector2D(cl_x4, cl_y4), pt))
					return true;
			}
		}
		
		Log.d("Polygon", "Passed Line Test");
		/*
		//check points
		// p1 points in p2
		float px1, px2, py1, py2;
		Vector2D testPoint;
		boolean flag1, flag2;
		boolean inside = false;
		
		for (int i = 0; i < p1.getSize(); i ++)
		{
			px1 = p2.getVertex(p2.getSize() - 1).getX();
			py1 = p2.getVertex(p2.getSize() - 1).getY();
			testPoint = p1.getVertex(i);
			inside = false;
			flag1 = (testPoint.getY() >= py1);
			
			for (int j = 0; j < p2.getSize(); j ++)
			{
				px2 = p2.getVertex(j).getX();
				py2 = p2.getVertex(j).getY();
				flag2 = (testPoint.getY() >= py2);
				if (flag1 != flag2)	
				{
					if (((py2 - testPoint.getY()) * (px1- - px2) >= (px2 - testPoint.getX()) * (py1 - py2)) == flag2 )
						inside = !inside;
				}
				px1 = px2;
				py1 = py2;
				flag1 = flag2;
			}
			
			if (inside) return true;
		}
		
		Log.d("Polygon", "Passed p1 in p2");
		
		// p2 points in p1
		for (int i = 0; i < p2.getSize(); i ++)
		{
			px1 = p1.getVertex(p1.getSize() - 1).getX();
			py1 = p1.getVertex(p1.getSize() - 1).getY();
			testPoint = p2.getVertex(i);
			inside = false;
			flag1 = (testPoint.getY() >= py1);
			
			for (int j = 0; j < p1.getSize(); j ++)
			{
				px2 = p1.getVertex(j).getX();
				py2 = p1.getVertex(j).getY();
				flag2 = (testPoint.getY() >= py2);
				if (flag1 != flag2)	
				{
					if (((py2 - testPoint.getY()) * (px1- - px2) >= (px2 - testPoint.getX()) * (py1 - py2)) == flag2 )
						inside = !inside;
				}
				px1 = px2;
				py1 = py2;
				flag1 = flag2;
			}
			
			if (inside) return true;
		}
		*/
		return false;
	}
	
	private static boolean pointOnLine(Vector2D lineStart, Vector2D lineEnd, Vector2D point)
	{
		// assume point is on line, and we are checking wether or not it is on the particular segment
		// primary use is for the collision function under "check lines"
		float xmax, xmin, ymax, ymin;
		xmax = Math.max(lineStart.getX(), lineEnd.getX());
		xmin = Math.min(lineStart.getX(), lineEnd.getX());
		ymax = Math.max(lineStart.getY(), lineEnd.getY());
		ymin = Math.min(lineStart.getY(), lineEnd.getY());
		
		if (point.getX() > xmax) return false;
		if (point.getX() < xmin) return false;
		if (point.getY() > ymax) return false;
		if (point.getY() < ymin) return false;
		
		return true;
	}
	
	public void draw(Graphics g, int c)
	{
		for (int i = 0; i < size; i ++)
		{
			g.drawLine((int)(pts.get(i).getX() + center.getX()), (int)(pts.get(i).getY() + center.getY()), (int)(pts.get((i + 1) % size).getX() + center.getX()), (int)(pts.get((i + 1) % size).getY() + center.getY()), c);
		}
	}
}
