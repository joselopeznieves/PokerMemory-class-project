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
		super.getTurnsTakenCounter().setDifficultyModeLabel("Same Rank Trio Level");
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
				Card otherCard1 = (Card) this.getTurnedCardsBuffer().get(0);
				Card otherCard2 = (Card) this.getTurnedCardsBuffer().get(1);
				if((card.getRank().equals(otherCard1.getRank())) && (card.getRank().equals(otherCard2.getRank()))) {
					this.setScore(this.calculateScore()); 
					this.getMainFrame().setScore(getScore());
					// Three cards match, so remove them from the list (they will remain face up)
					this.getTurnedCardsBuffer().clear();
				}
				else
				{
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
	
	public int calculateScore() {
		
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
	
	public boolean isGameOver() {
		
		int twoCounter = 0; 
		int threeCounter = 0;
		int fourCounter = 0;
		int fiveCounter = 0;
		int sixCounter = 0;
		int sevenCounter = 0;
		int eightCounter = 0;
		int nineCounter = 0;
		int tenCounter = 0;
		int jackCounter = 0;
		int queenCounter = 0;
		int kingCounter = 0;
		int aCounter = 0;
		
		if(this.getTurnedCardsBuffer().size() == 0) {
			
			for(int i = 0; i < this.getGrid().size(); i++) {
				
				if(!this.getGrid().get(i).isFaceUp()) {

					String cardRank = this.getGrid().get(i).getRank();

					switch(cardRank) {
					case "2":
						twoCounter += 1;
						if(twoCounter == 3) return false;
						break;
					case "3":
						threeCounter += 1;
						if(threeCounter == 3) return false;
						break;
					case "4":
						fourCounter += 1;
						if(fourCounter == 3) return false;
						break;
					case "5":
						fiveCounter += 1;
						if(fiveCounter == 3) return false;
						break;
					case "6":
						sixCounter += 1;
						if(sixCounter == 3) return false;
						break;
					case "7":
						sevenCounter += 1;
						if(sevenCounter == 3) return false;
						break;
					case "8":
						eightCounter += 1;
						if(eightCounter == 3) return false;
						break;
					case "9":
						nineCounter += 1;
						if(nineCounter == 3) return false;
						break;
					case "10":
						tenCounter += 1;
						if(tenCounter == 3) return false;
						break;
					case "j":
						jackCounter += 1;
						if(jackCounter == 3) return false;
						break;
					case "q":
						queenCounter += 1;
						if(queenCounter == 3) return false;
						break;
					case "k":
						kingCounter += 1;
						if(kingCounter == 3) return false;
						break;
					case "a":
						aCounter += 1;
						if(aCounter == 3) return false;
						break;
					}	
				}
			}
			return true;
		}
		else return false;
	}

}
