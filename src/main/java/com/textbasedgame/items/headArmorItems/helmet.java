package com.textbasedgame.items.headArmorItems;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;


import com.textbasedgame.items.genericItems.headArmor;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.pair;


public class helmet extends headArmor{
    private static final Set<String> tags = new HashSet<>(Arrays.asList("Heavy Armor", "Helmet", "Head Armor", "Metal"));
    
    public helmet(){

        armorAdd = (int)(quality * 1.3);
        setPrice(20);
        setName("Helmet");
        setDescription("A metal face and head covering. Provides quality protection but you dont feel any special power or energy when you place the helmet on your head. Good enough you say.");
        
    }
    public helmet(int qual){
        setQuality(qual);
        armorAdd = (int)(quality * 1.3);
        setPrice(20);
        setName("Helmet");
        setDescription("A metal face and head covering. Provides quality protection but you dont feel any special power or energy when you place the helmet on your head. Good enough you say.");

    }
    @Override
    protected Set<String> getTagsSet() {
        return tags;
    }
    @Override
    public pair<buffTypes, Integer> getSetBuff() {
        return new pair<>(null, null);
    }
    @Override
    public void printInfo() {}
}