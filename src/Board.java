import java.util.ArrayList;
import org.jsfml.graphics.RenderWindow;

/**
 * Created by Matt on 01/02/2017.
 */
public class Board {

    public static int MOVESPEED = 2;
    public static int BOARDSIZE = 14;

    int playerLocation = 0;
    Square[] squares = new Square[BOARDSIZE];
    public Board(){
        squares[0] = new Square(20,250,1,50,0,0);
        for(int i = 1; i<BOARDSIZE; i++) {
            squares[i] = new Square((i*70)+20,250,0,0, i,0);
        }
        squares[11] = new Square(11*70+20,250,2,4,11,1);
        squares[13] = new Square(13*70+20,250,2,4,13,4);
    }

    public void draw(RenderWindow w){
        for(int i = 0; i<BOARDSIZE; i++) {
            squares[i].draw(w);
        }
    }

    public void damageSquare(int amount, int relativeLocation, int element){
        if (playerLocation + relativeLocation > -1 && playerLocation + relativeLocation < BOARDSIZE)
            squares[playerLocation + relativeLocation].damageSquare(amount, element);
}

    public void playerMove(){
        if ((playerLocation + MOVESPEED)>=BOARDSIZE) {
            squares[playerLocation].moveContents(squares[BOARDSIZE-1]);
            playerLocation = BOARDSIZE - 1;
        }
        else if ((playerLocation + MOVESPEED)< 0) {
            squares[playerLocation].moveContents(squares[0]);
            playerLocation = 0;
        }
        else{
            squares[playerLocation].moveContents(squares[playerLocation+MOVESPEED]);
            playerLocation+=MOVESPEED;
        }
    }

    public void playerMove(int distance){
        if ((playerLocation + distance)>=BOARDSIZE) {
            squares[playerLocation].moveContents(squares[BOARDSIZE-1]);
            playerLocation = BOARDSIZE - 1;
        }
        else if ((playerLocation + distance)< 0) {
            squares[playerLocation].moveContents(squares[0]);
            playerLocation = 0;
        }
        else{
            squares[playerLocation].moveContents(squares[playerLocation+distance]);
            playerLocation+=distance;
        }
    }

    public void enemyTurn(){
        for(int i = 1; i<BOARDSIZE; i++) {
            if (squares[i].getOccupiedType() > 1&& squares[i-1].getOccupiedType()==0){
                try {
                    squares[i].moveContents(squares[i - 1]);
                }
                catch (ArrayIndexOutOfBoundsException e){

                }
            }
        }
    }


}
