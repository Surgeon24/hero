package arenas;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import common.MetaInf;
import elements.*;
import java.util.ArrayList;
import java.util.List;

import static common.Globals.length;
import static common.Globals.width;


public class Arena0 {
    public String floorColor = "#696969";
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;
    private Door door;
    public Hero hero = new Hero(4, 7);
    public Arena0(){
        walls = createWalls();
        coins = createCoins();
        monsters = createMonsters();
        door = new Door(78,22);

    }

    public List<Wall> getWalls(){ return walls;}
    public List<Coin> getCoins(){ return coins;}
    public List<Monster> getMonsters(){ return monsters;}
    public Door getDoor(){ return door;}

    public MetaInf getAll(){
        MetaInf meta = new MetaInf(hero.getPosition(), walls, coins, monsters, door);
        return meta;
    }
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
        for (int w = 8; w <= 12; w++) {
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
        ArrayList<Coin> coins = new ArrayList<>();
        coins.add(new Coin(50, 22));
        return coins;
    }

    private List<Monster> createMonsters() {
        ArrayList<Monster> monsters = new ArrayList<>();
        return monsters;
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
