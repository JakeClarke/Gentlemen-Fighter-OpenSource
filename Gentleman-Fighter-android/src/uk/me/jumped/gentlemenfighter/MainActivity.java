package uk.me.jumped.gentlemenfighter;

import uk.me.jumped.gentlemenfighter.gameplay.GameplayScreen;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.surfaceview.FixedResolutionStrategy;

public class MainActivity extends AndroidApplication {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGL20 = false;
		cfg.resolutionStrategy = new FixedResolutionStrategy(
				GentlemanFighterGame.TARGET_WIDTH,
				GentlemanFighterGame.TARGET_HEIGHT);

		initialize(new GentlemanFighterGame(new GameplayScreen()), cfg);
	}
}