/**
 * Created by ccuk on 15/02/2017.
 */
public class Character {
    public enum elementalType { FIRE, WATER, ELECTRIC, EARTH, NONE }
    int hp;
    int maxHP;
    elementalType ourElement;
    int enemyType = 0;
    int[] elementalBoosts = new int[5];
    int[] elementalBoostsTimers = new int[5];
    int poisonStacks = 0;
    int stunnedTimer = 0;

    public Character(int hp, int enemyType, elementalType type){
        this.hp = hp;
        this.maxHP = hp;
        this.ourElement = type;
        this.enemyType=enemyType;
        for(int i =0; i<5;i++){
            elementalBoosts[i]=0;
            elementalBoostsTimers[i]=0;
        }
    }

    public int getEnemyType(){
        return (enemyType);
    }
	
	public int getHP(){
		return hp;
	}
	
	public int getMaxHP(){
		return maxHP;
	}

	public elementalType getElement() {
	    return ourElement;
	}

    public void heal(int amount){
        hp += amount;
        if (hp>maxHP)
            hp=maxHP;
    }

    public void damage(int damage,elementalType element, Square currentSquare){
        if (element == elementalType.NONE) {
            hp -= damage;
        }
        else if (element == ourElement) {
            hp -= (0.5 * damage);
        }
        else if (element.ordinal() == ((ourElement.ordinal()+1)%4)) {
            hp -= (1.5 * damage);
        }
        else {
            hp -= damage;
        }		

        if(hp<=0){
            currentSquare.removeCharacter();
        }
    }		
}
