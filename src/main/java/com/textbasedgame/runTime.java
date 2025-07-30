package com.textbasedgame;
import java.io.File;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.monsters.*;
import com.textbasedgame.playerFiles.*;
import com.textbasedgame.util.response;
import com.textbasedgame.util.saveFiles;
import com.textbasedgame.world.*;


public class runTime
{
    public static String SAVE_FILE_ROOT;
    private static boolean newSaveFolderBool;
    
    public static void main(String[] args) {
        
        startup();


        if(newSaveFolderBool){
             //Startup
            gui.printOnGameSide("What is your name young one?");
            String Name = gui.getInput();

            gui.printOnGameSide("Ah, yes, Good day " + Name + " its so good to see you.");
            
            //Create Player
            player.setName(Name);

            //Allocate poins
            gui.printOnGameSide("Its time to allocate some skill points!");
            gui.printOnGameSide("Would You like to allocate your own points? ");
            String Response = gui.getInput();
            if(response.respondYes(Response))
            {
                gui.printOnGameSide("You have 5 points to spend on 3 different attributes! Choose wisely.");
                player.playerPointAllocation();

            }
            else{
                player.allocateSkillPoints();

            }
        }


        //Play Loop
        
        gui.printOnGameSide("These are your final stats!");
        player.printStats();
        while(true){
            saveFiles.save(); 
            gui.updatePlayerSide();
            world.menu();
        }
        
    }

    private static void startup(){
        SAVE_FILE_ROOT = System.getProperty("user.dir");
        newSaveFolderBool = new File(SAVE_FILE_ROOT + "\\save_Files").mkdir();
        SAVE_FILE_ROOT = SAVE_FILE_ROOT + "\\save_Files";

        shopitems.createShopItemsArr();
        shopitems.createShop();
        if(!newSaveFolderBool){
            saveFiles.readSave();
        }
        monsterArrayList.createMonsterList();
        gui.runGui();
    }
}