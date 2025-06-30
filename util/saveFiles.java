package util;
import items.*;
import items.consumableItems.bread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Scanner;
import playerFiles.*;
import world.*;

public abstract class saveFiles {
    
    private static File saveFile = new File("saveFile.txt");
    private static boolean newSave = false;


    public static boolean isNewFile(){
        return newSave;
    }
    public static File getFile(){
        return saveFile;
    }

    
    public static void readSave(){
        try{
            if(saveFile.createNewFile()){
                newSave = true;
            }
            else{
                Scanner s = new Scanner(saveFile);
                try{
                    s.nextLine();
                    s.close();
                }
                catch(java.util.NoSuchElementException e){
                    System.out.println("fileEmpty");
                    newSave = true;
                    return;
                }
                
                readPlayerSave(saveFile);
            }
        }
        catch(IOException e){
            System.out.println("Error Exit Code 1");
            System.exit(0);
        }
    }

    public static void save(){
        try{
            //delete old save
            saveFile.delete();
            File f = new File("saveFile.txt");
            saveFile = null;
            saveFile = f;

            FileWriter fWriter = new FileWriter(saveFile);

            // get string version of inventory 
            String s = "";
            for(item e: player.inventory){
                s += e.getItemNameForSaveFiles();
                if (e instanceof equipables){
                    s += "-";
                    s += ((equipables)e).getQualityInt();
                    if(((equipables)e).isEquipped()){
                        s += "-true";
                    }
                }
                s += ",";
            }
            
            fWriter.write("Player-Name: " + player.getName() + "\n");
            fWriter.write("Player-Level: " + player.playerLevel + "\n");
            fWriter.write("Player-Current-Health: " + player.getHealth() + "\n");
            fWriter.write("Player-Maximum-Health: " + player.getMaxHealth() + "\n");
            fWriter.write("Player-Strength: " + player.strength + "\n");
            fWriter.write("Player-Agility: " + player.agility + "\n");
            fWriter.write("Player-Intelligence: " + player.intelligence + "\n");
            fWriter.write("Player-XP-to-Level-Up: " + player.getXpToLevelUp() + "\n");
            fWriter.write("Player-XP: " + player.getXP() + "\n");
            fWriter.write("Player-Inventory: " + s + "\n");
            fWriter.write("World-StageNum: " + world.stageNum + "\n");
            fWriter.write("World-AreaNum: " + world.AREANUM + "\n");
            fWriter.write("PlayerCoins: " + player.BankBalance + "\n");
            fWriter.write("PlayerLuck: " + player.luck + "\n");
            fWriter.close();
            
        }
        catch(Exception e){
            System.out.println("fasldfkj");
        }
    }
    private static void goToNextReadableText(Scanner reader){
        reader.nextLine();
        reader.next();
    }
    public static void readPlayerSave(File file){
        int pLvl, chealth, maxhealth, str, ag, inte, xptlu, xp, stgNum, areaNum;
            String nameS, invListString;
            try{
                Scanner myReader = new Scanner(file);
                myReader.next();
                nameS = myReader.nextLine();
                nameS = nameS.substring(1);
                myReader.next();
                pLvl = myReader.nextInt();
                saveFiles.goToNextReadableText(myReader);
                chealth = myReader.nextInt();
                saveFiles.goToNextReadableText(myReader);
                maxhealth = myReader.nextInt();
                saveFiles.goToNextReadableText(myReader);
                str = myReader.nextInt();
                saveFiles.goToNextReadableText(myReader);
                ag = myReader.nextInt();
                saveFiles.goToNextReadableText(myReader);
                inte = myReader.nextInt();
                saveFiles.goToNextReadableText(myReader);
                xptlu = myReader.nextInt();
                saveFiles.goToNextReadableText(myReader);
                xp = myReader.nextInt();
                saveFiles.goToNextReadableText(myReader);
                invListString = myReader.nextLine();
                
                player.setName(nameS);
                player.allocateSkillPoints(str,ag,inte);
                player.setMaxHealth(maxhealth);
                player.setHealth(chealth);
                player.setXP(xp);
                player.setXpToLevelUp(xptlu);


                //DO INVENTORY

                /////////////////
                /// 
                /// WE SPLIT THIS SHIT INTO THREE POSSIBLE SEGMENTS 0- ITEM/CLASS KEY FOR LOOKUP IN HASHMAP   1- QUALITY AS AN INTEGER   2- IS EQUIPPED?  
                /// 
                /////////////////
                invListString = invListString.trim();
                if(invListString != ""){
                    String[] itemList = invListString.split(",");
                    for(String s: itemList){
                        s = s.trim();
                        if(s.contains("-")){

                            String[] sArr = s.split("-");
                            Class<? extends item> e = shopitems.allItemsList.get(sArr[0]);

                            if(sArr.length > 2){
                                boolean equipped = Boolean.parseBoolean(sArr[2]);
                                player.addItemToPlayer((equipables)getItemToAddToInv(e, Integer.parseInt(sArr[1])), equipped);
                            }
                            else{
                                player.addItemToPlayer(getItemToAddToInv(e, Integer.parseInt(sArr[1])));
                            }
                            
                        }
                        else{
                            Class<? extends item> e = shopitems.allItemsList.get(s);
                            player.addItemToPlayer(getItemToAddToInv(e));
                        }
                    }
                }
                                
                myReader.next();
                stgNum = myReader.nextInt();
                goToNextReadableText(myReader);
                areaNum = myReader.nextInt();
                goToNextReadableText(myReader);
                player.BankBalance = myReader.nextInt();
                goToNextReadableText(myReader);
                player.luck = myReader.nextDouble();

                world.AREANUM = areaNum;
                world.stageNum = stgNum;
                player.playerLevel = pLvl;

                myReader.close();
                return;
            }
            catch(IOException e){
                System.out.println("Uh oh");

                return;
            }
    }
    public static item getItemToAddToInv(Class<? extends item> e){

        try{
            Constructor<? extends item> ctor = e.getDeclaredConstructor();
            item a = ctor.newInstance();
            
            return a;
        }
        catch(Exception exception){
            return new bread();
        }
    }
    private static item getItemToAddToInv(Class<? extends item> e, int quality){
        try{
            try{
                Constructor<? extends item> ctor = e.getDeclaredConstructor(int.class);
                return ctor.newInstance(quality);
            }
            catch(Exception wrongConstructor){
                System.out.println("Falling back to no-arg constructor for " + e.getName());
                Constructor<? extends item> ctor = e.getDeclaredConstructor();
                return ctor.newInstance();
            }
        }
        catch(Exception somethingWentReallyWrong){
            System.out.println("Something went Really Wrong" + somethingWentReallyWrong);
            return new bread();
        }
    }
}
