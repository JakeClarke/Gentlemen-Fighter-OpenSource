package com.me.mygdxgame.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.me.mygdxgame.graphics.AnimatedSprite;
import com.me.mygdxgame.graphics.Frame;
import com.me.mygdxgame.screens.Screen;
import com.me.mygdxgame.Constants;

public class GameplayScreen extends Screen {
	
	private AnimatedSprite sprite;
	private SpriteBatch sb;
	private World world;
	private TextureRegion bgtr;

	@Override
	public void update(boolean isTop, float elapsedGameTime) {
		if(!isTop)
			return;
		
		this.world.step(elapsedGameTime * 0.001f, 6, 2);
		
		sprite.update(elapsedGameTime);
		
	}

	@Override
	public void render() {
		sb.begin();
		sb.draw(bgtr, 0, 0);
		
		sprite.render();
		
		sb.end();
	}

	@Override
	public void loadContent() {
		this.world = new World(new Vector2(0, -10), true); 
		
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
		
		bgtr = new TextureRegion(new Texture(Gdx.files.internal(Constants.Files.Graphics.BACKGROUNDS + "library.png")),1280,720);
		
		
	}

	@Override
	public void disposeContent() {
		
	}
	
	public World getWorld() {
		return this.world;
	}

}
