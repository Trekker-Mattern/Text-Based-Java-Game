package com.textbasedgame.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Image;

import com.textbasedgame.GUI.Styles.*;

public class TitleScreen {
    public static boolean gameOpened = false;
    
    public static void openTitleScreen(){
        pictureLoader titleScreenPictureLoader = new pictureLoader();

        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Background image in the center
        
        JLabel bgLabel = new JLabel();
        bgLabel.setBorder(new EmptyBorder(100, 100, 0, 100));
        
        bgLabel.setHorizontalAlignment(JLabel.CENTER);
        bgLabel.setVerticalAlignment(JLabel.CENTER);
        mainPanel.add(bgLabel, BorderLayout.CENTER);

        // Button container (transparent)
        JPanel buttonContainer = new JPanel(new GridLayout(1, 3, 20, 20));
        buttonContainer.setBorder(new EmptyBorder(10, 20, 10, 20));
        buttonContainer.setOpaque(false);

        JButton startButton = new JButton("Start");
        JButton howToPlayButton = new JButton("How To Play");
        JButton quitButton = new JButton("Quit");

        buttonStyler.setDefaultButtonSize(startButton);
        buttonStyler.setDefaultButtonSize(howToPlayButton);
        buttonStyler.setDefaultButtonSize(quitButton);

        buttonStyler.styleTitleScreenButton(startButton, true);
        buttonStyler.styleTitleScreenButton(howToPlayButton, true);
        buttonStyler.styleTitleScreenButton(quitButton, true);



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

        Image titleScreenImage = titleScreenPictureLoader.getImage(pictureLoader.imageIDs.TITLESCREEN).getImage();
        int bgWidth = gui.frame.getWidth();
        int bgHeight = gui.frame.getHeight();
        if (bgWidth <= 0 || bgHeight <= 0) {
            bgWidth = 750;
            bgHeight = 550;
        }
        ImageIcon img = new ImageIcon(titleScreenImage.getScaledInstance(bgWidth, bgHeight, Image.SCALE_SMOOTH));
        bgLabel.setIcon(img);
        
        gui.frame.getContentPane().removeAll();
        gui.frame.setLayout(new BorderLayout());
        gui.frame.add(mainPanel, BorderLayout.CENTER);
        gui.frame.revalidate();
        gui.frame.repaint();
        gui.frame.setVisible(true);
    }

    
}

