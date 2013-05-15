package uk.me.jumped.gentlemenfighter.gameplay.entities;

import uk.me.jumped.gentlemenfighter.Constants;
import uk.me.jumped.gentlemenfighter.graphics.AnimatedSprite;
import uk.me.jumped.gentlemenfighter.graphics.Frame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class PlayerEntity extends Entity {

	private Controller controller;
	private static final float HEIGHT = 100f, WIDTH = 100f;
	private AnimatedSprite walkingSprite = new AnimatedSprite();
	private boolean facingForward = true;
	public final String playerSpriteName;

	public PlayerEntity(float x, float y, final String playerSpriteName, final Controller controller, EntityManager parentManager) {
		super(genPlayerBodyDef(x,y), genFixtureDef(), parentManager);
		this.playerSpriteName = playerSpriteName;
		this.controller = controller;
		
		// sprite setup.
		this.walkingSprite.Batch = parentManager.getGameplayScreen().getMidSpriteBatch();
		this.walkingSprite.X = x;
		this.walkingSprite.Y = y;
		this.walkingSprite.Height = HEIGHT;
		this.walkingSprite.Width = WIDTH;
		
		Frame f = null;
		
		TextureRegion[] tr = TextureRegion.split(new Texture(Gdx.files.internal(Constants.Files.Graphics.PLAYER + this.playerSpriteName + ".png")), 32, 32)[0];
		for(TextureRegion r : tr) {
			f = new Frame();
			f.Region = r;
			this.walkingSprite.Frames.add(f);
		}
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void update(float elapsedMS) {
		Vector2 vel = new Vector2(this.controller.getAxis(Ouya.AXIS_LEFT_Y) * Constants.STICK_SCALER, 0).mul(Constants.WORLD_TO_BOX);
		//Vector2 vel = new Vector2(1000f, 1000f).mul(Constants.WORLD_TO_BOX);
		this.body.applyForceToCenter(vel);
		
		// TODO: Do something else if the player is in the air. 
		Vector2 curVol = this.body.getLinearVelocity().mul(Constants.BOX_TO_WORLD);
		
		if(curVol.x > 0) {
			facingForward = true;
		}
		else if(curVol.x < 0) {
			facingForward = false;
		}
		else if(curVol.x == 0) {
			this.walkingSprite.CurrentFrameNum = 0;
		}
		
		this.walkingSprite.flippedFlags = (this.facingForward) ? AnimatedSprite.FLIPPED_NONE : AnimatedSprite.FLIPPED_HORIZONTAL;
		
		Vector2 pos = this.getPosition();
		
		this.walkingSprite.X = pos.x - (WIDTH / 2);
		this.walkingSprite.Y = pos.y - (HEIGHT / 2);
		
		this.walkingSprite.update(Math.abs(elapsedMS * curVol.x) * 0.05f);
		
		Gdx.app.log("Player", this.playerSpriteName + ", Box pos: " + this.body.getPosition() + ", world pos:" + this.getPosition() + ", Velocity: " + curVol);
	}

	@Override
	public void render() {
		this.walkingSprite.render();
		
	}
	
	private static FixtureDef genFixtureDef() {
		// could be cached.
		FixtureDef fixDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox((WIDTH / 2) * Constants.WORLD_TO_BOX, (HEIGHT / 2) * Constants.WORLD_TO_BOX);
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
		bodyDef.position.set(new Vector2(posX + (WIDTH / 2), posY + (HEIGHT / 2)).mul(Constants.WORLD_TO_BOX));
		
		return bodyDef;
	}
}
