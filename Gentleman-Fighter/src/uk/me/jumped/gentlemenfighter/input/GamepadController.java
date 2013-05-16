package uk.me.jumped.gentlemenfighter.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.mappings.Ouya;

public class GamepadController extends AbstractController {

	public static final int XBOX_A = 0;
	public static final int XBOX_B = 1;
	public static final int XBOX_X = 2;
	public static final int XBOX_Y = 3;

	final Controller controller;

	public GamepadController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public boolean isJumpPressed() {
		return this.controller.getButton(XBOX_A);
	}

	@Override
	public float getHorizonatalMove() {
		Gdx.app.log("INPUT", "X:" + this.controller.getAxis(Ouya.AXIS_LEFT_Y));
		return this.controller.getAxis(Ouya.AXIS_LEFT_Y);
	}

	@Override
	public boolean isAttackPressed() {
		return this.controller.getButton(XBOX_B);
	}

}
