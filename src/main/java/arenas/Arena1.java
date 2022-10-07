package arenas;
import common.Game;
import common.MetaInf;
import elements.*;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static common.Globals.length;
import static common.Globals.width;


public class Arena1 {
    private String floorColor = "#696969";
    public Integer score = 0;
    private List<Wall> walls;
    private List<Coin> coins;

    private List<Monster> monsters;
    private List<String> text;
    private Door door;
    public Hero hero = new Hero(10, 10);
    public Arena1(){
        walls = createWalls();
        coins = createCoins();
        monsters = createMonsters();
        text = createText();
        door = new Door(70,22);
    }

    public int getLength(){ return length;}
    public int getWidth(){ return width;}
    public List<Wall> getWalls(){ return walls;}
    public List<Coin> getCoins(){ return coins;}
    public List<Monster> getMonsters(){ return monsters;}
    public Door getDoor(){ return door;}
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
            walls.add(new Wall(l, 5));
            walls.add(new Wall(l, 15));
        }
        for (int w = 6; w < 9; w++) {
            walls.add(new Wall(12, w));
            walls.add(new Wall(24, w+6));
            walls.add(new Wall(40, w));
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
    private List<String> createText(){
        ArrayList<String> text = new ArrayList<>();
        text.add("Dodge the spiders and collect all the coins to open another room.");
        return text;
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

        tGraphic.setForegroundColor(TextColor.Factory.fromString(hero.getColor()));
        tGraphic.putString(new TerminalPosition(5, 3), "Dodge the spiders and collect all the coins to open another room.");

    }
}
