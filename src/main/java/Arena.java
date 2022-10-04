import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    //colors
    public String floorColor = "#696969";
    private int length;
    private int width;
    private List<Wall> walls;
    private List<Coin> coins;
    Hero hero = new Hero(10, 10);
    int score = 0;
    public Arena(int l, int w){
        length = l;
        width = w;
        this.walls = createWalls();
        this.coins = createCoins();

    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int l = 0; l < length; l++) {
            walls.add(new Wall(l, 0));
            walls.add(new Wall(l, width));
        }
        for (int w = 1; w < width; w++) {
            walls.add(new Wall(0, w));
            walls.add(new Wall(length-1, w));
        }
        //additional wall
        for (int w = 15; w < width; w++) {
            walls.add(new Wall(15, w));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(length - 2) + 1,
                    random.nextInt(width - 2) + 1));
        return coins;
    }

    public void retrieveCoins(Position pos){
        for (Coin coin : coins){
            if (coin.getPosition().equals(pos)) {
                coins.remove(coin);
                score ++;
                break;
            }
        }
    }

    public boolean canHeroMove(Position pos){
        if (!checkWall(pos))
            return true;
        return false;
    }



    public boolean checkWall(Position pos){
        for (Wall wall : walls){
            if (wall.getPosition().equals(pos))
                return true;
        }
        return false;
    }

    public void draw(TextGraphics tGraphic){
        tGraphic.setBackgroundColor(TextColor.Factory.fromString(floorColor));
        tGraphic.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(length, width), ' ');
        hero.draw(tGraphic);
        for (Wall wall : walls)
            wall.draw(tGraphic);
        for (Coin coin : coins)
            coin.draw(this, tGraphic);
    }
}
