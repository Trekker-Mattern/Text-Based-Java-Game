package com.textbasedgame.util;
import org.apache.commons.io.FileUtils;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.lang.reflect.Type;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.textbasedgame.runTime;
import com.textbasedgame.items.*;
import com.textbasedgame.items.consumableItems.bread;
import com.textbasedgame.items.genericItems.keyItem;
import com.textbasedgame.playerFiles.*;
import com.textbasedgame.world.*;

public abstract class saveFiles {
    
    private static File saveFile = new File(runTime.SAVE_FILE_ROOT + "\\saveFile.txt");
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

        Gson gson = new GsonBuilder().registerTypeAdapter(item.class, new TJSONDeserializer()).setPrettyPrinting().create();

        FileWriter fWriter = null;
        FileWriter JSONWriter = null;
        try{
            //delete old save
            File saveFolderToDelete = new File(runTime.SAVE_FILE_ROOT);

            if (saveFolderToDelete.exists()) {
                FileUtils.deleteDirectory(saveFolderToDelete);
            } else {
                System.out.println("Directory does not exist: " + saveFolderToDelete.getAbsolutePath());
            }

            try{
                if(saveFolderToDelete.exists()){
                    FileUtils.deleteDirectory(saveFolderToDelete);
                }
            }
            catch(Exception e){
                System.out.println(e);
            }

            if(!new File(runTime.SAVE_FILE_ROOT).exists()){
                new File(runTime.SAVE_FILE_ROOT).mkdir();
            }

            File f = new File((runTime.SAVE_FILE_ROOT + "\\saveFile.txt"));
            saveFile = null;
            saveFile = f;

            fWriter = new FileWriter(saveFile);
            JSONWriter = new FileWriter(new File(runTime.SAVE_FILE_ROOT + "\\playerInventory.JSON"));


            //Begin JSON object
            JSONWriter.write("{\n");
            
            // Save Main Inventory
            JSONWriter.write("\"inventory\":[ \n");
            for(int i = 0; i < player.inventory.size(); i++){

                gson.toJson(player.inventory.get(i), item.class, JSONWriter);
                if(i < player.inventory.size() - 1){
                    JSONWriter.write(",\n");
                }
            }
            JSONWriter.write("\n],\n");

            // Save Key Item Inventory
            JSONWriter.write("\"keyItems\":[ \n");
            for(int i=0; i < player.keyItemInventory.size(); i++){
                gson.toJson(player.keyItemInventory.get(i), item.class, JSONWriter);
                if(i < player.keyItemInventory.size() - 1){
                    JSONWriter.write(",\n");
                }
            }
            JSONWriter.write("\n]\n");

            JSONWriter.write("}\n");


            //Save player Data to text file
            
            fWriter.write("Player-Name: " + player.getName() + "\n");
            fWriter.write("Player-Level: " + player.playerLevel + "\n");
            fWriter.write("Player-Current-Health: " + player.getHealth() + "\n");
            fWriter.write("Player-Maximum-Health: " + player.getMaxHealth() + "\n");
            fWriter.write("Player-Strength: " + player.strength + "\n");
            fWriter.write("Player-Agility: " + player.agility + "\n");
            fWriter.write("Player-Intelligence: " + player.intelligence + "\n");
            fWriter.write("Player-XP-to-Level-Up: " + player.getXpToLevelUp() + "\n");
            fWriter.write("Player-XP: " + player.getXP() + "\n");
            fWriter.write("World-StageNum: " + world.stageNum + "\n");
            fWriter.write("World-AreaNum: " + world.AREANUM + "\n");
            fWriter.write("PlayerCoins: " + player.BankBalance + "\n");
            fWriter.write("PlayerLuck: " + player.luck + "\n");
            fWriter.close();
            JSONWriter.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        finally
        {
            if(JSONWriter != null){
                try {
                    JSONWriter.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if(fWriter != null){
                try {
                    fWriter.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
    private static void goToNextReadableText(Scanner reader){
        reader.nextLine();
        reader.next();
    }
    public static void readPlayerSave(File file){

        int pLvl, chealth, maxhealth, str, ag, inte, xptlu, xp, stgNum, areaNum;
        String nameS;
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
                
                player.setName(nameS);
                player.allocateSkillPoints(str,ag,inte);
                player.setMaxHealth(maxhealth);
                player.setHealth(chealth);
                player.setXP(xp);
                player.setXpToLevelUp(xptlu);


                //DO INVENTORY

                /////////////////
                /// 
                /// Using GSON to read the json that is written to the saveFiles folder  
                /// 
                /////////////////      
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setPrettyPrinting();
                gsonBuilder.registerTypeAdapter(item.class, new TJSONDeserializer());
                Gson jsonToInvGson = gsonBuilder.create();

                Type listType = new TypeToken<List<item>>() {}.getType();
                FileReader jsonFileReader = new FileReader(runTime.SAVE_FILE_ROOT + "\\playerInventory.JSON");
                JsonObject rootObject = JsonParser.parseReader(jsonFileReader).getAsJsonObject();

                //Add items to inventory
                List<item> inv = jsonToInvGson.fromJson(rootObject.get("inventory"), listType);
                if(inv != null){
                    for(item invItem : inv){
                        if(invItem instanceof equipables){
                            equipables eqpItem = (equipables) invItem;
                            player.addItemToPlayer(eqpItem, eqpItem.isEquipped());
                        }
                        else{
                            player.addItemToPlayer(invItem);
                        }
                    }
                }

                //Add key items to inventory
                List<item> keyItems = jsonToInvGson.fromJson(rootObject.get("keyItems"), listType);
                if(keyItems != null){
                    for(item invItem : keyItems){
                        player.addKeyItemToPlayer((keyItem)invItem);
                    }
                }


                jsonFileReader.close();

                

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
            }
            catch(IOException e){
                System.out.println("Uh oh");
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
    
    
    @SuppressWarnings("unused")
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
