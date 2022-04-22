package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class YellowTest {

    @Test
    public void Test() {
        Yellow yellow = new Yellow(100, 200);
        assertEquals(100, yellow.getX());
        assertEquals(200, yellow.getY());
        assertEquals("down", yellow.getDirection());
        assertEquals(0, yellow.getTimer());
        assertEquals(false, yellow.canMove());

        yellow.moveDown(); // Testing down movement
        assertEquals("down", yellow.getDirection());
        yellow.move();
        assertEquals(true, yellow.canMove());
        yellow.tick();
        assertEquals(1, yellow.getTimer());
        assertEquals(100, yellow.getX());
        assertEquals(232, yellow.getY());
        assertEquals(false, yellow.canMove());

        yellow.moveUp(); // Testing up movement
        assertEquals("up", yellow.getDirection());
        yellow.move();
        assertEquals(true, yellow.canMove());
        yellow.tick();
        assertEquals(2, yellow.getTimer());
        assertEquals(100, yellow.getX());
        assertEquals(200, yellow.getY());
        assertEquals(false, yellow.canMove());

        yellow.moveRight(); // Testing right movement
        assertEquals("right", yellow.getDirection());
        yellow.move();
        assertEquals(true, yellow.canMove());
        yellow.tick();
        assertEquals(3, yellow.getTimer());
        assertEquals(132, yellow.getX());
        assertEquals(200, yellow.getY());
        assertEquals(false, yellow.canMove());

        yellow.moveLeft(); // Testing left movement
        assertEquals("left", yellow.getDirection());
        yellow.move();
        assertEquals(true, yellow.canMove());
        yellow.tick();
        assertEquals(4, yellow.getTimer());
        assertEquals(100, yellow.getX());
        assertEquals(200, yellow.getY());
        assertEquals(false, yellow.canMove());

        yellow.reset(150, 150); // Testing yellow reset
        yellow.tick();
        assertEquals("down", yellow.getDirection());
        assertEquals(5, yellow.getTimer());
        assertEquals(150, yellow.getX());
        assertEquals(150, yellow.getY());
    }
}
