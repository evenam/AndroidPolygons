package com.kilobolt.framework.implementation;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class AccelerometerHandler implements SensorEventListener
{
	private double x, y, z;

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) { /*ignore*/ } 

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		x = event.values[0];
		y = event.values[1];
		z = event.values[2];
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getZ()
	{
		return z;
	}
	
	public AccelerometerHandler(Context c)
	{
		SensorManager m = (SensorManager) c.getSystemService(Context.SENSOR_SERVICE);
		Sensor a = m.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		m.registerListener(this, a, SensorManager.SENSOR_DELAY_GAME);
	}
}
