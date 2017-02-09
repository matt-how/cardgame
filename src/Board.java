import java.util.ArrayList;
import org.jsfml.graphics.RenderWindow;

/**
 * Created by Matt on 01/02/2017.
 */
public class Board {

    public static int MOVESPEED = 2;

    int playerLocation = 0;
    Square[] squares = new Square[14];
    public Board(){
        squares[0] = new Square(20,250,1,50,0);
        for(int i = 1; i<14; i++) {
            squares[i] = new Square((i*70)+20,250,0,0, i);
        }
        squares[11] = new Square(11*70+20,250,2,20,20);
        squares[13] = new Square(13*70+20,250,2,20, 0);
    }

    public void draw(RenderWindow w){
        for(int i = 0; i<14; i++) {
            squares[i].draw(w);
        }
    }

    public void damageSquare(int amount, int relativeLocation){
        squares[playerLocation+relativeLocation].damageSquare(amount);
    }

    public void playerMove(){
        if(squares[playerLocation].moveContents(squares[playerLocation+MOVESPEED])==true){
            playerLocation+=MOVESPEED;
        }
    }

    public void playerMove(int distance){
        if(squares[playerLocation].moveContents(squares[playerLocation+distance])==true){
            playerLocation+=distance;
        }
    }

    public void enemyTurn(){
        for(int i = 0; i<14; i++) {
            if (squares[i].getOccupiedType() > 1){
                squares[i].moveContents(squares[i-1]);
            }
        }
    }


}
