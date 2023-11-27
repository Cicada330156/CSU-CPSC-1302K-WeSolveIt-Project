
/**
 * Driver code that implements the Recipe class.
 *
 * @author Sean Hendricks, Ryan Mckelphin, Ari-Betan Snook
 * @version 1.0
 */
import java.util.Scanner;
import java.util.ArrayList;

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
					while( ! oneSelect.equals( "0" )){
						System.out.println( "Will you be using: \nA) An Oven \nB) A Grill \nC) A Stovetop \n0) Back" );
						System.out.print( "Please make a selection (A, B, C, or 0): " );
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
							System.out.println(" New Grill Recipe added: " + newRecipe.getName() );
							recipes.add( newRecipe );
						}
						else if( oneSelect.equalsIgnoreCase( "C" )){
							System.out.println( "You have selected 'Stovetop'." );
							System.out.print( "Please enter the name of the Stovetop Recipe: " );
							oneSelect = echo.nextLine();
							Recipe newRecipe = new StovetopRecipe(oneSelect);
							System.out.println( "New Stovetop Recipe added: " + newRecipe.getName() );
							recipes.add( newRecipe );
						}
						else if( oneSelect.equalsIgnoreCase( "0" )){
							System.out.println( "Returning to main options menu." );
							break;
						} 
						else{
							System.out.println(oneSelect + " is not a valid option.");
						}
						oneSelect = "";
					}
					break;
				case "2":
					// option 2 code block edits an existing recipe object.
					String twoSelect = "";
					while( ! twoSelect.equals( "0" )){
						System.out.print( "Enter the name of the recipe you would like to edit (or enter 0 to return): " );
						twoSelect = echo.nextLine();
						System.out.println( "You entered " + twoSelect );
						for( Recipe rec : recipes ){
							if( twoSelect.equals( rec.getName() )){
								rec.editRecipe( echo );
							}
						}
						System.out.println( "ERROR: Recipe not found." );
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
					System.out.println("Not a recipe here.");
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
				case "0":
					// option 5 code block closes the program.
					System.out.println("Goodbye!");
					break;
				default:
					System.out.println("Sorry, not a valid option.");
					break;
			}
		}
	}
}
