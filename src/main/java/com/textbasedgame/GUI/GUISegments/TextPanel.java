package com.textbasedgame.GUI.GUISegments;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicProgressBarUI;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.Component;
import java.util.Queue;
import java.util.LinkedList;

import com.textbasedgame.GUI.Styles.textStyling.styles;
import com.textbasedgame.world.world;
import com.textbasedgame.GUI.Styles.*;

public class TextPanel extends JPanel{

	private JPanel oldTextPanel;
	private JPanel recentTextPanel;
	private JScrollPane scrollPane;
	private JScrollPane secondScrollPane;

    private static Queue<JLabel> textQueue = new LinkedList<JLabel>();



	public TextPanel(){

		///////////////////////////////////////////////////////////////////////////
        ///        TEXT PANEL
        ///////////////////////////////////////////////////////////////////////////

        //setup second JPanel for text
        Dimension minSizeTxt = new Dimension(600,800);
		oldTextPanel = new JPanel();
        oldTextPanel.setLayout(new BoxLayout(oldTextPanel, BoxLayout.Y_AXIS));
        oldTextPanel.setMinimumSize(minSizeTxt);
        oldTextPanel.setBackground(new Color(215, 215, 215));
        oldTextPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //JPanel for New Important text before stuff gets pushed to the main box
        recentTextPanel = new JPanel();
        recentTextPanel.setLayout(new BoxLayout(recentTextPanel, BoxLayout.Y_AXIS));
        recentTextPanel.setMinimumSize(minSizeTxt);
        recentTextPanel.setBackground(Color.white);
        recentTextPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        scrollPane = new JScrollPane(oldTextPanel);
        secondScrollPane = new JScrollPane(recentTextPanel);


		this.setLayout(new GridLayout(2,1, 10, 10));

        //Split the text panel into a section for new important information and old news
        this.add(scrollPane);
        this.add(secondScrollPane);

	}	


	public void pushOldText(){
        for(JLabel text: textQueue){
            oldTextPanel.add(text);
        }
        textQueue.clear();
        recentTextPanel.removeAll();
        SwingUtilities.invokeLater(() -> {
            oldTextPanel.scrollRectToVisible(oldTextPanel.getComponents()[oldTextPanel.getComponentCount()-1].getBounds());
        });
        recentTextPanel.revalidate();
        recentTextPanel.repaint();
        oldTextPanel.revalidate();
        oldTextPanel.repaint();
    }

    public void printOnGameSide(String s){
        JLabel text = new JLabel();
        text.setAlignmentX(Component.LEFT_ALIGNMENT);
        text.setText(s);
        recentTextPanel.add(text);
        textQueue.add(text);
        recentTextPanel.revalidate();
        SwingUtilities.invokeLater(() -> {
            text.scrollRectToVisible(text.getBounds());
        });
    }

    public void printOnGameSide(String s, styles style){
        JLabel text = new JLabel();
        text.setAlignmentX(Component.LEFT_ALIGNMENT);
        text.setText(s);
        textStyling.styleText(text, style);
        recentTextPanel.add(text);
        textQueue.add(text);
        recentTextPanel.revalidate();
        SwingUtilities.invokeLater(() -> {
            text.scrollRectToVisible(text.getBounds());
        });
    }

    public void printDialogue(String dialogue, world.CharacterNames character){
        JLabel text = new JLabel();
        text.setAlignmentX(Component.LEFT_ALIGNMENT);
        text.setText(dialogue);
        textStyling.styleDialogue(text, character);
        recentTextPanel.add(text);
        textQueue.add(text);
        recentTextPanel.revalidate();
        SwingUtilities.invokeLater(() -> {
            text.scrollRectToVisible(text.getBounds());
        });
    }    

    public void newlOnGameSide(){
        JLabel text = new JLabel();
        text.setText(" ");
        textQueue.add(text);
        recentTextPanel.add(text);
        recentTextPanel.revalidate();

    }

	//Set Text For Text Panel To Be Monster Fighting UI
    public void setMonsterRoomUI(String monsterName, int monsterCurrHealth, int monsterMaxHealth){
        JProgressBar monsterHealthBar = new JProgressBar(0, monsterMaxHealth);
        monsterHealthBar.setValue(monsterCurrHealth);
        monsterHealthBar.setStringPainted(true);
        monsterHealthBar.setString(monsterCurrHealth + " / " + monsterMaxHealth);
        monsterHealthBar.setStringPainted(true);
        

        Dimension size = new Dimension(400, 20);
        monsterHealthBar.setPreferredSize(size);
        monsterHealthBar.setMaximumSize(size);
        monsterHealthBar.setMinimumSize(size);
        
        monsterHealthBar.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        //set color of the health bar based on health percentage
        monsterHealthBar.setForeground(new Color((int)(255 - 255*((monsterCurrHealth * 1.0) / monsterMaxHealth)),(int)(255*(monsterCurrHealth * 1.0 / monsterMaxHealth)),0));
        monsterHealthBar.setBackground(null);
        monsterHealthBar.setBorder(new BevelBorder(0, Color.black, Color.black));
        monsterHealthBar.setUI(new BasicProgressBarUI() {
            protected Color getSelectionBackground() { return Color.BLACK; }
            protected Color getSelectionForeground() { return Color.BLACK; }
        });



        recentTextPanel.add(new JLabel("---- " + monsterName + " ----"));
        recentTextPanel.add(monsterHealthBar);
        recentTextPanel.revalidate();

    }
    @Override
    public void removeAll(){
        oldTextPanel.removeAll();
    }
}

