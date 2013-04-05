package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.me.mygdxgame.gameplay.GameplayScreen;
import com.me.mygdxgame.screens.GDXLogoScreen;
import com.me.mygdxgame.screens.ScreenManager;

public class GentlemanFighterGame implements ApplicationListener {

	private ScreenManager screenManager = null;
	
	@Override
	public void create() {		
		this.screenManager = new ScreenManager();
		
		this.screenManager.addScreen(new GameplayScreen());
		this.screenManager.addScreen(new GDXLogoScreen());
	}

	@Override
	public void dispose() {
		this.screenManager.dispose();
	}

	@Override
	public void render() {

		
		if(Controllers.getControllers().size > 0 && Controllers.getControllers().get(0).getButton(0)) {
			Gdx.app.log("Controller 1", "A pressed.");
		}
		
		this.screenManager.update();
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		this.screenManager.render();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
