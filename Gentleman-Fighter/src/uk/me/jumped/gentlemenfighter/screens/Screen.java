package uk.me.jumped.gentlemenfighter.screens;

public abstract class Screen {

	private boolean isExiting = false;

	private boolean isLongLoading = false;

	private boolean isPopup = false;

	private ScreenManager screenManager = null;

	abstract public void update(boolean isTop, float elapsedGameTime);

	abstract public void render();

	abstract public void loadContent();

	abstract public void disposeContent();

	public void exit() {
		isExiting = true;

	}

	public boolean isExiting() {
		return this.isExiting;
	}

	public void setLongLoading(boolean isLongLoading) {
		this.isLongLoading = isLongLoading;
	}

	public boolean isLongLoading() {
		return this.isLongLoading;
	}

	public boolean isPopup() {
		return isPopup;
	}

	public void setPopup(boolean isPopup) {
		this.isPopup = isPopup;
	}

	public ScreenManager getScreenManager() {
		return screenManager;
	}

	public void setScreenManager(ScreenManager screenManager) {
		this.screenManager = screenManager;
	}

}
