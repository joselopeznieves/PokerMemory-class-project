

import javax.swing.JFrame;

public class FlushLevel extends RankTrioLevel {
	
	//Flush Level: turn up 5 cards each turn. Cards must be of matching suits 

	protected FlushLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame){
		super(validTurnTime, mainFrame);
		this.getTurnsTakenCounter().setDifficultyModeLabel("Flush Level");
		this.setCardsToTurnUp(5);
	}
}
