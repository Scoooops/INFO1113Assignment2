package demolition;

import processing.core.PApplet;
import processing.core.PFont;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import processing.data.JSONObject;
import processing.data.JSONArray;
import java.util.Random;

/**
* The application being run.
*/
public class App extends PApplet {

    /**
    * The width of the application.
    */
    public static final int WIDTH = 480;
    /**
    * The height of the application.
    */
    public static final int HEIGHT = 480;
    /**
    * The fps of the application.
    */
    public static final int FPS = 60;

    /**
    * The list containing all the wall objects.
    */
    public static ArrayList<Wall> walls;
    /**
    * The list containing all the green tile objects.
    */
    public static ArrayList<Green> greens;
    /**
    * The list containing all the broken tile objects.
    */
    public static ArrayList<Broken> brokens;
    /**
    * The list containing all of the bombs.
    */
    public static ArrayList<Bomb> bombs;
    /**
    * The list of the lists of all the explosions.
    */
    public static ArrayList<ArrayList<Explosion>> explosions;
    /**
    * The nested list of a given explosion and its parts.
    */
    public ArrayList<Explosion> this_explosion;
    /**
    * The list containing the strings of the map layout.
    */
    public ArrayList<String> map_layout;
    /**
    * The number of lives the player has for a given level.
    */
    public static int lives;
    /**
    * A current line being read from the JSON file.
    */
    public static JSONObject json_line;
    /**
    * A JSONArray containing each level and its details.
    */
    public static JSONArray levels;
    /**
    * The time for a given level.
    */
    public static int time;
    /**
    * The font used for the text.
    */
    public static PFont font;
    /**
    * The level the player is currently on.
    */
    public static int level;
    /**
    * The time at which the player started the level.
    */
    public long start_time;
    /**
    * The time taken constantly and used to get an elapsed time.
    */
    public long finish_time;
    /**
    * The time taken constantly and used to get an elapsed time.
    */
    public long current_time;
    /**
    * The interval between animation changes in the players sprite.
    */
    public double player_move_interval;
    /**
    * The interval between animation changes in the enemies sprites.
    */
    public double enemy_move_interval;
    /**
    * The elapsed time of the game.
    */
    public long elapsed_time;
    /**
    * The elapsed time of the players animations.
    */
    public long player_elapsed_time;
    /**
    * The elapsed time of the enemies movements.
    */
    public long enemy_elapsed_time;
    /**
    * The elapsed time of the enemies animations.
    */
    public long enemy_image_interval;
    /**
    * The current png image for the player.
    */
    public int player_current_state;
    /**
    * The current png image for the yellow enemy.
    */
    public int yellow_current_state;
    /**
    * The current png image for the red enemy.
    */
    public int red_current_state;
    /**
    * Whether or not the player can move.
    */
    public boolean can_move;
    /**
    * Whether or not the yellow enemy can move.
    */
    public boolean yellow_can_move;
    /**
    * Whether or not the red enemy can move.
    */
    public boolean red_can_move;
    /**
    * Whether or not the player has completed the level.
    */
    public boolean win;
    /**
    * Whether or not the space bar has been pressed to place a bomb.
    */
    public boolean place_bomb;
    /**
    * Whether or not there is a yellow enemy in the level.
    */
    public boolean y_exists;
    /**
    * Whether or not there is a red enemy in the level.
    */
    public boolean r_exists;
    /**
    * The direction which the yellow enemy is facing.
    */
    public int yellow_direction;
    /**
    * The direction which the red enemy is facing.
    */
    public int red_direction;
    /**
    * The initial x-coordinate for the red enemy.
    */
    public int red_init_x;
    /**
    * The initial y-coordinate for the red enemy.
    */
    public int red_init_y;
    /**
    * The initial x-coordinate for the yellow enemy.
    */
    public int yellow_init_x;
    /**
    * The initial y-coordinate for the yellow enemy.
    */
    public int yellow_init_y;
    /**
    * The initial x-coordinate for the player.
    */
    public int player_init_x;
    /**
    * The initial y-coordinate for the player.
    */
    public int player_init_y;
    /**
    * The last direction the player was facing.
    */
    public int last_key_code;
    /**
    * The block underneath a given explosion sprite.
    */
    public String block;
    /**
    * The path of the config.json file.
    */
    private String config;
    /**
    * The contents of the read JSON file.
    */
    public File json;
    /**
    * A given wall object.
    */

    public Wall wall;
    /**
    * A given green tile object.
    */
    public Green green;
    /**
    * A given broken tile object.
    */
    public Broken broken;
    /**
    * A given goal tile object.
    */
    public Finish finish;
    /**
    * A given time icon object.
    */
    public Time time_sprite;
    /**
    * A given player icon object.
    */
    public Icon icon;
    /**
    * A given player object.
    */
    public Player player;
    /**
    * A given red enemy object.
    */
    public Red red_enemy;
    /**
    * A given yellow enemy object.
    */
    public Yellow yellow_enemy;
    /**
    * A given bomb object.
    */
    public Bomb bomb;
    /**
    * A given explosion object.
    */
    public Explosion explosion;
    /**
    * A random number between 37 and 40 that dictates red's movmements.
    */
    public Random red_turn;

    /**
    * Loading in a new map from a given JSONArray and level number.
    * @param level The current level the player is on.
    * @param levels The JSONArray of each levels details.
    * @return List of the map layout.
    */
    public ArrayList<String> loadNewMap(int level, JSONArray levels) {
        try {   // Reading the level files to load the map
            map_layout = new ArrayList<String>();
            time = levels.getJSONObject(level).getInt("time");
            File this_level = new File
                (levels.getJSONObject(level).getString("path"));
            Scanner map_line = new Scanner(this_level);
            while (map_line.hasNextLine()) {
                map_layout.add(map_line.nextLine());
            }
        } // If path is invalid or file doesn't exist
        catch (FileNotFoundException e){
            System.out.println("Map does not exist.");
            return null;
        }
        return map_layout;
    }

    /**
    * Loading in a character into the map.
    * @param character The object of characters class (a character).
    * @param player_current_state The current state of the player (1,2,3,4).
    * @param file1 The string path of the png of animation 1.
    * @param file2 The string path of the png of animation 2.
    * @param file3 The string path of the png of animation 3.
    * @param file4 The string path of the png of animation 4.
    * @return The player current state integer.
    */
    public int loadCharacters(Sprite character, int player_current_state,
        String file1, String file2, String file3, String file4) {
        // Creating a gif-like animation of the chraracter
        if (player_current_state == 1) {
            character.setSprite(this.loadImage(file2));
            player_current_state = 2;
        }
        else if (player_current_state == 2) {
            character.setSprite(this.loadImage(file3));
            player_current_state = 3;
        }
        else if (player_current_state == 3) {
            character.setSprite(this.loadImage(file4));
            player_current_state = 4;
        }
        else {
            character.setSprite(this.loadImage(file1));
            player_current_state = 1;
        }
        return player_current_state;
    }

    /**
    * Checking if player or enemy movement results in a collision with a wall.
    * @param character The object of characters class (a character).
    * @param key_code The direction which the character is intending to move in.
    * @return True if player can move, false if otherwise.
    */
    public boolean checkCollision(Sprite character, int key_code) {
        for(Wall wall : walls) {
            if (key_code == 37) {
                if (wall.getX() == (character.getX()-32) &&
                    wall.getY() == (character.getY()+16)) {
                    return false;
                }
            }
            else if (key_code == 39) {
                if (wall.getX() == (character.getX()+32) &&
                    wall.getY() == (character.getY()+16)) {
                    return false;
                }
            }
            else if (key_code == 38) {
                if (wall.getX() == (character.getX()) &&
                    wall.getY() == (character.getY()-16)) {
                    return false;
                }
            }
            else if (key_code == 40) {
                if (wall.getX() == (character.getX()) &&
                    wall.getY() == (character.getY()+48)) {
                    return false;
                }
            }
        }
        for(Broken broken : brokens) {
            if (key_code == 37) {
                if (broken.getX() == (character.getX()-32) &&
                    broken.getY() == (character.getY()+16)) {
                    return false;
                }
            }
            else if (key_code == 39) {
                if (broken.getX() == (character.getX()+32) &&
                    broken.getY() == (character.getY()+16)) {
                    return false;
                }
            }
            else if (key_code == 38) {
                if (broken.getX() == (character.getX()) &&
                    broken.getY() == (character.getY()-16)) {
                    return false;
                }
            }
            else if (key_code == 40) {
                if (broken.getX() == (character.getX()) &&
                    broken.getY() == (character.getY()+48)) {
                    return false;
                }
            }
        }
    return true;
    }

    /**
    * Set the tile underneath each part of a particular explosion.
    * @param explosion The given explosion.
    * @param walls The list of all the walls on the map.
    * @param brokens The list of all the broken tiles on the map.
    * @return The tile underneath the given explosion.
    */
    public String setBlockValues(Explosion explosion, ArrayList<Wall> walls,
        ArrayList<Broken> brokens) {
        for (Wall wall : walls) {
            if (explosion.getX() == wall.getX() &&
                explosion.getY() == wall.getY()) {
                explosion.setBlock("wall");
                return "wall";
            }
        }
        for (Broken broken : brokens) {
            if (explosion.getX() == broken.getX() &&
                explosion.getY() == broken.getY()) {
                explosion.setBlock("broken");
                return "broken";
            }
        }
        explosion.setBlock("green");
        return "green";
    }

    /**
    * Clear all the elements of the map.
    */
    public void clearMap(){
        greens.clear();
        brokens.clear();
        walls.clear();
        explosions.clear();
        bombs.clear();
        yellow_enemy = null;
        red_enemy = null;

    }

    /**
    * Reset the map if the player loses a life.
    */
    public void reset(){
        red_enemy.reset(red_init_x, red_init_y);
        yellow_enemy.reset(yellow_init_x, yellow_init_y);
        player.reset(player_init_x, player_init_y);
        lives -= 1;
        bombs.clear();
        this.keyCode = 40;
        yellow_direction = 40;
        red_direction = 40;
        explosions.clear();
        bombs.clear();
        for (Broken broken : brokens) {
            if (broken.getY() > 1000) {
                broken.y -= 10000;
            }
        }
    }

    /**
    * Set the path to the JSON file for testing.
    * @param config The path to the config.json file.
    */
    public void setConfig(String config) {
        this.config = config;
    }

    /**
    * The main application where the JSON file is read and base values are set.
    */
    public App() {
        try {   // Reading the Json file
            if (this.config == "" || this.config == null) {
                json = new File("config.json");
            }
            else {
                json = new File(this.config);
            }
            Scanner newline = new Scanner(json);
            String json_data = newline.useDelimiter("\\Z").next();
            newline.close();
            JSONObject json_lines = JSONObject.parse(json_data);
            lives = json_lines.getInt("lives");
            levels = json_lines.getJSONArray("levels");
        }   // If the file does not exist
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }
        // Creating a list for the sprite objects
        walls = new ArrayList<Wall>();
        greens = new ArrayList<Green>();
        brokens = new ArrayList<Broken>();
        bombs = new ArrayList<Bomb>();
        explosions = new ArrayList<ArrayList<Explosion>>();
        red_turn = new Random();
        place_bomb = false;
        int wall_x_coord = 0;
        int wall_y_coord = 448;
        yellow_direction = 40;
        red_direction = 40;
        last_key_code = 40;
        time_sprite = new Time(256, 16);
        icon = new Icon(128, 16);
        level = 0;

    }

    /**
    * Setting height and width of the map.
    */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
    * Loading in the map along with all the sprites.
    */
    public void setup() {
        frameRate(FPS);

        y_exists = false;
        r_exists = false;
        if (level < levels.size()) {
            greens.clear();
            brokens.clear();
            walls.clear();
            // Loading in the string list for the map
            map_layout = loadNewMap(level, levels);
            int y_value = 64;
            int x_value = 0;
            for (String str : map_layout) {
                x_value = 0;
                // Checking the character value an creating a corresponding
                // sprite accordingly
                for (int j = 0 ; j < str.length() ; j++) {
                    String c = str.substring(j, j+1);
                    if (c.equals("B")) {
                        x_value = 0 + j*32;
                        greens.add(new Green(x_value, y_value));
                        brokens.add(new Broken(x_value, y_value));
                    }
                    else if (c.equals("G")) {
                        x_value = 0 + j*32;
                        finish = new Finish(x_value, y_value);
                    }
                    else if (c.equals("P")) {
                        x_value = 0 + j*32;
                        player = new Player(x_value, y_value-16);
                        player_init_x = x_value;
                        player_init_y = y_value-16;
                        greens.add(new Green(x_value, y_value));
                    }
                    else if (c.equals("W")) {
                        x_value = 0 + j*32;
                        greens.add(new Green(x_value, y_value));
                        walls.add(new Wall(x_value, y_value));
                    }
                    else if (c.equals("Y")) {
                        x_value = 0 + j*32;
                        yellow_enemy = new Yellow(x_value, y_value-16);
                        yellow_init_x = x_value;
                        yellow_init_y = y_value-16;
                        greens.add(new Green(x_value, y_value));
                        y_exists = true;
                    }
                    else if (c.equals("R")) {
                        x_value = 0 + j*32;
                        red_enemy = new Red(x_value, y_value-16);
                        red_init_x = x_value;
                        red_init_y = y_value-16;
                        greens.add(new Green(x_value, y_value));
                        r_exists = true;
                    }
                    else {
                        x_value = 0 + j*32;
                        greens.add(new Green(x_value, y_value));
                    }
                }
                y_value += 32;
            }   // If there is no red or yellow enemy in the level file,
                // they will be placed off the map
            if (!(y_exists)) {
                yellow_enemy = new Yellow(10000, 10000);
                yellow_init_x = 10000;
                yellow_init_y = 10000;
            }
            if (!(r_exists)) {
                red_enemy = new Red(10000, 10000);
                red_init_x = 10000;
                red_init_y = 10000;
            }
        }
        current_time = 0;
        player_move_interval = 0;
        enemy_move_interval = 0;
        enemy_image_interval = 0;
        start_time = System.currentTimeMillis();
        finish_time = System.currentTimeMillis();

        // Getting elapsed times to check for certain conditions
        player_elapsed_time = finish_time-start_time;
        elapsed_time = (finish_time-start_time)/1000;

        // Load in all the initial sprites
        for (Green green : greens) {
            green.setSprite(this.loadImage
                ("src/main/resources/empty/empty.png"));
        }
        for (Wall wall : walls) {
            wall.setSprite(this.loadImage
                ("src/main/resources/wall/solid.png"));
        }
        for (Broken broken : brokens) {
            broken.setSprite(this.loadImage
                ("src/main/resources/broken/broken.png"));
        }
        finish.setSprite(this.loadImage
            ("src/main/resources/goal/goal.png"));
        time_sprite.setSprite(this.loadImage
            ("src/main/resources/icons/clock.png"));
        icon.setSprite(this.loadImage
            ("src/main/resources/icons/player.png"));
        player.setSprite(this.loadImage
            ("src/main/resources/player/player1.png"));
        red_enemy.setSprite(this.loadImage
            ("src/main/resources/red_enemy/red_down1.png"));
        yellow_enemy.setSprite(this.loadImage
            ("src/main/resources/yellow_enemy/yellow_down1.png"));

        // Current state dictates what png to go to next for the git-like
        // animation
        player_current_state = 1;
        yellow_current_state = 1;
        red_current_state = 1;
    }

    /**
    * The main loop of the application where the animation and key inputs occur.
    */
    public void draw() {
        background(242, 165, 58);
        win = false;

        // Setting up the text font/size/colour
        font = createFont("src/main/resources/PressStart2P-Regular.ttf", 20);
        textFont(font, 20);
        fill(0);

        // Checking if the player has won
        if (((player.getX() == finish.getX()) &&
            (player.getY()+16 == finish.getY())) &&
            (level == (levels.size()-1))) {
            clearMap();
            background(242, 165, 58);
            text("YOU WIN", 160, 240);
            win = true;
            return;
        }

        // Checking if the player has lost
        if ((elapsed_time > time || lives == 0) && !win) {
            clearMap();
            background(242, 165, 58);
            text("GAME OVER", 150, 240);
            return;
        }

        // Checking if space has been pressed
        if (place_bomb) {
            bombs.add(new Bomb(player.getX(), (player.getY()+16)));
            place_bomb = false;
        }

        // Checking if the player has collided with either of the enemies
        if((player.getX() == yellow_enemy.getX()) &&
            (player.getY() == yellow_enemy.getY()) ||
            (player.getX() == red_enemy.getX()) &&
            (player.getY() == red_enemy.getY())) {
                reset();
                return;
            }

        // If a bomb has been down for longer than two seconds, it will explode
        for (ArrayList<Explosion> explosion : explosions) {
            // Placing the individual sprites for the explosion
            for (Explosion explosion_part : explosion) {
                if ((player.getX() == explosion_part.getX()) &&
                    ((player.getY()+16) == explosion_part.getY())) {
                    reset();
                    return;
                }
                if ((yellow_enemy.getX() == explosion_part.getX()) &&
                    ((yellow_enemy.getY()+16) == explosion_part.getY())) {
                    yellow_enemy.x = 10000;
                }
                if ((red_enemy.getX() == explosion_part.getX()) &&
                    ((red_enemy.getY()+16) == explosion_part.getY())) {
                    red_enemy.x = 10000;
                }
                for (Broken broken : brokens) {
                    if ((broken.getX() == explosion_part.getX()) &&
                        (broken.getY() == explosion_part.getY())) {
                        broken.y += 10000;
                    }
                }
            }
        }

        // Updating all the sprites
        for (Green green : greens) {
            green.tick();
            green.draw(this);
        }
        for (Wall wall : walls) {
            wall.tick();
            wall.draw(this);
        }
        for (Broken broken : brokens) {
            broken.tick();
            broken.draw(this);
        }
        for (Bomb bomb : bombs) {
            // When the bomb has been placed over 2 seconds ago, it explodes
            if ((System.currentTimeMillis() - bomb.timePlaced()) > 2000) {
                this_explosion = new ArrayList<Explosion>();
                this_explosion.add(new Explosion
                    (bomb.getX(), bomb.getY(), "centre"));
                this_explosion.add(new Explosion
                    (bomb.getX()+64, bomb.getY(), "right1"));
                this_explosion.add(new Explosion
                    (bomb.getX()+32, bomb.getY(), "right2"));
                this_explosion.add(new Explosion
                    (bomb.getX()-64, bomb.getY(), "left1"));
                this_explosion.add(new Explosion
                    (bomb.getX()-32, bomb.getY(), "left2"));
                this_explosion.add(new Explosion
                    (bomb.getX(), bomb.getY()+64, "down1"));
                this_explosion.add(new Explosion
                    (bomb.getX(), bomb.getY()+32, "down2"));
                this_explosion.add(new Explosion
                    (bomb.getX(), bomb.getY()-64, "up1"));
                this_explosion.add(new Explosion
                    (bomb.getX(), bomb.getY()-32, "up2"));
                explosions.add(this_explosion);
                bombs.remove(bomb);
                break;
            }
            // Otherwise, continue with its animation
            else if ((System.currentTimeMillis() -
                bomb.timePlaced()) > 1750) {
                bomb.setSprite(this.loadImage
                    ("src/main/resources/bomb/bomb7.png"));
            }
            else if ((System.currentTimeMillis() -
                bomb.timePlaced()) > 1500) {
                bomb.setSprite(this.loadImage
                    ("src/main/resources/bomb/bomb6.png"));
            }
            else if ((System.currentTimeMillis() -
                bomb.timePlaced()) > 1250) {
                bomb.setSprite(this.loadImage
                    ("src/main/resources/bomb/bomb5.png"));
            }
            else if ((System.currentTimeMillis() -
                bomb.timePlaced()) > 1000) {
                bomb.setSprite(this.loadImage
                        ("src/main/resources/bomb/bomb4.png"));
            }
            else if ((System.currentTimeMillis() -
                bomb.timePlaced()) > 750) {
                bomb.setSprite(this.loadImage
                    ("src/main/resources/bomb/bomb3.png"));
            }
            else if ((System.currentTimeMillis() -
                bomb.timePlaced()) > 500) {
                bomb.setSprite(this.loadImage
                    ("src/main/resources/bomb/bomb2.png"));
            }
            else if ((System.currentTimeMillis() -
                bomb.timePlaced()) > 250) {
                bomb.setSprite(this.loadImage
                    ("src/main/resources/bomb/bomb1.png"));
            }
            else {
                bomb.setSprite(this.loadImage
                    ("src/main/resources/bomb/bomb.png"));
            }
            bomb.tick();
            bomb.draw(this);
        }
        // Animating the exlposion
        for (int k=0 ; k<explosions.size() ; k++) {
            for (Explosion explosion : explosions.get(k)) {
                if ((System.currentTimeMillis() -
                    explosion.timeExploded()) > 500) {
                    explosions.get(k).clear();
                    break;
                }
                // Checking for obstructions from walls and broken tiles
                if (explosion.getDirection().equals("centre")) {
                    explosion.setSprite(this.loadImage
                        ("src/main/resources/explosion/centre.png"));
                    explosion.setBlock("green");
                }
                else if (explosion.getDirection().equals("right1")) {
                    setBlockValues(explosion, walls, brokens);

                }
                else if (explosion.getDirection().equals("right2")) {
                    block = setBlockValues(explosion, walls, brokens);
                    if (block.equals("wall") || block.equals("broken")) {
                        for (Explosion next_explosion : explosions.get(k)) {
                            if (next_explosion.getDirection().equals("right1")) {
                                next_explosion.setBlock("");
                            }
                        }
                    }
                }
                else if (explosion.getDirection().equals("left1")) {
                    setBlockValues(explosion, walls, brokens);
                }
                else if (explosion.getDirection().equals("left2")) {
                    block = setBlockValues(explosion, walls, brokens);
                    if (block.equals("wall") || block.equals("broken")) {
                        for (Explosion next_explosion : explosions.get(k)) {
                            if (next_explosion.getDirection().equals("left1")) {
                                next_explosion.setBlock("");
                            }
                        }
                    }
                }
                else if (explosion.getDirection().equals("up1")) {
                    setBlockValues(explosion, walls, brokens);
                }
                else if (explosion.getDirection().equals("up2")) {
                    block = setBlockValues(explosion, walls, brokens);
                    if (block.equals("wall") || block.equals("broken")) {
                        for (Explosion next_explosion : explosions.get(k)) {
                            if (next_explosion.getDirection().equals("up1")) {
                                next_explosion.setBlock("");
                            }
                        }
                    }
                }
                else if (explosion.getDirection().equals("down1")) {
                    setBlockValues(explosion, walls, brokens);
                }
                else if (explosion.getDirection().equals("down2")) {
                    block = setBlockValues(explosion, walls, brokens);
                    if (block.equals("wall") || block.equals("broken")) {
                        for (Explosion next_explosion : explosions.get(k)) {
                            if (next_explosion.getDirection().equals("down1")) {
                                next_explosion.setBlock("");
                            }
                        }
                    }
                }
            }
            // Checking wheter explosion extremeties are blocked and therefore
            // need to be removed
            for (Explosion explosion : explosions.get(k)) {
                if (explosion.getDirection().equals("right1")) {
                    if ((explosion.getBlock().equals("green")) ||
                        (explosion.getBlock().equals("broken"))) {
                        explosion.setSprite(this.loadImage
                            ("src/main/resources/explosion/end_right.png"));
                    }
                }
                else if (explosion.getDirection().equals("right2")) {
                    if ((explosion.getBlock().equals("green")) ||
                        (explosion.getBlock().equals("broken"))) {
                        explosion.setSprite(this.loadImage
                            ("src/main/resources/explosion/horizontal.png"));
                    }
                }
                else if (explosion.getDirection().equals("left1")) {
                    if ((explosion.getBlock().equals("green")) ||
                        (explosion.getBlock().equals("broken"))) {
                        explosion.setSprite(this.loadImage
                            ("src/main/resources/explosion/end_left.png"));
                    }
                }
                else if (explosion.getDirection().equals("left2")) {
                    if ((explosion.getBlock().equals("green")) ||
                        (explosion.getBlock().equals("broken"))) {
                        explosion.setSprite(this.loadImage
                            ("src/main/resources/explosion/horizontal.png"));
                    }
                }
                else if (explosion.getDirection().equals("up1")) {
                    if ((explosion.getBlock().equals("green")) ||
                        (explosion.getBlock().equals("broken"))) {
                        explosion.setSprite(this.loadImage
                            ("src/main/resources/explosion/end_top.png"));
                    }
                }
                else if (explosion.getDirection().equals("up2")) {
                    if ((explosion.getBlock().equals("green")) ||
                        (explosion.getBlock().equals("broken"))) {
                        explosion.setSprite(this.loadImage
                            ("src/main/resources/explosion/vertical.png"));
                    }
                }
                else if (explosion.getDirection().equals("down1")) {
                    if ((explosion.getBlock().equals("green")) ||
                        (explosion.getBlock().equals("broken"))) {
                        explosion.setSprite(this.loadImage
                            ("src/main/resources/explosion/end_bottom.png"));
                    }
                }
                else if (explosion.getDirection().equals("down2")) {
                    if ((explosion.getBlock().equals("green")) ||
                        (explosion.getBlock().equals("broken"))) {
                        explosion.setSprite(this.loadImage
                            ("src/main/resources/explosion/vertical.png"));
                    }
                }
                if ((explosion.getBlock().equals("green")) ||
                    (explosion.getBlock().equals("broken"))) {
                    explosion.tick();
                    explosion.draw(this);
                }
            }
        }
        // Removing explosion extremeties that have been flagged as so
        for (int l=0 ; l<explosions.size() ; l++) {
            for (int m=0 ; m<explosions.get(l).size(); m++) {
                if (!((explosions.get(l).get(m).getBlock().equals("green")) ||
                    (explosions.get(l).get(m).getBlock().equals("broken")))) {
                    explosions.get(l).remove(explosions.get(l).get(m));
                }
            }
        }
        // Updating all other sprites
        finish.tick();
        finish.draw(this);
        time_sprite.tick();
        time_sprite.draw(this);
        icon.tick();
        icon.draw(this);
        player.tick();
        player.draw(this);
        red_enemy.tick();
        red_enemy.draw(this);
        yellow_enemy.tick();
        yellow_enemy.draw(this);

        // Updating elapsed time
        if (elapsed_time != current_time) {
            current_time = elapsed_time;
        }

        // Updating the player to give a gif-like animation based on
        // direction of movement
        if ((player_elapsed_time - player_move_interval) >= 200) {
            if (this.keyCode != 37 && this.keyCode != 38 &&
                this.keyCode != 39 && this.keyCode != 40) {
                this.keyCode = last_key_code;
            }
            if (this.keyCode == 37) {
                player_current_state = loadCharacters
                    (player, player_current_state,
                    "src/main/resources/player/player_left1.png",
                    "src/main/resources/player/player_left2.png",
                    "src/main/resources/player/player_left3.png",
                    "src/main/resources/player/player_left4.png");
            }
            else if (this.keyCode == 38) {
                player_current_state = loadCharacters
                    (player, player_current_state,
                    "src/main/resources/player/player_up1.png",
                    "src/main/resources/player/player_up2.png",
                    "src/main/resources/player/player_up3.png",
                    "src/main/resources/player/player_up4.png");
            }
            else if (this.keyCode == 39) {
                player_current_state = loadCharacters
                    (player, player_current_state,
                    "src/main/resources/player/player_right1.png",
                    "src/main/resources/player/player_right2.png",
                    "src/main/resources/player/player_right3.png",
                    "src/main/resources/player/player_right4.png");
            }
            else if (this.keyCode == 40) {
                player_current_state = loadCharacters
                    (player, player_current_state,
                    "src/main/resources/player/player1.png",
                    "src/main/resources/player/player2.png",
                    "src/main/resources/player/player3.png",
                    "src/main/resources/player/player4.png");
            }
            last_key_code = this.keyCode;
            player_move_interval = player_elapsed_time;
        }

        // Printing the timer on the screen & updating more elapsed times
        String str_time = String.valueOf(180-current_time);
        text(String.format(str_time, "%.2f"), 300, 44);
        text(lives, 172, 44);
        finish_time = System.currentTimeMillis();
        elapsed_time = (finish_time-start_time)/1000;
        player_elapsed_time = finish_time-start_time;
        enemy_elapsed_time = finish_time-start_time;

        // Giving the enemies their gif-like animations & timing
        // their movements
        if ((enemy_elapsed_time - enemy_image_interval) >= 200) {
            if ((enemy_elapsed_time - enemy_move_interval) >= 800) {
                yellow_can_move = false;
                red_can_move = false;
                // Checking whether they have to change direction
                while (!yellow_can_move) {
                    if (checkCollision
                        (yellow_enemy, yellow_direction)) {
                        break;
                    }
                    if (yellow_direction == 40) {
                        yellow_direction = 36;
                    }
                    yellow_direction += 1;
                }
                while (!red_can_move) {
                    if (checkCollision
                        (red_enemy, red_direction)) {
                        break;
                    }
                    red_direction = red_turn.nextInt(4)+37;
                }
                // Moving in a given direction
                if (yellow_direction == 37) {
                    yellow_enemy.moveLeft();
                }
                else if (yellow_direction == 38) {
                    yellow_enemy.moveUp();
                }
                else if (yellow_direction == 39) {
                    yellow_enemy.moveRight();
                }
                else {
                    yellow_enemy.moveDown();
                }
                if (red_direction == 37) {
                    red_enemy.moveLeft();
                }
                else if (red_direction == 38) {
                    red_enemy.moveUp();
                }
                else if (red_direction == 39) {
                    red_enemy.moveRight();
                }
                else {
                    red_enemy.moveDown();
                }
                yellow_enemy.move();
                red_enemy.move();
                enemy_move_interval = enemy_elapsed_time;
            }
            // Updating their animations accordingly
            if (yellow_direction == 37) {
                yellow_current_state = loadCharacters
                    (yellow_enemy, yellow_current_state,
                    "src/main/resources/yellow_enemy/yellow_left1.png",
                    "src/main/resources/yellow_enemy/yellow_left2.png",
                    "src/main/resources/yellow_enemy/yellow_left3.png",
                    "src/main/resources/yellow_enemy/yellow_left4.png");
            }
            else if (yellow_direction == 38) {
                yellow_current_state = loadCharacters
                    (yellow_enemy, yellow_current_state,
                    "src/main/resources/yellow_enemy/yellow_up1.png",
                    "src/main/resources/yellow_enemy/yellow_up2.png",
                    "src/main/resources/yellow_enemy/yellow_up3.png",
                    "src/main/resources/yellow_enemy/yellow_up4.png");
            }
            else if (yellow_direction == 39) {
                yellow_current_state = loadCharacters
                    (yellow_enemy, yellow_current_state,
                    "src/main/resources/yellow_enemy/yellow_right1.png",
                    "src/main/resources/yellow_enemy/yellow_right2.png",
                    "src/main/resources/yellow_enemy/yellow_right3.png",
                    "src/main/resources/yellow_enemy/yellow_right4.png");
            }
            else {
                yellow_current_state = loadCharacters
                    (yellow_enemy, yellow_current_state,
                    "src/main/resources/yellow_enemy/yellow_down1.png",
                    "src/main/resources/yellow_enemy/yellow_down2.png",
                    "src/main/resources/yellow_enemy/yellow_down3.png",
                    "src/main/resources/yellow_enemy/yellow_down4.png");
            }
            if (red_direction == 37) {
                red_current_state = loadCharacters
                    (red_enemy, red_current_state,
                    "src/main/resources/red_enemy/red_left1.png",
                    "src/main/resources/red_enemy/red_left2.png",
                    "src/main/resources/red_enemy/red_left3.png",
                    "src/main/resources/red_enemy/red_left4.png");
            }
            else if (red_direction == 38) {
                red_current_state = loadCharacters
                    (red_enemy, red_current_state,
                    "src/main/resources/red_enemy/red_up1.png",
                    "src/main/resources/red_enemy/red_up2.png",
                    "src/main/resources/red_enemy/red_up3.png",
                    "src/main/resources/red_enemy/red_up4.png");
            }
            else if (red_direction == 39) {
                red_current_state = loadCharacters
                    (red_enemy, red_current_state,
                    "src/main/resources/red_enemy/red_right1.png",
                    "src/main/resources/red_enemy/red_right2.png",
                    "src/main/resources/red_enemy/red_right3.png",
                    "src/main/resources/red_enemy/red_right4.png");
            }
            else {
                red_current_state = loadCharacters
                    (red_enemy, red_current_state,
                    "src/main/resources/red_enemy/red_down1.png",
                    "src/main/resources/red_enemy/red_down2.png",
                    "src/main/resources/red_enemy/red_down3.png",
                    "src/main/resources/red_enemy/red_down4.png");
            }
            enemy_image_interval = enemy_elapsed_time;
        }
        // Checking if player has advanced to the next level
        if (((player.getX() == finish.getX()) &&
            (player.getY()+16 == finish.getY())) &&
            !(level == (levels.size()-1))) {
            win = true;
            level += 1;
            clearMap();
            setup();
        }
    }

    /**
    * Checking for a key input to move the player.
    */
    public void keyReleased() {
        int key_code = this.keyCode;
        if (!this.keyPressed) {
            can_move = true;
            can_move = checkCollision(player, key_code);
            if (can_move) {
                if (key_code == 37) {
                    this.player.pressLeft();
                } else if (key_code == 39) {
                    this.player.pressRight();
                } else if (key_code == 38) {
                    this.player.pressUp();
                } else if (key_code == 40) {
                    this.player.pressDown();
                }
            }
            if (key_code == 32) {
                place_bomb = true;
            }
        }
    }


    /**
    * Running the application.
    * @param args Command line arguments.
    */
    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
