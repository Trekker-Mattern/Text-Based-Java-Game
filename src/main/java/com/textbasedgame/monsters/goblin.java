package com.textbasedgame.monsters;


public  class goblin extends monster {
    public goblin(){
        super.setName("Goblin");
        super.setStrength(monsterCreator.weakMonsterStr(mLevel));
        super.setOrigionalHealth(monsterCreator.weakMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.fastMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "stabs your toes with a toothpick sized knife";
    }
}