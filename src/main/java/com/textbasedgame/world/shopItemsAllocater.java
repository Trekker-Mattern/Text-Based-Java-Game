package com.textbasedgame.world;

import java.util.ArrayList;
import java.util.Arrays;

import com.textbasedgame.items.*;
import com.textbasedgame.items.genericItems.*;
import com.textbasedgame.items.headArmorItems.*;
import com.textbasedgame.items.chestArmorItems.*;
import com.textbasedgame.items.consumableItems.*;
import com.textbasedgame.items.footArmorItems.*;
import com.textbasedgame.items.legsArmorItems.*;
import com.textbasedgame.items.handItems.*;


public class shopItemsAllocater {

    private static String changeIntToMappedString(int i){
        if(i < 2){
            return "upToOuterGates";
        }
        else if(i < 4){
            return "upToCaves";
        }
        else{
            return "default";
        }
    }

    public static ArrayList<Class<? extends consumables>> getFoodItemArray(int i){
        String gameStage = changeIntToMappedString(i);
        
        

        
        
        
        switch (gameStage){
            case "upToOuterGates":{
                return new ArrayList<>(
                    Arrays.asList(
                        beefsteak.class,
                        bread.class,
                        fish.class
                    )
                );
            }
            case "upToCaves":{
                return new ArrayList<>(
                    Arrays.asList(
                        beefsteak.class,
                        bread.class,
                        fish.class,
                        threeCourseMeal.class
                    )
                );
            }
            default:{
                return new ArrayList<>(
                    Arrays.asList(
                        beefsteak.class,
                        bread.class,
                        fish.class,
                        threeCourseMeal.class
                    )
                );
            }
        }

    }

    public static ArrayList<Class<? extends equipables>> getEquipablesShopArray(int i){
        String gameStage = changeIntToMappedString(i);

        
        
        
        switch (gameStage){
            case "upToOuterGates":{
                return new ArrayList<>(
                    Arrays.asList(
                        chestplate.class,
                        clogs.class,
                        sword.class,
                        helmet.class,
                        wand.class,
                        leatherPants.class,
                        dagger.class,
                        club.class
                    )
                );
            }
            case "upToCaves":{
                return new ArrayList<>(
                    Arrays.asList(
                        chestplate.class,
                        clogs.class,
                        sword.class,
                        helmet.class,
                        leatherPants.class,
                        dagger.class,
                        club.class,
                        wand.class,
                        wizardHat.class,
                        wizardCloak.class,
                        wizardShoes.class,
                        spartanBreastplate.class,
                        spartanBoots.class,
                        spartanHelmet.class,
                        spartanSkirt.class
                    )
                );
            }
            default:{
                return new ArrayList<>(
                    Arrays.asList(
                        chestplate.class,
                        clogs.class,
                        sword.class,
                        helmet.class,
                        leatherPants.class,
                        dagger.class,
                        club.class,
                        wand.class,
                        wizardHat.class,
                        wizardCloak.class,
                        wizardShoes.class,
                        spartanBreastplate.class,
                        spartanBoots.class,
                        spartanHelmet.class,
                        spartanSkirt.class
                    )
                );
            }
        }
    }


    public static ArrayList<Class<? extends consumables>> getConsumableItemArray(int i){
        String gameStage = changeIntToMappedString(i);

        switch (gameStage){
            case "upToOuterGates":{
                return new ArrayList<>(
                    Arrays.asList(
                        beefsteak.class,
                        throwingKnife.class,
                        agilityPot.class
                    )
                );
            }
            case "upToCaves":{
                return new ArrayList<>(
                    Arrays.asList(
                        threeCourseMeal.class,
                        throwingKnife.class,
                        intelligencePot.class,
                        strengthPot.class,
                        agilityPot.class
                    )
                );
            }
            default:{
                return new ArrayList<>(
                    Arrays.asList(
                        beefsteak.class,
                        bread.class,
                        fish.class,
                        threeCourseMeal.class,
                        throwingKnife.class,
                        intelligencePot.class,
                        strengthPot.class,
                        agilityPot.class,
                        genericPotion.class
                    )
                );
            }
        }

    }

}
