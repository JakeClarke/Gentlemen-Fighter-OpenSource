package uk.me.jumped.gentlemenfighter;

import uk.me.jumped.gentlemenfighter.screens.Screen;
import uk.me.jumped.gentlemenfighter.screens.ScreenManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

public class GentlemanFighterGame implements ApplicationListener {

	public static final int TARGET_WIDTH = 1280, TARGET_HEIGHT = 720;

	private ScreenManager screenManager = null;

	public GentlemanFighterGame(Screen screen) {
		this(new Screen[] { screen });
	}

	public GentlemanFighterGame(Screen[] screens) {
		this.screenManager = new ScreenManager();
		this.screenManager.addScreens(screens);
	}

	@Override
	public void create() {

	}

	@Override
	public void dispose() {
		this.screenManager.dispose();
	}

	@Override
	public void render() {
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
