package demolition;

import processing.core.PApplet;


/**
 * Represents a breakable wall.
 */
public class Broken extends Sprite {

    /**
    * An integer that tracks the iterations of the sprite.
    */
    private int timer;

    /**
     * Creates a new broken tile at (x, y).
     * @param x The x-coordinate integer.
     * @param y The y-coordinate integer.
     */
    public Broken(int x, int y) {
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
