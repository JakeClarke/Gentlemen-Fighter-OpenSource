package uk.me.jumped.gentlemenfighter.gameplay;

import uk.me.jumped.gentlemenfighter.Constants;
import uk.me.jumped.gentlemenfighter.gameplay.entities.EntityManager;
import uk.me.jumped.gentlemenfighter.gameplay.entities.PlatformEntity;
import uk.me.jumped.gentlemenfighter.gameplay.entities.PlayerEntity;
import uk.me.jumped.gentlemenfighter.graphics.Frame;
import uk.me.jumped.gentlemenfighter.input.GamepadController;
import uk.me.jumped.gentlemenfighter.input.KeyboardController;
import uk.me.jumped.gentlemenfighter.input.TouchController;
import uk.me.jumped.gentlemenfighter.screens.Screen;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class GameplayScreen extends Screen {

	private SpriteBatch sb;
	private World world;
	private TextureRegion bgtr;
	private EntityManager entityManager;

	private Music music;

	private PlatformEntity[] boundaryPlatforms = new PlatformEntity[3];

	private ContactListener contactListener = new ContactListener() {

		@Override
		public void beginContact(Contact contact) {
			Gdx.app.log("CONTACTS-BEGIN", "A "
					+ contact.getFixtureA().getBody().getUserData().toString()
					+ ",B "
					+ contact.getFixtureB().getBody().getUserData().toString());
			Gdx.app.log("CONTACTS-BEGIN", contact.getWorldManifold()
					.getNormal().toString());
			if (contact.getFixtureA().getBody().getUserData() instanceof PlayerEntity
					&& contact.getFixtureB().getBody().getUserData() instanceof PlatformEntity) {
				if (Math.abs(contact.getWorldManifold().getNormal().y) > 0.5f) {
					((PlayerEntity) contact.getFixtureA().getBody()
							.getUserData()).grounded = true;
				}

				if (contact.getFixtureB().getBody().getUserData() == GameplayScreen.this.boundaryPlatforms[2]) {
					((PlayerEntity) contact.getFixtureA().getBody()
							.getUserData()).hit();
				}
			} else if (contact.getFixtureA().getBody().getUserData() instanceof PlayerEntity
					&& contact.getFixtureB().getBody().getUserData() instanceof PlayerEntity) {
				PlayerEntity p1 = (PlayerEntity) contact.getFixtureA()
						.getBody().getUserData();
				PlayerEntity p2 = (PlayerEntity) contact.getFixtureB()
						.getBody().getUserData();
				if (contact.getFixtureA() == p1.attackFixture
						&& contact.getFixtureB() == p2.attackFixture) {
					p1.addPlayerInRange(p2);
					p2.addPlayerInRange(p1);
				}
			}
		}

		@Override
		public void endContact(Contact contact) {
			// TODO fix ground detection.
			Gdx.app.log("CONTACTS-END", "A "
					+ contact.getFixtureA().getBody().getUserData().toString()
					+ ",B "
					+ contact.getFixtureB().getBody().getUserData().toString());
			Gdx.app.log("CONTACTS-END", contact.getWorldManifold().getNormal()
					.toString());
			if (contact.getFixtureA().getBody().getUserData() instanceof PlayerEntity
					&& contact.getFixtureB().getBody().getUserData() instanceof PlatformEntity) {
				if (Math.abs(contact.getWorldManifold().getNormal().y) > 0.5f) {
					((PlayerEntity) contact.getFixtureA().getBody()
							.getUserData()).grounded = false;
				}
			}

			if (contact.getFixtureA().getBody().getUserData() instanceof PlayerEntity
					&& contact.getFixtureB().getBody().getUserData() instanceof PlayerEntity) {
				PlayerEntity p1 = (PlayerEntity) contact.getFixtureA()
						.getBody().getUserData();
				PlayerEntity p2 = (PlayerEntity) contact.getFixtureB()
						.getBody().getUserData();
				if (contact.getFixtureA() == p1.attackFixture
						&& contact.getFixtureB() == p2.attackFixture) {
					p1.removePlayerInRange(p2);
					p2.removePlayerInRange(p1);
				}
			}

		}

		@Override
		public void preSolve(Contact contact, Manifold oldManifold) {
			// TODO Auto-generated method stub

		}

		@Override
		public void postSolve(Contact contact, ContactImpulse impulse) {
			// TODO Auto-generated method stub

		}

	};

	@Override
	public void update(boolean isTop, float elapsedGameTime) {
		if (!isTop)
			return;

		if (Gdx.input.isKeyPressed(Keys.R)) {
			this.music.stop();
			this.getScreenManager().addScreen(new GameplayScreen());
		}

		this.world.step(elapsedGameTime * 0.001f, 6, 2);

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

		if (Controllers.getControllers().size == 0) {
			if (Gdx.app.getType() == ApplicationType.Android) {
				this.entityManager.addEntity(new PlayerEntity(100f, 500f,
						Constants.PlayerClasses.FAT_DUDE,
						new TouchController(), this.entityManager));
			} else {
				this.entityManager.addEntity(new PlayerEntity(100f, 500f,
						Constants.PlayerClasses.FAT_DUDE,
						new KeyboardController(), this.entityManager));
			}

		} else {
			if (Controllers.getControllers().size >= 2) {
				this.entityManager.addEntity(new PlayerEntity(Gdx.graphics
						.getWidth() - 100f, 700f,
						Constants.PlayerClasses.SKINNY_DUDE,
						new GamepadController(Controllers.getControllers().get(
								1)), this.entityManager));
			}
			if (Controllers.getControllers().size >= 1) {
				this.entityManager.addEntity(new PlayerEntity(100f, 500f,
						Constants.PlayerClasses.FAT_DUDE,
						new GamepadController(Controllers.getControllers().get(
								0)), this.entityManager));
			}
			if (Controllers.getControllers().size == 1) {
				this.entityManager.addEntity(new PlayerEntity(Gdx.graphics
						.getWidth() - 100f, 700f,
						Constants.PlayerClasses.SKINNY_DUDE,
						new KeyboardController(), this.entityManager));
			}
		}

		Texture bgt = new Texture(
				Gdx.files.internal(Constants.Files.Graphics.BACKGROUNDS
						+ "library.png"));

		PlatformEntity platform = null;
		Frame[] fa = new Frame[1];
		fa[0] = new Frame();
		TextureRegion tr = new TextureRegion(bgt, 0, 730, 326, 30);
		fa[0].Region = tr;

		for (int i = 0; i < 5; i++) {
			platform = new PlatformEntity(0, 400 * i, 300, 30, fa,
					this.entityManager);
			this.entityManager.addEntity(platform);
		}

		final int rightx = Gdx.graphics.getWidth() - 300;

		for (int i = 0; i < 5; i++) {
			platform = new PlatformEntity(rightx, 400 * i, 300, 30, fa,
					this.entityManager);
			this.entityManager.addEntity(platform);
		}

		final int inneroffset = 200;

		platform = new PlatformEntity(rightx - inneroffset, 200, 300, 30, fa,
				this.entityManager);
		this.entityManager.addEntity(platform);

		platform = new PlatformEntity(inneroffset, 200, 300, 30, fa,
				this.entityManager);
		this.entityManager.addEntity(platform);

		this.boundaryPlatforms[0] = new PlatformEntity(-10, 0, 10,
				Gdx.graphics.getHeight(), fa, this.entityManager);
		this.entityManager.addEntity(this.boundaryPlatforms[0]);

		this.boundaryPlatforms[1] = new PlatformEntity(
				Gdx.graphics.getWidth() + 10, 0, 10, Gdx.graphics.getHeight(),
				fa, this.entityManager);
		this.entityManager.addEntity(this.boundaryPlatforms[1]);

		this.boundaryPlatforms[2] = new PlatformEntity(0, -50,
				Gdx.graphics.getWidth(), 5, fa, this.entityManager);
		this.entityManager.addEntity(this.boundaryPlatforms[2]);

		fa = new Frame[1];
		fa[0] = new Frame();
		tr = new TextureRegion(bgt, 1280, 0, 264, 606);
		fa[0].Region = tr;

		platform = new PlatformEntity(490, 0, 264, 606, fa, this.entityManager);
		this.entityManager.addEntity(platform);

		bgtr = new TextureRegion(bgt, 1280, 720);

		this.world.setContactListener(this.contactListener);

		this.music = Gdx.audio.newMusic(Gdx.files
				.internal(Constants.Files.Audio.MUSIC + "hashjamz.mp3"));

		this.music.setLooping(true);
		// TODO make it so that music starts only when the screen first comes
		// in.
		this.music.play();
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
