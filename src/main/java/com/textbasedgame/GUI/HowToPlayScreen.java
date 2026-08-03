package com.textbasedgame.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.textbasedgame.GUI.Styles.*;

public class HowToPlayScreen {
    public static void openScreen() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (gui.frame == null) {
                    return;
                }

                gui.frame.getContentPane().removeAll();
                gui.frame.getContentPane().setLayout(new BorderLayout());

                JPanel mainPanel = new JPanel(new BorderLayout());
                mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
                mainPanel.setBackground(Color.WHITE);

                JPanel contentPanel = new JPanel();
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
                contentPanel.setOpaque(false);

                JLabel titleLabel = new JLabel("How to Play", JLabel.CENTER);
                titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
                titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                titleLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, titleLabel.getPreferredSize().height));
                contentPanel.add(titleLabel);
                contentPanel.add(Box.createVerticalStrut(20));

                JSeparator separator = new JSeparator();
                separator.setForeground(Color.BLACK);
                separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));
                contentPanel.add(Box.createVerticalStrut(12));
                contentPanel.add(separator);
                contentPanel.add(Box.createVerticalStrut(48));


                String howToPlayText = "Welcome to Trekker's Adventure, a play on my name, and on the idea that a Trekker is someone who walks.\n\n 1. To play, you will be given a prompt such as 'You enter a room, would you like to continue?'. \n\tYou simply need to respond with yes, no, or a similar answer.\n\n 2. Every 5 levels there is a village where you can enter the shop or rest.\n\n 3. To continue the adventure, you enter the dungeon by typing 'dungeon' or more simply 'd'.\n\n 4. In a fight against an enemy, you can fight ('f'), use an item ('i'), or run ('run'). \n\n 5. When you inevitably fall in battle, you will be reserected back at the start of your adventure. You will keep your items and money, but the progress setback will be punishing.";
                JTextArea howToPlayArea = new JTextArea(howToPlayText);
                howToPlayArea.setFont(new Font("Arial", Font.PLAIN, 18));
                howToPlayArea.setLineWrap(true);
                howToPlayArea.setWrapStyleWord(true);
                howToPlayArea.setEditable(false);
                howToPlayArea.setOpaque(false);
                howToPlayArea.setFocusable(false);
                howToPlayArea.setAlignmentX(Component.LEFT_ALIGNMENT);
                contentPanel.add(howToPlayArea);

                JPanel buttonContainer = new JPanel(new GridLayout(1, 2, 20, 20));
                buttonContainer.setBorder(new EmptyBorder(20, 0, 0, 0));
                buttonContainer.setOpaque(false);

                JButton backButton = new JButton("Back");
                JButton startButton = new JButton("Start");

                buttonStyler.setDefaultButtonSize(backButton);
                buttonStyler.setDefaultButtonSize(startButton);
                buttonStyler.styleTitleScreenButton(backButton, true);
                buttonStyler.styleTitleScreenButton(startButton, true);

                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TitleScreen.openTitleScreen();
                    }
                });

                startButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gui.frame.getContentPane().removeAll();
                        gui.runGui();
                        synchronized (TitleScreen.class) {
                            TitleScreen.gameOpened = true;
                            TitleScreen.class.notifyAll();
                        }
                    }
                });

                buttonContainer.add(backButton);
                buttonContainer.add(startButton);
                mainPanel.add(contentPanel, BorderLayout.CENTER);
                mainPanel.add(buttonContainer, BorderLayout.SOUTH);

                gui.frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
                gui.frame.revalidate();
                gui.frame.repaint();
                gui.frame.setVisible(true);
            }
        });
    }
}
