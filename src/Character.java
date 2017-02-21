/**
 * Created by ccuk on 15/02/2017.
 */
public class Character {
    public enum elementalType { FIRE, WATER, ELECTRIC, EARTH, NONE };
    int hp;
    int maxHP;
    elementalType ourElement;
    int enemyType = 0;

    public Character(int hp, int enemyType, elementalType type){
        this.hp = hp;
        this.maxHP = hp;
        this.ourElement = type;
        this.enemyType=enemyType;
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
