package com.me.mygdxgame.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

public final class ScreenManager {
	
	private ArrayList<Screen> screens = new ArrayList<Screen>();
	long lastUpdateFrame = 0;
	
	public void update() {
		
		if(this.screens.isEmpty()) {
			Gdx.app.log("ScreenManager", "No screens, quiting!");
			Gdx.app.exit();
		}
		
		for (int i = this.screens.size() - 1; i > -1; i--) { 
			Screen s = this.screens.get(i);
			if(s.isExiting())
			{
				s.disposeContent();
				this.screens.remove(s);
			}
		}
		
		final long delta = TimeUtils.nanoTime() - lastUpdateFrame;
		float milis = (float)delta / 1000000;
		
		if(milis > 140f)
		{
			milis = 140f;
		}
		
		
		boolean isTop = true;
		
		for(int i = this.screens.size() - 1; i > -1; i--) {
			
			Screen s = this.screens.get(i);
			
			s.update(isTop, milis);
			
			isTop = isTop ? s.isPopup() : false;
		}
		
		Gdx.app.log("Screens", "Num: "+ this.screens.size());
		Gdx.app.log("Time", "Delta: " + delta + " ms:" + milis);
		
		lastUpdateFrame = TimeUtils.nanoTime();
	}
	
	public void render() {
		boolean isTop = true;
		
		for(int i = this.screens.size() - 1; i > -1; i--) {
			
			Screen s = this.screens.get(i);
			
			s.render();
			
			isTop = isTop ? s.isPopup() : false;
			
			if(!isTop)
				break;
		}
		
	}
	
	public void addScreen(Screen screen) {
		if(screen.isLongLoading()) {
			screen = new LoadingScreen(screen);
		}
		
		screen.setScreenManager(this);
		screen.loadContent();
		this.screens.add(screen);
	}
	
	private void addScreenNonloading(Screen screen) {
		this.screens.add(screen);
	}
	
	public void dispose()
	{
		for(Screen s : this.screens) {
			s.disposeContent();
		}
	}
	
	
	private final class LoadingScreen extends Screen {
		
		private boolean hasRendered = false;
		private Screen target = null;
		
		public LoadingScreen(Screen target) {
			this.target = target;
		}
		

		@Override
		public void update(boolean isTop, float elapsedGameTime) {
			// TODO Auto-generated method stub
		}

		@Override
		public void render() {
			if(this.hasRendered)
			{
				this.target.setScreenManager(this.getScreenManager());
				this.target.loadContent();
				this.getScreenManager().addScreenNonloading(target);
				
				this.exit();
			}
			
			hasRendered = true;
		}

		@Override
		public void loadContent() {
			
		}


		@Override
		public void disposeContent() {
			// TODO Auto-generated method stub
			
		}

	}
	
}
