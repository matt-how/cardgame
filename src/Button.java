import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Matt on 25/01/2017.
 */
public class Button extends SprActor{
    int buttonID;
    public Button (String filename, int x, int y, int buttonID){
        try {
            imgTexture.loadFromFile(Paths.get(filename));
        } catch (IOException ex) {
            ex.printStackTrace( );
            System.out.println("error loading texture");
        }
        img = new Sprite(imgTexture);
        img.setPosition(new Vector2f(x, y));
        this.x = x;
        this.y = y;
        this.buttonID = buttonID;
    }

    public int getButtonID(){
        return  (buttonID);
    }


}
