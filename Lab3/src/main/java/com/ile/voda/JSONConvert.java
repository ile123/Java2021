package com.ile.voda;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JSONConvert {

    public static void SensorToJSON(WaterSensor sensor,String FilePath){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString =  gson.toJson(sensor) ;
        System.out.println(jsonString);
        try {
            //Parametarza za lokaciju jsona
            FileWriter file = new FileWriter(FilePath);
            file.write(jsonString);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static WaterSensor JSONToSensor(String JSONFile_pathname) {
        //potrebno da u json bude broker i topic
        //treba vratiti WaterSensor
        WaterSensor sensor = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(JSONFile_pathname));
            sensor = new Gson().fromJson(reader, new TypeToken<WaterSensor>() {}.getType());
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sensor;
    }

}
