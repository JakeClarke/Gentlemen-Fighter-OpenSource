package uk.me.jumped.gentlemenfighter.gameplay;

import uk.me.jumped.gentlemenfighter.Constants;
import uk.me.jumped.gentlemenfighter.gameplay.entities.EntityManager;
import uk.me.jumped.gentlemenfighter.gameplay.entities.PlatformEntity;
import uk.me.jumped.gentlemenfighter.gameplay.entities.PlayerEntity;
import uk.me.jumped.gentlemenfighter.graphics.Frame;
import uk.me.jumped.gentlemenfighter.screens.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class GameplayScreen extends Screen {
	
	private SpriteBatch sb;
	private World world;
	private TextureRegion bgtr;
	private EntityManager entityManager;

	@Override
	public void update(boolean isTop, float elapsedGameTime) {
		if(!isTop)
			return;
		
		this.world.step(elapsedGameTime * 0.001f, 6, 2);
		Gdx.app.log("Physics", "Number of bodies: " + this.world.getBodyCount() + ", Step: " + elapsedGameTime * 0.001f + "(s)");
		
		this.entityManager.updateEntities(elapsedGameTime);
		
	}

	@Override
	public void render() {
		sb.begin();
		sb.draw(bgtr, 0, 0);
		
		this.entityManager.renderEntities();
		
		sb.end();
	}

	@Override
	public void loadContent() {
		this.world = new World(new Vector2(0, -10), true); 
		
		this.sb = new SpriteBatch();
		
		this.entityManager = new EntityManager(this);
		
		switch (Controllers.getControllers().size) {
		case 2:
			this.entityManager.addEntity(new PlayerEntity(100f, 700f, Constants.PlayerClasses.SKINNY_DUDE, Controllers.getControllers().get(1),this.entityManager));
		case 1:
			this.entityManager.addEntity(new PlayerEntity(100f, 500f, Constants.PlayerClasses.FAT_DUDE, Controllers.getControllers().get(0),this.entityManager));
			break;
		}
		
		Texture bgt = new Texture(Gdx.files.internal(Constants.Files.Graphics.BACKGROUNDS + "library.png"));
		
		PlatformEntity platform = null;
		Frame[] fa = new Frame[1];
		fa[0] = new Frame();
		TextureRegion tr = new TextureRegion(bgt, 0, 730, 326, 30);
		fa[0].Region = tr;
		
		for(int i = 0; i < 5; i++) {
			platform = new PlatformEntity(0, 400 * i, 300, 30, fa, this.entityManager);
			this.entityManager.addEntity(platform);
		}
		
		fa = new Frame[1];
		fa[0] = new Frame();
		tr = new TextureRegion(bgt, 1280, 0, 264, 606);
		fa[0].Region = tr;
		
		platform = new PlatformEntity(490, 0, 264, 606, fa, this.entityManager);
		this.entityManager.addEntity(platform);
		
		
		
		bgtr = new TextureRegion(bgt,1280,720);
		
		
	}

	@Override
	public void disposeContent() {
		
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public SpriteBatch getMidSpriteBatch() {
		return this.sb;
	}

}
