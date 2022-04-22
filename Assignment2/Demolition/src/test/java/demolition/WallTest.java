package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WallTest {

    @Test
    public void Test() {
        Wall wall = new Wall(100, 200);
        assertEquals(100, wall.getX());
        assertEquals(200, wall.getY());
        assertEquals(0, wall.getTimer());
        wall.tick();
        assertEquals(1, wall.getTimer());
    }
}
