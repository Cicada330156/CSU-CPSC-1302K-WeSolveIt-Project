import org.json.JSONObject;

/**
 * This class will uses extend, temp Strings, pot types, methods that notoveridden, and overridden methods as well.
 *@author Ryan McKelphin
 *@version 1.0
 */

public class StovetopRecipe extends Recipe {
	private String temp;
	private String potType;


	/**
	 * We are using the StoveTopRecipe to have a name temp and pottype and keeping it as a this value.
	 */
	// Constructor
	public StovetopRecipe(String name, String temp, String potType) {
		super(name);
		this.temp = temp;
		this.potType = potType;
	}

	// Getters and Setters
	/**
	 * We will use the get method to use the getTemp to return the temp.
	 */
	public String getTemp() {
		return temp;
	}
	/**
	 * We will use the set method to use the set the temp.
	 */
	public void setTemp(String temp) {
		this.temp = temp;
	}
	/**
	 * We will use the get method to use the getPotType to return the potType.
	 */
	public String getPotType() {
		return potType;
	}
	/**
	 * We will use the set method to use the setPotType for a stringpotType.
	 */
	public void setPotType(String potType) {
		this.potType = potType;
	}

	// Overridden Methods
	/**
	 *we will override this method and use the object method to use the JSON for super and put to get to use the json.
	 */
	@Override
	public JSONObject toJSON() {
		JSONObject json = super.toJSON();
		json.put("temp", temp);
		json.put("potType", potType);
		return json;
	}
	/**
	 *We will override the toString to have temp and pottype with returning the super.toStrint().
	/*
	@Override
	public String toString() {
	return super.toString() + ", Temp: " + temp + ", Pot Type: " + potType;
	}
	/**
	 * we will use the @Override the equals boolean to have return equals, StovetopRecipe and use if statements as well
	/*
	@Override
	public boolean equals(Object obj) {
	if (this == obj) return true;
	if (!(obj instanceof StovetopRecipe)) return false;
	StovetopRecipe other = (StovetopRecipe) obj;
	return super.equals(obj) && temp.equals(other.temp) && potType.equals(other.potType);
	}

	/**
	 *we will use the editRecipe to get the name,temp, and potType as the strings and display them.
	/*
	public void editRecipe(String name, String temp, String potType) {
	super.setName(name);
	setTemp(temp);
	setPotType(potType);
	}
}
