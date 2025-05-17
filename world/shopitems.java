package world;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import items.*;
import playerFiles.player;
import util.TrekkerMath;

public abstract class shopitems {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // precondition: all shops are 4 items
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static ArrayList<Class<? extends item>> allItemsList = new ArrayList<Class<? extends item>>();

    public static ArrayList<Class<? extends consumables>> consumableShopItems = new ArrayList<Class<? extends consumables>>();
    public static ArrayList<Class<? extends equipables>> equipableShopItems = new ArrayList<Class<? extends equipables>>();
    
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

        allItemsList.add(fish.class);
        allItemsList.add(sword.class);
        allItemsList.add(dagger.class);
        allItemsList.add(chestplate.class);
        allItemsList.add(threeCourseMeal.class);
        allItemsList.add(wand.class);
        allItemsList.add(helmet.class);
        allItemsList.add(bread.class);
        allItemsList.add(club.class);
    }

    public static void printShopItems(){
        itemsInShop = createShop();
        int index = 1;
        for(item e:itemsInShop){
            
            System.out.print(index + ": " + e.getItemName());
            System.out.println("   | costs " + e.getPrice() + " gold");
            index++;
        }

    }

    public static item[] createShop(){
        item[] randomItems = new item[4];





        for(int i = 0; i < randomItems.length-2; i++){
            Class<? extends consumables> itemType = consumableShopItems.get((int)(Math.random() * consumableShopItems.size()));
            try {
                Constructor<? extends consumables> ctor = itemType.getDeclaredConstructor();
                item a = ctor.newInstance();
                randomItems[i] = a;
                
                
            } catch (NoSuchMethodException e) {
                // Handle the case where the default constructor is not found
                e.printStackTrace();
            } catch (InstantiationException e) {
                // Handle the instantiation exception
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // Handle illegal access exception
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // Handle invocation target exception
                e.printStackTrace();
            }

        }
        for(int i =2; i < randomItems.length; i++){
            Class<? extends equipables> itemType = equipableShopItems.get((int)(Math.random() * equipableShopItems.size()));
                try {
                    Constructor<? extends equipables> ctor = itemType.getDeclaredConstructor();
                    item a = ctor.newInstance();
                    randomItems[i] = a;


                } catch (NoSuchMethodException e) {
                    // Handle the case where the default constructor is not found
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    // Handle the instantiation exception
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // Handle illegal access exception
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // Handle invocation target exception
                    e.printStackTrace();
                }
            }
        return randomItems;
    }

 

    public static void printShop(){
        System.out.println("You have " + player.BankBalance + " shmeckles!");

        System.out.println("Heres whats in the shop!");
        printShopItems();

    }
    public static void buyItem(int shopItemNum){

        item toAdd = itemsInShop[shopItemNum - 1];
    
        player.addItemToPlayer(toAdd);
        

        player.BankBalance -= toAdd.getPrice();
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


                } catch (NoSuchMethodException e) {
                    // Handle the case where the default constructor is not found
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    // Handle the instantiation exception
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // Handle illegal access exception
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // Handle invocation target exception
                    e.printStackTrace();
                }
        }
        else{
            Class<? extends consumables> itemType = consumableShopItems.get((int)(Math.random() * consumableShopItems.size()));
                try {
                    Constructor<? extends consumables> ctor = itemType.getDeclaredConstructor();
                    item a = ctor.newInstance();
                    return a;


                } catch (NoSuchMethodException e) {
                    // Handle the case where the default constructor is not found
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    // Handle the instantiation exception
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // Handle illegal access exception
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // Handle invocation target exception
                    e.printStackTrace();
                }
        }
        return null;
    }
}
