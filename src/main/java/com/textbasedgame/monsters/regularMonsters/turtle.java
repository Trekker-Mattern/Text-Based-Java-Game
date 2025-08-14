package com.textbasedgame.monsters.regularMonsters;
import com.textbasedgame.items.consumableItems.genericPotion;
import com.textbasedgame.monsters.monster;
import com.textbasedgame.monsters.monsterCreator;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;

public class turtle extends monster{
    public turtle(){
        super.setName("Turtle");
        super.setStrength(monsterCreator.weakMonsterStr(mLevel));
        super.setOriginalHealth(monsterCreator.strongMonsterHealth(mLevel));
        super.setSpeed(1);
    }
    @Override
    public String attackString(){
        return "slowly chomps down on your leg";
    }
    @Override
    public void onMonsterDeath(){
        genericPotion a = new genericPotion("Turtle Blood" ,buffTypes.ARMOR, 6, 5);
        if(player.getAgility() > 4){
            a.addBuff(buffTypes.AGILITY, -4, 5);
        }
        else{
            a.addBuff(buffTypes.AGILITY, player.getAgility() * -1, 5);
        }
        player.addItemToPlayer(a);
    }
}
