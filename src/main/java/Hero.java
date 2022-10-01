import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private Position position;
    private int step = 1;

    public Hero(int hx, int hy) {
        position = new Position(0,0);
        position.setX(hx);
        position.setY(hy);
    }
    //getters and setters

    public void setPosition(Position pos){
        this.position = pos;
    }
    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }
    public void setX(int newX) {
        position.setX(newX);
    }
    public void setY(int newY) {
        position.setX(newY);
    }
    //moves
    public Position moveUp(){
        return new Position(position.getX(), position.getY() - step);
    }
    public Position moveDown(){
        return new Position(position.getX(), position.getY() + step);
    }
    public Position moveLeft(){
        return new Position(position.getX() - step, position.getY());
    }
    public Position moveRight(){
        return new Position(position.getX() + step, position.getY());
    }

    public int getStep(){
        return step;
    }
    public void increaseStep(){
        step += 1;
    }

    public void decreaseStep(){
        step -= 1;
    }

    public void draw(Screen s){
        s.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('X')[0]);
    }

}
