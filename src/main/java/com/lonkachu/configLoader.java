package com.lonkachu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.FileNameMap;

/*
Auth Lonk: So, this is a pretty basic GSON configuration file java file, it pretty much attempts to read stackable.json, if it doesn't exist it creates stackable and sends it to the call that called this config
 */
public class configLoader {

    public static Config bootstrapConfig() throws IOException {

        Config config;

        try{
            config = configReader();
        } catch (FileNotFoundException e) {
            //If we cannot read the file, simply write the file using the default json writer
            config = configWriter();
        }

        return config;
    }

    private static Config configWriter() throws IOException {
        GsonBuilder b = new GsonBuilder();
        Gson gson = b.setPrettyPrinting().create();
        //gson.fieldNamingStrategy();
        FileWriter writer = new FileWriter("config/Stackable.json");

        //int maxStack = StackableMod.MAX_STACK;
        Config config = new Config(StackableMod.DEFAULT_STACK);


        writer.write(gson.toJson(config));
        writer.close();

        return config;
    }

    private static Config configReader() throws IOException {
        GsonBuilder b = new GsonBuilder();
        Gson gson = b.create();
        BufferedReader br = new BufferedReader(new FileReader("config/Stackable.json"));

        Config c = gson.fromJson(br, Config.class);
        if (c.GetOverrides() == null)
        {
            c = configWriter();
        }
        return c;
    }
}
