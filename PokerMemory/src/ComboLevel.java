import javax.swing.JDialog;
import java.awt.Dialog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
 
public class ComboLevel extends FlushLevel {
	
	private long score = 0;
	public long getScore() {
		return score;
	}


	public void setScore(long score) {
		this.score = score;
	}


	protected ComboLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		super.getTurnsTakenCounter().setDifficultyModeLabel("Combo Level");
		this.getMainFrame().setScore(getScore());
	}
	@Override
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
					// this time we want to turn up 5
					Card otherCard1 = (Card) this.getTurnedCardsBuffer().get(0);
					Card otherCard2 = (Card) this.getTurnedCardsBuffer().get(1);
					Card otherCard3 = (Card) this.getTurnedCardsBuffer().get(2);
					Card otherCard4 = (Card) this.getTurnedCardsBuffer().get(3);
				}
				return true;	
			}
			Object[] options = {"Royal Flush",
	                "Straight",
	                "Flush",
	                "Full House",
	                "Pass"};
			int option = JOptionPane.showOptionDialog(this.getMainFrame(),
					"Choose a Poker Hand: ",
							"Options",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							options,
							options[4]);
			switch(option) {
			case 0:	//Royal Flush
				checkRoyal();
				break;
			case 1: //Straight
				checkStraight();
				break;
			case 2: //Flush
				checkFlush();
				break;
			case 3: //Full House 
				checkFullHouse();
				break;
			case 4: //Pass
				//Subtract 5 from the score 
				this.setScore(this.getScore() - 5);
				this.getMainFrame().setScore(getScore());
				// The cards do not match, so start the timer to turn them down 
				this.getTurnDownTimer().start();
				break;
			}
		return false;
	}
	
	public void checkRoyal() {

		this.getTurnsTakenCounter();
		boolean samesuits = false;
		// get the other card (which was already turned up)
		// this time we want to turn up 5
		Card otherCard1 = (Card) this.getTurnedCardsBuffer().get(0);
		Card otherCard2 = (Card) this.getTurnedCardsBuffer().get(1);
		Card otherCard3 = (Card) this.getTurnedCardsBuffer().get(2);
		Card otherCard4 = (Card) this.getTurnedCardsBuffer().get(3);
		Card otherCard5 = (Card) this.getTurnedCardsBuffer().get(4);
		if((otherCard1.getSuit().equals(otherCard2.getSuit())) 
						&& (otherCard1.getSuit().equals(otherCard3.getSuit()))
						&& (otherCard1.getSuit().equals(otherCard4.getSuit()))
						&& (otherCard1.getSuit().equals(otherCard5.getSuit()))) {
			samesuits = true;
		}
		int a = 0, k = 0, q = 0, j = 0, t = 0;
		for(int i = 0; i < this.getTurnedCardsBuffer().size(); i++) {
		switch(this.getTurnedCardsBuffer().get(i).getRank()) {
			case "a":
				a++;
				break;
			case "k":
				k++;
				break;
			case "q":
				q++;
				break;
			case "j":
				j++;
				break;
			case "t":
				t++;
				break;
			}
		}
		if (a == 1 && k == 1 && q == 1 && j == 1 && t ==1 && samesuits == true) {
			this.setScore(this.getScore() + 3000); //2000 is the score for uncovering a royal flush
			this.getMainFrame().setScore(getScore());
			this.getTurnedCardsBuffer().clear();
		}
		else {
			//Subtract 5 from the score 
			this.setScore(this.getScore() - 5);
			this.getMainFrame().setScore(getScore());
			// The cards do not match, so start the timer to turn them down 
			this.getTurnDownTimer().start();
		}
	}
	public void checkStraight() {
		this.getTurnsTakenCounter();
		Card otherCard1 = (Card) this.getTurnedCardsBuffer().get(0);
		Card otherCard2 = (Card) this.getTurnedCardsBuffer().get(1);
		Card otherCard3 = (Card) this.getTurnedCardsBuffer().get(2);
		Card otherCard4 = (Card) this.getTurnedCardsBuffer().get(3);
		Card otherCard5 = (Card) this.getTurnedCardsBuffer().get(4);
		List<String> Order = new ArrayList<String> ();
		List<Integer> Order1 = new ArrayList<Integer> ();
		Order.add(otherCard1.getRank());
		Order.add(otherCard2.getRank());
		Order.add(otherCard3.getRank());
		Order.add(otherCard4.getRank());
		Order.add(otherCard5.getRank());
		for (int i = 0; i < Order.size(); i++) {
			if(((Order.get(i)).equals("a"))) {
				Order1.add(20);	
			}
			else if(((Order.get(i)).equals("t"))) {
				System.out.println(i);
				Order1.add(10);	
			}
			else if(((Order.get(i)).equals("j"))) {
				System.out.println(i);
				Order1.add(11);	
			}
			else if(((Order.get(i)).equals("q"))) {
				System.out.println(i);
				Order1.add(12);
			}
			else if(((Order.get(i)).equals("k"))) {
				System.out.println(i);
				Order1.add(13);
			}
			else {
				Order1.add(Integer.parseInt(Order.get(i)));
			}
		}
		Collections.sort(Order1);
		if (Order1.get(4) == 20 && Order1.get(3) == 5) {
			for (int i = 0; i < Order1.size(); i++) {
				if(((Order1.get(i)).equals(20))) {
					Order1.set(i, 1);
				}
			}
		}
		if (Order1.get(4) == 20 && Order1.get(3) == 13) {
			for (int i = 0; i < Order1.size(); i++) {
				if(((Order1.get(i)).equals(20))) {
					Order1.set(i, 14);
				}
			}
		}
		Collections.sort(Order1);
		System.out.println(Order1);
		if((Order1.get(1) == Order1.get(0) + 1 && Order1.get(2) == Order1.get(1) + 1 && 
			Order1.get(3) == Order1.get(2) + 1 && Order1.get(4) == Order1.get(3) + 1)) {
				if((!otherCard1.getSuit().equals(otherCard2.getSuit())) 
						|| (!otherCard2.getSuit().equals(otherCard3.getSuit()))
						|| (!otherCard3.getSuit().equals(otherCard4.getSuit()))
						|| (!otherCard4.getSuit().equals(otherCard5.getSuit()))) {
				// All five cards are in order with at least two different suits (still doesn't reject same suits)
					this.setScore(this.getScore() + 1000 + 100*Order1.get(4));
					this.getMainFrame().setScore(getScore());
					// Five cards match, so remove them from the list (they will remain face up)
					this.getTurnedCardsBuffer().clear();
					}
				else{
					this.setScore(this.getScore() - 5);
					this.getMainFrame().setScore(getScore());
					// The cards do not match, so start the timer to turn them down
					this.getTurnDownTimer().start();
				}
		}
		else{
			this.setScore(this.getScore() - 5);
			this.getMainFrame().setScore(getScore());
			// The cards do not match, so start the timer to turn them down
			this.getTurnDownTimer().start();
		}
}
	public void checkFlush() {
		// get the other card (which was already turned up)
		// this time we want to turn up 5
		Card otherCard1 = (Card) this.getTurnedCardsBuffer().get(0);
		Card otherCard2 = (Card) this.getTurnedCardsBuffer().get(1);
		Card otherCard3 = (Card) this.getTurnedCardsBuffer().get(2);
		Card otherCard4 = (Card) this.getTurnedCardsBuffer().get(3);
		Card otherCard5 = (Card) this.getTurnedCardsBuffer().get(4);
		
		if((otherCard1.getSuit().equals(otherCard2.getSuit())) 
				&& (otherCard2.getSuit().equals(otherCard3.getSuit()))
				&& (otherCard3.getSuit().equals(otherCard4.getSuit()))
				&& (otherCard4.getSuit().equals(otherCard5.getSuit()))) {
			//Calculate and set Score
			this.setScore(this.calculateScore());
			this.getMainFrame().setScore(getScore());
			// Five cards match, so remove them from the list (they will remain face up)
			this.getTurnedCardsBuffer().clear();
		
		}
		else
		{
			//Subtract 5 from the score 
			this.setScore(this.getScore() - 5);
			this.getMainFrame().setScore(getScore());
			// The cards do not match, so start the timer to turn them down 
			this.getTurnDownTimer().start();
		}
	}
	public void checkFullHouse() {
		this.getTurnsTakenCounter();
		Card otherCard1 = (Card) this.getTurnedCardsBuffer().get(0);
		Card otherCard2 = (Card) this.getTurnedCardsBuffer().get(1);
		Card otherCard3 = (Card) this.getTurnedCardsBuffer().get(2);
		Card otherCard4 = (Card) this.getTurnedCardsBuffer().get(3);
		Card otherCard5 = (Card) this.getTurnedCardsBuffer().get(4);
		List<String> Order = new ArrayList<String> ();
		List<Integer> Order1 = new ArrayList<Integer> ();
		Order.add(otherCard1.getRank());
		Order.add(otherCard2.getRank());
		Order.add(otherCard3.getRank());
		Order.add(otherCard4.getRank());
		Order.add(otherCard5.getRank());
		for (int i = 0; i < Order.size(); i++) {
			if(((Order.get(i)).equals("a"))) {
				Order1.add(20);	
			}
			else if(((Order.get(i)).equals("t"))) {
				System.out.println(i);
				Order1.add(10);	
			}
			else if(((Order.get(i)).equals("j"))) {
				System.out.println(i);
				Order1.add(11);	
			}
			else if(((Order.get(i)).equals("q"))) {
				System.out.println(i);
				Order1.add(12);
			}
			else if(((Order.get(i)).equals("k"))) {
				System.out.println(i);
				Order1.add(13);
			}
			else {
				Order1.add(Integer.parseInt(Order.get(i)));
			}
		}
		Collections.sort(Order1);
		if (Order1.get(0) == Order1.get(1) && Order1.get(1) == Order1.get(2) && Order1.get(3) == Order1.get(4)) {
			this.setScore(this.getScore() + 500 + 100*Order1.get(4));
			this.getMainFrame().setScore(getScore());
			// Five cards match, so remove them from the list (they will remain face up)
			this.getTurnedCardsBuffer().clear();
		}
		else if (Order1.get(0) == Order1.get(1) && Order1.get(2) == Order1.get(3) && Order1.get(3) == Order1.get(4)) {
			this.setScore(this.getScore() + 500 + 100*Order1.get(4));
			this.getMainFrame().setScore(getScore());
			// Five cards match, so remove them from the list (they will remain face up)
			this.getTurnedCardsBuffer().clear();
		}
		else {
			//Subtract 5 from the score 
			this.setScore(this.getScore() - 5);
			this.getMainFrame().setScore(getScore());
			// The cards do not match, so start the timer to turn them down 
			this.getTurnDownTimer().start();
		}
	}
	public long calculateScore() {
		
		long rankSum = 0;
		//Calculate the sum of ranks 
		//Special consideration for the special ranks are represented in the switch
		for(int i = 0; i < this.getTurnedCardsBuffer().size(); i++) {
			switch(this.getTurnedCardsBuffer().get(i).getRank()) {
			case "a":
				rankSum +=20;
				break;
			case "k":
				rankSum += 13;
				break;
			case "q":
				rankSum += 12;
				break;
			case "j":
				rankSum += 11;
				break;
			case "t":
				rankSum +=10;
				break;
			default:
				rankSum += Integer.parseInt(this.getTurnedCardsBuffer().get(i).getRank());
			}
		}
		
		long score = this.getScore() + 700 + rankSum; //700 is the base score for uncovering a valid hand
		return score;
		
	}
}