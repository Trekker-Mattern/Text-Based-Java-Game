package com.textbasedgame.monsters.regularMonsters;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.handItems.sword;
import com.textbasedgame.monsters.monster;
import com.textbasedgame.monsters.monsterCreator;
import com.textbasedgame.playerFiles.player;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public  class knight extends monster {
    public knight(){
        super.setName("Knight");

        
        super.setStrength(monsterCreator.medMonsterStr(mLevel));
        super.setOriginalHealth(monsterCreator.strongMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.slowMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "slashes you with its long sword";
    }
	@Override
    public Set<String> getMonsterWeakness() {
        return new HashSet<String>(Arrays.asList("Lightning", "Agility", "Corrosion"));
    }
	@Override
	public void onMonsterDeath() {
		gui.printOnGameSide("You manage to sneak an attack through the knights thick armor");
		gui.printOnGameSide("His sword drops to the floor and you grab it to bring with you on your adventure");
		player.addItemToPlayer(new sword(1));	
		
        gui.printOnGameSide("You obtain a sword!"); 
	}
}
