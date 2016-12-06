package project;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AllergyList {

	private boolean dairy;
	private boolean eggs;
	private boolean fish;
	private boolean peanuts;
	private boolean shellFish;
	private boolean soy;
	private boolean treeNuts;
	private boolean wheat;
	


/*
	public AllergyList() {
		this.dairy = false;
		this.eggs = false;
		this.fish = false;
		this.peanuts = false;
		this.shellFish = false;
		this.soy = false;
		this.treeNuts = false;
		this.wheat = false;
	}
*/	
	
	
	

	public void setDairy(boolean bool) {
		this.dairy = bool;
		
	}
	public void setEggs(boolean bool) {
		this.eggs = bool;
		
	}
	public void setFish(boolean bool) {
		this.fish = bool;
		
	}
	public void setPeanuts(boolean bool) {
		this.peanuts = bool;
		
	}
	public void setShellFish(boolean bool) {
		this.shellFish = bool;
		
	}
	public void setSoy(boolean bool) {
		this.soy = bool;
		
	}
	public void setTreeNuts(boolean bool) {
		this.treeNuts = bool;
		
	}
	public void setWheat(boolean bool) {
		this.wheat = bool;
		
	}
	
	public boolean getDairy() {
		return this.dairy;
	}
	public boolean getEggs() {
		return this.eggs;
	}
	public boolean getFish() {
		return this.fish;
	}
	public boolean getPeanuts() {
		return this.peanuts;
	}
	public boolean getShellFish() {
		return this.shellFish;
	}
	public boolean getSoy() {
		return this.soy;
	}
	public boolean getTreeNuts() {
		return this.treeNuts;
	}
	public boolean getWheat() {
		return this.wheat;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("**ALLERGYLIST**\n");
		builder.append("dairy: ");
		if (dairy)
			builder.append("true\n");
		else
			builder.append("false\n");
		
		builder.append("eggs: ");
		if (eggs)
			builder.append("true\n");
		else
			builder.append("false\n");
		
		builder.append("fish: ");
		if (fish) 
			builder.append("true\n");
		else
			builder.append("false\n");
		
		builder.append("peanuts: ");
		if (peanuts)
			builder.append("true\n");
		else
			builder.append("false\n");
		
		builder.append("shellFish: ");
		if (shellFish)
			builder.append("true\n");
		else
			builder.append("false\n");
		
		builder.append("soy: ");
		if (soy)
			builder.append("true\n");
		else
			builder.append("false\n");
		
		builder.append("treeNuts: ");
		if (treeNuts)
			builder.append("true\n");
		else
			builder.append("false\n");
		
		builder.append("wheat: ");
		if (wheat)
			builder.append("true\n");
		else
			builder.append("false\n");
		
		builder.append("**end**\n");
		return builder.toString();
	}
	
	//if allergen is present in food, appends it to string to be written to text file
	public String toStringFood() {
		StringBuilder builder = new StringBuilder();
		if (dairy)
			builder.append("dairy/");
		if (eggs)
			builder.append("eggs/");
		if (fish)
			builder.append("fish/");
		if (peanuts)
			builder.append("peanuts/");
		if (shellFish)
			builder.append("shellFish/");
		if (soy)
			builder.append("soy/");
		if (treeNuts)
			builder.append("treeNuts/");
		if (wheat)
			builder.append("wheat/");
		if (!dairy && !eggs && !fish && !peanuts && !shellFish && !soy && !treeNuts && !wheat)
			builder.append("no_allergens/");
		return builder.toString();
	}
	
	public void read(String fileName){
		try {
			BufferedReader br = null;
			String strLine = "";
			br = new BufferedReader( new FileReader(fileName));
			Pattern pattern;
			Matcher m;
			
			while (!strLine.equals("**ALLERGYLIST**")) {
				strLine = br.readLine();
			}
			//skip **ALLERGYLIST**
			strLine = br.readLine();
			
			//dairy
            pattern = Pattern.compile("^(dairy: )(\\w+)$");
            m = pattern.matcher(strLine);
            if (m.find()) {
            	this.dairy = Boolean.valueOf(m.group(2));
            	System.out.println(m.group(2));
            }
            
            //eggs
            pattern = Pattern.compile("^(eggs: )(\\w+)$");
            strLine = br.readLine();
            m = pattern.matcher(strLine);
            if (m.find()) {
            	this.eggs = Boolean.valueOf(m.group(2));
            	System.out.println(m.group(2));
            }
          
            //fish
            pattern = Pattern.compile("^(fish: )(\\w+)$");
            strLine = br.readLine();
            m = pattern.matcher(strLine);
            if (m.find()) {
            	this.fish = Boolean.valueOf(m.group(2));
            	System.out.println(m.group(2));
            }
            
            //peanuts
            pattern = Pattern.compile("^(peanuts: )(\\w+)$");
            strLine = br.readLine();
            m = pattern.matcher(strLine);
            if (m.find()) {
            	this.peanuts = Boolean.valueOf(m.group(2));
            	System.out.println(m.group(2));
            }
            
            //shellFish
            pattern = Pattern.compile("^(shellFish: )(\\w+)$");
            strLine = br.readLine();
            m = pattern.matcher(strLine);
            if (m.find()) {
            	this.shellFish = Boolean.valueOf(m.group(2));
            	System.out.println(m.group(2));
            }
            
            //soy
            pattern = Pattern.compile("^(soy: )(\\w+)$");
            strLine = br.readLine();
            m = pattern.matcher(strLine);
            if (m.find()) {
            	this.soy = Boolean.valueOf(m.group(2));
            	System.out.println(m.group(2));
            }
            
            //treeNuts
            pattern = Pattern.compile("^(treeNuts: )(\\w+)$");
            strLine = br.readLine();
            m = pattern.matcher(strLine);
            if (m.find()) {
            	this.treeNuts = Boolean.valueOf(m.group(2));
            	System.out.println(m.group(2));
            }
            
            //wheat
            pattern = Pattern.compile("^(wheat: )(\\w+)$");
            strLine = br.readLine();
            m = pattern.matcher(strLine);
            if (m.find()) {
            	this.wheat = Boolean.valueOf(m.group(2));
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
	}
	
	//if string is not "no_allergens", searches for each allergy and updates the 
	//correct allergy if allergen found. 
	public void readFood(String stringToRead) {
		Pattern pattern;
		Matcher m;
		pattern = Pattern.compile("no_allergens");
        m = pattern.matcher(stringToRead);
        if (!m.find()) {
        	pattern = Pattern.compile("dairy");
        	m = pattern.matcher(stringToRead);
        	if (m.find())
        		this.dairy = true;
        	pattern = Pattern.compile("eggs");
        	m = pattern.matcher(stringToRead);
        	if (m.find())
        		this.eggs = true;
        	pattern = Pattern.compile("fish");
        	m = pattern.matcher(stringToRead);
        	if (m.find())
        		this.fish = true;
        	pattern = Pattern.compile("peanuts");
        	m = pattern.matcher(stringToRead);
        	if (m.find())
        		this.peanuts = true;
        	pattern = Pattern.compile("shellFish");
        	m = pattern.matcher(stringToRead);
        	if (m.find())
        		this.shellFish = true;
        	pattern = Pattern.compile("soy");
        	m = pattern.matcher(stringToRead);
        	if (m.find())
        		this.soy = true;
        	pattern = Pattern.compile("treeNuts");
        	m = pattern.matcher(stringToRead);
        	if (m.find())
        		this.treeNuts = true;
        	pattern = Pattern.compile("wheat");
        	m = pattern.matcher(stringToRead);
        	if (m.find())
        		this.wheat = true;
        }
	}
	
	
}
