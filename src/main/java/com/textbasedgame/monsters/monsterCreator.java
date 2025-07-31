package com.textbasedgame.monsters;

import java.lang.reflect.*;
import com.textbasedgame.util.*;

public class monsterCreator {
    

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
        //Class<?> monsterType = 

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
        return (int)(TrekkerMath.randomDouble(2, .5) * monsterLevel);
    }
    public static int medMonsterSpeed(int monsterLevel){
        return (int)(TrekkerMath.randomDouble(1.5, .75) * monsterLevel);
    }

    public static int slowMonsterSpeed(int monsterLevel){
        return (int)(TrekkerMath.randomDouble(.5, .001) * monsterLevel);
    }

    public static int strongMonsterStr(int monsterLevel){
        //TODO : FINISH THIS CALCULATION
        
        return (int)(TrekkerMath.randomDouble(2, .5) * monsterLevel);
    }
    public static int weakMonsterStr(int monsterLevel){
        return (int)(TrekkerMath.randomDouble(1, .2) * monsterLevel);
    }
    public static int medMonsterStr(int monsterLevel){
        return (int)(TrekkerMath.randomDouble(1.5, .75) * monsterLevel);
    }
    public static int strongMonsterHealth(int monsterLevel){
        return (int)(TrekkerMath.randomDouble(2, .5) * monsterLevel);
    }
    public static int weakMonsterHealth(int monsterLevel){
        return (int)(TrekkerMath.randomDouble(1, .2) * monsterLevel);
    }
    public static int medMonsterHealth(int monsterLevel){
        return (int)(TrekkerMath.randomDouble(1.5, .75) * monsterLevel);
    }
}
