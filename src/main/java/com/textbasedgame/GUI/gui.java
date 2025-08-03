package com.textbasedgame.GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import com.textbasedgame.items.equipables;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.*;
import com.textbasedgame.world.world;

public class gui {

    public static JFrame frame;
    public static JPanel invPanel;
    private static JPanel outsideInvPanel;
    private static JPanel topofInvPanel;
    public static JPanel txtPanel;
    public static JPanel recentTextPanel;
    public static JTextField textField;
    public static String latestInput;
    private static JScrollPane scrollPane;
    private static JScrollPane secondScrollPane;
    

    public static void runGui(){
        //set up the container
        frame = new JFrame("Trekker RPG");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);


        //setup first JPanel
        outsideInvPanel = new JPanel();
        outsideInvPanel.setLayout(new BorderLayout());

        topofInvPanel = new JPanel();

        Dimension minSizeinv = new Dimension(600,800);
        invPanel = new JPanel();
        invPanel.setLayout(new BoxLayout(invPanel, BoxLayout.Y_AXIS));
        invPanel.setBackground(Color.gray);
        invPanel.setMinimumSize(minSizeinv);
        invPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        outsideInvPanel.add(invPanel, BorderLayout.CENTER);
        outsideInvPanel.add(topofInvPanel, BorderLayout.NORTH);
        

        
        //setup second JPanel for text
        Dimension minSizeTxt = new Dimension(600,800);
        txtPanel = new JPanel();
        txtPanel.setLayout(new BoxLayout(txtPanel, BoxLayout.Y_AXIS));
        txtPanel.setMinimumSize(minSizeTxt);
        txtPanel.setBackground(new Color(215, 215, 215));
        txtPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //JPanel for New Important text before stuff gets pushed to the main box
        recentTextPanel = new JPanel();
        recentTextPanel.setLayout(new BoxLayout(recentTextPanel, BoxLayout.Y_AXIS));
        recentTextPanel.setMinimumSize(minSizeTxt);
        recentTextPanel.setBackground(Color.white);
        recentTextPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        scrollPane = new JScrollPane(txtPanel);
        secondScrollPane = new JScrollPane(recentTextPanel);
        //Split the text panel into a section for new important information and old news
        JPanel txtPanelSplit = new JPanel(new GridLayout(2,1, 10, 10));
        txtPanelSplit.add(scrollPane);
        txtPanelSplit.add(secondScrollPane);

        //create a gridlayout container to hold the side by side panels
        JPanel gridLayoutPanel = new JPanel(new GridLayout(1,2,10,10));
        gridLayoutPanel.add(outsideInvPanel);
        gridLayoutPanel.add(txtPanelSplit);

        

        //Create the text input panel
        JPanel inputPanel = new JPanel();
        JButton enterButton = new JButton("Enter");
        enterButton.setFocusable(false);
        enterButtonListener buttonListener = new enterButtonListener();
        enterButton.addActionListener(buttonListener);

        textField = new JTextField(45);
        inputPanel.add(textField);
        inputPanel.add(enterButton);


        //Create a main panel to hold the split pane and the input panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(gridLayoutPanel, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        //add the main panel to the frame
        frame.add(mainPanel);
        frame.getRootPane().setDefaultButton(enterButton);

        //make the frame visible
        frame.setVisible(true);
    }

    public static void setInput(String input){
        latestInput = input;
        synchronized(gui.class){
            gui.class.notify();
        }
    }

    public static String getInput(){
        synchronized(gui.class){
            while(latestInput == null){
                try{
                    gui.class.wait();
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            String input = latestInput;
            latestInput = null;
            return input;
        }
    }
    public static void pushOldText(){
        for(Component text: recentTextPanel.getComponents()){
            txtPanel.add(text);
        }
        recentTextPanel.removeAll();
        SwingUtilities.invokeLater(() -> {
            txtPanel.scrollRectToVisible(txtPanel.getComponents()[txtPanel.getComponentCount()-1].getBounds());
        });
        recentTextPanel.revalidate();
        recentTextPanel.repaint();
        txtPanel.revalidate();
        txtPanel.repaint();
    }

    public static void printOnGameSide(String s){
        JLabel text = new JLabel();
        text.setText(s);
        recentTextPanel.add(text);
        recentTextPanel.revalidate();
        SwingUtilities.invokeLater(() -> {
            text.scrollRectToVisible(text.getBounds());
        });
    }
    public static void newlOnGameSide(){
        JLabel text = new JLabel();
        text.setText(" ");
        recentTextPanel.add(text);
        recentTextPanel.revalidate();

    }

    public static void updatePlayerSide(){
        topofInvPanel.removeAll();

        topofInvPanel.add(new JLabel("Name: " + player.getName() + "                     "));
        topofInvPanel.add(new JLabel("Level: " + player.getPlayerLevel() + "                     "));
        topofInvPanel.add(new JLabel("Shmeckles: " + Integer.toString(player.BankBalance) + "                     "));
        topofInvPanel.add(new JLabel("XP: " + player.getXP() + "/" + player.getXpToLevelUp()));


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

        giveLabelsColorAndShape(invPanel, 18, Color.WHITE);

        invPanel.revalidate();
        invPanel.repaint();
        topofInvPanel.revalidate();
        topofInvPanel.repaint();

    }

    private static JLabel createInventoryLabel(String s, equipables equip){
        if(equip != null){
            return new JLabel(s + ": " + equip.toString());
        }
        else{
            return new JLabel(s + ": ");
        }

    }

    private static void giveLabelsColorAndShape(JPanel panel, int fontSize, Color color){
        for(java.awt.Component comp : panel.getComponents()){
            if(comp instanceof JLabel){
                JLabel l = (JLabel)comp;
                java.awt.Font oldFont = l.getFont();
                l.setFont(new Font(oldFont.getName(), oldFont.getStyle(), fontSize ));
                l.setForeground(color);
            }
        }
    }

    public static void quit(){
        saveFiles.save();
        System.exit(0);
    }
    public static void listBuffs(){
        pushOldText();
        for(triple<player.buffTypes, Integer, Integer> buff : player.buffs){
            printOnGameSide(buff.first.toString() + " buff of strength " + buff.second + " for " + buff.third + " encounters");
        }
    }
    public static void showRawStats(){
        gui.printOnGameSide(Integer.toString(player.strength));
        gui.printOnGameSide(Integer.toString(player.agility));
        gui.printOnGameSide(Integer.toString(player.intelligence));
    }
}
