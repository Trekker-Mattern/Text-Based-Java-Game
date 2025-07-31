package com.textbasedgame.monsters;

public  class troll extends monster {
    public troll(){
        super.setName("Troll");
        super.setStrength(monsterCreator.strongMonsterStr(mLevel));
        super.setOrigionalHealth(monsterCreator.strongMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.slowMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "whacks you with a large club";
    }
}