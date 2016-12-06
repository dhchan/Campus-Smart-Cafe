package project;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardDatabase {

	private HashMap<Integer, String> cardDatabase;
	
	public CardDatabase() {
		cardDatabase = new HashMap<Integer, String>();
	}
	
	public void add(int key, String value) {
		cardDatabase.put(key, value);
	}
	
	public HashMap<Integer,String> getcardDatabase() {
		return this.cardDatabase;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("**CARDDATABASE**\n");
		for (Map.Entry<Integer, String> entry : cardDatabase.entrySet()) {
		    builder.append(Integer.toString(entry.getKey()) + " " + entry.getValue() + "\n");
		}
		builder.append("**end**");
		return builder.toString();
	}
	
	public void save() {
		WriteFile data = new WriteFile("CardDatabase.txt");
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
			
			while (!strLine.equals("**CARDDATABASE**")) {
				strLine = br.readLine();
			}
			
			
			while(!strLine.equals("**end**")){
				strLine = br.readLine();
				pattern = Pattern.compile("^(\\d+) (\\w+)$");
	            m = pattern.matcher(strLine);
	            if (m.find()) {
	            	this.add(Integer.valueOf(m.group(1)), m.group(2));
	            	System.out.println(m.group(1) + m.group(2));
	            }
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
	}
}
