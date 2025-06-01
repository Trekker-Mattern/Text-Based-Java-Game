package playerFiles;
import GUI.gui;
import items.*;
import items.genericItems.*;
import java.util.ArrayList;
import monsters.*;
import util.*;


public abstract class player {


    //Overall inventory
    public static ArrayList<item> inventory = new ArrayList<>();
    //Consumables
    public static ArrayList<item> consumableInv = new ArrayList<>();
    //equipped and unequipped inventory
    public static ArrayList<item> equipableItems = new ArrayList<>(); 
    public static ArrayList<equipables> equippedItems = new ArrayList<>();


    ///////////////////////
    /// Player inventory
    /// ///////////////////
    public static holdables LHand;
    public static holdables RHand;
    public static headArmor helm;
    public static chestArmor chestplate;
    public static legsArmor pants;
    public static boots shoes;



    public static String name;
    public static int BankBalance = 100;
    //private static String race;
    public static int strength;
    public static int agility;
    public static int intelligence;
    public static int armor = 0;
    public static double luck = Math.random() * 4;
    public static int playerLevel;
    private static int maxHealth;
    public static int health;
    private static int xpToLevelUp = 100;
    private static int xp = 0;
    public static boolean isBuff = false;
    public static String buffType;
    public static int buffLength;
    public static int currentBuffDuration;

    private static final int totalMaxStartingSkills = 10;





    public static void update(){
        if(isBuff == true){
            updateBuffs();
        }
    }
    //Allocating Skill Points
    public static void allocateSkillPoints(int pStrength, int pAgility, int pIntelligence){
        strength = pStrength;
        agility = pAgility;
        intelligence = pIntelligence;
    }
    public static void allocateSkillPoints(){
        strength = (int)(Math.random() * totalMaxStartingSkills);
        maxHealth = (int)((double)(strength + 1) * luck * 2.0) +1;
        health = maxHealth;
        intelligence = (int)(Math.random() * (totalMaxStartingSkills - strength));
        agility = (totalMaxStartingSkills - (strength + agility));

    }
    
    //Print your stats
    public static void printStats(){
        gui.updatePlayerSide();
        gui.printOnGameSide("Health: "+ health + "/"+maxHealth); 
        gui.printOnGameSide("Strength: " + strength); 
        gui.printOnGameSide("Agility: " + agility); 
        gui.printOnGameSide("Intelligence: " + intelligence);
    }


    //Health Getter
    public static int getMaxHealth(){
        return maxHealth;
    }
    public static void setMaxHealth(int x){
        maxHealth = x;
    }
    public static int getHealth(){
        return health;
    }
    public static void setHealth(int x){
        health = x;
    }
    public static void addHealth(int x){
        if(health + x < maxHealth)
            {health += x;}
        else{health = maxHealth;}
        gui.updatePlayerSide();
    }



    //Strength Getter
    public static int getStrength(){
        int val = strength;
        if(isBuff && buffType == "Strength"){
            val += 2;
        }

        for(equipables e : equippedItems){
            if(e.buffType == "Strength"){
                val += e.buffValue;
            }
        }
        return val;
    }

    //Agility Getter
    public static int getAgility(){
        int val = agility;
        if(isBuff && buffType == "Agility"){
            val += 2;
        }

        for(equipables e : equippedItems){
            if(e.buffType == "Agility"){
                val += e.buffValue;
            }
        }
        return val;
    }

    //Intelligence Getter
    public static int getIntelligence(){
        int val = intelligence;
        if(isBuff && buffType == "Intelligence"){
            val += 2;
        }

        for(equipables e : equippedItems){
            if(e.buffType == "Intelligence"){
                val += e.buffValue;
            }
        }
        return val;
    }

    public static int getArmor(){
        int val = armor;
        if(isBuff && buffType == "Armor"){
            val += 2;
        }

        for(equipables e : equippedItems){
            if(!(e instanceof holdables)){
                val += e.getArmorVal();
            }
        }
        return val;
    }


    //FOR FILE READING -- Finding Xp levels
    public static int getXpToLevelUp(){
        return xpToLevelUp;
    }
    public static void setXpToLevelUp(int x){
        xpToLevelUp = x;
    }
    public static int getXP(){
        return xp;
    }
    public static void setXP(int x){
        xp = x;
    }

    //Bank Balance
    public int getBankBalance(){
        return BankBalance;
    }
    public static void setName(String name) {
        player.name = name;
    }
    public static String getName(){
        return name;
    }
    public static void addItemToPlayer(item i){
        inventory.add(i);
        if(i.isConsumable()){
            consumableInv.add(i);
        }
        else{
            equipableItems.add(i);
        }
    }
    public static void addItemToPlayer(equipables i, boolean equipped){
        inventory.add(i);
        equipableItems.add(i);
        if(equipped){
            equippedItems.add(i);
            i.equipToSlot();
            i.onEquip();
            i.setEquipped(true);
        }
    }


    public static void updateBuffs(){
        if(name.equals("debug")){
            gui.printOnGameSide(buffType);
            gui.printOnGameSide(Integer.toString(buffLength));
            gui.printOnGameSide(Integer.toString(currentBuffDuration));
            gui.printOnGameSide(Boolean.toString(isBuff));
        }
        currentBuffDuration++;
        if(currentBuffDuration >= buffLength){
            deapplyBuff(buffType);
            buffType = null;
            buffLength = 0;
            currentBuffDuration = 0;
            isBuff = false;
        }
    }
    public static void deapplyBuff(String bType){
        isBuff = false;
        buffType = null;
    }

    public static void applyBuff(String bType, int bLength){
        buffLength = bLength;
        buffType = bType;
        currentBuffDuration = 0;
        if(isBuff == false){
            isBuff = true;
        }
        else{
            gui.printOnGameSide("Your prayers have failed, another god favors you already!");
        }
    }
    //Long ass function for asking and allocating skill points
    public static void playerPointAllocation(){

        int stpts = 0;
        int aglpts = 0;
        int intpts = 0;
        //boolean firstRunthrough;




        int totalpts = 0; //points spent
        while(totalpts < 10){
            
            gui.printOnGameSide("How many points would you like to allocate to strength?");
            gui.printOnGameSide("You have " + (10-totalpts) + " left to spend");
                
            int temp = Integer.parseInt(gui.getInput());
            if(temp <= (10-totalpts)){
                stpts += temp;
                totalpts += temp;
                gui.printOnGameSide("You allocate " + stpts + " to strength!");
            }
            else{
                gui.printOnGameSide("You silly goose you don't have " + temp + " points to spend you only have " + (10 - totalpts));
            }

            gui.printOnGameSide("How many points would you like to allocate to agility?");
            gui.printOnGameSide("You have " + (10-totalpts) + " left to spend");
            temp = Integer.parseInt(gui.getInput());
            if(temp <= (10 - totalpts)){
                aglpts += temp;
                totalpts += temp;
                gui.printOnGameSide("You allocate " + aglpts + " to agility!");
            }
            else{
                gui.printOnGameSide("You silly goose you don't have " + temp + " points to spend you only have " + (10 - totalpts));
            }
            gui.printOnGameSide("How many points would you like to allocate to intelligence?");
            gui.printOnGameSide("You have " + (10-totalpts) + " left to spend");
            temp = Integer.parseInt(gui.getInput());
            if(temp <= (10 - totalpts)){
                intpts += temp;
                totalpts += temp;
                gui.printOnGameSide("You allocate " + intpts + " to intelligence!");
            }
            else{
                gui.printOnGameSide("You silly goose you don't have " + temp + " points to spend you only have " + (10 - totalpts));
            }

            if(totalpts < 10){
                int r = 0;
                //gui.printOnGameSide("\n You have " + (10-totalpts) + " left to spend. \nWould you like to go back and add points to the attributes?");
                String userResponse = gui.getInput();
                while(r == 0){
                    gui.printOnGameSide("\nYou have " + (10-totalpts) + " left to spend. \nWould you like to go back and add points to the attributes?");
                    userResponse = gui.getInput();
                    if(response.respondYes(userResponse)){
                        r = 1;
                        break;
                        

                    }
                    else if(response.respondNo(userResponse)){
                        r = 2;
                        break;
                    }
                    
                }
                if(r == 2){
                    break;
                }
            }

        }
        
        allocateSkillPoints(stpts, aglpts, intpts);
        gui.updatePlayerSide();
 
    }

    public static int getPlayerLevel(){
        return playerLevel;
    }
    public static void gainXP(int exp){
        xp += exp;
        if(xp > xpToLevelUp){
            playerLevel++;
            levelPrompt();
        }
    }


    public static void printPlayerItems(){
        int printingNum = 1;
        for(item e : inventory){
            String s = printingNum + ": " + e.toString();
            if(e.getQuality() != null){
                gui.printOnGameSide(s + " - " + e.getQuality());
            }
            else{
                gui.printOnGameSide(s);
            }
            printingNum++;
        }
        

    }


    ///////////////////////////////////////////////////
    // Fighting things
    ///////////////////////////////////////////////////
    public static String getPlayerAttackString(){
        if (RHand != null){
            return RHand.getAttackString();
        }
        else if(LHand != null){
            return LHand.getAttackString();
        }
        return "slap";
    }

    public static void fightMonster(monster m){
        
        if(m.getSpeed() > agility){
            int damageDoneToMonster = damageDone();
            health -= damageTaken(m);
            m.subtractHealth(damageDoneToMonster);
            gui.printOnGameSide("You " + getPlayerAttackString()+ " " + m.getName() + " for "+ damageDoneToMonster);
                
        }
        else{
            int damageDoneToMonster = damageDone();
            m.subtractHealth(damageDoneToMonster);
            gui.printOnGameSide("You " + getPlayerAttackString()+ " " + m.getName() + " for "+ damageDoneToMonster);
            if(m.getHealth() > 0) {
                health -= damageTaken(m);
            }
        }
        if(m.getHealth() > 0){
            gui.printOnGameSide(m.getName() + " has " + m.getHealth() + " health left");
        }
        gui.newlOnGameSide();
        gui.updatePlayerSide();
        
    }

    private static void levelPrompt(){
        if(xp > xpToLevelUp){
            gui.printOnGameSide("Which stat would you like to level up?");
            gui.printOnGameSide("Your options are, strength, agility, intelligence, or health");
            
            String temp = gui.getInput();
            try {
                int num = Integer.parseInt(temp) - 1;
                if(num == 1){temp = "strength";}
                else if(num == 2){temp = "agility";}
                else if(num == 3){temp = "intelligence";}
                else if(num == 4){temp = "health";}
                else{temp = "health";}
            } catch (NumberFormatException ex) {}
            if(temp.toLowerCase().contains("strength")){
                strength += 1;
            }
            else if(temp.toLowerCase().contains("agility")){
                agility += 1;
            }
            else if(temp.toLowerCase().contains("intelligence")){
                intelligence += 1;
            }
            else {
                maxHealth += 10;
                health += (int)(((double)health/maxHealth)*10);
                temp = "health";
            }
            xp -= xpToLevelUp;
            xpToLevelUp *= 1.375;
            gui.printOnGameSide("You level up " + temp);
            gui.printOnGameSide("Your stats are now ");
            printStats();
            levelPrompt();
        }
    }


    private static int damageTaken(monster m){
        int monsterDamage;
        boolean monsterMiss;
        double monsterMultiplyer = 1;
        //Did monster miss
        if(TrekkerMath.randomInt(100, (agility * 2) - m.getSpeed()*2) > 90){
            monsterMiss = true;
        }
        else{
            monsterMiss = false;
        }
        if(monsterMiss == true){
            monsterMultiplyer = 0;
            gui.printOnGameSide("The monster missed and you take 0 damage!");
            return 0;
        }

        monsterDamage = (int)(m.getStrength() * monsterMultiplyer);
        if(monsterDamage < getArmor()){
            gui.printOnGameSide("The " + m.getName() + " tries to " + m.attackString());
            gui.printOnGameSide(" but your strong armor repels their attack");
            return 0;
        }
        if(monsterDamage >= health){
            death(m, monsterDamage);
        }
        gui.printOnGameSide(m.getName() + " " + m.attackString() + " for " + (m.getStrength()*monsterMultiplyer - getArmor()));
        m.attackEffects(monsterDamage - getArmor());
        gui.updatePlayerSide();
        return monsterDamage - getArmor();
    }

    public static int damageDone(){
        double multiplyer;
        boolean playerMiss = false;

        if(TrekkerMath.randomInt(100, (getIntelligence() * 3)) > 40){
            multiplyer = TrekkerMath.randomDouble(2, 0);
            if(multiplyer < .5){
                playerMiss = true;
            }
            else{
                playerMiss = false;
            }
        }
        else {
            multiplyer = 1;
        }
        if(playerMiss == true){
            if(multiplyer != 0){
                gui.printOnGameSide("You swing and only graze the monster but it still does damage.");
            }
            if(multiplyer == 0){
                gui.printOnGameSide("You completely miss on your attack hitting nothing but air.");
            }
        }
        

        ///////////////////////
        /// 
        /// New System for this this currently kind of sucks
        /// 
        /// ////////////////

        int LHandDMG;
        int RHandDMG;

        String LHandDMGType;
        String RHandDMGType;

        if(LHand == null){
            LHandDMGType = "Blunt";
            LHandDMG = 0;
        }
        else{
            LHandDMGType = RHand.getDMGType();
            LHandDMG = getDMGCalcForWeapon(LHand);
        }
        if(RHand == null){
            RHandDMGType = "Blunt";
            RHandDMG = 0;
        }
        else{
            RHandDMG = getDMGCalcForWeapon(RHand);
            RHandDMGType = RHand.getDMGType();
        }

        if(LHand == null && RHand == null){
            return (int)(getStrength() * multiplyer);
        }


        if(LHandDMGType == RHandDMGType){
            int dmg = LHandDMG + RHandDMG;
            dmg *= 1.35;
            dmg *= multiplyer;
            return dmg;
        }
        int dmg = LHandDMG + RHandDMG;
        dmg *= multiplyer;
        return dmg;
    }

    private static int getDMGCalcForWeapon(holdables h){
        if(h.getDMGType() == "Intelligence"){
            return h.getStatIncrease() + getStrength();
        }
        else if(h.getDMGType() == "Strength"){
            return h.getStatIncrease() + getStrength();
        }
        else{
            return getStrength();
        }
    }

    public static void askWhichHandToEquipTo(holdables itemToSwap){
        
        gui.printOnGameSide("Which hand would you like to switch?");
        String handToPick = gui.getInput();
        if(response.Left(handToPick)){
            LHand.Use();
            LHand = itemToSwap;
        }
        else{
            RHand.Use();
            RHand = itemToSwap;
        }
        gui.updatePlayerSide();

    }

    public static void death(monster m, int monsterDamage){
        health = 0;
        gui.printOnGameSide("You have been killed by " + m.getName() + " after it " + m.attackString() + " for " + monsterDamage);
        gui.printOnGameSide("Here are your final stats");
        printPlayerItems();
        printStats();
        saveFiles.save();
    }
}
