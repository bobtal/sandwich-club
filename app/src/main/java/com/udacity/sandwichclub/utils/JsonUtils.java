package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String JSON_NAME = "name";
    private static final String JSON_MAIN_NAME = "mainName";
    private static final String JSON_AKA = "alsoKnownAs";
    private static final String JSON_PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String JSON_DESCRIPTION = "description";
    private static final String JSON_IMAGE = "image";
    private static final String JSON_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonSandwich = new JSONObject(json);
            JSONObject jsonName = jsonSandwich.getJSONObject(JSON_NAME);
            String mainName = jsonName.getString(JSON_MAIN_NAME);
            List<String> alsoKnownAs = jsonToStringList(jsonName.getJSONArray(JSON_AKA));
            String placeOfOrigin = jsonSandwich.getString(JSON_PLACE_OF_ORIGIN);
            String description = jsonSandwich.getString(JSON_DESCRIPTION);
            String image = jsonSandwich.getString(JSON_IMAGE);
            List<String> ingredients = jsonToStringList(jsonSandwich.getJSONArray(JSON_INGREDIENTS));
            return new Sandwich(
                    mainName,
                    alsoKnownAs,
                    placeOfOrigin,
                    description,
                    image,
                    ingredients
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> jsonToStringList(JSONArray jsonArray) throws JSONException {
        List<String> stringList = new ArrayList<>(jsonArray.length());

        int listSize = stringList.size();
        for (int i = 0; i < listSize; i++) {
            stringList.add(jsonArray.getString(i));
        }

        return stringList;
    }
}
