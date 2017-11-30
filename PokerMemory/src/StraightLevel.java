import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;

public class StraightLevel extends FlushLevel{
	
	private long score = 0;
		
	public long getScore() {
		return score;
	}
	
	public void setScore(long score) {
		this.score = score;
	}
	
	//Straight level: Pick 5 cards on each turn. Cards must be in order.
	
	protected StraightLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		this.getTurnsTakenCounter().setDifficultyModeLabel("Straight level");
		this.setCardsToTurnUp(5);
		this.setCardsPerRow(10);
		this.setRowsPerGrid(5);
		this.getMainFrame().setScore(getScore());
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
				List<String> Order = new ArrayList<String> ();
				List<Integer> Order1 = new ArrayList<Integer> ();
				Order.add(card.getRank());
				Order.add(otherCard1.getRank());
				Order.add(otherCard2.getRank());
				Order.add(otherCard3.getRank());
				Order.add(otherCard4.getRank());
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
				// All five cards are in order with at least two different suits (still doesn't reject same suits)
							System.out.println("Hooray!!");
							this.setScore(this.calculateScore(Order1));
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
					/*long c = 0, d = 0, h = 0, s = 0;
					for(int i = 0; i < this.getTurnedCardsBuffer().size(); i++){
						switch(this.getTurnedCardsBuffer().get(i).getSuit()) {
						case "c":
							c += 1;
							break;
						case "d":
							d += 1;
							break;
						case "h":
							h += 1;
							break;
						case "s":
							s += 1;
							break;
				}
			}*/
			}
			return true;
		}
		return false;
	}
	public long calculateScore(List<Integer> Order1) {
		System.out.println(this.getScore());
		if(Order1.get(4).equals(14)) {
			long score = this.getScore() + 1000 + 100 * 20; //1000 is the base score for uncovering a valid hand
			return score;
		}
		else {
			long score = this.getScore() + 1000 + (long)(100 * Order1.get(4)); //1000 is the base score for uncovering a valid hand
			return score;
		}
		
	}

}

