import java.util.ArrayList;
Public class Recipe {
	protected String name;
	protected int prepTime; // | In minutes
	protected int cookTime; // |
	protected int standTime;// V
	protected ArrayList<String> ingredients;
	protected Arraylist<String> requiredKitchenware;
	protected ArrayList<Recipe> includes;
	protected String description;
	protected ArrayList<String> steps;
	protected boolean servedHot;



	/**
	 * Instantiates object with only a name. Calls the Recipe(String name, String description) as Recipe(name, "") 
	 * @param name the name of this new recipe
	 */
	public Recipe (String name) {
		this(name, "");
	}

	/**
	 * Instantiates the object with a name and description. Sets all other Strings to "" and all other ints to 0. Instantiates empty ArrayLists.
	 * @param name The name of this new recipe
	 * @param description The full length description of this recipe's product
	 */
	public Recipe (String name, String description) {
		this.name = name;
		this.description = desscription;
		cookTime = 0;
		standTime = 0;
		ingredients = new ArrayList<String>();
		/*
		   requiredKitchenware;
		   includes;
		   description;
		   steps;
		   servedHot;
		   */
	}



	/**
	 * Returns the total amount of time the recipe will take. Includes time for each of the subrecipes, disincluding stand time (assuming that that overlaps)
	 * @return an integer value outlining the expected time this recipe will take
	 */
	protected int getTime () {
		
	}

	/**
	 * Returns the total amount of time the recipe will take, with a breakdown of it. Includes time for each of the subrecipes, disincluding stand time (assuming that that overlaps)
	 * @return a String which includes the total time, a breakdown of how that number was reached, and the same for all included recipes
	 */
	protected String getTimeAsString () {
		
	}
	
	/**
	 * Starts a timer
	 * @param time the time in minutes
	 */
	public void startTimer (int time) {}



	/**
	 * Formats the values of the object in the JSON format
	 * @return this object, stored in the JSON format
	 */
	public String formatAsJSON () {}

	/**
	 * Asks the user what they would like to edit, and changes it for them. Continues looping back to the menu unless indicated otherwise
	 * @param stdin the scanner to use
	 * @return true if all was successful, false if there was an error
	 */
	public boolean editRecipe (Scanner stdin) {}



	/**
	 * checks if this recipe is equal to another recipe object
	 * @param otherRecipe the other recipe to compare to
	 * @return true is the two are the same, false if not
	 */
	public boolean equals (Recipe otherRecipe) {}

	/**
	 * returns a String representation of the object, formatted as follows:
	 * <ul>
	 * 	<li>
	 * 	the name of the recipe
	 * 	</li>
	 * 	<li>
	 * 	the time the recipe will take, as formatted by getTimeAsString()
	 * 	</li>
	 * 	<li>
	 * 	ingredients, as a table
	 * 	</li>
	 * 	<li>
	 * 	required cookware, as a list
	 * 	</li>
	 * 	<li>
	 *	The description of the recipe
	 * 	</li>
	 * 	<li>
	 * 	whether or not this is served hot
	 * 	</li>
	 * 	<li>
	 * 	the list of steps to take
	 * 	</li>
	 * 	<li>
	 * 	any other recipes included in this one, laid out in the same format
	 * 	</li>
	 * </ul>
	 * @return the String representation of this recipe
	 */
	public String toString () {}
}
