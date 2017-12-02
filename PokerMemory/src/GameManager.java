/**
 * Main class for Memory game
 *
 * @author Michael Leonhard (Original Author)
 * @author Modified by Bienvenido Vélez (UPRM)
 * @version Sept 2017
 */

import java.io.IOException;
<<<<<<< HEAD

import javax.swing.JFrame;
=======
>>>>>>> refs/remotes/ciic4010staff/master
import javax.swing.JOptionPane;

public class GameManager {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		// make an instance of the main MemoryFrame game class
		int playMore = 2;
		while (playMore != 1) {
			UpdatedMemoryFrame instance = new UpdatedMemoryFrame();
			instance.newGame("updatedEasyLevel");

			while(!instance.gameOver()) {
				Thread.sleep(500);
			}
			playMore = JOptionPane.showConfirmDialog(null, "Play Again?", "GAME OVER!!!", JOptionPane.YES_NO_OPTION);
			System.out.println(playMore+"");
		}
		System.exit(0);
	}
}
