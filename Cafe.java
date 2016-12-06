package project;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Cafe {

	private String name;
	private ArrayList<Food> menu;
	
	public ArrayList<Food> getMenu() {
		return menu;
	}

	public Cafe(String name) {
		this.name = name;
		menu = new ArrayList<Food>();
	}
	
	public Cafe() {
		this.name = "";
		menu = new ArrayList<Food>();
	}
	
	public void addFood(Food food) {
		menu.add(food);
	}
	
	//for each food item, calls food.toString() (includes allergies)
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.name + "\n");
		for (int i = 0; i < menu.size(); ++i) {
			builder.append(menu.get(i).toString() + "\n");
		}
		builder.append("**end**");
		return builder.toString();
	}
	
	//save the results of this.toString to a file with name of cafe as fileName and first line
	public void save() {
		WriteFile data = new WriteFile( this.name + ".txt" );
		try {
			 data.writeToFile(this.toString());
		}
		catch (IOException e) {
			System.out.println("Couldn't print to file");
		}
	}
	
	public void read(String fileName) {
		try {
			BufferedReader br = null;
			String strLine = "";
			br = new BufferedReader( new FileReader(fileName));
			
			//Get Cafe name
			strLine = br.readLine();
			this.name = strLine;
			
			strLine = br.readLine();
			while (!strLine.equals("**end**")) {
				
				//read food properties line
				Food food = new Food();
				food.read(strLine);
				
				//read allergy line
				strLine = br.readLine();
				//****NOTE***** MAKE SURE IS readFood(), NOT read()
				food.getAllergyList().readFood(strLine);
				
				this.addFood(food);
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
	}
}
