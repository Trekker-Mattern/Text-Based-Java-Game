package com.textbasedgame.items;
import com.textbasedgame.playerFiles.player;

public abstract class consumables extends item {
    protected boolean isAttackingConsumable = false;
    protected int numberInStack;
    public consumables(){
        numberInStack = 1;
    }
    @Override
    public void Use(){
        numberInStack--;
        if(numberInStack <= 0){
            removeFromInv();
        }
    }
    
    public void removeFromInv(){
        player.inventory.remove(this);
    }
    public void increaseStackValue(int increaseVal){
        numberInStack += increaseVal;
    }
    public String inventoryPrintingString(){
        return numberInStack + "x " + getItemName();
    }
    public int getStackValue(){return numberInStack;}
    public void setNumberInStack(int numberInStack) {
        this.numberInStack = numberInStack;
    }
    public boolean equals(consumables obj) {
        
        if(obj.getClass() == this.getClass()){
            if(this.getItemName().equals(obj.getItemName())){return true;}
        }
        return false;
    }
}
