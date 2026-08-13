package com.textbasedgame.GUI.GUISegments;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.textbasedgame.GUI.enterButtonListener;
import com.textbasedgame.GUI.Styles.buttonStyler;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;


public class InputPanel extends JPanel{

	private JButton enterButton;
	public JTextField textField;

	public InputPanel(){

        /////////////////////////////////////////////////////////////
        ///        TEXT INPUT PANEL
        ////////////////////////////////////////////////////////////
        

        //Create the text input panel
        enterButton = new JButton("Enter");
        
        //Button Styling
        enterButton.setFocusable(false);
        enterButtonListener buttonListener = new enterButtonListener();
        enterButton.addActionListener(buttonListener);
        buttonStyler.styleEnterButton(enterButton);
        

        //TextField Styling
        textField = new JTextField(45);
        textField.setFont(new Font("Times New Roman", Font.BOLD, 20));
        textField.setForeground(Color.BLACK);
        textField.setSize(new Dimension(110, 90));

        this.add(textField);
        this.add(enterButton);
        this.setBackground(Color.gray);

	}

	public JButton getInputButton(){return enterButton;}

}
