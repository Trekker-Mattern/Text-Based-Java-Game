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
        else if(input.toLowerCase().contains("list buffs") || input.toLowerCase().equals("-lb"))
        {
            gui.listBuffs();
        }
        else if(input.toLowerCase().contains("show raw stats") || input.toLowerCase().equals("-srs")){
            gui.showRawStats();
        }
        else if(input.toLowerCase().equals("clear") || input.toLowerCase().equals("-c")){
            gui.clearTopTextBox();
        }

        gui.textField.setText("");
        gui.setInput(input); // Send input to backend
    }


}
