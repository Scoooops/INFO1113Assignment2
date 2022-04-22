package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.core.PApplet;

public class AppTest {

    @Test
    public void SetupTest() {   // Testing the map setup
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.settings();
        app.delay(1000);
        app.setup();
        app.delay(1000);
        app.draw();
        assertEquals(2, app.levels.size());
        assertEquals(0, app.level);
        assertEquals(13, app.map_layout.size());
        assertEquals(194, app.greens.size());
        assertEquals(23, app.brokens.size());
        assertEquals(82, app.walls.size());
        assertEquals(32, app.player.getX());
        assertEquals(80, app.player.getY());
        assertEquals(160, app.yellow_enemy.getX());
        assertEquals(336, app.yellow_enemy.getY());
        assertEquals(256, app.red_enemy.getX());
        assertEquals(208, app.red_enemy.getY());
        assertTrue(app.r_exists);
        assertTrue(app.y_exists);
    }

    @Test
    public void PlayerControlTest() {   // Testing the player controls
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.settings();
        app.delay(1000);
        app.setup();
        app.delay(1000);
        app.draw();
        assertEquals(32, app.player.getX());
        assertEquals(80, app.player.getY());
        app.keyCode = 37;   // Testing inexecutable left movement
        app.keyReleased();
        app.draw();
        assertFalse(app.can_move);
        app.keyCode = 38;   // Testing inexecutable upwards movement
        app.keyReleased();
        app.draw();
        assertFalse(app.can_move);
        app.keyCode = 39;   // Testing possible right movement
        app.keyReleased();
        app.draw();
        assertTrue(app.can_move);
        assertEquals(64, app.player.getX());
        assertEquals(80, app.player.getY());
        app.keyCode = 40;   // Testing inexecutable downwards movement
        app.keyReleased();
        app.draw();
        assertFalse(app.can_move);
        app.keyCode = 39;   // Repositioning
        app.keyReleased();
        app.draw();
        app.keyCode = 40;   // Testing possible downwards movement
        app.keyReleased();
        app.draw();
        assertTrue(app.can_move);
        assertEquals(96, app.player.getX());
        assertEquals(112, app.player.getY());
        app.keyCode = 40;   // Repositioning
        app.keyReleased();
        app.draw();
        app.keyCode = 37;   // Testing possible left movement
        app.keyReleased();
        app.draw();
        assertTrue(app.can_move);
        assertEquals(64, app.player.getX());
        assertEquals(144, app.player.getY());
        app.keyCode = 39;   // Repositioning
        app.keyReleased();
        app.draw();
        app.keyCode = 38;   // Testing possible upwards movement
        app.keyReleased();
        app.draw();
        assertTrue(app.can_move);
        assertEquals(96, app.player.getX());
        assertEquals(112, app.player.getY());

        // Testing a bomb drop
        app.keyCode = 40;   // Repositioning
        app.keyReleased();
        app.draw();
        app.keyCode = 32;
        app.keyReleased();
        assertTrue(app.place_bomb);
        app.draw();
        assertEquals(1, app.bombs.size());
        assertEquals(96, app.bombs.get(0).getX());
        assertEquals(160, app.bombs.get(0).getY());
        app.player.reset(288, 144);
        app.delay(2000);
        app.draw();
        assertEquals(0, app.bombs.size());
        assertEquals(1, app.explosions.size());
        app.delay(500);
        assertEquals(9, app.explosions.get(0).size());
        app.delay(2000);

        // Testing an obstructed bomb drop that kills an enemy
        app.keyCode = 32;
        app.keyReleased();
        assertTrue(app.place_bomb);
        app.draw();
        assertEquals(1, app.bombs.size());
        assertEquals(288, app.bombs.get(0).getX());
        assertEquals(160, app.bombs.get(0).getY());
        app.player.reset(32, 80);
        app.delay(2000);
        app.yellow_enemy.reset(288, 144);
        app.draw();
        assertEquals(0, app.bombs.size());
        assertEquals(2, app.explosions.size());
        app.delay(500);
        assertEquals(8, app.explosions.get(1).size());
        app.delay(2000);
        app.draw();
        assertEquals(10000, app.yellow_enemy.getX());

        // Testing when player runs into an enemy
        app.player.reset(app.red_enemy.getX(), app.red_enemy.getY());
        app.draw();
        assertEquals(32, app.player.getX());
        assertEquals(80, app.player.getY());
        assertEquals(160, app.yellow_enemy.getX());
        assertEquals(336, app.yellow_enemy.getY());
        assertEquals(256, app.red_enemy.getX());
        assertEquals(208, app.red_enemy.getY());
        for (Broken broken : app.brokens) {
            assertTrue(broken.getY() < 1000);
        }
    }

    @Test
    public void NextLevelTest() {   // Testing level with no enemies & win
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.settings();
        app.delay(1000);
        app.setup();
        app.delay(1000);
        app.draw();

        // Level with no enemies
        app.player.reset(app.finish.getX(), (app.finish.getY()-16));
        app.draw();
        assertFalse(app.r_exists);
        assertFalse(app.y_exists);
        assertTrue(app.win);
        assertEquals(1, app.level);
        assertEquals(32, app.walls.size());
        assertEquals(1, app.brokens.size());
        assertEquals(194, app.greens.size());
        assertEquals(10000, app.yellow_enemy.getX());
        assertEquals(10000, app.yellow_enemy.getY());
        assertEquals(10000, app.red_enemy.getX());
        assertEquals(10000, app.red_enemy.getY());

        // Win
        app.player.reset(app.finish.getX(), (app.finish.getY()-16));
        app.draw();
        assertTrue(app.win);
        assertEquals(0, app.walls.size());
        assertEquals(0, app.brokens.size());
        assertEquals(0, app.greens.size());
        assertNull(app.yellow_enemy);
        assertNull(app.red_enemy);
    }

    @Test
    public void TimeoutTest() {   // Testing running out of time
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.settings();
        app.delay(1000);
        app.setup();
        app.delay(1000);
        app.draw();

        app.elapsed_time = 200;
        app.draw();
        assertFalse(app.win);
        assertEquals(0, app.walls.size());
        assertEquals(0, app.brokens.size());
        assertEquals(0, app.greens.size());
        assertNull(app.yellow_enemy);
        assertNull(app.red_enemy);
    }

    @Test
    public void OutOfLivesTest() {   // Testing running out of lives
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.settings();
        app.delay(1000);
        app.setup();
        app.delay(1000);
        app.draw();

        app.lives = 0;
        app.draw();
        assertFalse(app.win);
        assertEquals(0, app.walls.size());
        assertEquals(0, app.brokens.size());
        assertEquals(0, app.greens.size());
        assertNull(app.yellow_enemy);
        assertNull(app.red_enemy);
    }
}
