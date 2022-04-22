package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FinishTest {

    @Test
    public void Test() {
        Finish finish = new Finish(100, 200);
        assertEquals(100, finish.getX());
        assertEquals(200, finish.getY());
        assertEquals(0, finish.getTimer());
        finish.tick();
        assertEquals(1, finish.getTimer());
    }
}
