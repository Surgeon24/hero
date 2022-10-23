package elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Door extends Element{
    public Boolean open = true;
    private String closedDoorColor = "#125678";
    private String openedDoorColor = "#166678";

    public Door(int newX, int newY) { super(newX, newY);}

    public void draw(TextGraphics s){
        if (open)
            s.setBackgroundColor(TextColor.Factory.fromString(openedDoorColor));
        else
            s.setBackgroundColor(TextColor.Factory.fromString(closedDoorColor));
        s.putString(new TerminalPosition(getX(), getY()), " ");
    }
}
