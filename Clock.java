package project;

public class Clock {

	public void advanceClock(Card card) {
		//add Calories consumed today to arrayList
		card.getDietaryProfile().getCaloriesConsumedMonthly().add(card.getDietaryProfile().getCaloriesConsumedToday());
		//reset calories consumed today
		card.getDietaryProfile().setCaloriesConsumedToday(0);
		
		//add Funds spent today to arrayList
		card.getExpenseProfile().getFundsSpentMonthly().add(card.getExpenseProfile().getFundsSpentToday());
		//reset funds spent today
		card.getExpenseProfile().setFundsSpentToday(0);
		
	}
}
