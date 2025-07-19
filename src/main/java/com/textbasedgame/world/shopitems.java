package com.textbasedgame.world;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.*;
import com.textbasedgame.items.chestArmorItems.*;
import com.textbasedgame.items.consumableItems.*;
import com.textbasedgame.items.footArmorItems.*;
import com.textbasedgame.items.handItems.*;
import com.textbasedgame.items.headArmorItems.*;
import com.textbasedgame.items.legsArmorItems.*;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.TrekkerMath;

public abstract class shopitems {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // precondition: all shops are 4 items
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static HashSet<Class<? extends item>> allItemsList = new HashSet<>();

    public static ArrayList<Class<? extends consumables>> consumableShopItems = new ArrayList<>();
    public static ArrayList<Class<? extends equipables>> equipableShopItems = new ArrayList<>();
    
    public static int[] itemPrice = {5, 1, 25, 30, 10, 3, 30};
    
    private static item[] itemsInShop = new item[4];

    public static void createShopItemsArr(){
        consumableShopItems.add(fish.class);
        equipableShopItems.add(sword.class);
        equipableShopItems.add(dagger.class);
        equipableShopItems.add(chestplate.class);
        consumableShopItems.add(threeCourseMeal.class);
        equipableShopItems.add(wand.class);
        equipableShopItems.add(helmet.class);
        consumableShopItems.add(bread.class);
        equipableShopItems.add(club.class);
        equipableShopItems.add(leatherPants.class);
        equipableShopItems.add(clogs.class);
        consumableShopItems.add(intelligencePot.class);
        consumableShopItems.add(strengthPot.class);
        consumableShopItems.add(agilityPot.class);
        equipableShopItems.add(spartanHelmet.class);
        equipableShopItems.add(spartanBoots.class);
        equipableShopItems.add(spartanSkirt.class);
        equipableShopItems.add(spartanBreastplate.class);
        consumableShopItems.add(throwingKnife.class);
        equipableShopItems.add( wizardShoes.class);
        equipableShopItems.add(wizardHat.class);
        equipableShopItems.add( wizardCloak.class);


        allItemsList.add(fish.class);
        allItemsList.add(sword.class);
        allItemsList.add(dagger.class);
        allItemsList.add(chestplate.class);
        allItemsList.add(threeCourseMeal.class);
        allItemsList.add(wand.class);
        allItemsList.add(helmet.class);
        allItemsList.add(bread.class);
        allItemsList.add(club.class);
        allItemsList.add(leatherPants.class);
        allItemsList.add(clogs.class);
        allItemsList.add(agilityPot.class);
        allItemsList.add(strengthPot.class);
        allItemsList.add(intelligencePot.class);
        allItemsList.add(hydraHead.class);
        allItemsList.add(spartanHelmet.class);
        allItemsList.add(spartanBoots.class);
        allItemsList.add(spartanSkirt.class);
        allItemsList.add(spartanBreastplate.class);
        allItemsList.add(throwingKnife.class);
        allItemsList.add(wizardShoes.class);
        allItemsList.add(wizardHat.class);
        allItemsList.add(wizardCloak.class);
        allItemsList.add(genericPotion.class);

    }

    public static void printShopItems(){
        
        int index = 1;
        for(item e:itemsInShop){
            
            gui.printOnGameSide(index + ": " + e.getItemName() + "   | costs " + e.getPrice() + " gold");
            index++;
        }

    }

    public static void createShop(){
        item[] randomItems = new item[4];

        for(int i = 0; i < randomItems.length-2; i++){
            Class<? extends consumables> itemType = consumableShopItems.get((int)(Math.random() * consumableShopItems.size()));
            try {
                Constructor<? extends consumables> ctor = itemType.getDeclaredConstructor();
                item a = ctor.newInstance();
                randomItems[i] = a;
                
                
            } catch (Exception e) {
                // Handle the case where the default constructor is not found
                e.printStackTrace();
            }
        }
        for(int i =2; i < randomItems.length; i++){
            Class<? extends equipables> itemType = equipableShopItems.get((int)(Math.random() * equipableShopItems.size()));
                try {
                    Constructor<? extends equipables> ctor = itemType.getDeclaredConstructor();
                    item a = ctor.newInstance();
                    randomItems[i] = a;


                } catch (Exception e) {
                    // Handle the case where the default constructor is not found
                    e.printStackTrace();
                }
            }
        itemsInShop = randomItems;
    }

 

    public static void printShop(){
        gui.printOnGameSide("You have " + player.BankBalance + " shmeckles!");

        gui.printOnGameSide("Heres whats in the shop!");
        printShopItems();

    }
    public static void buyItem(int shopItemNum){

        item toAdd = itemsInShop[shopItemNum - 1];
    
        player.addItemToPlayer(toAdd);
        

        player.BankBalance -= toAdd.getPrice();

        createShop();
    }
    public static void buyItem(item i){
    
        player.addItemToPlayer(i);
        

        player.BankBalance -= i.getPrice();

        createShop();
    }
    
    public static item[] getShopArray(){
        return itemsInShop;
    }

    public static item getRandomItem(){
        int h = TrekkerMath.randomInt(0,1);
        if(h == 0){
            Class<? extends equipables> itemType = equipableShopItems.get((int)(Math.random() * equipableShopItems.size()));
                try {
                    Constructor<? extends equipables> ctor = itemType.getDeclaredConstructor();
                    item a = ctor.newInstance();
                    return a;


                } catch (Exception e) {
                    // Handle the case where the default constructor is not found
                    e.printStackTrace();
                } 
        }
        else{
            Class<? extends consumables> itemType = consumableShopItems.get((int)(Math.random() * consumableShopItems.size()));
                try {
                    Constructor<? extends consumables> ctor = itemType.getDeclaredConstructor();
                    item a = ctor.newInstance();
                    return a;


                } catch (Exception e) {
                    // Handle the case where the default constructor is not found
                    e.printStackTrace();
                } 
        }
        return null;
    }


}
