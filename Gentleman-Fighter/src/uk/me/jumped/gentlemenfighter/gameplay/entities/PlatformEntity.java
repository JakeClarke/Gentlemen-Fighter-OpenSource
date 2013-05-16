package uk.me.jumped.gentlemenfighter.gameplay.entities;

import java.util.Arrays;

import uk.me.jumped.gentlemenfighter.Constants;
import uk.me.jumped.gentlemenfighter.graphics.AnimatedSprite;
import uk.me.jumped.gentlemenfighter.graphics.Frame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class PlatformEntity extends Entity {

	final private AnimatedSprite platformSprite = new AnimatedSprite();

	public PlatformEntity(float posX, float posY, float width, float height,
			Frame[] frames, EntityManager parentManager) {
		super(genPlatformBodyDef(posX, posY, width, height), genFixtureDef(
				width, height), parentManager);

		this.platformSprite.X = posX;
		this.platformSprite.Y = posY;
		this.platformSprite.Width = width;
		this.platformSprite.Height = height;

		this.platformSprite.Batch = parentManager.getGameplayScreen()
				.getMidSpriteBatch();

		this.platformSprite.Frames.addAll(Arrays.asList(frames));
	}

	@Override
	public void update(float elapsedMS) {
		Vector2 pos = this.getPosition();

		this.platformSprite.X = pos.x - (this.platformSprite.Width / 2);
		this.platformSprite.Y = pos.y - (this.platformSprite.Height / 2);

		this.platformSprite.update(elapsedMS);

	}

	@Override
	public void render() {
		this.platformSprite.render();
	}

	private static FixtureDef genFixtureDef(float width, float height) {
		// could be cached.
		FixtureDef fixDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox((width / 2) * Constants.WORLD_TO_BOX, (height / 2)
				* Constants.WORLD_TO_BOX);
		fixDef.shape = shape;
		fixDef.density = 0.5f;
		fixDef.friction = 0.4f;
		fixDef.restitution = 0f;

		return fixDef;
	}

	private static BodyDef genPlatformBodyDef(float posX, float posY,
			float width, float height) {
		// could be cached partially.
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(new Vector2(posX + (width / 2), posY
				+ (height / 2)).mul(Constants.WORLD_TO_BOX));

		return bodyDef;
	}

}
