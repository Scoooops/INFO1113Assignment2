package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExplosionTest {

    @Test
    public void Test() {
        Explosion explosion = new Explosion(100, 200, "centre");
        assertEquals(100, explosion.getX());
        assertEquals(200, explosion.getY());
        assertEquals(0, explosion.getTimer());
        assertEquals("centre", explosion.getDirection());
        assertNotNull(explosion.timeExploded());
        assertEquals(" ", explosion.getBlock());
        explosion.setBlock("green");
        assertEquals("green", explosion.getBlock());
        explosion.tick();
        assertEquals(1, explosion.getTimer());
    }
}
