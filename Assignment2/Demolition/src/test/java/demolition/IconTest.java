package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IconTest {

    @Test
    public void Test() {
        Icon icon = new Icon(100, 200);
        assertEquals(100, icon.getX());
        assertEquals(200, icon.getY());
        assertEquals(0, icon.getTimer());
        icon.tick();
        assertEquals(1, icon.getTimer());
    }
}
