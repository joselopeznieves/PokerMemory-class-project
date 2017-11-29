import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
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
						"Each time you flip two cards up, the turn counter will\r\n"+
						"increase.  Try to win the game in the fewest number of turns!";

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