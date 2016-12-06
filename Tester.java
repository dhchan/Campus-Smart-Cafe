package project;
public class Tester {

	public static void main(String args[]) {
		
		Card card1 = new Card(1234, "abc123");
		
		//code to set Calorie limit
		card1.getDietaryProfile().setCalorieLimit(21);
		
		//code to get Calories left today
		int a = card1.getDietaryProfile().getCaloriesLeft();
		
		//code to set an allergy
		card1.getDietaryProfile().getAllergyList().setDairy(true);
		
		//code to see if user has an allergy
		boolean bool = card1.getDietaryProfile().getAllergyList().getFish();
		
		//code to add funds to account
		card1.getExpenseProfile().setTotalFunds(200.99);
		
		//code to get funds in account
		double b = card1.getExpenseProfile().getTotalFunds();
		
		Clock clock = new Clock();
		//clock.advanceClock(card1);
		
		card1.save();
		
		Card card2 = new Card();
		//card2.read("1234.txt");
		
		AllergyList allergyList = new AllergyList();
		allergyList.setDairy(true);
		allergyList.setWheat(true);
		AllergyList allergyList2 = new AllergyList();
		Food pizza = new Food("Pizza", 10, 2.50, 500, allergyList);
		Food coffee = new Food("Coffee", 20, 1.50, 100, allergyList2);
		Food salad = new Food("Salad", 15, 5.50, 90, allergyList2);
		Cafe cafe1 = new Cafe("TEST_CAFE");
		cafe1.addFood(pizza);
		cafe1.addFood(coffee);
		cafe1.addFood(salad);
		
		
		
		//write a cafe to text file
		cafe1.save();
		
		
		Cafe cafe2 = new Cafe();
		//read a cafe from a text file
		cafe2.read("TEST_CAFE.txt");
		
	}
}
