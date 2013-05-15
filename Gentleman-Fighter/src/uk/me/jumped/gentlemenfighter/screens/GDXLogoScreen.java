package uk.me.jumped.gentlemenfighter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class GDXLogoScreen extends Screen {
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	
	private static final float SHOW_TIME = 2000f;
	private float timeOnScreen = 0f;

	@Override
	public void update(boolean isTop, float elapsedGameTime) {
		if(timeOnScreen > SHOW_TIME) {
			this.exit();
		}
		
		timeOnScreen += elapsedGameTime;
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.draw(batch);
		batch.end();
		

	}

	@Override
	public void loadContent() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		
		sprite = new Sprite(region);
		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);

	}

	@Override
	public void disposeContent() {
		batch.dispose();
		texture.dispose();
	}

}
