package com.textbasedgame.monsters;

public class rat extends monster{
    public rat(){
        super.setName("Rat");
        super.setStrength(monsterCreator.weakMonsterStr(mLevel));
        super.setOrigionalHealth(monsterCreator.weakMonsterHealth(mLevel));
        super.setSpeed(monsterCreator.fastMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "bites down with its little teeth";
    }
}

