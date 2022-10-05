package arenas;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import elements.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Arena0 {
    //colors
    public String floorColor = "#696969";
    public String wallColor = "#0d0a02";
    private int length;
    private int width;
    public Integer score = 0;
    private List<Wall> walls;
    private List<Coin> coins;

    private List<Monster> monsters;
    public Hero hero = new Hero(10, 10);
    public Arena0(int l, int w){
        length = l;
        width = w;
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
        //additional walls
        for (int l = 0; l < length; l++) {
            walls.add(new Wall(l, 5));
        }
        for (int l = 0; l < length-5; l++) {
            walls.add(new Wall(l, 8));
            walls.add(new Wall(l, 12));
        }
        for (int w = 8; w < 12; w++) {
            walls.add(new Wall(length-5, w));
            walls.add(new Wall(length-5, w));
        }
        //
        for (int l = 5; l < length; l++) {
            walls.add(new Wall(l, 16));
            walls.add(new Wall(l, 20));
        }
        for (int w = 16; w < 20; w++) {
            walls.add(new Wall(5, w));
            walls.add(new Wall(5, w));
        }

        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        coins.add(new Coin(15, 7));
        coins.add(new Coin(30, 7));
        coins.add(new Coin(45, 10));
        coins.add(new Coin(30, 13));
        coins.add(new Coin(15, 13));
        return coins;
    }

    private List<Monster> createMonsters() {
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(17,6));
        monsters.add(new Monster(30,10));
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
        hero.draw( tGraphic);
        for (Wall wall : walls)
            wall.draw( tGraphic);
        for (Coin coin : coins)
            coin.draw( tGraphic);
        for (Monster monster : monsters)
            monster.draw(tGraphic);
        tGraphic.setForegroundColor(TextColor.Factory.fromString(hero.getColor()));
        tGraphic.putString(new TerminalPosition(25, 3), "Your path will be full of dangers, but the reward is worth it.");
        if (hero.getY() > 10)
            tGraphic.putString(new TerminalPosition(25, 3), "You are a proud X who wanted to feel the wind of change.");
        if (hero.getY() > 15)
            tGraphic.putString(new TerminalPosition(25, 3), "Are you actually ready?");


    }
}
