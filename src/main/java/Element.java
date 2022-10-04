import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Element {
    private Position position;
    Element(int newX, int newY) {
        position = new Position(newX, newY);
    }

    //getters and setters
    public Position getPosition(){
        return position;
    }
    public void setPosition(Position pos){ this.position = pos;}
    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }
    public void setX(int newX) {
        position.setX(newX);
    }
    public void setY(int newY) {
        position.setX(newY);
    }

    public void draw(TextGraphics s){
    }
}
