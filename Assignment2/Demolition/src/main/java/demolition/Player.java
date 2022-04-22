package demolition;

import processing.core.PApplet;


/**
 * Represents the player.
 */
public class Player extends Sprite {

    /**
    * An integer that tracks the iterations of the sprite.
    */
    private int timer;

    /**
    * The direction which the player is facing.
    */
    private String direction;

    /**
    * Whether or not a key has been pressed.
    */
    private boolean key_pressed;


    /**
     * Creates a new player character at (x, y).
     * @param x The x-coordinate integer.
     * @param y The y-coordinate integer.
     */
    public Player(int x, int y) {
        super(x, y);
        this.timer = 0;
        key_pressed = false;
        direction = "down";
    }

    /**
     * Updates the player constantly.
     */
    public void tick() {
        // Increments the timer
        this.timer++;

        if (key_pressed == true) {
            if (direction.equals("left")) {
                this.x -= 32;
                key_pressed = false;
            }
            else if (direction.equals("right")) {
                this.x += 32;
                key_pressed = false;
            }
            else if (direction.equals("up")) {
                this.y -= 32;
                key_pressed = false;
            }
            else {
                this.y += 32;
                key_pressed = false;
            }
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
     * Called in App when the left key is pressed.
     */
    public void pressLeft() {
        this.direction = "left";
        key_pressed = true;
    }

    /**
     * Called in App when the right key is pressed.
     */
    public void pressRight() {
        this.direction = "right";
        key_pressed = true;
    }

    /**
     * Called in App when the up key is pressed.
     */
    public void pressUp() {
        this.direction = "up";
        key_pressed = true;
    }

    /**
     * Called in App when the down key is pressed.
     */
    public void pressDown() {
        this.direction = "down";
        key_pressed = true;
    }

    /**
     * Resets the player to its original position.
     * @param x The x-coordinate integer.
     * @param y The y-coordinate integer.
     */
    public void reset(int x, int y) {
        this.x = x;
        this.y = y;
        direction = "down";
    }

    /**
     * Returns the key_pressed value.
     * @return True if a key has been pressed, false if otherwise.
     */
    public boolean getKeyPressed() {
        return key_pressed;
    }

    /**
     * Returns the direction value.
     * @return The direction of the sprite.
     */
    public String getDirection() {
        return direction;
    }

}
