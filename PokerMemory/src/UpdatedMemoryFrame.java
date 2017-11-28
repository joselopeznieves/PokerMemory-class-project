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
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            if (menuBar.getMenu(i).getText().equals("Memory")) {
                memoryMenu = menuBar.getMenu(i);
                break;
            }
        }
        
        ActionListener menuHandler = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(e.getActionCommand().equals("Flush Level")) newGame("flushlevel");
                    else if(e.getActionCommand().equals("Equal Pairr Level")) newGame("updatedEqualPair");
                    else if(e.getActionCommand().equals("Same Rank Trio Levell")) newGame("updatedRankTrio");
                } catch (IOException e2) {
                    e2.printStackTrace(); throw new RuntimeException("IO ERROR");
                }
            }
        };

        JMenuItem flushLevelMenuItem = new JMenuItem("Flush Level");
        flushLevelMenuItem.addActionListener(menuHandler);
        memoryMenu.add(flushLevelMenuItem);
        
        JMenuItem updatedEqualPairMenuItem = new JMenuItem("Equal Pairr Level");
        updatedEqualPairMenuItem.addActionListener(menuHandler);
        memoryMenu.add(updatedEqualPairMenuItem);
        
        JMenuItem updatedRankTrioMenuItem = new JMenuItem("Same Rank Trio Levell");
        updatedRankTrioMenuItem.addActionListener(menuHandler);
        memoryMenu.add(updatedRankTrioMenuItem);
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

        }
        else if(difficultyMode.equalsIgnoreCase("updatedEqualPair")) {
        	this.setGameLevel(new UpdatedEqualPairLevel(this.getTurnCounterLabel(), this));
        	this.getLevelDescriptionLabel().setText("Equal Pairr Level");
        	this.getTurnCounterLabel().reset();
        }
        else if(difficultyMode.equalsIgnoreCase("updatedRankTrio")) {
        	this.setGameLevel(new UpdatedRankTrio(this.getTurnCounterLabel(), this));
        	this.getLevelDescriptionLabel().setText("Same Rank Trio Levell");
        	this.getTurnCounterLabel().reset();
        }
        else {
            super.newGame(difficultyMode);
        }
        
     	
   	 // clear out the content pane (removes turn counter label and card field)
       BorderLayout bl  = (BorderLayout) this.getContentPane().getLayout();
       this.getContentPane().remove(bl.getLayoutComponent(BorderLayout.CENTER));
       this.getContentPane().add(showCardDeck(), BorderLayout.CENTER);

       // show the window (in case this is the first game)
       this.setVisible(true);
    }

}