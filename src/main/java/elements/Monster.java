package elements;

import arenas.Arena1;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element {
    String monsterColor = "#c4722f";
    private final int step = 1;
    public Monster(int newX, int newY) { super(newX, newY);}

    public Position move(Arena1 a){
        Random random = new Random();
        switch (random.nextInt(5)){
            case 1 -> {
                if (a.canMonsterMove(new Position(this.getX(), this.getY()-1))){
                    moveUp();
                }
            }
            case 2 -> {
                if (a.canMonsterMove(new Position(this.getX(), this.getY()+1))) {
                    moveDown();
                }
            }
            case 3 -> {
                if (a.canMonsterMove(new Position(this.getX()-1, this.getY()))) {
                    moveLeft();
                }
            }
            case 4 -> {
                if (a.canMonsterMove(new Position(this.getX()+1, this.getY()))) {
                    moveRight();
                }
            }
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


    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(floorColor));
        s.setForegroundColor(TextColor.Factory.fromString(monsterColor));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(getX(), getY()), "W");
    }
}
