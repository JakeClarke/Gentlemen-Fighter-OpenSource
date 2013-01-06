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
			allControllers = (ArrayList<XboxController>) XboxController.getAll();
		}
		
		for (final XboxController controller : allControllers){
			if (controller.poll()) activeControllers.add(controller);
			
			controller.addListener(new Listener(){
				@Override
				public void connected () {
					Gdx.app.log("controller", "controller connected, port="+controller.getPort() + ", #now connected="+activeControllers.size());
					activeControllers.add(controller);
				}
				
				@Override
				public void disconnected () {
					Gdx.app.log("controller", "controller disconnected, port="+controller.getPort() + ", #now connected="+activeControllers.size());
					activeControllers.remove(controller);
				}
				
				@Override
				public void buttonChanged (Button button, boolean pressed) {
					Gdx.app.log("controller", "buttonChanged, port="+controller.getPort() + ", button="+button.name()+", state="+pressed);
				}

				@Override
				public void axisChanged (Axis axis, float state) {
					Gdx.app.log("controller", "axisChanged, port="+controller.getPort() + ", axis="+axis.name()+", state="+state);
				}
			});
		}
		Gdx.app.log("controller", "getAllControllers(), allControllers size="+allControllers.size()+", activeControllers.size="+activeControllers.size());
		return allControllers;
	}
	
	static public ArrayList<XboxController> getActiveControllers(){
		if (allControllers==null)
			getAllControllers();
		Gdx.app.log("controller", "getActiveControllers(), size="+activeControllers.size());
		return activeControllers;
	}
}
