package items.chestArmorItems;
import items.genericItems.chestArmor;
import playerFiles.*;

public class chestplate extends chestArmor{
    int armourAdd;
    int quality;
    public chestplate(){
        quality = (int)((Math.random() * 6) +1);
        setQuality(quality);
        armourAdd = quality * 2;
        setPrice(45);
        setName("Chestplate");

    }
    public void onEquip(){
        
        player.addArmour(armourAdd);
    }
    public void onUnequip(){
        player.addArmour(armourAdd*-1);
    }
}