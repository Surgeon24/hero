package common;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.EOF;

public class MainMenu {
    private Boolean check = true;
    private Boolean press = false;
    int options = 1;
/*
    public void showMenu(Screen s) {
        while (check) {
            draw(s.newTextGraphics());
            try{
                s.clear();
                KeyStroke key = s.readInput();
                processKey(key);
                s.refresh();
            }
            catch (IOException e){e.printStackTrace();}
        }
    }*/

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
        return options;
    }
    public void draw(TextGraphics s) {
        s.setBackgroundColor(TextColor.Factory.fromString("#117491"));
        s.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(80, 25), ' ');
        s.enableModifiers(SGR.BOLD);
        s.setForegroundColor(TextColor.Factory.fromString("#ede9dd"));
        s.putString(new TerminalPosition(30, 10), "HERO v0.2");
        if (options == 0) s.setForegroundColor(TextColor.Factory.fromString("#ede9dd"));
        else s.setForegroundColor(TextColor.Factory.fromString("#968e5a"));
        s.putString(new TerminalPosition(30, 13), "START LEVEL 1");
        if (options == 1) s.setForegroundColor(TextColor.Factory.fromString("#ede9dd"));
        else s.setForegroundColor(TextColor.Factory.fromString("#968e5a"));
        s.putString(new TerminalPosition(30, 16), "START LEVEL 2");
    }

    private void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case EOF -> check = false;
            case ArrowUp -> options = (options-1) % 2;
            case ArrowDown -> options = (options+1) % 2;
            case Enter -> {
                if(options == 0) check = false;
                else if (options == 1) check = false;
            }
        }
    }

}