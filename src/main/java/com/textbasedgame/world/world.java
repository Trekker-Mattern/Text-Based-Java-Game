package com.textbasedgame.world;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.monsters.monsterArrayList;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.GameProgressWrapper;
import com.textbasedgame.util.itemInfoPrinter;
import com.textbasedgame.util.response;
import com.textbasedgame.util.saveFiles;
import com.textbasedgame.util.selectionMenu;
import com.textbasedgame.world.rooms.Room;

// Singleton class: accessed statically throughout game
public abstract class world { 

    public static int AREANUM = 0;
    private static final String areas[] = {"Village", "Outer Gates", "Grassland", "Graveyard", "Tunnels!", "Cave", "A second Cave", "A THIRD CAVE??", "Hell", "Why is there two hells?", "Are you actually still playing???", "Bored Yet?", "How bout now?", "Get some sleep please", "Touch Grass?"}; 
    public static int stageNum = 0;
    public enum CharacterNames {
        LIZZY,
        IGGY,
        PLAYER
    };

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
        Room room = roomFactory.getNextRoom();
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
        String userInput = gui.getInput();
        gui.pushOldText();

		
		int selectionValue = selectionMenu.selectScreenToInteger(player.inventory, userInput)-1;
		if(selectionValue == -2){gui.printOnGameSide("You dont have that many items you goof!");return false;}
		if(selectionValue != -1){player.inventory.get(selectionValue-1).Use();return true;}

                
        if(userInput.toLowerCase().contains("info") || userInput.toLowerCase().contains("help")){
            player.printPlayerItems();
            gui.printOnGameSide("Which item would you like more information on?");
			userInput = gui.getInput();
			selectionValue = selectionMenu.selectScreenToInteger(player.inventory, userInput)-1; 
			itemInfoPrinter.printItemInfo(player.inventory.get(selectionValue));
		}
		return false;
        
    }
    
    public static void run(){
        stageNum -= stageNum % 5;
        stageNum--; // because the stage num gets incremented at the end of dungeon
        gui.printOnGameSide("You flee and return back to the last safe area that you remember");
    }

    private static void changeArea(){
        shopitems.createShop();
        if(stageNum / 5 < areas.length - 1){
            AREANUM = stageNum / 5;
        }
        monsterArrayList.updateMonsterArrayListOnAreaUpdate();
        gui.printOnGameSide("You notice the scenery changing. You step down into " + getArea());
        gui.updatePlayerSide();
        gui.pushOldText();
    }

    public static void updateArea(){
        
        shopitems.createShop();
        if(stageNum / 5 < areas.length - 1){
            AREANUM = stageNum / 5;
        }
        monsterArrayList.updateMonsterArrayListOnAreaUpdate();
    }

}
