import javax.swing.JFrame;

public class UpdatedEqualPairLevel extends EqualPairLevel {
	
	private int score = 0;
	
	
	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	protected UpdatedEqualPairLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		super.getTurnsTakenCounter().setDifficultyModeLabel("Medium Level");
	}
	
	@Override
	protected boolean turnUp(Card card) {
		// the card may be turned
		if(this.getTurnedCardsBuffer().size() < getCardsToTurnUp()) 
		{
			this.getTurnedCardsBuffer().add(card);
			if(this.getTurnedCardsBuffer().size() == getCardsToTurnUp())
			{
				// there are two cards faced up
				// record the player's turn
				this.getTurnsTakenCounter().increment();
				// get the other card (which was already turned up)
				Card otherCard = (Card) this.getTurnedCardsBuffer().get(0);
				// the cards match, so remove them from the list (they will remain face up)
				if( otherCard.getNum() == card.getNum()) {
					this.score += 50;
					System.out.println(this.score);
					this.getTurnedCardsBuffer().clear();
				}
				// the cards do not match, so start the timer to turn them down
				else {
					this.score -= 5;
					System.out.println(this.score);
					this.getTurnDownTimer().start();
				}
			}
			return true;
		}
		// there are already the number of EasyMode (two face up cards) in the turnedCardsBuffer
		return false;
	}
	
}
