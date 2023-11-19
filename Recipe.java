import java.util.*;
import java.io.*;
public class Recipe {
	private boolean debug = false;

	protected String name;
	protected int prepTime; // | In minutes
	protected int cookTime; // |
	protected int standTime;// V
	protected ArrayList<String> ingredients;
	protected ArrayList<String> requiredKitchenware;
	protected ArrayList<Recipe> includes;
	protected String description;
	protected ArrayList<String> steps;
	protected boolean servedHot;
	//Recipe(), formatAsJSON(), Recipe(JsonObject)



	/**
	 * Asks the user for information to instantiate the object with.
	 */
	public Recipe () {
	}

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
		this.description = description;
		cookTime = 0;
		standTime = 0;
		ingredients = new ArrayList<String>();
		requiredKitchenware = new ArrayList<String>();
		includes = new ArrayList<Recipe>();
		description = "";
		steps = new ArrayList<String>();
		servedHot = true;
	}

	/**
	 * Instantiates the object baseed on a JsonObject representation of it
	 * @param JsonRepresentation the JsonObject representation of the object
	 */
	//public Recipe (JsonObject myJsonObj) {}



	private static int getAnInt (Scanner stdin) {
		Integer userInput = null;
		while (userInput == null) {
			try {
				userInput = stdin.nextInt();
				stdin.nextLine();
				return userInput;
			} catch (InputMismatchException e) {
				System.out.println("Not a valid int. Enter a valid integer.");
				stdin.nextLine();
			}
		}
		return userInput;
	}



	/**
	 * Gets all included recipes, as an Arraylist
	 * @return the arrayList which includes all included recipes, meant to be edited by the end user
	 */
	public ArrayList<Recipe> getIncludedRecipes () {
		return includes;
	}

	/**
	 * Returns the total amount of time the recipe will take. Includes time for each of the subrecipes, disincluding stand time (assuming that that overlaps)
	 * @return an integer value outlining the expected time this recipe will take
	 */
	protected int getTime () {
		return prepTime + cookTime + standTime;
	}

	/**
	 * Returns the total amount of time the recipe will take, with a breakdown of it. Includes time for each of the subrecipes, disincluding stand time (assuming that that overlaps)
	 * @return a String which includes the total time, a breakdown of how that number was reached, and the same for all included recipes
	 */
	protected String getTimeAsString () {
		return "Total time: " + (prepTime + cookTime + standTime) + "\nPrep time: " + prepTime + "\nCook time: " + cookTime + "\nStand time: " + standTime;
	}

	/**
	 * Starts a timer
	 * @param time the time in minutes
	 */
	private void startTimer (int time) {}



	//  | Overridable methods below |
	//  V			      V
	/**
	 * Formats the values of the object in the JSON format
	 * @return this object, stored in the JSON format
	 */
	public String formatAsJSON () {
		return "";
	}

	/**
	 * Asks the user what they would like to edit, and changes it for them. Continues looping back to the menu unless indicated otherwise
	 * @param stdin the scanner to use
	 * @return true if all was successful, false if there was an error
	 */
	protected final String OPTIONS = "";
	public boolean editRecipe (Scanner stdin) {
		int userInput = 0;
		boolean cont = true;
		while (cont) {
			System.out.print(OPTIONS);
			System.out.println("What option would you like to choose? (Enter an integer >= 0)");
			userInput = getAnInt(stdin);
			if (userInput == 0) {
				cont = false;
			} else if (userInput >= 0) {
				editItem ( stdin, userInput );
			} else {
				System.out.println("Not a valid choice (choices are >= 0)");
			}
		}
		return true;
	}

	private void editItem (Scanner stdin, int userInput) {
		int newTime;
		String answer;
		switch (userInput) {
			case 1:
				System.out.println("What would you like to change the name to?");
				name = stdin.nextLine();
				System.out.println("Changing to \"" + name + "\".");
				break;
			case 2:
				newTime = -1;
				while (newTime < 0) {
					System.out.print("What would you like to change the prep time to? Enter an integer value: ");
					try {
						newTime = stdin.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("not a valid int");
					} finally {
						stdin.nextLine();
					}
				}
				System.out.println("Changing to " + newTime + " minutes.");
				prepTime = newTime;
				break;
			case 3:
				newTime = -1;
				while (newTime < 0) {
					System.out.print("What would you like to change the cook time to? Enter an integer value: ");
					try {
						newTime = stdin.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("not a valid int");
					} finally {
						stdin.nextLine();
					}
				}
				System.out.println("Changing to " + newTime + " minutes.");
				cookTime = newTime;
				break;
			case 4:
				newTime = -1;
				while (newTime < 0) {
					System.out.print("What would you like to change the stand time to? Enter an integer value.");
					try {
						newTime = stdin.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("not a valid int");
					} finally {
						stdin.nextLine();
					}
				}
				System.out.println("Changing to " + newTime + " minutes.");
				standTime = newTime;
				break;
			case 5:
				System.out.println("Your ingredients are: ");
				for (int i = 0; i < ingredients.size(); i++) {
					System.out.println(i + "\t" + ingredients.get(i).toString());
				}
				System.out.println("would you like to [add], [edit], or [delete] an ingredient or [exit]?");
				answer = stdin.nextLine().toLowerCase();
				while (answer != "exit") {
					switch (answer) {
						case "add":
							System.out.println("What ingredient would you like to add?");
							ingredients.add(stdin.nextLine());
							break;
						case "edit"://could use updating
							System.out.println("What index would you like to edit? (listed above)");
							int indexToEdit = getAnInt(stdin);
							try {
								ingredients.get(indexToEdit);
								System.out.println("What would you like to change it to?");
								ingredients.set(indexToEdit, stdin.nextLine());
							} catch (IndexOutOfBoundsException e) {
								System.out.println("Not a valid index");
							}
							break;
						case "delete":
							System.out.println("What index would you like to delete? (listed above)");
							int indexToDelete = getAnInt(stdin);
							try {
								ingredients.remove(indexToDelete);
							} catch (IndexOutOfBoundsException e) {
								System.out.println("Not a valid index");
							}
							break;
						default:
							System.out.println("Sorry, that is not a valid option. Valid options are encased in brackets like [] below.");
					}
					System.out.println("Your ingredients are: ");
					for (int i = 0; i < ingredients.size(); i++) {
						System.out.println(i + "\t" + ingredients.get(i).toString());
					}
					System.out.println("would you like to [add] or [edit] an item or [exit]?");
					answer = stdin.nextLine().toLowerCase();
				}
				break;
			case 6:
				System.out.println("Your required kitchenware is: ");
				for (int i = 0; i < requiredKitchenware.size(); i++) {
					System.out.println(i + "\t" + requiredKitchenware.get(i).toString());
				}
				System.out.println("would you like to [add], [edit], or [delete] a piece of kitchenware or [exit]?");
				answer = stdin.nextLine().toLowerCase();
				while (answer != "exit") {
					switch (answer) {
						case "add":
							System.out.println("What kitchenware would you like to add?");
							requiredKitchenware.add(stdin.nextLine());
							break;
						case "edit"://could use updating
							System.out.println("What index would you like to edit? (listed above)");
							int indexToEdit = getAnInt(stdin);
							try {
								requiredKitchenware.get(indexToEdit);
								System.out.println("What would you like to change it to?");
								requiredKitchenware.set(indexToEdit, stdin.nextLine());
							} catch (IndexOutOfBoundsException e) {
								System.out.println("Not a valid index");
							}
							break;
						case "delete":
							System.out.println("What index would you like to delete? (listed above)");
							int indexToDelete = getAnInt(stdin);
							try {
								requiredKitchenware.remove(indexToDelete);
							} catch (IndexOutOfBoundsException e) {
								System.out.println("Not a valid index");
							}
							break;
						default:
							System.out.println("Sorry, that is not a valid option. Valid options are encased in brackets like [] below.");
					}
					System.out.println("Your kitchenware is: ");
					for (int i = 0; i < requiredKitchenware.size(); i++) {
						System.out.println(i + "\t" + requiredKitchenware.get(i).toString());
					}
					System.out.println("would you like to [add] or [edit] an item or [exit]?");
					answer = stdin.nextLine().toLowerCase();
				}
				break;
			case 7:
				//description
				System.out.println("Your current description: \n" + description);
				System.out.println("What wpould you like to change it to?");
				description = stdin.nextLine();
				break;
			case 8:
				//steps
				System.out.println("Your current steps are: ");
				for (int i = 0; i < steps.size(); i++) {
					System.out.println(i + "\t" + steps.get(i).toString());
				}
				System.out.println("would you like to [add], [edit], or [delete] a step or [exit]?");
				answer = stdin.nextLine().toLowerCase();
				while (answer != "exit") {
					switch (answer) {
						case "add":
							System.out.println("What step would you like to add?");
							steps.add(stdin.nextLine());
							break;
						case "edit"://could use updating
							System.out.println("What index would you like to edit? (listed above)");
							int indexToEdit = getAnInt(stdin);
							try {
								steps.get(indexToEdit);
								System.out.println("What would you like to change it to?");
								steps.set(indexToEdit, stdin.nextLine());
							} catch (IndexOutOfBoundsException e) {
								System.out.println("Not a valid index");
							}
							break;
						case "delete":
							System.out.println("What index would you like to delete? (listed above)");
							int indexToDelete = getAnInt(stdin);
							try {
								steps.remove(indexToDelete);
							} catch (IndexOutOfBoundsException e) {
								System.out.println("Not a valid index");
							}
							break;
						default:
							System.out.println("Sorry, that is not a valid option. Valid options are encased in brackets like [] below.");
					}
					System.out.println("Your steps are: ");
					for (int i = 0; i < steps.size(); i++) {
						System.out.println(i + "\t" + steps.get(i).toString());
					}
					System.out.println("would you like to [add] or [edit] an item or [exit]?");
					answer = stdin.nextLine().toLowerCase();
				}
				break;
			case 9:
				//served hot
				System.out.print("Currently your dish is ");
				if (!servedHot) {
				System.out.print("not ");
				}
				System.out.println("served hot");
				System.out.println("Would you like your dish to be served hot? [yes] or [no]");
				answer = "";
				while (!(answer.equals("yes") || answer.equals("no"))) {
					answer = stdin.nextLine().toLowerCase();
				}
				if (answer.equals("yes")) {
					servedHot = true;
				} else {
					servedHot = false;
				}
				break;
			case 10:
				//display
				System.out.println(this.toString());
				break;
			case 3301:
				debug = true;
				break;
			default:
				System.out.println("Sorry, not a valid option.");

		}
	}



	/**
	 * checks if this recipe is equal to another recipe object
	 * @param otherRecipe the other recipe to compare to
	 * @return true is the two are the same, false if not
	 */
	public boolean equals (Recipe otherRecipe) {
		if (otherRecipe.getClass() != Recipe.class) {
			return false;
		}
		if ( name != otherRecipe.name ) {
			return false;
		} else if ( prepTime != otherRecipe.prepTime ) {
			return false;
		} else if ( cookTime != otherRecipe.cookTime  ) {
			return false;
		} else if ( standTime != otherRecipe.standTime ) {
			return false;
		} else if ( ingredients != otherRecipe.ingredients ) {
			return false;
		} else if ( requiredKitchenware != otherRecipe.requiredKitchenware ) {
			return false;
		} else if ( includes != otherRecipe.includes ) {
			return false;
		} else if ( description != otherRecipe.description ) {
			return false;
		} else if ( steps != otherRecipe.steps ) {
			return false;
		} else if ( servedHot != otherRecipe.servedHot ) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * returns a String representation of the object, formatted as follows:
	 * <ul><li>the name of the recipe</li><li>the time the recipe will take, as formatted by getTimeAsString()</li><li>ingredients, as a table</li><li>required cookware, as a list</li><li>The description of the recipe</li><li>whether or not this is served hot</li><li>the list of steps to take</li><li>any other recipes included in this one, laid out in the same format</li></ul>
	 * @return the String representation of this recipe
	 */
	public String toString () {
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
