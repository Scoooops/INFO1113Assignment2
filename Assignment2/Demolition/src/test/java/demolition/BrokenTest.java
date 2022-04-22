package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BrokenTest {

    @Test
    public void Test() {
        Broken broken = new Broken(100, 200);
        assertEquals(100, broken.getX());
        assertEquals(200, broken.getY());
        assertEquals(0, broken.getTimer());
        broken.tick();
        assertEquals(1, broken.getTimer());
    }
}
