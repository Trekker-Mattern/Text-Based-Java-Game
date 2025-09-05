package com.textbasedgame.world.rooms;

import java.util.ArrayList;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.consumableItems.genericPotion;
import com.textbasedgame.items.genericItems.keyItem;
import com.textbasedgame.items.keyItems.potionsBag;
import com.textbasedgame.items.keyItems.potionsBag.potionHerbs;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.GameProgressWrapper;
import com.textbasedgame.util.response;
import com.textbasedgame.util.triple;

public class cauldronRoom extends Room {
    public static final int roomID = 8;
    @Override
    public int getRoomID() {
        return roomID;
    }
    @Override
    public void openRoom(){
        if(playerHasPotionBag()){

            gui.printOnGameSide("You walk into a room with a cauldron in the center!");
            gui.printOnGameSide("Would you like to create a potion with herbs from your bag?");

            potionsBag potBag = null;
            for(keyItem i : player.keyItemInventory){
                if(i instanceof potionsBag){
                    potBag = ((potionsBag)i);
                    break;
                }
            }

            if(response.respondYes(gui.getInput())){

                genericPotion creationPotion = new genericPotion("Created Potion");
                while(response.respondYes("Would you like to put an herb from your bag into the cauldron?")){
                    potionHerbs herbToPutInPotion = potBag.getHerbFromBag();

                    if(herbToPutInPotion != null){
                        putHerbIntoPotion(creationPotion, herbToPutInPotion);
                        gui.printOnGameSide("You add your ingredient to the bubbling concoction, the vat sizzles");
                    }

                }
                gui.printOnGameSide("Satisfied with your work you take an empty bottle out of your bag and gather as much of the liquid as you can");
                creationPotion.setName(gui.getInput("What would you like to name your potion?"));
                player.addItemToPlayer(creationPotion);
                return;
            }



        }
        else{
            gui.printOnGameSide("You step into the room and pause as your foot hits a soft object");
            gui.printOnGameSide("You pick up the bag that is on the floor");
            gui.printOnGameSide("Its a potions bag for holding herbs and making unique potions!");
            player.addKeyItemToPlayer(new potionsBag());
            GameProgressWrapper.gameProgress.potionBagUnlocked = true;
        }
    }


    private boolean playerHasPotionBag(){
        if(GameProgressWrapper.gameProgress.potionBagUnlocked){return true;}
        for(keyItem i : player.keyItemInventory){
            if(i instanceof potionsBag){
                return true;
            }
        }
        return false;
    }

    private void putHerbIntoPotion(genericPotion potion, potionHerbs herb){
        switch (herb){
            case Peppermint:
                if(!potion.isBuffsEmpty()){changePotionBuffTime(potion, -3);}
                potion.addBuff(buffTypes.HEALTH_REGENERATION, player.getMaxHealth() / 12, 6);
                break;
            case Dogwood:
                if(!potion.isBuffsEmpty()){changePotionBuffTime(potion, -1);}
                potion.addBuff(buffTypes.ARMOR, 4, 4);
                break;
            case EntBranch:
                if(!potion.isBuffsEmpty()){changePotionBuffTime(potion, -2);}
                potion.addBuff(buffTypes.STRENGTH, 2, 4);
                break;
            case Honeysuckle:
                if(!potion.isBuffsEmpty()){changePotionBuffTime(potion, -1);}
                potion.addBuff(buffTypes.AGILITY, 3, 5);
                break;
            case Ivy:
                if(!potion.isBuffsEmpty()){changePotionBuffTime(potion, -2);}
                potion.addBuff(buffTypes.INTELLIGENCE, 2, 4);
                break;
            case LemonGrass:
                potion.addBuff(buffTypes.HEALTH_REGENERATION, 3, 12);
                break;
            case Mushroom:
                if(!potion.isBuffsEmpty()){changePotionBuffTime(potion, 4);}
                break;
            case Sage:
                if(!potion.isBuffsEmpty()){changePotionBuffTime(potion, 6);}
                break;
            case ToadsFoot:
                if(!potion.isBuffsEmpty()){changePotionBuffTime(potion, 2);}
                break;
            default:
                potion.addBuff(buffTypes.STRENGTH, 1, 3);
                break;
                
        }

    }

    private void changePotionBuffTime(genericPotion potion, int deltaTime){
        ArrayList<triple<buffTypes, Integer, Integer>> buffs = potion.getBuffsAsList();
        for(triple<buffTypes, Integer, Integer> buff : buffs){
            buff.third += deltaTime;
        }
        potion.setBuffsList(buffs);
    }
}
