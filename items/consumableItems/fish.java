package items.consumableItems;
import GUI.gui;
import items.consumables;
import playerFiles.*;
public class fish extends consumables {
    int healthIncrease =  player.getMaxHealth()  / 5;
    public fish(){
        setPrice(5);
        setName("Fish");
        if(player.getName().equals("Jesus")){
            setName("Bread (Used to be fish)");
        }
    }
    public void Use(){
        removeFromInv();
        gui.printOnGameSide("You heal for " + healthIncrease);
        player.addHealth(healthIncrease);
    }
}
