import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import javax.swing.*;
import java.io.EOFException;
import java.io.IOException;
import java.net.SocketOption;

import static com.googlecode.lanterna.input.KeyType.Delete;
import static com.googlecode.lanterna.input.KeyType.EOF;

public class Game {
    private static Screen screen;
    private boolean runGame = true;
    //allows us to draw figures and change bg color

    Arena arena = new Arena(60, 20);
    private int step = 1;

    public Game() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void draw() {
        try {
            screen.clear();
            arena.draw(screen.newTextGraphics());
            screen.refresh();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (runGame) {
                draw();
                KeyStroke key = screen.readInput();
                processKey(key);
            }
            screen.close() ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveHero(Position position) {
        if (arena.canHeroMove(position))
            arena.hero.setPosition(position);
    }

    private void processKey (KeyStroke key){
        //Check, if window has been closed
        if (key.getKeyType() == EOF)
            runGame = false;
        //Check arrows
        System.out.println("x: " + arena.hero.getX() + "\ny: " + arena.hero.getY());
        switch (key.getKeyType()){
            case EOF -> runGame = false;
            case ArrowUp -> moveHero(arena.hero.moveUp());
            case ArrowDown -> moveHero(arena.hero.moveDown());
            case ArrowLeft -> moveHero(arena.hero.moveLeft());
            case ArrowRight -> moveHero(arena.hero.moveRight());
            case Character -> {
                switch (key.getCharacter()){
                    case 'q' -> runGame = false;
                    case 'x' -> arena.hero.increaseStep();
                    case 'z' -> {if (arena.hero.getStep() > 1) arena.hero.decreaseStep();}
                }
            }
        }
    }
}
