package com.web;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class UrlManager {
    private static Map<String, Map<String, String>> urlMaps;

    protected static Map<String, String> initUrlMaps() {         //чтение с JSON файла
        BufferedReader reader = new BufferedReader(new InputStreamReader(BasePage.class.getResourceAsStream("/AllUrls.json")));
        try {
            urlMaps = new ObjectMapper().enable(JsonParser.Feature.ALLOW_COMMENTS).readValue(reader, HashMap.class);
        } catch (Exception e) {
            throw new RuntimeException("Was not able to parse portal URL's. Details: " + e.getMessage());
        }

        Map<String, String> map = urlMaps.get("MyAppPage");
        if (map == null) {
            throw new RuntimeException("Url map for '" + "MyAppPage" + "' was not found.");
        }
        return map;
    }
}
