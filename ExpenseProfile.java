package project;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpenseProfile {

	private double totalFunds;
	private double fundsSpentToday;
	private ArrayList<Double> fundsSpentMonthly;
	
	public ExpenseProfile() {
		
		this.totalFunds = 0;
		this.fundsSpentMonthly = new ArrayList<Double>();
		this.fundsSpentToday = 0;
	}
	
	public void setTotalFunds(double totalFunds) {
		this.totalFunds = totalFunds;
	}
	
	public double getTotalFunds() {
		return this.totalFunds;
	}
	
	public void setFundsSpentToday(double funds){
		this.fundsSpentToday = funds;
	}
	
	public double getFundsSpentToday(){
		return this.fundsSpentToday;
	}
	
	public ArrayList<Double> getFundsSpentMonthly() {
		return this.fundsSpentMonthly;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("**EXPENSEPROFILE**\n");
		builder.append("totalFunds: " + Double.toString(totalFunds) + "\n");
		builder.append("fundsSpentToday: " + Double.toString(fundsSpentToday) + "\n");
		builder.append("fundsSpentMonthly: \n");
		for (int i = 0; i < fundsSpentMonthly.size(); ++i) {
			builder.append(Double.toString(fundsSpentMonthly.get(i)) + "\n");
		}
		builder.append("**end**\n");
		return builder.toString();
	}
	
	
	public void read(String fileName){
		try {
			BufferedReader br = null;
			String strLine = "";
			br = new BufferedReader( new FileReader(fileName));
			Pattern pattern;
			Matcher m;
			
			while (!strLine.equals("**EXPENSEPROFILE**")) {
				strLine = br.readLine();
			}
			//skip **EXPENSEPROFILE**
			strLine = br.readLine();
			
			//totalFunds
            pattern = Pattern.compile("^(totalFunds: )(\\d*[.]?\\d+|[-+]?\\d+[.]?)$");
            m = pattern.matcher(strLine);
            if (m.find()) {
            	this.totalFunds = Double.valueOf(m.group(2));
            	System.out.println(m.group(2));
            }
            
            //fundsSpentToday
            pattern = Pattern.compile("^(fundsSpentToday: )(\\d*[.]?\\d+|[-+]?\\d+[.]?)$");
            strLine = br.readLine();
            m = pattern.matcher(strLine);
            if (m.find()) {
            	this.fundsSpentToday = Double.valueOf(m.group(2));
            	System.out.println(m.group(2));
            }
            
            //skip "fundsSpentMonthly"
            strLine = br.readLine();
            
            strLine = br.readLine();
            while (!strLine.equals("**end**")) {
            	this.fundsSpentMonthly.add(Double.valueOf(strLine));
            	System.out.println(strLine);
            	strLine = br.readLine();
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
