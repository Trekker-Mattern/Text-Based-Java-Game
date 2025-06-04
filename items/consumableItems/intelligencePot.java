package items.consumableItems;
import items.consumables;
import playerFiles.player;
public class intelligencePot extends consumables {
    public intelligencePot(){
        setPrice(20);
        setName("Intelligence Potion");
    }

    public void Use(){
        removeFromInv();
        player.applyBuff("Intelligence", 3, 4);
    }
}
