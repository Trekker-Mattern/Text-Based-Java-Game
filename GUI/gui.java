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
    public static String latestInput;
    

    public static void runGui(){
        //set up the container
        frame = new JFrame("Trekker RPG");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);


        //setup first JPanel
        Dimension minSizeinv = new Dimension(600,800);
        invPanel = new JPanel();
        invPanel.setBackground(Color.gray);
        invPanel.setMinimumSize(minSizeinv);
        invPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        //setup second JPanel for text
        Dimension minSizeTxt = new Dimension(600,800);
        txtPanel = new JPanel();
        txtPanel.setLayout(new BoxLayout(txtPanel, BoxLayout.Y_AXIS));
        txtPanel.setMinimumSize(minSizeTxt);
        txtPanel.setBackground(Color.white);
        txtPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(txtPanel);

        //create a gridlayout container to hold the side by side panels
        JPanel gridLayoutPanel = new JPanel(new GridLayout(1,2,10,10));
        gridLayoutPanel.add(invPanel);
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
    }
    public static void newlOnGameSide(){
        JLabel text = new JLabel();
        text.setText(" ");
        txtPanel.add(text);
        txtPanel.revalidate();

    }
}
