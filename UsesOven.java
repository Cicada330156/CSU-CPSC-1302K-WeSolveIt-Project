/**
 * We will use this class/ interface for instance variables, a boolean preheat.
 * We will also use methods that include get or set methods for the preheat.
 * @author Ryan McKelphin
 * @version 1.0
 *
 */

public class UsesOven {
private boolean preheat;

    /**
* We are using an interface to UsesOven for finding out the preheat with a boolean.
*
*/
    public Oven() {
        this.preheat = false;
    }

   /**
    * We are talking about the getting the return of preheat
    *
    */
    public boolean getPreheated() {
        return this.preheat;
    }
    /**
    * We are talking about the setting the preheat
    *
    */
    public void setPreheat(boolean preheat) {
        this.preheat = preheat;
    }
}


