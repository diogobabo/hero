import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero extends Element{

    public Hero(int x, int y){
        super(x,y);
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
