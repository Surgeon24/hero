import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{
    String monsterColor = "#666666";
    private final int step = 1;
    public Monster(int newX, int newY) { super(newX, newY);}

    public Position move(){
        Random random = new Random();
        switch (random.nextInt(4)){
            case 1 -> {return moveUp();}
            case 2 -> {return moveDown();}
            case 3 -> {return moveLeft();}
            case 4 -> {return moveRight();}
        }
        return getPosition();
    }
    //moves
    public Position moveUp(){
        return new Position(getX(), getY() - step);
    }
    public Position moveDown(){
        return new Position(getX(), getY() + step);
    }
    public Position moveLeft(){
        return new Position(getX() - step, getY());
    }
    public Position moveRight(){ return new Position(getX() + step, getY());}


    public void draw(Arena a, TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(a.floorColor));
        s.setForegroundColor(TextColor.Factory.fromString(monsterColor));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(getX(), getY()), "W");
    }
}
