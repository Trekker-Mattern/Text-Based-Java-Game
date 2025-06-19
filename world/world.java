package world;

import GUI.gui;
import items.*;
import items.genericItems.attackingConsumable;
import monsters.*;
import playerFiles.*;
import util.*;


public abstract class world { 

    public static int AREANUM = 0;
    private static final String areas[] = {"Village", "Grassland", "Cave", "Hell", "A second Cave?", "A THIRD CAVE??", "Why is there two hells?", "Are you actually still playing???", "Bored Yet?", "How bout now?"}; 
    public static int stageNum = 0;

    public world(){}
    public static String getArea(){
        return areas[AREANUM];
    }
    public static int getStage(){
        return stageNum;
    }

    public static void menu(){
        if(stageNum % 5 == 0){
            gui.newlOnGameSide();
            gui.printOnGameSide("You have some options of what to do:");
            gui.newlOnGameSide();
            gui.printOnGameSide("Shop");
            gui.printOnGameSide("Dungeon");
            gui.printOnGameSide("Items");
            gui.printOnGameSide("Quit");
            gui.printOnGameSide("Save");
            gui.newlOnGameSide();

            String Ans = gui.getInput();
            if (response.quit(Ans)){gui.quit();}

            //OPEN SHOP!!!
            if(response.Shop(Ans)){
                openShop();
            }
            if(response.Dungeon(Ans)){
                openDungeon();
            }
            if(response.Items(Ans)){
                itemMenu();
            }
            if(response.Save(Ans)){
                //// SAVE
                saveFiles.save();
            }
        }
        else{
            openDungeon();
        }
    }

    
    private static void openShop(){
        shopitems.printShop();
        gui.printOnGameSide("Would you like to purchase one of these items?");
        String userInput = gui.getInput();
        boolean itemPurchased = false;
                
        try{
            int UserResp = Integer.parseInt(userInput);
            //int numUserIsBuying = input.nextInt();
            item[] shop = shopitems.getShopArray();
            
            if(player.BankBalance >= shopitems.getShopArray()[UserResp-1].getPrice()){
                shopitems.buyItem(UserResp);
                gui.newlOnGameSide();
                gui.printOnGameSide("You successfully bought " + shop[UserResp - 1] + " for " + shop[UserResp - 1].getPrice() + " shmeckles.");
                itemPurchased = true;
            }
            else{
                gui.printOnGameSide("You dont have enough money to buy that!");
                gui.printOnGameSide("You only have " + player.BankBalance + " shmeckles.");
            }
        }
        catch(NumberFormatException ex){
                    //do nothing ig
        }
        //yes buy shit
        if(response.respondYes(userInput)){
            item[] shop = shopitems.getShopArray();
            gui.printOnGameSide("What Item Would you like to buy?");
            gui.printOnGameSide("Number ___");
            try{
                int numUserIsBuying = Integer.parseInt(gui.getInput());
                

                if(player.BankBalance >= shop[numUserIsBuying - 1].getPrice()){
                    shopitems.buyItem(numUserIsBuying);
                    gui.newlOnGameSide();
                    gui.printOnGameSide("You successfully bought " + shop[numUserIsBuying - 1] + " for " + shop[numUserIsBuying - 1].getPrice() + " shmeckles.");
                    itemPurchased = true;
                }
                else{
                    gui.printOnGameSide("You dont have enough money to buy that!  You only have " + player.BankBalance + " shmeckles.");
                }
            }
            catch(NumberFormatException e){
                for(item i : shopitems.getShopArray()){
                    if(i.getItemName().toLowerCase() == userInput.toLowerCase()){
                        if(player.BankBalance >= i.getPrice()){
                            shopitems.buyItem(i);
                            gui.printOnGameSide("You successfully bought " + i + " for " + i.getPrice() + " shmeckles.");
                            itemPurchased = true;
                            break;
                        }
                        else{
                            gui.printOnGameSide("You dont have enough money to buy that!  You only have " + player.BankBalance + " shmeckles.");
                        }
                    }
                }
            }            
        }

        //Selling menu
        if(response.respondSell(userInput)){
            player.printPlayerItems();
            gui.printOnGameSide("Which item would you like to sell?");
            int itemSell = Integer.parseInt(gui.getInput());
            itemSell--; //adjust for 0 index
            try{
                if(player.inventory.get(itemSell) instanceof equipables && ((equipables)player.inventory.get(itemSell)).isEquipped()){
                    player.inventory.get(itemSell).Use();
                }
                int sellPrice = (int)(player.inventory.get(itemSell).getPrice() * .75);
                player.BankBalance += sellPrice;
                gui.printOnGameSide("You sell " + player.inventory.get(itemSell).getItemName() + " for " + sellPrice + " shmeckles");
                player.inventory.remove(player.inventory.get(itemSell));
            }
            catch(IndexOutOfBoundsException e){
                gui.printOnGameSide("You dont have that many items you goof!");
            }
            
        }

        //////////////////////////////////////////////////////////////
        /// Check to see if user put name of item and buy accordingly
        //////////////////////////////////////////////////////////////
        for(item i : shopitems.getShopArray()){
            if(i.getItemName().toLowerCase() == userInput.toLowerCase()){
                if(player.BankBalance >= i.getPrice()){
                    shopitems.buyItem(i);
                    gui.printOnGameSide("You successfully bought " + i + " for " + i.getPrice() + " shmeckles.");
                    itemPurchased = true;
                    break;
                }
                else{
                    gui.printOnGameSide("You dont have enough money to buy that!  You only have " + player.BankBalance + " shmeckles.");
                }
            }
        }
        //no dont buy shit recurse back to display
        if(response.respondNo(userInput)){}
        if(itemPurchased){
            shopitems.createShop();
        }

    }

    private static void openDungeon(){
        if(stageNum % 5 == 0 && AREANUM < areas.length - 1){
            changeArea();
        }

        //create monster
        if(stageNum % 10 == 9){
            boss b = monsterCreator.createBoss();
            b.printMonster();
            monsterMenu(b);
        }
        else{
            int randNum = TrekkerMath.randomInt(2, 0);
            if(randNum == 2){
                roomFactory.getRandomRoom().openRoom();
            }
            else{
                monster m = monsterCreator.createMonster();
                m.printMonster();
                monsterMenu(m);
            }
        }
        player.update();
    }

    public static void monsterMenu(monster m){
        while(m.getHealth() > 0){
            gui.newlOnGameSide();
            gui.printOnGameSide("What would you like to do?");
            gui.printOnGameSide("Fight, Use an item, or Quit");
            String h = gui.getInput(); 
            if(response.quit(h)){gui.quit();}

            if(response.respondFight(h)){
                player.fightMonster(m);
                if(m.getHealth() <= 0){
                    gui.newlOnGameSide();
                    gui.printOnGameSide("You defeated " + m.getName() + "!");
                    int coinGain = (int)((player.luck * m.getLevel()) + 4);
                    int xpGain = (int)((player.luck * m.getLevel())*4);
                    gui.printOnGameSide("You obtained " + coinGain + " shmeckles and " + xpGain + " XP!");
                    player.BankBalance += coinGain;
                    player.gainXP(xpGain);
                    m.onMonsterDeath();
                    stageNum++;
                }
                else{
                    m.printMonster();
                }
            }
            //////////////////////////////////////////
            //Use an item during a fight
            /////////////////////////////////////////
            else if(response.Items(h)){

                player.printPlayerItems();
                gui.printOnGameSide("Which item would you like to use?");
                String newInput = gui.getInput();
                try{
                    int number = Integer.parseInt(newInput);
                    try {
                        if(player.inventory.get(number-1) instanceof attackingConsumable){
                            gui.printOnGameSide(player.inventory.get(number-1).getAttackString());
                            gui.printOnGameSide("You deal " + Integer.toString(((attackingConsumable)player.inventory.get(number-1)).getDamageInt()) + " to " + m.getName());
                            m.subtractHealth(((attackingConsumable)player.inventory.get(number-1)).getDamageInt());

                            if(m.getHealth() <= 0){
                                gui.newlOnGameSide();
                                gui.printOnGameSide("You defeated " + m.getName() + "!");
                                int coinGain = (int)((player.luck * m.getLevel()) + 4);
                                int xpGain = (int)((player.luck * m.getLevel())*4);
                                gui.printOnGameSide("You obtained " + coinGain + " shmeckles and " + xpGain + " XP!");
                                player.BankBalance += coinGain;
                                player.gainXP(xpGain);
                                m.onMonsterDeath();
                                stageNum++;
                            }
                            else{
                                m.printMonster();
                                player.damageTaken(m);
                            }

                        }
                        player.inventory.get(number-1).Use();
                    } 
                    catch (IndexOutOfBoundsException e) {
                        gui.printOnGameSide("You dont have that many items you goof!");
                    }
                }
                catch(NumberFormatException ex){
                    //do nothing
                }
                if(response.respondYes(newInput)){
                    gui.printOnGameSide("What is the number of the item you would like to use");
                    int temp = Integer.parseInt(gui.getInput());
                    try {
                        if(player.inventory.get(temp-1) instanceof attackingConsumable){
                            gui.printOnGameSide(player.inventory.get(temp-1).getAttackString());
                            gui.printOnGameSide("You deal " + Integer.toString(((attackingConsumable)player.inventory.get(temp-1)).getDamageInt()) + " to " + m.getName());
                            m.subtractHealth(((attackingConsumable)player.inventory.get(temp-1)).getDamageInt());


                            if(m.getHealth() <= 0){
                                gui.newlOnGameSide();
                                gui.printOnGameSide("You defeated " + m.getName() + "!");
                                int coinGain = (int)((player.luck * m.getLevel()) + 4);
                                int xpGain = (int)((player.luck * m.getLevel())*4);
                                gui.printOnGameSide("You obtained " + coinGain + " shmeckles and " + xpGain + " XP!");
                                player.BankBalance += coinGain;
                                player.gainXP(xpGain);
                                m.onMonsterDeath();
                                stageNum++;
                            }
                            else{
                                m.printMonster();
                                player.damageTaken(m);
                            }

                        }
                        player.inventory.get(temp-1).Use();
                    } catch (IndexOutOfBoundsException e) {
                        gui.printOnGameSide("You dont have that many items you goof!");
                    }
                }
            }
        }
    }



    public static void itemMenu(){
        player.printPlayerItems();
        gui.printOnGameSide("Would you like to use an item?");
        String h = gui.getInput();
        try{
            int number = Integer.parseInt(h);
            try {
                player.inventory.get(number-1).Use();
            } 
            catch (IndexOutOfBoundsException e) {
                gui.printOnGameSide("You dont have that many items you goof!");
            }
        }
        catch(NumberFormatException ex){
            //do nothing
        }
        if(response.respondYes(h)){
            gui.printOnGameSide("What is the number of the item you would like to use");
            int temp = Integer.parseInt(gui.getInput());
            try {
                player.inventory.get(temp-1).Use();
            } catch (IndexOutOfBoundsException e) {
                gui.printOnGameSide("You dont have that many items you goof!");
            }
        }
    }

    private static void changeArea(){
        AREANUM++;
        monsterArrayList.updateMonsterArrayListOnAreaUpdate();
        gui.printOnGameSide("You notice the scenery changing. You step down into " + getArea());
    }

}
