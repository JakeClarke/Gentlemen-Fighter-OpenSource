package uk.me.jumped.gentlemenfighter.gameplay.entities;

import java.util.ArrayList;

import uk.me.jumped.gentlemenfighter.gameplay.GameplayScreen;

public final class EntityManager {

	private GameplayScreen gamePlayScreen;

	public EntityManager(GameplayScreen gamePlayScreen) {
		this.gamePlayScreen = gamePlayScreen;
	}

	private ArrayList<Entity> entities = new ArrayList<Entity>();

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}

	public void updateEntities(float elapsedMS) {
		for (Entity entity : entities) {
			if (entity.isActive())
				entity.update(elapsedMS);
		}
	}

	public void renderEntities() {
		for (Entity entity : entities) {
			if (entity.isActive())
				entity.render();
		}
	}

	public GameplayScreen getGameplayScreen() {
		return this.gamePlayScreen;
	}
}
