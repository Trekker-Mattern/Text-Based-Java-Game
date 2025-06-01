package world;

import GUI.gui;
import items.*;
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
                menu();
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
            int numUserIsBuying = Integer.parseInt(gui.getInput());
            

            if(player.BankBalance >= shop[numUserIsBuying - 1].getPrice()){
                shopitems.buyItem(numUserIsBuying);
                gui.newlOnGameSide();
                gui.printOnGameSide("You successfully bought " + shop[numUserIsBuying - 1] + " for " + shop[numUserIsBuying - 1].getPrice() + " shmeckles.");
            }
            else{
                gui.printOnGameSide("You dont have enough money to buy that!  You only have " + player.BankBalance + " shmeckles.");
                menu();
            }            
        }

        //Selling menu
        if(response.respondSell(userInput)){
            player.printPlayerItems();
            gui.printOnGameSide("Which item would you like to sell?");
            int itemSell = Integer.parseInt(gui.getInput());
            if(player.inventory.get(itemSell) instanceof equipables && ((equipables)player.inventory.get(itemSell)).isEquipped()){

            }
        }
        //no dont buy shit recurse back to display menu()
        if(response.respondNo(userInput)){
            menu();
        }
    }

    private static void openDungeon(){
        if(stageNum % 5 == 0 && AREANUM < areas.length - 1){
            changeArea();
        }


        gui.printOnGameSide("You arrive in " + areas[AREANUM] + " on stage "  + stageNum);

        //create monster
        if(stageNum % 10 == 9){
            boss b = monsterCreater.createBoss();
            b.printMonster();
            monsterMenu(b);
        }
        else{
            int randNum = TrekkerMath.randomInt(5, 0);
            if(randNum == 3){
                rooms.getRandomRoom();
            }
            else{
                monster m = monsterCreater.createMonster();
                m.printMonster();
                monsterMenu(m);
            }
        }
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
                itemMenu();
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
    }

}
