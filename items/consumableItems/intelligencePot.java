package items.consumableItems;
import items.consumables;
import playerFiles.player;
import playerFiles.player.buffTypes;
public class intelligencePot extends consumables {
    public intelligencePot(){
        setPrice(20);
        setName("Intelligence Potion");
    }

    public void Use(){
        removeFromInv();
        player.applyBuff(buffTypes.INTELLIGENCE, 3, 4);
    }
}
