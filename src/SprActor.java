import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

/**
 * Created by Matt on 25/01/2017.
 */
public abstract class SprActor extends Actor {

    Texture imgTexture = new Texture();
    Sprite img;
    public float getWidth(){
        return(img.getGlobalBounds().width);
    }
    public float getHeight(){
        return(img.getGlobalBounds().height);
    }

    public void setX(int x){
        this.x = x;
        img.setPosition(new Vector2f(x, y));
    }

    public void setY(int y){
        this.y = y;
        img.setPosition(new Vector2f(x, y));
    }

    void draw(RenderWindow w) {
        w.draw(img);
    }
}
