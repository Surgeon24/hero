import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall {

    private Position position;

    public Wall(int newX, int newY) {
        position = new Position(newX, newY);
    }
    //getters and setters

    public void setPosition(Position pos){
        this.position = pos;
    }

    public Position getPosition(){
        return position;
    }

    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString("#333333"));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(this.position.getX(), this.position.getY()), " ");
    }
}
