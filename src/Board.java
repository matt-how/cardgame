import org.jsfml.graphics.RenderWindow;

/**
 * Created by Matt on 01/02/2017.
 */
public class Board {

    public static int MOVESPEED = 1;
    public static int BOARDSIZE = 14;

    int playerLocation;
    Square[] squares = new Square[BOARDSIZE];
    public Board(){
        squares[0] = new Square(20,250,1,10,0,Character.elementalType.NONE);
        for(int i = 1; i<BOARDSIZE; i++) {
            squares[i] = new Square((i*70)+20,250,0,0, i,Character.elementalType.NONE);
        }
        squares[11] = new Square(11*70+20,250,2,4,11,Character.elementalType.EARTH);
        squares[13] = new Square(13*70+20,250,2,4,13,Character.elementalType.EARTH);
        playerLocation=0;
    }

    public void draw(RenderWindow w){
        for(int i = 0; i<BOARDSIZE; i++) {
            squares[i].draw(w);
        }
    }

    public void reduceTimers(){
        for (int i = 0; i < BOARDSIZE; i++){
            if ( squares[i].isOccupied){
                for(int j = 4;j>=0;j--){
                    squares[i].occupiedCharacter.elementalBoostsTimers[j]--;
                }
            }
        }
    }

    public void setElementalBoosts(int amount, Character.elementalType element, int noOfTurns){
        squares[playerLocation].occupiedCharacter.elementalBoosts[element.ordinal()] = amount;
        squares[playerLocation].occupiedCharacter.elementalBoostsTimers[element.ordinal()] = noOfTurns;
    }



    public void castBolt(int amount, Character.elementalType element){
        int i = playerLocation+1;
        while (i < BOARDSIZE){
            if (squares[i].isOccupied){
                squares[i].damageSquare(amount,element);
                return;
            }
            i++;
        }
    }

    public void healCharacter(int relativeSquareNumber,int amount){
        squares[playerLocation+relativeSquareNumber].occupiedCharacter.heal(amount);
    }

    public void damageSquare(int amount, int relativeLocation, Character.elementalType element){
        if(squares[playerLocation].occupiedCharacter.elementalBoostsTimers[element.ordinal()]>0){
            amount+=squares[playerLocation].occupiedCharacter.elementalBoosts[element.ordinal()];
        }
        if (playerLocation + relativeLocation > -1 && playerLocation + relativeLocation < BOARDSIZE)
            squares[playerLocation + relativeLocation].damageSquare(amount, element);
    }

    public void playerMove(){
       if ((playerLocation + MOVESPEED)>=BOARDSIZE) {
            /*squares[playerLocation].moveContents(squares[BOARDSIZE-1]);
            playerLocation = BOARDSIZE - 1;*/ //unnecesary in movespeed of 1 and moving a square to itself breaks the square
        }
        else if(squares[playerLocation+MOVESPEED].isOccupied==false){
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
        else if(!squares[playerLocation+distance].isOccupied){
            squares[playerLocation].moveContents(squares[playerLocation+distance]);
            playerLocation+=distance;
        }
    }

    public void enemyTurn(){
        for(int i = 1; i<BOARDSIZE; i++) {
            if ((squares[i].isOccupied) &&squares[i].occupiedCharacter.enemyType>1){
                if(squares[i-1].isOccupied&&squares[i-1].occupiedCharacter.getEnemyType()==1){
                    damageSquare(2,0,squares[i].occupiedCharacter.ourElement);
                }
                else if(!squares[i-1].isOccupied){
                    squares[i].moveContents(squares[i - 1]);
                }
            }

        }
    }


}
