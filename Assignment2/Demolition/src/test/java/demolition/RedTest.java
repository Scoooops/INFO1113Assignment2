package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RedTest {

    @Test
    public void Test() {
        Red red = new Red(100, 200);
        assertEquals(100, red.getX());
        assertEquals(200, red.getY());
        assertEquals("down", red.getDirection());
        assertEquals(0, red.getTimer());
        assertEquals(false, red.canMove());

        red.moveDown(); // Testing down movement
        assertEquals("down", red.getDirection());
        red.move();
        assertEquals(true, red.canMove());
        red.tick();
        assertEquals(1, red.getTimer());
        assertEquals(100, red.getX());
        assertEquals(232, red.getY());
        assertEquals(false, red.canMove());

        red.moveUp(); // Testing up movement
        assertEquals("up", red.getDirection());
        red.move();
        assertEquals(true, red.canMove());
        red.tick();
        assertEquals(2, red.getTimer());
        assertEquals(100, red.getX());
        assertEquals(200, red.getY());
        assertEquals(false, red.canMove());

        red.moveRight(); // Testing right movement
        assertEquals("right", red.getDirection());
        red.move();
        assertEquals(true, red.canMove());
        red.tick();
        assertEquals(3, red.getTimer());
        assertEquals(132, red.getX());
        assertEquals(200, red.getY());
        assertEquals(false, red.canMove());

        red.moveLeft(); // Testing left movement
        assertEquals("left", red.getDirection());
        red.move();
        assertEquals(true, red.canMove());
        red.tick();
        assertEquals(4, red.getTimer());
        assertEquals(100, red.getX());
        assertEquals(200, red.getY());
        assertEquals(false, red.canMove());

        red.reset(150, 150); // Testing red reset
        red.tick();
        assertEquals("down", red.getDirection());
        assertEquals(5, red.getTimer());
        assertEquals(150, red.getX());
        assertEquals(150, red.getY());
    }
}
