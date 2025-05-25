package items.headArmorItems;
import items.genericItems.headArmor;

public class spartanHelmet extends headArmor {
    public spartanHelmet(){

        armorAdd = (int)(quality * 1.8);
        setPrice(40);
        setName("Spartan Helmet");
        buffType = "Strength";
        buffValue = 3;
    }    

    
}
