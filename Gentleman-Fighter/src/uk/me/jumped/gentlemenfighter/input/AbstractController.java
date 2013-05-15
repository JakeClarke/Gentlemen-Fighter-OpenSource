package uk.me.jumped.gentlemenfighter.input;

public abstract class AbstractController {
	
	/**
	 * Abstracts calls to test if the player is pressing the jump button.
	 * @return true if the Jump button is pressed. Otherwise false.
	 */
	public abstract boolean isJumpPressed();
	
	/**
	 * Abstracts the horizontal movement of the controller.
	 * @return returns the x movement Vector.
	 */
	public abstract float getHorizonatalMove();
	
	/**
	 * Abstracts calls to test if the player is pressing the attack button.
	 * @return true if the attack button is pressed. Otherwise false.
	 */
	public abstract boolean isAttackPressed();
}
