package com.textbasedgame.world;

import java.util.ArrayList;
import java.util.Arrays;

import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.world.rooms.*;

import java.lang.reflect.Constructor;

public abstract class roomFactory {
    private static ArrayList<Class<? extends roomFactory>> t0Rooms = new ArrayList<>(Arrays.asList(chestRoom.class, fountainRoom.class));
    private static ArrayList<Class<?extends roomFactory>> t1Rooms = new ArrayList<>(Arrays.asList(idolRoom.class, portalRoom.class));
    private static ArrayList<Class<? extends roomFactory>> t2Rooms = new ArrayList<>(Arrays.asList(libraryRoom.class, soulWeighingRoom.class));
    private static ArrayList<Class<? extends roomFactory>> t3Rooms = new ArrayList<>(Arrays.asList(swordInStoneRoom.class));
    public static roomFactory getRandomRoom(){
        try{
            Constructor<? extends roomFactory> ctor = getWeightedRoomClass().getDeclaredConstructor();

            return ctor.newInstance();
        }
        catch(Exception e){
            System.out.println(e + " In getting Room");
            return new chestRoom();
        }
    }

    private static Class<? extends roomFactory> getWeightedRoomClass(){
        //////// 20% first Room 30% second 40% third else fourth
        
        int roomSelector;
        ///T3 Rooms 
        if(TrekkerMath.randomInt(100, 1) >= 85){
            roomSelector = TrekkerMath.randomInt(t3Rooms.size() -1, 0);
            return t3Rooms.get(roomSelector);
        }
        /// T2 Rooms
        else if (TrekkerMath.randomInt(100, 1) >= 70){
            roomSelector = TrekkerMath.randomInt(t2Rooms.size() -1, 0);
            return t2Rooms.get(roomSelector);
        }
        /// T1 Rooms
        else if (TrekkerMath.randomInt(100, 1) >= 60){
            roomSelector = TrekkerMath.randomInt(t1Rooms.size() -1, 0);
            return t1Rooms.get(roomSelector);
        }
        /// T0 Rooms
        else{
            roomSelector = TrekkerMath.randomInt(t0Rooms.size() -1, 0);
            return t0Rooms.get(roomSelector);
        }
    }

    public abstract int getRoomID();

    public abstract void openRoom();
}