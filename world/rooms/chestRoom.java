package world.rooms;
import items.item;
import GUI.gui;
import util.response;
import world.*;
import playerFiles.*;
import monsters.mimic;
import util.TrekkerMath;
import world.roomFactory;
public class chestRoom extends roomFactory {
    public void openRoom(){
        gui.printOnGameSide("You walk into a room and theres a chest sitting on the floor");
            gui.printOnGameSide("Would you like to open it?");
            
            String userResp = gui.getInput();
            if(response.respondYes(userResp)){
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
                        player.setHealth(player.getHealth() - damage);
                        break; 
                }
            
            }
    }
}
