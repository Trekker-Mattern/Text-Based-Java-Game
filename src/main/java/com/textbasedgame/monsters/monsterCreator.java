package com.textbasedgame.monsters;

import java.lang.reflect.*;
import com.textbasedgame.util.*;
import com.textbasedgame.world.*;

public class monsterCreator {
    private static final int monsterLevelDivider = 3;

    public static monster createMonster(){

        Class<? extends monster> mType = monsterArrayList.getMonsterType();
        try {
            Constructor<? extends monster> ctor = mType.getDeclaredConstructor();
            monster m = ctor.newInstance();
            return m;
        } catch (Exception e) {
            // Handle the case where the default constructor is not found
            e.printStackTrace();
        }

        return new monster();
    }


    public static boss createBoss(){

        //Get monster type

        Class<? extends boss> mType = monsterArrayList.getBossMonsterType();
        try {
            Constructor<? extends boss> ctor = mType.getDeclaredConstructor();
            boss b = ctor.newInstance();
            return b;
        } catch (Exception e) {
            // Handle the case where the default constructor is not found
            e.printStackTrace();
        } 

        return null;
    }


    public static int fastMonsterSpeed(int monsterLevel){
        return (int)(TrekkerMath.randomDouble(2, .5) * (monsterLevel / monsterLevelDivider));
    }
    public static int medMonsterSpeed(int monsterLevel){
        return (int)(TrekkerMath.randomDouble(1.5, .75) * (monsterLevel / monsterLevelDivider));
    }

    public static int slowMonsterSpeed(int monsterLevel){
        return (int)(TrekkerMath.randomDouble(.5, .001) * monsterLevel / monsterLevelDivider);
    }

    public static int strongMonsterStr(int monsterLevel){
        return (int)(TrekkerMath.randomDouble(2, .5) * monsterLevel / monsterLevelDivider);
    }
    public static int weakMonsterStr(int monsterLevel){
        return (int)(TrekkerMath.randomDouble(1, .2) * monsterLevel / monsterLevelDivider);
    }
    public static int medMonsterStr(int monsterLevel){
        return (int)(TrekkerMath.randomDouble(1.5, .75) * monsterLevel / monsterLevelDivider);
    }
    public static int strongMonsterHealth(int monsterLevel){
        if (monsterLevel  < 5) {
            return (int)(TrekkerMath.randomDouble(2, .8) * (monsterLevel) + 1); 
        }
        else{
            return (int)(TrekkerMath.randomDouble(2, .8) * (monsterLevel) + 1);
        }
    }
    public static int weakMonsterHealth(int monsterLevel){
        if(monsterLevel < 5){
            return (int)(TrekkerMath.randomDouble(1, .2) * (monsterLevel) + 1);
        }
        else{
            return (int)(TrekkerMath.randomDouble(1, .2) * (monsterLevel) + 1);
        }
    }
    public static int medMonsterHealth(int monsterLevel){
        if(monsterLevel < 5){
            return (int)(TrekkerMath.randomDouble(1.5, .75) * (monsterLevel) + 1);
        }
        else{
            return (int)(TrekkerMath.randomDouble(1.5, .75) * (monsterLevel / monsterLevelDivider) + 1);
        }
    }

    public static int getBossArmor(int monsterLevel){
        int monsterArmor;
        if(monsterLevel < 50){
            monsterArmor = 0;
        }
        else{
            monsterArmor = TrekkerMath.randomInt((int)(monsterLevel / 50), 0);
        }
        return monsterArmor;
    }

    public static int createMonsterLevel(){
        int baseCalc = (int)(world.stageNum * TrekkerMath.randomDouble(2, .5));

        if(baseCalc < 2){
            baseCalc++;
        }
        return baseCalc;
        
    }
}
