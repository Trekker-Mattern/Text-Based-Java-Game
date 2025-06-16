package items.genericItems;

import items.consumables;

public abstract class attackingConsumable extends consumables{
    public attackingConsumable(){
        isAttackingConsumable = true;

    }

    public void Use(){

    }

    public abstract int getDamageInt();
    public abstract String getAttackString();
}
