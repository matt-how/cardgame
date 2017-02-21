import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.RenderStates;
import org.jsfml.system.Vector2f;

public class HealthUI {
	
	Character occupiedCharacter;
	
	private RectangleShape healthBar;	
	private RectangleShape healthBorder;

	public HealthUI(int hp, int maxHP, int x, int y) {
		
		

		System.out.println("hp=" + hp + " Max HP=" + maxHP);
		
		healthBar = new RectangleShape(new Vector2f((hp*5), 10));
		healthBar.setFillColor(Color.GREEN);
		healthBar.setPosition((x+5),(y-10));
		
		healthBorder = new RectangleShape(new Vector2f((maxHP*5), 10));
		healthBorder.setFillColor(Color.BLACK);
		healthBorder.setPosition((x+5),(y-10));
	}

	public void draw(RenderWindow window) {
		window.draw(healthBorder,RenderStates.DEFAULT);
		window.draw(healthBar,RenderStates.DEFAULT);		
	}
		
}