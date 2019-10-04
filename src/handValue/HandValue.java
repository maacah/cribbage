/*
 * Purpose: Evaluate a hand
 * Last Modified: 13-10-2018
 * 
 * This a is class that keeps track of the points scored by a
 * given hand. This class contains a main method which is used 
 * for taking a 5 part string as input on the command line, 
 * creating a list of possible hand combinations and ultimately, 
 * printing an integer as output that representing the points 
 * scored by the given input. 
 * Author: Maacah Martins 
 * Peter Schachte's code from Combinations has been added to main method
 * 
 */

package handValue; 
import java.util.ArrayList;
import java.util.Collections;

public class HandValue{ 
	
	// Initialize Array Lists  
	private static ArrayList<String> handCombinations = new ArrayList<String>();
	private static ArrayList<String> hand = new ArrayList<String>();
	
	
	// static method sortList is used to sort a given array
	// using the OrdinalComparator class
	public static ArrayList<String> sortList(ArrayList<String> needsSorting) {
		Collections.sort(needsSorting, new OrdinalComparator());
		return needsSorting;
	}
	
	
	// Main method is used to take input on the command line
	// and return a single integer
	public static void main(String[] args) {
		
			// add each input string to hand
		    hand.add(args[0]);
	        hand.add(args[1]);
	        hand.add(args[2]);
	        hand.add(args[3]);
	        hand.add(args[4]);
	        
	        // Generate combinations, and add each
	        // to handCombinations
		    String[][] lines = (String[][])Combinations.combinations(args);
	        String[][] var2 = lines;
	        int var3 = lines.length;

	        for(int var4 = 0; var4 < var3; ++var4) {
	            String[] line = var2[var4];
	            String[] var6 = line;
	            int var7 = line.length;
	            String concated = "";
	            
	            
	            for(int var8 = 0; var8 < var7; ++var8) {
	                String str = var6[var8];
	                concated = concated + str.charAt(0);     
	            }
	            handCombinations.add(concated);     
	        }
	        
	        /* Create an integer variable for each point 
	         * scoring method. 
	         * Create an integer variable, totalPoints that 
	         * is equal to the sum of all points scored 
	         */
	       
	        int pointsForNob = PointScorers.oneForHisNob(hand);
	    	int pointsForFifteens = PointScorers.fifteens(handCombinations);
	    	int pointsForPairs = PointScorers.pairs(handCombinations);
	    	int pointsForAFlush = PointScorers.flush(hand);
	    	int pointsForRuns = PointScorers.cardsInRun(sortList(hand));
	    	int totalPoints = pointsForNob + pointsForFifteens 
	    			+ pointsForPairs + pointsForAFlush + pointsForRuns;
	    
	        System.out.println(totalPoints);
	              
	}
}	

	 
	        	
	

			
