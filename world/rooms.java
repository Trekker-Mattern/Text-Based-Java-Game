package world;
import util.*;
import GUI.gui;
import items.*;
import items.handItems.sword;
import playerFiles.*;
import monsters.*;
public class rooms {


    public static void getRandomRoom(){
    int roomNum = TrekkerMath.randomInt(3, 0);
    switch (roomNum){
        case 0:{
            
            gui.printOnGameSide("You walk into a room and theres a chest sitting on the floor");
            gui.printOnGameSide("Would you like to open it?");
            
            String userResp = gui.getInput();
            if(response.respondYes(userResp)){
                //int i = TrekkerMath.randomInt(0, 5);
                int i = TrekkerMath.randomInt(4,0);
                switch (i){
                    case 0:
                        gui.printOnGameSide("You open the chest and get an item!");
                        item a = shopitems.getRandomItem();
                        gui.printOnGameSide("You obtain " + a);
                        player.inventory.add(a);
                        if(a.isConsumable()){
                            player.consumableInv.add(a);
                        }
                        else{
                            player.equipableItems.add(a);
                        } 
                        
                    break;
                    case 1:
                        gui.printOnGameSide("You start to open the chest but a tongue wraps around your hand, its a mimic!");
                        mimic m = new mimic();
                        world.monsterMenu(m);
                        int extraMoney = (int)(TrekkerMath.randomInt(100,0) * player.luck);
                        gui.printOnGameSide("From the mimic's corpse you manage to scavange an extra " + extraMoney + " shmeckles!");
                        player.BankBalance += extraMoney;
                        
                    break;
                    case 2:
                        gui.printOnGameSide("You open the chest and there is money inside!");
                        extraMoney = (int)(TrekkerMath.randomInt(300,0) * player.luck);
                        player.BankBalance += extraMoney;
                        gui.printOnGameSide("You gain " + extraMoney + " shmeckles!");
                        
                    break;
                    case 3:
                        gui.printOnGameSide("The chest is locked");
                        gui.printOnGameSide("You try and try but cant open it");
                        
                    break;
                    case 4:
                        gui.printOnGameSide("You trip and fall on your way over to the chest and break your foot");
                        int damage = TrekkerMath.randomInt(player.health / 4, 0);
                        gui.printOnGameSide("You take " + damage + " damage from falling");
                        
                    break; 
                }
            
            }
            break;
        }
        //Pray to an idol
        case 1:{
            gui.printOnGameSide("You walk into a large empty cobblestoned room with a idol");
            String buffType = "";
            int i = TrekkerMath.randomInt(3, 0);
            switch(i){
                case 0:
                buffType = "Strength";
                gui.printOnGameSide("The statue seems to depict a hulking bear");
                break;
                case 1:
                buffType = "Intelligence";
                gui.printOnGameSide("The statue seems to depict a large owl with dark Jade eyes");
                break;
                case 2:
                buffType = "Agility";
                gui.printOnGameSide("The statue seems to depict a gazelle that is mid jump");
                break;
                case 3:
                buffType = "Armor";
                gui.printOnGameSide("The statue seems to depict an armadillo that is curled up into a little ball");
                break;
            }
            gui.printOnGameSide("Would you like to pray to the idol?");
            String userResp = gui.getInput();
            if(response.respondYes(userResp)){
                player.applyBuff(buffType, 5);
                gui.printOnGameSide("You feel blessed! The animal has improved you!");
            }
            
            break;
        }
        case 2:
        {
        //healing fountain?
        gui.printOnGameSide("You walk into a room with a fountain in the center. The room is too dark to see what color the water is");

        gui.printOnGameSide("Take a drink?");
        String userResp = gui.getInput();
        if(response.respondYes(userResp)){
            int i = TrekkerMath.randomInt(roomNum, 0);
            switch(i){
                case 0:
                gui.printOnGameSide("The water was crystal clear! You feel your wounds healing! Must've been a magic fountian!");
                player.addHealth(player.getMaxHealth() - player.getHealth());
                break;
                case 1:
                gui.printOnGameSide("The water tasted slightly metallic. You ignore the strange taste and continue drinking.");
                player.addHealth(player.getMaxHealth() / 10);
                gui.printOnGameSide("Do you decide to reach into the water to see what lies beneath the surface?");
                userResp = gui.getInput();
                if(response.respondYes(userResp)){
                    i = TrekkerMath.randomInt(1, 0);
                    switch(i){
                        case 0:
                        gui.printOnGameSide("You reach in and find coins!");
                        int coinage = TrekkerMath.randomInt(world.stageNum + 20, 0);
                        gui.printOnGameSide("You gain " + coinage + " shmeckles!");
                        break;
                        case 1:
                        gui.printOnGameSide("You reach in and find a sword lying under the surface of the water!");
                        player.addItemToPlayer(new sword());
                        break;
                        case 2:
                        gui.printOnGameSide("A goblin grabs your arm! In your haste to recover he takes off three of your fingers!");
                        player.health -= 10;
                        goblin g = new goblin();
                        world.monsterMenu(g);
                        break;
                    }
                }
                break;
                case 2:
                gui.printOnGameSide("The water tastes like garbage! You turn around and immediately vomit!");
                int h = player.health /= 4;
                gui.printOnGameSide("You manage to keep your intestines inside of you. You have " + h + " health left.");
                break;
            }
        }
        
        break;
        }
        case 3:{    
            //////////////////////
            /// 
            /// PORTAL WOOOOOSH
            /// 
            /// /////////////////

            int i = TrekkerMath.randomInt(0, 1);
            if(i == 0){
                gui.printOnGameSide("A portal sits in a mostly empty room, It shines with a green hue.");
                gui.printOnGameSide("Enter?");

                if(response.respondYes(gui.getInput())){
                    gui.printOnGameSide("You enter the floating spiral, slowly glancing around at the room that now surrounds you.");

                    i = TrekkerMath.randomInt(3, -3);
                    world.stageNum+=i;
                    if(i > 0){
                        gui.printOnGameSide("The room seems unfamilair but you get the sense that you've made progress.");
                    }
                    else if(i < 0){
                        gui.printOnGameSide("This room feels brutally familiar, reminding you of recent battles faught.");
                        gui.printOnGameSide( "You sigh knowing you have a longer way to go to progress now.");
                    }
                    else{
                        gui.printOnGameSide("The room looks exactly like the room you just left. You raise an eyebrow and continue on.");
                    }
                }

            }
            else{
                gui.printOnGameSide("A portal sits in a mostly empty room, It shines with a blue hue.");
                gui.printOnGameSide("Enter?");

                if(response.respondYes(gui.getInput())){
                    gui.printOnGameSide("You enter the floating spiral, slowly glancing around at the room that now surrounds you.");
                    

                    i = TrekkerMath.randomInt(3, -3);
                    world.AREANUM+=i;
                    if(i > 0){
                        gui.printOnGameSide("The scenery has changed considerably;");
                        gui.printOnGameSide("The light around you is significantly dimmer.");
                        gui.printOnGameSide( "You believe that this portal has helped make major progress in your journey.");
                    }
                    else if(i < 0){
                        gui.printOnGameSide("The scenery has changed considerably;");
                        gui.printOnGameSide("The flooring and walls look too familiar, closely resembling areas from the past.");
                        gui.printOnGameSide("The sickening sensation of lost progress sets in");
                    }
                    else{
                        gui.printOnGameSide("Nothing major changed. Actually nothing changed at all. You shrug and continue on.");
                    }
                }
            }








            break;
        }
        }
        world.stageNum++;
    }
}