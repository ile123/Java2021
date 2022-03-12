package com.ile.voda;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;

public class JSONConvert {
    public static String SensorToJSON(Sensor sensor){
        ObjectMapper mapper = new ObjectMapper();
        String json = new String();
        try {
            json = mapper.writeValueAsString(sensor);
            System.out.println("" + json);
        } catch (JsonProcessingException error) {
            error.printStackTrace();
        }
        return json;
    }

    public static Sensor JSONToSensor(String JSON_String){
        Gson gson = new Gson();
        Sensor sensor = gson.fromJson(JSON_String, Sensor.class);
        return sensor;
    }
}
