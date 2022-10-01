import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private int x;
    private int y;

    public Hero(int hx, int hy) {
        x = hx;
        y = hy;
    }
    //getters and setters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int newX) {
        x = newX;
    }
    public void setY(int newY) {
        y = newY;
    }
    //moves
    public void moveUp(int step){
        y -= step;
    }
    public void moveDown(int step){
        y += step;
    }
    public void moveLeft(int step){
        x -= step;
    }
    public void moveRight(int step){
        x += step;
    }

    public void draw(Screen s){
        s.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
    }

}
