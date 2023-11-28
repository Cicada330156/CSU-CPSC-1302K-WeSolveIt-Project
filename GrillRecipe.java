import java.util.InputMismatchException;
import java.util.Scanner;
import javax.json.*;

/**
 * In this class we will extend recipe, adding the instance variables preheat
 * and grill temp as well.
 * Then we will use methods get or set variables and the override these methods.
 * 
 * @author Ryan McKelphin
 * @version 1.1
 */
public class GrillRecipe extends Recipe {
    // INSTANCE VARS
    private boolean preheat;
    private int grillTemp;

    // CONSTRUCTORS
    /**
     * Constructs the object as in @see Recipe()
     * sets preheat to true and grillTemp to a placeholder value of -1
     */
    public GrillRecipe() {
        super();
        preheat = true;
        grillTemp = -1;
    }

    /**
     * Calls the parent Recipe(String name)
     * sets preheat to true and grillTemp to a placeholder of -1
     * 
     * @param name the name of the new Recipe
     */
    public GrillRecipe(String name) {
        super(name);
        preheat = true;
        grillTemp = -1;
    }

    /**
     * Instantiates the object based on its JsonObject representation
     * 
     * @throws javax.json.JsonException if the name does not load correctly. Else,
     *                                  prints an error to console and moves on.
     * @param JsonRepresentation the JsonObject representation of the object
     */
    public GrillRecipe(JsonObject myJsonObj) {
        super(myJsonObj);
        try {
            preheat = myJsonObj.getBoolean("preheat");
        } catch (javax.json.JsonException e) {
            System.out.println("error parsing preheat for object " + name);
        }
        try {
            grillTemp = myJsonObj.getInt("grillTemp");
        } catch (javax.json.JsonException e) {
            System.out.println("error parsing grillTemp for object " + name);
        }
    }

    // GETTERS AND SETTERS
    /**
     * Returns the value of the preheat variable
     * 
     * @return true if a preheated grill is needed, false if not
     */
    public boolean getPreheat() {
        return this.preheat;
    }

    /**
     * Sets the preheat variable
     * 
     * @param preheat whether or not the user should preheat the oven
     */
    public void setPreheat(boolean preheat) {
        this.preheat = preheat;
    }

    /**
     * Returns the temperature to set the grill to
     * 
     * @return the temperature which the grill should be set to
     */
    public int getGrillTemp() {
        return this.grillTemp;
    }

    /**
     * Sets the grill temp to be used
     * 
     * @param the required temperature
     */
    public void setGrillTemp(int grillTemp) {
        this.grillTemp = grillTemp;
    }

    // OVERRIDDEN METHODS
    /**
     * Formats the values of the object in the JSON format
     * 
     * @return this object, stored in the JSON format
     */
    public JsonObjectBuilder formatAsJSON() {
        JsonObjectBuilder json = super.formatAsJSON();
        json.add("recipeType", "GrillRecipe");
        json.add("preheat", preheat);
        json.add("grillTemp", grillTemp);
        return json;
    }

    // Options String
    protected final String OPTIONS = super.OPTIONS + "11) Edit preheat boolean\n" + //
            "12) Edit grill temp\n";

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
            userInput = super.getAnInt(stdin);
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
                            "Will you need to preheat the grill? (Enter true or false): ");
                    try {
                        newPreHeat = stdin.nextBoolean();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("not a valid option (true or false)");
                    } finally {
                        stdin.nextLine();
                    }
                }
                System.out.println("Changing to " + newPreHeat + ".");
                preheat = newPreHeat;
                break;
            case 12:
                int newGrillTemp = -1;
                while (newGrillTemp < 0) {
                    System.out.print(
                            "What would you like to change the grill temp to? Enter a positive integer value in Fahrenheit: ");
                    try {
                        newGrillTemp = stdin.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("not a valid int");
                    } finally {
                        stdin.nextLine();
                    }
                }
                System.out.println("Changing to " + newGrillTemp + " degrees.");
                grillTemp = newGrillTemp;
                break;
            default:
                System.out.println("Sorry, not a valid option.");

        }
    }

    /**
     * checks if this recipe is equal to another object
     * 
     * @param otherRecipe the other recipe to compare to
     * @return true if the two are the same, false if not
     */
    @Override
    public boolean equals(Object compareTo) {
        if (compareTo.getClass() != GrillRecipe.class) {
            return false;
        }
        GrillRecipe otherRecipe = (GrillRecipe) compareTo;
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
        } else if (grillTemp != otherRecipe.grillTemp) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * returns a String representation of the object, formatted as to be displayed
     * to the end user.
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
            descriptor += "Preheat grill to " + grillTemp + " degrees";
            descriptor += "\n";
        } else {
            descriptor += "Do not preheat grill.";
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
