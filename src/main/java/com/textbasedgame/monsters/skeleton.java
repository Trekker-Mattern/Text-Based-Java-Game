package com.textbasedgame.monsters;


public  class skeleton extends monster {
    public skeleton(){
        super.setName("Skeleton");
        super.setStrength(monsterCreator.medMonsterStr(mLevel));
        super.setOrigionalHealth(monsterCreator.weakMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.slowMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "rattles his bones at you";
    }
}
