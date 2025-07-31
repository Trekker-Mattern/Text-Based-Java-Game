package com.textbasedgame.monsters;

public class slime extends monster{
    public slime(){
        super.setName("Slime");
        super.setStrength(monsterCreator.weakMonsterStr(mLevel));
        super.setOrigionalHealth(monsterCreator.medMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.fastMonsterSpeed(mLevel));
    }
    
    @Override
    public String attackString(){
        return "squishes menacingly";
    }
}
