import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Matt on 19/01/2017.
 */
public class Square extends SprActor{
    public final int NONE = 0;
    public final int FIRE = 1;
    public final int WATER = 2;
    public final int ELECTRIC = 3;
    public final int EARTH = 4;
    int squareNumber;
    int occupiedType = 0;
    int hp;
    int weakness;

    public Square(int x, int y, int type, int hp, int squareNumber, int weakness){
        occupiedType = type;
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.weakness = weakness;
        this.squareNumber = squareNumber;
        obj = img;

        //
        // Load image/ texture
        //
        try {
            imgTexture.loadFromFile(Paths.get("square"+occupiedType+".png"));
        } catch (IOException ex) {
            ex.printStackTrace( );
            System.out.println("error loading texture");
        }
        img = new Sprite(imgTexture);
        img.setPosition(new Vector2f(x, y));
    }

    public void updateTexture(){
        try {
            imgTexture.loadFromFile(Paths.get("square"+occupiedType+".png"));
        } catch (IOException ex) {
            ex.printStackTrace( );
            System.out.println("error loading texture");
        }
        img.setTexture(imgTexture);
    }

    public void damageSquare(int damage,int element){
        if (element == 0) {
            hp -= damage;
        }
        else if (element == weakness) {
            hp -= (1.5 * damage);
        }
        else if (element == (weakness%4)+1) {
            hp -= (0.5 * damage);
        }
        else {
            hp -= damage;
        }

        if(hp<=0){
            hp=0;
            occupiedType=0;
            updateTexture();
        }
    }

    public void moveContents(Square destination){
        if (destination.getOccupiedType() == 0){
            destination.hp = hp;
            destination.occupiedType = occupiedType;
            destination.weakness = weakness;
            weakness = 0;
            destination.updateTexture();
            occupiedType = 0;
            hp = 0;
            updateTexture();
        }
    }

    public int getOccupiedType(){
        return(occupiedType);
    }

    void draw(RenderWindow w) {
        w.draw(img);
    }

}
