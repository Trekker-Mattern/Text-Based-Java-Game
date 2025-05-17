
import java.util.Scanner;

import GUI.gui;
import monsters.*;
import playerFiles.*;
import util.response;
import util.saveFiles;
import world.*;


public class runTime
{
    
    
    
    public static void main(String[] args) {
        
        startup();

        Scanner userInput = new Scanner(System.in);
        if(saveFiles.isNewFile()){
             //Startup
            System.out.println("What is your name young one?");
            String Name = userInput.nextLine();

            System.out.println("Ah, yes, Good day " + Name + " its so good to see you.");
            
            //Create Player
            player.setName(Name);

            //Allocate poins
            System.out.println("Its time to allocate some skill points!");
            System.out.println("Would You like to allocate your own points? ");
            String Response = userInput.nextLine();
            if(response.respondYes(Response))
            {
                System.out.println("You have 10 points to spend on 4 different attributes! Choose wisely.");
                player.playerPointAllocation();

            }
            else{
                player.allocateSkillPoints();

            }
        }
        else{//WERE READING FILES NOW????

            saveFiles.readPlayerSave(saveFiles.getFile());
            

        }

        //Play Loop
        
        System.out.println("These are your final stats!");
        player.printStats();
        while(true){
            saveFiles.save(world.AREANUM, world.stageNum); 
            world.menu();
            player.update();
            userInput.close();
        }
        
    }

    static void startup(){
        saveFiles.readSave();
        shopitems.createShopItemsArr();
        monsterArrayList.createMonsterList();
        gui.runGui();
    }
}