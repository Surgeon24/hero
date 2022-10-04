import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element{
    String coinColor = "#888888";
    public Coin(int newX, int newY) { super(newX, newY);}

    public void draw(Arena a, TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(a.floorColor));
        s.setForegroundColor(TextColor.Factory.fromString(coinColor));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(getX(), getY()), "o");
    }
}
