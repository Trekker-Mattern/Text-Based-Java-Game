
package com.textbasedgame.monsters.regularMonsters;

import com.textbasedgame.monsters.monster;
import com.textbasedgame.monsters.monsterCreator;

public  class livingStone extends monster {
    public livingStone(){
        super.setName("Living Stone");
        super.setStrength(monsterCreator.weakMonsterStr(mLevel));
        super.setOriginalHealth(monsterCreator.strongMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.slowMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "rolls into you crunching your feet and shins";
    }
}
