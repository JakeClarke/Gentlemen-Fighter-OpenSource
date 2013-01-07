package com.me.mygdxgame.gameplay.entities;

import com.esotericsoftware.controller.input.XboxController;

public class PlayerEntity extends Entity {

	@SuppressWarnings("unused")
	private String name;
	@SuppressWarnings("unused")
	private XboxController controller;

	public PlayerEntity(float x, float y, float width, float height,
			String name, XboxController controller) {
		super(x, y, width, height);
		this.name = name;
		this.controller = controller;
	}

	public void setNewController(XboxController controller) {
		this.controller = controller;
	}

	@Override
	public void update() {
		// TODO the update loop itself

	}

	// TODO needs other player-specific stuff probably
}
