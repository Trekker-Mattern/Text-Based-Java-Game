package com.textbasedgame.world;

import com.textbasedgame.items.consumables;
import com.textbasedgame.items.equipables;
import com.textbasedgame.items.item;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.response;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.GUI.pictureLoader.imageIDs;
import com.textbasedgame.util.itemInfoPrinter;
import com.textbasedgame.util.selectionMenu;

public class shop {
    public static void openShop(){
        gui.setImage(imageIDs.SHOP);
        shopitems.printShop();

        gui.pushOldText();
		item[] shop = shopitems.getShopArray();
 
		gui.printOnGameSide("Would you like to purchase one of these items?");
		gui.printOnGameSide("You can also sell items by typing sell!");
		String userInput = gui.getInput();      	

		
        if(response.respondYes(userInput)){interactWithShop(shop, gui.getInput("Which item would you like to purchase?"));return;}
		else if(response.respondSell(userInput)){sellMenu();return;}
		interactWithShop(shop, userInput);	
		gui.pushOldText(); 
    }



    private static boolean buyConfirmationMenu(item i){
		if(i == null)
			return false;
        if(i instanceof consumables){
            itemInfoPrinter.printConsumableInfo((consumables)i);
        }
        else if(i instanceof equipables){
            itemInfoPrinter.printEquipablesInfo((equipables) i);
        }

        gui.printOnGameSide("-------------------------------------");
        gui.newlOnGameSide();
        gui.printOnGameSide("Are you sure you want to buy " + i.getItemName() + " for " + i.getPrice() + " shmeckles?");
        gui.printOnGameSide("1: Yes");
        gui.printOnGameSide("2: No");

        String input = gui.getInput();

        if(input.equals("1") || response.respondYes(input)){
            return true;
        }
        else{
            return false;
        }
    }

	private static void sellMenu(){
		player.printPlayerItems();
		gui.printOnGameSide("Which item would you like to sell?");
		try{
			int itemSell = Integer.parseInt(gui.getInput());
			itemSell--; //adjust for 0 index
			if(player.inventory.get(itemSell) instanceof equipables && ((equipables)player.inventory.get(itemSell)).isEquipped()){
				player.inventory.get(itemSell).Use();
			}
			int sellPrice = (int)(player.inventory.get(itemSell).getPrice() * .75);
			player.gold += sellPrice;
			gui.printOnGameSide("You sell " + player.inventory.get(itemSell).getItemName() + " for " + sellPrice + " shmeckles");
			player.inventory.remove(player.inventory.get(itemSell));
			return;
		}
		catch(IndexOutOfBoundsException e){
			gui.printOnGameSide("You dont have that many items you goof!");
			return;
		}
		catch(NumberFormatException e){return;}
	 
	}

	private static void interactWithShop(item[] shop, String userInput){

		int selectionValue = selectionMenu.selectScreenToInteger(shop, userInput);
		if (selectionValue == -1 || shop[selectionValue-1] == null) return;
		item purchasedItem = shop[selectionValue - 1];
		boolean buyConfirm = buyConfirmationMenu(purchasedItem);

		if (buyConfirm == false){
			gui.printOnGameSide("You decided not to buy " + purchasedItem.toString() + ".");
			return;
		}
		if(player.gold >= purchasedItem.getPrice()){
			shopitems.buyItem(selectionValue);
			gui.newlOnGameSide();
			
		}
		else{
			gui.printOnGameSide("You dont have enough money to buy that!");
			gui.printOnGameSide("You only have " + player.gold + " shmeckles.");
		}
	}


}
