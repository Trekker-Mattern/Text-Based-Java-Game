package com.textbasedgame.util;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class selectionMenu{
	/**
	 * @param ArrayList<objects> list - list of items 
	 * @param String response - user input
	 * @return Integer > 0 iff value is found or word matches items .toString() | 
	 * integer correlates to 1+index
	 * @return -1 if nothing is found
	 */
	public static Integer selectScreenToInteger(ArrayList<?> list, String response)  {
	
	
		try{
			Integer retVal = Integer.parseInt(response);
			if(retVal < 0 || retVal >= list.size()){
				return -1;
			}
			return retVal;
		}
		catch(NumberFormatException e){
			int count = 1;
			for (Object object : list) {
				if (object == null) continue;
				if (object.toString().toLowerCase().equals(response.strip().toLowerCase())) {
					return count;
				}
				count++;
			}
		}
		return -1;	
	}
	public static Integer selectScreenToInteger(Object[] list, String response)  {
	
	
		try{
			Integer retVal = Integer.parseInt(response);
			if(retVal < 0 || retVal >= list.length){
				return -1;
			}
			return retVal;
		}
		catch(NumberFormatException e){
			int count = 1;
			for (Object object : list) {
				if(object == null) continue;
				if (object.toString().toLowerCase().equals(response.strip().toLowerCase())) {
					return count;
				}
				count++;
			}
		}
		return -1;	
	}
}
