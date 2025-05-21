package items.headArmorItems;
import items.genericItems.headArmour;


public class helmet extends headArmour{

    
    public helmet(){
        quality = (int)((Math.random() * 6) +1);
        setQuality(quality);
        armourAdd = (int)(quality * 1.3);
        setPrice(20);
        setName("Helmet");

    }
    
    
}