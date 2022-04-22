package demolition;

import processing.core.PApplet;


/**
 * Represents an explosiion animation.
 */
public class Explosion extends Sprite {

    /**
    * An integer that tracks the iterations of the sprite.
    */
    private int timer;

    /**
    * Time since the bomb exploded.
    */
    private long time_exploded;

    /**
    * Direction of the explosion sprite.
    */
    private String direction;

    /**
    * The block that is positioned under the explosion sprite.
    */
    private String block;

    /**
     * Creates a new explosion at (x, y).
     * @param x The x-coordinate integer.
     * @param y The y-coordinate integer.
     * @param direction The direciton the explosion is going.
     */
    public Explosion(int x, int y , String direction) {
        super(x, y);
        this.timer = 0;
        this.direction = direction;
        time_exploded = System.currentTimeMillis();
        this.block = " ";
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

    /**
     * Checks how long since the explosion started.
     * @return The time since the bomb exploded.
     */
    public long timeExploded() {
        return time_exploded;
    }

    /**
     * Returns the direction of the explosion.
     * @return The direction of the sprite.
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Sets the block which the explosion is covering.
     * @param block The block underneath the given explosion.
     */
    public void setBlock(String block) {
        this.block = block;
    }

    /**
     * Returns the block which the explosion is covering.
     * @return The block that is underneath the explosion.
     */
    public String getBlock() {
        return block;
    }

}
