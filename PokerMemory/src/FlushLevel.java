

import javax.swing.JFrame;

public class FlushLevel extends RankTrioLevel {
	
	//Flush Level: turn up 5 cards each turn. Cards must be of matching suits 

	protected FlushLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame){
		super(validTurnTime, mainFrame);
		this.getTurnsTakenCounter().setDifficultyModeLabel("Flush Level");
		this.setCardsToTurnUp(5);
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
					// Three cards match, so remove them from the list (they will remain face up)
					this.getTurnedCardsBuffer().clear();
				}
				else
				{
					// The cards do not match, so start the timer to turn them down
					this.getTurnDownTimer().start();
				}
			}
			return true;
		}
		return false;
	}
}
