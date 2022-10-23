package arenas;
import common.MetaInf;
import elements.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static common.Globals.length;
import static common.Globals.width;


public class Arena2 {
    private String floorColor = "#696969";
    private List<Wall> walls;
    private List<Coin> coins;

    private List<Monster> monsters;
    private List<String> text;
    private Door door;
    public Hero hero = new Hero(2, 20);
    public Arena2(){
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
            walls.add(new Wall(l,6));
            if ((l>5 && l<75) && l!=12 && l!=23 && l!=34 && l!=46 && l!=57 && l!=68) {
                walls.add(new Wall(l, 8));
                walls.add(new Wall(l, 21));
            }
            if ((l>8 && l<72) && (l<38 || l>42)){
                walls.add(new Wall(l, 10));
                walls.add(new Wall(l, 19));
            }
        }
        for (int w = 0; w < width; w++) {
            if (w>7 && w<22) {
                walls.add(new Wall(6, w));
                walls.add(new Wall(74, w));
            }
            if (w>9 && w<20){
                walls.add(new Wall(9,w));
                walls.add(new Wall(37,w));
                walls.add(new Wall(43,w));
                walls.add(new Wall(71,w));
            }
            if (w>6 && w!=14 && w!=15)
                walls.add(new Wall(40,w));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        ArrayList<Coin> coins = new ArrayList<>();
        coins.add(new Coin(7, 9));
        coins.add(new Coin(7, 20));
        coins.add(new Coin(23, 20));
        coins.add(new Coin(23, 7));
        coins.add(new Coin(39, 7));
        coins.add(new Coin(39, 22));

        coins.add(new Coin(41, 7));
        coins.add(new Coin(41, 22));
        coins.add(new Coin(57, 8));
        coins.add(new Coin(57, 21));
        coins.add(new Coin(74, 7));
        coins.add(new Coin(74, 22));
        return coins;
    }

    private List<Monster> createMonsters() {
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(8,11));
        monsters.add(new Monster(8,17));
        monsters.add(new Monster(22,7));
        monsters.add(new Monster(24,7));
        monsters.add(new Monster(39,9));
        monsters.add(new Monster(39,20));
        monsters.add(new Monster(49,9));
        monsters.add(new Monster(49,22));
        monsters.add(new Monster(57,7));
        monsters.add(new Monster(57,22));
        monsters.add(new Monster(65,9));
        monsters.add(new Monster(65,22));
        monsters.add(new Monster(72,7));
        monsters.add(new Monster(72,22));
        return monsters;
    }
    private List<String> createText(){
        ArrayList<String> text = new ArrayList<>();
        text.add("... especially in cramped spaces.");
        text.add("The door is open, so, it's not necessary to get all the coins.");
        text.add("There is no shame in being scary of spiders -_-");
        return text;
    }
}
