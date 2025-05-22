package util;
import items.*;
import items.consumableItems.bread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Scanner;
import playerFiles.*;
import world.shopitems;

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

    public static void save(int AreaNum, int StageNum){
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
                s += e.getItemName() + " ";
            }
            
            fWriter.write("Player-Name: " + player.getName() + "\n");
            fWriter.write("Player-Current-Health: " + player.getHealth() + "\n");
            fWriter.write("Player-Maximum-Health: " + player.getMaxHealth() + "\n");
            fWriter.write("Player-Strength: " + player.getStrength() + "\n");
            fWriter.write("Player-Agility: " + player.getAgility() + "\n");
            fWriter.write("Player-Intelligence: " + player.getIntelligence() + "\n");
            fWriter.write("Player-XP-to-Level-Up: " + player.getXpToLevelUp() + "\n");
            fWriter.write("Player-XP: " + player.getXP() + "\n");
            fWriter.write("Player-Inventory: " + s + "\n");
            fWriter.write("World-StageNum: " + StageNum + "\n");
            fWriter.write("World-AreaNum: " + AreaNum);
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
        int chealth, maxhealth, str, ag, inte, xptlu, xp;
            String nameS, invListString;
            try{
                Scanner myReader = new Scanner(file);
                myReader.next();
                nameS = myReader.nextLine();
                nameS = nameS.substring(1);
                myReader.next();
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
                invListString = invListString.substring(1);
                String[] itemList = invListString.split(" ");
                for(String s: itemList){
                    Class<? extends item> e = shopitems.allItemsList.get(s);
                    player.addItemToPlayer(getItemToAddToInv(e));
                }
                                

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
}
