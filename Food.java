package project;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Food {

	private String name;
	private int quantity;
	private double price;
	private int calories;
	private AllergyList allergyList;
	
	public Food(String name, int quantity, double price, int calories, AllergyList allergyList) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.calories = calories;
		this.allergyList = allergyList;
	}
	
	public Food() {
		name = "";
		quantity = 0;
		price = 0;
		calories = 0;
		allergyList = new AllergyList();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getCalories() {
		return this.calories;
	}
	
	public void setCalories(int calories) {
		this.calories = calories;
	}
	
	public AllergyList getAllergyList() {
		return this.allergyList;
	}
	
	public void setAllergyList(AllergyList allergyList) {
		this.allergyList = allergyList;
	}
	
	//writes "/" separated fields for each member variable, returns string.
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name + "/");
		builder.append(quantity + "/");
		builder.append(price + "/");
		builder.append(calories + "/");
		builder.append("\n");
		builder.append(allergyList.toStringFood());
		
		return builder.toString();
	}
	
	//pass in the string of form (name/quantity/price/calories)
	public void read(String stringToRead){
		Pattern pattern;
		Matcher m;
		pattern = Pattern.compile("^(\\w+)/(\\d+)/(\\d*[.]?\\d+|\\d+[.]?)/(\\d+)/$");
        m = pattern.matcher(stringToRead);
        if (m.find()) {
        	this.name = m.group(1);
        	this.quantity = Integer.valueOf(m.group(2));
        	this.price = Double.valueOf(m.group(3));
        	this.calories = Integer.valueOf(m.group(4));
        }
	}
}
