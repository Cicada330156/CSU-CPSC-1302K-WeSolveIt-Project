import java.util.Scanner;
import java.io.*;
import javax.json.*;

/**
 * This class will uses extend, temp Strings, pot types, methods that
 * notoveridden, and overridden methods as well.
 * 
 * @author Ryan McKelphin
 * @version 1.1
 */

public class StovetopRecipe extends Recipe {
    private String temp;
    private String potType;

    public StovetopRecipe(String name) {
        super(name);
    }

    /**
     * We are using the StoveTopRecipe to have a name temp and pottype and keeping
     * it as a this value.
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

    // Overridden Methods VVV
    /**
     * we will override this method and use the object method to use the JSON for
     * super and put to get to use the json.
     */
    @Override
    public JSONObject formatAsJSON() {
        JSONObject json = super.toJSON();
        json.put("recipeType", "StovetopRecipe");
        json.put("temp", temp);
        json.put("potType", potType);
        return json;
    }

    /**
     * we will use the editRecipe to get the name,temp, and potType as the strings
     * and display them.
     */
    public void editRecipe(String name, String temp, String potType) {
        super.name = name;
        setTemp(temp);
        setPotType(potType);
    }

    // Options String
    protected final String OPTIONS = super.OPTIONS + "";

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
     * we will use the @Override the equals boolean to have return equals,
     * StovetopRecipe and use if statements as well
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
     * We will override the toString to have temp and pottype with returning the
     * super.toStrint().
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
