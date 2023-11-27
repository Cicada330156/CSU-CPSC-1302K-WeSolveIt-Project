import java.util.InputMismatchException;
import java.util.Scanner;
import javax.json.*;

/**
 * for the BakingRecipe we will extend recipe implements uses oven
 * I will also use boolean preheat, oven temp, methods that are not overridden
 * that is get set instance variables
 * THen I will override these with format as JSON, toStrig, equals, and
 * editRecipe
 * 
 * @author Ryan McKelphin
 * @version 1.1
 *
 */
public class BakingRecipe extends Recipe implements UsesOven {
	// Instance variables
	protected boolean preheat;
	protected int ovenTemp;

	// constructors
	public BakingRecipe() {
	}

	public BakingRecipe(String name) {
		super(name);
	}

	public BakingRecipe(JsonObject myJsonObj) {
		super(myJsonObj);
		preheat = myJsonObj.getBoolean("preheat");
		ovenTemp = myJsonObj.getInt("ovenTemp");
	}

	// Getters and setters
	/**
	 * this is where we will return the preheat
	 * 
	 * @return whether or not preheat is required for this recipe
	 */
	@Override
	public boolean getPreheat() {
		return preheat;
	}

	/**
	 * this is where we will set the preheat as a boolean.
	 * 
	 * @param preheat whether this recipe should require preheat
	 */
	@Override
	public void setPreheat(boolean preheat) {
		this.preheat = preheat;
	}

	/**
	 * this is where we will return the ovenTemp and have a int getOvenTemp
	 * 
	 * @return the temperature to set the oven to
	 */
	public int getOvenTemp() {
		return ovenTemp;
	}

	/**
	 * this is where we will set the OvenTemp as a int and then call it as such.
	 * 
	 * @param ovenTemp the temperature which the oven should be set to for this
	 *                 recipe
	 */
	public void setOvenTemp(int ovenTemp) {
		this.ovenTemp = ovenTemp;
	}

	// Overridden methods VVV
	/**
	 * Formats the values of the object in the JSON format
	 * 
	 * @return this object, stored in the JSON format
	 */
	@Override
	public JsonObjectBuilder formatAsJSON() {
		JsonObjectBuilder json = super.formatAsJSON();
		json.add("recipeType", "BakingRecipe");
		json.add("preheat", preheat);
		json.add("ovenTemp", ovenTemp);
		return json;
	}

	// Options String
	protected final String OPTIONS = super.OPTIONS + "11) Edit preheat boolean\\n" + //
			"12) Edit oven temp\\n";

	/**
	 * Asks the user what they would like to edit, and changes it for them.
	 * Continues looping back to the menu unless indicated otherwise
	 * 
	 * @param stdin the scanner to use
	 * @return true if all was successful, false if there was an error
	 */
	public void editRecipe(Scanner stdin) {
		int userInput = 0;
		boolean cont = true;
		while (cont) {
			System.out.print(OPTIONS);
			System.out.println("What option would you like to choose? (Enter an integer >= 0)");
			userInput = getAnInt(stdin);
			if (userInput == 0) {
				cont = false;
			} else if ((userInput >= 0 && userInput <= 10) || userInput == 3301) {
				super.editItem(stdin, userInput);
			} else if (userInput > 10) {
				editItem(stdin, userInput);
			} else {
				System.out.println("Not a valid choice (choices are >= 0)");
			}
		}
	}

	protected void editItem(Scanner stdin, int userInput) {
		switch (userInput) {
			case 11:
				boolean newPreHeat;
				while (true) {
					System.out.print(
							"What would you like to change the preheat temp to? Enter a positive integer value in Fahrenheit: ");
					try {
						newPreHeat = stdin.nextBoolean();
						break;
					} catch (InputMismatchException e) {
						System.out.println("not a valid int");
					} finally {
						stdin.nextLine();
					}
				}
				System.out.println("Changing to " + newPreHeat + " degrees.");
				preheat = newPreHeat;
				break;
			case 12:
				int newOvenTemp = -1;
				while (newOvenTemp < 0) {
					System.out.print(
							"What would you like to change the preheat temp to? Enter a positive integer value in Fahrenheit: ");
					try {
						newOvenTemp = stdin.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("not a valid int");
					} finally {
						stdin.nextLine();
					}
				}
				System.out.println("Changing to " + newOvenTemp + " degrees.");
				ovenTemp = newOvenTemp;
				break;
			default:
				System.out.println("Sorry, not a valid option.");

		}
	}

	/**
	 * checks if this recipe is equal to another recipe object
	 * 
	 * @param otherRecipe the other recipe to compare to
	 * @return true is the two are the same, false if not
	 */
	public boolean equals(Object compareTo) {
		if (compareTo.getClass() != BakingRecipe.class) {
			return false;
		}
		BakingRecipe otherRecipe = (BakingRecipe) compareTo;
		if (!(name.equals(otherRecipe.name))) {
			return false;
		} else if (prepTime != otherRecipe.prepTime) {
			return false;
		} else if (cookTime != otherRecipe.cookTime) {
			return false;
		} else if (standTime != otherRecipe.standTime) {
			return false;
		} else if (!(ingredients.equals(otherRecipe.ingredients))) {
			return false;
		} else if (!(requiredKitchenware.equals(otherRecipe.requiredKitchenware))) {
			return false;
		} else if (!(includes.equals(otherRecipe.includes))) {
			return false;
		} else if (!(description.equals(otherRecipe.description))) {
			return false;
		} else if (!(steps.equals(otherRecipe.steps))) {
			return false;
		} else if (servedHot != otherRecipe.servedHot) {
			return false;
		} else if (preheat != otherRecipe.preheat) {
			return false;
		} else if (ovenTemp != otherRecipe.ovenTemp) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * returns a String representation of the object, formatted as follows:
	 * <ul>
	 * <li>the name of the recipe</li>
	 * <li>the time the recipe will take, as formatted by getTimeAsString()</li>
	 * <li>ingredients, as a table</li>
	 * <li>required cookware, as a list</li>
	 * <li>The description of the recipe</li>
	 * <li>whether or not this is served hot</li>
	 * <li>the list of steps to take</li>
	 * <li>any other recipes included in this one, laid out in the same format</li>
	 * </ul>
	 * 
	 * @return the String representation of this recipe
	 */
	@Override
	public String toString() {
		String descriptor = "";
		descriptor += "*** " + name + " ***";
		descriptor += "\n";
		descriptor += this.getTimeAsString();
		descriptor += "\n";
		if (servedHot) {
			descriptor += "Served hot";
		} else {
			descriptor += "Served cold";
		}
		descriptor += "\n";
		descriptor += description;
		descriptor += "\n\n";
		descriptor += "Tools required:";
		descriptor += "\n";
		for (int i = 0; i < requiredKitchenware.size(); i++) {
			descriptor += requiredKitchenware.get(i);
			descriptor += "\n";
		}
		descriptor += "Ingredients:";
		descriptor += "\n";
		for (int i = 0; i < ingredients.size(); i++) {
			descriptor += ingredients.get(i);
			descriptor += "\n";
		}
		if (preheat) {
			descriptor += "Preheat oven to " + ovenTemp + " degrees";
			descriptor += "\n";
		} else {
			descriptor += "Do not preheat oven.";
			descriptor += "\n";
		}
		descriptor += "Steps:";
		descriptor += "\n";
		for (int i = 0; i < steps.size(); i++) {
			descriptor += (i + ")\t" + steps.get(i));
			descriptor += "\n";
		}
		if (includes.size() < 0) {
			descriptor += "\n\n\n";
			descriptor += "Included recipes' instructions:";
			descriptor += "\n";
			for (int i = 0; i < includes.size(); i++) {
				descriptor += (i + ")\t" + includes.get(i).toString());
				descriptor += "\n";
			}
			descriptor += "\n";
		}
		return descriptor;
	}
}
