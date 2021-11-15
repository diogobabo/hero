import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private Position position;
    public Hero(int x, int y){
        position = new Position(x,y);
        position.setX(x);
        position.setY(y);
    }
    public void draw(Screen screen){
        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('X')[0]);
    }

    public void setX(int x) {
        position.setX(x);
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public void setY(int y) {
        position.setY(y);
    }
    public Position moveUp(){
        return new Position(position.getX(),position.getY() - 1);
    }
    public Position moveDown(){
        return new Position(position.getX(),position.getY() + 1);
    }
    public Position moveRight(){
        return new Position(position.getX() + 1 ,position.getY());
    }
    public Position moveLeft(){
        return new Position(position.getX() - 1,position.getY());
    }
    public void setPosition(Position position){
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
