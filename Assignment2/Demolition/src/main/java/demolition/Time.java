package demolition;

import processing.core.PApplet;


/**
 * Represents a timer.
 */
public class Time extends Sprite {

    /**
    * An integer that tracks the iterations of the sprite.
    */
    private int timer;

    /**
     * Creates a new timer at (x, y).
     * @param x The x-coordinate integer.
     * @param y The y-coordinate integer.
     */
    public Time(int x, int y) {
        super(x, y);
        this.timer = 0;
    }

    /**
     * Updates the timer constantly.
     */
    public void tick() {
        // Increments the timer
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
