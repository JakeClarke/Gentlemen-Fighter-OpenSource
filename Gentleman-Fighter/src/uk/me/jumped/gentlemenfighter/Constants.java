package uk.me.jumped.gentlemenfighter;

public final class Constants {

	public static final float WORLD_TO_BOX = 0.02f;

	public static final float BOX_TO_WORLD = 50f;

	public static final String DEFAULT_LEVEL = "library.lvl";

	public final class Files {

		public static final String ROOT = "data/";

		public final class Graphics {

			public static final String ROOT = Files.ROOT + "graphics/";

			public static final String BACKGROUNDS = ROOT + "backgrounds/";

			public static final String PLAYER = Graphics.ROOT
					+ "sprite sheets/characters/";
		}

		public final class Audio {
			public static final String ROOT = Files.ROOT + "audio/";

			public static final String MUSIC = Audio.ROOT + "music/";
		}

		public final class Levels {
			public static final String ROOT = Files.ROOT + "levels/";
		}
	}

	public final class PlayerClasses {

		public static final String FAT_DUDE = "fat dude";

		public static final String SKINNY_DUDE = "skinny dude";
	}

}
