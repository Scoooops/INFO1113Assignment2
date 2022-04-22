package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GreenTest {

    @Test
    public void Test() {
        Green green = new Green(100, 200);
        assertEquals(100, green.getX());
        assertEquals(200, green.getY());
        assertEquals(0, green.getTimer());
        green.tick();
        assertEquals(1, green.getTimer());
    }
}
