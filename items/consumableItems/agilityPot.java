package items.consumableItems;
import playerFiles.player;
import items.consumables;

public class agilityPot extends consumables{
    public agilityPot(){
        setPrice(15);
        setName("Agility Potion");
    }

    public void Use(){
        removeFromInv();
        player.applyBuff("Agility", 7, 4);
    }
}
