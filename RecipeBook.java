
/**
 * Driver code that implements the Recipe class.
 *
 * @author Sean Hendricks, Ryan Mckelphin, Ari-Betan Snook
 * @version 1.0
 */
import java.util.Scanner;
import java.util.ArrayList;
import javax.json.*;
import java.io.*;

// case 2: inform user when their inputted recipe is not in recipe ArrayList
public class RecipeBook {

	public static void main(String[] args) {
		System.out.println("Welcome to your recipe manager!\n");

		Scanner echo = new Scanner(System.in);
		ArrayList<String> optionsMenu = new ArrayList<String>();
		optionsMenu.add("Options:");
		optionsMenu.add("1) Add a recipe");
		optionsMenu.add("2) Edit a recipe");
		optionsMenu.add("3) List a recipe");
		optionsMenu.add("4) List all recipes");
		optionsMenu.add("5) Output recipes to a file");
		optionsMenu.add("0) Quit");

		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		// ArrayList<String> recipes = new ArrayList<String>();

		String select = "";
		while (!select.equals("0")) {
			for (int i = 0; i < optionsMenu.size(); i++) {
				System.out.println(optionsMenu.get(i));
			}
			System.out.print("Please select from the following options: ");
			select = echo.nextLine();

			switch (select) {
				case "1":
					// option 1 code block creates a new recipe object.
					String oneSelect = "";
					while (!oneSelect.equals("0")) {
						System.out.println("Will you be using: \nA) An Oven \nB) A Grill \nC) A Stovetop \n0) Back");
						System.out.print("Please make a selection (A, B, C, or 0): ");
						oneSelect = echo.nextLine();

						if (oneSelect.equalsIgnoreCase("A")) {
							System.out.println("You have selected 'Oven'.");
							System.out.print("Please enter the name of the Baking Recipe: ");
							oneSelect = echo.nextLine();
							Recipe newRecipe = new BakingRecipe(oneSelect);
							System.out.println("New Baking Recipe added: " + newRecipe.getName());
							recipes.add(newRecipe);
						} else if (oneSelect.equalsIgnoreCase("B")) {
							System.out.println("You have selected 'Grill'.");
							System.out.print("Please enter the name of the Grill Recipe: ");
							oneSelect = echo.nextLine();
							Recipe newRecipe = new GrillRecipe(oneSelect);
							System.out.println(" New Grill Recipe added: " + newRecipe.getName());
							recipes.add(newRecipe);
						} else if (oneSelect.equalsIgnoreCase("C")) {
							System.out.println("You have selected 'Stovetop'.");
							System.out.print("Please enter the name of the Stovetop Recipe: ");
							oneSelect = echo.nextLine();
							Recipe newRecipe = new StovetopRecipe(oneSelect);
							System.out.println("New Stovetop Recipe added: " + newRecipe.getName());
							recipes.add(newRecipe);
						} else if (oneSelect.equalsIgnoreCase("0")) {
							System.out.println("Returning to main options menu.");
							break;
						} else {
							System.out.println(oneSelect + " is not a valid option.");
						}
						oneSelect = "";
					}
					break;
				case "2":
					// option 2 code block edits an existing recipe object.
					String twoSelect = "";
					while (!twoSelect.equals("0")) {
						System.out.println("Current Recipes:");
						for (Recipe rec : recipes) {
							System.out.println(rec.getName());
						}
						System.out
								.print("Enter the name of the recipe you would like to edit (or enter 0 to return): ");
						twoSelect = echo.nextLine();
						System.out.println("You entered " + twoSelect);
						for (Recipe rec : recipes) {
							if (twoSelect.equals(rec.getName())) {
								rec.editRecipe(echo);
							}
						}
					}
					twoSelect = "";
					break;
				case "3":
					// option 3 code block displays a recipe.
					String threeSelect = "";
					System.out.print("Enter the name of the recipe you would like to display: ");
					threeSelect = echo.nextLine();
					System.out.println("You entered " + threeSelect);
					System.out.println();
					for (Recipe currentRecipe : recipes) {
						if (currentRecipe.getName().equals(threeSelect)) {
							System.out.println(currentRecipe);
							break;
						}
					}
					break;
				case "4":
					// option 4 code block displays all recipes.
					System.out.println("This code will display every recipe object.");
					System.out.println();
					for (int i = 0; i < recipes.size(); i++) {
						System.out.println(recipes.get(i));
					}
					System.out.println();
					break;
				case "5":
					// option 5 code block outputs each recipe to a file
					String fiveSelect = "";
					while (!fiveSelect.equalsIgnoreCase("yes") && !fiveSelect.equalsIgnoreCase("no")) {
						System.out
								.print("Are you sure you want to write your current recipes to a file? (yes or no): ");
						fiveSelect = echo.nextLine();
						if (fiveSelect.equalsIgnoreCase("no")) {
							System.out.println("Returning to main menu");
							break;
						} else if (fiveSelect.equalsIgnoreCase("yes")) {
							System.out.print("Enter the name of the file you want to create and write to: ");
							fiveSelect = echo.nextLine();
						} else {
							System.out.println("Sorry, not a valid option (yes or no).");
						}
					}
					break;
				case "6":
					// option 6 code block formats the recipe in JSON
					String sixSelect = "";
					while (!sixSelect.equalsIgnoreCase("yes") && !sixSelect.equalsIgnoreCase("no")) {
						System.out
								.print("Are you sure you want to write your current recipes to a file? (yes or no): ");
						sixSelect = echo.nextLine();
						if (sixSelect.equalsIgnoreCase("no")) {
							System.out.println("Returning to main menu");
							break;
						} else if (sixSelect.equalsIgnoreCase("yes")) {
							System.out.print("Enter the name of the file you want to create and write to: ");
							File fileToSaveTo = new File(echo.nextLine());
							if (fileToSaveTo.exists()) {
								System.out.println("File already exists. Would you like to overwrite? [yes] or [no]?");
								String response0 = echo.nextLine();
								while (!response0.equalsIgnoreCase("yes") && !response0.equalsIgnoreCase("no")) {
									System.out.println("Not a valid answer. Enter [yes] or [no].");
									response0 = echo.nextLine();
								}
								if (response0.equalsIgnoreCase("no")) {
									break;
								} else {
									try {
										fileToSaveTo.createNewFile();
									} catch (IOException e) {
										System.out.println("Cannot create file");
									}
								}
							} else {
								try {
									fileToSaveTo.createNewFile();
								} catch (IOException e) {
									System.out.println("Cannot create file");
								}
							}

							JsonArrayBuilder arrayToExport = Json.createArrayBuilder();
							for (Recipe recipe : recipes) {
								arrayToExport.add(recipe.formatAsJSON());
							}
							FileWriter writer = null;
							try {
								writer = new FileWriter(fileToSaveTo);
								writer.write(arrayToExport.build().toString());
							} catch (IOException e) {
								e.printStackTrace();
							} finally {
								try {
									writer.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						} else {
							System.out.println("Sorry, not a valid option (yes or no).");
						}
					}
					break;
				case "7":
					// loads recipes from a json file
					System.out.println("What file would you like to load from?");
					File myFile = new File(echo.nextLine());
					while (!myFile.isFile()) {
						System.out.println("Not a valid file\nEnter another");
						myFile = new File(echo.nextLine());
					}
					JsonObjectBuilder jsonIncludes = Json.createObjectBuilder();
					// ArrayList<JsonArray> jsonIncludes = new ArrayList<JsonArray>();
					try {
						JsonValue fileJson = Json.createReader(new FileReader(myFile)).read();
						if (fileJson.getValueType().equals(JsonValue.ValueType.ARRAY)) {
							for (JsonValue r : (JsonArray) fileJson) {
								if (r.getValueType().equals(JsonValue.ValueType.OBJECT)) {
									JsonObject nextRecipe = (JsonObject) r; // should ensure eventually that duplicate
																			// names cannot occurr
									jsonIncludes.add(nextRecipe.getString("name"), nextRecipe.getJsonArray("includes"));
									recipes.add(new Recipe((JsonObject) fileJson));
								} else {
									System.err.println("Problem parsing file");
								}
							}
						} else if (fileJson.getValueType().equals(JsonValue.ValueType.ARRAY)) {
							recipes.add(new Recipe((JsonObject) fileJson));
						}
					} catch (FileNotFoundException e) {
						System.err.println("Error: check for file exists did not work");
					}
					JsonObject jsonIncludesObject = jsonIncludes.build();
					for (Recipe r : recipes) {
						if (jsonIncludesObject.getJsonArray(r.getName()) != null) {
							for (JsonValue ar : jsonIncludesObject.getJsonArray(r.getName())) {
								JsonArray arCasted = (JsonArray) ar;
								for (JsonValue st : arCasted) {
									String stCasted = st.toString();
									for (Recipe recipe : recipes) {
										if (stCasted.equals(recipe.getName())) {
											r.getIncludedRecipes().add(recipe);
										}
									}
								}
							}
						}
					}
					break;
				case "0":
					// option 0 code block closes the program.
					System.out.println("Goodbye!");
					break;
				default:
					System.out.println("Sorry, not a valid option.");
					break;
			}
		}
	}
}
