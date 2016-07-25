package war;
public class CardOperations {

	public Card[] getArray(MultiDS<Card> deck) {
		Card[] arrDeck = new Card[52];  
		for (int i = 0; i < deck.size(); i++) {
			arrDeck[i] = deck.getCard(i);
		}
		//arrDeck = deck.toArray();
		return arrDeck;
	}

	//getName and put them together as a String

	//    public cardOperations(){
	//        
	//    }
	//    
	//public MultiDS<Card> buildDeck()
	//    {   
	//        MultiDS<Card> t=new MultiDS<Card>(52);
	//        for (int a = 0; a < Card.Suits.values().length; a++) {
	//            for (int b = 0; b < Card.Ranks.values().length; b++) {
	//                Card c1 = new Card(Card.Suits.values()[a], Card.Ranks.values()[b]);
	//                t.addItem(c1);
	//            }
	//        }
	//        return t;   
	//    }
	//public void dealCards(MultiDS<Card> drawDeck, MultiDS<Card> player1, MultiDS<Card> player2){
	//        int count=0;
	//        Card temp;
	//        while(!drawDeck.empty()){
	//            
	//            
	//        }     
	//    } 
}

