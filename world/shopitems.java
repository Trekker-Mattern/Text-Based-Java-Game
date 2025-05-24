package world;
import GUI.gui;
import items.*;
import items.chestArmorItems.chestplate;
import items.consumableItems.bread;
import items.consumableItems.fish;
import items.consumableItems.threeCourseMeal;
import items.handItems.club;
import items.handItems.dagger;
import items.handItems.sword;
import items.handItems.wand;
import items.headArmorItems.helmet;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import playerFiles.player;
import util.TrekkerMath;

public abstract class shopitems {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // precondition: all shops are 4 items
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static HashMap<String,Class<? extends item>> allItemsList = new HashMap<>();

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

        allItemsList.put("Fish",fish.class);
        allItemsList.put("Sword", sword.class);
        allItemsList.put("Dagger",dagger.class);
        allItemsList.put("Chestplate",chestplate.class);
        allItemsList.put("Three-Course-Meal",threeCourseMeal.class);
        allItemsList.put("Wand", wand.class);
        allItemsList.put("Helmet", helmet.class);
        allItemsList.put("Bread" ,bread.class);
        allItemsList.put("Club", club.class);
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
