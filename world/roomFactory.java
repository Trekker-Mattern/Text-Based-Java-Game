package world;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.reflect.Constructor;
import util.TrekkerMath;
import world.rooms.*;

public abstract class roomFactory {
    private static ArrayList<Class<? extends roomFactory>> roomsList = new ArrayList<>(Arrays.asList(chestRoom.class, fountainRoom.class, idolRoom.class, portalRoom.class));

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