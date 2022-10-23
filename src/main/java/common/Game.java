package common;

import arenas.*;
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
    private boolean runGame;
    Arena arena = new Arena();

    Arena0 arena0= new Arena0();
    Arena1 arena1 = new Arena1();
    Arena2 arena2 = new Arena2();
    Arena3 arena3 = new Arena3();
    Mainbar mainbar = new Mainbar();
    MainMenu mainMenu = new MainMenu();
    LoseScreen loseScreen = new LoseScreen();
    private int step = 1;
    int option = -1;

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
                runGame = true;
                while (runGame) {
                    if (option == -1){
                        option = mainMenu.showMenu(screen);
                        System.out.println("game-run-option -1 occurred\n");
                        switch (option){
                            case 0 -> arena.createAll(arena0.getAll());
                            case 1 -> arena.createAll(arena1.getAll());
                            case 2 -> arena.createAll(arena2.getAll());
                        }
                    }
                    draw();
                    if (verifyMonsterCollisions(arena.getMonsters(), arena.hero.getPosition()))
                        endScreen();
                    KeyStroke key = screen.readInput();
                    processKey(key);
                }
                screen.close();
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
                option ++;
                switch (option){
                    case 1 -> arena.createAll(arena1.getAll());
                    case 2 -> arena.createAll(arena2.getAll());
                    case 3 -> arena.createAll(arena3.getAll());
                }
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
                    case 'm' -> {arena.score = 0; option = -1;}
                }
            }
        }
    }

    private void endScreen(){
        loseScreen.showLoseScreen(screen, arena.score);
        option = -1;
        arena.score = 0;
    }
}
