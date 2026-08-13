package com.textbasedgame.GUI.GUISegments;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import com.textbasedgame.GUI.gui;

import com.textbasedgame.playerFiles.player;
import com.textbasedgame.items.equipables;
import com.textbasedgame.GUI.Styles.textStyling;
import com.textbasedgame.world.world;

public class InventoryPanel extends JPanel{
	private JPanel topOfInvPanel;
	private JPanel invPanel;
	

	public InventoryPanel(){
        this.setLayout(new BorderLayout());

        topOfInvPanel = new JPanel();

        Dimension minSizeinv = new Dimension(600,800);
        invPanel = new JPanel();
        invPanel.setLayout(new BoxLayout(invPanel, BoxLayout.Y_AXIS));
        invPanel.setMinimumSize(minSizeinv);
        invPanel.setBackground(Color.gray);
        invPanel.setMinimumSize(minSizeinv);
        invPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.add(invPanel, BorderLayout.CENTER);
        this.add(topOfInvPanel, BorderLayout.NORTH);
    
	}
    public void updatePlayerSide(){
        invPanel.removeAll();

        invPanel.add(new JLabel("Name: " + player.getName() + "                     "));
        invPanel.add(new JLabel("Level: " + player.getPlayerLevel() + "                     "));
        invPanel.add(new JLabel("Shmeckles: " + Integer.toString(player.gold) + "                     "));
        invPanel.add(new JLabel("XP: " + player.getXP() + "/" + player.getXpToLevelUp()));


        JProgressBar playerHealthBar = new JProgressBar(0, player.getMaxHealth());
        playerHealthBar.setValue(player.getHealth());
        playerHealthBar.setStringPainted(true);
        playerHealthBar.setString(player.getHealth() + " / " + player.getMaxHealth());
        playerHealthBar.setStringPainted(true);
        playerHealthBar.setUI(new BasicProgressBarUI() {
            protected Color getSelectionBackground() { return Color.BLACK; }
            protected Color getSelectionForeground() { return Color.BLACK; }
        });
        

        Dimension size = new Dimension(400, 20);
        playerHealthBar.setPreferredSize(size);
        playerHealthBar.setMaximumSize(size);
        playerHealthBar.setMinimumSize(size);
        
        playerHealthBar.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        //set color of the health bar based on health percentage
        playerHealthBar.setForeground(new Color((int)(255 - 255*((player.getHealth() * 1.0) / player.getMaxHealth())),(int)(255*(player.getHealth() * 1.0 / player.getMaxHealth())),0));
        playerHealthBar.setBackground(null);
        playerHealthBar.setBorder(new BevelBorder(0, Color.black, Color.black));





        invPanel.removeAll();
        JLabel health = new JLabel("Health: " + player.getHealth() + "/" + player.getMaxHealth());
        JLabel strength = new JLabel("Strength: " + player.getStrength());
        JLabel agility = new JLabel("Agility: " + player.getAgility());
        JLabel intelligence = new JLabel("Intelligence: " + player.getIntelligence());
        JLabel emptyJLabel = new JLabel(" ");
        JLabel helmet; 
        JLabel chestplate; 
        JLabel pants; 
        JLabel boots; 
        JLabel LeftHand; 
        JLabel RightHand; 
        JLabel Armor = new JLabel("Total Armor Value: " + player.getArmor());
        JLabel WorldName = new JLabel("Area: " + world.getArea());
        JLabel StageNum = new JLabel("Room Number: " + world.stageNum);

        helmet = createInventoryLabel("Helmet", player.helm); 
        chestplate = createInventoryLabel("Chestplate", player.chestplate);
        pants = createInventoryLabel("Pants", player.pants);
        boots = createInventoryLabel("Boots", player.shoes);
        LeftHand = createInventoryLabel("Left Hand", player.LHand);
        RightHand = createInventoryLabel("Right Hand", player.RHand);


        invPanel.add(health);
        invPanel.add(playerHealthBar);
        invPanel.add(strength);
        invPanel.add(agility);
        invPanel.add(intelligence);
        invPanel.add(emptyJLabel);
        invPanel.add(helmet);
        invPanel.add(chestplate);
        invPanel.add(pants);
        invPanel.add(boots);
        invPanel.add(LeftHand);
        invPanel.add(RightHand);
        invPanel.add(new JLabel(" "));
        invPanel.add(Armor);
        invPanel.add(new JLabel(" "));
        invPanel.add(WorldName);
        invPanel.add(StageNum);

        textStyling.giveLabelsColorAndShape(invPanel, 18, Color.WHITE);

        invPanel.revalidate();
        invPanel.repaint();
        this.revalidate();
        this.repaint();

        gui.updateImage();

    }

	private static JLabel createInventoryLabel(String s, equipables equip){
        if(equip != null){
            return new JLabel(s + ": " + equip.getItemName() + " - " + equip.getQuality());
        }
        else{
            return new JLabel(s + ": ");
        }

    }



}
