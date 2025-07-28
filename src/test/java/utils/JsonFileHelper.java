package utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

public class JsonFileHelper {

    public File getJsonDataFile(String name) throws URISyntaxException {
        File jsonData = new File(this.getClass().getClassLoader().getResource(name).toURI());
        return jsonData;
    }

    public JsonObject addProperty(File jsonFile, String property, int value){
        String jsonDataAsString = null;
        try {
            jsonDataAsString = Files.readString(jsonFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        JsonObject jsonObj = gson.fromJson (jsonDataAsString, JsonElement.class).getAsJsonObject();
        jsonObj.addProperty(property, value);
        System.out.println(jsonObj.toString());
        return jsonObj;
    }

    public JsonObject addProperty(File jsonFile, String property, String value){
        String jsonDataAsString = null;
        try {
            jsonDataAsString = Files.readString(jsonFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        JsonObject jsonObj = gson.fromJson (jsonDataAsString, JsonElement.class).getAsJsonObject();
        jsonObj.addProperty(property, value);
        System.out.println(jsonObj.toString());
        return jsonObj;
    }

    public String jsonObjectToString(JsonObject jsonObject){
        Gson gson = new Gson();
        return gson.toJson(jsonObject);
    }


}
