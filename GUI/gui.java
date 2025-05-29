package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import items.equipables;
import playerFiles.player;
import util.saveFiles;

public class gui {

    public static JFrame frame;
    public static JPanel invPanel;
    private static JPanel outsideInvPanel;
    private static JPanel topofInvPanel;
    public static JPanel txtPanel;
    public static JTextField textField;
    public static String latestInput;
    private static JScrollPane scrollPane;
    

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
        txtPanel.setBackground(Color.white);
        txtPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        scrollPane = new JScrollPane(txtPanel);

        //create a gridlayout container to hold the side by side panels
        JPanel gridLayoutPanel = new JPanel(new GridLayout(1,2,10,10));
        gridLayoutPanel.add(outsideInvPanel);
        gridLayoutPanel.add(scrollPane);

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

    public static void printOnGameSide(String s){
        JLabel text = new JLabel();
        text.setText(s);
        txtPanel.add(text);
        txtPanel.revalidate();

        SwingUtilities.invokeLater(() -> {
            text.scrollRectToVisible(text.getBounds());
        });
        
    }
    public static void newlOnGameSide(){
        JLabel text = new JLabel();
        text.setText(" ");
        txtPanel.add(text);
        txtPanel.revalidate();

    }

    public static void updatePlayerSide(){
        topofInvPanel.removeAll();

        topofInvPanel.add(new JLabel("Name: " + player.getName() + "                     "));
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
}
