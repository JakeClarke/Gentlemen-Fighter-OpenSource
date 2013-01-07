package com.me.mygdxgame.gameplay.entities;

public class PowerupEntity extends Entity {

	@SuppressWarnings("unused")
	private String name;

	public PowerupEntity(int x, int y, int width, int height, String name) {
		super(x, y, width, height);
		this.name = name;
	}

	@Override
	public void update() {
		// TODO the update loop itself

	}

	// TODO needs other powerup-specific stuff probably
}
