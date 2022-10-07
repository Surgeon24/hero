package arenas;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import elements.*;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private String floorColor = "#696969";
    public Hero hero = new Hero(10, 10);
    private int length;
    private int width;
    public Integer score = 0;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public Arena(){}
    public int getLength(){ return length;}
    public int getWidth(){ return width;}

    public List<Wall> getWalls(){ return walls;}
    public List<Coin> getCoins(){ return coins;}
    public List<Monster> getMonsters(){ return monsters;}

    public void createAll(int l, int w, List<Wall> newWalls, List<Coin> newCoin, List<Monster> newMonsters){
        this.length = l;
        this.width = w;
        this.walls = createWalls( newWalls);
        this.coins = createCoins(newCoin);
        this.monsters = createMonsters(newMonsters);
    }

    private List<Wall> createWalls(List<Wall> newWalls) {
        List<Wall> walls = new ArrayList<>();
        walls.addAll(newWalls);
        return walls;
    }

    private List<Coin> createCoins(List<Coin> newCoins) {
        ArrayList<Coin> coins = new ArrayList<>();
        coins.addAll(newCoins);
        return coins;
    }

    private List<Monster> createMonsters(List<Monster> newMonsters) {
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.addAll(newMonsters);
        return monsters;
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

    public void draw(TextGraphics tGraphic){
        tGraphic.setBackgroundColor(TextColor.Factory.fromString(floorColor));
        tGraphic.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(length, width), ' ');
        hero.draw(tGraphic);
        for (Wall wall : walls)
            wall.draw(tGraphic);
        for (Coin coin : coins)
            coin.draw( tGraphic);
        for (Monster monster : monsters)
            monster.draw(tGraphic);
    }
}
