package com.textbasedgame.world;

import com.textbasedgame.GUI.gui;
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
        gui.printOnGameSide("Dungeon");
        gui.printOnGameSide("Items - Info for item information");
        if(GameProgressWrapper.gameProgress.potionBagUnlocked){gui.printOnGameSide("Potions Crafting");}
        gui.printOnGameSide("Quit");
        gui.printOnGameSide("Save");
        gui.newlOnGameSide();
        String Ans = gui.getInput();
        Ans = Ans.toLowerCase();
        gui.pushOldText();
        if (response.quit(Ans)){gui.quit();}
        //OPEN SHOP!!!
        if(response.Shop(Ans)){
            shop.openShop();
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
        else if(Ans.contains("potions") && GameProgressWrapper.gameProgress.potionBagUnlocked){
            potionsMenu();
        }
    }

    private static void potionsMenu(){
        roomFactory.getCauldronRoom().openRoom();
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
