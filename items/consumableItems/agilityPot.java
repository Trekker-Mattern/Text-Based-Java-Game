package items.consumableItems;
import playerFiles.player;
import playerFiles.player.buffTypes;
import items.consumables;

public class agilityPot extends consumables{
    public agilityPot(){
        setPrice(15);
        setName("Agility Potion");
    }

    public void Use(){
        removeFromInv();
        player.applyBuff(buffTypes.AGILITY, 7, 4);
    }
}
