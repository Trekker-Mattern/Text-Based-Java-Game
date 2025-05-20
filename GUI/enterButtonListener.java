package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class enterButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String input = gui.textField.getText();
        gui.textField.setText("");
        gui.setInput(input); // Send input to backend
    }


}
