package com.textbasedgame.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.textbasedgame.util.response;

public class enterButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String input = gui.textField.getText();

        if(response.quit(input)){
            gui.quit();
        }
        else if(input.toLowerCase().contains("list buffs"))
        {
            gui.listBuffs();
        }

        gui.textField.setText("");
        gui.setInput(input); // Send input to backend
    }


}
