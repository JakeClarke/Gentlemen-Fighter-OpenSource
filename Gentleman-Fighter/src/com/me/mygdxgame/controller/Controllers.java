package com.me.mygdxgame.controller;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.controller.device.Axis;
import com.esotericsoftware.controller.device.Button;
import com.esotericsoftware.controller.input.XboxController;
import com.esotericsoftware.controller.input.XboxController.Listener;

public class Controllers {
	private static ArrayList<XboxController> allControllers = null;
	private static ArrayList<XboxController> activeControllers = new ArrayList<XboxController>();
	
	static public ArrayList<XboxController> getAllControllers(){
		if (allControllers==null){
			poll();
		}
		return allControllers;
	}
	
	static public ArrayList<XboxController> getActiveControllers(){
		if (allControllers==null){
			poll();
		}
		
		return activeControllers;
	}
	
	static public void poll() {
		activeControllers.clear();
		allControllers = (ArrayList<XboxController>) XboxController.getAll();
		for(XboxController c : allControllers) {
			if(c.poll())
				activeControllers.add(c);
			
		}
		
		Gdx.app.log("controllers", "total controllers: " + allControllers.size() + ", Active: " + activeControllers.size());
	}
}
