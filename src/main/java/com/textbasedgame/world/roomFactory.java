package com.textbasedgame.world;

import java.util.ArrayList;
import java.util.Arrays;

import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.world.rooms.*;

import java.lang.reflect.Constructor;

public abstract class roomFactory {
    private static ArrayList<Class<? extends roomFactory>> roomsList = new ArrayList<>(Arrays.asList(chestRoom.class, fountainRoom.class, idolRoom.class, portalRoom.class, libraryRoom.class, swordInStoneRoom.class));

    public static roomFactory getRandomRoom(){
        int num = TrekkerMath.randomInt(roomsList.size()-1, 0);
        try{
            Constructor<? extends roomFactory> ctor = roomsList.get(num).getDeclaredConstructor();

            return ctor.newInstance();
        }
        catch(Exception e){
            System.out.println(e + " In getting Room");
            return new chestRoom();
        }
    }

    public abstract void openRoom();
}