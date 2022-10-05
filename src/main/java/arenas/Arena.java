package arenas;

import com.googlecode.lanterna.graphics.TextGraphics;
import elements.Coin;
import elements.Monster;
import elements.Position;
import elements.Wall;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int length;
    private int width;
    public Integer score = 0;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public Arena(){
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    public int getLength(){ return length;}
    public int getWidth(){ return width;}
    public List<Monster> getMonsters(){ return monsters;}

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        //main walls
        for (int l = 0; l < length; l++) {
            walls.add(new Wall(l, 0));
            walls.add(new Wall(l, width));
        }
        for (int w = 1; w < width; w++) {
            walls.add(new Wall(0, w));
            walls.add(new Wall(length-1, w));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        ArrayList<Coin> coins = new ArrayList<>();
        return coins;}

    private List<Monster> createMonsters() {
        ArrayList<Monster> monsters = new ArrayList<>();
        return monsters;}

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

    public boolean canMonsterMove(Position pos){
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

    public void draw(TextGraphics tGraphic){}
}
