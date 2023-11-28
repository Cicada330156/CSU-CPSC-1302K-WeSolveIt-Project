import java.util.Scanner;
import javax.json.*;

/**
 * This class will uses extend, temp Strings, pot types, methods that
 * notoveridden, and overridden methods as well.
 * 
 * @author Ryan McKelphin
 * @version 1.1
 */

public class StovetopRecipe extends Recipe {
    // INSTANCE VARS
    private String temp;
    private String potType;

    // CONSTRUCTORS
    /**
     * Constructs the object as in @see Recipe()
     * sets preheat to true and grillTemp to a placeholder value of -1
     */
    public StovetopRecipe() {
        super();
        temp = "";
        potType = "";
    }

    /**
     * Calls the parent Recipe(String name)
     * sets preheat to true and grillTemp to a placeholder of -1
     * 
     * @param name the name of the new Recipe
     */
    public StovetopRecipe(String name) {
        super(name);
        temp = "";
        potType = "";
    }

    /**
     * Instantiates the object based on its JsonObject representation
     * 
     * @throws javax.json.JsonException if the name does not load correctly. Else,
     *                                  prints an error to console and moves on.
     * @param JsonRepresentation the JsonObject representation of the object
     */
    public StovetopRecipe(JsonObject myJsonObj) {
        super(myJsonObj);
        try {
            temp = myJsonObj.getString("temp");
        } catch (javax.json.JsonException e) {
            System.out.println("error parsing temp for object " + name);
        }
        try {
            potType = myJsonObj.getString("potType");
        } catch (javax.json.JsonException e) {
            System.out.println("error parsing potType for object " + name);
        }
    }

    // GETTERS AND SETTERS
    /**
     * Returns the stove temp
     * 
     * @return a string representing the stove's set temperature
     */
    public String getTemp() {
        return temp;
    }

    /**
     * Sets the recommended temperature of the stove
     * 
     * @param the temperature to be set
     */
    public void setTemp(String temp) {
        this.temp = temp;
    }

    /**
     * Returns the intended type of pot for this recipe
     * 
     * @return the type of pot to be used
     */
    public String getPotType() {
        return potType;
    }

    /**
     * Sets the intended pot type for this recipe
     * 
     * @param the pot type to be used
     */
    public void setPotType(String potType) {
        this.potType = potType;
    }

    // Overridden Methods VVV
    /**
     * Formats the values of the object in the JSON format
     * 
     * @return this object, stored in the JSON format
     */
    @Override
    public JsonObjectBuilder formatAsJSON() {
        JsonObjectBuilder json = super.formatAsJSON();
        json.add("recipeType", "StovetopRecipe");
        json.add("temp", temp);
        json.add("potType", potType);
        return json;
    }

    // Options String
    protected final String OPTIONS = super.OPTIONS + "11) Edit stove temp\n" + //
            "Edit pot type\n";

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
                String newTemp = "";
                while (newTemp.toLowerCase() != "lo" && newTemp.toLowerCase() != "med"
                        && newTemp.toLowerCase() != "hi") {
                    System.out.print("What would you like to change the temperature to? Enter [Lo], [Med], or [Hi]: ");
                    newTemp = stdin.nextLine();
                }
                System.out.println("Changing to " + newTemp + " degrees.");
                temp = newTemp;
                break;
            case 12:
                String newPotType = "";
                System.out.print("What would you like to change the pot type to?");
                newPotType = stdin.nextLine();
                System.out.println("Changing to " + newPotType);
                potType = newPotType;
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
        if (compareTo.getClass() != StovetopRecipe.class) {
            return false;
        }
        StovetopRecipe otherRecipe = (StovetopRecipe) compareTo;
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
        } else if (!(temp.equals(otherRecipe.temp))) {
            return false;
        } else if (!(potType.equals(otherRecipe.potType))) {
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
        descriptor += "This recipe requires a " + potType;
        descriptor += "\n";
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
