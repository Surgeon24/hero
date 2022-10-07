package common;

import arenas.Arena;
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
    Arena arena = new Arena();
    Arena0 arena0= new Arena0();
    Arena1 arena1 = new Arena1();
    Mainbar mainbar = new Mainbar();
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
            arena.draw(screen.newTextGraphics());
            mainbar.draw(arena.score, screen.newTextGraphics());
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
            if (option == 0)
                arena.createAll(arena0.getAll());
            if (option == 1)
                arena.createAll(arena1.getAll());
            while (runGame) {
                draw();
                if (verifyMonsterCollisions(arena.getMonsters(), arena.hero.getPosition()))
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
        if (arena.canHeroMove(position)) {
            arena.hero.setPosition(position);
            arena.retrieveCoins(position);
            if (verifyMonsterCollisions(arena.getMonsters(),
                    arena.hero.getPosition())) { endScreen();}
            moveMonsters(arena.getMonsters());

            if (arena.nextArena()){
                System.out.println("True was returned!\n");
                arena.clearAll();
                arena.createAll(arena1.getAll());
            }
        }
    }

    private void moveMonsters(List<Monster> monsters) {
        for (Monster monster : monsters)
            monster.move(arena);
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
