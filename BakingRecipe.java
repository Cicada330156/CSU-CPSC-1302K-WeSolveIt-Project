/**
 * for the BakingRecipe we will extend recipe implements uses oven
 * I will also use boolean preheat, oven temp, methods that are not overridden that is get set instance variables
 * THen I will override these with format as JSON, toStrig, equals, and editRecipe
 * @author Ryan McKelphin
 * @version 1.0
 *
 */
public class BakingRecipe extends Recipe implements UsesOven {
    private boolean preheat;
    private int ovenTemp;
/**
*this is where we use the getpreheat method to return preheat as a boolean
*/
  public boolean getPreheat() { return this.preheat; }
   /**
  * this is where we use the setPreheat boolean to cause the prheat to equal itself.
  */
  public void setPreheat(boolean preheat) { this.preheat = preheat; }

}
