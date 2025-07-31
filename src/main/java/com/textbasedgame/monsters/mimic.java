package com.textbasedgame.monsters;
public class mimic extends monster{
    public mimic(){
        super.setName("Mimic");
        super.setStrength(monsterCreator.strongMonsterStr(mLevel));
        super.setOrigionalHealth(monsterCreator.medMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.fastMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "bites you with oversized teeth";
    }
}