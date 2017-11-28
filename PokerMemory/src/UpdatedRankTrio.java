import java.util.Vector;

import javax.swing.JFrame;

public class UpdatedRankTrio extends RankTrioLevel {
	
	private int score = 0;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public UpdatedRankTrio(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		super.getTurnsTakenCounter().setDifficultyModeLabel("Trio Level");
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
				Card otherCard1 = (Card) this.getTurnedCardsBuffer().get(0);
				Card otherCard2 = (Card) this.getTurnedCardsBuffer().get(1);
				if((card.getRank().equals(otherCard1.getRank())) && (card.getRank().equals(otherCard2.getRank()))) {
					this.setScore(this.calculateScore(getTurnedCardsBuffer())); 
					// Three cards match, so remove them from the list (they will remain face up)
					this.getTurnedCardsBuffer().clear();
				}
				else
				{
					this.setScore(this.getScore() - 5); 
					// The cards do not match, so start the timer to turn them down
					this.getTurnDownTimer().start();
				}
			}
			return true;
		}
		return false;
	}
	
	public int calculateScore(Vector<Card> turnedCardsBuffer) {
		
		int rankSum = 0;
		//Calculate the sum of ranks 
		//Special consideration for the special ranks are represented in the switch
		for(int i = 0; i < this.getTurnedCardsBuffer().size(); i++) {
			switch(this.getTurnedCardsBuffer().get(i).getRank()) {
			case "a":
				rankSum += 20;
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
				rankSum += 10;
				break;
			default:
				rankSum += Integer.parseInt(this.getTurnedCardsBuffer().get(i).getRank());
			}
		}
		
		int score = this.getScore() + 100 + rankSum; //100 is the base score for uncovering a valid hand
		return score;
		
	}

}
