import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import java.io.IOException;
import java.nio.file.Paths;

public class HealthUI {
	
	Character occupiedCharacter;
	boolean elementNone = false;
	int healthDif = 0;
	
	private RectangleShape healthBar;	
	private RectangleShape healthBorder;
	private CircleShape elementType;
	private CircleShape elementBorder;

	public HealthUI(int hp, int maxHP, Character.elementalType ourElement, int x, int y) {

		if(hp !=0) {
			healthDif = 60 / maxHP;
		}

		healthBar = new RectangleShape(new Vector2f((hp*healthDif), 10));
		healthBar.setFillColor(Color.GREEN);
		healthBar.setPosition((x + 5), (y - 10));

		healthBorder = new RectangleShape(new Vector2f(60, 10));
		healthBorder.setFillColor(Color.BLACK);
		healthBorder.setPosition((x + 5), (y - 10));

		if(ourElement == Character.elementalType.NONE) {
			elementNone = true;
		}else{
			elementType = new CircleShape(10);
			elementType.setPosition((x + 5), (y - 30));

			Texture imgTexture = new Texture();

			try {
				imgTexture.loadFromFile(Paths.get("eType" + ourElement + ".png"));
			}catch (IOException ex) {
				ex.printStackTrace();
			}
			elementType.setTexture((ConstTexture) imgTexture);
		}
	}

	public void draw(RenderWindow window) {
		window.draw(healthBorder,RenderStates.DEFAULT);
		window.draw(healthBar,RenderStates.DEFAULT);
//		window.draw(elementBorder, RenderStates.DEFAULT);
		if(elementNone == false) {
			window.draw(elementType, RenderStates.DEFAULT);
		}
	}
		
}
