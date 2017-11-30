import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

public class StraightLevel extends FlushLevel{
	//Straight level: Pick 5 cards on each turn. Cards must be in order.
	
	protected StraightLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		this.getTurnsTakenCounter().setDifficultyModeLabel("Straight level");
		this.setCardsToTurnUp(5);
		this.setCardsPerRow(10);
		this.setRowsPerGrid(5);
	}
	protected boolean turnUp(Card card) {
		// the card may be turned
		if(this.getTurnedCardsBuffer().size() < getCardsToTurnUp()) 
		{
			// add the card to the list
			this.getTurnedCardsBuffer().add(card);
			if(this.getTurnedCardsBuffer().size() == getCardsToTurnUp())
			{
				// We are uncovering the last card in this turn
				// Record the player's turn
				this.getTurnsTakenCounter().increment();
				// get the other card (which was already turned up)
				Card otherCard1 = (Card) this.getTurnedCardsBuffer().get(0);
				Card otherCard2 = (Card) this.getTurnedCardsBuffer().get(1);
				Card otherCard3 = (Card) this.getTurnedCardsBuffer().get(2);
				Card otherCard4 = (Card) this.getTurnedCardsBuffer().get(3);
				Card otherCard5 = (Card) this.getTurnedCardsBuffer().get(4);
				List<String> Order = new ArrayList<String> ();
				Order.add(otherCard1.getRank());
				Order.add(otherCard2.getRank());
				Order.add(otherCard3.getRank());
				Order.add(otherCard4.getRank());
				Order.add(otherCard5.getRank());
				for (int i = 0; i < Order.size(); i++) {
					System.out.println(i);
					System.out.println("Order: " + Order.get(i));
					if(Order.get(i) == "a") {
						System.out.println("a");
						Order.set(i, "20");	
					}
					else if(Order.get(i) == "t") {
						System.out.println("t");
						Order.set(i, "10");	
					}
					else if(Order.get(i) == "j") {
						System.out.println("j");
						Order.set(i, "11");	
					}
					else if(Order.get(i) == q) {
						System.out.println("q");
						Order.set(i, "12");	
					}
					else if(Order.get(i) == k) {
						System.out.println("k");
						Order.set(i, "13");	
					}
				}
				Collections.sort(Order);
				
				System.out.println("Order: " + Order);
				/*if() {
					// All five cards are in order with at least two different suits
					
				}
				else
				{
					// The cards do not match, so start the timer to turn them down
					this.getTurnDownTimer().start();
				}*/
			}
			return true;
		}
		return false;
	}
}
