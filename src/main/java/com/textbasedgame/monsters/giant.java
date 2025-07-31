package com.textbasedgame.monsters;

public  class giant extends monster {
    public giant(){
        super.setName("Giant");
        super.setStrength(monsterCreator.medMonsterStr(mLevel));
        super.setOrigionalHealth(monsterCreator.strongMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.slowMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "whacks you with his big fist";
    }
}