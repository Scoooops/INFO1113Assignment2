package demolition;

import processing.core.PApplet;


/**
 * Represents a bomb.
 */
public class Bomb extends Sprite {

    /**
    * An integer that tracks the iterations of the sprite.
    */
    private int timer;

    /**
    * Time since the bomb was placed.
    */
    private Long time_placed;

    /**
     * Creates a new bomb at (x, y).
     * @param x The x-coordinate integer.
     * @param y The y-coordinate integer.
     */
    public Bomb(int x, int y) {
        super(x, y);
        this.timer = 0;
        time_placed = System.currentTimeMillis();
    }

    /**
     * Updates the sprite constantly.
     */
    public void tick() {
        // Increments the timer
        this.timer++;
    }

    /**
     * Checks how long since the bomb was placed.
     * @return The time since the bomb was placed.
     */
    public long timePlaced() {
        return time_placed;
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
