package project;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Card {

	private int id;
	public int getId() {
		return id;
	}

	private String password;
	private DietaryProfile dietaryProfile;
	private ExpenseProfile expenseProfile;
	
	public Card(int id, String password) {
		this.id = id;
		this.password = password;
		this.dietaryProfile = new DietaryProfile();
		this.expenseProfile = new ExpenseProfile();
	}
	
	public Card() {
		this.id = 0;
		this.password = "";
		this.dietaryProfile = new DietaryProfile();
		this.expenseProfile = new ExpenseProfile();
	}
	
	public void setDietaryProfile(DietaryProfile dietaryProfile) {
		this.dietaryProfile = dietaryProfile;
	}
	
	public DietaryProfile getDietaryProfile() {
		return this.dietaryProfile;
	}
	
	public ExpenseProfile getExpenseProfile() {
		return this.expenseProfile;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("**CARD**\n");
		builder.append("ID Number: " + Integer.toString(id) + "\n");
		builder.append("Password: " + password + "\n");
		builder.append("**end**\n");
		builder.append(dietaryProfile.toString());
		builder.append(expenseProfile.toString());
		return builder.toString();
	}
	
	public void save() {
		WriteFile data = new WriteFile( this.id + ".txt" );
		try {
			data.writeToFile(this.toString());
		}
		catch (IOException e) {
			System.out.println("Couldn't print to file");
		}
	}
	
	public void read(String fileName){
		try {
			BufferedReader br = null;
			String strLine = "";
			br = new BufferedReader( new FileReader(fileName));
			Pattern pattern;
			Matcher m;
			
			while (!strLine.equals("**CARD**")) {
				strLine = br.readLine();
			}
			
			//skip **CARD**
            strLine = br.readLine();
            
            //Match and pull ID Number
			pattern = Pattern.compile("^(ID Number: )(\\d+)$");
            m = pattern.matcher(strLine);
            if (m.find()) {
            	this.id = Integer.valueOf(m.group(2));
            	System.out.println(m.group(2));
            }
            
            //Match password
            pattern = Pattern.compile("^(Password: )(\\w+)$");
            strLine = br.readLine();
            m = pattern.matcher(strLine);
            if (m.find()) {
            	this.password = m.group(2);
            	System.out.println(m.group(2));
            }
            
            //close buffered reader
            br.close();
		}
		catch (FileNotFoundException e) {
            System.err.println("File not found.");
        } 
        catch (IOException e) {
            System.err.println("Unable to read the file.");
        }
		
		this.getDietaryProfile().read(fileName);
		this.getExpenseProfile().read(fileName);
	}
}
