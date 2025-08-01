package com.textbasedgame.monsters;

public  class snake extends monster {
    public snake(){
        super.setName("Snake");

        
        super.setStrength(monsterCreator.weakMonsterStr(mLevel));
        super.setOrigionalHealth(monsterCreator.weakMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.fastMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "bites you really hard";
    }
}