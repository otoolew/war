package war;
import java.util.Scanner;

public class War {
	Card[] arrDeck;
	Card c1;
	int res;
	private static int roundCount=1;
	private static boolean newGame=true;
	private static boolean gameOn=true;
	MultiDS<Card> drawDeck;
	MultiDS<Card> player1Deck;
	MultiDS<Card> player2Deck;
	MultiDS<Card> player1Discard;
	MultiDS<Card> player2Discard;
	MultiDS<Card> warPile;

	public static void main(String[] args) {

		// Variable declaration
		int player1WinHand;
		int player2WinHand;
		int player1Lose;
		int player2Lose;
		String p1name="Player 1";
		String p2name="Player 2";


		if(newGame){
			// Setting up Data structures

			MultiDS<Card> drawDeck =  new MultiDS<>(52);
			MultiDS<Card> player1Deck = new MultiDS<>(52);
			MultiDS<Card> player2Deck = new MultiDS<>(52);
			MultiDS<Card> player1Discard = new MultiDS<>(52);
			MultiDS<Card> player2Discard = new MultiDS<>(52);
			MultiDS<Card> warPile = new MultiDS<>(52);

			// Priming the Scanner
			Scanner scan= new Scanner(System.in);

			//Building the Deck
			for (Card.Suits value : Card.Suits.values()) {
				for (Card.Ranks value1 : Card.Ranks.values()) {
					Card c1 = new Card(value, value1);
					drawDeck.addItem(c1);
				}
			}
			//COMPLETED Deck Building

			//Display drawDeck for debug
			//System.out.println(drawDeck.toString());


			//Welcome Message
			System.out.println("Lets play WAR!");

			//Shuffling deck before dealing
			drawDeck.shuffle();
			// COMPLETE Shuffling
			System.out.println("Please Enter a Max Number of Rounds to play.");
			int maxRounds = scan.nextInt();
			//Dealing Cards to Players
			while(!drawDeck.empty()){
				player1Deck.addItem(drawDeck.getCard(0));
				//debugging to make sure cards are stored properly
				//System.out.println("Player 1 Deck size:"+ player1Deck.size());
				drawDeck.removeItem();
				player2Deck.addItem(drawDeck.getCard(0));
				//debugging to make sure cards are stored properly
				//System.out.println("Player 2 Deck size:"+ player2Deck.size());
				drawDeck.removeItem();
			}// COMPLETED Dealing of Cards
			int p1Hand = checkHandSize(player1Deck);
			int p2Hand = checkHandSize(player2Deck);
			int p1Dis = checkHandSize(player1Discard);
			int p2Dis = checkHandSize(player2Discard);  
			while(gameOn){
				boolean atWar=false;
				do{

					boolean checkedHands=false;
					p1Hand = checkHandSize(player1Deck);
					p2Hand = checkHandSize(player2Deck);
					p1Dis = checkHandSize(player1Discard);
					p2Dis = checkHandSize(player2Discard);             

					while( p1Hand==0){                  
						if(p1Dis==0){
							System.out.println("Player 1 is out of Cards!");
							gameOn=false;
							break;   
						}                  
						while(!player1Discard.empty()){
							player1Deck.addItem(player1Discard.getCard(0));
							player1Discard.removeItem();
						}
						System.out.println("Player 1 collected thier discard pile!");
						p1Hand = checkHandSize(player1Deck);
					}

					while( p2Hand==0){                  
						if(p2Dis==0){
							System.out.println("Player 2 is out of Cards!");
							gameOn=false;
							break;   
						}                  
						while(!player2Discard.empty()){
							player2Deck.addItem(player2Discard.getCard(0));
							player2Discard.removeItem();
						}
						p2Hand = checkHandSize(player2Deck);
						System.out.println("Player 2 collected their discard pile!");
					}    
					p1Hand = checkHandSize(player1Deck);
					p2Hand = checkHandSize(player2Deck);

					if(p1Hand > 0 && p2Hand > 0){
						checkedHands=true;    
					}

					if(checkedHands){                   

						Card a = player1Deck.removeItem();
						Card b = player2Deck.removeItem();

						System.out.println("P1: " + a);
						System.out.println("P2: " + b);
						int decision = a.compareTo(b);
						if (decision>0){
							player1Discard.addItem(a);
							player1Discard.addItem(b);
							while(!warPile.empty()){
								player1Discard.addItem(warPile.removeItem());
							}
							System.out.println("Player 1 WON!");   
						}
						else if (decision<0){
							player2Discard.addItem(a);
							player2Discard.addItem(b);
							while(!warPile.empty()){
								player2Discard.addItem(warPile.removeItem());
							}
							System.out.println("Player 2 WON!"); 
						}
						else if(decision==0){                      
							warPile.addItem(a);
							warPile.addItem(b);                     
							System.out.println("War!"+ a +"and "+b+" at stake!");                                  
						}
						System.out.println("P1 Hand: "+ player1Deck.size()+" Discard: "+
								player1Discard.size());
						System.out.println("P2 Hand: "+player2Deck.size()+" Discard: "+
								player2Discard.size());
					} 

				}while(atWar);
				System.out.println("Round: "+ roundCount++);
				if(roundCount>maxRounds){
					gameOn=false;
				} 
			}// END of gameOn
			int p1Total=p1Hand+p1Dis;
			int p2Total=p2Hand+p2Dis;
			System.out.println("Player 1: "
					+ "\n"+ p1Total);
			System.out.println("Player 2: "
					+ "\n"+ p2Total);
			if(p1Total>p2Total){                
				System.out.println("Player 1 WINS the WAR GAME!");           
			}else if(p2Total>p1Total){
				System.out.println("Player 2 WINS the WAR GAME!");    
			}else{
				System.out.println("Draw");
			}
		}// end of new game loop
	}


	private static int checkHandSize(MultiDS<Card> player){        
		return player.size();      
	}

	public static int result(Card x, Card y){

		int result = x.compareTo(y);

		return result;

	}//END result
}// END WAR