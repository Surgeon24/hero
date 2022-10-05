package elements;

import arenas.Arena1;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element {
    String coinColor = "#66a834";
    public Coin(int newX, int newY) { super(newX, newY);}

    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(floorColor));
        s.setForegroundColor(TextColor.Factory.fromString(coinColor));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(getX(), getY()), "o");
    }
}
