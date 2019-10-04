/*
 * Purpose: Create a comparator to sort through values by ordinal 
 * number 
 * 
 * This class provides a single comparator which compares two
 * given strings. The method fromString is used to convert a
 * character to a CribbageRank value and compares the ordinal value 
 * associated with each CribbageRank.
 * 
 
 */

package handValue; 

import java.util.Comparator;

public class OrdinalComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		// convert string inputs to CribbageRank objects
		CribbageRank a = CribbageRank.fromString(o1.charAt(0));
		CribbageRank b = CribbageRank.fromString(o2.charAt(0));
		// create variables x and y to represent the ordinal values 
		// of a and b.
		int x = a.ordinal();
		int y = b.ordinal();
		
		/* if the ordinal value of a is greater than that of b, then 
		 * when sorting using this comparator, o1 will be placed before o2, otherwise
		 * it will be placed after.
		 */
		
		if (x > y) {
			return 1;
		}
		else if (x < y) {
			return -1;
		}
		
		return 0;
	}
}
