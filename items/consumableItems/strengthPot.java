package items.consumableItems;
import items.consumables;
import playerFiles.player;

public class strengthPot extends consumables {
    public strengthPot(){
        setPrice(20);
        setName("Strength Potion");
    }

    public void Use(){
        removeFromInv();
        player.applyBuff("Strength", 3, 4);
    }
}
