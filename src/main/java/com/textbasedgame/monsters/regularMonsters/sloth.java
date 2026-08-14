package com.textbasedgame.monsters.regularMonsters;

import com.textbasedgame.monsters.monster;
import com.textbasedgame.monsters.monsterCreator;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public  class sloth extends monster {
    public sloth(){
        super.setName("Sloth");

        
        super.setStrength(monsterCreator.weakMonsterStr(mLevel));
        super.setOriginalHealth(monsterCreator.medMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.slowMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "claws slowly at you";
    }
	@Override
    public Set<String> getMonsterWeakness() {
        return new HashSet<String>(Arrays.asList("Speed", "Agility"));
    }
}
