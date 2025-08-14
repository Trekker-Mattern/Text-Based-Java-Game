package com.textbasedgame.monsters.regularMonsters;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.textbasedgame.monsters.monster;
import com.textbasedgame.monsters.monsterCreator;

public class demon extends monster{
    public demon(){
        super.setName("Demon");
        super.setStrength(monsterCreator.medMonsterStr(mLevel));
        super.setOriginalHealth(monsterCreator.weakMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.fastMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "scratches you with flaming claws";
    }

    @Override
    public Set<String> getMonsterWeakness() {
        return new HashSet<String>(Arrays.asList("Holy", "Cold", "Ice", "Water"));
    }
}
