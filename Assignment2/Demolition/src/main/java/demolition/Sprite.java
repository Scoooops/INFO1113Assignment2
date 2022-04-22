package demolition;
import processing.core.PImage;
import processing.core.PApplet;

/**
 * An abstract class for the sprites.
 */
public abstract class Sprite {

    /**
     * x coordinate.
     */
    protected int x;

    /**
     * y coordinate.
     */
    protected int y;

    /**
     * The sprite.
     */
    private PImage sprite;

    /**
     * Creating a new sprite.
     * @param x The x-coordinate integer.
     * @param y The y-coordinate integer.
     */
    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets an image for the sprite..
     * @param sprite The image to be loaded in.
     */
    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }

    /**
     * Updates the sprite constantly.
     */
    public abstract void tick();

    /**
     * Loads the sprite into the application.
     * @param app The app to be drawn.
     */
    public void draw(PApplet app) {
        app.image(this.sprite, this.x, this.y);
    }

    /**
     * x-coordinate.
     * @return The x-coordinate.
     */
    public int getX() {
        return this.x;
    }

    /**
     * y-coordinate.
     * @return The y-coordinate.
     */
    public int getY() {
        return this.y;
    }
}
