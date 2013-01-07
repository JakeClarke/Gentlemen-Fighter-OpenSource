package com.me.mygdxgame.gameplay.entities;

import java.util.ArrayList;

public final class EntityManager {

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

	public void updateEntities() {
		for (Entity entity : entities) {
			entity.update();
		}
	}
}
