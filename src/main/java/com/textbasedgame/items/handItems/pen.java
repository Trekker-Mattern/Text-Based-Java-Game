package com.textbasedgame.items.handItems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.genericItems.holdables;
import com.textbasedgame.monsters.monster;
import com.textbasedgame.util.TrekkerMath;

public class pen extends holdables{
    private int damage;
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Pen", "Strength", "Sharp", "Slicing", "Intelligence", "Stabbing"));
    private boolean isPen = false;
    //baseDmg = 2.5*quality;
    // penDmg = 1

    public pen(){
        damage = (int)(2.5*quality);
        setPrice(40);
        setName("Pen");
        setDMGType(damageTypes.INTELLIGENCE);

        if(TrekkerMath.randomInt(1,0) == 1){
            isPen = false;
        }
        else{
            damage = 1;
        }
        
    }
    public pen(int qual){
        setQuality(qual);
        damage = (int)(2.5*quality);
        setPrice(40);
        setName("Pen");
        setDMGType(damageTypes.INTELLIGENCE);

    }
    @Override
    public int getItemDamage(){
        return damage;
    }
    @Override
    public void attackEffects(monster m){
        
        if(TrekkerMath.randomInt(1,0) == 1){
            if(isPen){gui.printOnGameSide("The Pen transforms into a formidable sword!");}
            damage = (int)(2.5*quality);
            isPen = false;
        }
        else{
            if(!isPen){gui.printOnGameSide("Your sword rapidly shrinks back into its natural pen state...");}
            damage = 1;
        }

    }
    @Override
    public String getAttackString() {
        return "slashes with a transformed writing apparatus";
    }
    @Override
    protected Set<String> getTagsSet() {
        return tags;
    } 
    @Override
    public void printInfo() {}
}
