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

public class Arena {

    private String floorColor = "#696969";
    public Hero hero = new Hero(0,0);
    public Integer score = 0;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;
    private List<String> text;
    private Door door;

    public Arena(){}

    public List<Wall> getWalls(){ return walls;}
    public List<Coin> getCoins(){ return coins;}
    public List<Monster> getMonsters(){ return monsters;}
    public Door getDoor(){ return door;}

    public void createAll(MetaInf inf){
        hero.setPosition(inf.pos);
        walls = createWalls( inf.walls);
        coins = createCoins(inf.coins);
        monsters = createMonsters(inf.monsters);
        text = inf.text;
        door = inf.door;
    }

    public void clearAll(){
        walls.removeAll(walls);
        coins.removeAll(coins);
        monsters.removeAll(monsters);
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
                if (coins.isEmpty()) {
                    door.open = true;
                    System.out.println("Door was open!\n");
                }
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

    public Boolean nextArena(){
        if (door.getPosition().equals(hero.getPosition()) && door.open)
            return true;
        return false;
    }

    public void draw(TextGraphics tGraphics){

        tGraphics.setBackgroundColor(TextColor.Factory.fromString(floorColor));
        tGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(length, width), ' ');
        hero.draw(tGraphics);
        for (Wall wall : walls)
            wall.draw(tGraphics);
        for (Coin coin : coins)
            coin.draw( tGraphics);
        for (Monster monster : monsters)
            monster.draw(tGraphics);
        door.draw(tGraphics);
        tGraphics.setBackgroundColor(TextColor.Factory.fromString(floorColor));
        tGraphics.setForegroundColor(TextColor.Factory.fromString("#bbbbbb"));
        int i = 1;
        for (String t : text) {
            tGraphics.putString(new TerminalPosition(5, 2*i), t);
            i++;
        }
    }
}
