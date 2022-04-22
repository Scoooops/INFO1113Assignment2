package demolition;

import processing.core.PApplet;


/**
 * Represents a finish tile.
 */
public class Finish extends Sprite {

    /**
    * An integer that tracks the iterations of the sprite.
    */
    private int timer;

    /**
     * Creates a new finish tile at (x, y).
     * @param x The x-coordinate integer.
     * @param y The y-coordinate integer.
     */
    public Finish(int x, int y) {
        super(x, y);
        this.timer = 0;
    }

    /**
     * Updates the sprite constantly.
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
