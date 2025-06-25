package items.consumableItems;
import items.consumables;
import playerFiles.player;
import playerFiles.player.buffTypes;

public class strengthPot extends consumables {
    public strengthPot(){
        setPrice(20);
        setName("Strength Potion");
    }

    public void Use(){
        removeFromInv();
        player.applyBuff(buffTypes.STRENGTH, 3, 4);
    }
}
