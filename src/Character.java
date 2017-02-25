/**
 * Created by ccuk on 15/02/2017.
 */
public class Character {
    public enum elementalType { FIRE, WATER, ELECTRIC, EARTH, NONE };
    int hp;
    int damageMult = 1;
    int maxHP;
    int damage;
    elementalType ourElement;
    int enemyType = 0;
    int minRange;
    int maxRange;

    public Character(int hp, int enemyType, elementalType type){
        this.hp = hp;
        this.maxHP = hp;
        this.ourElement = type;
        this.enemyType=enemyType;

        switch(enemyType) {
            case 2 : minRange = 1;
            maxRange = 1;
            break;
            case 3 : minRange = 1;
            maxRange = 2;
            break;
            case 4 : minRange = 1;
            maxRange=3;
        }
    }

    public int getMax(){
        return maxRange;
    }

    public int getEnemyType(){
        return (enemyType);
    }

    public void heal(int amount){
        hp += amount;
        if (hp>maxHP)
            hp=maxHP;
    }

    public void damage(int damage,elementalType element, Square currentSquare){
        this.damage = damage + damageMult;

        if (element == elementalType.NONE) {
            hp -= this.damage;
        }
        else if (element == ourElement) {
            hp -= (0.5 * this.damage);
        }
        else if (element.ordinal() == ((ourElement.ordinal()+1)%4)) {
            hp -= (1.5 * this.damage);
        }
        else {
            hp -= this.damage;
        }

        if(hp<=0){
            currentSquare.removeCharacter();
        }
    }

    public void difficultyInc() {
        hp = (int)(hp * 1.5);
        damageMult++;
    }

}
