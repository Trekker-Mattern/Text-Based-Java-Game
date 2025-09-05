package com.textbasedgame.items.keyItems;

import java.util.ArrayList;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.genericItems.keyItem;

public class potionsBag extends keyItem{
    
    private ArrayList<potionHerbs> bagContents;

    public potionsBag(){
        bagContents = new ArrayList<>();
    }

    @Override
    public void Use(){

    }
    @Override
    public void printInfo(){

    }

    public void addHerbToBag(potionHerbs potHerb){
        bagContents.add(potHerb);
    }

    public potionHerbs getHerbFromBag(){
        if(!bagContents.isEmpty()){
            listHerbsInBag();
            gui.printOnGameSide("Which ingredient would you like to pick?");
            try{
                int ingredientNum = Integer.parseInt(gui.getInput());
                return bagContents.get(ingredientNum);
            }
            catch(NumberFormatException | IndexOutOfBoundsException e){
                gui.printOnGameSide("Select a valid Number!");
            }

        }
        return null;
    }

    public void listHerbsInBag(){
        for(int i = 0; i < bagContents.size(); i++){
            gui.printOnGameSide((i+1) + ": " + bagContents.get(i).toString());
        }
    }


    public enum potionHerbs {Peppermint, Sage, ToadsFoot, Mushroom, Honeysuckle, Dogwood, EntBranch, LemonGrass, Ivy}

}
