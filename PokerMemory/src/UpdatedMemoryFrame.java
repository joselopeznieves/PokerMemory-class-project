import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;


public class UpdatedMemoryFrame extends MemoryFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2584088587389709975L;

	public UpdatedMemoryFrame() {
        super();
        JMenuBar menuBar = this.getJMenuBar();
        JMenu memoryMenu = null;
        JMenu helpMenu = null;
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            if (menuBar.getMenu(i).getText().equals("Memory")) {
                memoryMenu = menuBar.getMenu(i);
                break;
            }
        }
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            if (menuBar.getMenu(i).getText().equals("Help")) {
                helpMenu = menuBar.getMenu(i);
                break;
            }
        }
        
        ActionListener menuHandler = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dprintln("actionPerformed " + e.getActionCommand());
                try {
                    if(e.getActionCommand().equals("Flush Level")) newGame("flushlevel");
                    else if(e.getActionCommand().equals("Straight Level")) newGame("straightlevel");
                    else if(e.getActionCommand().equals("Combo Level")) newGame("combolevel");
                    else if(e.getActionCommand().equals("Equal Pair Level")) newGame("updatedEqualPair");
                    else if(e.getActionCommand().equals("Same Rank Trio Level")) newGame("updatedRankTrio");
                    else if(e.getActionCommand().equals("Easy Level")) newGame("updatedEasyLevel");
                    else if(e.getActionCommand().equals("How To Play")) showInstructions();
                    else if(e.getActionCommand().equals("Scoring")) showScoreInstructions();
                    } catch (IOException e2) {
                    e2.printStackTrace(); throw new RuntimeException("IO ERROR");
                }
            }
        };
        
        //Removes the old Easy Level, Equal Pair, and Rank Trio Levels
        memoryMenu.remove(memoryMenu.getItem(0));
        memoryMenu.remove(memoryMenu.getItem(0));
        memoryMenu.remove(memoryMenu.getItem(0));
        //Remove old how to play to later on replace it
        helpMenu.remove(helpMenu.getItem(0));

        //Memory Menu Items
        JMenuItem updatedEasyLevelItem = new JMenuItem("Easy Level");
        updatedEasyLevelItem.addActionListener(menuHandler);
        memoryMenu.add(updatedEasyLevelItem);
        
        JMenuItem updatedEqualPairMenuItem = new JMenuItem("Equal Pair Level");
        updatedEqualPairMenuItem.addActionListener(menuHandler);
        memoryMenu.add(updatedEqualPairMenuItem);
        
        JMenuItem updatedRankTrioMenuItem = new JMenuItem("Same Rank Trio Level");
        updatedRankTrioMenuItem.addActionListener(menuHandler);
        memoryMenu.add(updatedRankTrioMenuItem); 
        
        JMenuItem flushLevelMenuItem = new JMenuItem("Flush Level");
        flushLevelMenuItem.addActionListener(menuHandler);
        memoryMenu.add(flushLevelMenuItem);
        
        JMenuItem straightLevelMenuItem = new JMenuItem("Straight Level");
        straightLevelMenuItem.addActionListener(menuHandler);
        memoryMenu.add(straightLevelMenuItem);
        
        JMenuItem comboLevelMenuItem = new JMenuItem("Combo Level");
        comboLevelMenuItem.addActionListener(menuHandler);
        memoryMenu.add(comboLevelMenuItem);
        
        
        //Help Menu Items
        JMenuItem howToPlayItem = new JMenuItem("How To Play");
        howToPlayItem.addActionListener(menuHandler);
        helpMenu.add(howToPlayItem);
        
        JMenuItem scoringItem = new JMenuItem("Scoring");
        scoringItem.addActionListener(menuHandler);
        helpMenu.add(scoringItem);
        
        
    }

    /**
     * Prepares a new game (first game or non-first game)
     * @throws IOException 
     */
    public void newGame(String difficultyMode) throws IOException
    {
        // Reset the turn counter label
        this.getTurnCounterLabel().reset();

        // make a new card field with cards, and add it to the window

        if(difficultyMode.equalsIgnoreCase("flushlevel")) {
            this.setGameLevel(new FlushLevel(this.getTurnCounterLabel(), this));
            this.getLevelDescriptionLabel().setText("Flush Level");
            this.getTurnCounterLabel().reset();
            
         // clear out the content pane (removes turn counter label and card field)
            BorderLayout bl  = (BorderLayout) this.getContentPane().getLayout();
            this.getContentPane().remove(bl.getLayoutComponent(BorderLayout.CENTER));
            this.getContentPane().add(showCardDeck(), BorderLayout.CENTER);

        }
        else if(difficultyMode.equalsIgnoreCase("updatedEqualPair")) {
        	this.setGameLevel(new UpdatedEqualPairLevel(this.getTurnCounterLabel(), this));
        	this.getLevelDescriptionLabel().setText("Equal Pair Level");
        	this.getTurnCounterLabel().reset();
        	
        	// clear out the content pane (removes turn counter label and card field)
            BorderLayout bl  = (BorderLayout) this.getContentPane().getLayout();
            this.getContentPane().remove(bl.getLayoutComponent(BorderLayout.CENTER));
            this.getContentPane().add(showCardDeck(), BorderLayout.CENTER);
        }
        else if(difficultyMode.equalsIgnoreCase("updatedRankTrio")) {
        	this.setGameLevel(new UpdatedRankTrio(this.getTurnCounterLabel(), this));
        	this.getLevelDescriptionLabel().setText("Same Rank Trio Level");
        	this.getTurnCounterLabel().reset();
        	
        	// clear out the content pane (removes turn counter label and card field)
            BorderLayout bl  = (BorderLayout) this.getContentPane().getLayout();
            this.getContentPane().remove(bl.getLayoutComponent(BorderLayout.CENTER));
            this.getContentPane().add(showCardDeck(), BorderLayout.CENTER);
        }
        else if(difficultyMode.equalsIgnoreCase("updatedEasyLevel")) {
        	this.setGameLevel(new UpdatedEasyLevel(this.getTurnCounterLabel(), this));
        	this.getLevelDescriptionLabel().setText("Easy Level");
        	this.getTurnCounterLabel().reset();
        	
        	// clear out the content pane (removes turn counter label and card field)
            BorderLayout bl  = (BorderLayout) this.getContentPane().getLayout();
            this.getContentPane().remove(bl.getLayoutComponent(BorderLayout.CENTER));
            this.getContentPane().add(showCardDeck(), BorderLayout.CENTER);
        }
        else if(difficultyMode.equalsIgnoreCase("straightlevel")) {
            this.setGameLevel(new StraightLevel(this.getTurnCounterLabel(), this));
            this.getLevelDescriptionLabel().setText("Straight Level");
            this.getTurnCounterLabel().reset();

            // clear out the content pane (removes turn counter label and card field)
            BorderLayout bl  = (BorderLayout) this.getContentPane().getLayout();
            this.getContentPane().remove(bl.getLayoutComponent(BorderLayout.CENTER));
            this.getContentPane().add(showCardDeck(), BorderLayout.CENTER);

            
        }
        else if(difficultyMode.equalsIgnoreCase("combolevel")) {
        	this.setGameLevel(new ComboLevel(this.getTurnCounterLabel(), this));
        	this.getLevelDescriptionLabel().setText("Combo Level");
        	this.getTurnCounterLabel().reset();
        	
        	// clear out the content pane (removes turn counter label and card field)
            BorderLayout bl  = (BorderLayout) this.getContentPane().getLayout();
            this.getContentPane().remove(bl.getLayoutComponent(BorderLayout.CENTER));
            this.getContentPane().add(showCardDeck(), BorderLayout.CENTER);
        }
        else {
            super.newGame(difficultyMode);
        }
        
       // show the window (in case this is the first game)
       this.setVisible(true);
    }
    
    private void showInstructions()
	{
		dprintln("MemoryGame.showInstructions()");
		final String HOWTOPLAYTEXT = 
				"How To Play\r\n" +
						"\r\n" +
						"EQUAL PAIR Level\r\n"+
						"The game consists of 8 pairs of cards.  At the start of the game,\r\n"+
						"every card is face down.  The object is to find all the pairs and\r\n"+
						"turn them face up.\r\n"+
						"\r\n"+
						"Click on two cards to turn them face up. If the cards are the \r\n"+
						"same, then you have discovered a pair.  The pair will remain\r\n"+
						"turned up.  If the cards are different, they will flip back\r\n"+
						"over automatically after a short delay.  Continue flipping\r\n"+
						"cards until you have discovered all of the pairs.  The game\r\n"+
						"is won when all cards are face up.\r\n"+
						"\r\n"+
						"SAME RANK TRIO Level\r\n"+
						"The game consists of a grid of distinct cards.  At the start of the game,\r\n"+
						"every card is face down.  The object is to find all the trios \r\n"+
						"of cards with the same rank and turn them face up.\r\n"+
						"\r\n"+
						"Click on three cards to turn them face up. If the cards have the \r\n"+
						"same rank, then you have discovered a trio.  The trio will remain\r\n"+
						"turned up.  If the cards are different, they will flip back\r\n"+
						"over automatically after a short delay.  Continue flipping\r\n"+
						"cards until you have discovered all of the pairs.  The game\r\n"+
						"is won when all cards are face up.\r\n"+
						"\r\n"+
						"FLUSH Level"+
						"\r\n"+
						"The level consist of a grid of distinct cards. (Same as SAME RANK TRIO LEVEL) \r\n"+
						"The objective is to discover five cards that have the same suit.\r\n"+
						" Once all possible  five card matches are made, the game is won. \r\n"+
						"\r\n"+
						"STRAIGHT Level"+
						"\r\n"+
						"The game consists of a grid of distinct cards. \r\n"+
						"The objective is to discover five cards that can be arranged \r\n"+
						"in ascending order of different suits.\r\n"+
						"i.e. 2h, 3c, 4s, 5h, and 6h. \r\n" +
						" Once all possible  five card matches are made, the game is won. \r\n"+
						"\r\n"+
						"COMBO Level"+
						"\r\n"+
						"The COMBO Level features a mix of the FLUSH level, the STRAIGHT level, \r\n"+
						"And two other poker hands: the FULL HOUSE and the ROYAL FLUSH. \r\n"+
						"The FULL HOUSE is obtained when you uncover three cards of one rank, \r\n"+
						"followed by two cards of another rank. The ROYAL FLUSH consists of \r\n"+
						"uncovering a king, a queen, a jack, and a ten suit. Once you have \r\n"+
						"uncovered five cards, the COMBO level lets you choose how do you want \r\n"+
						"to evaluate your hand. Did you uncovered a STRAIGHT? Or maybe a FULL HOUSE \r\n"+
						"suits your uncovered hand? The choise is yours! If you do not like your hand, \r\n"+
						"just press the PASS button and the cards will be covered again."+
						"\r\n"+
						"\r\n"+
						"Try to win the game in the least amount of turns \r\n"+
						"while achieving the highest score!";
						
						

		JOptionPane.showMessageDialog(this, HOWTOPLAYTEXT
				, "How To Play", JOptionPane.PLAIN_MESSAGE);
		/**
		 * Shows an dialog box with information about the program
		 */
	}
    
    private void showScoreInstructions()
   	{
   		dprintln("MemoryGame.showScoreInstructions()");
   		final String SCORINGTEXT = 
   				"Scoring for each game level: \r\n" +
   						"\r\n" +
   						"EQUAL PAIR Level:\r\n"+
   						"50 points for each matching pair. \r\n"+
   						"-5 points for each unsuccessful turn.\r\n"+
   						"\r\n"+
   						"SAME RANK TRIO Level:\r\n"+
   						"100 points for each matching trio \r\n"+
   						"+ the sum of the ranks of the cards revealed. \r\n"+
   						"-5 points for each unsuccessful turn.\r\n"+
   						"\r\n"+
   						"FLUSH Level:\r\n"+
   						"700 for each matching five card hand \r\n"+
   						"+ the sum of the ranks of the cards revealed. \r\n"+
   						"-5 points for each unsuccessful turn.\r\n"+
   						"\r\n" +
   						"STRAIGHT Level:\r\n"+
   						"1000 for each straight ranks on a five card hand \r\n"+
   						"+ 100 * by the highest rank. \r\n"+
   						"-5 points for each unsuccessful turn.\r\n"+
   						"\r\n"+
   						"COMBO Level: \r\n"+
   						"Scores for FLUSH and STRAIGHT hands stay the same \r\n"+
   						"For the ROYAL FLUSH hand: 3000 for uncovered hand \r\n"+
   						"For the FULL HOUSE hand: 500 \r\n"+
   						"+ 100 * the highest rank of the uncovered cards.\r\n"+
   						"-5 points for unsuccessful turn and for each PASS \r\n"+
   						"\r\n"+
   						"Special Cards Values: \r\n"+
   						"A = 20 \r\n"+
   						"King = 13 \r\n"+
   						"Queen = 12 \r\n"+
   						"Jack = 11 \r\n"+
   						"\r\n";
   						
   						

   		JOptionPane.showMessageDialog(this, SCORINGTEXT
   				, "Scoring", JOptionPane.PLAIN_MESSAGE);
   		/**
   		 * Shows an dialog box with information about the program
   		 */
   	}

	

}