package elements;

import arenas.Arena1;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends Element {
    private final int step = 1;
    private final String heroColor = "#2d94cc";

    public Hero(int newX, int newY){
        super(newX, newY);
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
    public int getStep(){ return step;}

    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(floorColor));
        s.setForegroundColor(TextColor.Factory.fromString(heroColor));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(getX(), getY()), "X");
    }

}
