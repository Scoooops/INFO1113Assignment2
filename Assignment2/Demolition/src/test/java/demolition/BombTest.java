package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BombTest {

    @Test
    public void Test() {
        Bomb bomb = new Bomb(100, 200);
        assertEquals(100, bomb.getX());
        assertEquals(200, bomb.getY());
        assertNotNull(bomb.timePlaced());
        assertEquals(0, bomb.getTimer());
        bomb.tick();
        assertEquals(1, bomb.getTimer());
    }
}
