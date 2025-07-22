package com.textbasedgame.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

import com.textbasedgame.items.item;

public class TJSONDeserializer implements JsonDeserializer{
    @Override
    public item deserialize(JsonElement json, Type typeofT, JsonDeserializationContext context) throws JsonParseException{
        JsonObject jsonObject = json.getAsJsonObject();
        String itemType = jsonObject.get("classOfItem").getAsString();
        
        try{

            Class<? extends item> clazz = (Class<? extends item>) Class.forName("com.textbasedgame.items." + itemType);
            return context.deserialize(jsonObject, clazz);
        }
        catch(ClassNotFoundException e){
            throw new JsonParseException("Unknown Item Type in TJSONDeserializer " + itemType, e); 
        }
    }
}
