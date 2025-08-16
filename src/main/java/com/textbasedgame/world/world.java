package com.textbasedgame.world;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.equipables;
import com.textbasedgame.items.genericItems.attackingConsumable;
import com.textbasedgame.items.item;
import com.textbasedgame.monsters.boss;
import com.textbasedgame.monsters.monster;
import com.textbasedgame.monsters.monsterArrayList;
import com.textbasedgame.monsters.monsterCreator;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.util.itemInfoPrinter;
import com.textbasedgame.util.response;
import com.textbasedgame.util.saveFiles;


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
                infoMenu();
            }
        }
        else{
            openDungeon();
        }
    }

    
    private static void infoMenu(){
        player.printPlayerItems();
        gui.printOnGameSide("Which item would you like more information on?");
        try{

            int itemNum = Integer.parseInt(gui.getInput()) - 1;

            itemInfoPrinter.printItemInfo(player.inventory.get(itemNum));
        }
        catch(NumberFormatException| IndexOutOfBoundsException e){

        }
    }

    private static void openShop(){
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
        if(stageNum % 10 == 9){
            boss b = monsterCreator.createBoss();
            monsterMenu(b);
        }
        else{
            int randNum = TrekkerMath.randomInt(2, 0);
            if(randNum == 2){
                roomFactory.getRandomRoom().openRoom();
            }
            else{
                gui.pushOldText();
                monster m = monsterCreator.createMonster();
                monsterMenu(m);
            }
        }
        stageNum++;
        player.update();
    }

    public static void monsterMenu(monster m){
        while(m.getHealth() > 0){
            gui.newlOnGameSide();
            m.printMonster();
            gui.printOnGameSide("What would you like to do?");
            gui.printOnGameSide("Fight, Use an item, Run, or Quit");
            String h = gui.getInput(); 
            gui.pushOldText();
            if(response.quit(h)){gui.quit();}

            if(response.respondRun(h)){run(); break;}
            if(h.equals("info")){infoMenu(); break;}

            if(response.respondFight(h)){
                player.fightMonster(m);
                if(player.dead){
                    gui.printOnGameSide("You collapse to the floor and feel your spirit float away");
                    gui.printOnGameSide("--Press enter or type yes to continue your journey--");
                    if(response.respondNo(gui.getInput())){
                        gui.quit();
                    }
                    player.onRespawn();
                    return;
                }
                if(m.getHealth() <= 0){
                    gui.newlOnGameSide();
                    gui.printOnGameSide("You defeated " + m.getName() + "!");
                    int coinGain = (int)((player.luck * m.getLevel()) + 4);
                    int xpGain = (int)((player.luck * m.getLevel())*4);
                    gui.printOnGameSide("You obtained " + coinGain + " shmeckles and " + xpGain + " XP!");
                    player.BankBalance += coinGain;
                    player.gainXP(xpGain);
                    m.onMonsterDeath();
                    
                }
            }
            //////////////////////////////////////////
            //Use an item during a fight
            /////////////////////////////////////////
            else if(response.Items(h)){
                itemMenu(m);
            }
        }
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
    private static void itemMenu(monster m){
        player.printPlayerItems();
        gui.printOnGameSide("Which item would you like to use?");
        String newInput = gui.getInput();
        try{
            int number = Integer.parseInt(newInput);
            try {
                if(player.inventory.get(number-1) instanceof attackingConsumable){
                    attackingConsumable atkCons = (attackingConsumable)player.inventory.get(number-1);
                    gui.printOnGameSide(atkCons.getAttackString());
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
                        
                    }
                    else{
                        if(player.getAgility() < m.getSpeed()){
                            m.printMonster();
                            gui.printOnGameSide("While you use your item the monster attacks");
                            player.damageTaken(m);
                        }
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
                    attackingConsumable atkCons = (attackingConsumable)player.inventory.get(temp-1);
                    gui.printOnGameSide(atkCons.getAttackString());
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
                        
                    }
                    else{
                        m.printMonster();
                        player.damageTaken(m);
                    }
                }
                player.inventory.get(temp-1).Use();
            } 
            catch (IndexOutOfBoundsException e) {
                gui.printOnGameSide("You dont have that many items you goof!");
            }
        }
        else if(newInput.toLowerCase().contains("info") ||newInput.toLowerCase().contains("help")){
            infoMenu();
        }
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
