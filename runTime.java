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


        if(saveFiles.isNewFile()){
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
            shopitems.createShop();
            gui.updatePlayerSide();
            world.menu();
        }
        
    }

    static void startup(){
        shopitems.createShopItemsArr();
        saveFiles.readSave();
        monsterArrayList.createMonsterList();
        gui.runGui();
    }
}