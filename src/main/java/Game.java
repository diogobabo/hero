import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena;
    int width;
    int height;
    public Game(int width, int height) throws IOException {

        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        arena = new Arena(width, height);
        this.height = height;
        this.width = width;
    }
    private void draw() throws IOException{
        screen.clear();
        arena.draw(screen.newTextGraphics());
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
            else if(flag == 3){

                screen.clear();
                TextGraphics graphics = screen.newTextGraphics();
                graphics.setForegroundColor(TextColor.Factory.fromString("#f50000"));
                graphics.enableModifiers(SGR.BOLD);
                graphics.putString((width/2)-5, (height/2)-2, "YOU LOSE");
                graphics.setForegroundColor(TextColor.Factory.fromString("#fcfcfc"));
                graphics.enableModifiers(SGR.BOLD);
                graphics.putString(((width*2)/3), (height)-1, " 'q' to quit");
                screen.refresh();
                key = screen.readInput();

                if(KeyType.EOF == key.getKeyType()){
                    break;
                }else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    screen.close();
                }

                return;
            }
        }

    }
    private int processKey(KeyStroke key) {
        return arena.processKey(key);
    }

}
