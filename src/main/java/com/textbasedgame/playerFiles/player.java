package com.textbasedgame.playerFiles;
import java.util.ArrayList;
import java.util.Set;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.items.equipables;
import com.textbasedgame.items.genericItems.boots;
import com.textbasedgame.items.genericItems.chestArmor;
import com.textbasedgame.items.genericItems.headArmor;
import com.textbasedgame.items.genericItems.holdables;
import com.textbasedgame.items.genericItems.legsArmor;
import com.textbasedgame.items.genericItems.holdables.damageTypes;
import com.textbasedgame.items.item;
import com.textbasedgame.monsters.monster;
import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.util.pair;
import com.textbasedgame.util.response;
import com.textbasedgame.util.saveFiles;
import com.textbasedgame.util.triple;



public abstract class player {

    public enum buffTypes {STRENGTH, AGILITY, INTELLIGENCE, ARMOR};

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
    //private static String race;
    public static int strength;
    public static int agility;
    public static int intelligence;
    public static int armor = 0;
    public static double luck = Math.random() * 4;
    public static int BankBalance = (int)(5 * luck);
    public static int playerLevel = 5;
    private static int maxHealth;
    public static int health;
    private static int xpToLevelUp = 10;
    private static int xp = 0;
    public static boolean isBuff = false;
    public static ArrayList<triple<buffTypes, Integer, Integer>> buffs = new ArrayList<>();

    private static final int totalMaxStartingSkills = 5;





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
        maxHealth = (int)((double)(strength + 1) * (luck+1)) + 10;
        health = maxHealth;
    }
    public static void allocateSkillPoints(){
        strength = (int)(Math.random() * totalMaxStartingSkills);
        maxHealth = (int)((double)(strength + 1) * (luck+1)) + 10;
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

    
    

    //Strength Getter
    public static int getStrength(){
        int val = strength;
        for(triple<buffTypes, Integer, Integer> b : buffs){
            if(b.first == buffTypes.STRENGTH){
                val += b.third;
            }
        }

        for(equipables e : equippedItems){
            for(pair<buffTypes, Integer>buff : e.buffs){
                if(buff.first == buffTypes.STRENGTH){
                    val+= buff.second;
                }
            }
        }
        
        try{
            pair<buffTypes, Integer> buffPair = getArmorSetBuff();
            if(buffPair != null && buffPair.first != null && buffPair.first == buffTypes.STRENGTH){
                val += buffPair.second;
            }
            return val;
        }
        catch(Exception e){
            //If it fails to get the set buff then just return the armor value
            return val;
        }
    }

    //Agility Getter
    public static int getAgility(){
        int val = agility;
        for(triple<buffTypes, Integer, Integer> b : buffs){
            if(b.first == buffTypes.AGILITY){
                val += b.third;
            }
        }

        for(equipables e : equippedItems){

            for(pair<buffTypes, Integer>buff : e.buffs){
                if(buff.first == buffTypes.AGILITY){
                    val+= buff.second;
                }
            }
        }
        try{
            pair<buffTypes, Integer> buffPair = getArmorSetBuff();
            if(buffPair != null && buffPair.first != null && buffPair.first == buffTypes.AGILITY){
                val += buffPair.second;
            }
            return val;
        }
        catch(Exception e){
            //If it fails to get the set buff then just return the armor value
            return val;
        }
    }

    //Intelligence Getter
    public static int getIntelligence(){
        int val = intelligence;
        for(triple<buffTypes, Integer, Integer> b : buffs){
            if(b.first == buffTypes.INTELLIGENCE){
                val += b.third;
            }
        }

        for(equipables e : equippedItems){

            for(pair<buffTypes, Integer>buff : e.buffs){
                if(buff.first == buffTypes.INTELLIGENCE){
                    val+= buff.second;
                }
            }
        }
        try{
            pair<buffTypes, Integer> buffPair = getArmorSetBuff();
            if(buffPair != null && buffPair.first != null && buffPair.first == buffTypes.INTELLIGENCE){
                val += buffPair.second;
            }
            return val;
        }
        catch(Exception e){
            //If it fails to get the set buff then just return the armor value
            return val;
        }
    }

    public static int getArmor(){
        int val = armor;
        for(triple<buffTypes, Integer, Integer> b : buffs){
            if(b.first == buffTypes.ARMOR){
                val += b.third;
            }
        }

        for(equipables e : equippedItems){
            if(!(e instanceof holdables) && e != null){
                val += e.getArmorVal();
            }
        }
        for(equipables e : equippedItems){

            for(pair<buffTypes, Integer>buff : e.buffs){
                if(buff.first == buffTypes.ARMOR){
                    val+= buff.second;
                }
            }
        }

        try{
            pair<buffTypes, Integer> buffPair = getArmorSetBuff();
            if(buffPair != null && buffPair.first != null && buffPair.first == buffTypes.ARMOR){
                val += buffPair.second;
            }
            return val;
        }
        catch(Exception e){
            //If it fails to get the set buff then just return the armor value
            return val;
        }
    }


    //Getters and Setters!!
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
    public int getBankBalance(){
        return BankBalance;
    }
    public static void setName(String name) {
        player.name = name;
    }
    public static String getName(){
        return name;
    }





    ///////////////////////////
    /// 
    /// ADD Items To Player!!
    /// 
    /// ////////////////
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

    public static void printPlayerItems(){
        int printingNum = 1;
        for(item e : inventory){
            String s = printingNum + ": " + e.toString();
            if(e.getQuality() != null){
                if(((equipables)e).isEquipped() == true){
                    s += " - " + e.getQuality() + " - Equipped";
                }
                else s += " - " + e.getQuality();
                gui.printOnGameSide(s);
            }
            else{
                gui.printOnGameSide(s);
            }
            printingNum++;
        }
    }


    /////////////////////////////
    /// 
    /// Dont ask me how this works it just does probably
    /// Returns the pair with buffType and Integer if there is a buff; if not returns null;
    /// /////////////////////////
    private static pair<buffTypes, Integer> getArmorSetBuff(){

        // For every item that is equipped, get the class names for the set, check all equipped items for the class (anyMatch) if all are there return the class?
        for(equipables e : equippedItems){
            if(e instanceof holdables || e == null){
                continue;
            }

            Set<Class<? extends equipables>> setItems = e.getSetItems();
            if(setItems.isEmpty()){
                continue;
            }

            if(setItems.stream().allMatch(reqItem -> equippedItems.stream().anyMatch(eq -> eq.getClass() == reqItem))){
                return e.getSetBuff();
            }
        }
        return null;
    }


    //////////////////////////
    /// 
    /// Buffs
    /// 
    //////////////////////////
    
    

    public static void updateBuffs(){

        for(int i = 0; i < buffs.size(); i++){
            buffs.get(i).second--;
            if(buffs.get(i).second == 0){
                buffs.remove(i);
                i--;
            }
        }
        if(buffs.isEmpty()){
            isBuff = false;
        }
    }
    public static void applyBuff(buffTypes bType, int bLength, int bAmount){

        buffs.add(new triple<>(bType, bLength, bAmount));

        if(isBuff == false){
            isBuff = true;
        }
        gui.updatePlayerSide();
    }


    ///////////////////////////////////////////////////////////
    //Long ass function for asking and allocating skill points
    ///////////////////////////////////////////////////////////

    public static void playerPointAllocation(){

        int stpts = 0;
        int aglpts = 0;
        int intpts = 0;

        int totalpts = 0; //points spent
        while(totalpts < totalMaxStartingSkills){
            
            gui.printOnGameSide("How many points would you like to allocate to strength?");
            gui.printOnGameSide("You have " + (totalMaxStartingSkills-totalpts) + " left to spend");
                
            int temp = Integer.parseInt(gui.getInput());
            if(temp <= (totalMaxStartingSkills-totalpts)){
                stpts += temp;
                totalpts += temp;
                gui.printOnGameSide("You allocate " + stpts + " to strength!");
            }
            else{
                gui.printOnGameSide("You silly goose you don't have " + temp + " points to spend you only have " + (totalMaxStartingSkills - totalpts));
            }

            gui.printOnGameSide("How many points would you like to allocate to agility?");
            gui.printOnGameSide("You have " + (totalMaxStartingSkills-totalpts) + " left to spend");
            temp = Integer.parseInt(gui.getInput());
            if(temp <= (totalMaxStartingSkills - totalpts)){
                aglpts += temp;
                totalpts += temp;
                gui.printOnGameSide("You allocate " + aglpts + " to agility!");
            }
            else{
                gui.printOnGameSide("You silly goose you don't have " + temp + " points to spend you only have " + (totalMaxStartingSkills - totalpts));
            }
            gui.printOnGameSide("How many points would you like to allocate to intelligence?");
            gui.printOnGameSide("You have " + (totalMaxStartingSkills-totalpts) + " left to spend");
            temp = Integer.parseInt(gui.getInput());
            if(temp <= (totalMaxStartingSkills - totalpts)){
                intpts += temp;
                totalpts += temp;
                gui.printOnGameSide("You allocate " + intpts + " to intelligence!");
            }
            else{
                gui.printOnGameSide("You silly goose you don't have " + temp + " points to spend you only have " + (10 - totalpts));
            }

            if(totalpts < totalMaxStartingSkills){
                int r = 0;
                while(r == 0){
                    gui.printOnGameSide("\nYou have " + (totalMaxStartingSkills-totalpts) + " left to spend. \nWould you like to go back and add points to the attributes?");
                    String userResponse = gui.getInput();
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

    /////////////////////////////
    ///  Damage!
    /// ////////////////////////

    public static int damageTaken(monster m){
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

        //////
        /// Umm trekker tf were you doing here?
        monsterDamage = (int)(m.getStrength() * monsterMultiplyer);


        monsterDamage =(int)(monsterDamage / ((((double)getArmor() + 10) / 10)  * 1.25));


        if(monsterDamage >= health){
            death(m, monsterDamage);
        }
        gui.printOnGameSide(m.getName() + " " + m.attackString() + " for " + monsterDamage);
        m.attackEffects(monsterDamage);
        gui.updatePlayerSide();
        return monsterDamage;
    }
    ///// TODO: Change how crits work
    public static int damageDone(){
        double multiplier;
        boolean playerMiss = false;

        if(TrekkerMath.randomInt(100, (getIntelligence() * 3)) > 40){
            multiplier = TrekkerMath.randomDouble(2, 0);
            if(multiplier < .5){
                playerMiss = true;
            }
            else{
                playerMiss = false;
            }
        }
        else {
            multiplier = 1;
        }
        if(playerMiss == true){
            if(multiplier != 0){
                gui.printOnGameSide("You swing and only graze the monster but it still does damage.");
            }
            if(multiplier == 0){
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

        damageTypes LHandDMGType;
        damageTypes RHandDMGType;

        if(LHand == null){
            LHandDMGType = damageTypes.STRENGTH;
            LHandDMG = 0;
        }
        else{
            LHandDMGType = LHand.getDMGType();
            LHandDMG = getDMGCalcForWeapon(LHand);
        }
        if(RHand == null){
            RHandDMGType = damageTypes.STRENGTH;
            RHandDMG = 0;
        }
        else{
            RHandDMG = getDMGCalcForWeapon(RHand);
            RHandDMGType = RHand.getDMGType();
        }

        if(LHand == null && RHand == null){
            return (int)(getStrength() * multiplier);
        }


        if(LHandDMGType == RHandDMGType){
            int dmg = LHandDMG + RHandDMG;
            dmg *= 1.35;
            dmg *= multiplier;
            return dmg;
        }
        int dmg = LHandDMG + RHandDMG;
        dmg *= multiplier;
        return dmg;
    }

    private static int getDMGCalcForWeapon(holdables h){
        
        if(h.getDMGType() == damageTypes.INTELLIGENCE){
            return h.getItemDamage() + getIntelligence();
        }
        else if(h.getDMGType() == damageTypes.INTELLIGENCE){
            return h.getItemDamage() + getStrength();
        }
        else if(h.getDMGType() == damageTypes.AGILITY){
            return h.getItemDamage() + getAgility();
        }
        else{
            return getStrength();
        }
    }


    //////////////////////
    /// Levels
    /// //////////////////
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
        }
    }
    public static int getPlayerLevel(){
        return playerLevel;
    }
    public static void gainXP(int exp){
        xp += exp;
        while(xp >= xpToLevelUp){
            playerLevel++;
            levelPrompt();
        }
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
