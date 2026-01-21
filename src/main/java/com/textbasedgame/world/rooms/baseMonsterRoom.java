package com.textbasedgame.world.rooms;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.genericItems.attackingConsumable;
import com.textbasedgame.monsters.*;
import com.textbasedgame.world.world;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.itemInfoPrinter;
import com.textbasedgame.util.response;


public class baseMonsterRoom extends Room{
    private static final int roomID = -1;

    private monster m;

    public baseMonsterRoom(){
        m = monsterCreator.createMonster();
    }

    @Override
    public void openRoom() {
        gui.pushOldText();
        monsterMenu(m);
    }

    public monster getMonster(){
        return m;
    };

    @Override
    public int getRoomID() {
        return roomID;
    }

    public static void monsterMenu(monster m){
        while(m.getHealth() > 0){
            gui.newlOnGameSide();
            m.printMonster();
            gui.printOnGameSide("What would you like to do?");
            gui.printOnGameSide("Fight, Use an item, Run, or Quit");
            String h = gui.getInput(); 
            gui.pushOldText();
            if(response.quit(h)){gui.quit();}

            if(response.respondRun(h)){world.run(); break;}
            if(h.equals("info")){itemInfoPrinter.infoMenu(); break;}

            if(response.respondFight(h)){
                player.fightMonster(m);
                if(player.dead){
                    gui.printOnGameSide("You collapse to the floor and feel your spirit float away");
                    gui.printOnGameSide("--Press enter or type yes to continue your journey--");
                    if(response.respondNo(gui.getInput())){
                        gui.quit();
                    }
                    player.onRespawn();
                    return;
                }
                if(m.getHealth() <= 0){
                    gui.newlOnGameSide();
                    gui.printOnGameSide("You defeated " + m.getName() + "!");
                    int coinGain = (int)((player.luck * m.getLevel() / 4) + 1);
                    int xpGain = (int)((player.luck * m.getLevel())*2);
                    gui.printOnGameSide("You obtained " + coinGain + " shmeckles and " + xpGain + " XP!");
                    player.BankBalance += coinGain;
                    player.gainXP(xpGain);
                    m.onMonsterDeath();
                    
                }
            }
            //////////////////////////////////////////
            //Use an item during a fight
            /////////////////////////////////////////
            else if(response.Items(h)){
                itemMenu(m);
            }
        }
    }


        private static void itemMenu(monster m){
        player.printPlayerItems();
        gui.printOnGameSide("Which item would you like to use?");
        String newInput = gui.getInput();
        try{
            int number = Integer.parseInt(newInput);
            try {
                if(player.inventory.get(number-1) instanceof attackingConsumable){
                    attackingConsumable atkCons = (attackingConsumable)player.inventory.get(number-1);
                    gui.printOnGameSide(atkCons.getAttackString());
                    gui.printOnGameSide("You deal " + Integer.toString(((attackingConsumable)player.inventory.get(number-1)).getDamageInt()) + " to " + m.getName());
                    m.subtractHealth(((attackingConsumable)player.inventory.get(number-1)).getDamageInt());

                    if(m.getHealth() <= 0){
                        gui.newlOnGameSide();
                        gui.printOnGameSide("You defeated " + m.getName() + "!");
                        int coinGain = (int)((player.luck * m.getLevel()) + 4);
                        int xpGain = (int)((player.luck * m.getLevel())*4);
                        gui.printOnGameSide("You obtained " + coinGain + " shmeckles and " + xpGain + " XP!");
                        player.BankBalance += coinGain;
                        player.gainXP(xpGain);
                        m.onMonsterDeath();
                        
                    }
                    else{
                        if(player.getAgility() < m.getSpeed()){
                            m.printMonster();
                            gui.printOnGameSide("While you use your item the monster attacks");
                            player.damageTaken(m);
                        }
                    }
                }
                player.inventory.get(number-1).Use();
            } 
            catch (IndexOutOfBoundsException e) {
                gui.printOnGameSide("You dont have that many items you goof!");
            }
        }
        catch(NumberFormatException ex){
            //do nothing
        }
        if(response.respondYes(newInput)){
            gui.printOnGameSide("What is the number of the item you would like to use");
            int temp = Integer.parseInt(gui.getInput());
            try {
                if(player.inventory.get(temp-1) instanceof attackingConsumable){
                    attackingConsumable atkCons = (attackingConsumable)player.inventory.get(temp-1);
                    gui.printOnGameSide(atkCons.getAttackString());
                    gui.printOnGameSide("You deal " + Integer.toString(((attackingConsumable)player.inventory.get(temp-1)).getDamageInt()) + " to " + m.getName());
                    m.subtractHealth(((attackingConsumable)player.inventory.get(temp-1)).getDamageInt());


                    if(m.getHealth() <= 0){
                        gui.newlOnGameSide();
                        gui.printOnGameSide("You defeated " + m.getName() + "!");
                        int coinGain = (int)((player.luck * m.getLevel()) + 4);
                        int xpGain = (int)((player.luck * m.getLevel())*4);
                        gui.printOnGameSide("You obtained " + coinGain + " shmeckles and " + xpGain + " XP!");
                        player.BankBalance += coinGain;
                        player.gainXP(xpGain);
                        m.onMonsterDeath();
                        
                    }
                    else{
                        m.printMonster();
                        player.damageTaken(m);
                    }
                }
                player.inventory.get(temp-1).Use();
            } 
            catch (IndexOutOfBoundsException e) {
                gui.printOnGameSide("You dont have that many items you goof!");
            }
        }
        else if(newInput.toLowerCase().contains("info") ||newInput.toLowerCase().contains("help")){
            itemInfoPrinter.infoMenu();
        }
    }

}
