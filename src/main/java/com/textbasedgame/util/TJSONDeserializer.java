package com.textbasedgame.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import com.textbasedgame.items.item;

public class TJSONDeserializer implements JsonDeserializer<item>, JsonSerializer<item>{
    @SuppressWarnings("unchecked")
    @Override
    public item deserialize(JsonElement json, Type typeofT, JsonDeserializationContext context) throws JsonParseException{
        JsonObject jsonObject = json.getAsJsonObject();
        String itemType = jsonObject.get("classofItem").getAsString();
        
        try{
            Class<? extends item> clazz = (Class<? extends item>) Class.forName(itemType);
            return context.deserialize(jsonObject, clazz); 
        }
        catch(ClassNotFoundException e){
            throw new JsonParseException("Unknown Item Type in TJSONDeserializer " + itemType, e); 
        }
    }

    @Override
    public JsonElement serialize(item src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        return jsonObject; // Serialize the class name as a string
    }
    
}
