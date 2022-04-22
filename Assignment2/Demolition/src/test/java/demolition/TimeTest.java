package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {

    @Test
    public void Test() {
        Time time = new Time(100, 200);
        assertEquals(100, time.getX());
        assertEquals(200, time.getY());
        assertEquals(0, time.getTimer());
        time.tick();
        assertEquals(1, time.getTimer());
    }
}
