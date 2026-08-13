package com.textbasedgame.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.ActionMap;
import javax.swing.InputMap;

import com.textbasedgame.GUI.pictureLoader.imageIDs;
import com.textbasedgame.GUI.GUISegments.InputPanel;
import com.textbasedgame.GUI.GUISegments.InventoryPanel;
import com.textbasedgame.GUI.GUISegments.TextPanel;
import com.textbasedgame.GUI.Styles.*;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.pair;
import com.textbasedgame.util.saveFiles;
import com.textbasedgame.util.triple;
import com.textbasedgame.world.world;


public class gui {

    public static JFrame frame;
	public static InputPanel inputPanel;
    public static InventoryPanel invPanel;
	public static TextPanel textPanel;
    private static JPanel leftPanelContainer;
    public static JPanel imagePanel;
    public static String latestInput;
    private static imageIDs currentImageID;

    
    private static final pictureLoader pLoader = new pictureLoader();


    //For fullscreen toggles
    private static boolean fullscreen;
    private static Rectangle windowedBounds;
    private static int windowedExtendedState;
   

    /**
    * Initialize GUI and Required GUI aspects
    */
    public static void setupGui(){
        textStyling.createCharacterStylingMap();
        fullscreen = false;
        //set up the container
        frame = new JFrame("Trekker RPG");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(1000, 800);
        frame.addComponentListener(new resizeActionListener());
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);

        frame.setIconImage(pLoader.getAppIcon().getImage());

        //Fullscreen toggle with F11
        InputMap inputMap = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = frame.getRootPane().getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("F11"), "toggleFullscreen");
        actionMap.put("toggleFullscreen", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!fullscreen) {
                    windowedBounds = frame.getBounds();
                    windowedExtendedState = frame.getExtendedState();
                    frame.dispose();
                    frame.setUndecorated(true);
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                } else {
                    frame.dispose();
                    frame.setUndecorated(false);
                    if (windowedBounds != null) {
                        frame.setBounds(windowedBounds);
                    }
                    frame.setExtendedState(windowedExtendedState);
                }
                frame.setLocationRelativeTo(null);
                frame.setResizable(true);
                frame.setVisible(true);
                if (inputPanel != null && inputPanel.getInputButton() != null) {
                    frame.getRootPane().setDefaultButton(inputPanel.getInputButton());
                }
                fullscreen = !fullscreen;
            }
        });
        
        //make the frame visible
        frame.setVisible(true);
    }


    public static void runGui(){

        //setup first JPanel
        leftPanelContainer = new JPanel(new GridLayout(2, 1, 10, 10));
		invPanel = new InventoryPanel();
        imagePanel = new JPanel();
		textPanel = new TextPanel(); 
		inputPanel = new InputPanel();

        leftPanelContainer.add(invPanel);
        leftPanelContainer.add(imagePanel);

        


        //create a gridlayout container to hold the side by side panels
        JPanel gridLayoutPanel = new JPanel(new GridLayout(1,2,10,10));
        gridLayoutPanel.add(leftPanelContainer);
        gridLayoutPanel.add(textPanel);


        
        //Create a main panel to hold the split pane and the input panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(gridLayoutPanel, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        //add the main panel to the frame
        frame.add(mainPanel);
        frame.getRootPane().setDefaultButton(inputPanel.getInputButton());

        
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
                    System.out.println(e);
                }
            }
            String input = latestInput;
            latestInput = null;
            return input;
        }
    }

    public static String getInput(String printString){
        gui.printOnGameSide(printString);
        return getInput();
    }


	public static void setMonsterRoomUI(String monsterName, int monsterCurrHealth, int monsterMaxHealth){textPanel.setMonsterRoomUI(monsterName, monsterCurrHealth, monsterMaxHealth);}
    public static void pushOldText(){textPanel.pushOldText();}
    public static void printOnGameSide(String s){textPanel.printOnGameSide(s);}
    public static void printOnGameSide(String s, textStyling.styles style){textPanel.printOnGameSide(s, style);}
    public static void printDialogue(String dialogue, world.CharacterNames character){textPanel.printDialogue(dialogue, character);}
    public static void newlOnGameSide(){textPanel.newlOnGameSide();}
	public static void updatePlayerSide(){ invPanel.updatePlayerSide();} 
	public static void clearTopTextBox(){textPanel.removeAll();}

    public static void quit(){
        saveFiles.save();
        System.exit(0);
    }
    public static void listBuffs(){
        pushOldText();
        gui.printOnGameSide("--- Showing Buffs ---");
        for(triple<player.buffTypes, Integer, Integer> buff : player.buffs){
            printOnGameSide(buff.first.toString() + " buff of strength " + buff.second + " for " + buff.third + " encounters");
        }
        pair<player.buffTypes, Integer> aSB = player.getArmorSetBuff();
        if(aSB != null){
            printOnGameSide("-Armor Set Buff-");
            printOnGameSide(aSB.first.toString() + " buff of " + aSB.second);
        }
    }
    public static void showRawStats(){
        gui.printOnGameSide(Integer.toString(player.strength));
        gui.printOnGameSide(Integer.toString(player.agility));
        gui.printOnGameSide(Integer.toString(player.intelligence));
    }

    public static void updateImage(){
        
        imagePanel.removeAll();

        ImageIcon img = pLoader.getImage(currentImageID);
        if(img == null || img.getIconHeight() == 0 || img.getIconHeight() == 0){img = pLoader.getImage(imageIDs.LIBRARY);}
        ImageIcon imgIcon;
        try{
            imgIcon = new ImageIcon(img.getImage().getScaledInstance(imagePanel.getWidth(), imagePanel.getHeight(), Image.SCALE_DEFAULT));
        }
        catch (Exception e){
            System.out.println(e);
            imgIcon = new ImageIcon(img.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
        }

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(imgIcon);
        imagePanel.add(imageLabel);
        imagePanel.revalidate();
        imagePanel.repaint();
    }
    public static void setImage(imageIDs imageID){
        currentImageID = imageID;
        updateImage();
    }


}
