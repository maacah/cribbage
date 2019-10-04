/*
 * Purpose: Create a collection of methods used in the main file
 * to calculate points scored for a given hand. 
 * 
 * This class contains five static methods, 
 * one for each of the ways in which points 
 * can be scored in the game of cribbage. 
 * Each method takes an Array List as input. 
 * For methods where combinations aren't a concern 
 * (oneForHisNob and flush), the input is an array List
 * of the cards in the hand. For pairs and fifiteens,
 * the input is an Array List of all possible 
 * combinations of cards in the hand. For the last 
 * method cardsInRun the input is the a sorted list
 * of the cards in the hand.
 * 
 */

package handValue; 
import java.util.ArrayList;

public class PointScorers {
	
	// create constant variable to represent 
		// number of cards in any given hand
	private static final int NUM_OF_CARDS = 5;

	// static method oneForHisNob determines whether hand contains
	// the 'nob' if it does it will return 1, otherwise 0.
	public static int oneForHisNob(ArrayList<String> hand) {
		int points = 0;
		
		String startCard = hand.get(NUM_OF_CARDS -1);
		for (int i = 0; i < NUM_OF_CARDS - 1; i++) {
			String card = hand.get(i);
			if ((card.charAt(0) == 'J') && (card.charAt(1) == startCard.charAt(1))){
				points = 1; 
			}
		}
		return points;
	}
	
	
	/* static method flush determines whether each card in the 
	 *  hand is of the same suit. If yes it will return 4. If yes 
	 *  and start card is also of that suit, will return 5. 
	 *  Otherwise, it will return 0.
	 */
	public static int flush(ArrayList<String> hand) {
		char suit = hand.get(0).charAt(1);
		for (int i = 1; i < (NUM_OF_CARDS - 1); i++) {
			if (hand.get(i).charAt(1) != suit) {
	
				return 0;
			}
		}
		if (hand.get((NUM_OF_CARDS -1)).charAt(1) == suit){
			return 5;
		}
		else {
			return 4;
		}
	}
	
	/* static method pairs iterates through all of the combinations
	 * in combinations. If a combination is a length 2 and of 
	 * equal numerical value numOfPairs increased by 1. It returns
	 * numOfPairs times 2. 
	 */
	public static int pairs(ArrayList<String> combinations) {
		int numberOfCombinations = combinations.size();
		int numOfPairs = 0;
		for (int i = 0; i < numberOfCombinations ; i++) {
			String combo = (String) combinations.get(i);
			if (combo.length() == 2) {
				if (combo.charAt(0) == combo.charAt(1)) {
				++numOfPairs; }
			}
		}
		return numOfPairs * 2;
	}	
	
	/* static method fifteens iterates through all of the combinations 
	 * in combinations. For each combination that adds to equal 15
	 * numOfFifteens is incremented by 1. It returns numOfFifteens
	 * times 2.
	 */
	public static int fifteens(ArrayList<String> combinations) {
		int numberOfCombinations = combinations.size();
		int numOfFifteens = 0;
		
		// iterate through all combinations
		for (int i = 0; i < numberOfCombinations ; i++) {
			String combo = (String) combinations.get(i);
			int lengthOfCombo = combo.length();
			int value = 0;
			// iterate through each card in combination
			for (int j = 0; j < lengthOfCombo; j++) {
				char card = combo.charAt(j);
				// if a special card: King, Queen, Jack and Ten add 10
				if( card == 'K' || card == 'Q' || card == 'J'|| card == 'T' ) {
					value = value + 10;
				}
				// if a special card: Ace, add 1
				else if( card == 'A') {
					value = value + 1;
				}
				// if a numerical card, add it's value
				else { int x = card - '0';
					value = value + x;
				}
				}
			// if the total equals 15, then increment numOfFifteeens by 1. 
			if (value == 15) {
				++ numOfFifteens;	
			}
		}
	return numOfFifteens * 2;
	}
	
	
	/* static method cardsInRun takes as input a sorted list of 
	 * cards in a hand. This method determines whether the hand 
	 * contains a run, if so what length and how many possibilities 
	 * of the run there are. Based on the length and number of runs
	 * it will return a single integer representing the number 
	 * of points scored. 
	 * 
	 */
	public static int cardsInRun(ArrayList<String> sortedArray) {
	    int lenOfhand = sortedArray.size();
		int cardsInRun = 1;
		int duplicate = 0;
		int numberOfRuns = 0;
		CribbageRank duplicateCard = null;
		
		/* iterate through cards and determine how many, if any, are in 
		 * sequential order. Store this value in cardsInRun. If there are any
		 * duplicates of a card, increment duplicate by one and store the duplicate 
		 * value as duplicateCard
		*/ 
		for (int i = 0; i < lenOfhand - 1; i++) {
			char char1 =  sortedArray.get(i).charAt(0);
			char char2 = sortedArray.get(i+1).charAt(0);
			CribbageRank card = CribbageRank.fromString(char1);
			CribbageRank nextCard = CribbageRank.fromString(char2);
					
			if (nextCard == card.nextHigher()) {
				cardsInRun++;	
			}
			
		//  Check for duplicate cards
			else if (nextCard == card) {
		 		// if there is already a pair of the card, increment duplicate by 1
				if (card == duplicateCard) {
					duplicate ++;
				}
				// if this is the first duplicate, increment duplicate by 2 and assign 
				// dupliacteCard to the value of card.
				else {
				duplicate = duplicate + 2;	
				duplicateCard = card;
				}
				
			}	
			//  when  nextCard that is not sequential nor equal to card, then determine if there
			// is still possibility of run
			else {
				// if there is no possibility of making a run, return 0
				if (i > 2 && cardsInRun == 1) {
					return 0;
				}
				// if there hasn't already been a run of length 3, then reset cardsInRun to 1
				else if (cardsInRun< 3)
				{cardsInRun = 1;}
			}
		}
		
		// after the iteration, determine the number of runs possible and return the number 
		// of points scored 
		
		
		if (cardsInRun >= 3) {
			numberOfRuns = 1; 
		}
		
		if (cardsInRun >= 3 && duplicate == 0) {
			return cardsInRun;	
		}
		
		else if (cardsInRun >= 3 && duplicate > 0) {
			numberOfRuns = numberOfRuns * duplicate;
			return cardsInRun * numberOfRuns;
		}
		
		else { 
			return 0; 
		}

}
	
}
