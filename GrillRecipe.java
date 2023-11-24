/**
 * In this class we will extend recipe, use instance variables, boolean, a grill temp as well.
 * Then we will use methods get or set variables and the override these methods. 
 * @author Ryan McKelphin
 *@version 1.0
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
    /**
    * this is where we override the toString method for the return of a grillRecipe array.
    */
    @Override
    public String toString() {
        return "GrillRecipe [preheat=" + preheat + ", grillTemp=" + grillTemp + "]";
    }
    /**
    * this is where we override the equals object and use if statements for the grillTemp and also other classes as well
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GrillRecipe other = (GrillRecipe) obj;
        if (preheat != other.preheat)
            return false;
        if (grillTemp != other.grillTemp)
            return false;
        return true;
    }

    // Method to format the object as JSON
    /**
    * this is were we format a String as a JSON and return them with preheat and even the grillTemp.
    */
    public String formatAsJSON() {
        return "{ \"preheat\": " + preheat + ", \"grillTemp\": " + grillTemp + " }";
    }

    // Method to edit the recipe
    /**
    * We are going to edit the Recipe to the boolean preheat and the int grillTemp.
    */
    public void editRecipe(boolean preheat, int grillTemp) {
        this.preheat = preheat;
        this.grillTemp = grillTemp;
    }
}
