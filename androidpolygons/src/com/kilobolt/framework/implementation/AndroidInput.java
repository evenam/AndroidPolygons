package com.kilobolt.framework.implementation;

import java.util.List;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

import com.kilobolt.framework.Input;

public class AndroidInput implements Input {    
    TouchHandler touchHandler;
    AccelerometerHandler accelerometerHandler;

    public AndroidInput(Context context, View view, float scaleX, float scaleY) {
        if(Integer.parseInt(VERSION.SDK) < 5) 
            touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
        else
            touchHandler = new MultiTouchHandler(view, scaleX, scaleY);   
        accelerometerHandler = new AccelerometerHandler(context);
    }


    @Override
    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }

    @Override
    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    @Override
    public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }



    @Override
    public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }


	@Override
	public double getAccelerometerX() {

		return accelerometerHandler.getX();
	}


	@Override
	public double getAccelerometerY() {

		return accelerometerHandler.getY();
	}


	@Override
	public double getAccelerometerZ() {
		
		return accelerometerHandler.getZ();
	}
    
}
