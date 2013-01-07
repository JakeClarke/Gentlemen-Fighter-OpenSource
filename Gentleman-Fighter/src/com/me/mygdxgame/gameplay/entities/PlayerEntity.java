package com.me.mygdxgame.gameplay.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.esotericsoftware.controller.device.Axis;
import com.esotericsoftware.controller.input.XboxController;
import com.me.mygdxgame.Constants;
import com.me.mygdxgame.graphics.AnimatedSprite;
import com.me.mygdxgame.graphics.Frame;

public class PlayerEntity extends Entity {

	private XboxController controller;
	private static final float HEIGHT = 100f, WIDTH = 100f;
	private AnimatedSprite walkingSprite = new AnimatedSprite();
	private boolean facingForward = true;

	public PlayerEntity(float x, float y, XboxController controller, EntityManager parentManager) {
		super(genPlayerBodyDef(x,y), genFixtureDef(), parentManager);
		this.controller = controller;
		
		// sprite setup.
		this.walkingSprite.Batch = parentManager.getGameplayScreen().getMidSpriteBatch();
		this.walkingSprite.X = x;
		this.walkingSprite.Y = y;
		this.walkingSprite.Height = HEIGHT;
		this.walkingSprite.Width = WIDTH;
		
		Frame f = null;
		
		TextureRegion[] tr = TextureRegion.split(new Texture(Gdx.files.internal("data/graphics/sprite sheets/characters/fat dude.png")), 32, 32)[0];
		for(TextureRegion r : tr) {
			f = new Frame();
			f.Region = r;
			this.walkingSprite.Frames.add(f);
		}
	}

	public void setController(XboxController controller) {
		this.controller = controller;
	}

	@Override
	public void update(float elapsedMS) {
		Vector2 vel = new Vector2(this.controller.get(Axis.leftStickX) * Constants.STICK_SCALER, 0);
		this.body.applyForceToCenter(vel);
		
		// TODO: Do something else if the player is in the air. 
		Vector2 curVol = this.body.getLinearVelocity().mul(Constants.WORLD_TO_BOX);
		
		if(curVol.x > 0) {
			facingForward = true;
		}
		else if(curVol.x < 0) {
			facingForward = false;
		}
		else if(curVol.x == 0) {
			this.walkingSprite.CurrentFrameNum = 0;
		}
		
		Vector2 pos = this.getPosition();
		
		this.walkingSprite.X = pos.x;
		this.walkingSprite.Y = pos.y;
		
		this.walkingSprite.update(elapsedMS * curVol.x);
	}

	@Override
	public void render() {
		this.walkingSprite.render();
		
	}
	
	private static FixtureDef genFixtureDef() {
		// could be cached.
		FixtureDef fixDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(WIDTH * Constants.WORLD_TO_BOX, HEIGHT * Constants.WORLD_TO_BOX);
		fixDef.shape = shape;
		fixDef.density = 0.5f; 
		fixDef.friction = 0.4f;
		fixDef.restitution = 0f;
		
		return fixDef;
	}
	
	private static BodyDef genPlayerBodyDef(float posX, float posY) {
		// could be cached partially.
		BodyDef bodyDef = new BodyDef();
		bodyDef.fixedRotation = true;
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(new Vector2(posX, posY).mul(Constants.WORLD_TO_BOX));
		
		return bodyDef;
	}
}
