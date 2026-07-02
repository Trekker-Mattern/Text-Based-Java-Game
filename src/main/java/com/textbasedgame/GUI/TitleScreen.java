package com.textbasedgame.GUI;
import java.util.Queue;
import java.util.LinkedList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import javax.swing.JLayeredPane;

import com.textbasedgame.runTime;

public class TitleScreen {
    public static boolean gameOpened = false;
    
    public static void openTitleScreen(){
        pictureLoader titleScreenPictureLoader = new pictureLoader();

        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Background image in the center
        JLabel bgLabel = new JLabel(titleScreenPictureLoader.getImage(pictureLoader.imageIDs.BLANK));
        bgLabel.setHorizontalAlignment(JLabel.CENTER);
        bgLabel.setVerticalAlignment(JLabel.CENTER);
        mainPanel.add(bgLabel, BorderLayout.CENTER);

        // Button container (transparent)
        JPanel buttonContainer = new JPanel(new GridLayout(1, 3, 20, 20));
        buttonContainer.setOpaque(false);

        JButton startButton = new JButton("Start");
        JButton howToPlayButton = new JButton("How To Play");
        JButton quitButton = new JButton("Quit");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.frame.getContentPane().removeAll();
                gui.runGui();
                synchronized(TitleScreen.class){
                    gameOpened = true;
                    TitleScreen.class.notify();
                }
            }
        });

        howToPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HowToPlayScreen.openScreen();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.quit();
            }
        });

        buttonContainer.add(startButton);
        buttonContainer.add(howToPlayButton);
        buttonContainer.add(quitButton);
        mainPanel.add(buttonContainer, BorderLayout.SOUTH);

        gui.frame.getContentPane().removeAll();
        gui.frame.setLayout(new BorderLayout());
        gui.frame.add(mainPanel, BorderLayout.CENTER);
        gui.frame.revalidate();
        gui.frame.repaint();
        gui.frame.setVisible(true);
    }

    
}

