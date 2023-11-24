/**
 * for the BakingRecipe we will extend recipe implements uses oven
 * I will also use boolean preheat, oven temp, methods that are not overridden that is get set instance variables
 * THen I will override these with format as JSON, toStrig, equals, and editRecipe
 * @author Ryan McKelphin
 * @version 1.0
 *
 */
public class BakingRecipe extends Recipe implements UsesOven {
    // Instance variables
    private boolean preheat;
    private int ovenTemp;

    // Getters and setters
    /**
    * this is where we will return the preheat
    */
    public boolean getPreheat() {
        return preheat;
    }
    /**
    * this is where we will set the preheat as a boolean.
    */
    public void setPreheat(boolean preheat) {
        this.preheat = preheat;
    }
    /**
    * this is where we will return the ovenTemp and have a int getOvenTemp
    */
    public int getOvenTemp() {
        return ovenTemp;
    }
    /**
    * this is where we will set the OvenTemp as a int and then call it as such.
    */
    public void setOvenTemp(int ovenTemp) {
        this.ovenTemp = ovenTemp;
    }

    // Implemented methods from UsesOven interface
    /**
    * this is where we Override preheat and  have a boolean preheat method that will return preheat.
    */
    @Override
    public boolean preheat() {
        return preheat;
    }
    /**
    * this is where we Override the ovenTemp to a int.
    */
    @Override
    public int ovenTemp() {
        return ovenTemp;
    }
}
