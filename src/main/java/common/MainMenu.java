package common;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import static common.Globals.length;
import static common.Globals.width;

public class MainMenu {
    private Boolean check = true;
    private int options = 1;

    public int showMenu(Screen screen) {
        try {
            while (check == true) {
                screen.clear();
                draw(screen.newTextGraphics());
                screen.refresh();
                KeyStroke key = screen.readInput();
                processKey(key);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        check = true;
        return options;
    }
    public void draw(TextGraphics s) {
        s.setBackgroundColor(TextColor.Factory.fromString("#117491"));
        s.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(length, width), ' ');
        s.enableModifiers(SGR.BOLD);
        s.setForegroundColor(TextColor.Factory.fromString("#ede9dd"));
        s.putString(new TerminalPosition(30, 10), "HERO v0.2");
        if (options == 0) s.setForegroundColor(TextColor.Factory.fromString("#ede9dd"));
        else s.setForegroundColor(TextColor.Factory.fromString("#968e5a"));
        s.putString(new TerminalPosition(30, 13), "START LEVEL 1");
        if (options == 1) s.setForegroundColor(TextColor.Factory.fromString("#ede9dd"));
        else s.setForegroundColor(TextColor.Factory.fromString("#968e5a"));
        s.putString(new TerminalPosition(30, 16), "START LEVEL 2");
        if (options == 2) s.setForegroundColor(TextColor.Factory.fromString("#ede9dd"));
        else s.setForegroundColor(TextColor.Factory.fromString("#968e5a"));
        s.putString(new TerminalPosition(30, 19), "START LEVEL 3");
    }

    private void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case EOF -> check = false;
            case ArrowUp -> options = (options-1) % 3;
            case ArrowDown -> options = (options+1) % 3;
            case Enter -> check = false;
        }
    }

}