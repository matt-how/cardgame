import org.jsfml.graphics.RenderWindow;
import java.util.Random;

/**
 * Created by Matt on 01/02/2017.
 */
public class Board {
    public boolean gameOver = false;
    private static int MOVESPEED = 1;
    private static int BOARDSIZE = 14;
    private boolean electricDagger = false;
    private int level = 0;
    private int playerLocation;
    Square[] squares = new Square[BOARDSIZE]; //array of game squares

    public Board(){
        setupLevel();
    } //constructor calls new level method

    public void draw(RenderWindow w){ //allows the Driver to draw the squares
        for(int i = 0; i<BOARDSIZE; i++) {
            squares[i].draw(w);
        }
    }

    public void reduceTimers(){ //reduce all the per-turn timers and scan for dead player
        for (int i = 0; i < BOARDSIZE; i++){
            if ( squares[i].isOccupied){
                for(int j = 4;j>=0;j--){
                    squares[i].occupiedCharacter.elementalBoostsTimers[j]--;
                }
                squares[i].occupiedCharacter.stunnedTimer-=1;
            }
            if (squares[i].occupiedCharacter.enemyType == -1){ //if a square is -1 then that is a dead player and the game is over
                gameOver=true;
            }
        }
    }

    public void doublePoison(){ //function to double the amount of poison on enemies
        for(int i = 0; i < BOARDSIZE; i++) {
            if(squares[i].isOccupied&&squares[i].occupiedCharacter.getEnemyType()>1&&squares[i].occupiedCharacter.poisonStacks>0){
                squares[i].occupiedCharacter.poisonStacks *= 2;
            }
        }
    }

    public void setElementalBoosts(int amount, Character.elementalType element, int noOfTurns){ //increase elemental boost timers for player
        squares[playerLocation].occupiedCharacter.elementalBoosts[element.ordinal()] = amount;
        squares[playerLocation].occupiedCharacter.elementalBoostsTimers[element.ordinal()] = noOfTurns;
    }

    public void setTrap(int relativeLocation){ //places a trap tile in a location relative to the player
        if(playerLocation+relativeLocation<BOARDSIZE) {
            if (!squares[playerLocation + relativeLocation].isOccupied) {
                squares[playerLocation + relativeLocation] = new Square(squares[playerLocation + relativeLocation].x, 250, 6, 10, relativeLocation + playerLocation, Character.elementalType.NONE);
            }
        }
    }


    public void castBolt(int amount, Character.elementalType element, boolean stunFlag, boolean poisonFlag){ //travels out from the player in search of a target to deal damage to
        int i = playerLocation+1;
        while (i < BOARDSIZE){
            if (squares[i].isOccupied&&squares[i].occupiedCharacter.enemyType<6){
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

    public void healCharacter(int relativeSquareNumber,int amount){ //calls heal on a square's character for a given amount
        squares[playerLocation+relativeSquareNumber].occupiedCharacter.heal(amount);
        squares[playerLocation+relativeSquareNumber].updateTexture();
    }

    public void stunCharacter(int relativeSquareNumber, int amount){ //increases a stun timer by a given amount
        squares[playerLocation+relativeSquareNumber].occupiedCharacter.stunnedTimer = amount;
    }

    public void poisonSquare(int squareNumber, int amount){ //increases a poison timer by a given amount
        squares[squareNumber].occupiedCharacter.poisonStacks+=amount;
    }

    public void damageSquare(int amount, int relativeLocation, Character.elementalType element){ //method to deal damage to a square's character, taking into account boosts and elemental variables
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
    } //turn on extra boost

    public void setupLevel(){ //create level and give enemies proportional health
        Random rand = new Random();
        level += 1;
        squares[0] = new Square(20,250,1,10,0,Character.elementalType.NONE);
        for(int i = 1; i<BOARDSIZE; i++) {
            squares[i] = new Square((i*70)+20,250,0,0, i,Character.elementalType.NONE);
        }
        squares[11] = new Square(11*70+20,250,2,4+level,11,Character.elementalType.values()[rand.nextInt(3)]); //random element
        squares[13] = new Square(13*70+20,250,2,4+level,13,Character.elementalType.values()[rand.nextInt(3)]);
        playerLocation=0;

    }

    public void playerMove(){ //move button move
       if ((playerLocation + MOVESPEED)>=BOARDSIZE) {
            setupLevel();
        }
        else if(!squares[playerLocation+MOVESPEED].isOccupied){
            squares[playerLocation].moveContents(squares[playerLocation+MOVESPEED]);
            playerLocation+=MOVESPEED;
        }
    }

    public void playerMove(int distance){ //card based move
        int i;
        if ((playerLocation + distance)>=BOARDSIZE) {
            squares[playerLocation].moveContents(squares[BOARDSIZE-1]);
            playerLocation = BOARDSIZE - 1;
        }
        else if ((playerLocation + distance)< 0) {
            squares[playerLocation].moveContents(squares[0]);
            playerLocation = 0;
        }
        else if(distance<0){
            squares[playerLocation].moveContents(squares[playerLocation+distance]);
            playerLocation+=distance;
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

    public void enemyTurn(){ //deals with enemy AI. They move forward if they can, and if they get blocked by a player they attack and if they run into a trap they take damage
        for(int i = 1; i<BOARDSIZE; i++) {
            if (squares[i].occupiedCharacter.poisonStacks>0)
                damageSquare(squares[i].occupiedCharacter.poisonStacks,i-playerLocation, Character.elementalType.NONE);
            if ((squares[i].isOccupied) &&squares[i].occupiedCharacter.enemyType>1&&squares[i].occupiedCharacter.enemyType<6){
                if(squares[i].occupiedCharacter.stunnedTimer<=0){
                    if(squares[i-1].isOccupied){
                        switch (squares[i-1].occupiedCharacter.getEnemyType()){
                            case 1:
                                damageSquare(2,0,squares[i].occupiedCharacter.ourElement);
                                break;
                            case 6:
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
            }


        }
    }


}
