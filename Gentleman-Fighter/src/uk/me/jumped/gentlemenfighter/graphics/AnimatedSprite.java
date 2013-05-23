package uk.me.jumped.gentlemenfighter.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AnimatedSprite {

	public final static int FLIPPED_NONE = 0, FLIPPED_HORIZONTAL = 1,
			FLIPPED_VERTICAL = 2;

	public SpriteBatch Batch;
	public ArrayList<Frame> Frames = new ArrayList<Frame>();
	public int CurrentFrameNum;
	private float currentTime;
	public Color Tint = Color.WHITE;
	public int flippedFlags = FLIPPED_NONE;

	public float X, Y, Height = -1f, Width = -1f;

	public void update(float elapsedMS) {

		if (this.Frames.isEmpty()) {
			Gdx.app.error("Animation", "Sprite has no frames to render");
		}

		if (this.Frames.size() > 1) {
			this.currentTime += elapsedMS;
			Frame currentFrame = this.Frames.get(CurrentFrameNum);
			while (this.currentTime > currentFrame.Length) {
				this.currentTime -= currentFrame.Length;
				this.CurrentFrameNum++;

				if (this.CurrentFrameNum >= this.Frames.size()) {
					this.CurrentFrameNum = 0;

				}

				currentFrame = this.Frames.get(CurrentFrameNum);
			}
		} else {
			this.CurrentFrameNum = 0;
		}

	}

	public void render() {
		Frame currentFrame = this.Frames.get(CurrentFrameNum);

		float x = this.X, y = this.Y, h = (this.Height == -1) ? currentFrame.Region
				.getRegionHeight() : this.Height, w = (this.Width == -1) ? currentFrame.Region
				.getRegionWidth() : this.Width;

		if ((flippedFlags & FLIPPED_HORIZONTAL) == FLIPPED_HORIZONTAL) {
			x = this.X + w;
			w = -w;
		}
		if ((this.flippedFlags & FLIPPED_VERTICAL) == FLIPPED_VERTICAL) {
			y = this.Y + h;
			h = -h;
		}
		if (this.flippedFlags > FLIPPED_HORIZONTAL + FLIPPED_VERTICAL) {
			Gdx.app.error("Sprite animation",
					"Sprite flipped value higher then expected");
		}

		this.Batch.setColor(Tint);
		this.Batch.draw(currentFrame.Region, x, y, w, h);
	}
}
