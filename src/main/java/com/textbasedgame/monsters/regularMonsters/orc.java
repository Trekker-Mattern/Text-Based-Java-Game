package com.textbasedgame.monsters.regularMonsters;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.genericItems.keyItem;
import com.textbasedgame.items.keyItems.potionsBag;
import com.textbasedgame.items.keyItems.potionsBag.potionHerbs;
import com.textbasedgame.monsters.monster;
import com.textbasedgame.monsters.monsterCreator;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.GameProgressWrapper;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public  class orc extends monster {
    public orc(){
        super.setName("Orc");

        
        super.setStrength(monsterCreator.strongMonsterStr(mLevel));
        super.setOriginalHealth(monsterCreator.medMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.slowMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "launches a large rock at you";
    }
	@Override
    public Set<String> getMonsterWeakness() {
        return new HashSet<String>(Arrays.asList("Lightning", "Light", "Corrosion"));
    }
	@Override
	public void onMonsterDeath() {
		gui.printOnGameSide("The orcs bag falls to the floor with him as he drops dead");	
		gui.printOnGameSide("Inside there sits some herbs!");

		if(GameProgressWrapper.gameProgress.potionBagUnlocked){
			gui.printOnGameSide("You grab the herb and toss it into your bag before continuing on your journey.");
			for(keyItem k : player.keyItemInventory){
				if(k instanceof potionsBag){
					((potionsBag)k).addHerbToBag(potionHerbs.Sage);
					gui.printOnGameSide("You obtain Sage!");
					return;
				}
			}
		}
		gui.printOnGameSide("You have nowhere to put the herbs so you leave it on the ground and continue on.");

	}
}
