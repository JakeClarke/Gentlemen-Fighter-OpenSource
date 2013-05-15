package uk.me.jumped.gentlemenfighter.gameplay.entities;

import uk.me.jumped.gentlemenfighter.Constants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public abstract class Entity {
	
	protected Body body;
	
	private EntityManager manager;

	public Entity(BodyDef bodyDef, FixtureDef fixtureDef, EntityManager parentManager) {
		this.manager = parentManager;
		
		this.body = this.manager.getGameplayScreen().getWorld().createBody(bodyDef);
		this.body.setUserData(this);
		this.body.createFixture(fixtureDef);
	}

	public abstract void update(float elapsedMS);
	
	public abstract void render();

	public Vector2 getPosition() {
		return this.body.getPosition().mul(Constants.BOX_TO_WORLD);
	}
	
	public void setPosition(Vector2 pos) {
		this.body.getPosition().set(pos.mul(Constants.WORLD_TO_BOX));
	}
	
	
	public Body getBody() {
		return this.body;
	}
	
	public EntityManager getManager() {
		return this.manager;
	}

}