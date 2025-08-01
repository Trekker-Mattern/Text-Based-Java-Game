package com.textbasedgame.items.genericItems;

import com.textbasedgame.items.consumables;

public abstract class attackingConsumable extends consumables{
    public attackingConsumable(){
        isAttackingConsumable = true;
    }

    public abstract int getDamageInt();
    @Override
    public abstract String getAttackString();
}
