import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Matt on 25/01/2017.
 */

public class Deck {
    public static int DECKSIZE = 30;

    ArrayList<Card> cards = new ArrayList<Card>();

    public void shuffle(){ //Yates shuffle alogrithm
        int j;
        for(int i = DECKSIZE-1; i>0;i--){
            j = (int )(Math.random() * i);
            Collections.swap(cards,i,j);
        }
    }

    public void addCard(Card i){
        cards.add(i);
    } //add card to decklist


    public Card drawCard() { //return the top card and remove it
        if(cards.size()==0){
            return(new Card(0));
        }
        return(cards.remove(cards.size()-1));
    }


}
