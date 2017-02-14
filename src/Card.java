import org.jsfml.graphics.Sprite;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Matt on 18/01/2017.
 */
public class Card extends SprActor
{
    int id;

    public Card(int id)
    {
        this.id = id;
        //
        // Load image/ texture
        //
        try {
            imgTexture.loadFromFile(Paths.get("cards/card"+id+".png"));
        } catch (IOException ex) {
            ex.printStackTrace( );
            System.out.println("error loading texture");
        }
        img = new Sprite(imgTexture);
        img.scale((float)0.5, (float)0.5);
    }

    public int getID(){
        return(id);
    }


}
