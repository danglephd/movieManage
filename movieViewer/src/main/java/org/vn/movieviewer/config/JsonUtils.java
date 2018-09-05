/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 *
 * @author danglph
 */
public class JsonUtils {
    private static JsonParser jsonParser = new JsonParser();
    
    public static String object2Json(Object data) {

        Gson gson = new GsonBuilder()
                .create();
        return gson.toJson(data);
    }

    public static <T> T jSon2Object(JsonElement jsonElement, Class<T> enClass) {

        Gson gson = new GsonBuilder()
                .create();
        return gson.fromJson(jsonElement, enClass);
    }
    
    public static JsonElement parseStr(String response){
         return jsonParser.parse(response);
    }
    
}
