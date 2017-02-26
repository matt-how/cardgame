import org.jsfml.graphics.RenderWindow;

/**
 * Created by Matt on 01/02/2017.
 */
public class Board {

    private static int MOVESPEED = 1;
    private static int BOARDSIZE = 14;
    private boolean electricDagger = false;

    private int playerLocation;
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
                squares[i].occupiedCharacter.stunnedTimer-=1;
            }
        }
    }

    public void doublePoison(){
        for(int i = 0; i < BOARDSIZE; i++) {
            if(squares[i].isOccupied&&squares[i].occupiedCharacter.getEnemyType()>1&&squares[i].occupiedCharacter.poisonStacks>0){
                squares[i].occupiedCharacter.poisonStacks *= 2;
            }
        }
    }

    public void setElementalBoosts(int amount, Character.elementalType element, int noOfTurns){
        squares[playerLocation].occupiedCharacter.elementalBoosts[element.ordinal()] = amount;
        squares[playerLocation].occupiedCharacter.elementalBoostsTimers[element.ordinal()] = noOfTurns;
    }

    public void setTrap(int relativeLocation){
        if(!squares[playerLocation+relativeLocation].isOccupied){
            squares[playerLocation+relativeLocation]=new Square(squares[playerLocation+relativeLocation].x,250,5,10,relativeLocation+playerLocation, Character.elementalType.EARTH);
        }
    }


    public void castBolt(int amount, Character.elementalType element, boolean stunFlag, boolean poisonFlag){
        int i = playerLocation+1;
        while (i < BOARDSIZE){
            if (squares[i].isOccupied&&squares[i].occupiedCharacter.enemyType<5){
                if(poisonFlag)
                    poisonSquare(i,amount);
                else
                    damageSquare(amount,i-playerLocation,element);
                if(stunFlag)
                    stunCharacter(i-playerLocation,1);
                return;
            }
            i++;
        }
    }

    public void healCharacter(int relativeSquareNumber,int amount){
        squares[playerLocation+relativeSquareNumber].occupiedCharacter.heal(amount);
        squares[playerLocation+relativeSquareNumber].updateTexture();
    }

    public void stunCharacter(int relativeSquareNumber, int amount){
        squares[playerLocation+relativeSquareNumber].occupiedCharacter.stunnedTimer = amount;
    }

    public void poisonSquare(int squareNumber, int amount){
        squares[squareNumber].occupiedCharacter.poisonStacks+=amount;
    }

    public void damageSquare(int amount, int relativeLocation, Character.elementalType element){
        if(squares[playerLocation].occupiedCharacter.elementalBoostsTimers[element.ordinal()]>0){
            amount+=squares[playerLocation].occupiedCharacter.elementalBoosts[element.ordinal()];
        }
        if (playerLocation + relativeLocation > -1 && playerLocation + relativeLocation < BOARDSIZE) {
            squares[playerLocation + relativeLocation].damageSquare(amount, element);
        }
        if (electricDagger&&relativeLocation!=0){
            stunCharacter(relativeLocation,2);
            electricDagger=false;
        }
    }

    public void setElectricDagger(){
        electricDagger=true;
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
        int i;
        if ((playerLocation + distance)>=BOARDSIZE) {
            squares[playerLocation].moveContents(squares[BOARDSIZE-1]);
            playerLocation = BOARDSIZE - 1;
        }
        else if ((playerLocation + distance)< 0) {
            squares[playerLocation].moveContents(squares[0]);
            playerLocation = 0;
        }
        else{
            for(i = 1; i <= distance;i++){
                if(squares[playerLocation+i].isOccupied)
                    break;
            }
            squares[playerLocation].moveContents(squares[playerLocation+i-1]);
            playerLocation+=i-1;
        }
    }

    public void enemyTurn(){
        for(int i = 1; i<BOARDSIZE; i++) {
            if ((squares[i].isOccupied) &&squares[i].occupiedCharacter.enemyType>1&&squares[i].occupiedCharacter.enemyType<5){
                if(squares[i].occupiedCharacter.stunnedTimer<=0){
                    if(squares[i-1].isOccupied){
                        switch (squares[i-1].occupiedCharacter.getEnemyType()){
                            case 1:
                                damageSquare(2,0,squares[i].occupiedCharacter.ourElement);
                                break;
                            case 5:
                                poisonSquare(i,1);
                                stunCharacter(i-playerLocation,2);
                                squares[i].moveContents(squares[i-1]);
                                break;
                        }

                    }
                    else if(!squares[i-1].isOccupied){
                        squares[i].moveContents(squares[i - 1]);
                    }
                }
                if (squares[i].occupiedCharacter.poisonStacks>0)
                    damageSquare(squares[i].occupiedCharacter.poisonStacks,i-playerLocation, Character.elementalType.EARTH);
            }


        }
    }


}
