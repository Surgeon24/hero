import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Arena {
    private int length;
    private int width;

    Hero hero = new Hero(10, 10); //create Hero

    public Arena(int l, int w){
        length = l;
        width = w;
    }

    public boolean canHeroMove(Position pos){
            if (pos.getX() < length && pos.getX() >= 0 && pos.getY() < width && pos.getY() >= 0)
                return true;
            else
                return false;
    }

    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString("#696969"));
        s.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(length, width), ' ');
        hero.draw(s);

    }
}
