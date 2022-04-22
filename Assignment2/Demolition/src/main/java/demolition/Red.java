package demolition;

import processing.core.PApplet;


/**
 * Represents the red enemy.
 */
public class Red extends Sprite {

    /**
    * An integer that tracks the iterations of the sprite.
    */
    private int timer;

    /**
    * The direction which the red enemy is facing.
    */
    private String direction;

    /**
    * Whether or not the red enemy can move.
    */
    private boolean can_move;


    /**
     * Creates the red enemy at (x, y).
     * @param x The x-coordinate integer.
     * @param y The y-coordinate integer.
     */
    public Red(int x, int y) {
        super(x, y);
        this.timer = 0;
        direction = "down";
        can_move = false;
    }

    /**
     * Updates the sprite constantly.
     */
    public void tick() {
        // Increments the timer
        this.timer++;
        if (can_move) {
            if (direction.equals("left")) {
                this.x -= 32;
            }
            else if (direction.equals("right")) {
                this.x += 32;
            }
            else if (direction.equals("up")) {
                this.y -= 32;
            }
            else {
                this.y += 32;
            }
            can_move = false;
        }
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
     * Allows the red enemy to move.
     */
    public void move() {
        can_move = true;
    }

    /**
     * Changes the red enemies direction to the left.
     */
    public void moveLeft() {
        direction = "left";
    }

    /**
     * Changes the red enemies direction to the right.
     */
    public void moveRight() {
        direction = "right";
    }

    /**
     * Changes the red enemies direction upwards.
     */
    public void moveUp() {
        direction = "up";
    }

    /**
     * Changes the red enemies direction downwards.
     */
    public void moveDown() {
        direction = "down";
    }

    /**
     * Resets the red enemy to its original position.
     * @param x The x-coordinate integer.
     * @param y The y-coordinate integer.
     */
    public void reset(int x, int y) {
        this.x = x;
        this.y = y;
        direction = "down";
    }

    /**
     * Returns the can_move value.
     * @return True if the enemy can move, false if otherwise.
     */
    public boolean canMove() {
        return can_move;
    }

    /**
     * Returns the direction value.
     * @return The direction of the sprite.
     */
    public String getDirection() {
        return direction;
    }

}
