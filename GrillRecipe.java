import java.util.InputMismatchException;
import java.util.Scanner;
import javax.json.*;

/**
 * In this class we will extend recipe, use instance variables, boolean, a grill
 * temp as well.
 * Then we will use methods get or set variables and the override these methods.
 * 
 * @author Ryan McKelphin
 * @version 1.1
 */
public class GrillRecipe extends Recipe {
    private boolean preheat;
    private int grillTemp;

    // Constructor
    /**
     * this is where we will declare a boolean and a int for grillTemp and preheat.
     */
    public GrillRecipe(boolean preheat, int grillTemp) {
        this.preheat = preheat;
        this.grillTemp = grillTemp;
    }

    public GrillRecipe(String name) {
        super(name);
        this.preheat = true;
        this.grillTemp = -1;
    }

    public GrillRecipe(JsonObject myJsonObj) {
        super(myJsonObj);
        preheat = myJsonObj.getBoolean("preheat");
        grillTemp = myJsonObj.getInt("grillTemp");
    }

    public GrillRecipe() {
        this.preheat = true;
        this.grillTemp = -1;
    }

    // Getter and Setter methods
    /**
     * This is where we use a get method for the preheat to return.
     */
    public boolean getPreheat() {
        return this.preheat;
    }

    /**
     * This is where we use a set method for the preheat as a boolean.
     */
    public void setPreheat(boolean preheat) {
        this.preheat = preheat;
    }

    /**
     * This is where we use a get method for the grillTemp to return.
     */
    public int getGrillTemp() {
        return this.grillTemp;
    }

    /**
     * This is where we use a set method for the grillTemp to set as a int.
     */
    public void setGrillTemp(int grillTemp) {
        this.grillTemp = grillTemp;
    }

    // Overridden methods
    // Method to format the object as JSON
    /**
     * this is were we format a String as a JSON and return them with preheat and
     * even the grillTemp.
     */
    public JsonObjectBuilder formatAsJSON() {
        JsonObjectBuilder json = super.formatAsJSON();
        json.add("recipeType", "GrillRecipe");
        json.add("preheat", preheat);
        json.add("grillTemp", grillTemp);
        return json;
    }

    /**
     * Method to edit the recipe
     * We are going to edit the Recipe to the boolean preheat and the int grillTemp.
     */
    public void editRecipe(boolean preheat, int grillTemp) {
        this.preheat = preheat;
        this.grillTemp = grillTemp;
    }

    // Options String
    protected final String OPTIONS = super.OPTIONS + "11) Edit preheat boolean\n" + //
            "12) Edit grill temp\n";

    /**
     * Method to edit the recipe
     * We are going to edit the Recipe to the boolean preheat and the int grillTemp.
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
     * this is where we override the equals object and use if statements for the
     * grillTemp and also other classes as well
     * we will use the @Override the equals boolean to have return equals,
     * StovetopRecipe and use if statements as well
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
     * this is where we override the toString method for the return of a grillRecipe
     * array.
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
