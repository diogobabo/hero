import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

public class Arena {
    int width;
    int height;
    private Hero hero;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        hero = new Hero(10, 10);

    }

    public int processKey(KeyStroke key) {
        System.out.println(key);
        if (key.getKeyType() == KeyType.ArrowUp)
            moveHero(hero.moveUp());
        else if (key.getKeyType() == KeyType.ArrowDown) {
            moveHero(hero.moveDown());
        } else if (key.getKeyType() == KeyType.ArrowLeft) {
            moveHero(hero.moveLeft());
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            moveHero(hero.moveRight());
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            return 1;
        } else {
            return 2;
        }
        return 0;
    }

    private void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width+1, height+1), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(hero.getX(), hero.getY()), "X");

    }

    public boolean canHeroMove(Position position){

        if(0 <= position.getY() && position.getY() <= height && 0 <= position.getX() && position.getX() <= width){
            return true;
        }

        return false;
    }
}
