package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void Test() {
        Player player = new Player(100, 200);
        assertEquals(100, player.getX());
        assertEquals(200, player.getY());
        assertEquals(false, player.getKeyPressed());
        assertEquals("down", player.getDirection());
        assertEquals(0, player.getTimer());

        player.pressDown(); // Testing down key input
        assertEquals(true, player.getKeyPressed());
        assertEquals("down", player.getDirection());
        player.tick();
        assertEquals(1, player.getTimer());
        assertEquals(100, player.getX());
        assertEquals(232, player.getY());
        assertEquals(false, player.getKeyPressed());

        player.pressUp(); // Testing up key input
        assertEquals(true, player.getKeyPressed());
        assertEquals("up", player.getDirection());
        player.tick();
        assertEquals(2, player.getTimer());
        assertEquals(100, player.getX());
        assertEquals(200, player.getY());
        assertEquals(false, player.getKeyPressed());

        player.pressRight(); // Testing right key input
        assertEquals(true, player.getKeyPressed());
        assertEquals("right", player.getDirection());
        player.tick();
        assertEquals(3, player.getTimer());
        assertEquals(132, player.getX());
        assertEquals(200, player.getY());
        assertEquals(false, player.getKeyPressed());

        player.pressLeft(); // Testing left key input
        assertEquals(true, player.getKeyPressed());
        assertEquals("left", player.getDirection());
        player.tick();
        assertEquals(4, player.getTimer());
        assertEquals(100, player.getX());
        assertEquals(200, player.getY());
        assertEquals(false, player.getKeyPressed());

        player.reset(150, 150); // Testing player reset
        player.tick();
        assertEquals("down", player.getDirection());
        assertEquals(5, player.getTimer());
        assertEquals(150, player.getX());
        assertEquals(150, player.getY());
        assertEquals(false, player.getKeyPressed());
    }
}
