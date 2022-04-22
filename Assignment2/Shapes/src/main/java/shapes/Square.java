package shapes;

import processing.core.PImage;

/**
 * Represents a square.
 */
public class Square extends Shape {

    /**
     * Whether to move left.
     */
    private boolean moveLeft;

    /**
     * Whether to move up.
     */
    private boolean moveUp;

    /**
     * Creates a new square with coordinates (20, 20)
     */
    public Square() {
        super(20, 20);
        this.moveLeft = false;
        this.moveUp = false;
    }

    /**
     * Updates the square every frame.
     */
    public void tick() {
        // If moveLeft is true, move left by decrementing x
        if (moveLeft) {
            this.x--;
        } else {
            // Move right by incrementing x
            this.x++;
        }

        if (moveUp) {
            this.y--;
        } else {
            this.y++;
        }
    }

    /**
     * Called in App when the left key is pressed.
     */
    public void pressLeft() {
        this.moveLeft = true;
    }

    /**
     * Called in App when the right key is pressed.
     */
    public void pressRight() {
        this.moveLeft = false;
    }

    /**
     * Called in App when the up key is pressed.
     */
    public void pressUp() {
        this.moveUp = true;
    }

    /**
     * Called in App when the down key is pressed.
     */
    public void pressDown() {
        this.moveUp = false;
    }
}
