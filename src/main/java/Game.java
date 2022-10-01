import com.googlecode.lanterna.TextCharacter;
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

    Hero hero = new Hero(10, 10); //create Hero
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
            hero.draw(screen);
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

    private void processKey (KeyStroke key){
        //Check, if window has been closed
        if (key.getKeyType() == EOF)
            runGame = false;
        //Check arrows
        System.out.println(key);
        switch (key.getKeyType()){
            case EOF -> runGame = false;
            case ArrowUp -> hero.moveUp(step);
            case ArrowDown -> hero.moveDown(step);
            case ArrowLeft -> hero.moveLeft(step);
            case ArrowRight -> hero.moveRight(step);
            case Character -> {
                switch (key.getCharacter()){
                    case 'q' -> runGame = false;
                    case 'x' -> step += 1;
                    case 'z' -> {if (step > 1) step -= 1;}
                }
            }
        }
    }
}
