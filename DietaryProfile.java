package project;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DietaryProfile {
	private AllergyList allergyList; 
	private int calorieLimit = 0;
	private int caloriesConsumedToday = 0;
	private ArrayList<Integer> caloriesConsumedMonthly;
	/*private double sugarLimit;
	private double sugarConsumed;
	private double sodiumLimit;
	private double sodiumConsumed;*/
	
	public DietaryProfile(AllergyList allergyList) {
		this.calorieLimit = 0;
		this.caloriesConsumedToday = 0;
		this.caloriesConsumedMonthly = new ArrayList<Integer>();
		this.allergyList = allergyList;
	}
	
	public DietaryProfile() {
		this.calorieLimit = 0;
		this.caloriesConsumedToday = 0;
		this.caloriesConsumedMonthly = new ArrayList<Integer>();
		this.allergyList = new AllergyList();
	}
	
	public void setCalorieLimit(int calorieLimit) {
		this.calorieLimit = calorieLimit;
	}
	
	public int getCalorieLimit() {
		return this.calorieLimit;
	}
	
	public void setCaloriesConsumedToday(int calories) {
		this.caloriesConsumedToday = calories;
	}
	
	public int getCaloriesConsumedToday() {
		return this.caloriesConsumedToday;
	}
	
	public int getCaloriesLeft() {
		return calorieLimit - caloriesConsumedToday;
	}
	
	public AllergyList getAllergyList() {
		return this.allergyList;
	}
	
	public ArrayList<Integer> getCaloriesConsumedMonthly(){
		return this.caloriesConsumedMonthly;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("**DIETARYPROFILE**\n");
		builder.append("calorieLimit: " + Integer.toString(calorieLimit) + "\n");
		builder.append("caloriesConsumedToday: " + Integer.toString(caloriesConsumedToday) + "\n");
		builder.append("caloriesConsumedMonthly: \n");
		for (int i = 0; i < caloriesConsumedMonthly.size(); i++) {
			builder.append(Integer.toString(caloriesConsumedMonthly.get(i)) + "\n");
			System.out.println("Calories consumed monthly: " + caloriesConsumedMonthly.get(i));
		}
		builder.append("**end**\n");
		builder.append(this.allergyList.toString());
		return builder.toString();
	}
	
	public void read(String fileName){
		try {
			BufferedReader br = null;
			String strLine = "";
			br = new BufferedReader( new FileReader(fileName));
			Pattern pattern;
			Matcher m;
			
			while (!strLine.equals("**DIETARYPROFILE**")) {
				strLine = br.readLine();
			}
			//skip **DIETARYPROFILE**
			strLine = br.readLine();
			
			//Match calorieLimit
	        pattern = Pattern.compile("^(calorieLimit: )(\\w+)$");
	        m = pattern.matcher(strLine);
	        if (m.find()) {
	        	calorieLimit = Integer.valueOf(m.group(2));
	        	System.out.println(m.group(2));
	        }
	        
	        //Match caloriesConsumedToday
	        pattern = Pattern.compile("^(caloriesConsumedToday: )(\\w+)$");
	        strLine = br.readLine();
	        m = pattern.matcher(strLine);
	        if (m.find()) {
	        	caloriesConsumedToday = Integer.valueOf(m.group(2));
	        	System.out.println(m.group(2));
	        }
	        
	        //skip "caloriesConsumedMonthly"
	        strLine = br.readLine();
	        
	        strLine = br.readLine();
	        while (!strLine.equals("**end**")) {
	        	caloriesConsumedMonthly.add(Integer.valueOf(strLine));
	        	System.out.println(strLine);
	        	strLine = br.readLine();
	        }
	        
	        //close buffered reader
	        br.close();
		}
		catch (FileNotFoundException e) {
            System.err.println("Unable to find the file.");
        } 
        catch (IOException e) {
            System.err.println("Unable to read the file.");
        }
		
		this.getAllergyList().read(fileName);
	}
	
}
