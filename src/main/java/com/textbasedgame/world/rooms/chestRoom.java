package com.textbasedgame.world.rooms;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.item;
import com.textbasedgame.monsters.mimic;
import com.textbasedgame.playerFiles.*;
import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.util.response;
import com.textbasedgame.world.*;
public class chestRoom extends roomFactory {
    private final int roomID = 1;
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
                        gui.printOnGameSide("From the mimic's corpse you manage to scavenge an extra " + extraMoney + " shmeckles!");
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
    @Override
    public int getRoomID() {
        return roomID;
    }
}
