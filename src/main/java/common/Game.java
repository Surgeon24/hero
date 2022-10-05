package common;

import arenas.Arena0;
import arenas.Arena1;
import elements.Monster;
import elements.Position;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;
import java.io.IOException;
import java.util.List;

public class Game {
    private static Screen screen;
    private boolean runGame = true;
    //allows us to draw figures and change bg color
    Arena0 arena0= new Arena0(80, 23);
    Arena1 arena1 = new Arena1(80, 23);
    Mainbar mainbar = new Mainbar();
    public Integer score = 0;
    private int step = 1;
    int option = 0;

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
            if (option == 0)
                arena0.draw(screen.newTextGraphics());
            if (option == 1)
                arena1.draw(screen.newTextGraphics());
            mainbar.draw(score+ arena0.score+arena1.score, screen.newTextGraphics());
            screen.refresh();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            MainMenu mainMenu = new MainMenu();
            option = mainMenu.showMenu(screen);
            while (runGame) {
                draw();
                if (verifyMonsterCollisions(arena1.getMonsters(), arena1.hero.getPosition()))
                    endScreen();
                KeyStroke key = screen.readInput();
                processKey(key);
            }
            screen.close() ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveHero(Position position) {
        if (arena1.canHeroMove(position)) {
            arena1.hero.setPosition(position);
            arena1.retrieveCoins(position);
            if (verifyMonsterCollisions(arena1.getMonsters(),
                    arena1.hero.getPosition())) { endScreen();}
            moveMonsters(arena1.getMonsters());
        }
    }

    private void moveMonsters(List<Monster> monsters) {
        for (Monster monster : monsters)
            monster.move(arena1);
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
        System.out.println("x: " + arena1.hero.getX() + "\ny: " + arena1.hero.getY());
        System.out.println("Score: " + score);
        switch (key.getKeyType()){
            case EOF -> runGame = false;
            case ArrowUp -> moveHero(arena1.hero.moveUp());
            case ArrowDown -> moveHero(arena1.hero.moveDown());
            case ArrowLeft -> moveHero(arena1.hero.moveLeft());
            case ArrowRight -> moveHero(arena1.hero.moveRight());
            case Character -> {
                switch (key.getCharacter()){
                    case 'q' -> runGame = false;
                }
            }
        }
    }

    private void endScreen(){
        System.out.println("You lose! Your score is: " + score);
        System.out.println("Press any button to quit.");
        try{ KeyStroke key = screen.readInput();}
        catch (IOException e){e.printStackTrace();}
        runGame = false;
    }
}
