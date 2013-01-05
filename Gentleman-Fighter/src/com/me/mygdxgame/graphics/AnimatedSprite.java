package com.me.mygdxgame.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Color;

public class AnimatedSprite {

	public SpriteBatch Batch;
	public ArrayList<Frame> Frames = new ArrayList<Frame>();
	public int CurrentFrameNum;
	private float currentTime;
	public Color Tint = Color.WHITE;
	
	public float X, Y, Height = -1f, Width = -1f;
	
	public void update(float elapsedMS) {
		
		if(this.Frames.isEmpty()) {
			Gdx.app.error("Animation", "Sprite has no frames to render");
		}
		
		this.currentTime += elapsedMS;
		
		Frame currentFrame = this.Frames.get(CurrentFrameNum);
		
		while (this.currentTime > currentFrame.Length) {
			this.currentTime -= currentFrame.Length;
			this.CurrentFrameNum++;
			
			if(this.CurrentFrameNum >= this.Frames.size()) {
				this.CurrentFrameNum = 0;
				
			}
			
			currentFrame = this.Frames.get(CurrentFrameNum);
		}
		
	}
	
	public void render() {
		Frame currentFrame = this.Frames.get(CurrentFrameNum);
		
		this.Batch.setColor(Tint);
		this.Batch.draw(currentFrame.Region, this.X, this.Y, 
				(this.Width == -1) ? currentFrame.Region.getRegionWidth() : this.Width, 
				(this.Height == -1) ? currentFrame.Region.getRegionHeight() : this.Height);
	}
}
