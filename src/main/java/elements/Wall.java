package elements;

import arenas.Arena1;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element {
    private String wallColor = "#0d0a02";

    public Wall(int newX, int newY) { super(newX, newY);}

    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(wallColor));
        s.putString(new TerminalPosition(getX(), getY()), " ");
    }
}
