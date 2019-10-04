/** This class provides a single static method to compute all
 * combinations of objects in the input.  It also provides a main
 * method for experimentation.
 * 
 * This class has been edited from the original. The main method has been
 * removed.
 *
 * @author Peter Schachte <schachte@unimelb.edu.au>
 */


package handValue; 
import java.lang.reflect.Array;

public class Combinations {
    public Combinations() {
    }

    public static <T> Object[][] combinations(T[] list) {
        Object[][] combos = (Object[][])((Object[][])Array.newInstance(list.getClass(), (int)Math.pow(2.0D, (double)list.length)));

        for(int i = 0; i < combos.length; ++i) {
            int count = 0;

            int j;
            for(j = 0; j < list.length; ++j) {
                if ((i & 1 << j) != 0) {
                    ++count;
                }
            }

            combos[i] = (Object[])((Object[])Array.newInstance(list.getClass().getComponentType(), count));
            count = 0;

            for(j = 0; j < list.length; ++j) {
                if ((i & 1 << j) != 0) {
                    combos[i][count] = list[j];
                    ++count;
                }
            }
        }
        return combos;    
    }

}


