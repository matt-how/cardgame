import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.RenderStates;
import org.jsfml.system.Vector2f;

public class HealthUI {
	
	Character occupiedCharacter;
	
	private RectangleShape healthBar;	
	private RectangleShape healthBorder;
	private CircleShape elementType;
	private CircleShape elementBorder;

	public HealthUI(int hp, int maxHP, Character.elementalType ourElement, int x, int y) {

		System.out.println(ourElement);

		healthBar = new RectangleShape(new Vector2f((hp * 5), 10));
		healthBar.setFillColor(Color.GREEN);
		healthBar.setPosition((x + 5), (y - 10));

		healthBorder = new RectangleShape(new Vector2f((maxHP * 5), 10));
		healthBorder.setFillColor(Color.BLACK);
		healthBorder.setPosition((x + 5), (y - 10));

		elementType = new CircleShape(5);
		elementType.setPosition((x + 5), (y - 25));
		if (ourElement == Character.elementalType.FIRE) {
			elementType.setFillColor(Color.RED);
		} else if (ourElement == Character.elementalType.WATER) {
			elementType.setFillColor(Color.BLUE);
		} else if (ourElement == Character.elementalType.ELECTRIC) {
			elementType.setFillColor(Color.YELLOW);
		} else if (ourElement == Character.elementalType.EARTH) {
			elementType.setFillColor(Color.GREEN);
		}

		elementBorder = new CircleShape(7);
		elementBorder.setPosition((x + 3), (y - 27));
		elementBorder.setFillColor(Color.BLACK);
	}

	public void draw(RenderWindow window) {
		window.draw(healthBorder,RenderStates.DEFAULT);
		window.draw(healthBar,RenderStates.DEFAULT);
		window.draw(elementBorder, RenderStates.DEFAULT);
		window.draw(elementType, RenderStates.DEFAULT);
	}
		
}
