package com.textbasedgame.items;

public abstract class item {
    private int itemPrice;
    private String name;
    private boolean isConsumable;
    protected String classofItem;
    
    public item(){
        classofItem = this.getClass().getName();
    }
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
    @Override
    public String toString() {
        return name;
    }
    //protected abstract void setClass(Class<? extends item> clazz);
    public String getClassofItem(){
        return classofItem;
    }

    //Be careful with this use
    public void Use(){}
    public abstract void printInfo();
}
