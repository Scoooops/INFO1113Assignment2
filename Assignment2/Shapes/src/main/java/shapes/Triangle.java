package shapes;

import processing.core.PImage;

/**
 * Represents a triangle.
 */
public class Triangle extends Shape {

    /**
     * Whether to move left.
     */
    private boolean move;


    /**
     * Creates a new triangle with coordinates (20, 20)
     */
    public Triangle() {
        super(100, 100);
        this.move = false;
    }

    /**
     * Updates the triangle every frame.
     */
    public void tick() {
        if (move) {
            this.x = this.x + 18;
            this.y = this.y + 24;
            this.move = false;
        }
    }

    /**
     * Called in App when the space key is pressed.
     */
    public void pressspace() {
        this.move = true;
    }
}
