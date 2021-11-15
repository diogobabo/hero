import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Arena {
    int width;
    int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        hero = new Hero(10, 10);
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }
    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
            if(coins.get(i).getPosition().equals(hero.getPosition())){
                coins.remove(i);
                i = i-1;
            }
        }
        return coins;
    }
    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            monsters.add(new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
            if(monsters.get(i).getPosition().equals(hero.getPosition())){
                monsters.remove(i);
                i = i-1;
            }
        }
        return monsters;
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
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
        if (canHeroMove(position)){
            hero.setPosition(position);
            retrieveCoins(position);
            moveMonsters();
        }

    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Wall wall : walls)
            wall.draw(graphics,"#00ff26","*");
        for (Coin coin : coins){
            coin.draw(graphics,"#fcfcfc","O");
        }
        for (Monster monster : monsters){
            monster.draw(graphics,"#f50000","R");
        }
        hero.draw(graphics,"#FFFF33","X");

    }

    public boolean canHeroMove(Position position){

        if(!(0 <= position.getY() && position.getY() <= height-1 && 0 <= position.getX() && position.getX() <= width-1)){
            return false;
        }
        for (Wall wall : walls){
            if (wall.getPosition().equals(position)){
                return false;
            }
        }

        return true;
    }
    private void  moveMonsters(){
        for (Monster monster : monsters){
            Position nexPos = monster.move();
            for (Wall wall : walls){
                if (wall.getPosition().equals(nexPos)){
                    return;
                }
            }
            if(0 <= nexPos.getY() && nexPos.getY() <= height-1 && 0 <= nexPos.getX() && nexPos.getX() <= width-1){
                monster.setPosition(nexPos);
            }

        }
    }

    private boolean retrieveCoins(Position position) {
        for(int x = 0; x<coins.size();x++){
            if(coins.get(x).getPosition().equals(position)){
                coins.remove(x);
                return true;
            }
        }
        return false;
    }

}
