package arenas;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import common.MetaInf;
import elements.*;
import java.util.ArrayList;
import java.util.List;

import static common.Globals.length;
import static common.Globals.width;


public class Arena0 {
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;
private List<String> text;
    private Door door;
    public Hero hero = new Hero(4, 14);
    public Arena0(){
        walls = createWalls();
        coins = createCoins();
        monsters = createMonsters();
        text = createText();
        door = new Door(78,22);

    }

    public MetaInf getAll(){
        MetaInf meta = new MetaInf(hero.getPosition(), walls, coins, monsters, door, text);
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
            walls.add(new Wall(l, 12));
        }
        for (int l = 0; l < length-10; l++) {
            walls.add(new Wall(l, 15));
            walls.add(new Wall(l, 16));
        }

        for (int l = 10; l < length; l++) {
            walls.add(new Wall(l, 19));
            walls.add(new Wall(l, 20));
        }

        return walls;
    }

    private List<Coin> createCoins() {
        ArrayList<Coin> coins = new ArrayList<>();
        coins.add(new Coin(20,14));
        coins.add(new Coin(35,18));
        coins.add(new Coin(50, 22));
        return coins;
    }

    private List<Monster> createMonsters() {
        ArrayList<Monster> monsters = new ArrayList<>();
        return monsters;
    }

    private List<String> createText(){
        ArrayList<String> text = new ArrayList<>();
        text.add("You are a proud X who wanted to feel the wind of change.");
        text.add("Your path will be full of dangers, but the reward is worth it.");
        text.add("Are you actually ready?");
        text.add("Prove it! Go to the door, collecting all coins to get a better score.");
        return text;
    }
}
