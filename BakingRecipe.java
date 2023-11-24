/**
 * for the BakingRecipe we will extend recipe implements uses oven
 * I will also use boolean preheat, oven temp, methods that are not overridden that is get set instance variables
 * THen I will override these with format as JSON, toStrig, equals, and editRecipe
 * @author Ryan McKelphin
 * @version 1.1
 *
 */
public class BakingRecipe extends Recipe implements UsesOven {
	// Instance variables
	private boolean preheat;
	private int ovenTemp;

	// Getters and setters
	/**
	 * this is where we will return the preheat
	 * @return whether or not preheat is required for this recipe
	 */
	@override
	public boolean getPreheat() {
		return preheat;
	}
	/**
	 * this is where we will set the preheat as a boolean.
	 * @param preheat whether this recipe should require preheat
	 */
	@override
	public void setPreheat(boolean preheat) {
		this.preheat = preheat;
	}
	/**
	 * this is where we will return the ovenTemp and have a int getOvenTemp
	 * @return the temperature to set the oven to
	 */
	public int getOvenTemp() {
		return ovenTemp;
	}
	/**
	 * this is where we will set the OvenTemp as a int and then call it as such.
	 * @param ovenTemp the temperature which the oven should be set to for this recipe
	 */
	public void setOvenTemp(int ovenTemp) {
		this.ovenTemp = ovenTemp;
	}
}
