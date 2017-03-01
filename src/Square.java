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
	HealthUI healthbar;

    public Square(int x, int y, int type, int hp, int squareNumber, Character.elementalType elementType){ //constructor for square
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
                imgTexture.loadFromFile(Paths.get("square"+(occupiedCharacter.getEnemyType()+(occupiedCharacter.ourElement.ordinal()%4))+".png"));

            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("error loading texture");
            }

            healthbar = new HealthUI(occupiedCharacter.getHP(), occupiedCharacter.getMaxHP(), occupiedCharacter.getElement(), x, y);
		
        }
        img = new Sprite(imgTexture);
        img.setPosition(new Vector2f(x, y));
    }

    public void updateTexture(){ //update the picture to match the occupied character type
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
                imgTexture.loadFromFile(Paths.get("square"+(occupiedCharacter.getEnemyType()+(occupiedCharacter.ourElement.ordinal()%4))+".png"));

            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("error loading texture");
            }
			healthbar = new HealthUI(occupiedCharacter.getHP(), occupiedCharacter.getMaxHP(), occupiedCharacter.getElement(), x, y);
        }
        img.setTexture(imgTexture);
    }

    public void removeCharacter(){ //remove the current occupied character
        isOccupied = false;
        occupiedCharacter = new Character(0,0,Character.elementalType.NONE);
        updateTexture();
    }

    public void damageSquare(int damage,Character.elementalType element){ //damage the character
        occupiedCharacter.damage(damage,element,this);
        healthbar = new HealthUI(occupiedCharacter.getHP(), occupiedCharacter.getMaxHP(), occupiedCharacter.getElement(), x, y);
    }

    public void moveContents(Square destination){//move character onto given square
        if (destination!=this){
            destination.occupiedCharacter = this.occupiedCharacter;
            destination.isOccupied=true;
            destination.updateTexture();
            this.removeCharacter();
        }
    }

    void draw(RenderWindow w) { //method to draw character and healthbar
        w.draw(img);
		if (isOccupied){
				healthbar.draw(w);
		}
    }

}
