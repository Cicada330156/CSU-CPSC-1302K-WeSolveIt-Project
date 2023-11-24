import javax.json.*;
import java.io.*;
import java.util.ArrayList;
public class ParseFile {//repurposes code and examples from https://docs.oracle.com/middleware/1221/wls/WLPRG/java-api-for-json-proc.htm#WLPRG1061
	public String firstName;
	public String lastName;
	public int age;
	public Address adress;
	public ArrayList<PhoneNumber> phoneNumbers;

	public class Address {
		public String streetAddress;
		public String city;
		public String state;
		public int postalCode;
	}
	public class PhoneNumber {
		public String type;
		public String number;
	}

	public static void main (String[] args) {
		System.err.println("DEBUG: 1");
		JsonReader reader = null;
		JsonStructure jsonst = null;
		try {
			jsonst = Json.createReader(new FileReader("data.json")).read();
		} catch (FileNotFoundException e) {
			System.out.println("Error loading file: " + e);
		}
		try {
			JsonArray recipeBook = (JsonArray) jsonst;
			try {
				for (JsonValue value : recipeBook) {
					JsonObject recipe = (JsonObject) value;
					ParseFile myObj = createParsedFileFromJsonObject(recipe);
				}
			} catch (ClassCastException e) {
				System.out.print("Expected a JSON object");
				System.out.println(e);
			}
		} catch (ClassCastException e) {
			System.out.println("could not parse data. Expected array of objects. received: " + jsonst.getValueType());
		}
	}
	public static ParseFile createParsedFileFromJsonObject (JsonObject myJsonObj) {
		ParseFile myObj = new ParseFile();
		try {
			myObj.firstName = (String) myJsonObj.getString( "firstName" );
			myObj.lastName = (String) myJsonObj.getString( "lastName" );
		} catch (ClassCastException e) {
			System.out.println("data type was not what was expected for variable name");
		}
		return myObj;
	}
}
