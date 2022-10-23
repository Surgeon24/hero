package common;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.*;

public class LoseScreen {
    private Boolean check = true;
    private Integer score;
    private Integer highScore;
    File scoreFile = new File("highScore.dat");

    public void showLoseScreen(Screen screen, int s) {
        score = s;
        highScore = getHighScore();
        try {
            while (check == true) {
                screen.clear();
                draw(screen.newTextGraphics());
                screen.refresh();
                KeyStroke key = screen.readInput();
                processKey(key);
            }
            check = true;
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private int getHighScore(){
        try{
            Integer output;
            FileReader readFile = new FileReader(scoreFile);
            BufferedReader reader = new BufferedReader(readFile);
            output = Integer.parseInt(reader.readLine());
            reader.close();
            return output;
        }
        catch (Exception e){
            return 0;
        }
    }

    private void putHighScore(Integer newHighScore){
        if(!scoreFile.exists()) {
            try {
                scoreFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter writeFile = new FileWriter(scoreFile);
            BufferedWriter writer = new BufferedWriter(writeFile);
            writer.write(score.toString());
            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(TextGraphics s) {
        s.enableModifiers(SGR.BOLD);
        s.setForegroundColor(TextColor.Factory.fromString("#ede9dd"));
        s.putString(new TerminalPosition(20, 10), "You died! Your score: " + score);
        if (score > highScore) {
            s.putString(new TerminalPosition(20, 12), "This is your new high score!");
            putHighScore(score);
        }
        else
            s.putString(new TerminalPosition(20, 12), "Your high score is " + highScore);
        s.putString(new TerminalPosition(20, 14), "Press Enter to go to the main menu.");

    }

    private void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case EOF -> check = false;
            case Enter -> check = false;
        }
    }

}
