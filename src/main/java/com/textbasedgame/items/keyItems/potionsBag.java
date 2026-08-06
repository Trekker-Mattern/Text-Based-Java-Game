package com.textbasedgame.items.keyItems;

import java.util.ArrayList;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.genericItems.keyItem;
import com.textbasedgame.util.pair;

public class potionsBag extends keyItem{
    
    private ArrayList<pair<potionHerbs, Integer>> bagContents;

    public potionsBag(){
        bagContents = new ArrayList<pair<potionHerbs, Integer>>();
        this.setName("Potions Bag");
        this.setDescription("A bag filled with various potion ingredients (mostly herbs).");
    }

    @Override
    public void Use(){

    }
    @Override
    public void printInfo(){
        gui.printOnGameSide("Name: " + this.getItemName());
    }

    public void addHerbToBag(potionHerbs potHerb){
		for (pair<potionHerbs, Integer> pair : bagContents) {
				if (pair.first == potHerb) {
					pair.second++;
					return;
				}
			}

		bagContents.add(new pair<potionHerbs, Integer>(potHerb, 1));
    }

    public potionHerbs getHerbFromBag(){
        if(!bagContents.isEmpty()){
            gui.printOnGameSide("Contents of the Potions Bag:");
            listHerbsInBag();
            gui.printOnGameSide("Which ingredient would you like to pick?");
            try{
                int ingredientNum = Integer.parseInt(gui.getInput());
				pair<potionHerbs, Integer> ret = bagContents.get(ingredientNum - 1); 
				bagContents.remove(ingredientNum);
                return ret.first; 
            }
            catch(NumberFormatException | IndexOutOfBoundsException e){
                gui.printOnGameSide("Select a valid Number!");
            }

        }
        else{
            gui.printOnGameSide("Your herb bag is empty!");
        }
        return null;
    }

    public void listHerbsInBag(){
        for(int i = 0; i < bagContents.size(); i++){
            gui.printOnGameSide((i+1) + ": " + bagContents.get(i).first.toString() +  " x" + bagContents.get(i).second );
        }
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof potionsBag){
            return this.getItemName().equals(((potionsBag) obj).getItemName());
        }
        return false;
    }

    public enum potionHerbs {Peppermint, Sage, ToadsFoot, Mushroom, Honeysuckle, Dogwood, EntBranch, LemonGrass, Ivy}

}
