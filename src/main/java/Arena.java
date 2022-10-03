import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int length;
    private int width;
    private List<Wall> walls;
    Hero hero = new Hero(10, 10); //create Hero

    public Arena(int l, int w){
        length = l;
        width = w;
        this.walls = createWalls();
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int l = 0; l < length; l++) {
            walls.add(new Wall(l, 0));
            walls.add(new Wall(l, width));
        }
        for (int w = 1; w < width; w++) {
            walls.add(new Wall(0, w));
            walls.add(new Wall(length-1, w));
        }
        //additional wall
        for (int w = 15; w < width; w++) {
            walls.add(new Wall(15, w));
        }
        return walls;
    }
    public boolean canHeroMove(Position pos){
        if (!checkWall(pos))
            return true;
        return false;
    }



    public boolean checkWall(Position pos){
        for (Wall wall : walls){
            if (wall.getPosition().equals(pos))
                return true;
        }
        return false;
    }

    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString("#696969"));
        s.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(length, width), ' ');
        hero.draw(s);
        for (Wall wall : walls)
            wall.draw(s);

    }
}
