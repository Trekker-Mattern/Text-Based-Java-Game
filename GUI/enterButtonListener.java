package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class enterButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        notify();
    }

    public String getInput(){
        try{
            wait();
            return gui.textField.getText();
        }
        catch(Exception e){
            System.out.println("Something went wrong with input");
            return "";
        }
        
    }
}
