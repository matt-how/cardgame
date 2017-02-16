import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Matt on 19/01/2017.
 */
public class Square extends SprActor{

    int squareNumber;
    boolean isOccupied;
    Character occupiedCharacter;

    public Square(int x, int y, int type, int hp, int squareNumber, Character.elementalType elementType){
        isOccupied=false;
        occupiedCharacter = new Character(0,0,Character.elementalType.NONE);
        if (type > 0) {
            isOccupied = true;
            occupiedCharacter = new Character(hp,type,elementType);
        }
        this.x = x;
        this.y = y;
        this.squareNumber = squareNumber;
        obj = img;

        //
        // Load image/ texture
        //
        if (!isOccupied) {
            try {
                imgTexture.loadFromFile(Paths.get("square0.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("error loading texture");
            }
        }
        else{
            try {
                imgTexture.loadFromFile(Paths.get("square"+occupiedCharacter.getEnemyType()+".png"));
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("error loading texture");
            }
        }
        img = new Sprite(imgTexture);
        img.setPosition(new Vector2f(x, y));
    }

    public void updateTexture(){
        if (!isOccupied) {
            try {
                imgTexture.loadFromFile(Paths.get("square0.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("error loading texture");
            }
        }
        else{
            try {
                imgTexture.loadFromFile(Paths.get("square"+occupiedCharacter.getEnemyType()+".png"));
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("error loading texture");
            }
        }
        img.setTexture(imgTexture);
    }

    public void removeCharacter(){
        isOccupied = false;
        occupiedCharacter = new Character(0,0,Character.elementalType.NONE);
        updateTexture();
    }

    public void damageSquare(int damage,Character.elementalType element){
        occupiedCharacter.damage(damage,element,this);
    }

    public void moveContents(Square destination){
        if (destination!=this){
            destination.occupiedCharacter = this.occupiedCharacter;
            destination.isOccupied=true;
            destination.updateTexture();
            this.removeCharacter();
        }
    }

    void draw(RenderWindow w) {
        w.draw(img);
    }

}
