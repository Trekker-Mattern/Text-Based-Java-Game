package items;
import playerFiles.*;

public abstract class consumables extends item {
    protected boolean isAttackingConsumable = false;
    public consumables(){
        setIsConsumable(true);
    }
    public void Use(){
        removeFromInv();
    }
    public void removeFromInv(){
        player.inventory.remove(this);
        player.consumableInv.remove(this);
    }
}
