package arenas;
import common.MetaInf;
import elements.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static common.Globals.length;
import static common.Globals.width;


public class Arena1 {
    private String floorColor = "#696969";
    private List<Wall> walls;
    private List<Coin> coins;

    private List<Monster> monsters;
    private List<String> text;
    private Door door;
    public Hero hero = new Hero(2, 20);
    public Arena1(){
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
            if (l <= 14 || (l>=21 && l<=34) || (l>=41 && l<=54) || l >= 61) {
                walls.add(new Wall(l, 9));
                walls.add(new Wall(l, 10));
            }
            if (l>=13 && l<= 62) {
                walls.add(new Wall(l, 5));
                walls.add(new Wall(l, 6));
            }
        }
        for (int w = 0; w < width; w++) {
            if (w>6 && w<9){
                walls.add(new Wall(13,w));
                walls.add(new Wall(14,w));
                walls.add(new Wall(61,w));
                walls.add(new Wall(62,w));
            }
            if (w>10 && w<21){
                walls.add(new Wall(27,w));
                walls.add(new Wall(28,w));
                walls.add(new Wall(67,w));
                walls.add(new Wall(68,w));
            }
            if (w>14){
                walls.add(new Wall(7,w));
                walls.add(new Wall(8,w));
                walls.add(new Wall(47,w));
                walls.add(new Wall(48,w));
            }
        }
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        coins.add(new Coin(28, 8));
        coins.add(new Coin(48, 8));
        coins.add(new Coin(28, 22));
        coins.add(new Coin(44, 21));
        coins.add(new Coin(32, 13));
        coins.add(new Coin(48, 12));
        return coins;
    }

    private List<Monster> createMonsters() {
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(18,7));
        monsters.add(new Monster(38,7));
        monsters.add(new Monster(58,7));
        monsters.add(new Monster(18,18));
        monsters.add(new Monster(38,18));
        monsters.add(new Monster(58,18));
        return monsters;
    }
    private List<String> createText(){
        ArrayList<String> text = new ArrayList<>();
        text.add("Dodge the spiders! Maybe, they are not so smart, but they're dangerous...");
        return text;
    }
}
