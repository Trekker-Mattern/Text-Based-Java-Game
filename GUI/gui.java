package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class gui {

    public static JFrame frame;
    public static JPanel invPanel;
    public static JPanel txtPanel;
    public static JTextField textField;
    

    public static void runGui(){
        //set up the container
        frame = new JFrame("Trekker RPG");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


        //setup first JPanel
        Dimension minSizeinv = new Dimension(600,800);
        invPanel = new JPanel();
        invPanel.setBackground(Color.gray);
        invPanel.setMinimumSize(minSizeinv);

        
        //setup second JPanel for text
        Dimension minSizeTxt = new Dimension(600,800);
        txtPanel = new JPanel();
        txtPanel.setMinimumSize(minSizeTxt);
        txtPanel.setBackground(Color.white);

        //create a gridlayout container to hold the side by side panels
        JPanel gridLayoutPanel = new JPanel(new GridLayout(1,2,10,10));
        gridLayoutPanel.add(invPanel);
        gridLayoutPanel.add(txtPanel);

        //Create the text input panel
        JPanel inputPanel = new JPanel();
        JButton enterButton = new JButton("Enter");
        enterButton.setFocusable(false);
        enterButtonListener buttonListener = new enterButtonListener();
        enterButton.addActionListener(buttonListener);

        JTextField textField = new JTextField(45);
        inputPanel.add(textField);
        inputPanel.add(enterButton);


        //Create a main panel to hold the split pane and the input panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(gridLayoutPanel, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        //add the main panel to the frame
        frame.add(mainPanel);

        //make the frame visible
        frame.setVisible(true);
    }
}
