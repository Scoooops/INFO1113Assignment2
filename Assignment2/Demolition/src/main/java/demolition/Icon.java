package demolition;

import processing.core.PApplet;


/**
 * Represents a icon.
 */
public class Icon extends Sprite {

    /**
    * An integer that tracks the iterations of the sprite.
    */
    private int timer;

    /**
     * Creates a new time icon at (x, y).
     * @param x The x-coordinate integer.
     * @param y The y-coordinate integer.
     */
    public Icon(int x, int y) {
        super(x, y);
        this.timer = 0;
    }

    /**
     * Updates the icon constantly.
     */
    public void tick() {
        // Increments the icon
        this.timer++;
    }

    /**
     * Returns the timer value.
     * @return The current value of the timer.
     */
    public int getTimer() {
        // Increments the timer
        return this.timer;
    }

}
