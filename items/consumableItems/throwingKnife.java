package items.consumableItems;
import playerFiles.player;
import items.genericItems.attackingConsumable;

public class throwingKnife extends attackingConsumable {
    int amountInSet = 3;
    public throwingKnife(){
        setPrice(3);
        setName("Throwing Knife");
    }
    @Override
    public void Use(){
        amountInSet--;
        if(amountInSet <= 0){
            removeFromInv();
        }

    }

    public int getDamageInt(){
        return player.getAgility() + player.getPlayerLevel();
    }
    public String getAttackString(){
        return "You throw the small knife with considerable force";
    }
}
