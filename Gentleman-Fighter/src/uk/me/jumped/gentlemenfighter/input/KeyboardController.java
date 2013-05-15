package uk.me.jumped.gentlemenfighter.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class KeyboardController extends AbstractController {

	@Override
	public boolean isJumpPressed() {
		return Gdx.input.isKeyPressed(Keys.SPACE);
	}

	@Override
	public float getHorizonatalMove() {
		float res = 0f;
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			res -= 1f;
		}
		if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			res += 1f;
		}
		
		return res;
	}

	@Override
	public boolean isAttackPressed() {
		return Gdx.input.isKeyPressed(Keys.SHIFT_LEFT);
	}

}
