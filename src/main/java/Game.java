import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Hero hero;
    public Game(int with, int height) throws IOException {

        TerminalSize terminalSize = new TerminalSize(40, 20);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        hero = new Hero(10, 10);
    }
    private void draw() throws IOException{
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }
    public void run() throws IOException {
        int flag;
        KeyStroke key;
        while(true){
            draw();
            key = screen.readInput();
            flag = processKey(key);
            if(flag == 1){
                screen.close();
            }
            else if(KeyType.EOF == key.getKeyType()){
                break;
            }
        }

    }
    private int processKey(KeyStroke key) {
        System.out.println(key);
        if (key.getKeyType() == KeyType.ArrowUp)
            moveHero(hero.moveUp());
        else if(key.getKeyType() == KeyType.ArrowDown){
            moveHero(hero.moveDown());
        }
        else if(key.getKeyType() == KeyType.ArrowLeft){
            moveHero(hero.moveLeft());
        }
        else if(key.getKeyType() == KeyType.ArrowRight){
            moveHero(hero.moveRight());
        }
        else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
            return 1;
        }
        else{
            return 2;
        }
        return 0;
    }
    private void moveHero(Position position) {
        hero.setPosition(position);
    }

}
