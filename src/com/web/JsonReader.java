package com.web;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class JsonReader {

    public Map<String, String> readFromJson(){
        Map<String, String> valuesMap;
        InputStream stream = JsonReader.class.getResourceAsStream("/HomePageStats.json");
        if (stream == null) {
            throw new RuntimeException("Could not find json");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        try {
            valuesMap = new ObjectMapper().enable(JsonParser.Feature.ALLOW_COMMENTS).readValue(reader, HashMap.class);
        } catch (Exception e) {
            throw new RuntimeException("Was not able to parse portal URL's. Details: " + e.getMessage());
        }
        return valuesMap;
    }
}
