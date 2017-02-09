import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Matt on 19/01/2017.
 */
public class Square extends SprActor{
    int squareNumber;
    int occupiedType = 0;
    int hp;

    public Square(int x, int y, int type, int hp, int squareNumber){
        occupiedType = type;
        this.x = x;
        this.y = y;
        this.hp = hp;
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

    public void damageSquare(int damage){
        hp -= damage;
        if(hp<=0){
            hp=0;
            occupiedType=0;
            updateTexture();
        }
    }

    public boolean moveContents(Square destination){
        if (destination.getOccupiedType() == 0){
            destination.hp = hp;
            destination.occupiedType = occupiedType;
            destination.updateTexture();
            occupiedType = 0;
            hp = 0;
            updateTexture();
            return (true);
        }
        return (false);
    }

    public int getOccupiedType(){
        return(occupiedType);
    }

    void draw(RenderWindow w) {
        w.draw(img);
    }

}
