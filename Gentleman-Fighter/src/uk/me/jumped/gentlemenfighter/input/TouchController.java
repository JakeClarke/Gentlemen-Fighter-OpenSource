package uk.me.jumped.gentlemenfighter.input;

import com.badlogic.gdx.Gdx;

public class TouchController extends AbstractController {

	private final int SWITCH_POINT;

	public TouchController() {
		SWITCH_POINT = Gdx.graphics.getWidth() / 2;
	}

	@Override
	public boolean isJumpPressed() {
		if(!Gdx.input.isTouched())
			return false;
		
		for(int i = 0; i < 3; i++) {
			int x = Gdx.input.getX(i);
			if (x < SWITCH_POINT && Gdx.input.isTouched(i))
				return true;
		}
		return false;
	}

	@Override
	public float getHorizonatalMove() {
		float y = Gdx.input.getAccelerometerY();
		y *= 0.10f;
		return y;
	}

	@Override
	public boolean isAttackPressed() {
		if (!Gdx.input.isTouched())
			return false;

		for (int i = 0; i < 3; i++) {
			int x = Gdx.input.getX(i);
			if (x > SWITCH_POINT)
				return true;
		}
		return false;
	}

}
