import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element{

    public Wall(int newX, int newY) { super(newX, newY);}

    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString("#333333"));
        s.putString(new TerminalPosition(getX(), getY()), " ");
    }
}
