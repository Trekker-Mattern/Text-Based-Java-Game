package com.textbasedgame.world;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.equipables;
import com.textbasedgame.items.item;
import com.textbasedgame.monsters.monsterArrayList;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.GameProgressWrapper;
import com.textbasedgame.util.itemInfoPrinter;
import com.textbasedgame.util.response;
import com.textbasedgame.util.saveFiles;
import com.textbasedgame.world.rooms.Room;

// Singleton class: accessed statically throughout game
public abstract class world { 

    public static int AREANUM = 0;
    private static final String areas[] = {"Village", "Outer Gates", "Grassland", "Graveyard", "Tunnels!", "Cave", "A second Cave", "A THIRD CAVE??", "Hell", "Why is there two hells?", "Are you actually still playing???", "Bored Yet?", "How bout now?", "Get some sleep please", "Touch Grass?"}; 
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
            villageMenu();
        }
        else{
            openDungeon();
        }
    }

    private static void villageMenu(){
        gui.newlOnGameSide();
        gui.printOnGameSide("You have some options of what to do:");
        gui.newlOnGameSide();
        gui.printOnGameSide("Shop");
        if(GameProgressWrapper.gameProgress.potionBagUnlocked){gui.printOnGameSide("Potions Crafting");}
        gui.printOnGameSide("Dungeon");
        gui.printOnGameSide("Items");
        gui.printOnGameSide("Quit");
        gui.printOnGameSide("Save");
        gui.newlOnGameSide();
        String Ans = gui.getInput();
        Ans = Ans.toLowerCase();
        gui.pushOldText();
        if (response.quit(Ans)){gui.quit();}
        //OPEN SHOP!!!
        if(response.Shop(Ans)){
            openShop();
        }
        else if(response.Dungeon(Ans)){
            openDungeon();
        }
        else if(response.keyItems(Ans)) {
            player.printKeyItems();
        }
        else if(response.Items(Ans)){
            itemMenu();
        }
        else if(response.Save(Ans)){
            //// SAVE
            saveFiles.save();
        }
        else if(Ans.equals("info")){
            itemInfoPrinter.infoMenu();
        }
        else if(Ans.contains("potions")){
            potionsMenu();
        }
    }

    private static void potionsMenu(){
        roomFactory.getCauldronRoom().openRoom();
    }
    
    

    private static void openShop(){
        gui.setImage(0);
        shopitems.printShop();
        gui.printOnGameSide("Would you like to purchase one of these items?");
        gui.printOnGameSide("You can also sell items by typing sell!");
        String userInput = gui.getInput();

        gui.pushOldText();
        
        try{
            int UserResp = Integer.parseInt(userInput);
            //int numUserIsBuying = input.nextInt();
            item[] shop = shopitems.getShopArray();
            
            if(player.BankBalance >= shopitems.getShopArray()[UserResp-1].getPrice()){
                shopitems.buyItem(UserResp);
                gui.newlOnGameSide();
                gui.printOnGameSide("You successfully bought " + shop[UserResp - 1] + " for " + shop[UserResp - 1].getPrice() + " shmeckles.");
            }
            else{
                gui.printOnGameSide("You dont have enough money to buy that!");
                gui.printOnGameSide("You only have " + player.BankBalance + " shmeckles.");
            }
        }
        catch(NumberFormatException | IndexOutOfBoundsException ex){
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
                }
                else{
                    gui.printOnGameSide("You dont have enough money to buy that!  You only have " + player.BankBalance + " shmeckles.");
                }
            }
            catch(NumberFormatException e){
                for(item i : shopitems.getShopArray()){
                    if(i.getItemName().toLowerCase().equals(userInput.toLowerCase())){
                        if(player.BankBalance >= i.getPrice()){
                            shopitems.buyItem(i);
                            gui.printOnGameSide("You successfully bought " + i + " for " + i.getPrice() + " shmeckles.");
                            break;
                        }
                        else{
                            gui.printOnGameSide("You dont have enough money to buy that!  You only have " + player.BankBalance + " shmeckles.");
                        }
                    }
                }
            }   
            catch(IndexOutOfBoundsException e){
                gui.printOnGameSide("There isnt that many items in the shop");
            }         
        }

        //Selling menu
        if(response.respondSell(userInput)){
            player.printPlayerItems();
            gui.printOnGameSide("Which item would you like to sell?");
            try{
                int itemSell = Integer.parseInt(gui.getInput());
                itemSell--; //adjust for 0 index
                if(player.inventory.get(itemSell) instanceof equipables && ((equipables)player.inventory.get(itemSell)).isEquipped()){
                    player.inventory.get(itemSell).Use();
                }
                int sellPrice = (int)(player.inventory.get(itemSell).getPrice() * .75);
                player.BankBalance += sellPrice;
                gui.printOnGameSide("You sell " + player.inventory.get(itemSell).getItemName() + " for " + sellPrice + " shmeckles");
                player.inventory.remove(player.inventory.get(itemSell));
                return;
            }
            catch(IndexOutOfBoundsException e){
                gui.printOnGameSide("You dont have that many items you goof!");
                return;
            }
            catch(NumberFormatException e){return;}
            
        }

        //////////////////////////////////////////////////////////////
        /// Check to see if user put name of item and buy accordingly
        //////////////////////////////////////////////////////////////
        for(item i : shopitems.getShopArray()){
            if(i.getItemName().toLowerCase().equals(userInput.toLowerCase())){
                if(player.BankBalance >= i.getPrice()){
                    shopitems.buyItem(i);
                    gui.printOnGameSide("You successfully bought " + i + " for " + i.getPrice() + " shmeckles.");
                    break;
                }
                else{
                    gui.printOnGameSide("You dont have enough money to buy that!  You only have " + player.BankBalance + " shmeckles.");
                }
            }
        }
        //no dont buy shit recurse back to display
        if(response.respondNo(userInput)){gui.pushOldText();}
    }

    private static void openDungeon(){
        if(stageNum % 5 == 0 && AREANUM < areas.length - 1){
            changeArea();
        }

        //create monster
        Room room = roomFactory.getRandomRoom();
        room.openRoom();

        stageNum++;
        player.update();
    }

    



    public static boolean itemMenu(){
        gui.pushOldText();
        player.printPlayerItems();
        gui.printOnGameSide("------KEY ITEMS-------");
        player.printKeyItems();
        gui.printOnGameSide("Would you like to use an item?");
        gui.printOnGameSide("You can also type info to get info on a specific item");
        String h = gui.getInput();
        gui.pushOldText();
        try{
            int number = Integer.parseInt(h);
            try {
                player.inventory.get(number-1).Use();
                return true;
            } 
            catch (IndexOutOfBoundsException e) {
                gui.printOnGameSide("You dont have that many items you goof!");
                return false;
            }
        }
        catch(NumberFormatException ex){
            //do nothing
        }
        if(response.respondYes(h)){
            gui.printOnGameSide("What is the number of the item you would like to use");
            int temp = Integer.parseInt(gui.getInput());
            gui.pushOldText();
            try {
                player.inventory.get(temp-1).Use();
                return true;
            } catch (IndexOutOfBoundsException e) {
                gui.printOnGameSide("You dont have that many items you goof!");
                return false;
            }
        }
        else if(h.toLowerCase().contains("info") || h.toLowerCase().contains("help")){
            player.printPlayerItems();
            gui.printOnGameSide("Which item would you like more information on?");
            try{

                int itemNum = Integer.parseInt(gui.getInput()) - 1;

                itemInfoPrinter.printItemInfo(player.inventory.get(itemNum));
            }
            catch(NumberFormatException| IndexOutOfBoundsException e){

            }
        }
        return false;
        
    }
    
    public static void run(){
        stageNum -= stageNum % 5;
        stageNum--; // because the stage num gets incremented at the end of dungeon
        gui.printOnGameSide("You flee and return back to the last safe area that you remember");
    }

    private static void changeArea(){
        if(stageNum / 5 < areas.length - 1){
            AREANUM = stageNum / 5;
        }
        monsterArrayList.updateMonsterArrayListOnAreaUpdate();
        gui.printOnGameSide("You notice the scenery changing. You step down into " + getArea());
        gui.updatePlayerSide();
        gui.pushOldText();
    }

    public static void updateArea(){
        if(stageNum / 5 < areas.length - 1){
            AREANUM = stageNum / 5;
        }
        monsterArrayList.updateMonsterArrayListOnAreaUpdate();
    }

}
