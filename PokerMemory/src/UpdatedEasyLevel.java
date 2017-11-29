import javax.swing.JFrame;

public class UpdatedEasyLevel extends EasyLevel {
	
	private long score = 0;
	
	public long getScore() {
		return this.score;
	}

	//Class created so the easy level will display at least a 0 score instead of "New Label"
	public UpdatedEasyLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		this.getTurnsTakenCounter().setDifficultyModeLabel("Easy Level");
		this.getMainFrame().setScore(getScore());
	}

}
