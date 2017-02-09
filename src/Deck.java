import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Matt on 25/01/2017.
 */

public class Deck {
    public static int DECKSIZE = 30;

    ArrayList<Card> cards = new ArrayList<Card>();

    public Deck(){
        setupTestDeck();
    }

    public void shuffle(){
        int j;
        for(int i = DECKSIZE-1; i>0;i--){
            j = (int )(Math.random() * i);
            Collections.swap(cards,i,j);
        }
    }

    public void setupTestDeck()
    {
        for(int i = 0; i < DECKSIZE; i++){
            cards.add(new Card((i%17)+2));
        }
    }

    public Card drawCard() {
        if(cards.size()==0){
            return(new Card(0));
        }
        return(cards.remove(cards.size()-1));
    }


}
