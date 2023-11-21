/**
 * Driver code that implements the Recipe class.
 *
 * @author Sean Hendricks, Ryan Mckelphin, Ari-Betan Snook
 * @version 1.0
 */
import java.util.Scanner;
import java.util.ArrayList;
public class RecipeBook{

	public static void main( String[] args ){
		System.out.println( "Welcome to your recipe manager!\n" );
		
		Scanner echo = new Scanner( System.in );
		ArrayList<String> optionsMenu = new ArrayList<String>();
		optionsMenu.add( "Options:" );
		optionsMenu.add( "1) Add a recipe" );
		optionsMenu.add( "2) Edit a recipe" );
		optionsMenu.add( "3) List a recipe" );
		optionsMenu.add( "4) List all recipes" );
		optionsMenu.add( "5) Quit" );

		String select = "";
		while( ! select.equals( "5" )){
			for( int i = 0; i < optionsMenu.size(); i++ ){
				System.out.println( optionsMenu.get( i ));
			}
			System.out.print( "Please select from the following options: " );
			select = echo.nextLine();

			switch( select ){
				case "1":
					// option 1 code block creates a new recipe object.
					String oneSelect = "";
					System.out.println( "Will you be using: \nA) An Oven \nB) A Grill \nC) A Stove Top" );
					System.out.print( "Please make a selection: " );
					oneSelect = echo.nextLine();
					break;
				case "2":
					// option 2 code block edits an existing recipe object.
					String twoSelect = "";
					System.out.print( "Enter the name of the recipe you would like to edit: " );
					twoSelect = echo.nextLine();
					break;
				case "3":
					// option 3 code block displays a recipe.
					String threeSelect = "";
					System.out.print( "Enter the name of the recipe you would like to display: " );
					threeSelect = echo.nextLine();
					break;
				case "4":
					// option 4 code block displays all recipes.
					System.out.println( "This code will display every recipe object." );
					break;
				case "5":
					// option 5 code block closes the program.
					System.out.println( "Goodbye!" );
					break;
			}
		}
	}
}
