import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{
    String monsterColor = "#c4722f";
    private final int step = 1;
    public Monster(int newX, int newY) { super(newX, newY);}

    public Position move(){
        Random random = new Random();
        switch (random.nextInt(4)){
            case 1 -> {System.out.println("\n1"); moveUp();}
            case 2 -> {System.out.println("\n2"); moveDown();}
            case 3 -> {System.out.println("\n3"); moveLeft();}
            case 4 -> {System.out.println("\n4"); moveRight();}
        }
        return getPosition();
    }
    //moves
    public void moveUp(){
        setPosition(new Position(getX(), getY()-step));
    }
    public void moveDown(){
        setPosition(new Position(getX(), getY() + step));
    }
    public void moveLeft(){
        setPosition(new Position(getX() - step, getY()));
    }
    public void moveRight(){
        setPosition(new Position(getX() + step, getY()));
    }


    public void draw(Arena a, TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(a.floorColor));
        s.setForegroundColor(TextColor.Factory.fromString(monsterColor));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(getX(), getY()), "W");
    }
}
