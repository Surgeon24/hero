package common;

import arenas.Arena1;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Mainbar {
    Mainbar(){}
    public void draw(Integer score, TextGraphics s){
        s.setForegroundColor(TextColor.Factory.fromString("#999999"));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(70, 0), "Score: " + score.toString());
    }
}
