package com.textbasedgame.items;

public abstract class item {
    private int itemPrice;
    private String name;
    private boolean isConsumable;
    protected Class<? extends item> classofItem;

    private boolean isAttackingItem;

    public void setName(String name){
        this.name = name;
    }
    public String getItemName(){
        return name;
    }
    public int getPrice(){
        return itemPrice;
    }
    public void setPrice(int x){
        itemPrice = x;
    }
    public String toString() {

        return name;
    }
    public boolean isConsumable(){
        return isConsumable;
    }
    public void setIsConsumable(boolean x){
        isConsumable = x;
    }
    public void Use(){
        
    }
    protected abstract void setClass(Class<? extends item> clazz);
    public int getStatIncrease(){return 0;}
    public void setIsAttackingItem(boolean x){
        isAttackingItem = x;
    }
    public boolean attackingItem(){
        return isAttackingItem;
    }
    public String getAttackString(){
        return "";
    }
    public String getQuality(){
        return null;
    }
}
