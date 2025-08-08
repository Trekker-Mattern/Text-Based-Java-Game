package com.textbasedgame.items.handItems;

import java.util.Set;

import com.textbasedgame.items.item;
import com.textbasedgame.items.genericItems.holdables;
import com.textbasedgame.GUI.gui;

import java.util.HashSet;
import java.util.Arrays;

import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.pair;
public class spartanSpear extends holdables{

    private int damage;
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Spartan Spear", "Strength", "Sharp", "Stabbing", "Impaling", "Long"));

    public spartanSpear(){
        setIsAttackingItem(true);
        damage =(int)(2.4*quality);
        setPrice(50);
        setName("Spartan Spear");
        setDMGType(damageTypes.STRENGTH);
        buffs.add(new pair<>(player.buffTypes.STRENGTH, 3));
        buffs.add(new pair<>(buffTypes.AGILITY, 1));
    }
    public spartanSpear(int qual){
        setQuality(qual);
        setIsAttackingItem(true);
        damage =(int)(2.4*quality);
        setPrice(50);
        setName("Spartan Spear");
        setDMGType(damageTypes.STRENGTH);
        buffs.add(new pair<>(player.buffTypes.STRENGTH, 3));
        buffs.add(new pair<>(buffTypes.AGILITY, 1));
    }
    @Override
    public int getItemDamage(){
        return damage;
    }
    @Override
    public String getAttackString() {
        return "impale";
    }
    @Override
    protected Set<String> getTagsSet() {
        return tags;
    }
    @Override
    protected void setClass(Class<? extends item> clazz){
        classofItem = spartanSpear.class.getName();
    } 
    @Override
    public void printInfo() {
        gui.printOnGameSide("This item is not part of the Spartan Set");
        gui.printOnGameSide("It does not need to be equipped in order to get the set bonus for");
        gui.printOnGameSide("the spartan set.");
    }
}

