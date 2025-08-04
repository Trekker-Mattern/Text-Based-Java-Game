package com.textbasedgame.util;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.*;
import com.textbasedgame.items.genericItems.holdables;
import com.textbasedgame.playerFiles.player.buffTypes;

import java.util.ArrayList;
import java.util.Set;

public class itemInfoPrinter {
    public static void printItemInfo(item i){
        gui.pushOldText();
        if(i instanceof consumables){
            printConsumableInfo((consumables)i);
        }
        else if(i instanceof equipables){
            printEquipablesInfo((equipables) i);
        }

        gui.printOnGameSide("Enter any input to continue");
        gui.getInput();
    }
    
    private static void printConsumableInfo(consumables i){
        String name = i.getItemName();
        int price = i.getPrice(); 

        gui.printOnGameSide("-- " + name + " --");
        gui.printOnGameSide("Price: " + price);
        i.printInfo();
    }

    private static void printEquipablesInfo(equipables i){
        String name = i.getItemName();
        int price = i.getPrice(); 
        int qualityInt = i.getQualityInt();
        String qualityString = i.getQuality();

        String setName = i.getEquipmentSetName();
        Set<Class<? extends equipables>> otherItemsInSet = i.getSetItems();

        Set<String> tags = i.getItemTags();

        gui.printOnGameSide("--- " + name + " ---");

        if(setName != null){
            gui.newlOnGameSide();
            gui.printOnGameSide("Set: " + setName);
            gui.printOnGameSide("-- Set Items --");
            for(Class<? extends equipables> c : otherItemsInSet){
                try{
                    gui.printOnGameSide(c.getConstructor().newInstance().getItemName());
                }
                catch(Exception e){
                    gui.printOnGameSide(c.getSimpleName());
                }
            }
            gui.newlOnGameSide();
        }


        gui.printOnGameSide("Price: " + price);
        gui.printOnGameSide("Quality: " + qualityInt + " - " + qualityString);
        
        
        if(i instanceof holdables){
            gui.printOnGameSide("Damage Type: " + ((holdables)i).getDMGType());
            gui.printOnGameSide("Damage: " + ((holdables)i).getItemDamage());
        }
        else{
            gui.printOnGameSide("Armor Value: " + i.getArmorVal());
        }

        if(i.isEquipped()){gui.printOnGameSide("-Equipped-");}
        
        gui.newlOnGameSide();

        if(!i.getItemBuffs().isEmpty()){
            gui.printOnGameSide("-- Buffs --");
            ArrayList<pair<buffTypes, Integer>> buffs = i.getItemBuffs();
            for(pair<buffTypes, Integer> buff : buffs){
                gui.printOnGameSide(buff.first.toString() + " buff of strength " + buff.second);
            }
        }

        gui.newlOnGameSide();
        gui.printOnGameSide("--- Tags ---");
        String tagsString = "";
        for(String tag : tags){
            tagsString += tag + ", ";
        }
        gui.printOnGameSide(tagsString);
        i.printInfo();
    }
}

