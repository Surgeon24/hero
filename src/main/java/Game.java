import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

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
                if (verifyMonsterCollisions(arena.getMonsters(),
                        arena.hero.getPosition())) { endScreen();}
                KeyStroke key = screen.readInput();
                processKey(key);
            }
            screen.close() ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveHero(Position position) {
        if (arena.canHeroMove(position)) {
            arena.hero.setPosition(position);
            arena.retrieveCoins(position);
            if (verifyMonsterCollisions(arena.getMonsters(),
                    arena.hero.getPosition())) { endScreen();}
            moveMonsters(arena.getMonsters());
        }
    }

    private void moveMonsters(List<Monster> monsters) {
        for (Monster monster : monsters)
            monster.move();
    }

    private Boolean verifyMonsterCollisions(List<Monster> monsters, Position pos){
        for (Monster monster : monsters){
            if (monster.getPosition().equals(pos)) {
                return true;
            }
        }
        return false;
    }

    private void processKey (KeyStroke key){
        //Check, if window has been closed
        if (key.getKeyType() == EOF)
            runGame = false;
        //Check arrows
        System.out.println("x: " + arena.hero.getX() + "\ny: " + arena.hero.getY());
        System.out.println("Score: " + arena.score);
        switch (key.getKeyType()){
            case EOF -> runGame = false;
            case ArrowUp -> moveHero(arena.hero.moveUp());
            case ArrowDown -> moveHero(arena.hero.moveDown());
            case ArrowLeft -> moveHero(arena.hero.moveLeft());
            case ArrowRight -> moveHero(arena.hero.moveRight());
            case Character -> {
                switch (key.getCharacter()){
                    case 'q' -> runGame = false;
                }
            }
        }
    }

    private void endScreen(){
        System.out.println("You lose! Your score is: " + arena.score);
        System.out.println("Press any button to quit.");
        try{ KeyStroke key = screen.readInput();}
        catch (IOException e){e.printStackTrace();}
        runGame = false;
    }
}
