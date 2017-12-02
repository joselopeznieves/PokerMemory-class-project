import javax.swing.JFrame;

public class FlushLevel extends RankTrioLevel {
	
	private long score = 0;
	
	public long getScore() {
		return score;
	}
	
	public void setScore(long score) {
		this.score = score;
	}
	
	//Flush Level: turn up 5 cards each turn. Cards must be of matching suits 

	protected FlushLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame){
		super(validTurnTime, mainFrame);
		this.getTurnsTakenCounter().setDifficultyModeLabel("Flush Level");
		this.setCardsToTurnUp(5);
		this.getMainFrame().setScore(getScore());
	}
	
	// The make deck method stays the same, since we want a deck without repetitions
	
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
				
				// we want to compare the suits (not the ranks) of the 5 cards 
				if((card.getSuit().equals(otherCard1.getSuit())) 
						&& (card.getSuit().equals(otherCard2.getSuit()))
						&& (card.getSuit().equals(otherCard3.getSuit()))
						&& (card.getSuit().equals(otherCard4.getSuit()))) {
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
			return true;
		}
		return false;
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
	
	
	public boolean isGameOver(){
		
		int clubCounter = 0; 
		int spadesCounter = 0;
		int heartsCounter = 0;
		int diamondsCounter = 0;
		
		if(this.getTurnedCardsBuffer().size() == 0) {
			
			for(int i = 0; i < this.getGrid().size(); i++) {
				
				if(!this.getGrid().get(i).isFaceUp()) {

					String cardSuit = this.getGrid().get(i).getSuit();

					switch(cardSuit) {
					case "s":
						spadesCounter += 1;
						if(spadesCounter == 5) return false;
						break;
					case "c":
						clubCounter += 1;
						if(clubCounter == 5) return false;
						break;
					case "h":
						heartsCounter += 1;
						if(heartsCounter == 5) return false;
						break;
					case "d":
						diamondsCounter += 1;
						if(diamondsCounter == 5) return false;
						break;
					}	
				}
			}
			return true;
		}
		else return false;

	}
}
