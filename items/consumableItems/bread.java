package items.consumableItems;
import GUI.gui;
import items.consumables;
import playerFiles.*;
public class bread extends consumables{
    int healthIncrease =  player.getMaxHealth()  / 3;
    public bread(){
        setPrice(7);
        setName("Bread");
    }
    public void Use(){
        removeFromInv();
        gui.printOnGameSide("You heal for " + healthIncrease);
        player.addHealth(healthIncrease);
    }
}
