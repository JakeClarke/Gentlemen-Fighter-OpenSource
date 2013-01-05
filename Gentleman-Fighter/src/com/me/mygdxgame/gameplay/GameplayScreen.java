package com.me.mygdxgame.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.mygdxgame.graphics.AnimatedSprite;
import com.me.mygdxgame.graphics.Frame;
import com.me.mygdxgame.screens.Screen;

public class GameplayScreen extends Screen {
	
	private AnimatedSprite sprite;
	private SpriteBatch sb;

	@Override
	public void update(boolean isTop, float elapsedGameTime) {
		if(!isTop)
			return;
		
		sprite.update(elapsedGameTime);
		
	}

	@Override
	public void render() {
		sb.begin();
		
		sprite.render();
		
		sb.end();
	}

	@Override
	public void loadContent() {
		this.sb = new SpriteBatch();
		
		this.sprite = new AnimatedSprite();
		this.sprite.Batch = this.sb;
		//this.sprite.Tint = Color.RED;
		
		this.sprite.Height = 100f;
		this.sprite.Width = 100f;
		
		Frame f = null;
		
		TextureRegion[] tr = TextureRegion.split(new Texture(Gdx.files.internal("data/graphics/sprite sheets/characters/fat dude.png")), 32, 32)[0];
		for(TextureRegion r : tr) {
			f = new Frame();
			f.Region = r;
			this.sprite.Frames.add(f);
		}
		
		
		this.sprite.Frames.add(f);
	}

	@Override
	public void disposeContent() {
		
		
	}

}
